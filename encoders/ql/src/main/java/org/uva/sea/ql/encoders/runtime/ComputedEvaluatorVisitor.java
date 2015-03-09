package org.uva.sea.ql.encoders.runtime;

import java.util.List;

import org.uva.sea.ql.encoders.ast.BaseAstVisitor;
import org.uva.sea.ql.encoders.ast.expression.BinaryExpression;
import org.uva.sea.ql.encoders.ast.expression.BracedExpression;
import org.uva.sea.ql.encoders.ast.expression.Expression;
import org.uva.sea.ql.encoders.ast.expression.NameExpression;
import org.uva.sea.ql.encoders.ast.expression.UnaryExpression;
import org.uva.sea.ql.encoders.ast.type.BooleanType;
import org.uva.sea.ql.encoders.ast.type.DataType;
import org.uva.sea.ql.encoders.ast.type.IntegerType;
import org.uva.sea.ql.encoders.ast.type.StringType;
import org.uva.sea.ql.encoders.runtime.operator.BinaryOperator;
import org.uva.sea.ql.encoders.runtime.operator.UnaryOperator;
import org.uva.sea.ql.encoders.service.OperatorTable;
import org.uva.sea.ql.encoders.service.QuestionByName;

public class ComputedEvaluatorVisitor extends BaseAstVisitor<Object> {

	private final DataType<?> dataType;

	private final List<RuntimeQuestion> questions;

	private final OperatorTable operatorTable;

	public ComputedEvaluatorVisitor(DataType<?> dataType, List<RuntimeQuestion> questions, OperatorTable operatorTable) {
		this.dataType = dataType;
		this.questions = questions;
		this.operatorTable = operatorTable;
	}

	@Override
	public Object visit(BracedExpression bracedExpression) {
		Expression expression = bracedExpression.getExpression();
		return expression.accept(this);
	}

	@Override
	public Object visit(BinaryExpression binaryExpression) {
		Expression leftHand = binaryExpression.getLeftHand();
		Expression rightHand = binaryExpression.getRightHand();
		Object leftValue = leftHand.accept(this);
		Object rightValue = rightHand.accept(this);

		BinaryOperator operator = operatorTable.getBinaryOperator(binaryExpression.getOperator());
		if (dataType instanceof BooleanType) {
			return operator.calculate((BooleanType) dataType, (Boolean) leftValue, (Boolean) rightValue);
		} else if (dataType instanceof IntegerType) {
			return operator.calculate((IntegerType) dataType, (Integer) leftValue, (Integer) rightValue);
		}
		return operator.calculate((StringType) dataType, (String) leftValue, (String) rightValue);
	}

	@Override
	public Object visit(UnaryExpression unaryExpression) {
		Expression expression = unaryExpression.getExpression();
		UnaryOperator operator = operatorTable.getUnaryOperator(unaryExpression.getOperator());
		Object value = expression.accept(this);
		return operator.calculate((BooleanType) dataType, (Boolean) value);
	}

	@Override
	public Object visit(NameExpression nameExpression) {
		String name = nameExpression.getName();
		QuestionByName questionByName = new QuestionByName();
		RuntimeQuestion runtimeQuestion = questionByName.getRuntimeQuestion(name, questions);
		return runtimeQuestion.getValue();
	}
}