package lang.ql.ast.expression;

/**
 * Created by bore on 17/02/15.
 */
public class LtEqu extends BinaryExpr
{
    public LtEqu(Expr left, Expr right, int lineNumber)
    {
        super(left, right, lineNumber);
    }

    public <T> T accept(ExprVisitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
