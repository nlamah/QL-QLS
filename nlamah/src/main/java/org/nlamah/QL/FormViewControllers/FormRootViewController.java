package org.nlamah.QL.FormViewControllers;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.nlamah.QL.FormModel.Form;
import org.nlamah.QL.FormModel.FormElement;
import org.nlamah.QL.FormViews.ContentView;
import org.nlamah.QL.FormViews.NavigationView;

public class FormRootViewController extends FormElementViewController
{
	private final static int FRAME_WIDTH = 900;
	private final static int FRAME_HEIGHT = 600;
	
	private JFrame frame;
	private NavigationView navigationView;
	private ContentView contentView;
	
	public FormRootViewController(Form form)
	{
		super(form);
		
		loadFrame();
		
		loadNavigationAndContentView();
		
		addNavigationAndContentViews();
	}
	
	public void showForm()
	{
		frame.setVisible(true);
	}
	
	private void loadFrame()
	{
		frame = new JFrame();
		
		frame.setTitle(((Form)modelElement()).getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void loadNavigationAndContentView()
	{
		navigationView = new NavigationView();
		
		contentView = new ContentView();
		
		ArrayList<FormElementViewController> childViewControllers = childViewControllers();
		
		for (int i = 0; i < childViewControllers.size(); i++)
		{
			contentView.add(Box.createVerticalGlue());
			
			contentView.add(childViewControllers.get(i).view());
		}
	}
	
	private void addNavigationAndContentViews()
	{	
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navigationView, new JScrollPane(contentView));
        frame.setContentPane(splitPane);
	}


	@Override
	public void modelStateChanged(FormElement formElement) 
	{
		System.out.println("test");
	}

	@Override
	int preferredViewHeight() 
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
