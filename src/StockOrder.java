
public class StockOrder {

	public StockOrder(int orderID, String status) 
	{
		this.orderID = orderID;
		Status = status;
	}
	private int orderID;
	private String Status;
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
