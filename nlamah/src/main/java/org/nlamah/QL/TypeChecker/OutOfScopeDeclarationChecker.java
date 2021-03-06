package org.nlamah.QL.TypeChecker;

import java.util.List;

import org.nlamah.QL.Model.Form.Abstract.QLNode;
import org.nlamah.QL.Error.CyclicDependencyError;
import org.nlamah.QL.Error.OutOfScopeDeclarationError;
import org.nlamah.QBase.Tools.ArrayTools;
import org.nlamah.QBase.Tools.QLTools;
import org.nlamah.QBase.TypeChecker.QBaseAbstractTypeChecker;
import org.nlamah.QL.Interfaces.QLNodeVisitor;
import org.nlamah.QL.Model.Expression.Binary.AddExpression;
import org.nlamah.QL.Model.Expression.Binary.AndExpression;
import org.nlamah.QL.Model.Expression.Binary.DivideExpression;
import org.nlamah.QL.Model.Expression.Binary.EqualExpression;
import org.nlamah.QL.Model.Expression.Binary.GreaterThanEqualExpression;
import org.nlamah.QL.Model.Expression.Binary.GreaterThanExpression;
import org.nlamah.QL.Model.Expression.Binary.MultiplyExpression;
import org.nlamah.QL.Model.Expression.Binary.OrExpression;
import org.nlamah.QL.Model.Expression.Binary.SmallerThanEqualExpression;
import org.nlamah.QL.Model.Expression.Binary.SmallerThanExpression;
import org.nlamah.QL.Model.Expression.Binary.SubtractExpression;
import org.nlamah.QL.Model.Expression.Binary.UnEqualExpression;
import org.nlamah.QL.Model.Expression.Literal.BooleanLiteral;
import org.nlamah.QL.Model.Expression.Literal.IdentifierLiteral;
import org.nlamah.QL.Model.Expression.Literal.NumberLiteral;
import org.nlamah.QL.Model.Expression.Literal.TextLiteral;
import org.nlamah.QL.Model.Expression.Unary.MinusExpression;
import org.nlamah.QL.Model.Expression.Unary.NotExpression;
import org.nlamah.QL.Model.Expression.Unary.PlusExpression;
import org.nlamah.QL.Model.Form.ComputedQuestion;
import org.nlamah.QL.Model.Form.ConditionalBlock;
import org.nlamah.QL.Model.Form.ElseIfThenBlock;
import org.nlamah.QL.Model.Form.ElseThenBlock;
import org.nlamah.QL.Model.Form.Form;
import org.nlamah.QL.Model.Form.IfThenBlock;
import org.nlamah.QL.Model.Form.InputQuestion;
import org.nlamah.QL.Model.Form.Abstract.FormElement;
import org.nlamah.QL.Model.Form.Abstract.FormQuestion;

public class OutOfScopeDeclarationChecker extends QBaseAbstractTypeChecker implements QLNodeVisitor 
{
	private IdentifierLiteral identifier;

	public OutOfScopeDeclarationChecker(IdentifierLiteral identifier)
	{
		this.identifier = identifier;

		identifier.accept(this);
	}

	private FormQuestion isIdentifierDeclaredHere(List<FormElement> childElements) 
	{	
		FormQuestion declaredQuestion = null;

		if (ArrayTools.arrayExistsAndHasElements(childElements))
		{
			for (FormElement childElement : childElements)
			{
				if (childElement.identifier() != null)
				{
					if (childElement.identifier().equals(identifier))
					{		
						assert childElement instanceof FormQuestion;

						declaredQuestion = (FormQuestion)childElement;

						identifier.setCorrespondingQuestion(declaredQuestion);
					}
				}
			}
		}

		return declaredQuestion;
	}

	@Override
	public QLNode visit(AddExpression addExpression) 
	{
		return addExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(AndExpression andExpression) 
	{
		return andExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(DivideExpression divideExpression) 
	{
		return divideExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(EqualExpression equalExpression) 
	{
		return equalExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(GreaterThanExpression greaterThanExpression) 
	{
		return greaterThanExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(GreaterThanEqualExpression greaterThanEqualExpression) 
	{
		return greaterThanEqualExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(MultiplyExpression multiplyExpression) 
	{
		return multiplyExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(OrExpression orExpression) 
	{
		return orExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(SmallerThanExpression smallerThanExpression) 
	{
		return smallerThanExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(SmallerThanEqualExpression smallerThanEqualExpression) 
	{
		return smallerThanEqualExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(SubtractExpression subtractExpression) 
	{
		return subtractExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(UnEqualExpression unEqualExpression) 
	{
		return unEqualExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(BooleanLiteral booleanLiteral) 
	{
		assert(false);
		return null;
	}

	@Override
	public QLNode visit(IdentifierLiteral identifierLiteral) 
	{		
		return identifierLiteral.parentNode().accept(this);
	}

	@Override
	public QLNode visit(NumberLiteral numberLiteral) 
	{
		assert(false);
		return null;
	}

	@Override
	public QLNode visit(TextLiteral textLiteral) 
	{
		assert(false);
		return null;
	}

	@Override
	public QLNode visit(MinusExpression minusExpression) 
	{
		return minusExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(NotExpression notExpression) 
	{
		return notExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(PlusExpression plusExpression) 
	{
		return plusExpression.parentNode().accept(this);
	}

	@Override
	public QLNode visit(ConditionalBlock conditionalBlock) 
	{	
		return conditionalBlock.parentNode().accept(this);
	}

	@Override
	public QLNode visit(ElseIfThenBlock elseIfThenBlock) 
	{
		FormQuestion declaredQuestion = isIdentifierDeclaredHere(elseIfThenBlock.childElements());

		if (declaredQuestion != null)
		{
			return declaredQuestion;
		}

		return elseIfThenBlock.parentNode().accept(this);	
	}

	@Override
	public QLNode visit(ElseThenBlock elseThenBlock) 
	{	
		FormQuestion declaredQuestion = isIdentifierDeclaredHere(elseThenBlock.childElements());

		if (declaredQuestion != null)
		{
			return declaredQuestion;
		}

		return elseThenBlock.parentNode().accept(this);
	}

	@Override
	public QLNode visit(Form form) 
	{
		FormQuestion declaredQuestion =  isIdentifierDeclaredHere(form.childElements());

		if (declaredQuestion == null)
		{
			FormQuestion outOfScopeQuestion = QLTools.getQuestionWithIdentifier(form.questions(), this.identifier);

			assert(outOfScopeQuestion != null);

			errors.add(new OutOfScopeDeclarationError(this.identifier, outOfScopeQuestion));
		}

		return declaredQuestion;
	}

	@Override
	public QLNode visit(IfThenBlock ifThenBlock) 
	{
		FormQuestion declaredQuestion = isIdentifierDeclaredHere(ifThenBlock.childElements());

		if (declaredQuestion != null)
		{	
			return declaredQuestion;
		}

		return ifThenBlock.parentNode().accept(this);
	}

	@Override
	public QLNode visit(InputQuestion inputQuestion) 
	{
		assert(false);
		return null;
	}

	@Override
	public QLNode visit(ComputedQuestion computedQuestion) 
	{	
		if (computedQuestion.identifier().equals(identifier))
		{
			errors.add(new CyclicDependencyError(identifier, computedQuestion));
		}

		return computedQuestion.parentNode().accept(this);
	}
}