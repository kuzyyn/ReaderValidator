package test.pl.kuzyyn.model.protocol;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import main.pl.kuzyyn.model.protocol.D_Expert96;

public class Expert96Test extends TestCase {
	private String message;
	private char ENQ = (char)5;
	private char EOT = (char)4;
	private char ACK = (char)6;
	public void setUp(){
		message = EOT + "20" + ENQ;
	}
	@Test
	public void testEstablishMessage() {
		D_Expert96 expert = new D_Expert96();
		TreeMap<String,String> messageMap = new TreeMap<String,String>();
		messageMap=expert.getEstablishmentMessage();
		assertTrue(messageMap.containsKey("MESSAGE"));
		assertTrue(messageMap.containsKey("ACK"));
		assertTrue(messageMap.containsKey("NAK"));
		assertTrue(messageMap.get("MESSAGE").equals(message));
	}

}
