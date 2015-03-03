package com.form.language.ast.expression.literal;

import org.antlr.v4.runtime.Token;

import com.form.language.ast.expression.Expression;
import com.form.language.ast.type.Type;
import com.form.language.error.ErrorCollector;
import com.form.language.memory.IdCollector;
import com.form.language.memory.IdTypeTable;

public abstract class Literal implements Expression {

	public Token tokenInfo;
	public Literal(Token tokenInfo){
		this.tokenInfo = tokenInfo;
	}
	@Override
	public Boolean isCorrectlyTyped() {
		return true;
	}
	
	public String showTokenInfo(){
		return "line: " + tokenInfo.getLine() + ", column:" + tokenInfo.getCharPositionInLine();
	}
	
	@Override
	public void getErrors(ErrorCollector errorCollector) {
		return;
	}
	
	@Override
	public void collectIds(IdCollector idCollector)
	{
		return;		
	}
	@Override
	public void setType(IdTypeTable ids) {}
	
}
