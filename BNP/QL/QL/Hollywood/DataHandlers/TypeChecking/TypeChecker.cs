﻿using System.Linq;
using QL.Exceptions.Errors;

namespace QL.Hollywood.DataHandlers.TypeChecking
{
    class TypeChecker :IExecutable
    {

        public TypeChecker() { }
        public bool execute(DataContext context)
        {
            try
            {
            TypeCheckerVisitor typeChecker = new TypeCheckerVisitor(context.TypeReference, context.ASTHandlerExceptions);
            
                context.RootNode.Accept(typeChecker);
            }
            catch (QLError ex)
            {
                /* Exceptions preventing TypeChecker from finishing */
                context.ASTHandlerExceptions.Add(ex);
                return false;
            }

            return !context.ASTHandlerExceptions.Any();
        }
    }
}
