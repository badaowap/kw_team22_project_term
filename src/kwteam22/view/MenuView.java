package kwteam22.view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kwteam22.model.Customer;
import kwteam22.model.Menu;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class MenuView extends JFrame {

	Query query;
	private static final long serialVersionUID = 1L;
	private JPanel rootPane;
	private DefaultTableModel tableModel;
	Customer cus;
	List<Menu> menus;
	private JButton btnDrink, btnWine, btnCafe, btnFood, btnBuy, btnExit, 
			btnInfor, btnLogin, btnSignUp, btnLogout, btnAdmin, btnMenu, btnReset;
	private JTable table;
	private JLabel lblTotal, lblMoney;
	private Menu m;
	public HashMap<Menu, Integer> mapTable;
	private Basket basket;
	private JMenuBar menuBar;
	private Out out;
	//private Admin admin;
	private BillView infor;
	private Login login;
	private SignUp signUp;
	
	public static boolean loginAcc;
	public static Customer loginCus;

	@SuppressWarnings("static-access")
	public MenuView(boolean loginAcc, Customer cus) {
		this.loginAcc = loginAcc;
		this.loginCus = cus;
		setBackground(SystemColor.inactiveCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Wine Store"); // file name
		loadMenuData(); // 메뉴 전부 다 읽어서 받음
		mapTable = new HashMap<Menu, Integer>(); // 메뉴와 해당한 개수 저장
		addControl(); // GUI 설계 함수
		addEvent(); // 이벤트 처리 함수
	}

	// 테이블에다가 데이터 표현하는 함수
	void showData() {
		tableModel.setRowCount(0); // 만약에 출력전 테이터 없으면 테이블 내용을 다 치움
		for (Menu m : mapTable.keySet()) {
			tableModel.addRow(new Object[] { m.getName(), mapTable.get(m), (m.getPrice() * mapTable.get(m)) }); // 테이블
																												// 데이터
																												// 표현
		}

		int total = 0;
		for (Menu m : mapTable.keySet()) {
			total += (m.getPrice() * mapTable.get(m));
		}
		lblMoney.setText(String.valueOf(total)); // 총 계선해야 될 금액
	}

	public Menu findMenuByName(String name) { // 비교해서 menu개체 return
		for (Menu m : menus) {
			if (m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

	public void addMenu(String name, int countR) { // add data to MenuView from Dink, Cafe, Wine, Food
		m = findMenuByName(name);
		if (!mapTable.containsKey(m)) {
			mapTable.put(m, countR);
		} else {
			int getValueSame = 0;
			getValueSame = mapTable.get(m);
			int newValue = getValueSame + countR;
			mapTable.replace(m, newValue);
		}
		showData();
	}

	private void loadMenuData() { // load all data of menus
		Conn<Menu> menu = new ConnToDB<Menu>();
		menus = menu.query(query, "from Menu");
	}

	private void addEvent() {
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventReset(e);
			}
		});
		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventLogout(e);
			}
		});
		btnDrink.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventWater(e);
			}
		});

		btnCafe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventCafe(e);
			}
		});
		btnWine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventWine(e);
			}
		});
		btnFood.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventFood(e);
			}
		});
		btnBuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventBuy(e);
			}
		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventExit(e);
			}
		});
		btnAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventAdmin(e);
			}
		});

		btnInfor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventInfo(e);
			}
		});

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventLogin(e);
			}
		});

		btnSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventSignUp(e);
			}
		});

	}

	protected void btnEventReset(ActionEvent e) {
		mapTable.clear();
		showData();
	}

	protected void btnEventLogout(ActionEvent e) {
		if (loginAcc == true) {
			MenuView menuView = new MenuView(false, loginCus);
			menuView.setVisible(true);
			menuView.setLocationRelativeTo(null);
			this.setVisible(false);
			this.dispose();
		}
	}

	protected void btnEventSignUp(ActionEvent e) {
		signUp = new SignUp(this, true);
		signUp.setLocationRelativeTo(null);
		signUp.setVisible(true);
	}

	protected void btnEventLogin(ActionEvent e) {
		login = new Login(this, true);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}

	protected void btnEventInfo(ActionEvent e) {
		infor = new BillView(this, true);
		infor.setLocationRelativeTo(null);
		infor.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventAdmin(ActionEvent e) {

		/*
		 * admin = new Admin(this, true); admin.setLocationRelativeTo(null);
		 * admin.setVisible(true); this.setVisible(false);
		 */

	}

	protected void btnEventExit(ActionEvent e) {
		out = new Out(this, true);
		out.setLocationRelativeTo(null);
		out.setVisible(true);
	}

	protected void btnEventCancel(ActionEvent e) {
		mapTable.clear();
		showData();
	}

	protected void btnEventBuy(ActionEvent e) {
		basket = new Basket(this, true);
		basket.setLocationRelativeTo(null);
		basket.setVisible(true);
	}

	protected void btnEventFood(ActionEvent e) {
		Food w = new Food(this, true);
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventWine(ActionEvent e) {
		Wine w = new Wine(this, true);
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventCafe(ActionEvent e) {
		Cafe w = new Cafe(this, true);
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventWater(ActionEvent e) {
		Drink w = new Drink(this, true);
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		this.setVisible(false);
	}

	private void addControl() {
		setBounds(100, 100, 606, 559);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		btnAdmin = new JButton("관리자");
		btnAdmin.setBackground(SystemColor.inactiveCaption);
		menuBar.add(btnAdmin);

		btnMenu = new JButton("메뉴");
		btnMenu.setBackground(SystemColor.inactiveCaption);
		menuBar.add(btnMenu);

		btnInfor = new JButton("영수증");
		btnInfor.setBackground(SystemColor.inactiveCaption);
		menuBar.add(btnInfor);

		btnLogin = new JButton("로그인");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		if (loginAcc == true) {
			if(loginCus.getLevel() == 2) {
				btnLogin.setForeground(Color.RED);
			}
			btnLogin.setText( "Hi, "+loginCus.getName());
			btnLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
		
				}
			});
		}
		menuBar.add(btnLogin);

		btnSignUp = new JButton("회원가입");
		btnSignUp.setBackground(Color.YELLOW);
		if (loginAcc == true) {
			btnSignUp.setVisible(false);
		}
		menuBar.add(btnSignUp);

		btnLogout = new JButton("로그웃");
		btnLogout.setBackground(Color.GRAY);
		if (loginAcc == false) {
			btnLogout.setVisible(false);
		}
		menuBar.add(btnLogout);

		btnExit = new JButton("종류");
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.ORANGE);
		menuBar.add(btnExit);

		rootPane = new JPanel();
		rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rootPane);

		JPanel panel = new JPanel();
		GroupLayout gl_rootPane = new GroupLayout(rootPane);
		gl_rootPane.setHorizontalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rootPane
						.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(29, Short.MAX_VALUE)));
		gl_rootPane.setVerticalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rootPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(31, Short.MAX_VALUE)));

		// gan anh vo button
		btnCafe = new JButton("Cafe");
		btnCafe.setBackground(SystemColor.inactiveCaption);

		btnDrink = new JButton("Drink");
		btnDrink.setBackground(SystemColor.inactiveCaption);

		btnFood = new JButton("Food");

		btnFood.setBackground(SystemColor.inactiveCaption);

		btnWine = new JButton("Wine");
		btnWine.setBackground(SystemColor.inactiveCaption);
		table = new JTable();
		String columnName[] = { "이름", "개수", "총금액" };
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, columnName));
		tableModel = (DefaultTableModel) table.getModel();
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(table);

		lblTotal = new JLabel("Total:");

		lblMoney = new JLabel("0");

		btnBuy = new JButton("주문");
		btnBuy.setBackground(SystemColor.inactiveCaption);
		
		btnReset = new JButton("Reset");
		btnReset.setBackground(Color.GREEN);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnWine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnFood, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDrink, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCafe, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sp, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(42)
									.addComponent(btnReset)
									.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
									.addComponent(lblTotal)
									.addGap(18)
									.addComponent(lblMoney, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(313)
							.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(sp, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
							.addGap(8)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMoney)
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReset)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnCafe, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDrink, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnFood, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnWine, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		rootPane.setLayout(gl_rootPane);
	}
}
