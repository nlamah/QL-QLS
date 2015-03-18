package gui.file;

import gui.UIComponent;

import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class FormFileChooser extends UIComponent {
	private JFileChooser fileChooser;
	
	public FormFileChooser() {
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FormFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
	}
	
	public File getSelectedFile() {
		return fileChooser.getSelectedFile();
	}
	
	public boolean showOpenDialog(UIComponent parent) {
		int returnValue = fileChooser.showOpenDialog(parent.getComponent());
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateComponent() {
		fileChooser.revalidate();
		fileChooser.repaint();
	}

	@Override
	public JComponent getComponent() {
		return fileChooser;
	}
}