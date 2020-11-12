package kwteam22.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
				if (txtBirthday.getText().trim().equalsIgnoreCase("") == false && customer.getLevel() == 1) {
					date = new SimpleDateFormat("yyyy/MM/dd").parse(txtBirthday.getText());
					connCus.modify(customer, customer.getPhone(), date, 2);
				}else if(txtBirthday.getText().trim().equalsIgnoreCase("") == false && customer.getLevel() == 2) {
					customer = MenuView.loginCus; 
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
		}else {
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
		setBounds(100, 100, 398, 286);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 70, 109, 0, 0, 157, 0 };
		gbl_contentPanel.rowHeights = new int[] { 26, 33, 0, 0, 26, 33, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNewLabel = new JLabel("phone: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		txtPhone = new JTextField();
		if (cus.getPhone().equals("00000000000"))
			txtPhone.setText("");
		else {
			txtPhone.setText(cus.getPhone());
		}
		txtPhone.setColumns(10);
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.anchor = GridBagConstraints.NORTH;
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.insets = new Insets(0, 0, 5, 0);
		gbc_txtPhone.gridwidth = 4;
		gbc_txtPhone.gridx = 1;
		gbc_txtPhone.gridy = 0;
		contentPanel.add(txtPhone, gbc_txtPhone);

		lblName = new JLabel("name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		contentPanel.add(lblName, gbc_lblName);

		txtName = new JTextField();
		txtName.setText(cus.getName());

		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.NORTH;
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.gridwidth = 4;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 2;
		contentPanel.add(txtName, gbc_txtName);

		lblBirthday = new JLabel("birthday:");
		GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
		gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthday.gridx = 0;
		gbc_lblBirthday.gridy = 4;
		contentPanel.add(lblBirthday, gbc_lblBirthday);

		txtBirthday = new JTextField();
		if (cus.getLevel() == 0 || cus.getLevel() == 1)
			txtBirthday.setText("");
		else {
			txtBirthday.setText(String.valueOf(cus.getBirthday()));
		}
		GridBagConstraints gbc_txtBirthday = new GridBagConstraints();
		gbc_txtBirthday.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirthday.gridwidth = 4;
		gbc_txtBirthday.insets = new Insets(0, 0, 5, 0);
		gbc_txtBirthday.gridx = 1;
		gbc_txtBirthday.gridy = 4;
		contentPanel.add(txtBirthday, gbc_txtBirthday);
		txtBirthday.setColumns(10);

		JLabel label_1 = new JLabel("총 계산: ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 6;
		contentPanel.add(label_1, gbc_label_1);

		labelTotal = new JLabel(" ");
		labelTotal.setText(String.valueOf(basket.labelTotal));

		GridBagConstraints gbc_labelTotal = new GridBagConstraints();
		gbc_labelTotal.gridwidth = 3;
		gbc_labelTotal.anchor = GridBagConstraints.NORTH;
		gbc_labelTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelTotal.gridx = 2;
		gbc_labelTotal.gridy = 6;
		contentPanel.add(labelTotal, gbc_labelTotal);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[] { 256, 65, 53, 0 };
			gbl_buttonPane.rowHeights = new int[] { 29, 0 };
			gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			buttonPane.setLayout(gbl_buttonPane);
			{
				btnCancel = new JButton("취소");
				btnCancel.setBackground(Color.GRAY);
				btnCancel.setActionCommand("Cancel");
				GridBagConstraints gbc_btnCancel = new GridBagConstraints();
				gbc_btnCancel.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
				gbc_btnCancel.gridx = 1;
				gbc_btnCancel.gridy = 0;
				buttonPane.add(btnCancel, gbc_btnCancel);
			}
			{
				btnOk = new JButton("OK");
				btnOk.setBackground(Color.GRAY);
				btnOk.setActionCommand("OK");
				GridBagConstraints gbc_btnOk = new GridBagConstraints();
				gbc_btnOk.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnOk.gridx = 2;
				gbc_btnOk.gridy = 0;
				buttonPane.add(btnOk, gbc_btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
		}
	}
}
