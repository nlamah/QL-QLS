package org.fugazi.gui.widgets;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.event.ItemListener;
import java.util.EventListener;

public class RadioBtn implements IWidget<Boolean> {

    private final String label;

    // todo: generalize the component
    private JRadioButton component;

    public RadioBtn(String _label) {
        this.label = _label;
        component = new JRadioButton(this.label);
    }

    @Override
    public JComponent getJComponent() {
        return component;
    }

    @Override
    public void addItemListener(ItemListener _listener) {
        component.addItemListener(_listener);
    }

    @Override
    public void addDocumentListener(DocumentListener _listener) {
        throw new AssertionError();
    }

    @Override
    public Boolean getValue() {
        return this.component.isSelected();
    }
}
