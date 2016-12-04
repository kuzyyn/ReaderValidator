package main.pl.kuzyyn.controller.components;

import javax.swing.JPanel;

import main.pl.kuzyyn.view.components.MainFrame;

public class ShowConsole implements GUICommand {
	MainFrame mainFrame;
	JPanel consolePanel;

	ShowConsole(MainFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	
	@Override
	public void execute() {
		if(consolePanel ==null){
			consolePanel = mainFrame.getConsole().getConsolePanel();
			}
		mainFrame.getMainPanel().add(consolePanel,"growx,pushx,wrap, h 200px::");
		mainFrame.getMainPanel().revalidate();
		mainFrame.getMainPanel().repaint();
	}
}
