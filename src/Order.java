
public class Order {

	public Order(float cost, int orderId, int customerID, String orderStatus,
			String address, boolean processing) 
	{
		this.cost = cost;
		this.orderId = orderId;
		this.customerID = customerID;
		this.orderStatus = orderStatus;
		this.Address = address;
		this.processing = processing;
	}

	
	private String orderStatus;
	private String Address;
	private float cost;
	private int orderId;
	private int customerID;
	private boolean processing;
	
	public boolean isProcessing() {
		return processing;
	}


	public void setProcessing(boolean processing) {
		this.processing = processing;
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}

	
	
	
	
	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	
		
	
	public float getCost() 
	{
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	
	
}
