package main.pl.kuzyyn.controller.components;

import javax.swing.JPanel;

import main.pl.kuzyyn.view.components.MainFrame;

public class ShowStatusBar implements GUICommand {

	MainFrame mainFrame;
	JPanel statusPanel;

	ShowStatusBar(MainFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	
	@Override
	public void execute() {
		if(statusPanel ==null){
			 statusPanel = mainFrame.getStatusBar().getStatusPanel();
			}
				mainFrame.getMainPanel().add(statusPanel,"pushx,growx,south");
				mainFrame.getMainPanel().revalidate();
				mainFrame.getMainPanel().repaint();
	}

}
