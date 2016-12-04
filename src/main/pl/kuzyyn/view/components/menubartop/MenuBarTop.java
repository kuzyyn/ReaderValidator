package main.pl.kuzyyn.view.components.menubartop;

import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuBarTop  {



	public JMenuBar createMenuBar(){
		JMenuBar menubar = new JMenuBar();
		menubar.add(new FileMenu().createFileJMenu());
		menubar.add(new ViewMenu().createViewJMenu());
		return menubar;
	}
		
	protected ImageIcon getScaledIcon(int width, int height, String path){
			ImageIcon ii = new ImageIcon(path);
			Image img = ii.getImage();
			Image newimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ii=new ImageIcon(newimg);
			return ii;
		}
	
}
