package main.pl.kuzyyn.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.view.components.MenuTop;

public class MenuTopListener implements ActionListener {
MenuTop menuTop;
GUIObjectController gui;


public MenuTopListener(MenuTop menuTop){
	this.menuTop=menuTop;
	gui=GUIObjectController.getInstance();
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == menuTop.getOperatorButton())
		{
		gui.getGuiController().turnOn("OPERATOR_SETUP_PANEL");
		}
		if(e.getSource() == menuTop.getConnectionButton())
			{
			gui.getGuiController().turnOn("C_SETUP_PANEL");
			}
		if (e.getSource() == menuTop.getPlateInButton())
			{
			System.out.println("Plate In wcisniety");
			}
		if (e.getSource() == menuTop.getPlateInButton()){
			
		}
		
		
	}

}
