package org.fugazi.gui.widgets;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.event.ItemListener;

public interface IWidget <T> {

    /**
     * Get the Java Swing implementation.
     */
    public JComponent getJComponent();
    
    public T getValue();
    public void setValue(T _value);

    // Todo: think of that.
    public void addItemListener(ItemListener _listener); // Use it on bool
    public void addDocumentListener(DocumentListener _listener); // Use it on text
}
