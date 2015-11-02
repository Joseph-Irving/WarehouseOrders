import java.util.ArrayList;


public class Salesman {

	private int oa = 8;
	private int ob = 6;
	private int oc = 5;
	private int oe = 9;
	private int ab = 7;
	private int ac = 10;
	private int ae = 15;
	private int bc = 4;
	private int be = 9;
	private int ce = 6;

	public static void getRoute(int orderID)
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = databaseConnector.collectOrders();
		int length = orders.size();
		ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
		orderLines = databaseConnector.createLines();
		ArrayList<Product> products = new ArrayList<Product>();
		products = databaseConnector.createProducts();
		int lengthj = orderLines.size();
		int lengthk = products.size();
		ArrayList<String> locations = new ArrayList<String>();
		for(int i =0; i<length; i++)
		
		{
			if(orders.get(i).getOrderId() == orderID)
			{
				for(int j = 0; j<lengthj; j++)
				{
					if(orders.get(i).getOrderId() == orderLines.get(j).getOrderID())
					{
						for(int k=0; k<lengthk; k++)
						{
							if(orderLines.get(j).getProductID() == products.get(k).getProductID())
							{
								locations.add(products.get(k).getLocation());
							}
						}
					}
				}
			}
		}
		
		for(int i=0; i<locations.size(); i++)
		{
			
		}
	}

}
