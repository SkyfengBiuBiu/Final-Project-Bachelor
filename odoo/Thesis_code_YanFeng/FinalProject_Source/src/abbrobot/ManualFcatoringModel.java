package abbrobot;

public class ManualFcatoringModel {
	int orderId=0;
	int productId=0;
	int quatity=0;
	private String ipId;
	
	public ManualFcatoringModel(int orderId, int productId, int quatity,String ipId) {
		this.orderId = orderId;
		this.productId = productId;
		this.quatity = quatity;
		this.ipId = ipId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	
	public String getIpId() {
		return ipId;
	}

	public void setIpId(String ipId) {
		this.ipId = ipId;
	}


}
