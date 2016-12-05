package main.pl.kuzyyn.model.protocol;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import main.pl.kuzyyn.controller.communication.RSController;

public class Messaging implements Callable<String>{
	private String ack,nak;
	@Override
	public String call() throws Exception {
		String message;
		while(true){
			message=RSController.getInstance().getProtocol().lookForMessageAndRemove(ack, nak);
			if (message!=null){
				return message;
			}
			Thread.sleep(1);
		}
	}
	public String waitingForMessage(String ack,String nak, int timeout) {
		System.out.println("Jestem w waiting");
		this.ack=ack;
		this.nak=nak;
		System.out.println("ack: "+this.ack);
		String message=null;
		ExecutorService insidePool = Executors.newSingleThreadExecutor();
		Future<String> task = insidePool.submit(this);
		try {
			message= (String) task.get(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			ConsoleLogger.outErrorLog("Nie otrzymano oczekiwanej wiadomoœci w wyznaczonym czasie");
			task.cancel(true);
			message = "TIMEOUT";
			e.printStackTrace();
		} finally{
			insidePool.shutdown();
			System.out.println("Jestem po shutdown");
		}
		System.out.println("waitingformessage: wiadomosc: " + message + " Ascii: "+getASCII(message));
		return message;
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
	
	

}
