package org.uva.ql.ast.type;

import org.uva.ql.visitor.Visitor;

public class IntType extends Type {
	
	@Override
	public boolean isInt() {
		return true;
	}
	
	@Override
	public boolean isEqual(Type type) {
		return type.isInt();
	}
	
	@Override
	public String toString() {
		return "Int";
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
