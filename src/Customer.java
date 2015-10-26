
public class Customer {

	

	private String addressline2;
	private String addressline3;
	private String postcode;
	private String phoneNumber;
	private String customerID;
	private String firstName;
	private String lastName;
	private String addressline1;
	public String getAddressline1() {
		return addressline1;
	}
	
	public Customer(String firstName, String lastName, String addressline1,
			String addressline2, String addressline3, String postcode,
			String phoneNumber, String customerID) 
	{

		this.firstName = firstName;
		this.lastName = lastName;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.addressline3 = addressline3;
		this.postcode = postcode;
		this.phoneNumber = phoneNumber;
		this.customerID = customerID;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getAddressline3() {
		return addressline3;
	}

	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Customer()
	{
		firstName="";
		lastName="";
		phoneNumber = "";
		customerID ="";
	}
	
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	
	
}
