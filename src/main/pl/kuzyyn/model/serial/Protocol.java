package main.pl.kuzyyn.model.serial;

import java.util.TreeMap;

import gnu.io.SerialPort;

public interface Protocol {

	public int getBaudRate();
	public int getParity();
	public int getBits();
	public int getStopBit();
	
	
	
	SerialPort setSerialPortParams(SerialPort serialPort);
	void decodeMessage(String lastMessage);
	void onMessage(byte b);
	boolean initialize();
	public TreeMap getEstablishmentMessage();
	public String lookForMessageAndRemove(String messageType, String negativeAnswer);

}