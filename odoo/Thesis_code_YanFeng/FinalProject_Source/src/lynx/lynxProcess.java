package lynx;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.telnet.TelnetClient;

public class lynxProcess {
	
	//initialize a TelnetClient object
	TelnetClient telnetClient = new TelnetClient();
	//define the variables of writing and reading 
	InputStream inputStream=null;
	PrintStream pStream=null;
	
	MoreActions actions=null;
	boolean flag1=false;
	boolean flag2=false;
	
	public void init() throws SocketException, IOException {
		//set the length of time out for client
		telnetClient.setDefaultTimeout(50000);
		//set the address and port
		telnetClient.connect("192.168.200.210", 7171);
		//initialize variables
		inputStream = telnetClient.getInputStream();
		pStream = new PrintStream(telnetClient.getOutputStream());
		actions = new MoreActions();
	}
	
	
	public boolean enrollBox() {
		actions.preloginIn(inputStream);
		String password = "admin";
		actions.loginIn(inputStream, pStream, password);
		System.out.println("The adept lynx robot is controlling to pick up!");
		System.out.println(actions.gotoGoal(inputStream, pStream));
		return flag1=true;
	}
	
	public boolean exitBox() {
		System.out.println("The adept lynx robot is controlling to leave!");
		System.out.println(actions.moveBackward(inputStream, pStream));
		return flag2=true;
	}
	
	public void disconnect() throws IOException {
		if (null != pStream) {
			pStream.close();
		}
		telnetClient.disconnect();
	}
	
}
