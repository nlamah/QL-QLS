package lang.ql.gui.input.regular;

import lang.ql.gui.GuiVisitor;
import lang.ql.gui.input.Input;
import lang.ql.semantics.values.DateValue;

/**
 * Created by Nik on 22-02-2015
 */
public class DateInput extends Input
{
    public DateInput(String id)
    {
        super(id);
    }

    public DateInput(String id, Boolean visible, Boolean disabled)
    {
        super(id, visible, disabled);
    }

    public <T> T accept(GuiVisitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
