import java.awt.FlowLayout;
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
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class CustomerGUI extends JFrame{

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private int selected;
	private JList newlist;
	private JTextArea textarea;
	private String newline = "\n";
	private ArrayList<String> moreList;
	public CustomerGUI(){prepareGUI();}

	public static void main(String[] args) 
	{
		CustomerGUI sD = new CustomerGUI();
		sD.customer();
	}
	private void prepareGUI() {
		mainFrame = new JFrame("Customer Orders");
		mainFrame.setSize(800, 800);
		mainFrame.setLayout(new GridLayout(3, 1));
		headerLabel = new JLabel("", JLabel.CENTER);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent)     {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	private void customer() {
		headerLabel.setText("Customer Orders");
		
		String[] options = {"Placed", "Picked", "Packed", "GDZ", "Delivered"};
		JComboBox status = new JComboBox(options);
		status.setSelectedIndex(4);
		status.addActionListener(new cmb());
		controlPanel.add(status);
		
		
		String[] listData = OMS.customerOrders();
		newlist = new JList(listData);
		controlPanel.add(newlist);
		
		JButton submitButton = new JButton("CheckOut");
		submitButton.setActionCommand("CheckOut");
		JButton checkINButton = new JButton("CheckIn");
		submitButton.addActionListener(new BCL());
		checkINButton.addActionListener(new BCL());
		controlPanel.add(submitButton);
		controlPanel.add(checkINButton);
		mainFrame.setVisible(true);
		
		textarea = new JTextArea(5, 20);
		controlPanel.add(textarea);
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
	
	


	private class BCL implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent ae) {

			String command = ae.getActionCommand();
			switch (command) {
			case "CheckOut": OMS.custProcc(1, newlist.getSelectedIndex());

			break;
			case "CheckIn": OMS.custProcc(0, newlist.getSelectedIndex());
			
			break;

			}
			
			}}
	
	private class cmb implements ActionListener {
		public void actionPerformed (ActionEvent a)
		{
			JComboBox cb = (JComboBox)a.getSource();
	        String stat = (String)cb.getSelectedItem();
	        OMS.custUpdate(stat, newlist.getSelectedIndex());
		}
	}
        
        
    
}
