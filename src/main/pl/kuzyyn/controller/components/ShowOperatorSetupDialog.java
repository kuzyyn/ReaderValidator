package main.pl.kuzyyn.controller.components;

import java.util.Calendar;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import main.pl.kuzyyn.view.components.OperatorDialog;

public class ShowOperatorSetupDialog implements GUICommand {
	OperatorDialog od;
	GUIController gui;

	ShowOperatorSetupDialog(OperatorDialog od){
		if (this.od == null) this.od=od;
		gui = GUIObjectController.getInstance().getGuiController();
	}
	@Override
	public void execute() {
		int result = od.showConnectionSetupDialog();
		
		TreeMap<String,String> labels = new TreeMap<String,String>();
	
		 if (result == (JOptionPane.OK_OPTION)){
			 
			Calendar selectedDate = (Calendar)od.getDatePicker().getModel().getValue();
			String stringDate =selectedDate.get(Calendar.YEAR)+"-"+selectedDate.get(Calendar.MONTH)+"-"+selectedDate.get(Calendar.DAY_OF_MONTH);
			String operatorName = (String) od.getOperatorListBox().getSelectedItem();
			//System.out.println("Odczytana data z date pickera: " +stringDate + "// controller.components.ShowOperatorSetupDialog");
        	labels.put("DATE", stringDate);
        	labels.put("OPERATOR", operatorName);
        	
        	

        	gui.update("UPPER_CONTENT", labels);
        }
        if (result == (JOptionPane.CANCEL_OPTION)){
        	System.out.println("Cancel");
        }
        if (result == (JOptionPane.CLOSED_OPTION)){
        	System.out.println("closed");
        }
        
	}

}
