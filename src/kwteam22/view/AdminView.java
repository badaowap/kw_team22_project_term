package kwteam22.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kwteam22.model.Basket;
import kwteam22.model.BasketPK;
import kwteam22.model.Bill;
import kwteam22.model.Customer;
import kwteam22.model.Menu;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class AdminView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel rootPane;
	private DefaultTableModel tableModel_1, tableModel_2;
	private JMenuBar menuBar;
	private JButton btnMenu, btnSee;
	private MenuView bill;
	@SuppressWarnings("unused")
	private BillView infor;
	HashMap<String, Customer> cusMenuMap;
	private Out out;
	private JTable table1;
	private JTextField txtSearch;
	private JScrollPane scrollPane_1;
	private JTable table_2;
	private String phoneSearch;
	Query query;
	Conn<Customer> connCus;
	Conn<Menu> connMenu;
	Conn<Bill> connBill;
	Conn<Basket> connBasket;
	List<Menu> menus;
	List<Customer> customers;
	List<Bill> bills;
	List<Basket> baskets;
	List<BasketPK> basketBKs;
	HashMap<Customer, Integer> mapCusInfo;
	private JLabel lblName;
	private JLabel lblName_1;
	private JLabel lblPhone;
	private JLabel lblBirthday;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lblCopyrightByKwteam;

	/**
	 * @wbp.parser.constructor
	 */
	public AdminView(JFrame parent, boolean modal) {
		this.setTitle("Admin");
		bill = (MenuView) parent;
		loadBillData();
		loadCustomerMenu();
		addControl();
		showCusInfor();
		addEvent();
	}

	// read info from file, then add into maptemp --
	private void loadBillData() {
		connBill = new ConnToDB<Bill>();
		bills = connBill.query(query, "from Bill");
	}

	// đọc thông tin customer file rồi xử lí
	private void loadCustomerMenu() {
		connBasket = new ConnToDB<Basket>();
		baskets = connBasket.query(query, "from Basket");
	}

	public void addTotalToMap(Customer cus, int countR) { // add data to MenuView from Dink, Cafe, Wine, Food
		if (!mapCusInfo.containsKey(cus)) {
			mapCusInfo.put(cus, countR);
		} else {
			int getValueSame = 0;
			getValueSame = mapCusInfo.get(cus);
			int newValue = getValueSame + countR;
			mapCusInfo.replace(cus, newValue);
		}
	}

	void showCusInfor() {
		mapCusInfo = new HashMap<Customer, Integer>();
		tableModel_1.setRowCount(0);
		for (Bill b : bills) {
			Customer c = new Customer();
			c.setPhone(b.getCustomer().getPhone());
			addTotalToMap(c, b.getTotal());
		}
		for (Customer c : mapCusInfo.keySet())
			tableModel_1.addRow(new Object[] { c.getPhone(), mapCusInfo.get(c) });
	}

	private void addEvent() {
		btnMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventMenu(e);
			}
		});
		btnSee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventSee(e);
			}
		});

	}

	protected void btnEventSee(ActionEvent e) {
		phoneSearch = txtSearch.getText().trim();
		Customer c = new Customer();
		connCus = new ConnToDB<Customer>();
		customers = connCus.query(query, "from Customer");
		for (Customer cus : customers) {
			if (cus.getPhone().equalsIgnoreCase(phoneSearch)) {
				c = cus;
				break;
			}
		}
		List<Bill> listBills = new ArrayList<Bill>();
		for (Bill b : bills) {
			if(b.getCustomer().equals(c)) {
				listBills.add(b);
			}
		}
		tableModel_2.setNumRows(0);
		for (Bill b : listBills)
			tableModel_2.addRow(new Object[] { b.getCustomer().getPhone(), b.getTotal() });
		lblName_1.setText(c.getName());

		lbl2.setText(c.getPhone());
		lbl3.setText(String.valueOf(c.getBirthday()));
	}

	protected void btnEventMenu(ActionEvent e) {
		bill.setLocationRelativeTo(null);
		bill.setVisible(true);
		this.setVisible(false);
	}

	private void addControl() {
		setBounds(100, 100, 604, 553);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		btnMenu = new JButton("메뉴");
		btnMenu.setFont(new Font("Dialog", Font.BOLD, 15));
		btnMenu.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnMenu);

		rootPane = new JPanel();
		rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rootPane);

		String columnName1[] = { "전화번호", "총 지불" };
		String columnName2[] = { "전화번호", "금액" };

		JPanel panel_1 = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		table1 = new JTable();
		table1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, columnName1));
		tableModel_1 = (DefaultTableModel) table1.getModel();
		scrollPane.setViewportView(table1);
		scrollPane.setViewportView(table1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		btnSee = new JButton("보기");
		btnSee.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		JPanel panel_2 = new JPanel();

		scrollPane_1 = new JScrollPane();
		table_2 = new JTable();
		table_2.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, columnName2));
		tableModel_2 = (DefaultTableModel) table_2.getModel();
		scrollPane_1.setViewportView(table_2);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(102, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Dialog", Font.BOLD, 15));

		lblName_1 = new JLabel(" ");
		lblName_1.setFont(new Font("Dialog", Font.BOLD, 15));

		lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Dialog", Font.BOLD, 15));

		lblBirthday = new JLabel("Birthday:");
		lblBirthday.setFont(new Font("Dialog", Font.BOLD, 15));

		lbl2 = new JLabel(" ");
		lbl2.setFont(new Font("Dialog", Font.BOLD, 15));

		lbl3 = new JLabel(" ");
		lbl3.setFont(new Font("Dialog", Font.BOLD, 15));

		lblCopyrightByKwteam = new JLabel("Copyright by KWteam22");
		lblCopyrightByKwteam.setForeground(Color.RED);
		lblCopyrightByKwteam.setFont(new Font("Dialog", Font.BOLD, 15));
		GroupLayout gl_rootPane = new GroupLayout(rootPane);
		gl_rootPane.setHorizontalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
				.createSequentialGroup()
				.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
						.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_rootPane.createSequentialGroup().addGroup(gl_rootPane
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_rootPane.createSequentialGroup().addComponent(lblName).addGap(23))
										.addGroup(
												gl_rootPane.createSequentialGroup().addComponent(lblPhone).addGap(18)))
										.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblName_1, GroupLayout.PREFERRED_SIZE, 175,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 175,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_rootPane.createSequentialGroup()
										.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 171,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnSee)))
								.addGroup(gl_rootPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
												.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 257,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_rootPane.createSequentialGroup().addComponent(lblBirthday)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl3,
																GroupLayout.PREFERRED_SIZE, 175,
																GroupLayout.PREFERRED_SIZE)))))
						.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_rootPane.createSequentialGroup().addGap(189).addComponent(lblCopyrightByKwteam)))
				.addContainerGap(293, Short.MAX_VALUE)));
		gl_rootPane.setVerticalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rootPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_rootPane.createSequentialGroup()
										.addGroup(gl_rootPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 29,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnSee))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_rootPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblName).addComponent(lblName_1))
										.addGap(18)
										.addGroup(gl_rootPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 15,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lbl2))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addComponent(lbl3)
												.addComponent(lblBirthday))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(panel_2, 0, 0, Short.MAX_VALUE))
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
						.addComponent(lblCopyrightByKwteam).addContainerGap()));
		rootPane.setLayout(gl_rootPane);
	}
}
