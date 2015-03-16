package org.uva.sea.qls.encoders.ast;

//Duplication?

public class TextLocation {

	private int line;
	private int column;

	public TextLocation(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}

	@Override
	public String toString() {
		return "Line: " + line + " Column: " + column;
	}
}
