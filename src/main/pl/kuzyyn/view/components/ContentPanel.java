package main.pl.kuzyyn.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.pl.kuzyyn.controller.communication.RSController;
import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.model.protocol.ConsoleLogger;
import main.pl.kuzyyn.model.serial.Sender;
import main.pl.kuzyyn.view.TextPrompt;
import net.miginfocom.swing.MigLayout;

public class ContentPanel implements Printable{
JLabel label1;
JPanel externalPanel,insidePanel,upperPanel;
Calendar calDate;
private JLabel operatorNameLabel;
private JLabel qcSerialLabel;
private JLabel validationDateLabel;
private JLabel readerModelLabel;
private JLabel readerYearLabel;



	public JPanel createContentPanel() {
		
		externalPanel = new JPanel();
		setupExternalpanel();
		insidePanel = new JPanel();
		upperPanel = new JPanel();
		
		setupInsidePanel();
		setupUpperPanel();
		externalPanel.add(insidePanel,"w 210mm!, h 297mm::,pushx,pushy,growy,align center"); 
	return externalPanel;
	}
	
		private void setupExternalpanel() {
			MigLayout externalMgr = new MigLayout();
			externalPanel.setLayout(externalMgr);
				
			}
		
		private void setupInsidePanel() {
		
		MigLayout internalMgr = new MigLayout("insets 10mm 20mm 10mm 20mm ");
		insidePanel.setLayout(internalMgr );
		insidePanel.setBorder(BorderFactory.createLineBorder(Color.black));	
		insidePanel.setBackground(Color.white);
		insidePanel.add(upperPanel,"wrap");
		
		JTextField rsSenderField = new JTextField(60);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (RSController.getInstance().isConnectedToCom()){
				RSController.getInstance().establishConnectionWithReader();
				} else {
					ConsoleLogger.outErrorLog("Najpierw nawi¹¿ komunikacjê");
				}
			}
			
		});
		insidePanel.add(rsSenderField);
		insidePanel.add(sendButton);
		}
		private void setupUpperPanel() {
		upperPanel.setLayout(new MigLayout("insets 5 5 30 5","[]5mm[]25mm[]5mm[]","[]5[]"));
		upperPanel.setBackground(Color.white);
		calDate = Calendar.getInstance();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-M-dd");
		String currentDate = ft.format(calDate.getTime());
		ft.applyPattern("k:mm");
		String currentTime= ft.format(calDate.getTime());
		JLabel operatorDescriptionLabel = new JLabel("Operator:");
		operatorNameLabel = new JLabel("Robert K");
		JLabel qcDescriptionLabel = new JLabel("QC Plate:");
		qcSerialLabel= new JLabel("211");
		JLabel dateDescriptionLabel = new JLabel("Validation time: ");
		validationDateLabel= new JLabel(currentDate + "  "+currentTime);
		JLabel readerDescriptionLabel = new JLabel("Reader model: ");
		readerModelLabel = new JLabel("Expert Plus");
		JLabel readerYearDescriptionLabel = new JLabel("Year of manufacture:");
		readerYearLabel= new JLabel("2000");

		upperPanel.add(operatorDescriptionLabel);
		upperPanel.add(operatorNameLabel);
		upperPanel.add(readerDescriptionLabel);
		upperPanel.add(readerModelLabel,"wrap");
		
		upperPanel.add(qcDescriptionLabel);
		upperPanel.add(qcSerialLabel);
		upperPanel.add(readerYearDescriptionLabel);
		upperPanel.add(readerYearLabel,"wrap");
		
		upperPanel.add(dateDescriptionLabel);
		upperPanel.add(validationDateLabel);

		insidePanel.revalidate();
		insidePanel.repaint();
		
	}


	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
	
	    if (page > 0) {
	         return NO_SUCH_PAGE;
	    }
	
	    // User (0,0) is typically outside the
	    // imageable area, so we must translate
	    // by the X and Y values in the PageFormat
	    // to avoid clipping.
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.translate(pf.getImageableX(), pf.getImageableY());
	
	    // Now we perform our rendering
	  insidePanel.paint(g2d);
	   
	
	    // tell the caller that this page is part
	    // of the printed document
	    return PAGE_EXISTS;
	}
	public Calendar getCurrentDate() {
		return calDate;
	}
	public void setCurrentDate(Calendar newDate) {
		calDate=newDate;
	}
	public JPanel getUpperPanel() {
		return upperPanel;
	}
	public JLabel getOperatorNameLabel() {
		return operatorNameLabel;
	}

	public JLabel getQcSerialLabel() {
		return qcSerialLabel;
	}

	public JLabel getValidationDateLabel() {
		return validationDateLabel;
	}

	public JLabel getReaderModelLabel() {
		return readerModelLabel;
	}

	public JLabel getReaderYearLabel() {
		return readerYearLabel;
	}


}
