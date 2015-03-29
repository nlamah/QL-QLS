package nl.uva.softwcons.qls.ui;

import static nl.uva.softwcons.qls.ui.widget.RawValueAndTypeToConverterMap.TABLE;

import java.util.Optional;

import nl.uva.softwcons.ql.ast.form.Form;
import nl.uva.softwcons.ql.ast.statement.Question;
import nl.uva.softwcons.ql.ui.widget.CheckboxWidget;
import nl.uva.softwcons.ql.ui.widget.TextFieldWidget;
import nl.uva.softwcons.ql.ui.widget.Widget;
import nl.uva.softwcons.ql.ui.widget.factory.DefaultWidgetFactory;
import nl.uva.softwcons.ql.ui.widget.factory.WidgetFactory;
import nl.uva.softwcons.qls.ast.stylesheet.Stylesheet;
import nl.uva.softwcons.qls.ast.widget.type.CheckboxType;
import nl.uva.softwcons.qls.ast.widget.type.DropdownType;
import nl.uva.softwcons.qls.ast.widget.type.RadioButtonType;
import nl.uva.softwcons.qls.ast.widget.type.SliderType;
import nl.uva.softwcons.qls.ast.widget.type.TextType;
import nl.uva.softwcons.qls.ast.widget.type.WidgetType;
import nl.uva.softwcons.qls.ast.widget.type.WidgetTypeVisitor;
import nl.uva.softwcons.qls.ui.style.StyleBlock;
import nl.uva.softwcons.qls.ui.widget.DropdownWidget;
import nl.uva.softwcons.qls.ui.widget.RadioButtonWidget;
import nl.uva.softwcons.qls.ui.widget.SliderWidget;

public class StylizedWidgetFactory implements WidgetFactory, WidgetTypeVisitor<Widget> {
    private final StylesheetResolver resolver;
    private final WidgetFactory defaultFactory;

    public StylizedWidgetFactory(final Form form, final Stylesheet stylesheet) {
        this.resolver = new StylesheetResolver(stylesheet, form);
        this.defaultFactory = new DefaultWidgetFactory();
    }

    @Override
    public Widget getWidget(final Question question) {
        final Optional<WidgetType> questionWidget = resolver.getWidgetType(question.getId());

        if (questionWidget.isPresent()) {
            final Widget widget = getWidgetByType(questionWidget.get());
            widget.setConverter(TABLE.get(widget.getClass(), question.getType()));

            return applyStyle(widget, resolver.getStyle(question.getId()));
        }

        return defaultFactory.getWidget(question);
    }

    @Override
    public Widget visit(final CheckboxType type) {
        return new CheckboxWidget(type.getYes());
    }

    @Override
    public Widget visit(final DropdownType type) {
        return new DropdownWidget(type.getYes(), type.getNo());
    }

    @Override
    public Widget visit(final RadioButtonType type) {
        return new RadioButtonWidget(type.getYes(), type.getNo());
    }

    @Override
    public Widget visit(final SliderType type) {
        return new SliderWidget(type.getStart(), type.getEnd(), type.getStep());
    }

    @Override
    public Widget visit(final TextType type) {
        return new TextFieldWidget();
    }

    private Widget getWidgetByType(final WidgetType widgetType) {
        return widgetType.accept(this);
    }

    private Widget applyStyle(final Widget widget, final StyleBlock style) {
        widget.getWidget().setStyle(style.asString());

        return widget;
    }

}
