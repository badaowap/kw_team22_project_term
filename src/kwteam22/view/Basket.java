package kwteam22.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import kwteam22.model.Menu;

public class Basket extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private MenuView menuView;
	HashMap<Menu, Integer> mapBasket;
	private DefaultTableModel tableModel;
	private JLabel label;
	JLabel labelTotal;
	private JButton btnDelete;
	private JButton btnPay;
	private JLabel lblCoupon;
	private JLabel lblXacNhan;
	private Delete delete;
	private JButton btnInsert;

	public Basket(JFrame jFrame, boolean modal) {
		super(jFrame, modal);
		menuView = (MenuView) jFrame;
		this.setTitle("Basket Information");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addControl();
		mapBasket = new HashMap<Menu, Integer>();
		mapBasket.putAll(menuView.mapTable); // menuView 테이블의 모든 데이터을 복사함
		showData();
		addEvent();
	}

	int totalMoney() { // 주문한 문걸들의 총 금액을 계산 함수
		int total = 0;
		for (Menu m : mapBasket.keySet()) {
			total += (m.getPrice() * mapBasket.get(m));
		}
		if (total >= 70000 && MenuView.loginCus.getLevel() != 0) { // 쿠폰을 적용하면 3000원 빼준다.
			total = total - 3000;
			lblXacNhan.setText("있음");
		} else {
			lblXacNhan.setText("없음");
		}
		return total;
	}

	public void showData() { // 테이블에서 데이터를 표현
		tableModel.setRowCount(0); // 첫번째 데이블이 빈게 한다.
		for (Menu m : mapBasket.keySet()) {
			tableModel.addRow(new Object[] { m.getName(), mapBasket.get(m), (m.getPrice() * mapBasket.get(m)) });
		}

		labelTotal.setText(String.valueOf(totalMoney()));
	}

	public void addEvent() {
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventDelete();

			}
		});
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventPay();
			}
		});
		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventInsert();

			}
		});

	}

	protected void btnEventInsert() {
		MenuView newMenu = new MenuView(MenuView.loginAcc, MenuView.loginCus, MenuView.loginAdmin);
		newMenu.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		newMenu.mapTable = mapBasket; // 원내 테이블에 있던 다 치움
		newMenu.showData();
		newMenu.setVisible(true);
		this.dispose();
	}

	protected void btnEventPay() {

		CustomerView customer = new CustomerView(menuView, this, true); // customer 화면 부름
		customer.setLocationRelativeTo(null);
		customer.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventDelete() {
		delete = new Delete(this, true); // delete 화면 부름
		delete.setLocationRelativeTo(null);
		delete.setVisible(true);
		this.setVisible(false);
	}

	public void addControl() {
		setBounds(100, 100, 339, 399);
		String columnName[] = { "이름", "개수", "총금액" };
		table = new JTable();
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, columnName));
		tableModel = (DefaultTableModel) table.getModel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);

		label = new JLabel("Total: ");

		labelTotal = new JLabel("0");

		lblCoupon = new JLabel("Coupon:");

		lblXacNhan = new JLabel("0");
		lblXacNhan.setFont(new Font("Dialog", Font.BOLD, 15));

		btnInsert = new JButton("   +   ");
		btnInsert.setFont(new Font("Dialog", Font.BOLD, 14));
		btnInsert.setBackground(Color.LIGHT_GRAY);

		btnDelete = new JButton("   -   ");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 14));
		btnDelete.setBackground(Color.LIGHT_GRAY);

		btnPay = new JButton("계산");
		btnPay.setFont(new Font("Dialog", Font.BOLD, 14));
		btnPay.setBackground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
								.addComponent(btnInsert))
							.addGap(86)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(labelTotal, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCoupon)
									.addGap(5)
									.addComponent(lblXacNhan)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelTotal, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCoupon)
								.addComponent(lblXacNhan))
							.addGap(18)
							.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(22))
		);
		getContentPane().setLayout(groupLayout);
	}
}
