import java.util.ArrayList;
import java.util.Scanner;
public class CreateNewStockOrder {

	public static int stockOrder()
	{
		ArrayList<StockOrder> stockOrders = new ArrayList<StockOrder>();
		stockOrders = databaseConnector.collectStockOrders();
		int newOrderID = 0;
		int lengthi = stockOrders.size();
		newOrderID = lengthi + 1;
		return newOrderID;
	}

	public static void orderLine(int orderID)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the product ID you want");
		int product = scanner.nextInt();
		System.out.println("Please enter the quantity");
		int quantity = scanner.nextInt();
		databaseConnector.newStockOrderLine(orderID, product, quantity);
		System.out.println("Do you have more products to add? Y or N");
		String decision = scanner.next();

		if(decision.equals("Y"))
		{
			orderLine(orderID);
		}
		else
		{
			System.out.println("Thank you for your order");
			scanner.close();
			GUI.main(null);

		}

	}

	public static String[] orderList()
	{
		ArrayList<StockOrder> customerOrders = new ArrayList<StockOrder>(); //gets the orders
		customerOrders = databaseConnector.collectStockOrders();	
		int length = customerOrders.size();
		String[] listData = new String[length];
		for (int i = 0; i < length; i++)// generates a list output of all the orders
		{
			String id = String.valueOf(customerOrders.get(i).getOrderID());

			listData[i] = ("Order ID: " + id + ", Order Status: " + customerOrders.get(i).getStatus());	
		}
		return listData;
	}

	public static void updateStatus(int input)
	{
		ArrayList<StockOrder> customerOrders = new ArrayList<StockOrder>(); //gets the orders
		customerOrders = databaseConnector.collectStockOrders();
		int i  = customerOrders.get(input).getOrderID();

		databaseConnector.updateStockStatus(i);

	}

	public static String[] productList()
	{
		ArrayList<Product> products = new ArrayList<Product>();
		products = databaseConnector.createProducts();
		int length = products.size();
		String[] list = new String[length];

		for(int i = 0; i<length; i++)
		{
			list[i] = (+ products.get(i).getProductID() + ", Name: " + products.get(i).getProductName());
		}

		return list;		
	}

	public static int findProductID(int input)
	{
		int id;
		ArrayList<Product> products = new ArrayList<Product>();
		products = databaseConnector.createProducts();
		id = products.get(input).getProductID();
		
		return id;	
	}

}
