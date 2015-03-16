package org.uva.ql.view.widgit;

import javax.swing.JCheckBox;

import org.uva.ql.ast.type.Type;
import org.uva.ql.ast.type.UndefinedType;
import org.uva.ql.ast.value.BoolValue;
import org.uva.ql.ast.value.Value;
import org.uva.ql.view.listener.CheckBoxListener;
import org.uva.ql.view.listener.WidgetListener;

public class CheckBox extends Widget {

	private JCheckBox checkBox;
	private WidgetListener widgetListener;

	public CheckBox(WidgetListener listener) {
		super();
		checkBox = new JCheckBox();
		this.widgetListener = listener;
		checkBox.setOpaque(false);
	}

	@Override
	public void setIdentifier(String identifier) {
		super.setIdentifier(identifier);
		CheckBoxListener checkBoxListener = new CheckBoxListener(this.widgetListener, getIdentifier(), this);
		checkBox.addItemListener(checkBoxListener);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getValue() {
		return checkBox.isSelected();
	}

	@Override
	public JCheckBox getWidget() {
		return checkBox;
	}

	@Override
	public void setWidgetValue(Value value, Type type) {
		if (!type.isEqual(new UndefinedType())) {
			if (type.isBool() && value.isDefined()) {
				BoolValue booleanValue = (BoolValue) value;
				if (booleanValue.value()) {
					checkBox.setSelected(true);
				} else {
					checkBox.setSelected(false);
				}
			} else {
				checkBox.setSelected(false);
			}
		} else {
			checkBox.setSelected(false);
		}
	}
}
