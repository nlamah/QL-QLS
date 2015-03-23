﻿using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics.Contracts;
using System.Linq;
using QL.AST;
using QL.AST.Nodes;
using QL.AST.Nodes.Branches;
using QL.AST.Nodes.Branches.Operators;
using QL.AST.Nodes.Terminals;
using QL.AST.ValueWrappers;
using QL.Exceptions;
using QL.Exceptions.Errors;

namespace QL.Hollywood.DataHandlers.Evaluation
{
    public class EvaluatorVisitor : IVisitor
    {
        public IList<QLBaseException> Exceptions { get; private set; }
        
        public readonly IDictionary<ITypeResolvable, ITerminalWrapper> ReferenceLookupTable; // a lookup of references to terminals
        public readonly IDictionary<Identifier, ITypeResolvable> IdentifierLookupTable; // a lookup of identifiers to resolvable types
        private EvaluationTerminalWrapperFactory _evaluationTerminalWrapperFactory;

        public IDictionary<ITypeResolvable, ITerminalWrapper> GetValuesIfNoErrors()
        {
            return Exceptions.Any() ? null : ReferenceLookupTable;
        }

        
        public EvaluatorVisitor(ObservableCollection<QLBaseException> exceptions, IDictionary<ITypeResolvable, ITerminalWrapper> referenceTable, IDictionary<Identifier, ITypeResolvable> identifierTable)
        {
            Exceptions = exceptions;
            _evaluationTerminalWrapperFactory = new EvaluationTerminalWrapperFactory();       
            ReferenceLookupTable = referenceTable;
            IdentifierLookupTable = identifierTable;

        }

        

        #region Regular elements
        public void Visit(Form node)
        {
           node.Block.Accept(this);
           
        }

        public void Visit(Block node)
        {
            foreach (ElementBase child in node.Children)
            {
                child.Accept(this);
            }
        }

        public void Visit(ControlUnit node)
        {
            node.Expression.Accept(this);
            YesnoWrapper conditionstate= ReferenceLookupTable[node.Expression] as YesnoWrapper;
            if (node.ConditionTrueBlock != null && conditionstate.Value.HasValue && conditionstate.Value.Value)
            {
                node.ConditionTrueBlock.Accept(this);
            }
            if (node.ConditionFalseBlock != null && conditionstate.Value.HasValue && !conditionstate.Value.Value)
            {
                node.ConditionFalseBlock.Accept(this);
            }

        }

        public void Visit(StatementUnit node)
        {
            node.Expression.Accept(this);

            IdentifierLookupTable[node.Identifier] = node.Expression;

        }

        public void Visit(QuestionUnit node)
        {
            IdentifierLookupTable[node.Identifier] = node.DataType;
            if (!ReferenceLookupTable.ContainsKey(node.DataType)) { 
                ReferenceLookupTable[node.DataType]= _evaluationTerminalWrapperFactory.CreateWrapper(node.DataType);
            }
        }


        ITerminalWrapper _GetValueFor(Identifier i) 
        {
            if (IdentifierLookupTable.ContainsKey(i) && ReferenceLookupTable.ContainsKey(IdentifierLookupTable[i]))
            {
                return ReferenceLookupTable[IdentifierLookupTable[i]];
            }
            else if (IdentifierLookupTable.ContainsKey(i))
            {
                throw new Exception("reference not initialized");
            }
            else
            {
                throw new QLError("Usage of a variable before declaration");
            }
        }
        ITerminalWrapper _GetValueFor(ITypeResolvable i)
        {
            if (ReferenceLookupTable.ContainsKey(i))
            {
                return ReferenceLookupTable[i];
            }
            else{
                throw new Exception("how is this possible? there should be at least null valued wrapper");
            }
        
        }
        

        public void Visit(Expression node)
        {

            //if expression is literal
            if(node.Child== null)
            {
                throw new Exception("Expression should have one and only one child");
            }

            node.Child.Accept(this);

            ReferenceLookupTable[node] = _GetValueFor((dynamic)node.Child);
        }

        #endregion
        void _VisitBinary(BinaryTreeElementBase node)
        {
            node.Left.Accept(this);
            node.Right.Accept(this);
        }

        #region Operators
        public void Visit(EqualsOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];

            ReferenceLookupTable[node] = ((dynamic)leftWrapper) == ((dynamic)rightWrapper);
            
        }

        public void Visit(NotEqualsOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) != ((dynamic)rightWrapper);
        }

        public void Visit(GreaterThanOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
                ReferenceLookupTable[node] = ((dynamic)leftWrapper) > ((dynamic)rightWrapper);
        }

        public void Visit(GreaterThanEqualToOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
                ReferenceLookupTable[node] = ((dynamic)leftWrapper) >= ((dynamic)rightWrapper);
        }

        public void Visit(LessThanOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
                ReferenceLookupTable[node] = ((dynamic)leftWrapper) < ((dynamic)rightWrapper);
        }

        public void Visit(LessThanEqualToOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) <= ((dynamic)rightWrapper);
        }

        public void Visit(MultiplicationOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) * ((dynamic)rightWrapper);
            
        }

        public void Visit(DivisionOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) / ((dynamic)rightWrapper);
        }

        public void Visit(PlusOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) + ((dynamic)rightWrapper);
            
        }

        public void Visit(MinusOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) - ((dynamic)rightWrapper);
        }

        public void Visit(AndOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) & ((dynamic)rightWrapper);
         }

        public void Visit(OrOperator node)
        {
            _VisitBinary(node);

            ITerminalWrapper leftWrapper = ReferenceLookupTable[(ITypeResolvable)node.Left];
            ITerminalWrapper rightWrapper = ReferenceLookupTable[(ITypeResolvable)node.Right];
            ReferenceLookupTable[node] = ((dynamic)leftWrapper) | ((dynamic)rightWrapper);
        }
        #endregion

        #region Terminals
        public void Visit(Number node)
        {
            ReferenceLookupTable[node] = new NumberWrapper(node);
            
        }

        public void Visit(Yesno node)
        {
            ReferenceLookupTable[node] = new YesnoWrapper(node);


        }

        public void Visit(Text node)
        {
            ReferenceLookupTable[node] = new TextWrapper(node);

        }

        public void Visit(Identifier node)
        {            
        }

        public void Visit(ElementBase node)
        {
            throw new QLError("Not implemented: " + node.GetType().ToString());
        }
        #endregion


    }
}
