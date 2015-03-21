﻿using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using Antlr4.Runtime;
using QL.AST.Nodes;
using QL.AST.Nodes.Branches;
using QL.AST.Nodes.Terminals;
using QL.AST.ValueWrappers;
using QL.Exceptions;
using QL.Exceptions.Warnings;

namespace QL.Hollywood
{
    public class DataContext
    {
        public string Input;
        public Stream InputStream;
        public AntlrInputStream AntlrInput; //Input used in parser

        public Form RootNode; //AST root node, entry point for all visitors

        /// <summary>
        /// A collection of all errors and warnings occurring during all stages of interpreting input grammar
        /// </summary>
        public ObservableCollection<QLBaseException> ASTHandlerExceptions { get; private set; }
        /// <summary>
        /// A collection of all warnings occurring during all stages of interpreting input grammer
        /// </summary>
        public ObservableCollection<QLWarning> ASTHandlerWarnings { get; private set; }

        /// <summary>
        /// Maps defined identifiers to the datatype of the value they contain
        /// </summary>
        public IDictionary<Identifier, Type> TypeReference { get; private set; }
        /// <summary>
        /// 
        /// </summary>
        public IDictionary<ITypeResolvable, ITerminalWrapper> ReferenceLookupTable { get; private set; } // a lookup of references to terminals
        public IDictionary<Identifier, ITypeResolvable> IdentifierTable;

      
        public DataContext(){
            ASTHandlerExceptions = new ObservableCollection<QLBaseException>();
            ASTHandlerWarnings = new ObservableCollection<QLWarning>();
            TypeReference = new Dictionary<Identifier, Type>();
            ReferenceLookupTable = new Dictionary<ITypeResolvable, ITerminalWrapper>();
            IdentifierTable = new Dictionary<Identifier, ITypeResolvable>();

        }
        public DataContext(string input):this()
        {
            Input=input;        
        }
        public DataContext(Stream input):this()
        {
            InputStream=input;
        }

        
       
        public ITerminalWrapper GetWrappedValue(string IdentifierName)
        {
            //convenience method for getting the Terminal wrapper based on identifier name. 
            return GetWrappedValue(new Identifier(IdentifierName));
        }
        public ITerminalWrapper GetWrappedValue(Identifier i)
        {
            //convenience method for getting the Terminal wrapper based on Identifier node. 
           
            if (IdentifierTable.ContainsKey(i) && ReferenceLookupTable.ContainsKey(IdentifierTable[i]))
            { 
                return ReferenceLookupTable[IdentifierTable[i]];
            }
            else
            {
                return null;
            }
        }

    }
}
