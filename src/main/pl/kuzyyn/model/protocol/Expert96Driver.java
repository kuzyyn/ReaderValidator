package main.pl.kuzyyn.model.protocol;

import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import main.pl.kuzyyn.controller.communication.RSController;
import main.pl.kuzyyn.model.serial.Protocol;

public class Expert96Driver  {
	
	private static final int baudRate = 19200;
	private static final int parity = SerialPort.PARITY_EVEN;
	private static final int bits =SerialPort.DATABITS_7;
	private static final int stopBit = SerialPort.STOPBITS_2;
	
	private final static char NUL = (char)0; // null message
	private final static char STX = (char)2; // Start of text
	private final static char ETX = (char)3; // end of text
	private final static char EOT = (char)4; // end of transmission
	private final static char ENQ = (char)5; // select reader
	private final static char ACK = (char)6; // positive acknowledge to the sender
	private final static char NAK = (char)21; // negative acknowledge to the sender
	private final static char endLine=ACK; //

	private static String readerState;
	private RSController controller;
	private LinkedHashMap<String,String> messagesMap;
	private int messagesToRead = 0;
	private boolean youGotNewMessage = false;
	static boolean stopWaiting = false;
	
	public Expert96Driver(RSController rsController) {
		this.controller = rsController;
		messagesMap = new LinkedHashMap<String,String>();
	}
	
	public SerialPort setSerialPortParams(SerialPort serialPort) {
		try {
			serialPort.setSerialPortParams(baudRate,bits,stopBit,parity);
		} catch (UnsupportedCommOperationException e) {

			e.printStackTrace();
		}
		return serialPort;
	}


	
	boolean waitingForBBC=false;
	byte[] buffer = new byte[1024];
	int tail = 0;


	public void onMessage(byte b){
		if (waitingForBBC){
			waitingForBBC=false;
			buffer[tail]=b;
			tail++;
			decodeMessage(getMessage(buffer,tail));		
			tail=0;
		} else { 
			buffer[tail]=b;
			tail++;
			
			if (b == ETX){
				waitingForBBC=true;
			} else if (b==EOT || b==ENQ || b == ACK || b == NAK ){	
				System.out.println(getMessage(buffer,tail));
				decodeMessage(getMessage(buffer,tail));	
				tail = 0;
			}			
		} 	
	}
	public boolean initialize(){
		boolean result = false;
		String response;
		String message;
		message = getEndOfTransmission()+getEstablishment();
	//	controller.send(message);
		waitForMessage("ACK");
		response = getMessageFromQueue("ESTABLISHED");
		return result;
	}

	private String getMessageFromQueue(String type) {
		
		return null;
	}

	public void waitForMessage(String type) {
		boolean messageFound = false;
		stopWaiting=false;
		Timer timer = new Timer();	
		timer.schedule(new TimerTask() {
			boolean messagein = false;
			int n=0;
			@Override
			public void run() {
				if (messagesMap.containsKey(type) || ++n==15){
					
					stopWaiting=true;
					timer.cancel();
				}
				System.out.println(n);
				
			}

		}, 0, 1000);
		while (!stopWaiting){
			if (stopWaiting){
				break;
			}
		}
		System.out.println("zaraz za stopwaiting");
		
		//return messageFound;
		
		
	}

	public String getMessage(byte[] buffer, int length){
		return new String(buffer,0,tail);
	}
	
	public String getEstablishment(){
		String message = "20"+ENQ;
		return message;	
	}
	public String getEndOfTransmission(){
		String message = String.valueOf(EOT);
		return message;	
	}
	public String getAcknowledgment(){
		String message = String.valueOf(ACK);
		return message;	
	}
	private int getBBC(String fullMessage){
		int BBCcount = -1;
		if (fullMessage.length()>=3){
			
			BBCcount = fullMessage.charAt(1);
			for (int i = 2; i<fullMessage.length();i++){
					BBCcount = (BBCcount ^ fullMessage.charAt(i));	
			}
		}
		System.out.println("bbc counted: " + BBCcount);
		return BBCcount;
		
	}
	public enum messageType {endOfCommunication,communicationEstablishment,acknowledge,negAcknowledge,broken,statusCommand,controlCommand};
	
	public void decodeMessage(String lastMessage) {
		
		String messageType = getMessageType(lastMessage);
		String previousMessage = messagesMap.put(messageType, lastMessage);
		if (previousMessage!=null){
			ConsoleLogger.outErrorLog("Nadpisano klucz "+messageType+", poprzednia wartoœæ: " + getASCII(previousMessage) +", nowa wartoœæ: " + getASCII(lastMessage));
		} else{
			messagesToRead++;
			youGotNewMessage = true;
			
		}
		System.out.println("Odczytano: "+lastMessage + ", Ascii: "+getASCII(lastMessage)+ ", Typ: " +messageType );
		ConsoleLogger.inRegularLog("Odczytano: "+lastMessage + ", Ascii: "+getASCII(lastMessage)+ ", Typ: " +messageType );	
	}
	

	private String getMessageType(String lastMessage) {
		String message="BROKEN";
		if (lastMessage.equals("20"+ACK)){
			message = "ESTABLISHED";
			System.out.println("checked if ESTABLISHED");
		} else if (lastMessage.equals(String.valueOf(ACK))){
			message = "ACK";

		} else if (lastMessage.equals(String.valueOf(NAK))){
			message ="NAK";
			System.out.println("checked if NAK");
		} else if (lastMessage.equals(String.valueOf(NUL))){
			//to do 
			System.out.println("checked if NUL");
		} else if (isControlMessage(lastMessage)){
				message = "CONTROL";
				System.out.println("checked if control");
		} else if (isStatusMessage(lastMessage)){
				message = "STATUS";
				System.out.println("checked if status");
			
		
		} else {
			message = "BROKEN";
			System.out.println("Broken message: "+lastMessage);
		}
		System.out.println("Message received: "+message);	
		return message;
	}

	
		private boolean isStatusMessage(String lastMessage) {
			boolean isStatus = false;
			if (hasGoodFormat(lastMessage) && hasGoodBBC(lastMessage)) {
				String message = lastMessage.substring(1, lastMessage.length()-2);
				System.out.println("IC ascii message  "+getASCII(message));
				
				String pattern = "[QEF]{0,3}";
				
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(message);
				isStatus = m.matches();
			}
			if (!isStatus) {
				System.out.println("not status message");
			}
			return isStatus;
	}


		private boolean isControlMessage(String lastMessage) {
			boolean isControl = false;
			if (hasGoodFormat(lastMessage) && hasGoodBBC(lastMessage)) {
				String message = lastMessage.substring(1, lastMessage.length()-2);
				System.out.println("IC ascii message  "+getASCII(message));
				
				String pattern = "([IASXJRa-z0-9])+G{1}$";
				
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(message);
				isControl = m.matches();
			}
			if (!isControl) {
				System.out.println("not control message");
			}
			return isControl;
		}
		private boolean hasGoodFormat(String fullMessage){
			boolean formatGood = false;
			if (fullMessage.length() >=3){
				System.out.println("HGF ascii fullMessage "+getASCII(fullMessage));
				String message = fullMessage.substring(0, fullMessage.length()-1);
				System.out.println("HGF ascii message " + getASCII(message));
				String pattern = "^\u0002{1}.{1,255}\u0003$";
				
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(message);
				formatGood = m.matches();
			}
			System.out.println("HGF formatGood: " + formatGood);
			return formatGood;
		}

		private  boolean hasGoodBBC(String fullMessage) {
			
			int BBCcount=-1;
			int BBCreceived=-2;
			if (fullMessage.length() >=3){				
				System.out.println("HGBBC ascii fullMessage "+getASCII(fullMessage));
				BBCreceived = fullMessage.charAt(fullMessage.length()-1);
				String message = fullMessage.substring(0, fullMessage.length()-1);
				
				BBCcount = message.charAt(1);
				for (int i = 2; i<message.length();i++){
						BBCcount = (BBCcount ^ message.charAt(i));	
						
				}
				
			}
			System.out.println("bbc received: " + BBCreceived + " and BBC counted: "+BBCcount);
			if (BBCcount == BBCreceived){
				return true;
			} else {
				return false;
			}

		}

				private String getASCII(String fullMessage) {
						String asciiMessage="";
						int ascii;
						for (int i = 0; i<fullMessage.length();i++){
							ascii = (int)fullMessage.charAt(i);
							asciiMessage+= ascii + " "; 
						}
					return asciiMessage;
					}

		
				public int getBaudRate() {
					// TODO Auto-generated method stub
					return 0;
				}

		
				public int getParity() {
					// TODO Auto-generated method stub
					return 0;
				}

			
				public int getBits() {
					// TODO Auto-generated method stub
					return 0;
				}

	
				public int getStopBit() {
					// TODO Auto-generated method stub
					return 0;
				}

	
				public TreeMap getEstablishmentMessage() {
					// TODO Auto-generated method stub
					return null;
				}


}