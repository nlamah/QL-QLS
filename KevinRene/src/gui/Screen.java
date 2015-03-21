package gui;

import javax.swing.JComponent;

import ql.ast.expression.Identifier;
import ql.gui.DefaultChangeHandler;
import ql.gui.UIComponent;
import ql.gui.structure.Panel;
import qls.gui.structure.UIPage;
import qls.gui.structure.UISection;

public abstract class Screen extends DefaultChangeHandler implements UIComponent {
	private UIPage page;
	
	public Screen(Identifier identifier) {
		page = new UIPage(identifier);
	}
	
	public void addSection(UISection screenSection) {
		addSection(screenSection, "");
	}
	public void addSection(UISection screenSection, String migSetting) {		
		page.addComponent(screenSection, migSetting);
		screenSection.setHandler(this);
	}
	
	public Panel getScreen() {
		return page;
	}
	
	public void hideScreen() {
		page.getComponent().setVisible(false);
	}
	
	public void showScreen() {
		page.getComponent().setVisible(true);
	}
	
	@Override
	public void updateComponent() {
		page.updateComponent();
	}

	@Override
	public JComponent getComponent() {
		return page.getComponent();
	}
	
	@Override
	public String toString() {
		return page.toString();
	}
}