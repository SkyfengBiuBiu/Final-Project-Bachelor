package connectCommo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Commo {
	boolean flag=false;
	
	public  boolean changeDirection() throws Exception {
		
		HttpURLConnectionImp http = new HttpURLConnectionImp();
		
		//System.out.println("The homing command has been sent to COMMO controller");
		http.homingGet();
	    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	    Runnable runnable = new Runnable() {
	        public void run()
	        {
	        	
	    		try {
	    			//System.out.println("The stop command has been sent to COMMO controller");
	    			flag=http.stopGet();
					} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    };
	
	    // Run it in 8 hours - you would have to calculate how long to wait from "now"
	    service.schedule(runnable, (long) 14.79, TimeUnit.SECONDS); // You can 
	//34.56
	    //14.79
	    service.shutdown();
	    return flag;
		}

}