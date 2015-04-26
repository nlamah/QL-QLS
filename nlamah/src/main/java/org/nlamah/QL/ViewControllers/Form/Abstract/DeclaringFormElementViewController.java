package org.nlamah.QL.ViewControllers.Form.Abstract;

import java.util.ArrayList;

import org.nlamah.QL.Model.Form.Abstract.DeclaringFormElement;
import org.nlamah.QL.Views.Form.Abstract.FormElementView;

public abstract class DeclaringFormElementViewController extends FormElementViewController 
{
	private ArrayList<FormElementViewController> childViewControllers;
	private ArrayList<FormElementView> childViews;
	
	public DeclaringFormElementViewController(DeclaringFormElement modelElement) 
	{
		super(modelElement);
	}
	
	public ArrayList<FormElementViewController> childViewControllers()
	{		
		return childViewControllers;
	}
	
	public void setChildViewControllers(ArrayList<FormElementViewController> childViewControllers)
	{
		this.childViewControllers = childViewControllers;
	}

	public ArrayList<FormElementView> childViews() 
	{
		return childViews;
	}

	public void setChildViews(ArrayList<FormElementView> childViews)
	{
		this.childViews = childViews;
	}

}
