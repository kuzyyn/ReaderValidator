package main.pl.kuzyyn.controller.components;

import java.util.Vector;

import main.pl.kuzyyn.model.serial.AvailablePorts;

public class SerialController {
	private volatile static SerialController serialController;
	private SerialController(){};

	public static SerialController getInstance(){
		if (serialController==null){
			synchronized(SerialController.class){
				if(serialController ==null){
					serialController = new SerialController();
				}
			}
		}
		return serialController;
	}
	
	public Vector<String> getSerialComList(){
		return AvailablePorts.listPorts();
	}

}
