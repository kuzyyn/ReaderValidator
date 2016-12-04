package main.pl.kuzyyn.model.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import main.pl.kuzyyn.controller.communication.RSController;
import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.model.protocol.ConsoleLogger;

public class ReaderConnect {
	Protocol protocol;
	InputStream in ;
    OutputStream out ;
    SerialPort serialPort ;
    CommPort commPort;
    Receiver receiver;
    Sender sender;
    public ReaderConnect(Protocol protocol){
    	this.protocol =  protocol;
    }
    
public void connect(String portName) throws Exception{
	
		boolean used = false;
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if(portIdentifier.isCurrentlyOwned())
		{	
			JOptionPane.showMessageDialog(GUIObjectController.getInstance().getMainFrame().getJMainFrame(), " currently owned");
		} 
		else{
			try{
			 commPort = portIdentifier.open(this.getClass().getName(),2000);
			} catch(PortInUseException e){
				JOptionPane.showMessageDialog(GUIObjectController.getInstance().getMainFrame().getJMainFrame(), "Port jest zaj�ty, wybierz inny port exc");
				ConsoleLogger.outErrorLog("Port COM zaj�ty");
				used=true;
			}
			if (used == false){
				ConsoleLogger.outRegularLog("Pr�ba po��czenia do portu "+portName);
               serialPort = (SerialPort) commPort;
               serialPort.setSerialPortParams(protocol.getBaudRate(),protocol.getBits(),protocol.getStopBit(),protocol.getParity());

               in = serialPort.getInputStream();
               out = serialPort.getOutputStream();    
               receiver = new Receiver(in);
               serialPort.addEventListener(receiver);
               serialPort.notifyOnDataAvailable(true);
               sender = new Sender(out,protocol);
               RSController.getInstance().setConnectedToCom(true);
               ConsoleLogger.outRegularLog("Pod��czenie do portu zako�czone sukcesem.");

			}	
		}
	}
	
	public void disconnect(){
		if (serialPort != null) {
	        try {
	        	
	        	in.close();
	        	out.close();       
	        	RSController.getInstance().setConnectedToCom(false);
	        } catch (IOException ex) {
	            ConsoleLogger.outErrorLog("B��d przy zamykaniu port�w");
	        } finally{
	        	sender.endSendThread();
	        	
	        	serialPort.removeEventListener();
	        	serialPort.close();
	        	RSController.getInstance().setConnectedToCom(false);
	        	ConsoleLogger.outRegularLog("Serial port disconnected");
	        }        
	    }
	}
}
