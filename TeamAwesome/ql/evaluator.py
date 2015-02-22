from AST import AST
import ASTNodes
from collections import OrderedDict
from CustomTypes import *
import OperatorTypes

class QuestionValueTable(object):
	def __init__(self):
		self._table = OrderedDict()

	def __iter__(self):
		return self._table.__iter__()

	def items(self):
		return self._table.items()

	def values(self):
		return self._table.values()

	def keys(self):
		return self._table.keys()

	def add(self, question, val):
		self._table[question] = val

	def get(self, question, evaluator):
		val = self._table[question]

		if isinstance(val, Expression):
			return val.evaluate(evaluator)
		return val

class QuestionTable(object):
	def __init__(self, ast):
		self._table = OrderedDict()

		for formStatement in ast.root.statements:
			form = Form(formStatement)

			for statement in formStatement.getChildren():
				self._add(statement, ExpressionsTuple(), form)

	def __iter__(self):
		return self._table.__iter__()

	def items(self):
		return self._table.items()

	def values(self):
		return self._table.values()

	def keys(self):
		return self._table.keys()

	def _add(self, statement, expressionsTuple, form):
		if isinstance(statement, ASTNodes.IfStatement):
			childExpressionsTuple = expressionsTuple + (ExpressionFactory.create((statement.expr)),)
			
			for childStatement in statement.getChildren():
				self._add(childStatement, childExpressionsTuple, form)
		else:
			question = Question(statement, expressionsTuple, form)
			questionList = self._table.get(question.identifier, QuestionList())
			questionList.append(question)
			self._table[question.identifier] = questionList

	def get(self, identifier, evaluator):
		assert isinstance(evaluator, Evaluator)

		questions = self._table.get(identifier, None)
		
		if questions:
			return questions.getVisibleQuestion(evaluator)
		return questions

	def getQuestionList(self, identifier):
		return self._table.get(identifier, None)

class ExpressionsTuple(tuple):
	def __add__(self, value):
		return ExpressionsTuple(tuple.__add__(self, value))

	def evaluate(self, evaluator):
		return all(expr.evaluate(evaluator) for expr in self)

class QuestionList(list):
	def getVisibleQuestion(self, evaluator):
		for question in self:
			if question.isVisible(evaluator):
				return question
		return None

class ExpressionFactory(object):
	@staticmethod
	def create(expressionNode):
		if expressionNode:
			method = ExpressionFactory._getCreateMethod(expressionNode)
			return method(expressionNode)
		return None
		
	@staticmethod
	def _getCreateMethod(expressionNode):
		methodName = '_create' + expressionNode.__class__.__name__
		return getattr(ExpressionFactory, methodName)

	@staticmethod
	def _createBinaryExpression(binaryExpressionNode):
		return BinaryExpression(binaryExpressionNode)

	@staticmethod
	def _createUnaryExpression(unaryExpressionNode):
		return UnaryExpression(unaryExpressionNode)

	@staticmethod
	def _createAtomicExpression(atomicExpressionNode):
		return AtomicExpression(atomicExpressionNode)

class Expression(object):
	pass

class BinaryExpression(Expression):
	def __init__(self, binaryExpressionNode):
		self.left = ExpressionFactory.create(binaryExpressionNode.left)
		self.op = binaryExpressionNode.op
		self.right = ExpressionFactory.create(binaryExpressionNode.right)

	def evaluate(self, evaluator):		
		leftValue = self.left.evaluate(evaluator)
		rightValue = self.right.evaluate(evaluator)
		return evaluator.evaluateBinaryExpression(self.op, leftValue, rightValue)

class UnaryExpression(Expression):
	def __init__(self, unaryExpressionNode):
		self.op = unaryExpressionNode.op
		self.right = ExpressionFactory.create(unaryExpressionNode.right)

	def evaluate(self, evaluator):
		rightValue = self.right.evaluate(evaluator)
		return evaluator.evaluateUnaryExpression(self.op, rightValue)

class AtomicExpression(Expression):
	def __init__(self, atomicExpressionNode):
		self.left = atomicExpressionNode.left

	def evaluate(self, evaluator):
		if isinstance(self.left, Identifier):
			return evaluator.getValue(self.left)
		return self.left

class Form(object):
	def __init__(self, formStatementNode):
		assert isinstance(formStatementNode, ASTNodes.FormStatement)

		self.identifier = formStatementNode.identifier

class Question(object):
	def __init__(self, questionStatementNode, conditionalExpressionsTuple, form):
		assert isinstance(questionStatementNode, ASTNodes.QuestionStatement)
	
		self.identifier = questionStatementNode.identifier	
		self.valueExpression = ExpressionFactory.create(questionStatementNode.expr)
		self.text = questionStatementNode.text
		self.type = questionStatementNode.type

		self.conditionalExpressions = conditionalExpressionsTuple
		self.form = form

	def isVisible(self, evaluator):
		return self.conditionalExpressions.evaluate(evaluator)

class Page(object):
	def __init__(self, questionIdentifiers):
		self.questionIdentifiers = questionIdentifiers

	def getQuestions(self, evaluator):
		questions = [evaluator.getQuestion(identifier) for identifier in self.questionIdentifiers]
		return [question for question in questions if question != None]

class Evaluator(object):
	def __init__(self, ast):
		self.questionTable = QuestionTable(ast)
		self.questionValueTable = QuestionValueTable()
		
		for questions in self.questionTable.values():
			for question in questions:
				if question.valueExpression:
					self.questionValueTable.add(question, question.valueExpression)
				else:
					self.questionValueTable.add(question, None)

		self.typeTable = OperatorTypes.Table()
		self.pages = []

	def evaluateBinaryExpression(self, operator, leftValue, rightValue):
		pythonOp = evaluator.typeTable.getBinaryOperator(operator, leftValue, rightValue)
		if pythonOp:
			return pythonOp(leftValue, rightValue)
		return None

	def evaluateUnaryExpression(self, operator, rightValue):
		pythonOp = evaluator.typeTable.getUnaryOperator(operator, rightValue)
		if pythonOp:
			return pythonOp(rightValue)
		return None

	def getValue(self, identifier):
		question = self.questionTable.get(identifier, self)
		return self.questionValueTable.get(question, self)

	def getQuestion(self, identifier):
		return self.questionTable.get(identifier, self)	

	def createDefaultPages(self):
		self.pages = []
		self.pages.append(Page(self.questionTable.keys()))

	def getVisibleQuestions(self, pageNumber):
		return self.pages[pageNumber].getQuestions(self)


ast = AST("test_visitor.QL")
evaluator = Evaluator(ast)
evaluator.createDefaultPages()

for q in evaluator.getVisibleQuestions(0):
	print(q.text)

#for identifier, question in ec.items():
#	print(identifier, question)
	