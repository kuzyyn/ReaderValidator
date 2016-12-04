package main.pl.kuzyyn.controller.components;

import javax.swing.JPanel;

import main.pl.kuzyyn.view.components.MainFrame;

public class HideServicePanel implements GUICommand {

	MainFrame mainFrame;
	JPanel servicePanel;

	HideServicePanel(MainFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	
	@Override
	public void execute() {
		if(servicePanel ==null){
			servicePanel = mainFrame.getMenuTop().getServicePanel();
			}
			mainFrame.getMenuTop().getMenuPanel().remove(servicePanel);
			mainFrame.getMenuTop().getMenuPanel().revalidate();
			mainFrame.getMenuTop().getMenuPanel().repaint();
	}

}
