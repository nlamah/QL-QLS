using System;
using System.Runtime.Serialization;
using QL.AST;
using QL.AST.Nodes;

namespace QL.Exceptions
{
    public class QLBaseException : Exception
        /* Base class for all QL related errors and warnings.
         * Warning does not change the flow of the program.
         * Error changes the flow of the program.
         * 
         */
    {
        public SourceLocation SourceLocation { get; protected set; }

        public virtual string Origin
        {
            get { return GetType().Name; }
        }

        public virtual string Severity
        {
            get
            {
                string typeName = GetType().Name.ToLowerInvariant();
                return typeName.Contains("warning") ? "Warning" : typeName.Contains("error") ? "Error" : "Exception";
            }
        }

        public QLBaseException()
        { }

        public QLBaseException(string message) : base(message)
        { }

        public QLBaseException(string message, ElementBase source) : base(message)
        {
            SourceLocation = source.SourceLocation;
        }

        public QLBaseException(string message, SourceLocation source) : base(message)
        {
            SourceLocation = source;
        }

        public QLBaseException(string message, Exception inner) : base(message, inner)
        { }

        public override string ToString()
        {
            return string.Format("{0} '{1}' @ {2}", Message, SourceLocation.Source, SourceLocation);
        }
    }
}