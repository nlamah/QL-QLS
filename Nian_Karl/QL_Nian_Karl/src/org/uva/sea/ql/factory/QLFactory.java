package org.uva.sea.ql.factory;

import org.uva.sea.ql.AST.QuestionType;
import org.uva.sea.ql.AST.expression.Expression;
import org.uva.sea.ql.AST.expression.Operator;
import org.uva.sea.ql.AST.statement.QuestionStatement;
import org.uva.sea.ql.parser.antlr.QLParser.ExpressionContext;
import org.uva.sea.ql.parser.antlr.QLParser.QuestionContext;

public class QLFactory implements IQLFactory {

	@Override
	public QuestionStatement getQuestion(QuestionContext ctx) {
		QuestionType type = getQuestionType(ctx.getChild(0)
				.getText());
		String questionIdentifier = ctx.getChild(1).getText();
		// Escape the "" in the beginning of the questionlabel.
		String questionLabel = ctx.getChild(1).getText();
		return new QuestionStatement(type, questionIdentifier, questionLabel);
	}

	@Override
	public QuestionType getQuestionType(String s) {
		switch (s) {
		case "Int":
			return QuestionType.INT;
		case "Cur":
			return QuestionType.CUR;
		case "Str":
			return QuestionType.STR;
		case "Bool":
			return QuestionType.BOOL;
		default:
			return QuestionType.NO_TYPE;
		}
	}
	
	public boolean stringToBoolean(String s) {
		if (s.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Expression getExpression(ExpressionContext expr){
		
		return null;
	}
	
	public Operator getOperator(String s){
		for (Operator operator : Operator.values()) {
			if (operator.getOperatorString().equals(s)) {
				return operator;
			}
		}
		return null; 
	}
	
	
}
