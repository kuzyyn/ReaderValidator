package main.pl.kuzyyn.controller.components;

import java.util.TreeMap;

import javax.swing.JLabel;

import main.pl.kuzyyn.view.components.MainFrame;
import main.pl.kuzyyn.view.components.StatusBar;

public class UpdateStatusLabel implements GUIUpdateCommand {
	MainFrame mainFrame;
	public UpdateStatusLabel(MainFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	@Override
	public <V> void execute(TreeMap<String, V> values) {
		StatusBar statusBar = mainFrame.getStatusBar();
		String readerText = (String) values.get("READER_TYPE");
		JLabel readerType = statusBar.getReaderType();
		if ((readerText != null) && (readerType != null)){
			readerType.setText(readerText);
			readerType.setEnabled(true);
		} else {
			System.out.println("Jakiœ null w reader lub tekst");
		}
		String comText = (String)values.get("COM");
		JLabel comLabel = statusBar.getPortNumber();
		if ((comText != null) && (comLabel != null)){
			comLabel.setText(comText);
			comLabel.setEnabled(true);
		}
		String statusText = (String)values.get("STATUS");
		JLabel statusLabel = statusBar.getStatusValue();

		
		if ((statusText != null) && (statusLabel != null)){
			statusLabel.setText(statusText);
		} else {
			System.out.println("Jakiœ null w statusLabel lub tekst");
		}
		// TODO Auto-generated method stub
		
	}

}
