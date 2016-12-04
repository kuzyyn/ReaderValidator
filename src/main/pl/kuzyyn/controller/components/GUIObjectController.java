package main.pl.kuzyyn.controller.components;


import main.pl.kuzyyn.model.protocol.ConsoleLogger;
import main.pl.kuzyyn.view.components.*;

public class GUIObjectController {
	private MainFrame mainFrame;
	private GUIController guiController;
	private DataController dataController;
	private volatile static GUIObjectController objectController;
	private GUIObjectController(){};
	
	public static GUIObjectController getInstance(){
		if (objectController==null){
			synchronized(GUIObjectController.class){
				if(objectController ==null){
					objectController = new GUIObjectController();
				}
			}
		}
		return objectController;
	}

	public void initializeMainFrame(){
		
		guiController = new GUIController();
		dataController = new DataController();
		mainFrame = new MainFrame();
		setGUICommands();
		new ConsoleLogger(mainFrame.getConsole());
		
	}
	
	private void setGUICommands() {
		NoCommand noCommand = new NoCommand();
		ShowStatusBar showSB = new ShowStatusBar(mainFrame);
		HideStatusBar hideSB = new HideStatusBar(mainFrame);
		ShowServicePanel showSP = new ShowServicePanel(mainFrame);
		HideServicePanel hideSP = new HideServicePanel(mainFrame);
		ShowConnectionSetupDialog csd = new ShowConnectionSetupDialog(new ConnectionSetupDialog());
		ShowOperatorSetupDialog od = new ShowOperatorSetupDialog(new OperatorDialog());
		ShowConsole showConsole = new ShowConsole(mainFrame);
		HideConsole hideConsole = new HideConsole(mainFrame);

		
		guiController.setCommand("STATUS_BAR", showSB, hideSB);
		guiController.setCommand("SERVICE_PANEL", showSP, hideSP);
		guiController.setCommand("C_SETUP_PANEL", csd, noCommand);
		guiController.setCommand("OPERATOR_SETUP_PANEL",od,noCommand);
		guiController.setCommand("CONSOLE", showConsole, hideConsole);
		
		UpdateStatusLabel updateSL = new UpdateStatusLabel(mainFrame);
		UpdateUpperContentLabels uucl = new UpdateUpperContentLabels(getContentPanel() );
	
		guiController.setUpdateCommand("STATUS_BAR", updateSL);
		guiController.setUpdateCommand("UPPER_CONTENT", uucl);
	}

	public MainFrame getMainFrame(){
		return mainFrame;
	}
	public GUIController getGuiController(){
		return guiController;
	}
	public ContentPanel getContentPanel(){
		return mainFrame.getContentPanel();
	}
	public DataController getDataController(){
		return dataController;
	}




	
	
}
