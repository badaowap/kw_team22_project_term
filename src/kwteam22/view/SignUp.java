package kwteam22.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import kwteam22.model.Customer;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;
import java.awt.Font;

public class SignUp extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel user_label, phone_label, message, birthday_label;
	JTextField txtName, txtBirthday, txtPhone;
	JButton submit, btnBack;
	MenuView menuView;
	JCheckBox checkBoxFavorite;
	Query query;
	Conn<Customer> conn;
	List<Customer> customers;

	public SignUp(JFrame jFrame, boolean modal) {
		this.setLocationRelativeTo(null);
		this.setTitle("Sign Up");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menuView = (MenuView) jFrame;
		addControl();
		addEvent();
	}

	private void addEvent() {
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventSignUp(e);
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
		menuView.dispose();
		MenuView newMenuView = new MenuView(false, null, null);
		newMenuView.setLocationRelativeTo(null);
		newMenuView.setVisible(true);
		this.dispose();
	}
	
	protected void btnEventSignUp(ActionEvent e) {
		String name = txtName.getText().trim();
		String phone = txtPhone.getText().trim();
		String birthday = txtBirthday.getText().trim();
		Date date;
		try {
			date = new SimpleDateFormat("yyyy/MM/dd").parse(birthday);
		} catch (Exception e1) {
			date = null;
			e1.printStackTrace();
		}
		Customer member = null;
		if (checkBoxFavorite.isSelected() && date != null) {
			member = new Customer(phone, name, date);
			member.setLevel(2);
		} else if (checkBoxFavorite.isSelected() == false || date == null) {
			member = new Customer(phone, name);
			member.setLevel(1);
		}
		conn = new ConnToDB<Customer>();
		customers = conn.query(query, "from Customer");

		if (customers.contains(member) == false) {
			message.setText(" Hi, " + member.getName() + "");
			conn.add(member);
			menuView.dispose();
			MenuView newMenuView = new MenuView(true, member, null);
			newMenuView.setLocationRelativeTo(null);
			newMenuView.setVisible(true);
			this.dispose();
		} else {
			message.setText(" Invalid user.. ");
		}
	}

	private void addControl() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		// User Label
		user_label = new JLabel();
		user_label.setFont(new Font("Dialog", Font.BOLD, 14));
		user_label.setText("Name :");
		txtName = new JTextField();

		// Password
		phone_label = new JLabel();
		phone_label.setFont(new Font("Dialog", Font.BOLD, 14));
		phone_label.setText("Phone :");
		txtPhone = new JTextField();

		// phone number
		birthday_label = new JLabel();
		birthday_label.setFont(new Font("Dialog", Font.BOLD, 14));
		birthday_label.setText("Birthday :");
		txtBirthday = new JTextField();

		// Submit

		submit = new JButton("SUBMIT");
		submit.setFont(new Font("Dialog", Font.BOLD, 14));
		submit.setBackground(Color.LIGHT_GRAY);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);

		message = new JLabel();

		getContentPane().add(panel);

		checkBoxFavorite = new JCheckBox("단골 등록");
		checkBoxFavorite.setFont(new Font("Dialog", Font.BOLD, 15));
		checkBoxFavorite.setBackground(Color.WHITE);

		btnBack = new JButton("Back ");
		btnBack.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBack.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(phone_label, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(user_label, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(birthday_label, GroupLayout.PREFERRED_SIZE, 91,
										GroupLayout.PREFERRED_SIZE))
						.addGap(24)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtBirthday, 207, 207, Short.MAX_VALUE)
								.addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.addComponent(checkBoxFavorite)
								.addComponent(message, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
						.addGap(53))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup().addGap(57)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
								.addComponent(submit, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addGap(42)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(user_label, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(phone_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGap(18).addComponent(checkBoxFavorite).addGap(11)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(birthday_label, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(message, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(submit, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGap(49)));
		panel.setLayout(gl_panel);
		setTitle("Please SignUp Here !");
		setSize(387, 363);
		setVisible(true);
	}
}
