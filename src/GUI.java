import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI extends JFrame{

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private JPanel stockPanel;
	private JPanel custPanel;
	private JList newlist;
	private JTextArea textarea;
	private String newline = "\n";
	private ArrayList<String> moreList;
	private JComboBox status;
	private String[] options = {"Placed", "Picked", "Packed", "GDZ", "Delivered"};
	private JComboBox products;
	private JTextField quantity;
	private int newOrderID;

	public GUI(){prepareGUI();}

	public static void main(String[] args) {
		GUI sD = new GUI();
		sD.MainMenu();


	}
	private void prepareGUI() {
		mainFrame = new JFrame("Warehouse Order Tracking System");
		mainFrame.setSize(900, 900);
		mainFrame.setLayout(new GridLayout(3,1));
		headerLabel = new JLabel("", JLabel.CENTER);
		headerLabel.setFont(new Font("Open Sans", Font.PLAIN, 35));
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent)     {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		
		custPanel = new JPanel();
		custPanel.setLayout(new GridLayout(2,4));
		stockPanel = new JPanel();
		stockPanel.setLayout(new GridLayout(2,4));
		mainFrame.setVisible(true);

	}

	private void MainMenu() {
		
		headerLabel.setText("Warehouse Order Management System");
		JButton customerButton = new JButton("Customer Orders");
		JButton stockButton = new JButton("Stock Orders");
		customerButton.setActionCommand("Customer");
		stockButton.setActionCommand("Stock");
		customerButton.addActionListener(new BCL());
		stockButton.addActionListener(new BCL());
		customerButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		stockButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		controlPanel.add(customerButton);
		controlPanel.add(stockButton);
		mainFrame.setVisible(true);}

	private class BCL implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent ae) {
			String command = ae.getActionCommand();
			switch (command) {
			case "Customer": customer();
			
			break;
			case "Stock": stockOrders();
			break;
			case "CheckOut": OMS.custProcc(1, newlist.getSelectedIndex());
			mainFrame.remove(textarea);
			customer();
			break;
			case "CheckIn": OMS.custProcc(0, newlist.getSelectedIndex());
			mainFrame.remove(textarea);
			customer();
			break;
			case "Confirm": String stat = (String)status.getSelectedItem();
			OMS.custUpdate(stat, newlist.getSelectedIndex());
			mainFrame.remove(textarea);
			customer();
			break;
			case "Home": mainFrame.setVisible(false);
			main(null);
			break;
			case "Confirm Delivery": CreateNewStockOrder.updateStatus(newlist.getSelectedIndex());
			mainFrame.remove(newlist);
			stockOrders();
			break;
			case "Order": databaseConnector.newStockOrder(newOrderID);
			mainFrame.remove(newlist);
			stockOrders();
			break;
			case "Order Stock": String z = quantity.getText();
				int quantities =  Integer.parseInt(z);
			 int productID = CreateNewStockOrder.findProductID(products.getSelectedIndex()) ;
				databaseConnector.newStockOrderLine(newOrderID, productID, quantities);
				mainFrame.remove(newlist);
				stockOrders();
			

			}}}

	private void stockOrders()
	{
		stockPanel.removeAll();
		mainFrame.remove(controlPanel);
		mainFrame.remove(custPanel);
		mainFrame.add(stockPanel);
		headerLabel.setText("Stock Orders");
		newOrderID = CreateNewStockOrder.stockOrder();
		String[] listData = CreateNewStockOrder.orderList();
		newlist = new JList(listData);
		mainFrame.add(newlist);
		
		quantity = new JTextField(10);
		quantity.setText("quantity");
		
		
		JButton orderButton = new JButton("Create New Stock Order");
		orderButton.setActionCommand("Order");
		orderButton.addActionListener(new BCL());
		orderButton.setFont(new Font("Open Sans", Font.PLAIN, 18));
		stockPanel.add(orderButton);
		products = new JComboBox(CreateNewStockOrder.productList());
		products.setSelectedIndex(0);
		
		JButton orderStockButton = new JButton("Order Stock");
		orderStockButton.setActionCommand("Order Stock");
		orderStockButton.addActionListener(new BCL());
		orderStockButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		
		
		JButton deliveredButton = new JButton("Confirm Delivery");
		deliveredButton.setActionCommand("Confirm Delivery");
		deliveredButton.addActionListener(new BCL());
		deliveredButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		stockPanel.add(deliveredButton);
		
		JButton homeButton = new JButton("Home");
		homeButton.setActionCommand("Home");
		homeButton.addActionListener(new BCL());
		homeButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		stockPanel.add(homeButton);
		stockPanel.add(products);
		stockPanel.add(quantity);
		stockPanel.add(orderStockButton);
		mainFrame.setVisible(true);
		
		

	}
	
	private void customer() {
		
		custPanel.removeAll();
		
		mainFrame.remove(controlPanel);
		mainFrame.remove(stockPanel);
		
		mainFrame.add(custPanel);
		
		headerLabel.setText("Customer Orders");

		
		status = new JComboBox(options);
		status.setSelectedIndex(0);
		


		String[] listData = OMS.customerOrders();
		newlist = new JList(listData);
		newlist.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(newlist);
		
		custPanel.add(listScroller);
		custPanel.add(status);
		JButton submitButton = new JButton("CheckOut");
		submitButton.setActionCommand("CheckOut");
		submitButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		JButton checkINButton = new JButton("CheckIn");
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new BCL());
		confirmButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		submitButton.addActionListener(new BCL());
		checkINButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		checkINButton.addActionListener(new BCL());
		custPanel.add(confirmButton);
		custPanel.add(submitButton);
		custPanel.add(checkINButton);
		
		JButton homeButton = new JButton("Home");
		homeButton.setActionCommand("Home");
		homeButton.addActionListener(new BCL());
		homeButton.setFont(new Font("Open Sans", Font.PLAIN, 22));
		custPanel.add(homeButton);
		

		textarea = new JTextArea(5, 20);
		mainFrame.add(textarea);
		textarea.setEditable(false);
		newlist.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					moreList= OMS.showMore(newlist.getSelectedIndex());
					printMore();
				}
			}
		});
		mainFrame.setVisible(true);
	}

	private void printMore()
	{
		textarea.setText(null);
		int length = moreList.size();
		for(int i=0; i<length; i++)
		{
			textarea.append(moreList.get(i) + newline);
		}
	}
}
