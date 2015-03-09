package ql.ast;

import ql.ast.visitor.Visitor;

public interface ASTNode {
	@Override
	public String toString();
	
	public <T> T accept(Visitor<T> visitor);
}
