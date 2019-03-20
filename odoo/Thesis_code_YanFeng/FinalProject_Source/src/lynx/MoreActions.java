package lynx;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class MoreActions {

	public String preloginIn(InputStream inputStream) {

		byte[] b = new byte[1024];
		int size;
		StringBuffer sBuffer = new StringBuffer(300);
		while (true) { 
			try {
			size = inputStream.read(b);
	
				if (-1 != size) {
					sBuffer.append(new String(b, 0, size));
					if (sBuffer.toString().trim().endsWith("Enter password:")) {
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sBuffer.toString();

	}

	public String loginIn(InputStream inputStream, PrintStream pStream, String password) {
		pStream.println(password); // write the command
		pStream.flush(); // send the command to telnet Server

		byte[] b = new byte[1024];
		int size;
		StringBuffer sBuffer = new StringBuffer(300);
		while (true) { // read the data from the server until read the the special one 
			try {
				size = inputStream.read(b);

				if (-1 != size) {
					sBuffer.append(new String(b, 0, size));
					if (sBuffer.toString().trim().endsWith("End of commands")) {
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sBuffer.toString();

	}
	
	public String gotoGoal(InputStream inputStream, PrintStream pStream)  {
		
		pStream.println("patrolOnce PickRoute"); // write the command
		pStream.flush(); // push the command to Telnet Server

		byte[] b = new byte[1024];
		int size;
		StringBuffer sBuffer = new StringBuffer(300);
		while (true) { // read the data from the server until read the the special one 
			try {
				size = inputStream.read(b);
				if (-1 != size) {
					sBuffer.append(new String(b, 0, size));
					if (sBuffer.toString().trim().endsWith("Finished patrolling route PickRoute")) {
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sBuffer.toString();

	}
	
	public String moveBackward(InputStream inputStream, PrintStream pStream) {
		pStream.println("patrolOnce LeaveRoute"); // write the command
		pStream.flush(); // push the command to Telnet Server

		byte[] b = new byte[1024];
		int size;
		StringBuffer sBuffer = new StringBuffer(300);
		while (true) { // read the data from the server until read the the special one 
			try {
				size = inputStream.read(b);

				if (-1 != size) {
					sBuffer.append(new String(b, 0, size));
					if (sBuffer.toString().trim().endsWith("Finished patrolling route LeaveRoute")) {
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sBuffer.toString();

	}
}
