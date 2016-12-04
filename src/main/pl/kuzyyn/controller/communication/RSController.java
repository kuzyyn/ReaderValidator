package main.pl.kuzyyn.controller.communication;

import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import main.pl.kuzyyn.controller.components.GUIObjectController;
import main.pl.kuzyyn.model.protocol.ConsoleLogger;
import main.pl.kuzyyn.model.protocol.D_Expert96;
import main.pl.kuzyyn.model.protocol.Messaging;
import main.pl.kuzyyn.model.serial.Protocol;
import main.pl.kuzyyn.model.serial.ReaderConnect;
import main.pl.kuzyyn.model.serial.Sender;

public class RSController  {
	
	private ReaderConnect rc;
	private static RSController rsController;
	private boolean readerBusy = false;
	private ExecutorService coordinatorPool;

	private RSController(){};
	private String ack,nak;
	private Messaging messagesTask;
	Protocol protocol;
	
	
	private boolean connectedToCom = false;
	private boolean connectionEstablished = false;
	
	public static RSController getInstance(){
		if (rsController==null){
			synchronized(RSController.class){
				if(rsController ==null){
					rsController = new RSController();
					rsController.initialize();
				}
			}
		}
		return rsController;
	}
	
	private void initialize() {
		ConsoleLogger.outRegularLog("Inicjalizacja RSControllera");
		coordinatorPool=Executors.newSingleThreadExecutor();
		messagesTask = new Messaging();
	}

	public void onMessage(byte b){
		if (isConnectedToCom()){
			protocol.onMessage(b);
		}
	}
	
	public void establishConnectionWithReader(){
		if (!isBusyReader()){	
			coordinatorPool.submit(new Runnable(){
				@Override
				public void run() {
					String result = null;
					String message,ack,nak;
					
					TreeMap messageMap = protocol.getEstablishmentMessage();
					if(messageMap.containsKey("MESSAGE") && messageMap.containsKey("ACK") && messageMap.containsKey("NAK")){
						message = (String)messageMap.get("MESSAGE");
						ack = (String)messageMap.get("ACK");
						nak = (String)messageMap.get("NAK");
						System.out.println("nak: "+nak);
						System.out.println("message " +message);
						System.out.println(messagesTask);
						Sender.send(message);	
						System.out.println(RSController.getInstance().messagesTask);
						result=messagesTask.waitingForMessage("ACK","NAK",1000);	
						System.out.println("result "+result +" mialo byc ack: "+ack);
						if (result.equals(ack)){ // musi byæ 20ACK ?
							setConnectionEstablished(true);
						} else if (result == nak){
							ConsoleLogger.outErrorLog("Nie uda³o siê nawi¹zaæ komunikacji, otrzymano odpowiedz negatywna");	
						}
						
						else {
							ConsoleLogger.outErrorLog("Nie uda³o siê nawi¹zaæ komunikacji, otrzymano odpowiedz: " + result + " ASCII: "+getASCII(result));					
						}
								
					} else {
						ConsoleLogger.outErrorLog("Brak danych do ustanowienia po³¹czenia");
					}
				}	
			});			
		}else {
			ConsoleLogger.outRegularLog("Czytnik zajêty, nie mogê ustanowiæ po³¹czenia");
		} 	
	}
	

	
	
	
	private synchronized void setConnectionEstablished(boolean b){
		connectionEstablished=b;
		ConsoleLogger.outRegularLog("Nawi¹zano po³¹czenie z czytnikiem");
	}
	private synchronized void reserveReader(boolean b) {
		if (isConnectedToCom()){
		readerBusy = b;
		}
	}
	private synchronized boolean isBusyReader(){
		return readerBusy;
	}
	public boolean isConnectedToCom(){
		return connectedToCom;
	}
	public void setConnectedToCom(boolean isConnected){
			connectedToCom = isConnected ;	
	}

	public int connect(String comPort, String readerType) {
		int result=-1;
		if (rc!=null){
			rc.disconnect();
		}
		protocol = null;
		if (readerType.equals("Expert96")){
			ConsoleLogger.outRegularLog("Znaleziono sterownik: "+readerType);
			protocol = new D_Expert96();
		}
		if (protocol != null){
			try {
			rc = new ReaderConnect(protocol);
			rc.connect(comPort);

			result = 1;
			}catch(NullPointerException e){
				ConsoleLogger.outErrorLog("B³¹d podczas tworzenia po³¹czenia");
				rc = null;
			}catch(Exception e){
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(GUIObjectController.getInstance().getMainFrame().getJMainFrame(),
				    "Nie ma takiego sterownika");
		}
		return result;
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
	
	public void onShutdown(){
		coordinatorPool.shutdown();
	}
	public ExecutorService getCoordinatorPool() {
		return coordinatorPool;
	}

	public Protocol getProtocol() {
		return protocol;
	}



}
