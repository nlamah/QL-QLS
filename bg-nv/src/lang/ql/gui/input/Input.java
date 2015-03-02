package lang.ql.gui.input;

import lang.ql.ast.expression.Expr;
import lang.ql.gui.GuiElement;

/**
 * Created by Nik on 17-2-15.
 */
public abstract class Input extends GuiElement
{
    private String id;
    private Boolean disabled;

    public Input(String id)
    {
        this(id, true, false);
    }

    public Input(String id, Boolean visible, Boolean disabled)
    {
        super(visible);
        this.id = id;
        this.disabled = disabled;
    }

    public Boolean getDisabled()
    {
        return disabled;
    }

    public void setDisabled(Boolean disabled)
    {
        this.disabled = disabled;
        setChanged();
        notifyObservers();
    }

    public String getId()
    {
        return id;
    }
}
