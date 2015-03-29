package ql.gui.structure;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ql.gui.DefaultComponent;
import ql.gui.UIComponent;

public class Panel extends DefaultComponent {
	private JPanel panel;
	private List<UIComponent> components;
	
	public Panel() {		
		panel = new JPanel(new MigLayout("hidemode 3"));
		
		components = new ArrayList<UIComponent>();
	}
	
	public Panel(UIComponent handler) {
		this();		
		setHandler(handler);
	}
	
	protected JPanel getPanel() {
		return panel;
	}
	
	protected List<UIComponent> getComponents() {
		return components;
	}
	
	public void addComponent(UIComponent component) {
		addComponent(component, "wrap");
	}
	
	public void addComponent(UIComponent component, String migSetting) {
		components.add(component);
		
		panel.add(component.getComponent(), migSetting);
		
		component.setHandler(this);
		component.getComponent().setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	@Override
	public void updateComponent() {
		for(UIComponent component : components) {
			component.updateComponent();
		}
		
		panel.revalidate();
		panel.repaint();
	}
	
	@Override
	public JComponent getComponent() {
		return panel;
	}
}
