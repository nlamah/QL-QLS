package org.fugazi.ast.Statement;

/**
 * Generic Visitor class for Statements.
 * @param <T>
 */
public interface StatementVisitor<T> {

    public T visit(QuestionStatement questionStatement);
    public T visit(IfStatement ifStatement);
    public T visit(ComputedQuestionStatement assignQuestStatement);
}