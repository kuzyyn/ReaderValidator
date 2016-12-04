package main.pl.kuzyyn.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JCheckBox;
import javax.swing.JMenuItem;

import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.view.components.MainFrame;
import main.pl.kuzyyn.view.components.menubartop.FileMenu;
import main.pl.kuzyyn.view.components.menubartop.MenuBarTop;

public class FileMenuListener implements ActionListener {
FileMenu fileMenu;
GUIObjectController gui;
	public FileMenuListener(FileMenu fileMenu){
		this.fileMenu = fileMenu;
		this.gui=GUIObjectController.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem currentItem = (JMenuItem) e.getSource();
		if (currentItem == fileMenu.getExitItem()) {
			System.exit(0);
		}
		if (currentItem == fileMenu.getPrintItem()){
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(gui.getMainFrame().getContentPanel());
			boolean doPrint = job.printDialog();
			if (doPrint) {
				try {
					job.print();
				} catch(PrinterException ex){
					System.out.println("Printing did not succeed");
				}
			}
		}
		
		
	}


}
