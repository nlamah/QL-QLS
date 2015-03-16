﻿using AST.Nodes.Interfaces;
using AST.Representation;

namespace AST.Nodes.Expressions
{
    public class Id : BaseExpression, IHasType
    {
        public string Name { get; private set; }
        private Types.Type type = new Types.UndefinedType();
        
        public Id(string name, PositionInText position)
            : base(position)
        {
            this.Name = name;
        }

        public override T Accept<T>(ASTVisitors.IVisitor<T> visitor)
        {
            return visitor.Visit(this);
        }

        public override T Accept<T>(ASTVisitors.Interfaces.IExpressionVisitor<T> visitor)
        {
            throw new System.NotImplementedException();
        }

        public Types.Type RetrieveType()
        {
            return this.type;
        }

        public void SetType(Types.Type type)
        {
            this.type = type;
        }

        public override bool Equals(object obj)
        {
            return (this.Name.Equals(((Id)obj).Name));
        }

        public override int GetHashCode()
        {
            return this.Name.GetHashCode();
        }
    }
}
