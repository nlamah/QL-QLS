package lang.ql.semantics;

import lang.ql.ast.AstVisitor;
import lang.ql.ast.expression.*;
import lang.ql.ast.form.Form;
import lang.ql.ast.statement.CalculatedQuestion;
import lang.ql.ast.statement.IfCondition;
import lang.ql.ast.statement.Question;
import lang.ql.semantics.values.*;

/**
 * Created by bore on 23/02/15.
 */
public class Evaluator implements ExprVisitor<Value>
{
    private EvalEnv env;

    public static Value evaluate(Expr e, EvalEnv env)
    {
        Evaluator eval = new Evaluator(env);
        return e.accept(eval);
    }

    private Evaluator(EvalEnv env)
    {
        this.env = env;
    }

    @Override
    public Value visit(BoolExpr e)
    {
        return new BooleanValue(e.getValue());
    }

    @Override
    public Value visit(IntExpr e)
    {
        return new IntegerValue(e.getValue());
    }

    @Override
    public Value visit(DecExpr e)
    {
        return new DecimalValue(e.getValue());
    }

    @Override
    public Value visit(StrExpr e)
    {
        return new StringValue(e.getValue());
    }

    @Override
    public Value visit(Ident id)
    {
        return this.env.getValue(id.getId());
    }

    @Override
    public Value visit(Neg e)
    {
        return e.getOperand().accept(this).neg();
    }

    @Override
    public Value visit(Pos e)
    {
        return e.getOperand().accept(this).pos();
    }

    @Override
    public Value visit(Not e)
    {
        return e.getOperand().accept(this).not();
    }

    @Override
    public Value visit(Add e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.add(right);
    }

    @Override
    public Value visit(Sub e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.sub(right);
    }

    @Override
    public Value visit(Mul e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.mul(right);
    }

    @Override
    public Value visit(Div e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.div(right);
    }

    @Override
    public Value visit(Gt e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.gt(right);
    }

    @Override
    public Value visit(Lt e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.lt(right);
    }

    @Override
    public Value visit(GtEqu e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.gtEqu(right);
    }

    @Override
    public Value visit(LtEqu e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.ltEqu(right);
    }

    @Override
    public Value visit(Equ e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.equ(right);
    }

    @Override
    public Value visit(NotEqu e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.notEqu(right);
    }

    @Override
    public Value visit(And e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.and(right);
    }

    @Override
    public Value visit(Or e)
    {
        Value left = e.getLeft().accept(this);
        Value right = e.getRight().accept(this);
        return left.or(right);
    }
}
