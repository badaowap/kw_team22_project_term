package kwteam22.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.Query;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import kwteam22.model.Admin;
import kwteam22.model.Customer;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel phone_label, message;
	JTextField phone_text;
	JButton submit, btnBack;
	MenuView menuView;
	Query query;
	List<Customer> customers;
	Conn<Customer> connCus;
	Customer loginCus;

	public Login(JFrame jFrame, boolean modal, Customer loginCus, Admin loginAdmin) {
		this.loginCus = loginCus;
		this.setLocationRelativeTo(null);
		this.setTitle("Login");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menuView = (MenuView) jFrame;
		addControl();
		loadCustomerData();
		addEvent();
	}

	private void loadCustomerData() {
		connCus = new ConnToDB<Customer>();
		customers = connCus.query(query, "from Customer");
	}

	private void addEvent() {
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventSubmit(e);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventBack(e);
			}
		});
	}

	protected void btnEventBack(ActionEvent e) {
		MenuView newView = new MenuView(false, null, null);
		newView.setLocationRelativeTo(null);
		newView.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}

	protected void btnEventSubmit(ActionEvent e) {
		String phone = phone_text.getText();
		boolean login = false;

		for (Customer cus : customers) {
			if (phone.trim().equalsIgnoreCase(cus.getPhone())) {
				message.setText(" Hi, " + phone + "");
				login = true;
				MenuView menuView = new MenuView(login, cus, null);
				menuView.setVisible(true);
				menuView.setLocationRelativeTo(null);
				this.setVisible(false);
				this.dispose();

			} else {
				message.setText(" Invalid user.. ");
			}
		}
	}

	private void addControl() {
		// Submit
		submit = new JButton("SUBMIT");
		submit.setBackground(Color.LIGHT_GRAY);
		submit.setForeground(Color.BLACK);

		message = new JLabel();

		phone_label = new JLabel();
		phone_label.setText("Phone Number :");
		phone_text = new JTextField();
		
		btnBack = new JButton("BACK");
		btnBack.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(message, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(phone_label, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
									.addGap(2)
									.addComponent(phone_text, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(91)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(submit)))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(phone_label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(phone_text, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(message, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(submit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		setTitle("Please Login Here !");
		setSize(420, 205);
		setVisible(true);

	}
}
