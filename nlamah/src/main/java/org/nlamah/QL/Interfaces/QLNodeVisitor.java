package org.nlamah.QL.Interfaces;

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
import org.nlamah.QL.Model.Form.Abstract.QLNode;

public interface QLNodeVisitor
{
	public QLNode visit(AddExpression addExpression);
	public QLNode visit(AndExpression andExpression);
	public QLNode visit(DivideExpression divideExpression);
	public QLNode visit(EqualExpression equalExpression);
	public QLNode visit(GreaterThanExpression greaterThanExpression);
	public QLNode visit(GreaterThanEqualExpression greaterThanEqualExpression);
	public QLNode visit(MultiplyExpression multiplyExpression);
	public QLNode visit(OrExpression orExpression);
	public QLNode visit(SmallerThanExpression smallerThanExpression);
	public QLNode visit(SmallerThanEqualExpression smallerThanEqualExpression);
	public QLNode visit(SubtractExpression subtractExpression);
	public QLNode visit(UnEqualExpression unEqualExpression);
	public QLNode visit(BooleanLiteral booleanLiteral);
	public QLNode visit(IdentifierLiteral identifierLiteral);
	public QLNode visit(NumberLiteral numberLiteral);
	public QLNode visit(TextLiteral textLiteral);
	public QLNode visit(MinusExpression minusExpression);
	public QLNode visit(NotExpression notExpression);
	public QLNode visit(PlusExpression plusExpression);
	public QLNode visit(ConditionalBlock conditionalBlock);
	public QLNode visit(ElseIfThenBlock elseIfThenBlock);
	public QLNode visit(ElseThenBlock elseThenBlock);
	public QLNode visit(Form form);
	public QLNode visit(IfThenBlock ifThenBlock);
	public QLNode visit(InputQuestion inputQuestion);
	public QLNode visit(ComputedQuestion computedQuestion);
}