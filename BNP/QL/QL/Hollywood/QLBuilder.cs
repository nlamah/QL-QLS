﻿using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using QL.Exceptions;
using QL.Exceptions.Errors;
using QL.Hollywood.DataHandlers;
using QL.Hollywood.DataHandlers.ASTCreation;
using QL.Hollywood.DataHandlers.Evaluation;
using QL.Hollywood.DataHandlers.InputHandling;
using QL.Hollywood.DataHandlers.TypeChecking;

namespace QL.Hollywood
{
    /// <summary>
    /// This is the god class.
    ///  After putting source as into the constructor, this class accumulates all the information
    ///  made by evaluator, type checker ...
    ///  Is this good, bad? 
    /// </summary>
    public class QLBuilder
    {
        protected IList<IExecutable> Initializers;
        protected IList<IExecutable> ASTBuilders;
        protected IList<IExecutable> TypeCheckers;
        protected IList<IExecutable> Evaluators;
        protected IList<IExecutable> Renderers;
        protected IList<IExecutable> Exporters;
        public bool InputSet;
        public bool AstBuilt;
        public bool TypeChecked;
        public bool Evaluated;
        public bool Rendered;

        public readonly DataContext DataContext; //needs to be public because of tests
        
        public IList<Exception> UnhandledExceptions { get; private set; }
        public ObservableCollection<QLBaseException> QLExceptions
        {
            get { return new ObservableCollection<QLBaseException>(Enumerable.Concat(DataContext.ASTHandlerExceptions, DataContext.ASTHandlerWarnings)); }
        }
        
        public QLBuilder()
        {
            Initializers = new List<IExecutable>();
            ASTBuilders = new List<IExecutable>();
            TypeCheckers = new List<IExecutable>();
            Evaluators = new List<IExecutable>();
            Renderers = new List<IExecutable>();
            Exporters = new List<IExecutable>();
            InputSet = AstBuilt = TypeChecked = Evaluated = Rendered = false;

            DataContext = new DataContext();
            UnhandledExceptions = new List<Exception>();
        }

        public QLBuilder(string input) : this()
        {
            SetInput(input);
        }

        public QLBuilder(Stream input) : this()
        {
            SetInput(input);
        }

        public void RegisterInitializer(IExecutable handler)
        {
            Initializers.Add(handler);
        }

        public void RegisterASTBuilder(IExecutable handler)
        {
            ASTBuilders.Add(handler);
        }

        public void RegisterTypeChecker(IExecutable handler)
        {
            TypeCheckers.Add(handler);
        }

        public void RegisterEvaluator(IExecutable handler)
        {
            Evaluators.Add(handler);
        }

        public void RegisterRenderer(IExecutable handler)
        {
            Renderers.Add(handler);
        }

        public void RegisterExporter(IExecutable handler)
        {
            Exporters.Add(handler);
        }


        private bool RunOneLevel(IEnumerable<IExecutable> thisLevelHandlers)
        {
            bool successfulExecution = true;

            foreach (IExecutable handler in thisLevelHandlers)
            {
                try
                {
                    successfulExecution = handler.execute(DataContext);
                }
                catch (QLBaseException ex)
                {
                    DataContext.ASTHandlerExceptions.Add(ex);
                    successfulExecution = false;

                }
                catch (Exception ex)
                {
                    //not known exception!
                    UnhandledExceptions.Add(ex);
                    successfulExecution = false;

                }

                if (!successfulExecution)
                {
                    break;
                }
            }

            return successfulExecution;
        }

        public bool RunInit()
        {
            InputSet = RunOneLevel(Initializers);
            return InputSet;
        }

        public bool RunASTBuilders()
        {
            if (!InputSet)
            {
                DataContext.ASTHandlerExceptions.Add(new QLError("previous step not completed successfuly"));
                return false;
            }

            AstBuilt = RunOneLevel(ASTBuilders);
            return AstBuilt;
        }

        public bool RunTypeCheckers()
        {

            if (!AstBuilt)
            {
                DataContext.ASTHandlerExceptions.Add(new QLError("previous step not completed successfuly"));
                return false;
            }
            TypeChecked = RunOneLevel(TypeCheckers);
            return TypeChecked;

        }

        public bool RunEvaluators()
        {

            if (!TypeChecked)
            {
                DataContext.ASTHandlerExceptions.Add(new QLError("previous step not completed successfuly"));
                return false;
            }
            Evaluated = RunOneLevel(Evaluators);
            return Evaluated;

        }
      

        public bool RunRenderers()
        {

            if (!Evaluated)
            {
                DataContext.ASTHandlerExceptions.Add(new QLError("previous step not completed successfuly"));
                return false;
            }
            Rendered = RunOneLevel(Renderers);
            return Rendered;

        }

        public bool RunExporters()
        {

            if (!Evaluated)
            {
                DataContext.ASTHandlerExceptions.Add(new QLError("Evaluation not completed successfuly"));
                return false;
            }
            return RunOneLevel(Exporters);

        }

        /// <summary>
        /// Runs all registrerd handlers. If any error occurs, returns false but continues running if possible.
        /// </summary>
        public bool RunAllHandlers()
        {
            bool retVal = false;
            retVal |= RunInit();
            retVal |= RunASTBuilders();
            retVal |= RunTypeCheckers();
            retVal |= RunEvaluators();
            retVal |= RunRenderers();
            retVal |= RunExporters();
            return retVal;
        }

        public void RegisterGenericDataHandlers()
        {
            RegisterInitializer(new Initializer());
            RegisterASTBuilder(new ASTBuilder());
            RegisterTypeChecker(new TypeChecker());
            RegisterEvaluator(new Evaluator());
        }
        
        public void SetInput(string input)
        {
            DataContext.Input = input;
        }

        public void SetInput(Stream input)
        {
            DataContext.InputStream = input;
        }
    }
}
