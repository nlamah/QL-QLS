package gui.widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import net.miginfocom.swing.MigLayout;
import cons.Value;
import cons.ValueEnvironment;
import cons.ql.ast.expression.Identifier;
import cons.value.StringValue;

public class TextField extends Widget implements CaretListener {
	
	protected JPanel container;
	protected JTextField textField;
	protected JLabel errorLabel;
	
	public TextField (Identifier identifier, ValueEnvironment controller) {
		super(identifier, controller);

		textField = new JTextField(100);
    	textField.setMaximumSize(new Dimension(textField.getPreferredSize().width, textField.getPreferredSize().height * 2));
    	textField.setFont(new Font("Serif", Font.BOLD, 20));
    	textField.addCaretListener(this);
    	textField.setFocusable(true);
    	
    	errorLabel = new JLabel();
    	errorLabel.setFont(new Font("Serif", Font.BOLD, 20));
    	errorLabel.setVisible(true);
    	
    	container = new JPanel(new MigLayout());
    	container.add(textField);
    	container.add(errorLabel, "wrap");
	}
	
	public TextField (Identifier identifier, ValueEnvironment controller, boolean enabled) {
		this(identifier, controller);
    	textField.setEnabled(enabled);
    	textField.setFocusable(enabled);
    	if (!enabled) {
    		textField.removeCaretListener(this);
    	}
	}

	@Override
	public JComponent getComponent() {
		return this.container;
	}


	@Override
	public void setValue(Value value) {
		
		System.out.println("Set value for " + getIdentifier() + " to " + value);
		
		// Update value of the JComponent
		textField.setText(value.toString());
		storeAndNotify(getIdentifier(), value);
	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		Value value = new StringValue(textField.getText());	
		
		storeAndNotify(getIdentifier(), value);
	}
	
	protected void setError(String text) {
		textField.setBorder(BorderFactory.createLineBorder(Color.RED));
		errorLabel.setText(text);
	}
	
	protected void removeError() {
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		errorLabel.setText("");
	}
}
