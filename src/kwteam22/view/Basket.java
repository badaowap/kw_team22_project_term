package kwteam22.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kwteam22.model.Menu;
import java.awt.Color;

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
		if (MenuView.loginAcc == true && total >= 70000) { // 쿠폰을 적용하면 3000원 빼준다.
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
		menuView.mapTable = mapBasket; // 원내 테이블에 있던 다 치움
		menuView.showData();
		menuView.setVisible(true);
		this.setVisible(false);
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
		setBounds(100, 100, 346, 399);
		String columnName[] = { "이름", "개수", "총금액" };
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 127, 65, 17, 105, 0 };
		gridBagLayout.rowHeights = new int[] { 247, 20, 20, 29, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		table = new JTable();
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, columnName));
		tableModel = (DefaultTableModel) table.getModel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		label = new JLabel("Total: ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridwidth = 2;
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		getContentPane().add(label, gbc_label);

		labelTotal = new JLabel("0");
		GridBagConstraints gbc_labelTotal = new GridBagConstraints();
		gbc_labelTotal.anchor = GridBagConstraints.NORTH;
		gbc_labelTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelTotal.insets = new Insets(0, 0, 5, 0);
		gbc_labelTotal.gridx = 3;
		gbc_labelTotal.gridy = 1;
		getContentPane().add(labelTotal, gbc_labelTotal);

		lblCoupon = new JLabel("Coupon:");
		GridBagConstraints gbc_lblCoupon = new GridBagConstraints();
		gbc_lblCoupon.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblCoupon.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoupon.gridwidth = 2;
		gbc_lblCoupon.gridx = 1;
		gbc_lblCoupon.gridy = 2;
		getContentPane().add(lblCoupon, gbc_lblCoupon);

		lblXacNhan = new JLabel("0");
		GridBagConstraints gbc_lblXacNhan = new GridBagConstraints();
		gbc_lblXacNhan.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblXacNhan.insets = new Insets(0, 0, 5, 0);
		gbc_lblXacNhan.gridx = 3;
		gbc_lblXacNhan.gridy = 2;
		getContentPane().add(lblXacNhan, gbc_lblXacNhan);

		btnInsert = new JButton("추가");
		btnInsert.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnInsert.insets = new Insets(0, 0, 0, 5);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 3;
		getContentPane().add(btnInsert, gbc_btnInsert);

		btnDelete = new JButton("삭제");
		btnDelete.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 3;
		getContentPane().add(btnDelete, gbc_btnDelete);

		btnPay = new JButton("계산");
		btnPay.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPay.gridwidth = 2;
		gbc_btnPay.gridx = 2;
		gbc_btnPay.gridy = 3;
		getContentPane().add(btnPay, gbc_btnPay);
	}
}
