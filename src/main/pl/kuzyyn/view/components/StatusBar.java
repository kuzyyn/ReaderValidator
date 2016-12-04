package main.pl.kuzyyn.view.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

public class StatusBar {
JLabel statusValue,readerType,portNumber;

JPanel statusPanel;
	public JPanel createStatusPanel() {
		statusPanel = new JPanel();
		statusPanel.setLayout(new MigLayout(
				));
		
		JLabel statusLabel = new JLabel("Status: ");
		statusValue = new JLabel("disconnected");
		JLabel readerLabel = new JLabel("Reader model: ");
		readerType = new JLabel("bbb");
		JLabel portLabel = new JLabel("COM port: ");
		portNumber = new JLabel ("");
		statusPanel.add(statusLabel);
		statusPanel.add(statusValue);
		
		statusPanel.add(readerLabel,"gapleft 10");
		statusPanel.add(readerType);
		
		statusPanel.add(portLabel,"gapleft 10");
		statusPanel.add(portNumber);
		
		readerLabel.setEnabled(false);
		readerType.setEnabled(false);
		portLabel.setEnabled(false);
		portNumber.setEnabled(false);
		statusValue=setRightBorder(statusValue);
		
		statusPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		//readerType=setRightBorder(readerType);
		
		
		statusPanel.setBackground(Color.white);
		return statusPanel;
	}
	private JLabel setRightBorder(JLabel label) {
		
		Border paddingBorder = BorderFactory.createEmptyBorder(2,10,2,10);
		Border border =BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black);
		label.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		return label;
	}
	
	public JLabel getStatusValue() {
		return statusValue;
	}
	public JLabel getReaderType() {
		return readerType;
	}
	public JLabel getPortNumber() {
		return portNumber;
	}
	public JPanel getStatusPanel() {
		return statusPanel;
	}
	

}
