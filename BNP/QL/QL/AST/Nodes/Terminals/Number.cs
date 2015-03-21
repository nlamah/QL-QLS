﻿using System;

namespace QL.AST.Nodes.Terminals
{
    public class Number : BinaryTreeElementBase, IResolvableTerminalType
    {
        public int? Value { get; set; }

        public Number()
        {}

        public void SetValue(object value)
        {
            Value = Int32.Parse(value.ToString());
        }

        public Type GetReturnType()
        {
            return GetType();
        }

        public override string ToString()
        {
            if (!Value.HasValue)
            {
                throw new Exception();
            }
            
            return Value.ToString();
        }
    }
}
