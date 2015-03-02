package gui.widgets;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import cons.Value;
import cons.ValueEnvironment;
import cons.ql.ast.expression.Identifier;
import cons.value.IntegerValue;

public class IntegerField extends TextField implements CaretListener {
	
	public IntegerField (Identifier identifier, ValueEnvironment controller) {
		super(identifier, controller);
		textField.setText("0");
	}
	
	public IntegerField (Identifier identifier, ValueEnvironment controller, boolean enabled) {
		super(identifier, controller, enabled);
		textField.setText("0");
	}

	@Override
	public void setValue(Value value) {
		
		System.out.println("Set value for " + getIdentifier() + " to " + value);
		
		// Update value of the JTextField
		textField.setText(value.toString());

		storeAndNotify(getIdentifier(), value);
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		try {
			Value value = new IntegerValue(Integer.parseInt(textField.getText()));
			
			storeAndNotify(getIdentifier(), value);

			removeError();
		}
		catch (NumberFormatException nfe) {
			setError("Not a valid integer");
		}
	}
}
