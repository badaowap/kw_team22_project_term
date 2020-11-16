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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kwteam22.model.Admin;
import kwteam22.model.Customer;
import kwteam22.model.Menu;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;
import java.awt.Font;

public class MenuView extends JFrame {

	Query query;
	private static final long serialVersionUID = 1L;
	private JPanel rootPane;
	private DefaultTableModel tableModel;
	Customer cus;
	List<Menu> menus;
	private JButton btnDrink, btnWine, btnCafe, btnFood, btnBuy, btnExit, btnInfor, btnLogin, btnSignUp, btnLogout,
			btnAdmin, btnReset;
	private JTable table;
	private JLabel lblTotal, lblMoney;
	private Menu m;
	public HashMap<Menu, Integer> mapTable;
	private Basket basket;
	private JMenuBar menuBar;
	private Out out;
	
	// private Admin admin;
	private BillView infor;
	private AdminView admin;
	private Login login;
	private LoginAsAdmin loginAsAdmin;
	private SignUp signUp;

	public static boolean loginAcc;
	public static Customer loginCus;
	public static Admin loginAdmin;
	private JMenu mnLogin;
	private JButton btnLoginAsAdmin;
	Food food;
	Wine wine;
	Cafe cafe;
	Drink drink;

	@SuppressWarnings("static-access")
	public MenuView(boolean loginAcc, Customer cus, Admin admin) {
		this.loginAcc = loginAcc;
		this.loginCus = cus;
		this.loginAdmin = admin;
		setBackground(SystemColor.inactiveCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Wine Store"); // file name
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

		btnLoginAsAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventLoginAsAdmin(e);
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
			MenuView menuView = new MenuView(false, loginCus, null);
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
		this.setVisible(false);
	}

	protected void btnEventLogin(ActionEvent e) {
		login = new Login(this, true, loginCus, null);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}

	protected void btnEventLoginAsAdmin(ActionEvent e) {
		loginAsAdmin = new LoginAsAdmin(this, true, null, loginAdmin);
		loginAsAdmin.setLocationRelativeTo(null);
		loginAsAdmin.setVisible(true);
		this.setVisible(false);
		this.dispose();
	}

	protected void btnEventInfo(ActionEvent e) {
		infor = new BillView(this, true, loginCus, loginAdmin);
		infor.setLocationRelativeTo(null);
		infor.setVisible(true);
	}

	protected void btnEventAdmin(ActionEvent e) {		
		 admin = new AdminView(this, true); 
		 admin.setLocationRelativeTo(null);
		 admin.setVisible(true); 
		 this.setVisible(false);
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
		this.setVisible(false);
	}

	protected void btnEventFood(ActionEvent e) {
		food = new Food(this, true);
		food.setLocationRelativeTo(null);
		food.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventWine(ActionEvent e) {
		wine = new Wine(this, true);
		wine.setLocationRelativeTo(null);
		wine.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventCafe(ActionEvent e) {
		cafe = new Cafe(this, true);
		cafe.setLocationRelativeTo(null);
		cafe.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventWater(ActionEvent e) {
		drink = new Drink(this, true);
		drink.setLocationRelativeTo(null);
		drink.setVisible(true);
		this.setVisible(false);
	}


	private void addControl() {
		setBounds(100, 100, 822, 594);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		btnAdmin = new JButton("관리자");
		btnAdmin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAdmin.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnAdmin);
		btnAdmin.setVisible(false);
		if (loginAdmin != null) {
			btnAdmin.setVisible(true);
		}

		btnInfor = new JButton("영수증");
		btnInfor.setFont(new Font("Dialog", Font.BOLD, 14));
		btnInfor.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnInfor);
		mnLogin = new JMenu("Login");
		mnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogin = new JButton("Member");
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLoginAsAdmin = new JButton("Admin    ");
		btnLoginAsAdmin.setFont(new Font("Dialog", Font.BOLD, 14));
		if (loginAcc == true) {
			if (loginCus != null) {
				if (loginCus.getLevel() == 2) {
					mnLogin.setForeground(Color.RED);
				}
				mnLogin.setText("Hi, " + loginCus.getName());
				btnLogin.setVisible(false);
			} else if (loginAdmin != null) {
				mnLogin.setForeground(Color.RED);
				mnLogin.setText("Hi, " + loginAdmin.getUser());
				btnLoginAsAdmin.setVisible(false);
			}
		}
		btnSignUp = new JButton("SignUp");
		btnSignUp.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSignUp.setBackground(Color.YELLOW);
		if (loginAcc == true) {
			btnSignUp.setVisible(false);
		}
		menuBar.add(mnLogin);
		mnLogin.add(btnLogin);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.LIGHT_GRAY);

		btnLoginAsAdmin.setBackground(Color.LIGHT_GRAY);
		mnLogin.add(btnLoginAsAdmin);
		menuBar.add(btnSignUp);

		btnLogout = new JButton("로그웃");
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogout.setBackground(Color.LIGHT_GRAY);
		if (loginAcc == false) {
			btnLogout.setVisible(false);
		}
		menuBar.add(btnLogout);

		btnExit = new JButton("종류");
		btnExit.setFont(new Font("Dialog", Font.BOLD, 14));
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.ORANGE);
		btnExit.setVisible(false);
		if (loginAdmin != null) {
			btnExit.setVisible(true);
		}
		menuBar.add(btnExit);

		rootPane = new JPanel();
		rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rootPane);

		JPanel panel = new JPanel();
		GroupLayout gl_rootPane = new GroupLayout(rootPane);
		gl_rootPane.setHorizontalGroup(
			gl_rootPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rootPane.createSequentialGroup()
					.addContainerGap(0, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_rootPane.setVerticalGroup(
			gl_rootPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
		);

		// gan anh vo button
		btnCafe = new JButton("Cafe");
		btnCafe.setFont(new Font("Dialog", Font.BOLD, 17));
		btnCafe.setBackground(Color.PINK);

		btnDrink = new JButton("Drink");
		btnDrink.setFont(new Font("Dialog", Font.BOLD, 17));
		btnDrink.setBackground(Color.CYAN);

		btnFood = new JButton("Food");
		btnFood.setFont(new Font("Dialog", Font.BOLD, 17));

		btnFood.setBackground(Color.GREEN);

		btnWine = new JButton("Wine");
		btnWine.setFont(new Font("Dialog", Font.BOLD, 17));
		btnWine.setBackground(Color.ORANGE);
		table = new JTable();
		String columnName[] = { "이름", "개수", "총금액" };
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, columnName));
		tableModel = (DefaultTableModel) table.getModel();
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(table);

		lblTotal = new JLabel("Total:");

		lblMoney = new JLabel("0");

		btnBuy = new JButton("주문");
		btnBuy.setFont(new Font("Dialog", Font.BOLD, 14));
		if(loginAdmin != null) {
			btnBuy.setVisible(false);
		}
		btnBuy.setBackground(Color.LIGHT_GRAY);

		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Dialog", Font.BOLD, 14));
		btnReset.setBackground(Color.GREEN);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFood, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addComponent(btnDrink, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnCafe, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnWine, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(42)
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTotal)
									.addGap(18)
									.addComponent(lblMoney, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sp, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(11)
							.addComponent(btnCafe, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDrink, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFood, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnWine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(29))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(sp, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMoney, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReset))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		rootPane.setLayout(gl_rootPane);
		//add(rootPane,SwingConstants.CENTER);
	}
}
