package main.pl.kuzyyn.model.serial;

import java.io.IOException;
import java.io.OutputStream;

import main.pl.kuzyyn.model.protocol.ConsoleLogger;

public class Sender  implements Runnable {
	static OutputStream out;
	Protocol protocol;
	Thread tSend;

	boolean waitFlag;
	boolean endThread;
	String lastMessage;
	
	
	public Sender(OutputStream out, Protocol protocol) {
		Sender.out = out;
		this.protocol = protocol;
		tSend=new Thread(this,"sender");
		waitFlag = true;
		endThread = false;
		tSend.start();	
	}
	private static void send(byte[] bytes) throws IOException{
		try {
			System.out.println("Reader Validator: sending: "+ new String (bytes,0,bytes.length));	
			out.write(bytes);
		} catch(IOException e){
			ConsoleLogger.outErrorLog("B³¹d wysy³ania danych. Sprawdz poprawnosc podlaczenia portu COM");
		}
	}
	private void sendThroughThread(byte[] bytes) throws IOException{
		try {
			System.out.println("Reader Validator: sending: "+ new String (bytes,0,bytes.length));
			
			out.write(bytes);
		//	out.flush();
		} catch(IOException e){
			ConsoleLogger.outErrorLog("B³¹d wysy³ania danych");
			e.printStackTrace();
		}
	}
	public void sendThroughThread(String message){
		ConsoleLogger.outRegularLog(message);
		wakeSender(message);
	}

	public static void send(String message) {
		ConsoleLogger.outRegularLog(message);
		try {
			send((message).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ConsoleLogger.outErrorLog("Error while sending");
		}	
	}
	
	@Override
	public void run() {
		try{
			while (endThread == false){

			synchronized (this) {
				while (waitFlag) {
					wait();
					}
				}
			if (endThread == true) break;
			sendThroughThread((lastMessage).getBytes());
			sleepSender();
			}
		}catch(InterruptedException e){
			ConsoleLogger.outErrorLog("Error w w¹tku tSend.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ConsoleLogger.outRegularLog("Wyjœcie z w¹tku tSend");
		
	}
	synchronized void sleepSender(){
		waitFlag = true;
	}
	synchronized void wakeSender(String message){
		lastMessage = message;
		waitFlag = false;
		notify();
	}
	public synchronized void endSendThread(){
		endThread = true;
		waitFlag = false;
		notify();
		
	}
	public Thread gettSend() {
		return tSend;
	}

}