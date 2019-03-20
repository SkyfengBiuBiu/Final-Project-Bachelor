//This is client.Client.java file.

package client;

import abbrobot.AbbRobotService;
import abbrobot.ManualFcatoringModel;
import connectCommo.Commo;
import lynx.lynxProcess;
import odooActions.ManufacturingOrders;
import odooActions.OdooConnector;
import odooActions.OrderProcess;

public class clientMainClass {
	
	
	static int orderId=0;
	static ManufacturingOrders productOrder=null;
	static boolean flag=false;
	//get next order from odoo
	static OdooConnector ma = new OdooConnector();
	static OrderProcess proOrder=new OrderProcess();
	static lynxProcess ly= new lynxProcess();
	static Commo actions=new Commo();
	static boolean flag1=false;
	static boolean flag2=false;
	static boolean flag3=false;
	static boolean flag4=false;
	
		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
			orderId++;
			proOrder.getToDoList(ma.getDataFromManufactoring());
			productOrder=proOrder.getNextOrder();
			System.out.println("The manufactoring order is being excuted:");
			System.out.println(productOrder.toString());
			run(productOrder);
			ma.updateToDone(productOrder.getId());
		}
		
		public static void run(ManufacturingOrders productOrder) throws Exception {
			AbbRobotService  as=new AbbRobotService();
			as.checkConn("2");
			ManualFcatoringModel testModel=new ManualFcatoringModel(orderId,productOrder.getId(),(int) productOrder.getAmount(),"2");
			System.out.println(as.sendCoord(testModel));
			System.out.println(as.checkCoords(testModel));
			System.out.println(as.getCoord("2"));
			//System.out.println(as.getamountRead("2"));
			while(!flag) {
				Thread.sleep(1000);
				if(as.getamountRead("2").equalsIgnoreCase("{\"amountRead\":\"1}")) {
					System.out.println("ABB has finished the task successfully");
					flag=true;
				}
			}
			try        
			{
			    Thread.sleep(15000);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
			System.out.println("--------------------------------------------------------");
			ly.init();
			while(!flag1) {
				flag1=ly.enrollBox();
			}
			System.out.println("--------------------------------------------------------");
			
			
			System.out.println("Control electric cylinder to move up!");
			while(!flag2) {
				flag2=actions.changeDirection();
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("Control electric cylinder to move down!");
			while(!flag3) {
				flag3=actions.changeDirection();
			}
		
			
			System.out.println("--------------------------------------------------------");
			
			while(!flag4) {
				flag4=ly.exitBox();
			}
		
			ly.disconnect();	
			System.out.println("--------------------------------------------------------");}
		}
		
		
		

