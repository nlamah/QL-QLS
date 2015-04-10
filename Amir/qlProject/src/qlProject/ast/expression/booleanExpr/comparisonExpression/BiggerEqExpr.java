package qlProject.ast.expression.booleanExpr.comparisonExpression;

import qlProject.ast.expression.BinaryExpression;
import qlProject.ast.expression.IExpression;
import qlProject.ast.expression.IExpressionVisitor;

public class BiggerEqExpr extends BinaryExpression { 

	public BiggerEqExpr(IExpression left, IExpression right) {
		super(left, right);
	}
	

	@Override
	public Object accept(IExpressionVisitor visitor){
		return visitor.visit(this);
	}

}