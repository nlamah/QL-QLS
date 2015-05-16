package org.nlamah.QL.Model.Expression.Literal;

import org.nlamah.QBase.QBaseQuestionType;
import org.nlamah.QL.Model.Form.Abstract.QLNode;
import org.nlamah.QL.Interfaces.QLNodeVisitor;
import org.nlamah.QL.Model.Expression.Abstract.ValueExpression;

public class NumberLiteral extends ValueExpression 
{	
	public NumberLiteral(int number)
	{
		this(Integer.toString(number));
	}
	
	public NumberLiteral(String numberValueString)
	{
		super(numberValueString, QBaseQuestionType.NUMBER);
	}
	
	public int value()
	{
		return Integer.parseInt(valueString);
	}
	
	@Override
	public QLNode accept(QLNodeVisitor visitor) 
	{
		return visitor.visit(this);
	}
}
