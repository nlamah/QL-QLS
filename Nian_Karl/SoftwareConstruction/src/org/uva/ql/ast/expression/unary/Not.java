package org.uva.ql.ast.expression.unary;

import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.type.BoolType;
import org.uva.ql.ast.type.Type;
import org.uva.ql.typecheck.TypeChecker;
import org.uva.ql.visitor.ExpressionVisitor;
import org.uva.utility.CodePosition;

public class Not extends Unary {

	public Not(Expression expr,CodePosition pos) {
		super(expr,pos);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "!" + this.expr.toString();
	}

	@Override
	public Type getType(TypeChecker typeChecker) {
		return new BoolType();
	}
}