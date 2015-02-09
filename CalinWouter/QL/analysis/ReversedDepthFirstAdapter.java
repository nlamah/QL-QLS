/* This file was generated by SableCC (http://www.sablecc.org/). */

package QL.analysis;

import java.util.*;
import QL.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPForm().apply(this);
        outStart(node);
    }

    public void inAForm(AForm node)
    {
        defaultIn(node);
    }

    public void outAForm(AForm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAForm(AForm node)
    {
        inAForm(node);
        if(node.getStmtlist() != null)
        {
            node.getStmtlist().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAForm(node);
    }

    public void inASingleStmtlist(ASingleStmtlist node)
    {
        defaultIn(node);
    }

    public void outASingleStmtlist(ASingleStmtlist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleStmtlist(ASingleStmtlist node)
    {
        inASingleStmtlist(node);
        if(node.getHead() != null)
        {
            node.getHead().apply(this);
        }
        outASingleStmtlist(node);
    }

    public void inAMultiStmtlist(AMultiStmtlist node)
    {
        defaultIn(node);
    }

    public void outAMultiStmtlist(AMultiStmtlist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultiStmtlist(AMultiStmtlist node)
    {
        inAMultiStmtlist(node);
        if(node.getTail() != null)
        {
            node.getTail().apply(this);
        }
        if(node.getHead() != null)
        {
            node.getHead().apply(this);
        }
        outAMultiStmtlist(node);
    }

    public void inASingleStmt(ASingleStmt node)
    {
        defaultIn(node);
    }

    public void outASingleStmt(ASingleStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleStmt(ASingleStmt node)
    {
        inASingleStmt(node);
        if(node.getHead() != null)
        {
            node.getHead().apply(this);
        }
        outASingleStmt(node);
    }

    public void inAQuestionStmt(AQuestionStmt node)
    {
        defaultIn(node);
    }

    public void outAQuestionStmt(AQuestionStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAQuestionStmt(AQuestionStmt node)
    {
        inAQuestionStmt(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getStr() != null)
        {
            node.getStr().apply(this);
        }
        outAQuestionStmt(node);
    }

    public void inAValueStmt(AValueStmt node)
    {
        defaultIn(node);
    }

    public void outAValueStmt(AValueStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAValueStmt(AValueStmt node)
    {
        inAValueStmt(node);
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        outAValueStmt(node);
    }

    public void inAIfelseStmt(AIfelseStmt node)
    {
        defaultIn(node);
    }

    public void outAIfelseStmt(AIfelseStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfelseStmt(AIfelseStmt node)
    {
        inAIfelseStmt(node);
        if(node.getElsestmts() != null)
        {
            node.getElsestmts().apply(this);
        }
        if(node.getIfstmts() != null)
        {
            node.getIfstmts().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outAIfelseStmt(node);
    }

    public void inAIfStmt(AIfStmt node)
    {
        defaultIn(node);
    }

    public void outAIfStmt(AIfStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfStmt(AIfStmt node)
    {
        inAIfStmt(node);
        if(node.getIfstmts() != null)
        {
            node.getIfstmts().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outAIfStmt(node);
    }

    public void inABoolType(ABoolType node)
    {
        defaultIn(node);
    }

    public void outABoolType(ABoolType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABoolType(ABoolType node)
    {
        inABoolType(node);
        outABoolType(node);
    }

    public void inAStringType(AStringType node)
    {
        defaultIn(node);
    }

    public void outAStringType(AStringType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringType(AStringType node)
    {
        inAStringType(node);
        outAStringType(node);
    }

    public void inAIntType(AIntType node)
    {
        defaultIn(node);
    }

    public void outAIntType(AIntType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntType(AIntType node)
    {
        inAIntType(node);
        outAIntType(node);
    }

    public void inAAddExp(AAddExp node)
    {
        defaultIn(node);
    }

    public void outAAddExp(AAddExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddExp(AAddExp node)
    {
        inAAddExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAAddExp(node);
    }

    public void inATrueExp(ATrueExp node)
    {
        defaultIn(node);
    }

    public void outATrueExp(ATrueExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATrueExp(ATrueExp node)
    {
        inATrueExp(node);
        outATrueExp(node);
    }

    public void inAFalseExp(AFalseExp node)
    {
        defaultIn(node);
    }

    public void outAFalseExp(AFalseExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFalseExp(AFalseExp node)
    {
        inAFalseExp(node);
        outAFalseExp(node);
    }

    public void inAOrExp(AOrExp node)
    {
        defaultIn(node);
    }

    public void outAOrExp(AOrExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExp(AOrExp node)
    {
        inAOrExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAOrExp(node);
    }

    public void inAAndExp(AAndExp node)
    {
        defaultIn(node);
    }

    public void outAAndExp(AAndExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExp(AAndExp node)
    {
        inAAndExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAAndExp(node);
    }

    public void inASubExp(ASubExp node)
    {
        defaultIn(node);
    }

    public void outASubExp(ASubExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubExp(ASubExp node)
    {
        inASubExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outASubExp(node);
    }

    public void inAEqExp(AEqExp node)
    {
        defaultIn(node);
    }

    public void outAEqExp(AEqExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqExp(AEqExp node)
    {
        inAEqExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAEqExp(node);
    }

    public void inANeqExp(ANeqExp node)
    {
        defaultIn(node);
    }

    public void outANeqExp(ANeqExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANeqExp(ANeqExp node)
    {
        inANeqExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outANeqExp(node);
    }

    public void inALtExp(ALtExp node)
    {
        defaultIn(node);
    }

    public void outALtExp(ALtExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALtExp(ALtExp node)
    {
        inALtExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outALtExp(node);
    }

    public void inAGtExp(AGtExp node)
    {
        defaultIn(node);
    }

    public void outAGtExp(AGtExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGtExp(AGtExp node)
    {
        inAGtExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAGtExp(node);
    }

    public void inALteExp(ALteExp node)
    {
        defaultIn(node);
    }

    public void outALteExp(ALteExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALteExp(ALteExp node)
    {
        inALteExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outALteExp(node);
    }

    public void inAGteExp(AGteExp node)
    {
        defaultIn(node);
    }

    public void outAGteExp(AGteExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGteExp(AGteExp node)
    {
        inAGteExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAGteExp(node);
    }

    public void inAMulExp(AMulExp node)
    {
        defaultIn(node);
    }

    public void outAMulExp(AMulExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMulExp(AMulExp node)
    {
        inAMulExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAMulExp(node);
    }

    public void inADivExp(ADivExp node)
    {
        defaultIn(node);
    }

    public void outADivExp(ADivExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivExp(ADivExp node)
    {
        inADivExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outADivExp(node);
    }

    public void inAModExp(AModExp node)
    {
        defaultIn(node);
    }

    public void outAModExp(AModExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAModExp(AModExp node)
    {
        inAModExp(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outAModExp(node);
    }

    public void inAParenExp(AParenExp node)
    {
        defaultIn(node);
    }

    public void outAParenExp(AParenExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParenExp(AParenExp node)
    {
        inAParenExp(node);
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outAParenExp(node);
    }

    public void inANotExp(ANotExp node)
    {
        defaultIn(node);
    }

    public void outANotExp(ANotExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotExp(ANotExp node)
    {
        inANotExp(node);
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outANotExp(node);
    }

    public void inANumberExp(ANumberExp node)
    {
        defaultIn(node);
    }

    public void outANumberExp(ANumberExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumberExp(ANumberExp node)
    {
        inANumberExp(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANumberExp(node);
    }
}
