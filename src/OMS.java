import java.util.ArrayList;

public class OMS {

	public static ArrayList<String> showMore(int input)
	{
		ArrayList<Order> customerOrders = new ArrayList<Order>(); //gets the orders
		customerOrders = databaseConnector.collectOrders();
		ArrayList<Product> products = new ArrayList<Product>();
		products = databaseConnector.createProducts();
		ArrayList<OrderLine> lines = new ArrayList<OrderLine>();
		lines = databaseConnector.createLines();
		ArrayList<String> list = new ArrayList<String>();
		int length = customerOrders.size();
		int lengthj = lines.size();
		int lengthk = products.size();


		for (int i = 0; i < length; i++) //finds the order that has been entered and prints more info out
		{

			if(i == input)
			{
				list.add("Order ID: " + (customerOrders.get(i).getOrderId()) + ", Order Status: " + (customerOrders.get(i).getOrderStatus()) + 
						", Address: " + (customerOrders.get(i).getAddress()) + ", cost: " + (customerOrders.get(i).getCost())+", CustomerID: " + (customerOrders.get(i).getCustomerID()) );
				for (int j=0; j<lengthj; j++)
				{
					if(lines.get(j).getOrderID() == customerOrders.get(i).getOrderId())
					{
						for(int k = 0; k<lengthk; k++)
						{
							if(products.get(k).getProductID() == lines.get(j).getProductID())
							{
								list.add("Product Name: "+ products.get(k).getProductName() + ", Quantity: " + lines.get(j).getQuantity() + ", Location: "  + products.get(k).getLocation());
							}

						}
					}

				}
			}

		}
		return list;
	}

	public static void custUpdate(String status, int input)
	{

		ArrayList<Order> customerOrders = new ArrayList<Order>(); //gets the orders
		customerOrders = databaseConnector.collectOrders();	
		int length = customerOrders.size();
		for(int i = 0; i<=length; i++)
		{
			if(i  == customerOrders.get(input).getOrderId())
			{
				databaseConnector.updateStatus(status, i);
			}
		}
	}

	public static void custProcc(int decision, int input)
	{

		ArrayList<Order> customerOrders = new ArrayList<Order>(); //gets the orders
		customerOrders = databaseConnector.collectOrders();	
		int length = customerOrders.size();
		for(int i = 0; i<=length; i++)
		{
			if(i  == customerOrders.get(input).getOrderId())
			{
				databaseConnector.updateProcess(decision, i);
			}
		}


	}

	public static String[] customerOrders()
	{
		ArrayList<Order> customerOrders = new ArrayList<Order>(); //gets the orders
		customerOrders = databaseConnector.collectOrders();	
		int length = customerOrders.size();
		String[] listData = new String[length];
		for (int i = 0; i < length; i++)// generates a list output of all the orders
		{
			String id = String.valueOf(customerOrders.get(i).getOrderId());

			listData[i] = ("Order ID: " + id + ", Order Status: " + customerOrders.get(i).getOrderStatus() + ", Processing: " + customerOrders.get(i).isProcessing() );	
		}
		return listData;
	}

}
