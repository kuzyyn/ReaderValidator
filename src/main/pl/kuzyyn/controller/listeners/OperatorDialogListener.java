package main.pl.kuzyyn.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.pl.kuzyyn.view.components.OperatorDialog;

public class OperatorDialogListener implements PropertyChangeListener, ActionListener {
OperatorDialog operator;
	public OperatorDialogListener(OperatorDialog operatorDialog) {
		this.operator= operatorDialog;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();
		JDialog dialog = operator.getOperatorDialog();
		JOptionPane optionPane = operator.getOperatorOptionPane();
		if (dialog.isVisible() && (e.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY) ) ){
			Object value = optionPane.getValue();
			if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
            operator.setClosingProperty((Integer)value);
			dialog.setVisible(false);

		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		JButton addButton = operator.getAddOperatorButton();
		JButton removeButton = operator.getRemoveOperatorButton();
		JComboBox<String> list = operator.getOperatorListBox();
		JTextField operatorField = operator.getNewOperatorField();
		
		if (source.equals(addButton)){

			boolean actionFlag = true;
			if (actionFlag){
				if (addButton.getText().equals("Add")){
					addButton.setText("OK");	
					removeButton.setText("Cancel");	
					list.setVisible(false);
					operatorField.setVisible(true);
					actionFlag=false;
					
				}
			}
			if (actionFlag){
			if (addButton.getText().equals("OK")){
				addButton.setText("Add");
				removeButton.setText("Delete");
				String name = operatorField.getText();
				operatorField.setText("");
				int valid = validStatus(name);
				if (valid==0){
				list.addItem(name);
				}
				
				list.setVisible(true);
				operatorField.setVisible(false);
				if (removeButton.isEnabled()==false && list.getItemCount()>0) removeButton.setEnabled(true);
				actionFlag=false;
				}	
			}	
		}
		
		if (source.equals(removeButton)){
			boolean actionFlag = true;
			if (actionFlag){
				if (removeButton.getText().equals("Delete")){
				try{
				
					list.removeItemAt(list.getSelectedIndex());
				if (list.getItemCount()==0){
					removeButton.setEnabled(false);
				}
				} catch(ArrayIndexOutOfBoundsException ex){
					
				}
				actionFlag=false;
				}
			}	
			if (actionFlag){
				if (removeButton.getText().equals("Cancel")){
				System.out.println("Cancel");
				addButton.setText("Add");
				removeButton.setText("Delete");
				actionFlag=false;
				list.setVisible(true);
				operatorField.setVisible(false);
				}
			}			
			
		}
	} // actionPerformed

	private int validStatus(String name) {
		if (name.length()>0 && name.length()<=30) {
			return 0;
		} else		
		return -1;
	}
		
	

}
