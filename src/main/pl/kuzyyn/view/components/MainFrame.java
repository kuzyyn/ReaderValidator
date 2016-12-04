package main.pl.kuzyyn.view.components;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.pl.kuzyyn.view.components.menubartop.MenuBarTop;
import net.miginfocom.swing.MigLayout;


public class MainFrame {
	
	ContentPanel contentPanel;
	StatusBar statusBar;
	MenuTop menuTop;
	ConsolePanel console;
	JFrame mainFrame;
	JPanel mainPanel;


	public MainFrame(){
		initUI();
	}
	
	private void initUI(){
		mainFrame = new JFrame();
		setUpMainFrame();
		mainFrame.setJMenuBar(new MenuBarTop().createMenuBar());

		mainPanel = createMainPanel();
		mainFrame.add(mainPanel,"push,grow");
		
		menuTop= new MenuTop();
		mainPanel.add(menuTop.createMenuTopPanel(),"pushx,growx,wrap");
		
		mainPanel.add(createScrollPane() ,"grow,push,wrap"); 
		
		console = new ConsolePanel();
		console.createConsolePanel();
	//	mainPanel.add(,"growx,pushx,wrap");
	//	console.getConsolePanel().setVisible(false);
		
		statusBar=new StatusBar();
		mainPanel.add(statusBar.createStatusPanel(),"pushx,growx,south");

		
	}
	
	private void setUpMainFrame(){
		mainFrame.setTitle("Reader Validator 0.0.2");
		mainFrame.setSize(1000,800);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(3);
		mainFrame.setMinimumSize(new Dimension(500,200));
		mainFrame.setVisible(true);
		ImageIcon appIcon = new ImageIcon("res/settings.png");
		mainFrame.setIconImage(appIcon.getImage());
		Container pane = mainFrame.getContentPane();        
		pane.setLayout(new MigLayout("insets 0"));
		
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel();
		MigLayout mainPanelLayout = new MigLayout("insets 0","","[]0[]0[]"	
				);
		panel.setLayout(mainPanelLayout);
		
		return panel;
	}
	private JScrollPane createScrollPane(){
		contentPanel = new ContentPanel();
		JPanel contentJPanel =  contentPanel.createContentPanel();
		 JScrollPane scrollPane = new JScrollPane(contentJPanel);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(50, 30, 300, 50);
	        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
	    
		return scrollPane;
	}
	
	public StatusBar getStatusBar(){
		return statusBar;
	}

	public JFrame getJMainFrame(){
		return mainFrame;
	}
	
	public JPanel getMainPanel(){
		return mainPanel;
	}

	public MenuTop getMenuTop() {
		return menuTop;
	}
	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public ConsolePanel getConsole() {
		// TODO Auto-generated method stub
		return console;
	}
	
}
