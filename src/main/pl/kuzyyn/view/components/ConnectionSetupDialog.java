package main.pl.kuzyyn.view.components;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.controller.listeners.DialogListener;
import main.pl.kuzyyn.model.serial.AvailablePorts;
import net.miginfocom.swing.MigLayout;

public class ConnectionSetupDialog {

	JPanel connectionSubPanel;
	JList<String> comJList;
	JList<String> readerList;
	JOptionPane optionPane;
	JDialog dialog;
	int closingProperty=-1;
	
	public int showConnectionSetupDialog(int comPortIndex, int readerTypeIndex){
		updateLists(comPortIndex,readerTypeIndex);
		createSubPanel();
		createJDialog();
		return closingProperty;
	}

	private void createSubPanel() {
		if (connectionSubPanel == null) {
			connectionSubPanel = new JPanel(new MigLayout("","[]5[]","[]15[]"));
		setUpSubPanel();
		} 		
	}
		private void setUpSubPanel() {
			JLabel comLabel  = new JLabel("Select com port");
			JLabel readerLabel  = new JLabel("Select reader");	
			
			connectionSubPanel.add(comLabel,"align center");
			connectionSubPanel.add(readerLabel,"align center, wrap");
			connectionSubPanel.add(createComScrollList(),"h 150::,w 45%::,growy");
			connectionSubPanel.add(createReaderScrollList(),"push,grow");
					
		}
		
		private JScrollPane createComScrollList(){
			comJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane= getScrollPane(comJList);			
						
			Border paddingBorder = BorderFactory.createEmptyBorder(2,2,2,5);
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
			scrollPane.setBorder(border);
			comJList.setBorder(paddingBorder);	
			return scrollPane;
		}
		private JScrollPane getScrollPane(JList<String> list) {
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(list);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(50, 30, 300, 50);
	        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
			return scrollPane;
		}

		private JScrollPane createReaderScrollList(){
			readerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane= getScrollPane(readerList);			
						
			Border paddingBorder = BorderFactory.createEmptyBorder(2,2,2,5);
			Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
			scrollPane.setBorder(border);
			readerList.setBorder(paddingBorder);	
			return scrollPane;
			
		}
	private void createJDialog() {
		
	dialog = new JDialog(GUIObjectController.getInstance().getMainFrame().getJMainFrame(), "Connection setup",true); 
	dialog.setContentPane(createOptionPane());
	dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	
	
	dialog.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
			optionPane.setValue(new Integer(
                    JOptionPane.CLOSED_OPTION));
		}
	});
	optionPane.addPropertyChangeListener(new DialogListener(this));

	dialog.pack();
	dialog.setLocationRelativeTo(null);
	dialog.setResizable(false);
	dialog.setVisible(true);
			
	}

	
	private JOptionPane createOptionPane() {
		 optionPane = new JOptionPane(
				connectionSubPanel,
				    JOptionPane.PLAIN_MESSAGE,
				    JOptionPane.OK_CANCEL_OPTION,
				    null,null,null	    
					);
		return optionPane;
	}

	private void updateLists(int comPortIndex,int readerTypeIndex) {
		DefaultListModel<String> model1 = new DefaultListModel<String>();
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		Vector<String> vector = AvailablePorts.listPorts();
		for (int i =0; i<vector.size();i++){
			model1.addElement(vector.get(i));
		}
		if (comJList ==null) {
			comJList = new JList<String>(model1);
		} else{
			comJList.setModel(model1);
		}
		vector.removeAllElements();
		vector = getReaderList();
		for (int i =0; i<vector.size();i++){
			model2.addElement(vector.get(i));
		}
		
		if (readerList ==null) {
			readerList = new JList<String>(model2);
		} else
		readerList.setModel(model2);
		
		comJList.setSelectedIndex(comPortIndex);
		readerList.setSelectedIndex(readerTypeIndex);
	}



	private Vector<String> getReaderList() {
		Vector<String> reader= new Vector<String>();
		reader.add("Expert96");
		reader.add("Expert Plus");
		reader.add("UVM 340");
		reader.add("UVM 22");
		reader.add("UVM 340");
		reader.add("UVM 340");
		reader.add("UVM 340");
		return reader;
		
		
	}
	public JDialog getDialog(){
		return dialog;
	}
	
	public JList<String> getComJList() {
		return comJList;
	}
	public JList<String> getReaderTypeList() {
		return readerList;
	}

	public JOptionPane getOptionPane() {
		
		return optionPane;
	}

	public void setClosingProperty(Integer value) {
		this.closingProperty=value;
		
	}
}
