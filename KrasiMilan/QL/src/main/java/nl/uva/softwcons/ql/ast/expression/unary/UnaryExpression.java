package nl.uva.softwcons.ql.ast.expression.unary;

import nl.uva.softwcons.ql.ast.LineInfo;
import nl.uva.softwcons.ql.ast.expression.Expression;

public abstract class UnaryExpression extends Expression {
    private LineInfo lineInfo;
    private Expression expression;

    public UnaryExpression(Expression expr, LineInfo lineInfo) {
        this.expression = expr;
        this.lineInfo = lineInfo;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public LineInfo getLineInfo() {
        return lineInfo;
    }

}