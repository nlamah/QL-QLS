package org.uva.sea.ql.encoders.runtime;

import java.util.List;

import org.uva.sea.ql.encoders.ast.BaseAstVisitor;
import org.uva.sea.ql.encoders.ast.expression.BinaryExpression;
import org.uva.sea.ql.encoders.ast.expression.BracedExpression;
import org.uva.sea.ql.encoders.ast.expression.Expression;
import org.uva.sea.ql.encoders.ast.expression.NameExpression;
import org.uva.sea.ql.encoders.ast.expression.UnaryExpression;
import org.uva.sea.ql.encoders.ast.type.BooleanType;
import org.uva.sea.ql.encoders.runtime.operator.BinaryOperator;
import org.uva.sea.ql.encoders.runtime.operator.UnaryOperator;
import org.uva.sea.ql.encoders.service.OperatorTable;
import org.uva.sea.ql.encoders.service.QuestionByName;

public class ConditionEvaluatorVisitor extends BaseAstVisitor<Boolean> {

	private final List<RuntimeQuestion> questions;

	private final OperatorTable operatorTable;

	public ConditionEvaluatorVisitor(List<RuntimeQuestion> questions, OperatorTable operatorTable) {
		this.questions = questions;
		this.operatorTable = operatorTable;
	}

	@Override
	public Boolean visit(BracedExpression bracedExpression) {
		Expression expression = bracedExpression.getExpression();
		return expression.accept(this);
	}

	@Override
	public Boolean visit(BinaryExpression binaryExpression) {
		Expression leftHand = binaryExpression.getLeftHand();
		Expression rightHand = binaryExpression.getRightHand();
		Boolean leftValue = leftHand.accept(this);
		Boolean rightValue = rightHand.accept(this);

		BinaryOperator operator = operatorTable.getBinaryOperator(binaryExpression.getOperator());
		return operator.calculate(BooleanType.BOOLEAN, leftValue, rightValue);
	}

	@Override
	public Boolean visit(UnaryExpression unaryExpression) {
		Expression expression = unaryExpression.getExpression();
		UnaryOperator operator = operatorTable.getUnaryOperator(unaryExpression.getOperator());
		Boolean value = expression.accept(this);
		return operator.calculate(BooleanType.BOOLEAN, value);
	}

	@Override
	public Boolean visit(NameExpression nameExpression) {
		String name = nameExpression.getName();
		QuestionByName questionByName = new QuestionByName();
		RuntimeQuestion runtimeQuestion = questionByName.getRuntimeQuestion(name, questions);
		return (Boolean) runtimeQuestion.getValue();
	}
}