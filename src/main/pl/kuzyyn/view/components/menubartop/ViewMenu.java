package main.pl.kuzyyn.view.components.menubartop;

import java.awt.event.ItemEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.controller.listeners.ViewMenuListener;

public class ViewMenu extends MenuBarTop {
GUIObjectController windowControl;
JCheckBoxMenuItem serviceMenu,statusCheck,consoleMenu;

	public ViewMenu(){
		
	}
	
	protected JMenu createViewJMenu(){
		JMenu jview = new JMenu("View");
		jview.add(createBarsJMenu());	
		
		
		return jview;
	}
	
		private JMenu createBarsJMenu(){ 
			JMenu jshow = new JMenu("Toolbars");
			jshow.add(createStatusJMenuItem());
			jshow.add(createStatusToggle());
			jshow.add(createServiceToggle());
			jshow.add(createConsoleToggle());
			return jshow;
		}
		
		private JMenuItem createStatusJMenuItem() {
			JMenuItem statusItem = new JMenuItem("Status");
			return statusItem;
		}
		private JCheckBoxMenuItem createStatusToggle(){
			
			statusCheck =  new JCheckBoxMenuItem("Show status bar");
			statusCheck.setSelected(true);
			statusCheck.addItemListener(new ViewMenuListener(this));
			return statusCheck;
		} 
		private JCheckBoxMenuItem createServiceToggle(){
			serviceMenu = new JCheckBoxMenuItem("Show service menu");
			serviceMenu.setSelected(false);
			serviceMenu.addItemListener(new ViewMenuListener(this)); 
			serviceMenu.setActionCommand("serviceMenu");
			return serviceMenu;
		}
		private JCheckBoxMenuItem createConsoleToggle(){
			consoleMenu = new JCheckBoxMenuItem("Show console");
			consoleMenu.setSelected(false);
			consoleMenu.addItemListener(new ViewMenuListener(this)); 
			consoleMenu.setActionCommand("consoleMenu");
			return consoleMenu;
		}
		
		public JCheckBoxMenuItem getServiceCheckBox(){
			return serviceMenu;	
		}
		public JCheckBoxMenuItem getStatusCheckBox(){
			return statusCheck;	
		}
		public JCheckBoxMenuItem getConsoleCheckBox(){
			return consoleMenu;	
		}
}
