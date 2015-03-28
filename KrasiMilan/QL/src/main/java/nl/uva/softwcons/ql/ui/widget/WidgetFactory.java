package nl.uva.softwcons.ql.ui.widget;

import nl.uva.softwcons.ql.ast.statement.ComputedQuestion;
import nl.uva.softwcons.ql.ast.statement.Question;

public interface WidgetFactory {
    Widget getWidget(Question question);

    Widget getWidget(ComputedQuestion question);
}