package main.pl.kuzyyn.controller.components;

import java.util.Calendar;

public class DataController {
GUIObjectController guiOC;
	public DataController(){
		if (guiOC == null) {
			guiOC = GUIObjectController.getInstance();
		}
	}
	
	public Calendar getContentPanelDate(){
		System.out.println("getContentPanel");
		Calendar date = guiOC.getContentPanel().getCurrentDate();
		System.out.println(date);
		return guiOC.getContentPanel().getCurrentDate();		
	}
	public void setContentPanelDate(){
		
	}
	
}
