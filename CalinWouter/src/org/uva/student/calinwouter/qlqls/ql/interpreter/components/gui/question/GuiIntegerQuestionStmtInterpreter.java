package org.uva.student.calinwouter.qlqls.ql.interpreter.components.gui.question;

import org.uva.student.calinwouter.qlqls.generated.node.AQuestionStmt;
import org.uva.student.calinwouter.qlqls.ql.interpreter.components.FormInterpreter;
import org.uva.student.calinwouter.qlqls.ql.interpreter.types.TInteger;

public class GuiIntegerQuestionStmtInterpreter extends GuiStringQuestionStmtInterpreter {

    @Override
    protected boolean update(String value) {
        try {
            formInterpreter.setField(node.getIdent().getText(), new TInteger(Integer.parseInt(value)));
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public GuiIntegerQuestionStmtInterpreter(FormInterpreter formInterpreter, AQuestionStmt node) {
        super(formInterpreter, node);
    }
}