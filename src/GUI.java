import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI extends JFrame{

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public GUI(){prepareGUI();}

	public static void main(String[] args) {
		GUI sD = new GUI();
		sD.MainMenu();


	}
	private void prepareGUI() {
		mainFrame = new JFrame("Java SWING Examples");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent)     {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
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
		controlPanel.add(customerButton);
		controlPanel.add(stockButton);
		mainFrame.setVisible(true);}

	private class BCL implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent ae) {
			String command = ae.getActionCommand();
			switch (command) {
			case "Customer": mainFrame.setVisible(false);
			CustomerGUI.main(null);
			break;
			case "Stock": mainFrame.setVisible(false);
			CreateNewStockOrder.stockOrder();
			break;

			}}}



}
