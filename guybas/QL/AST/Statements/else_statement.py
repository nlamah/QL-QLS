import QL.AST.Statements.if_statement as if_statement
import QL.Main.converters as converters


class IfElseBlock(if_statement.IfBlock):

    # Override
    def __init__(self, condition, statements, else_statements):
        self._condition = condition
        self._statements = statements
        self.else_statements = else_statements
        self._element = None

    # Override
    def pretty_print(self, level=0):
        s = "\n" + "   " * level + "If (" + self._condition.pretty_print(0) + ")"
        for x in self._statements:
            s += "   " * level + x.pretty_print(level+1)

        s += "   " * level + "else"
        for x in self.else_statements:
            s += "   " * level + x.pretty_print(level+1)
        return s

    # Override
    def id_collection(self):
        ids = []
        for x in self._statements:
            ids += x.id_collection()
        for x in self.else_statements:
            ids += x.id_collection()
        return ids

    # Override
    def label_collection(self):
        labels = []
        for x in self._statements:
            labels += x.label_collection()
        for x in self.else_statements:
            labels += x.label_collection()
        return labels

    # Override
    def get_dependency_collection(self, dependencies):
        ids = self.id_collection()
        for i in ids:
            if i in dependencies:
                dependencies[i] = dependencies[i] + self._condition.get_dependencies()
            else:
                dependencies[i] = self._condition.get_dependencies()
        for x in self._statements:
            dependencies = dict(list(dependencies.items()) + list(x.get_dependency_collection(dependencies).items()))
        return dependencies

    # Override
    def return_expressions(self):
        s = [self._condition]
        for x in self._statements:
            s += x.return_expressions()
        for x in self.else_statements:
            s += x.return_expressions()
        return s

    # Override
    def get_parent_id(self):
        return self._parent_id

    # Override
    def set_parent_id(self, pid):
        self._parent_id = pid
        m = converters.Converters.get_md5(str(self))
        for s in self._statements:
            s.set_parent_id(m)
            s.set_parent_condition(self._condition)
        for s in self.else_statements:
            s.set_parent_id(m)
            s.set_parent_condition(self._condition.add_not())

    # Override
    def set_order(self, order_num):
        c = order_num
        for s in self._statements:
            c = s.set_order(c)
        for s in self.else_statements:
            c = s.set_order(c)
        return c

    def set_element(self, gui):
        ...

    def set_parent_condition(self, condition):
        ...

    # Override
    def get_id_type_collection(self):
        d = {}
        for s in self._statements:
            d = dict(list(d.items()) + list(s.get_id_type_collection().items()))
        for s in self.else_statements:
            d = dict(list(d.items()) + list(s.get_id_type_collection().items()))
        return d

    def get_e_statements(self):
        return self.else_statements

    def get_element(self):
        return self._element

    def get_statement_dict(self):
        d = {}
        for s in self._statements:
            d = dict(list(d.items()) + list(s.get_statement_dict().items()))
        for s in self.else_statements:
            d = dict(list(d.items()) + list(s.get_statement_dict().items()))

        return d