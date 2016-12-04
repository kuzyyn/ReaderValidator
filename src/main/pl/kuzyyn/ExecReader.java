package main.pl.kuzyyn;

import java.awt.EventQueue;
import java.io.IOException;

import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.model.protocol.Expert96Driver;
import main.pl.kuzyyn.model.serial.ReaderConnect;
import main.pl.kuzyyn.model.serial.Sender;

public class ExecReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(() -> {
			
			GUIObjectController.getInstance().initializeMainFrame();
        });
		
	}

}
