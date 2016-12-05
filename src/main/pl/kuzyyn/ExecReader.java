package main.pl.kuzyyn;

import java.awt.EventQueue;

import main.pl.kuzyyn.controller.components.GUIObjectController;

public class ExecReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(() -> {
			
			GUIObjectController.getInstance().initializeMainFrame();
        });
		
	}

}
