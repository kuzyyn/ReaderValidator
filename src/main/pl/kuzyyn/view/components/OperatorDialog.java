package main.pl.kuzyyn.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;

import main.pl.kuzyyn.controller.components.DataController;
import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.controller.listeners.DialogListener;
import main.pl.kuzyyn.controller.listeners.OperatorDialogListener;
import main.pl.kuzyyn.model.serial.AvailablePorts;
import main.pl.kuzyyn.view.DateLabelFormatter;
import main.pl.kuzyyn.view.TextPrompt;
import net.miginfocom.swing.MigLayout;

public class OperatorDialog  {
private	DataController dataController;
	private JPanel operatorPanel;
	private JOptionPane operatorOptionPane;
	private JDialog operatorDialog;
	private JDatePickerImpl datePicker;
	
	private JComboBox<String> operatorList;
	private String names;
	int closingProperty=-1;
	private JButton addOperatorButton,removeOperatorButton;
	private JTextField newOperatorField;
	

	public OperatorDialog(){
		if (this.dataController == null) {  dataController = GUIObjectController.getInstance().getDataController(); 
		System.out.println("6 Operator Dialog setUpOperatorPanel, dataController: "+this.dataController);
		initialize();
		}	
		}
	
	private void initialize() {
		createOperatorPanel();
		createJDialog();
	}

	public int showConnectionSetupDialog(){
		showDialog();
		return closingProperty;
	}

	
	private void createOperatorPanel() {
		if (operatorPanel == null) {
			operatorPanel = new JPanel(new MigLayout("hidemode 3","[]5[]","[]10[]"));
			System.out.println("1. Operator Dialog createOperatorPanel, operatorPanel by³ null??");
		setUpOperatorPanel();
		
		} 		

		
	}

	private void setUpOperatorPanel() {

		newOperatorField = new JTextField(18);
		TextPrompt  textPrompt = new TextPrompt("Operator's name",newOperatorField);
		textPrompt.setForeground(Color.black);
		newOperatorField.setEditable(true);
		newOperatorField.setVisible(false);
		textPrompt.changeAlpha(0.5f);
		
		JLabel operatorLabel  = new JLabel("Operator: ");
		operatorList = new JComboBox<String>();
		setupOperatorList();
		addOperatorButton = new JButton("Add");
		removeOperatorButton = new JButton("Delete");
		addOperatorButton.addActionListener(new OperatorDialogListener(this)); 
		/*
			public void actionPerformed(ActionEvent e) {
				
		});
		*/
		removeOperatorButton.addActionListener(new OperatorDialogListener(this));
		
		JLabel dateLabel  = new JLabel("Date and time:");	
		UtilCalendarModel model = new UtilCalendarModel();
		model.setSelected(true);

		
		Calendar date = dataController.getContentPanelDate();
		model.setDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
		Properties p = new Properties();
		//p.put("text.today", "dfffff");
		p.put("text.month", "Miesi¹c");
		p.put("text.year", "Rok");
		
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
		 datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		
		
		operatorPanel.add(operatorLabel);
		operatorPanel.add(operatorList,"growx");
		operatorPanel.add(newOperatorField,"grow");
		operatorPanel.add(addOperatorButton,"w 60::");
		operatorPanel.add(removeOperatorButton,"w 80::, wrap");
		operatorPanel.add(dateLabel);
		operatorPanel.add(datePicker,"growx");
	}

	private void setupOperatorList() {
		 names = "Robert K;Marcin G;Dominik K;Mateusz F";
		String [] namess = names.split(";");
		for (int i=0;i<namess.length;i++){
		operatorList.addItem(namess[i]);
		
		}
		
		//operatorList.addActionListener(this);
		
		
		
	}
	private void createJDialog() {
		if (operatorDialog== null){
		operatorDialog = new JDialog(GUIObjectController.getInstance().getMainFrame().getJMainFrame(), "Operator setup",true); 
		operatorDialog.setContentPane(createOptionPane());
		operatorDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		
		operatorDialog.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				operatorOptionPane.setValue(new Integer(
	                    JOptionPane.CLOSED_OPTION));
			}
		});
		operatorOptionPane.addPropertyChangeListener(new OperatorDialogListener(this));

		operatorDialog.pack();
		operatorDialog.setLocationRelativeTo(null);
		operatorDialog.setResizable(false);
		}				
		}
	private void showDialog(){
		operatorDialog.setVisible(true);
	}
		
		private JOptionPane createOptionPane() {
			operatorOptionPane = new JOptionPane(
					operatorPanel,
					    JOptionPane.PLAIN_MESSAGE,
					    JOptionPane.OK_CANCEL_OPTION,
					    null,null,null	    
						);
			return operatorOptionPane;
		}

		private void updateList() {
			DefaultListModel<String> model1 = new DefaultListModel<String>();
			String list = getOperatorList();

	}
		public JDatePickerImpl getDatePicker() {
			return datePicker;
		}
		public DataController getDataController() {
			return dataController;
		}

		private String getOperatorList() {
			// TODO Auto-generated method stub
			return names;
		}
		
		public JOptionPane getOperatorOptionPane() {
			return operatorOptionPane;
		}


		public JDialog getOperatorDialog() {
			return operatorDialog;
		}


		public void setClosingProperty(Integer value) {
			closingProperty = value;
			
		}
		public JComboBox<String>  getOperatorListBox(){
			return operatorList;
		}

		public JButton getAddOperatorButton() {
			return addOperatorButton;
		}

		public void setAddOperatorButton(JButton addOperatorButton) {
			this.addOperatorButton = addOperatorButton;
		}

		public JButton getRemoveOperatorButton() {
			return removeOperatorButton;
		}

		public void setRemoveOperatorButton(JButton removeOperatorButton) {
			this.removeOperatorButton = removeOperatorButton;
		}
		public JTextField getNewOperatorField() {
			return newOperatorField;
		}

		public void setNewOperatorField(JTextField newOperatorField) {
			this.newOperatorField = newOperatorField;
		}

	
	

}
