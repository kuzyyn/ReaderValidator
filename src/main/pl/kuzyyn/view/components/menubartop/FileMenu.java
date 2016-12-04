package main.pl.kuzyyn.view.components.menubartop;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import main.pl.kuzyyn.controller.listeners.FileMenuListener;


public class FileMenu extends MenuBarTop {
	
JMenuItem printItem,exitItem;
	
	protected JMenu createFileJMenu(){
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.add(createQCJMenuItem());	
		file.add(createSaveJMenuItem());	
		file.add(createPrintJMenuItem());
		file.add(createExitJMenuItem());	
		return file;
			
	}
		private JMenuItem createQCJMenuItem() {
			ImageIcon exitIcon = getScaledIcon(10,15,"res/file-folder.png");
			JMenuItem qcItem = new JMenuItem("Read QC File",exitIcon);
			qcItem.setMnemonic(KeyEvent.VK_Q);
			qcItem.setToolTipText("Read QC file");
			qcItem.addActionListener(new FileMenuListener(this));	
			return qcItem;
		}
		private JMenuItem createSaveJMenuItem() {
			ImageIcon saveIcon = getScaledIcon(10,15,"res/save-icon.png");
			JMenuItem saveItem = new JMenuItem("Save results",saveIcon);
			saveItem.setToolTipText("Save your results in a CSV file");
			saveItem.addActionListener(new FileMenuListener(this));	
			return saveItem;
		}
		private JMenuItem createPrintJMenuItem() {
			ImageIcon saveIcon = getScaledIcon(10,15,"res/save-icon.png");
			printItem = new JMenuItem("Print",saveIcon);
			printItem.addActionListener(new FileMenuListener(this));	
			printItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
			return printItem;
		}
	
		private JMenuItem createExitJMenuItem() {
			ImageIcon exitIcon = getScaledIcon(10,15,"res/exit.png");
			exitItem = new JMenuItem("Exit",exitIcon);
			exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));
			exitItem.setToolTipText("Exit");
			exitItem.addActionListener(new FileMenuListener(this));	
			return exitItem;
		}
		
	public JMenuItem getPrintItem(){
		return printItem;
	}
	public JMenuItem getExitItem(){
		return exitItem;
	}
}
