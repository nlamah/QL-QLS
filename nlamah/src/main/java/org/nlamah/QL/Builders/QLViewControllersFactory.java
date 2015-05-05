package org.nlamah.QL.Builders;

import java.util.ArrayList;
import java.util.List;

import org.nlamah.QBase.QBaseHelper;
import org.nlamah.QL.Interfaces.QLFormElementVisitor;
import org.nlamah.QL.Model.Form.BooleanQuestion;
import org.nlamah.QL.Model.Form.ComputedQuestion;
import org.nlamah.QL.Model.Form.ConditionalBlock;
import org.nlamah.QL.Model.Form.ElseIfThenBlock;
import org.nlamah.QL.Model.Form.ElseThenBlock;
import org.nlamah.QL.Model.Form.Form;
import org.nlamah.QL.Model.Form.IfThenBlock;
import org.nlamah.QL.Model.Form.NumberQuestion;
import org.nlamah.QL.Model.Form.TextQuestion;
import org.nlamah.QL.Model.Form.Abstract.DeclaringFormElement;
import org.nlamah.QL.Model.Form.Abstract.FormElement;
import org.nlamah.QL.View.Controllers.BooleanQuestionViewController;
import org.nlamah.QL.View.Controllers.ComputedQuestionViewController;
import org.nlamah.QL.View.Controllers.ConditionalBlockViewController;
import org.nlamah.QL.View.Controllers.ElseIfThenBlockViewController;
import org.nlamah.QL.View.Controllers.ElseThenBlockViewController;
import org.nlamah.QL.View.Controllers.FormRootViewController;
import org.nlamah.QL.View.Controllers.IfThenBlockViewController;
import org.nlamah.QL.View.Controllers.NumberQuestionViewController;
import org.nlamah.QL.View.Controllers.TextQuestionViewController;
import org.nlamah.QL.View.Controllers.Abstract.DeclaringFormElementViewController;
import org.nlamah.QL.View.Controllers.Abstract.FormElementViewController;

public class QLViewControllersFactory implements QLFormElementVisitor
{	
	FormRootViewController rootViewController;
	FormElementViewController currentlyCreatedViewController;
	
	public QLViewControllersFactory(FormRootViewController rootViewController)
	{
		super();	
		
		this.rootViewController = rootViewController;
	}
	
	public List<FormElementViewController> createChildViewControllers(DeclaringFormElement declaringFormElement)
	{
		List<FormElementViewController> childViewControllers = null;
		
		if (QBaseHelper.arrayExistsAndHasElements(declaringFormElement.childElements()))
		{
			childViewControllers = new ArrayList<FormElementViewController>();
			
			for (FormElement formElement : declaringFormElement.childElements())
			{
				formElement.accept(this);
				
				childViewControllers.add(currentlyCreatedViewController);
			}
		}
		
		return childViewControllers;
	}
	
	public FormElementViewController createFormElementViewController(FormElement formElement)
	{
		formElement.accept(this);
		
		return currentlyCreatedViewController;
	}
	
	private void createAndAddChildViewControllers(DeclaringFormElementViewController viewController, DeclaringFormElement formElement)
	{
		viewController.setChildViewControllers(createChildViewControllers(formElement));
		
		currentlyCreatedViewController = viewController;
		
		currentlyCreatedViewController.setRootViewController(this.rootViewController);
	}
	
	private ConditionalBlockViewController createIfThenBlockViewController(ConditionalBlockViewController conditionalBlockViewController, IfThenBlock ifThenBlock)
	{
		IfThenBlockViewController ifThenBlockViewController = null;
		
		assert(ifThenBlock != null);
		
		ifThenBlockViewController = new IfThenBlockViewController(ifThenBlock);
		ifThenBlockViewController.setChildViewControllers(createChildViewControllers(ifThenBlock));

		conditionalBlockViewController.setIfThenBlockViewController(ifThenBlockViewController);
		
		return conditionalBlockViewController;
	}
	
	private ConditionalBlockViewController createElseIfThenBlockViewControllers(ConditionalBlockViewController conditionalBlockViewController, List<ElseIfThenBlock> elseIfThenBlocks)
	{
		List<ElseIfThenBlockViewController> elseIfThenBlockViewControllers = null;
		
		if (QBaseHelper.arrayExistsAndHasElements(elseIfThenBlocks))
		{
			int numberOfElseIfThenViewControllers = elseIfThenBlocks.size();
			
			elseIfThenBlockViewControllers = new ArrayList<ElseIfThenBlockViewController>(numberOfElseIfThenViewControllers);
			
			for (ElseIfThenBlock elseIfThenBlock : elseIfThenBlocks)
			{
				ElseIfThenBlockViewController viewController = new ElseIfThenBlockViewController(elseIfThenBlock);
				
				viewController.setChildViewControllers(createChildViewControllers(elseIfThenBlock));
				
				elseIfThenBlockViewControllers.add(viewController);
			}
		}
		
		conditionalBlockViewController.setElseIfThenBlockViewControllers(elseIfThenBlockViewControllers);
		
		return conditionalBlockViewController;
	}
	
	private ConditionalBlockViewController createElseThenBlockViewController(ConditionalBlockViewController conditionalBlockViewController, ElseThenBlock elseThenBlock)
	{		
		ElseThenBlockViewController elseThenBlockViewController = null;
		
		if (elseThenBlock != null)
		{
			elseThenBlockViewController = new ElseThenBlockViewController(elseThenBlock);
			
			elseThenBlockViewController.setChildViewControllers(createChildViewControllers(elseThenBlock));
		}
		
		conditionalBlockViewController.setElseThenBlockViewController(elseThenBlockViewController);
		
		return conditionalBlockViewController;
	}


	@Override
	public void visit(BooleanQuestion booleanQuestion) 
	{		
		currentlyCreatedViewController = new BooleanQuestionViewController(booleanQuestion);
		
		currentlyCreatedViewController.setRootViewController(this.rootViewController);
	}

	@Override
	public void visit(ComputedQuestion computedQuestion) 
	{
		currentlyCreatedViewController = new ComputedQuestionViewController(computedQuestion);
		
		currentlyCreatedViewController.setRootViewController(this.rootViewController);
	}

	@Override
	public void visit(ConditionalBlock conditionalBlock) 
	{
		ConditionalBlockViewController conditionalBlockViewController = ConditionalBlockViewControllerFactory.createConditionalBlockViewController(conditionalBlock);
		
		conditionalBlockViewController = createIfThenBlockViewController(conditionalBlockViewController, conditionalBlock.ifThenBlock());
		conditionalBlockViewController = createElseIfThenBlockViewControllers(conditionalBlockViewController, conditionalBlock.elseIfThenBlocks());
		conditionalBlockViewController = createElseThenBlockViewController(conditionalBlockViewController, conditionalBlock.elseThenBlock());
		
		currentlyCreatedViewController = conditionalBlockViewController;
		
		currentlyCreatedViewController.setRootViewController(this.rootViewController);
	}

	@Override
	public void visit(ElseIfThenBlock elseIfThenBlock) 
	{	
		DeclaringFormElementViewController declaringFormElementViewController = new ElseIfThenBlockViewController(elseIfThenBlock);
		
		createAndAddChildViewControllers(declaringFormElementViewController, elseIfThenBlock);
	}

	@Override
	public void visit(ElseThenBlock elseThenBlock) 
	{
		DeclaringFormElementViewController declaringFormElementViewController = new ElseThenBlockViewController(elseThenBlock);
		
		createAndAddChildViewControllers(declaringFormElementViewController, elseThenBlock);
	}

	@Override
	public void visit(Form form) 
	{
		assert false;
	}

	@Override
	public void visit(IfThenBlock ifThenBlock) 
	{
		DeclaringFormElementViewController declaringFormElementViewController = new IfThenBlockViewController(ifThenBlock);
		
		createAndAddChildViewControllers(declaringFormElementViewController, ifThenBlock);
	}

	@Override
	public void visit(NumberQuestion numberQuestion) 
	{
		currentlyCreatedViewController = new NumberQuestionViewController(numberQuestion);
		
		currentlyCreatedViewController.setRootViewController(this.rootViewController);
	}

	@Override
	public void visit(TextQuestion textQuestion) 
	{
		currentlyCreatedViewController = new TextQuestionViewController(textQuestion);
		
		currentlyCreatedViewController.setRootViewController(this.rootViewController);
	}
}
