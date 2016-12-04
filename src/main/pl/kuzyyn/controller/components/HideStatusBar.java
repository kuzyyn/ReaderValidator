package main.pl.kuzyyn.controller.components;

import javax.swing.JPanel;

import main.pl.kuzyyn.view.components.MainFrame;

public class HideStatusBar implements GUICommand {

	MainFrame mainFrame;
	JPanel statusPanel;

	HideStatusBar(MainFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	
	@Override
	public void execute() {
		if(statusPanel ==null){
			 statusPanel = mainFrame.getStatusBar().getStatusPanel();
			}
			mainFrame.getMainPanel().remove(statusPanel);
			mainFrame.getMainPanel().revalidate();
			mainFrame.getMainPanel().repaint();
	}


}
