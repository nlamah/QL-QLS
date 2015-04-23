package org.nlamah.QL.Views.Form;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import org.nlamah.QL.Helper.QLHelper;
import org.nlamah.QL.ViewControllers.Form.BooleanQuestionViewController;
import org.nlamah.QL.Views.Abstract.FormElementView;

@SuppressWarnings("serial")
public class BooleanQuestionView extends FormElementView 
{	
	private JLabel typeLabel;
	private JLabel questionLabel;
	private JCheckBox checkBox;
	
	public BooleanQuestionView(BooleanQuestionViewController viewController) 
	{
		super(viewController);
	}
	
	public void fillInType(String type)
	{
		typeLabel.setText(type);
	}
	
	public void fillInQuestionString(String questionString)
	{
		questionLabel.setText(QLHelper.surroundStringWithHtmlTags(questionString));
	}
	
	public void fillInCheckbox(boolean isChecked)
	{
		checkBox.setSelected(isChecked);
	}

	public void initializeComponents()
	{
		typeLabel = new JLabel();
		questionLabel = new JLabel();
		checkBox = new JCheckBox("Yes");
		checkBox.addItemListener((BooleanQuestionViewController) viewController);
	}
	
	public void addComponentsToView()
	{	
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(typeLabel);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(questionLabel);
		add(Box.createRigidArea(new Dimension(10, 0)));
		add(checkBox);
		add(Box.createRigidArea(new Dimension(10, 0)));
	}

	@Override
	public void layoutView() 
	{	
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBackground(Color.white);
		
		setPreferredSize(new Dimension(QLHelper.contentWidth(), 100));
        setMaximumSize(getPreferredSize()); 
        setMinimumSize(getPreferredSize());
	}
}
