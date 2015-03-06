package edu.parser.QLS;

import edu.Widgets;
import edu.exceptions.TypeCheckException;
import edu.parser.QL.nodes.question.QLQuestion;
import edu.parser.QLS.nodes.AbstractNode;
import edu.parser.QLS.nodes.Identifier;
import edu.parser.QLS.nodes.Section;
import edu.parser.QLS.nodes.Stylesheet;
import edu.parser.QLS.nodes.statement.Default;
import edu.parser.QLS.nodes.statement.Page;
import edu.parser.QLS.nodes.statement.QLSQuestion;
import edu.parser.QLS.nodes.statement.Statement;
import edu.parser.nodes.styles.Style;
import edu.parser.nodes.styles.Widget;
import edu.parser.nodes.QuestionType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Steven Kok on 01/03/2015.
 */
public class TypeChecker implements QLSVisitor {
    public static final String UNIDENTIFIED_STYLESHEET_QUESTION = "Stylesheet contains questions that are not contained in the form. Unknown question identifiers:";
    public static final String FOUND_DUPLICATE_QUESTIONS = "Found duplicate question entries in QLS for: ";
    private final List<QLSQuestion> stylesheetQuestions;
    private final List<QLQuestion> formQuestions;

    public TypeChecker() {
        this.stylesheetQuestions = new ArrayList<>();
        this.formQuestions = new ArrayList<>();
    }

    public void start(List<QLQuestion> allFormQuestions, Stylesheet stylesheet) {
        initializeLists();
        this.formQuestions.addAll(allFormQuestions);
        visit(stylesheet);
        confirmQuestionsExistInForm(formQuestions);
        confirmNoDuplicateQuestions(stylesheetQuestions);
    }

    private void initializeLists() {
        this.stylesheetQuestions.clear();
        this.formQuestions.clear();
    }

    private void confirmNoDuplicateQuestions(List<QLSQuestion> stylesheetQuestions) {
        List<QLSQuestion> duplicateQuestions = getDuplicateQuestions(stylesheetQuestions);
        if (!duplicateQuestions.isEmpty()) {
            String duplicateQuestionsString = ListToString(duplicateQuestions);
            throw new TypeCheckException(FOUND_DUPLICATE_QUESTIONS + duplicateQuestionsString);
        }
    }

    private List<QLSQuestion> getDuplicateQuestions(List<QLSQuestion> stylesheetQuestions) {
        return stylesheetQuestions.stream()
                .filter(a -> stylesheetQuestions.stream()
                        .filter(b -> a.getIdentifier().equals(b.getIdentifier())).count() > 1)
                .collect(Collectors.toList());
    }

    private String ListToString(List<QLSQuestion> duplicateQuestions) {
        return duplicateQuestions.stream()
                .map(QLSQuestion::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public AbstractNode visit(Stylesheet stylesheet) {
        visitStatements(stylesheet.getStatements());
        return stylesheet;
    }

    private void visitStatements(List<Statement> stylesheet) {
        stylesheet.stream()
                .forEach(element -> element.accept(this));
    }

    private void confirmQuestionsExistInForm(List<QLQuestion> formQuestions) {
        List<QLSQuestion> notFoundQuestions = stylesheetQuestions.stream()
                .filter(stylesheetQuestion ->
                        formQuestions.stream()
                                .noneMatch(doesFormQuestionsContainStylesheetQuestion(stylesheetQuestion)))
                .collect(Collectors.toList());

        if (!notFoundQuestions.isEmpty()) {
            throw new TypeCheckException(UNIDENTIFIED_STYLESHEET_QUESTION
                    + listToString(notFoundQuestions));
        }
    }

    private String listToString(List<QLSQuestion> list) {
        return list
                .stream()
                .map(element -> element.getIdentifier().getIdentifier())
                .collect(Collectors.joining(", "));
    }

    private Predicate<QLQuestion> doesFormQuestionsContainStylesheetQuestion(QLSQuestion stylesheetQuestion) {
        return formQuestion -> stylesheetQuestion.getIdentifier().getIdentifier()
                .equals(formQuestion.getIdentifier().getIdentifier());
    }

    @Override
    public AbstractNode visit(Page page) {
        page.getSections()
                .stream()
                .forEach(section -> section.accept(this));
        return page;
    }

    @Override
    public AbstractNode visit(QLSQuestion question) {
        confirmQuestionHasCompatibleType(question);
        stylesheetQuestions.add(question);
        return question;
    }

    private void confirmQuestionHasCompatibleType(QLSQuestion stylesheetQuestion) {
        QLQuestion formQuestion = getFormQuestion(stylesheetQuestion.getIdentifier().getIdentifier());
        List<Widgets> supportedWidgets = formQuestion.getQuestionType().getWidgets();
        boolean isWidgetTypeCompatible = isWidgetTypeCompatible(stylesheetQuestion.getStyles(), supportedWidgets);

        if (!isWidgetTypeCompatible) {
            throw new TypeCheckException("Widget type is not compatible for: " + stylesheetQuestion.getIdentifier());
        }
    }

    private QLQuestion getFormQuestion(String identifier) {
        List<QLQuestion> questionList = this.formQuestions.stream()
                .filter(formQuestion -> formQuestion.getIdentifier().getIdentifier().equals(identifier))
                .collect(Collectors.toList());

        if (questionList.isEmpty()) {
            throw new TypeCheckException("Couldn't find form question with identifier: " + identifier);
        } else if (questionList.size() > 1) {
            throw new TypeCheckException("Found duplicate form question.");
        } else {
            return questionList.get(0);
        }
    }

    private boolean isWidgetTypeCompatible(List<Style> styles, List<Widgets> supportedWidgets) {
        return styles.stream()
                .filter(style -> style instanceof Widget)
                .map(widget -> (Widget) widget)
                .allMatch(widget -> supportedWidgets.contains(widget.getWidget()));
    }

    @Override
    public AbstractNode visit(Identifier identifier) {
        return identifier;
    }

    @Override
    public AbstractNode visit(Section section) {
        visitStatements(section.getStatements());
        return section;
    }

    @Override
    public AbstractNode visit(Default aDefault) {
        return aDefault;
    }

    @Override
    public AbstractNode visit(Style style) {
        return style;
    }

    @Override
    public AbstractNode visit(QuestionType questionType) {
        return questionType;
    }

}
