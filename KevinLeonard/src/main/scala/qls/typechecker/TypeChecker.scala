package qls.typechecker

import ql.typechecker.Error
import qls.ast._
import types.TypeEnvironment

class TypeChecker {

  def check(q: Question, env: TypeEnvironment): Option[Error] = {
    val name = q.variable.name
    val widget = q.widget
    val _type = env getOrElse(name, throw new AssertionError(s"Error in type checker. Undefined variable $name."))

    if (widget.allowsType(_type)) {
      None
    } else {
      Some(Error(s"${widget.toString.capitalize} widget not allowed for question $name", Some(q.pos)))
    }
  }
}
