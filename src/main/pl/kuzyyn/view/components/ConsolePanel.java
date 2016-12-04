package main.pl.kuzyyn.view.components;


import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;

public class ConsolePanel {
JPanel consolePanel;
JTextPane inputArea, outputArea;
	
	public JPanel createConsolePanel() {
		consolePanel = new JPanel(new MigLayout());
		JLabel inputLabel = new JLabel("in");
		JLabel outputLabel = new JLabel("out");
		inputArea = new JTextPane();
		inputArea = setupArea(inputArea);
		outputArea = new JTextPane();
		outputArea = setupArea(outputArea);
		
		consolePanel.add(inputLabel,"align center");
		consolePanel.add(outputLabel,"align center, wrap");
		consolePanel.add(getScrollPaneForArea(inputArea),"grow,push");
		consolePanel.add(getScrollPaneForArea(outputArea),"grow,push");
		
		consolePanel.setBorder(BorderFactory.createMatteBorder(1, 0,0,0, Color.black));

		return consolePanel;
	}
	private JScrollPane getScrollPaneForArea(JTextPane area) {
		JScrollPane jp = new JScrollPane(area);
		jp.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return jp;
	}
	private JTextPane setupArea(JTextPane area) {
		area.setEditable(false);
		area= alignBorder(area);
		return area;
	}
	public JPanel getConsolePanel() {
		return consolePanel;
		
	}
	
	JTextPane alignBorder(JTextPane area){
		area.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return area;
	}
	public JTextPane getInputArea() {
		return inputArea;
	}
	public JTextPane getOutputArea() {
		return outputArea;
	}

}
