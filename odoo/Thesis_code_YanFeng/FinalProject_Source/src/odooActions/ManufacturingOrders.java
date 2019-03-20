package odooActions;

public class ManufacturingOrders {

	String referernce;
	String deadline_start;
	String product;
	double quantity;
	String material_availity;
	String state;
	double finished_quantity;
	double amount;
	int id;
	
	@Override
	public String toString() {
		return "ManufacturingOrders [referernce=" + referernce + ", deadline_start=" + deadline_start + ", product="
				+ product + ", quantity=" + quantity + ", material_availity=" + material_availity + ", state=" + state
				+ ", finished_quantity=" + finished_quantity + ", amount=" + amount + ", id=" + id + "]";
	}

	public ManufacturingOrders() {
		this.amount = 0;
	}


	
	public double getFinished_quantity() {
		return finished_quantity;
	}

	

	public void setFinished_quantity(double finished_quantity) {
		this.finished_quantity = finished_quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double quantity,double finished_quantity) {
		this.amount = quantity-finished_quantity;
	}
	public String getReferernce() {
		return referernce;
	}
	public void setReferernce(String referernce) {
		this.referernce = referernce;
	}
	public String getDeadline_start() {
		return deadline_start;
	}
	public void setDeadline_start(String deadline_start) {
		this.deadline_start = deadline_start;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getMaterial_availity() {
		return material_availity;
	}
	public void setMaterial_availity(String material_availity) {
		this.material_availity = material_availity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
