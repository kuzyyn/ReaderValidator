package main.pl.kuzyyn.controller.components;

import javax.swing.JPanel;

import main.pl.kuzyyn.view.components.MainFrame;

public class HideConsole implements GUICommand {

	MainFrame mainFrame;
	JPanel consolePanel;

	HideConsole(MainFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	@Override
	public void execute() {
		if(consolePanel ==null){
			consolePanel = mainFrame.getConsole().getConsolePanel();
			}
		mainFrame.getMainPanel().remove(consolePanel);
		mainFrame.getMainPanel().revalidate();
		mainFrame.getMainPanel().repaint();
	}

}
