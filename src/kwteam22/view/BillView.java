package kwteam22.view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.Query;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import kwteam22.model.Admin;
import kwteam22.model.Bill;
import kwteam22.model.Customer;
import kwteam22.view.template.Conn;
import kwteam22.view.template.ConnToDB;

public class BillView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel rootPane;
	private JMenuBar menuBar;
	private JButton btnExit, btnMenu;
	private MenuView bill;
	private Out out;
	// private List<Bill> arrCus;
	private JTextPane textPane;
	private Query query;
	private List<Bill> bills;
	Conn<Customer> connCus;
	List<Customer> customers;
	Conn<Admin> connAdmin;
	List<Admin> admins;
	

	public BillView(JFrame parent, boolean modal) {
		this.setTitle("영수증");
		bill = (MenuView) parent;
		loadCustomerData();
		addControl();
		addEvent();
	}

	void showData(StringBuilder str, List<Bill> lists) {
		for (Bill bill :  lists) {
			str.append("bill id: " + bill.getId() + "\n" + "Phone Number: " + bill.getCustomer().getPhone() + "\n"
					+ "Pay Total: " + bill.getTotal() + "\n-----------------------------------------" + "\n");
		}
	}

	// file으로 부터 데이터 읽음
	private void loadCustomerData() {
		// boolean flag = false;
		Conn<Bill> conn = new ConnToDB<Bill>();
		bills = conn.query(query, "from Bill");
	}

	private void addEvent() {
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventExit(e);
			}
		});
		btnMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventMenu(e);
			}
		});
	}

	protected void btnEventMenu(ActionEvent e) {
		bill = new MenuView(MenuView.loginAcc, MenuView.loginCus);
		bill.setLocationRelativeTo(null);
		bill.setVisible(true);
		this.setVisible(false);
	}

	protected void btnEventExit(ActionEvent e) {

		out = new Out(this);
		out.setLocationRelativeTo(null);
		out.setVisible(true);
	}

	@SuppressWarnings("unlikely-arg-type")
	private void addControl() {
		setBounds(100, 100, 327, 226);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		btnMenu = new JButton("메뉴");
		btnMenu.setBackground(SystemColor.scrollbar);
		menuBar.add(btnMenu);

		btnExit = new JButton("종류");
		btnExit.setBackground(Color.ORANGE);
		menuBar.add(btnExit);

		rootPane = new JPanel();
		rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rootPane);
		rootPane.setLayout(new BoxLayout(rootPane, BoxLayout.X_AXIS));
		textPane = new JTextPane();
		StyledDocument doc = textPane.getStyledDocument();
		Style styleDef = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setFontFamily(styleDef, "SansSerif");
		StringBuilder str = new StringBuilder();

		connCus = new ConnToDB<Customer>();
		customers = connCus.query(query, "from Customer");
		connAdmin = new ConnToDB<Admin>();
		admins = connAdmin.query(query, "from Admin");
		
		if (customers.contains(MenuView.loginCus)) {
			showData(str, MenuView.loginCus.getBills());
		}else if(admins.contains(MenuView.loginCus)) {
			showData(str, bills);
		}
		try {
			doc.insertString(doc.getLength(), String.valueOf(str), doc.getStyle("regular"));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		JScrollPane sc = new JScrollPane(textPane);
		textPane.setEditable(false);
		rootPane.add(sc);

	}
}
