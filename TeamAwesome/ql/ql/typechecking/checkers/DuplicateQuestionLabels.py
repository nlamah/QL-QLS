from typechecking import Message

from .AbstractBase import AbstractBase



class Checker(AbstractBase):
    def __init__(self, parser, resultAlgebra):
        super().__init__(parser, resultAlgebra)
        self._labels = {}

    def visitQuestionnaireEnd(self, questionnaire):
        for text, lines in self._labels.items():
            if len(lines) > 1:
                for l in lines:
                    self._result = self._resultAlgebra.withWarning(
                        self._result,
                        Message.Warning(
                            'duplicate question label `'+text+'`',
                            l
                        )
                    )

        return super().visitQuestionnaireEnd(questionnaire)

    def visitQuestionStatement(self, node):
        if node.text not in self._labels:
            self._labels[node.text] = []
        self._labels[node.text].append(node.lineNumber)
