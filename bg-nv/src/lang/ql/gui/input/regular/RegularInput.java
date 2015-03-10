package lang.ql.gui.input.regular;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lang.ql.gui.input.Input;
import lang.ql.semantics.ValueTable;
import lang.ql.semantics.errors.Message;
import lang.ql.semantics.values.Value;

/**
 * Created by Nik on 3-3-15.
 */
public abstract class RegularInput<T, U extends Control> extends Input<U>
{
    private Text errorField;

    public RegularInput(String id, U control, Boolean visible, Boolean disabled)
    {
        super(id, control, visible, disabled);

        this.errorField = new Text(null);
        this.errorField.setFill(Color.FIREBRICK);
        this.errorField.setVisible(false);
        this.errorField.setManaged(false);

        this.getInputNode().getChildren().add(this.errorField);
    }

    public abstract Value convertUserInputToValue(T userInput);

    @Override
    public void update(ValueTable valueTable)
    {
        setChanged();
        notifyObservers(valueTable);
    }

    public void processUserInput(T userInput, ValueTable valueTable)
    {
        valueTable.storeValue(this.getId(), this.convertUserInputToValue(userInput));
        this.update(valueTable);
    }

    protected void resetValidation()
    {
        this.errorField.setText(null);
        this.errorField.setVisible(false);
        this.errorField.setManaged(false);
    }

    protected void addValidationError(Message validationError)
    {
        this.errorField.setText(validationError.getMessage());
        this.errorField.setVisible(true);
        this.errorField.setManaged(true);
    }

    public Text getErrorField()
    {
        return this.errorField;
    }
    
    public abstract void attachListener(ValueTable valueTable);

    protected ChangeListener<T> constructChangeListener(ValueTable valueTable)
    {
        return (observable, oldValue, newValue) -> processUserInput(newValue, valueTable);
    }
}