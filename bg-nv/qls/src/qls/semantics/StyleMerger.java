package qls.semantics;

import ql.ast.form.Form;
import ql.ast.type.Type;
import ql.semantics.QuestionCollector;
import ql.semantics.QuestionResult;
import ql.semantics.QuestionMap;
import qls.ast.Page;
import qls.ast.rule.Rules;
import qls.ast.Styleable;
import qls.ast.Stylesheet;
import qls.ast.StylesheetVisitor;
import qls.ast.statement.*;

/**
 * Created by bore on 09/03/15.
 */
public class StyleMerger implements StylesheetVisitor<Void>, StatementVisitor<Void>
{
    private final QuestionMap questions;
    private final StyleStack styleStack;
    private final FormStyle styles;

    public static FormStyle getStyles(Stylesheet s, Form f)
    {
        QuestionResult result = QuestionCollector.collect(f);
        StyleMerger styleEval = new StyleMerger(result.getQuestionMap());
        styleEval.visit(s);

        return styleEval.styles;
    }

    private StyleMerger(QuestionMap questions)
    {
        this.questions = questions;
        this.styleStack = new StyleStack();
        this.styles = new FormStyle();
    }

    @Override
    public Void visit(Stylesheet s)
    {
        for (Page p : s.getBody())
        {
            p.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Page p)
    {
        return this.visitStyleable(p, p.getBody());
    }

    @Override
    public Void visit(Section sec)
    {
        return this.visitStyleable(sec, sec.getBody());
    }

    private Void visitStyleable(Styleable s, Iterable<qls.ast.statement.Statement> stats)
    {
        Style style = s.getStyle();

        this.styleStack.push(style);

        for (Statement stat : stats)
        {
            stat.accept(this);
        }

        this.styleStack.pop();

        return null;
    }

    @Override
    public Void visit(Question q)
    {
        Type questionType = this.questions.getType(q.getId());
        Rules result = this.styleStack.getRulesForType(questionType);

        this.styles.registerStyle(q.getId(), result);

        return null;
    }

    @Override
    public Void visit(QuestionWithRules q)
    {
        Rules r = q.getBody();
        Type questionType = this.questions.getType(q.getId());
        Style s = new Style();
        s.addRules(questionType, r);

        this.styleStack.push(s);
        Rules result = this.styleStack.getRulesForType(questionType);
        this.styleStack.pop();

        this.styles.registerStyle(q.getId(), result);

        return null;
    }

    @Override
    public Void visit(DefaultStat d)
    {
        return null;
    }
}
