package org.nlamah.QL.Binary;

import org.nlamah.QL.Expression.Expression;
import org.nlamah.QL.Expression.ExpressionVisitor;
import org.nlamah.QL.Literal.ValueExpression;

public class UnEqualExpression extends BinaryLogicalExpression 
{
	public UnEqualExpression(Expression leftHandExpression, Expression rightHandExpression) 
	{
		super(leftHandExpression, rightHandExpression);
	}
	
	@Override
	public ValueExpression accept(ExpressionVisitor visitor) 
	{
		return visitor.evaluate(this);	
	}
}
