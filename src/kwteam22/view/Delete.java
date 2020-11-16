package kwteam22.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kwteam22.model.Menu;

public class Delete extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private static Basket basket;
	HashMap<Menu, Integer> mapDelete, mapSearch;
	private DefaultTableModel tableModel;
	private JButton btnDelete;
	private JButton btnSearch;
	private JTextField textSearch;
	private Menu keyDelete;
	private JButton btnBack;

	public Delete(JDialog jFrame, boolean modal) {
		super(jFrame, modal);
		basket = (Basket) jFrame;
		basket.setVisible(false);
		this.setTitle("Delete");
		addControl();
		mapDelete = new HashMap<Menu, Integer>();
		mapDelete.putAll(basket.mapBasket);
		mapSearch = new HashMap<Menu, Integer>();
		addEvent();
	}

	public void showData() {
		tableModel.setRowCount(0);
		for (Menu m : mapSearch.keySet()) {
			tableModel.addRow(new Object[] { m.getName(), mapSearch.get(m), (m.getPrice() * mapSearch.get(m)) });
		}
	}

	public void addEvent() {
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventDelete(e);
			}
		});
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventSearch();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEventBack();			}
		});

	}

	protected void btnEventBack() {
		this.dispose();
		basket.setLocationRelativeTo(null);
		basket.setVisible(true);
	}

	protected void btnEventSearch() { //삭제하고 싶은 것을 먼서 검색해야된다
		mapSearch.clear(); //처음에 빈HashMap 만든다
		if (textSearch.getText().trim().equalsIgnoreCase("")) { //아무것도 쓰지않으면 알림
			JOptionPane.showMessageDialog(rootPane, "빈칸!!! 다시 입력하시오.");
		} else {
			for (Menu m : mapDelete.keySet()) { //조금 같은 이름이라도 다 뽑음
				if (m.getName().toLowerCase().contains(textSearch.getText())
						|| m.getName().toUpperCase().contains(textSearch.getText())) {
					mapSearch.put(m, mapDelete.get(m)); //hashMap에서 저장된다.
					keyDelete = m;
				}
			}
			showData(); //테이블으로 데이터를 표현
		}
	}

	protected void btnEventDelete(ActionEvent e) { //삭제 함수. 각 알람이 나온다
		if (mapSearch.size() > 1) {
			JOptionPane.showMessageDialog(rootPane, "하나밖에 삭제가 불가능."); 
			textSearch.setText("");
			mapSearch.clear();
			showData();
		} else if (mapSearch.size() <= 0) {
			JOptionPane.showMessageDialog(rootPane, "가제될 것이 없다.");
			mapSearch.clear();
			textSearch.setText("");
			showData();
		} else {
			mapDelete.remove(keyDelete);
			JOptionPane.showMessageDialog(rootPane, "삭제가 되었다.");
			textSearch.setText("");
			mapSearch.remove(keyDelete);
			showData();
			basket.mapBasket = mapDelete;
			basket.showData();
			this.dispose();
			basket.setLocationRelativeTo(null);
			basket.setVisible(true);
		}
	}

	public void addControl() {
		setBounds(100, 100, 448, 370);
		String columnName[] = { "Name", "Quantity", "Price" };
		
				textSearch = new JTextField();
				textSearch.setColumns(10);
				
						btnSearch = new JButton("Search");
						btnSearch.setBackground(Color.LIGHT_GRAY);
				table = new JTable();
				table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, columnName));
				tableModel = (DefaultTableModel) table.getModel();
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportView(table);
				
				btnBack = new JButton("Back");
				btnBack.setBackground(Color.LIGHT_GRAY);
		
				btnDelete = new JButton("Delete");
				btnDelete.setBackground(Color.LIGHT_GRAY);
				GroupLayout groupLayout = new GroupLayout(getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(btnSearch))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(133)
							.addComponent(btnBack)
							.addGap(5)
							.addComponent(btnDelete))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSearch))
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
				);
				getContentPane().setLayout(groupLayout);
	}
}
