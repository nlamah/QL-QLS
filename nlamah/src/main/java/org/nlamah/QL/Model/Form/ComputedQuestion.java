package org.nlamah.QL.Model.Form;

import org.nlamah.QBase.Constants.QBaseEqualityState;
import org.nlamah.QBase.Constants.QBaseQuestionType;
import org.nlamah.QL.Model.Form.Abstract.QLNode;
import org.nlamah.QL.Interfaces.QLFormElementVisitor;
import org.nlamah.QL.Interfaces.QLNodeVisitor;
import org.nlamah.QL.Model.Expression.Abstract.Expression;
import org.nlamah.QL.Model.Expression.Abstract.ValueExpression;
import org.nlamah.QL.Model.Expression.Evalutation.ExpressionEvaluator;
import org.nlamah.QL.Model.Expression.Literal.IdentifierLiteral;
import org.nlamah.QL.Model.Expression.Literal.TextLiteral;
import org.nlamah.QL.Model.Form.Abstract.FormQuestion;

public class ComputedQuestion extends FormQuestion 
{
	private Expression expression;

	public ComputedQuestion(IdentifierLiteral identifier, TextLiteral questionText, QBaseQuestionType type, Expression expression) 
	{
		super(identifier, questionText, type);

		this.expression = expression;

		expression.setParentNode(this);
	}

	public Expression expression()
	{
		return expression;
	}

	public ValueExpression computedValue() 
	{
		ExpressionEvaluator evalution = new ExpressionEvaluator();

		return (ValueExpression) expression.accept(evalution);
	}

	@Override 
	public boolean equals(Object object) 
	{
		switch (equalityStateStack.peek())
		{
		case IDENTIFIER_ONLY:
		{
			if (!super.equals(object))
			{
				return false;
			}

			break;
		}

		case QUESTIONTEXT_ONLY:
		{
			if (!super.equals(object))
			{
				return false;
			}

			break;
		}
		default:
		{
			if (!super.equals(object))
			{
				return false;
			}

			if (!(object instanceof ComputedQuestion))
			{
				return false;
			}

			ComputedQuestion value = (ComputedQuestion)object;

			if (!(this.expression.equals(value.expression)))
			{
				return false;
			}

			break;
		}
		}

		return true;
	}

	@Override
	public QLNode accept(QLNodeVisitor visitor) 
	{
		return visitor.visit(this);
	}

	@Override
	public void accept(QLFormElementVisitor visitor) 
	{
		visitor.visit(this);	
	}

	@Override
	public void push(QBaseEqualityState state) 
	{
		equalityStateStack.push(state);
	}

	@Override
	public QBaseEqualityState popState() 
	{
		return equalityStateStack.pop();
	}
}