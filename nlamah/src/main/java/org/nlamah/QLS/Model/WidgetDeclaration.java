package org.nlamah.QLS.Model;

import org.nlamah.QLS.Interfaces.QLSNodeVisitor;

public class WidgetDeclaration extends StyleDeclaration 
{
	public WidgetDeclaration(WidgetType widgetType) 
	{
		super(widgetType);
	}

	@Override
	public QLSNode accept(QLSNodeVisitor visitor) 
	{
		return visitor.visit(this);
	}
}
