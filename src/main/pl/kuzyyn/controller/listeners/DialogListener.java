package main.pl.kuzyyn.controller.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import main.pl.kuzyyn.view.components.ConnectionSetupDialog;

public class DialogListener implements PropertyChangeListener {
ConnectionSetupDialog csd;

public DialogListener(ConnectionSetupDialog csd){
	this.csd=csd;
}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();
		JDialog dialog = csd.getDialog();
		JOptionPane optionPane = csd.getOptionPane();
		if (dialog.isVisible() && (e.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY) ) ){
			Object value = optionPane.getValue();
			if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
            csd.setClosingProperty((Integer)value);
			dialog.setVisible(false);

		}
		
	}
}
