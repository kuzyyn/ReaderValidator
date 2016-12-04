package main.pl.kuzyyn.model.protocol;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gnu.io.SerialPort;
import main.pl.kuzyyn.model.serial.Protocol;

public class D_Expert96 implements Protocol {

	private TreeMap<String,String> messagesMap;
	
	public D_Expert96(){
		messagesMap = new TreeMap<String,String>();
	}
	
	private static final int baudRate = 19200;
	private static final int parity = 2; //SerialPort.PARITY_EVEN;
	private static final int bits = 7; //SerialPort.DATABITS_7;
	private static final int stopBit = 2 ; // SerialPort.STOPBITS_2;
	
	public int getBaudRate(){
		return baudRate;
	}
	public int getParity(){
		return parity;
	}
	public int getBits(){
		return bits;
	}
	public int getStopBit(){
		return stopBit;
	}
	private final static char NUL = (char)0; // null message
	private final static char STX = (char)2; // Start of text
	private final static char ETX = (char)3; // end of text
	private final static char EOT = (char)4; // end of transmission
	private final static char ENQ = (char)5; // select reader
	private final static char ACK = (char)6; // positive acknowledge to the sender
	private final static char NAK = (char)21; // negative acknowledge to the sender

	
	@Override
	public TreeMap<String,String> getEstablishmentMessage() {
		clearMessagesMap();	
		StringBuilder message=new StringBuilder();
		message.append(EOT).append("20").append(ENQ);
		TreeMap<String,String> messageMap = new TreeMap<String, String>();
		messageMap.put("MESSAGE", message.toString());
		messageMap.put("ACK", "20"+String.valueOf(ACK));
		messageMap.put("NAK", String.valueOf(NAK));
		return messageMap;
	}
	
	private synchronized void clearMessagesMap(){
		messagesMap.clear();
	}
	@Override
	public String lookForMessageAndRemove(String messageType, String negativeAnswer){
		String message=null;
		if (messagesMap.containsKey(messageType)){
			message =messagesMap.get(messageType);
			messagesMap.remove(messageType);
		}
		if (messagesMap.containsKey(negativeAnswer)){
			message =messagesMap.get(negativeAnswer);
			messagesMap.remove(negativeAnswer);
		}
		return message;
	}
	
	private boolean waitingForBBC=false;
	private byte[] buffer = new byte[1024];
	private int tail = 0;
	

	@Override
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
				decodeMessage(getMessage(buffer,tail));	
				tail = 0;
			}			
		} 	
	}
	public String getMessage(byte[] buffer, int length){
		return new String(buffer,0,tail);
	}
public enum messageType {communicationEstablishment,acknowledge,negAcknowledge,broken,statusCommand,controlCommand};
	
	public void decodeMessage(String lastMessage) {
		
		String messageType = getMessageType(lastMessage);
		String previousMessage;
		synchronized (this){
			previousMessage = messagesMap.put(messageType, lastMessage);
		}
		
		if (previousMessage!=null){
			ConsoleLogger.outErrorLog("Nadpisano klucz "+messageType+", poprzednia wartoœæ: " + getASCII(previousMessage) +", nowa wartoœæ: " + getASCII(lastMessage));
		} 
		System.out.println("Odczytano: "+lastMessage + ", Ascii: "+getASCII(lastMessage)+ ", Typ: " +messageType );
		ConsoleLogger.inRegularLog("Odczytano: "+lastMessage + ", Ascii: "+getASCII(lastMessage)+ ", Typ: " +messageType );	
	}
	

	private String getMessageType(String lastMessage) {
		String message="BROKEN";
		if (lastMessage.equals(String.valueOf(ACK))){
			message = "ACK";
			System.out.println("message ACK");
		} else if (lastMessage.equals("20"+String.valueOf(ACK))){
			message ="ACK";
			System.out.println("message 20ACK");
		}  
		else if (lastMessage.equals(String.valueOf(NAK))){
			message ="NAK";
			System.out.println("message NAK");
		} else if (lastMessage.equals(String.valueOf(NUL))){
			message ="NUL";
			System.out.println("message NUL");
		} else if (isControlMessage(lastMessage)){
				message = "CONTROL";
				System.out.println("message Control");
		} else if (isStatusMessage(lastMessage)){
				message = "STATUS";
				System.out.println("message isstatus");
		} 
		else {
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
				@Override
				public SerialPort setSerialPortParams(SerialPort serialPort) {
					// TODO Auto-generated method stub
					return null;
				}
				@Override
				public boolean initialize() {
					// TODO Auto-generated method stub
					return false;
				}
				
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
