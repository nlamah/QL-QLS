# Generated from java-escape by ANTLR 4.5
from antlr4 import *

# This class defines a complete generic visitor for a parse tree produced by QLParser.

class QLVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by QLParser#statement.
    def visitStatement(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#form.
    def visitForm(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#if_statement.
    def visitIf_statement(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#boolean.
    def visitBoolean(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question_type.
    def visitQuestion_type(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#atom.
    def visitAtom(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#expr.
    def visitExpr(self, ctx):
        return self.visitChildren(ctx)

