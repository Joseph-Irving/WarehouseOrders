import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class databaseConnector
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.50.15.13:8888/warehouse";
	static final String USER = "root";
	static final String PASS = "dingo";

	public static void main(String[] args)
	{



	}
	public static ArrayList<Product> createProducts() 
	{

		Connection conn = null;
		Statement stmt = null;
		ArrayList<Product> products = new ArrayList<Product>();
		try 
		{
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to database...");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
			String sql2 = "SELECT productID, ProductName, Location FROM products";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int productID = rs.getInt("productID");
				String productName = rs.getString("ProductName");
				String location = rs.getString("Location");
				Product product = new Product(productID, productName, location);
				products.add(product);
			}
			rs.close();

		} 
		catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		return products;
	} 

	public static ArrayList<OrderLine> createLines() 
	{

		Connection conn = null;
		Statement stmt = null;
		ArrayList<OrderLine> lines = new ArrayList<OrderLine>();
		try 
		{
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to database...");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
			String sql2 = "SELECT orderID, productID, Quantity FROM orderline";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int orderID = rs.getInt("orderID");
				int productID = rs.getInt("productID");
				int quantity = rs.getInt("Quantity");
				OrderLine line = new OrderLine(orderID, productID, quantity);
				lines.add(line);
			}
			rs.close();

		} 
		catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
		return lines;
	} 


	public static ArrayList<Order> collectOrders() {

		Connection conn = null;
		Statement stmt = null;

		try 
		{
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to database...");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Order> customerOrders = new ArrayList<Order>();


		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
			String sql2 = "SELECT orderID, cost, customerID, Address, Status, Processing FROM customerOrders";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int orderID = rs.getInt("orderID");
				float cost = rs.getFloat("cost");
				int customerID = rs.getInt("customerID");
				String address = rs.getString("Address");
				String status = rs.getString("Status");
				boolean processing = rs.getBoolean("Processing");
				Order order = new Order(cost, orderID, customerID, status, address, processing);
				customerOrders.add(order);
			}
			rs.close();
		} 
		catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Done");
		return customerOrders;
	} 

	public static ArrayList<StockOrder> collectStockOrders() {

		Connection conn = null;
		Statement stmt = null;

		try 
		{
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to database...");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<StockOrder> stockOrders = new ArrayList<StockOrder>();


		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
			String sql2 = "SELECT orderID, Status FROM stockorders";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int orderID = rs.getInt("orderID");
				String status = rs.getString("Status");
				StockOrder order = new StockOrder(orderID, status);
				stockOrders.add(order);
			}
			rs.close();
		} 
		catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Done");
		return stockOrders;
	} 

	public static void updateStatus(String newStatus, int orderID) //update the status in the database
	{
		Connection conn = null;
		Statement stmt = null;

		try 
		{
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to database...");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("Creating statement...");

		try{
			stmt = conn.createStatement();
			String sql3 = "UPDATE customerorders " + "SET Status = '"+newStatus+"' WHERE orderID = '"+orderID+"'";
			stmt.executeUpdate(sql3);
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye! I have updated the status");
	}
	
	public static void updateStockStatus(int orderID) //update the status in the database
	{
		Connection conn = null;
		Statement stmt = null;

		try 
		{
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to database...");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("Creating statement...");

		try{
			stmt = conn.createStatement();
			String sql3 = "UPDATE stockorders " + "SET Status = 'Delivered' WHERE orderID = '"+orderID+"'";
			stmt.executeUpdate(sql3);
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye! I have updated the status");
	}

	public static void updateProcess(int process, int orderID) //update the process in the database
	{
		Connection conn = null;
		Statement stmt = null;

		try 
		{
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to processdatabase...");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("Creating statement...");

		try{
			stmt = conn.createStatement();
			String sql3 = "UPDATE customerorders " + "SET Processing = '"+process+"' WHERE orderID = '"+orderID+"'";
			stmt.executeUpdate(sql3);
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye! I have updated processing");
	}

	public static void newStockOrder(int orderID) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName( "com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			
			stmt = conn.createStatement();
			String sql = "INSERT INTO stockorders " + "VALUES ('"+orderID+"', 'Placed')";
			stmt.executeUpdate(sql);
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Your Order Has been added!");
	}

	public static void newStockOrderLine(int orderID, int productID, int Quantity) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName( "com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			
			stmt = conn.createStatement();
			String sql = "INSERT INTO stockorderline " + "VALUES ('"+orderID+"', '"+productID+"', '"+Quantity+"')";
			stmt.executeUpdate(sql);
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Your Orderline Has been added!");
	} 




}









