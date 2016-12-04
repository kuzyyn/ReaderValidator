package main.pl.kuzyyn.controller.components;

import javax.swing.JPanel;

import main.pl.kuzyyn.view.components.MainFrame;

public class ShowServicePanel implements GUICommand {

	MainFrame mainFrame;
	JPanel servicePanel;

	ShowServicePanel(MainFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	
	@Override
	public void execute() {
		if(servicePanel ==null){
			servicePanel = mainFrame.getMenuTop().getServicePanel();
			}
		mainFrame.getMenuTop().getMenuPanel().add(servicePanel,"pushy,growy");
		mainFrame.getMenuTop().getMenuPanel().revalidate();
		mainFrame.getMenuTop().getMenuPanel().repaint();
	}

}
