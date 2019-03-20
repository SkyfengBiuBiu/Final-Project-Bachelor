package odooActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public class OrderProcess {

	ArrayList<ManufacturingOrders> toDoList;
	ManufacturingOrders order ;
	String state = "";
	int index=0;
	Integer max_Id=0;
	
	
	public Integer getMax_Id() {
		return max_Id;
	}


	public void setMax_Id(Integer max_Id) {
		this.max_Id = max_Id;
	}


	public ArrayList<ManufacturingOrders> getToDoList(ArrayList<ManufacturingOrders> rawList) {

		toDoList = new ArrayList<ManufacturingOrders>();
		order = new ManufacturingOrders();
		for (int i = 0; i < rawList.size(); i++) {
			order = rawList.get(i);
			state = order.getState();
			if (state.equals("confirmed") || state.equals("progress")) {
				toDoList.add(order);
			}
		}

		return toDoList;
	}
	
	
	public ManufacturingOrders getNextOrder() {
		
		if(index<toDoList.size()){
			return order=this.toDoList.get(index++);
		}else {
			return null;
		}
	
	}
	
	public boolean checkOrders(Object[] cid) {
		  Integer[] in_cid = (Integer[]) cid;
		  Arrays.sort(in_cid);
		  
		return false;
		  
	}
	

	
	
}
