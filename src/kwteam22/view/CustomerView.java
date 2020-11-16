package kwteam22.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import kwteam22.model.Bill;
import kwteam22.model.Customer;
import kwteam22.model.Menu;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class CustomerView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPhone;
	private JTextField txtName;
	private JButton btnCancel, btnOk;
	private Basket basket;
	private MenuView menuView;
	HashMap<Menu, Integer> hashCus;
	JLabel labelTotal;
	private JLabel lblBirthday, lblName;
	private JTextField txtBirthday;
	private Customer cus;
	List<Customer> customers;
	Conn<Customer> connCus;
	Conn<Bill> connBill;
	Query query;
	private JLabel lblNewLabel;
	private JLabel label;

	public CustomerView(JFrame jFrame, JDialog jDialog, boolean modal) {
		super(jFrame, modal);
		menuView = (MenuView) jFrame;
		basket = (Basket) jDialog;
		basket.setVisible(false);
		hashCus = new HashMap<Menu, Integer>();
		hashCus.putAll(basket.mapBasket);
		this.setTitle("Customer Information");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		cusDataAccess();
		addControl();
		labelTotal.setText(basket.labelTotal.getText());
		{
			btnCancel = new JButton("취소");
			btnCancel.setFont(new Font("Dialog", Font.BOLD, 15));
			btnCancel.setBackground(Color.LIGHT_GRAY);
			btnCancel.setActionCommand("Cancel");
		}
		{
			btnOk = new JButton("OK");
			btnOk.setFont(new Font("Dialog", Font.BOLD, 15));
			btnOk.setBackground(Color.LIGHT_GRAY);
			btnOk.setActionCommand("OK");
			getRootPane().setDefaultButton(btnOk);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblName))
								.addComponent(lblBirthday)
								.addGroup(gl_contentPanel
										.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)))
								.addGap(24)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtBirthday, GroupLayout.PREFERRED_SIZE, 305,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 302,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 298,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(109).addComponent(label)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(labelTotal, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(17, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_contentPanel.createSequentialGroup().addContainerGap(247, Short.MAX_VALUE)
								.addComponent(btnCancel).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnOk)
								.addGap(39)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup()
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(
						txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(lblBirthday).addComponent(txtBirthday,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(label)
						.addComponent(labelTotal))
				.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnCancel)
						.addComponent(btnOk))
				.addGap(33)));
		contentPanel.setLayout(gl_contentPanel);
		addEvent();

	}

	private void cusDataAccess() {
		if (MenuView.loginCus != null && MenuView.loginAcc == false) {
			cus = MenuView.loginCus;
		} else if (MenuView.loginCus == null && MenuView.loginAcc == false) {
			connCus = new ConnToDB<Customer>();
			customers = connCus.query(query, "from Customer");
			for (Customer c : customers) {
				if (c.getPhone().equals("00000000000")) {
					cus = c;
				}
			}

		} else {
			cus = MenuView.loginCus;
		}
	}

	private void addEvent() {
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnEventOk(e);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventCancel(e);
			}
		});
	}

	protected void btnEventCancel(ActionEvent e) {
		MenuView newMenuView = new MenuView(MenuView.loginAcc, MenuView.loginCus, MenuView.loginAdmin);
		newMenuView.setLocationRelativeTo(null);
		newMenuView.setVisible(true);
		this.dispose();
	}

	protected void btnEventOk(ActionEvent e) throws ParseException {
		connBill = new ConnToDB<Bill>();
		connCus = new ConnToDB<Customer>();
		customers = connCus.query(query, "from Customer");
		Date date = null;
		Customer customer = null;

		if (txtPhone.getText().trim().equalsIgnoreCase("") == false) {
			customer = new Customer();
			customer.setPhone(txtPhone.getText().trim());
			customer.setName(txtName.getText().trim());

			if (customers.contains(customer)) {
				if (txtBirthday.getText().trim().equalsIgnoreCase("") == false) {
					date = new SimpleDateFormat("yyyy/MM/dd").parse(txtBirthday.getText());
					connCus.modify(customer, customer.getPhone(), date, 2);
				}
			} else {
				if (txtBirthday.getText().trim().equalsIgnoreCase("") == false) {
					customer.setLevel(2);
					date = new SimpleDateFormat("yyyy/MM/dd").parse(txtBirthday.getText());
					customer.setBirthday(date);
					connCus.add(customer);

				} else {
					customer.setLevel(1);
					customer.setBirthday(null);
					connCus.add(customer);
				}
			}
		} else {
			customer = cus;
		}

		Bill bill = new Bill(customer, Integer.parseInt(labelTotal.getText()));
		connBill.add(bill);
		JOptionPane.showMessageDialog(rootPane, "계산되었다.");
		MenuView menu = new MenuView(false, null, null);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		menuView.dispose();
		this.dispose();
	}

	private void addControl() {
		setBounds(100, 100, 422, 241);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblNewLabel = new JLabel("phone: ");

		txtPhone = new JTextField();
		if (cus.getPhone().equals("00000000000"))
			txtPhone.setText(" ");
		else {
			txtPhone.setText(cus.getPhone());
			txtPhone.setEditable(false);
		}
		txtPhone.setColumns(10);

		lblName = new JLabel("name:");

		txtName = new JTextField();
		if (cus.getPhone().equals("00000000000"))
			txtName.setText(" ");
		else {
			txtName.setText(cus.getName());
			txtName.setEditable(false);
		}
		txtName.setText(cus.getName());

		txtName.setColumns(10);

		lblBirthday = new JLabel("birthday:");

		txtBirthday = new JTextField();
		if (cus.getLevel() == 0 || cus.getLevel() == 1)
			txtBirthday.setText(" ");
		else {
			txtBirthday.setText(String.valueOf(cus.getBirthday()));
			txtBirthday.setEditable(false);
		}
		txtBirthday.setColumns(10);

		label = new JLabel("총 계산: ");
		label.setFont(new Font("Dialog", Font.BOLD, 15));

		labelTotal = new JLabel(" ");
		labelTotal.setText(String.valueOf(basket.labelTotal));
	}
}
