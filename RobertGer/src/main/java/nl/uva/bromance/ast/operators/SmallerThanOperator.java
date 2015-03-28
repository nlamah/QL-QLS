package nl.uva.bromance.ast.operators;

import nl.uva.bromance.ast.conditionals.IntResult;
import nl.uva.bromance.ast.conditionals.Result;
import nl.uva.bromance.ast.visitors.OperatorVisitor;

/**
 * Created by Ger on 24-2-2015.
 */
public class SmallerThanOperator extends Operator {

    @Override
    public Result performOperation(Result one, Result two) {
        IntResult intResultOne = (IntResult) one;
        IntResult intResultTwo = (IntResult) two;
        return intResultOne.smallerThan(intResultTwo);
    }

    @Override
    public String getOperatorString() {
        return "<";
    }

    @Override
    public Operator getNewOperatorOfThisType() {
        return new SmallerThanOperator();
    }

    @Override
    public void accept(OperatorVisitor visitor) {
        visitor.visit(this);
    }
}
