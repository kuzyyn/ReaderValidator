package main.pl.kuzyyn.model.serial;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import main.pl.kuzyyn.controller.communication.RSController;

public class Receiver implements SerialPortEventListener {
InputStream in;
private String lastMessage="";
	public Receiver(InputStream in) {
		this.in = in;

	}

	public void onReceive(byte b) {
		RSController.getInstance().onMessage(b);
}

	@Override
	public void serialEvent(SerialPortEvent se) {
		int data;
		try{
			data=in.read();
				onReceive((byte)data);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
