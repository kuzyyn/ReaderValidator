package test.pl.kuzyyn.controller.communication;

import static org.junit.Assert.assertTrue;

import java.util.TreeMap;

import org.junit.BeforeClass;
import org.junit.Test;

import main.pl.kuzyyn.controller.communication.RSController;
import main.pl.kuzyyn.controller.components.GUIObjectController;

public class RSControllerTest {
	@BeforeClass
	public static void initialize(){
		GUIObjectController.getInstance().initializeMainFrame();
	}
	
	@Test
	public void shouldGetSingletonInstance(){
		RSController rs1,rs2;
		rs1= RSController.getInstance();
		rs2=RSController.getInstance();
		
		assertTrue(rs1==rs2);
	}
	@Test 
	public void shouldPoolBeNotNullAfterInitialization(){
		RSController rs1;
		rs1=RSController.getInstance();
		assertTrue(rs1.getCoordinatorPool()!=null);
		
	}
	@Test
	public void shouldConnectToExpert96(){
		int result = RSController.getInstance().connect("COM3","Expert96");
		assertTrue(result==1);
	}
	@Test
	public void shouldNotConnectToRandom(){
		int result = RSController.getInstance().connect("COM3","SomeRandomValue");
		assertTrue(result==-1);
	}
	@Test
	public void shouldEstablishConnection(){
		RSController.getInstance().connect("COM3","Expert96");
		TreeMap messageMap = RSController.getInstance().getProtocol().getEstablishmentMessage();
		assertTrue(messageMap.containsKey("MESSAGE"));
		assertTrue(messageMap.containsKey("ACK"));
		assertTrue(messageMap.containsKey("NAK"));

		
		
		
	}
}
