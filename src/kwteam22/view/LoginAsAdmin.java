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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import kwteam22.model.Admin;
import kwteam22.model.Customer;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class LoginAsAdmin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel user_label, phone_label, message;
	JTextField userName_text;
	JTextField phone_text;
	JButton submit, btnBack;
	MenuView menuView;
	Query query;
	List<Admin> admins;
	Conn<Admin> connAdmin;
	Admin loginAdmin;

	public LoginAsAdmin(JFrame jFrame, boolean modal, Customer loginCus, Admin loginAdmin) {
		this.loginAdmin = loginAdmin;
		this.setLocationRelativeTo(null);
		this.setTitle("Login");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menuView = (MenuView) jFrame;
		addControl();
		loadAdminData();
		addEvent();
	}

	private void loadAdminData() {
		connAdmin = new ConnToDB<Admin>();
		admins = connAdmin.query(query, "from Admin");
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
		this.dispose();
	}

	protected void btnEventSubmit(ActionEvent e) {
		String userName = userName_text.getText();
		boolean login = false;

		String password = phone_text.getText();
		for (Admin admin : admins) {
			if (userName.trim().equalsIgnoreCase(admin.getUser()) && password.trim().equals(admin.getPassword())) {
				message.setText(" Hi, " + userName + "");
				login = true;
				MenuView menuView = new MenuView(login, null, admin);
				menuView.setVisible(true);
				menuView.setLocationRelativeTo(null);
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
		phone_label.setText("Password :");

		user_label = new JLabel();
		user_label.setText("User name :");
		userName_text = new JTextField();
		phone_text = new JPasswordField();
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(phone_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(user_label, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(phone_text, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addComponent(userName_text, 203, 203, 203)))
						.addComponent(message, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(73)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(submit)
					.addGap(60))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(user_label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(userName_text, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(phone_label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(phone_text, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(message, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(submit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(13))
		);
		getContentPane().setLayout(groupLayout);
		setTitle("Please Login Here !");
		setSize(371, 192);
		setVisible(true);

	}
}