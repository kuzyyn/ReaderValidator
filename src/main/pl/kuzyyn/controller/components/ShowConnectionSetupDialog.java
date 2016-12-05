package main.pl.kuzyyn.controller.components;

import java.util.TreeMap;

import javax.swing.JOptionPane;

import main.pl.kuzyyn.controller.communication.RSController;
import main.pl.kuzyyn.model.serial.ReaderConnect;
import main.pl.kuzyyn.view.components.ConnectionSetupDialog;

public class ShowConnectionSetupDialog implements GUICommand {
	ConnectionSetupDialog csd;
	int comPortIndex=-1;
	int readerTypeIndex=-1;
	GUIController gui;
	ReaderConnect rc;

	ShowConnectionSetupDialog(ConnectionSetupDialog csd){
		if (this.csd == null) this.csd=csd;
		gui = GUIObjectController.getInstance().getGuiController();
	}
	
	
	@Override
	public void execute() {
		
		 int result = csd.showConnectionSetupDialog(comPortIndex,readerTypeIndex);
		 TreeMap<String,String> labels = new TreeMap<String,String>();
		 
		
		 if (result == (JOptionPane.OK_OPTION)){
			 
			 comPortIndex=csd.getComJList().getSelectedIndex();
			 readerTypeIndex=csd.getReaderTypeList().getSelectedIndex();
			 
         	String comPort = csd.getComJList().getSelectedValue();
         	String readerType = csd.getReaderTypeList().getSelectedValue();
         	int connectionResult=-1;
         	if (comPort!= null && readerType !=null){
         		connectionResult =RSController.getInstance().connect(comPort,readerType);
         	}
         	if (connectionResult ==1){
         		labels.put("COM", comPort);       	
             	labels.put("READER_TYPE",readerType);
             	labels.put("STATUS", "connected");
             	
             	
         	} else {
         		labels.put("COM", "");       	
             	labels.put("READER_TYPE","");
             	labels.put("STATUS", "disconnected");
         	}

         	gui.update("STATUS_BAR", labels);
         	
         }
         if (result == (JOptionPane.CANCEL_OPTION)){

         }
         if (result == (JOptionPane.CLOSED_OPTION)){

         }
		
	}


}
