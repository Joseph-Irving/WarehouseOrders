
public class Product {
	public Product(int productID, String productName, String location) {
		this.productID = productID;
		this.productName = productName;
		this.location = location;
	}
	
	private int productID;
	private String productName;
	private String location;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
