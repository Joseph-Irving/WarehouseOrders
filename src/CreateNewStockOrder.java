import java.util.ArrayList;
import java.util.Scanner;
public class CreateNewStockOrder {

	public static void stockOrder()
	{
		ArrayList<StockOrder> stockOrders = new ArrayList<StockOrder>();
		stockOrders = databaseConnector.collectStockOrders();
		int newOrderID = 0;
		int lengthi = stockOrders.size();
		newOrderID = lengthi + 1;
		databaseConnector.newStockOrder(newOrderID);
		orderLine(newOrderID);
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

}
