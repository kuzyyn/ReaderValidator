package main.pl.kuzyyn.controller.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;

import main.pl.kuzyyn.controller.components.GUIController;
import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.view.components.menubartop.ViewMenu;

public class ViewMenuListener implements ItemListener {
ViewMenu viewMenu;

	public ViewMenuListener(ViewMenu viewMenu){
		if (this.viewMenu==null) this.viewMenu=viewMenu;
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		GUIController guiController = GUIObjectController.getInstance().getGuiController();
		JCheckBoxMenuItem currentItem=(JCheckBoxMenuItem) e.getSource();
		
		if (currentItem == viewMenu.getServiceCheckBox()){
			if (e.getStateChange() == ItemEvent.SELECTED){
				guiController.turnOn("SERVICE_PANEL");
			} else if (e.getStateChange() == ItemEvent.DESELECTED){
				guiController.turnOff("SERVICE_PANEL");
			}
			
		}
		if (currentItem == viewMenu.getStatusCheckBox()){
			if (e.getStateChange() == ItemEvent.SELECTED){
				guiController.turnOn("STATUS_BAR");
			} else if (e.getStateChange() == ItemEvent.DESELECTED){
				guiController.turnOff("STATUS_BAR");
			}
		}
		if (currentItem == viewMenu.getConsoleCheckBox()){
			if (e.getStateChange() == ItemEvent.SELECTED){
				guiController.turnOn("CONSOLE");
			} else if (e.getStateChange() == ItemEvent.DESELECTED){
				guiController.turnOff("CONSOLE");
			}
		}

	}

}
