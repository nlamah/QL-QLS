/* This file was generated by SableCC (http://www.sablecc.org/). */

package QL.node;

import QL.analysis.*;

@SuppressWarnings("nls")
public final class TTtrue extends Token
{
    public TTtrue()
    {
        super.setText("true");
    }

    public TTtrue(int line, int pos)
    {
        super.setText("true");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TTtrue(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTTtrue(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TTtrue text.");
    }
}
