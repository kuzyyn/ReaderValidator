package main.pl.kuzyyn.view.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import main.pl.kuzyyn.controller.listeners.MenuTopListener;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class MenuTop {
JPanel menuPanel,connectionPanel,controlPanel,servicePanel,userPanel,qcPanel;


private JButton connectionSetupButton,autoDetectButton,plateInButton,plateOutButton,initializeButton,
singleMeasureButton,operatorButton,qcButton,calibrationButton,qcMeasurementButton,ledCheckButton,positionCheckButton;
private JLabel connectionLabel,operationLabel,userLabel,qcLabel,serviceLabel;

	public JPanel createMenuTopPanel() {
		JPanel outerPanel = new JPanel (new MigLayout("insets 0"));
		outerPanel.setBackground(new Color(0xd3e3f2));
		outerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		menuPanel = new JPanel();
		setupMenuPanel();
		menuPanel.add(createUserPanel(),"pushy,growy");
		menuPanel.add(createControlPanel(),"pushy,growy");
		menuPanel.add(createConnectionSection(),"pushy,growy");
		menuPanel.add(createQCSection(),"pushy,growy");
		outerPanel.add(menuPanel,"pushx, align center");
		disableNotReadyButtons();
		return outerPanel;
	}
		private void setupMenuPanel() {
			menuPanel.setLayout(new MigLayout("insets 3 3 5 3"));
			menuPanel.setBackground(new Color(0xd3e3f2));
		}
		
		private JPanel createUserPanel() {
			userPanel = new JPanel();
			setUpSectionPanel(userPanel);
			operatorButton = new JButton("Operator");
			setUpMenuButton(operatorButton);
			qcButton = new JButton("QC Plate");
			setUpMenuButton(qcButton);
			userLabel = new JLabel("general setup");
			setUpMenuLabel(userLabel);
			userPanel.add(operatorButton,"pushx,growx,wrap");
			userPanel.add(qcButton,"pushx,growx,wrap");
			userPanel.add(userLabel,new CC().alignX("center").spanX());			
			return userPanel;
		}

		private JPanel createControlPanel() {
			controlPanel = new JPanel();
			setUpSectionPanel(controlPanel);
			initializeButton = new JButton("Initialize");
			setUpMenuButton(initializeButton);
			plateInButton = new JButton("Plate in");
			setUpMenuButton(plateInButton);
			plateOutButton =new JButton("Plate out");
			setUpMenuButton(plateOutButton);
			singleMeasureButton = new JButton("<html><center>"+"Single"+"<br>"+"Measurement"+"</center></html>");
			setUpMenuButton(singleMeasureButton);
			operationLabel = new JLabel("reader control");
			setUpMenuLabel(operationLabel);
			controlPanel.add(initializeButton,"span 1 2,growy,pushy");
			controlPanel.add(plateInButton,",pushx,growx");
			controlPanel.add(singleMeasureButton,"pushy,growy,span 1 2,wrap");
			controlPanel.add(plateOutButton,"pushx,growx,wrap");		
			controlPanel.add(operationLabel,new CC().alignX("center").spanX());	
			return controlPanel;
		}

		private JPanel createConnectionSection() {
			connectionPanel = new JPanel();
				setUpSectionPanel(connectionPanel);
			
			connectionSetupButton = new JButton("<html><center>"+"Set up"+"<br>"+"connection"+"</center></html>");
				setUpMenuButton(connectionSetupButton);
			autoDetectButton = new JButton("AUTO detect");
				setUpMenuButton(autoDetectButton);
				autoDetectButton.setEnabled(false);
			
			connectionLabel = new JLabel("connection setup");
				setUpMenuLabel(connectionLabel);
							
			connectionPanel.add(connectionSetupButton,"growy,pushy");
			connectionPanel.add(autoDetectButton, "growy,pushy,wrap");
			connectionPanel.add(connectionLabel, new CC().alignX("center").spanX());
			
			return connectionPanel;
	}
		private JPanel createQCSection(){
			qcPanel = new JPanel();
				setUpSectionPanel(qcPanel);
			calibrationButton = new JButton("Calibration");
				setUpMenuButton(calibrationButton);
			qcMeasurementButton = new JButton("<html><center>"+"Quality Check"+"<br>"+"Validation"+"</center></html>");
				setUpMenuButton(qcMeasurementButton);
			qcLabel = new JLabel("Quality Check (Validation)");
				setUpMenuLabel(qcLabel);
			qcPanel.add(calibrationButton,"pushy,growy");
			qcPanel.add(qcMeasurementButton,"pushy,growy,wrap");
			qcPanel.add(qcLabel,new CC().alignX("center").spanX());
			
			return qcPanel;
		}
		private JPanel createServiceSection() {
			 servicePanel = new JPanel();
				setUpSectionPanel(servicePanel);
						
			ledCheckButton = new JButton("Diode check");
				setUpMenuButton(ledCheckButton);
			positionCheckButton = new JButton("Position check");
				setUpMenuButton(positionCheckButton);
				
			
			serviceLabel = new JLabel("service");
				setUpMenuLabel(serviceLabel);
							
			servicePanel.add(ledCheckButton,"growx,pushx,wrap");
			servicePanel.add(positionCheckButton, "growx,pushx,wrap");
			servicePanel.add(serviceLabel, new CC().alignX("center").spanX());
			servicePanel.setVisible(true);
			
			ledCheckButton.setEnabled(false);
			positionCheckButton.setEnabled(false);
			
			return servicePanel;
	}

		private void setUpSectionPanel(JPanel panel) {
			MigLayout mg = new MigLayout("insets 3 2 4 3");
			panel.setLayout(mg);
			panel.setBackground(new Color(0xc9d8e3));
			panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			
		}
		
		private void setUpMenuLabel(JLabel label) {
			label.setOpaque(true);
			label.setBackground(new Color(0xc0d8e7));
			
		}

		private void setUpMenuButton(JButton button) {
			button.setFocusable(false);
			button.setOpaque(true);
			button.setBackground(new Color(0xa4eaff));
			button.addActionListener(new MenuTopListener(this));
			
			
		}
		
		private void disableNotReadyButtons() {
			this.qcButton.setEnabled(false);
			this.initializeButton.setEnabled(false);
			this.plateInButton.setEnabled(false);
			this.plateOutButton.setEnabled(false);
			this.singleMeasureButton.setEnabled(false);
			this.calibrationButton.setEnabled(false);
			this.qcMeasurementButton.setEnabled(false);			
		}

		public JButton getConnectionButton(){
			return connectionSetupButton;
		}
		public JButton getPlateInButton(){
			return plateInButton;
		}
		public JPanel getServicePanel() {
			if (servicePanel == null) {
				return createServiceSection();
			} else {
				return servicePanel;
			}
		}

		public JPanel getMenuPanel() {
			return menuPanel;
		}
		public JButton getOperatorButton(){
			return operatorButton;
		}
		

}
