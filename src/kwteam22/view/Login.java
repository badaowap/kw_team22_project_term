package kwteam22.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kwteam22.model.Admin;
import kwteam22.model.Customer;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel user_label, phone_label, message;
	JTextField userName_text;
	JTextField phone_text;
	JButton submit, cancel;
	MenuView bill;
	Query query;
	List<Customer> customers;
	Conn<Customer> connCus;
	List<Admin> admins;
	Conn<Admin> connAdmin;
	

	public Login(JFrame jFrame, boolean modal) {
		this.setLocationRelativeTo(null);
		this.setTitle("Login");
		bill = (MenuView) jFrame;
		addControl();
		loadCustomerData();
		loadAdminData();
		addEvent();
	}

	
	private void loadCustomerData() {
		connCus = new ConnToDB<Customer>();
		customers = connCus.query(query, "from Customer");
	}
	
	private void loadAdminData() {
		connAdmin = new ConnToDB<Admin>();
		admins = connAdmin.query(query, "from Admin");
	}
	
	
	private void addControl() {

		// User Label
		user_label = new JLabel();
		user_label.setText("Name :");
		userName_text = new JTextField();

		// Password

		phone_label = new JLabel();
		phone_label.setText("Phone Number :");
		phone_text = new JTextField();

		// Submit

		submit = new JButton("SUBMIT");
		submit.setBackground(Color.LIGHT_GRAY);
		submit.setForeground(Color.BLACK);

		panel = new JPanel(new GridLayout(3, 1));

		panel.add(user_label);
		panel.add(userName_text);
		panel.add(phone_label);
		panel.add(phone_text);

		message = new JLabel();
		panel.add(message);
		panel.add(submit);

		getContentPane().add(panel, BorderLayout.CENTER);
		setTitle("Please Login Here !");
		setSize(295, 118);
		setVisible(true);

	}

	private void addEvent() {
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventSubmit(e);
			}
		});
	}


	protected void btnEventSubmit(ActionEvent e) {
		String userName = userName_text.getText();
		String phone = phone_text.getText();
		boolean login = false;
		for(Customer cus : customers) {
			if (userName.trim().equalsIgnoreCase(cus.getName()) && phone.trim().equalsIgnoreCase(cus.getPhone())){
				message.setText(" Hi, " + userName + "");
				login = true;
				MenuView menuView = new MenuView(login, cus);
				menuView.setVisible(true);
				menuView.setLocationRelativeTo(null);
				this.dispose();
				
			} else {
				message.setText(" Invalid user.. ");
			}
		}

	}
}
