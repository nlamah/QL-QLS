package org.nlamah.QL.Model.Expression.Literal;

import org.nlamah.QL.Interfaces.QLNodeVisitor;
import org.nlamah.QL.Model.Expression.Abstract.ValueExpression;
import org.nlamah.QL.Model.Form.Abstract.QLNode;
import org.nlamah.QL.Model.Form.Abstract.LiteralType;

public class BooleanLiteral extends ValueExpression
{	
	public BooleanLiteral(String booleanValueString)
	{
		super(booleanValueString, LiteralType.BOOLEAN);	
	}
	
	public boolean primitiveValue()
	{
		return valueString.equals("yes") ? true : false; 
	}

	@Override
	public QLNode accept(QLNodeVisitor visitor) 
	{
		return visitor.visit(this);
	}

	
}
