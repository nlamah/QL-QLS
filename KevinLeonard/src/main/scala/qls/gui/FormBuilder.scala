package qls.gui

import ql.ast.{BooleanType, Expression, NumberType, Question => QLQuestion, StringType}
import ql.gui.{FormBuilder => QLFormBuilder}
import qls.ast._
import qls.gui.widgets._
import qls.typechecker.Questions
import types.EvalEnvironment

import scalafx.collections.ObservableMap
import scalafx.scene.layout.VBox

class FormBuilder(stylesheet: StyleSheet, env: EvalEnvironment = ObservableMap.empty) extends QLFormBuilder(env) {

  val questionStyles: List[Question] = Questions.extract(stylesheet)

  override def buildQuestion(q: QLQuestion, visibilityExpressions: List[Expression]): VBox = {
    questionStyles.find(_.variable == q.variable) match {
      case None => throw new AssertionError("All questions should be placed.")
      case Some(qs) => qs.widget match {
        case SpinBox(_) => throw new NotImplementedError("Spinner class not available.")
        case Slider(styleProperties) => new SliderQuestionWidget(q, visibilityExpressions, env)
        case Text(styleProperties) => q._type match {
          case NumberType() => new NumberQuestionWidget(q, visibilityExpressions, env)
          case StringType() => new StringQuestionWidget(q, visibilityExpressions, env)
          case BooleanType() => throw new AssertionError("Text widget not allowed for boolean questions.")
        }
        case TextBlock(styleProperties) => new TextBlockQuestionWidget(q, visibilityExpressions, env)
        case Radio(styleProperties) => new RadioQuestionWidget(q, visibilityExpressions, env)
        case CheckBox(styleProperties) => new CheckBoxQuestionWidget(q, visibilityExpressions, env)
        case DropDown(styleProperties) => new DropDownQuestionWidget(q, visibilityExpressions, env)
      }
    }
  }

}
