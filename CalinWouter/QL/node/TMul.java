/* This file was generated by SableCC (http://www.sablecc.org/). */

package QL.node;

import QL.analysis.*;

@SuppressWarnings("nls")
public final class TMul extends Token
{
    public TMul()
    {
        super.setText("*");
    }

    public TMul(int line, int pos)
    {
        super.setText("*");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TMul(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMul(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TMul text.");
    }
}
