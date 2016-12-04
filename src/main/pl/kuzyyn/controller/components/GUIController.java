package main.pl.kuzyyn.controller.components;

import java.util.TreeMap;

import javax.swing.JPanel;

import main.pl.kuzyyn.view.components.MainFrame;

public class GUIController {
MainFrame mainFrame;
JPanel servicePanel,statusPanel;
TreeMap<String,GUICommand> OnMap;
TreeMap<String,GUICommand>  OffMap;
TreeMap<String,GUIUpdateCommand> updateMap;


	public GUIController(){
		if (mainFrame ==null) {
			mainFrame = GUIObjectController.getInstance().getMainFrame();
			OnMap = new TreeMap<String,GUICommand> ();
			OffMap = new TreeMap<String,GUICommand> ();
			updateMap = new TreeMap<String,GUIUpdateCommand> ();
		}
		
	}
	public void setCommand(String key, GUICommand turnOn, GUICommand turnOff){
		OnMap.put(key, turnOn);
		OffMap.put(key, turnOff);
	}
	public void setUpdateCommand(String key, GUIUpdateCommand update){
		updateMap.put(key, update);
	}
	
	public void turnOn(String key){
		GUICommand on = (GUICommand) OnMap.get(key);
		if (on != null) {
		on.execute();
		} else {
			System.out.println("Brak polecenia dla obiektu o kluczu \""+key+"\"");
		}
		
	}
	public void turnOff(String key){
		GUICommand off = (GUICommand) OffMap.get(key);
		if (off != null) {
		off.execute();
		} else {
			System.out.println("Brak polecenia dla obiektu o kluczu \""+key+"\"");
		}
	}
	
	public <V> void update(String key,TreeMap<String,V> values){
		GUIUpdateCommand update = (GUIUpdateCommand)updateMap.get(key);
		if (update != null) {
			update.execute(values);
			} else {
				System.out.println("Brak polecenia dla obiektu o kluczu \""+key+"\"");
			}
	}
	
}
