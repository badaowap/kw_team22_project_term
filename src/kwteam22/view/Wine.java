package kwteam22.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import kwteam22.model.Menu;

public class Wine extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel rootPane, selectPanel;
	private JButton btnSelect, btnBrothers, btnMakedonik, btnCuvee89, btnLamarca, btnRon, btnUnruly;
	private JLabel lblSelect, labelCount, lblBrothers, lblCuvee89, lblLamarca, lblMakedonik, lblRon, lblUnruly;
	private MenuView menuView;
	int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0; // click chon thi tu dong tang len 1
	private JLabel lblBrothers_1;
	private JLabel lblCuvee89_1;
	private JLabel lblLamarca_1;
	private JLabel lblMakedonik_1;
	private JLabel lblRon_1;
	private JLabel lblUnruly_1;

	public Wine(JFrame jFrame, boolean modal) {
		this.setLocationRelativeTo(null);
		this.setTitle("Wine"); // ten
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menuView = (MenuView) jFrame;
		addControl();
		addEvent();
	}

	private void addEvent() {
		btnBrothers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count1;
				String countMenu = String.valueOf(count1);
				labelCount.setText(countMenu);
				lblSelect.setText(lblBrothers.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnCuvee89.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count2;
				String countMenu = String.valueOf(count2);
				labelCount.setText(countMenu);
				lblSelect.setText(lblCuvee89.getText());
				count1 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;

			}
		});

		btnLamarca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count3;
				String countMenu = String.valueOf(count3);
				labelCount.setText(countMenu);
				lblSelect.setText(lblLamarca.getText());
				count2 = 0;
				count1 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnRon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count4;
				String countMenu = String.valueOf(count4);
				labelCount.setText(countMenu);
				lblSelect.setText(lblRon.getText());
				count2 = 0;
				count3 = 0;
				count1 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnMakedonik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count5;
				String countMenu = String.valueOf(count5);
				labelCount.setText(countMenu);
				lblSelect.setText(lblMakedonik.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count1 = 0;
				count6 = 0;
			}
		});

		btnUnruly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count6;
				String countMenu = String.valueOf(count6);
				labelCount.setText(countMenu);
				lblSelect.setText(lblUnruly.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count1 = 0;
			}
		});
		btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelect(e);
			}
		});

	}

	protected void btnSelect(ActionEvent e) {
		if (lblSelect.getText() == "Selected menu") {
			JOptionPane.showMessageDialog(rootPane, "제품을 선택하지 않았음");
		} else if (Integer.parseInt(labelCount.getText()) <= 0) {
			JOptionPane.showMessageDialog(rootPane, "수량을 선택하시요");
		} else {
			menuView.addMenu(lblSelect.getText(), Integer.parseInt(labelCount.getText()));
			lblSelect.setText("Selected menu");
			labelCount.setText("0");
			MenuView newMenu = new MenuView(MenuView.loginAcc, MenuView.loginCus, MenuView.loginAdmin);
			newMenu.setVisible(true);
			newMenu.setLocationRelativeTo(null);
			newMenu.mapTable = menuView.mapTable;
			newMenu.showData();
			this.dispose();
		}

	}

	public void addControl() {

		setBounds(100, 100, 533, 678);
		rootPane = new JPanel();
		rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rootPane);

		selectPanel = new JPanel();

		JLabel lblNewLabel = new JLabel("선택:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));

		JLabel label_1 = new JLabel("\uAC1C\uC218: ");
		label_1.setFont(new Font("Dialog", Font.BOLD, 15));

		btnSelect = new JButton("주문");
		btnSelect.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSelect.setBackground(Color.LIGHT_GRAY);

		lblSelect = new JLabel("Selected menu");
		lblSelect.setFont(new Font("Dialog", Font.BOLD, 15));

		labelCount = new JLabel("0");

		btnBrothers = new JButton(new ImageIcon("images/wine/Brothers.png"));

		btnMakedonik = new JButton(new ImageIcon("images/wine/Cuvee 89.png"));
		// ten va anh menu
		btnCuvee89 = new JButton(new ImageIcon("images/wine/Lamarca.png"));

		btnLamarca = new JButton(new ImageIcon("images/wine/Makedonik.jpg"));

		btnRon = new JButton(new ImageIcon("images/wine/Ron.png"));

		btnUnruly = new JButton(new ImageIcon("images/wine/Unruly.png"));

		lblBrothers = new JLabel();
		lblCuvee89 = new JLabel();
		lblLamarca = new JLabel();
		lblMakedonik = new JLabel();
		lblRon = new JLabel();
		lblUnruly = new JLabel();

		for (Menu m : menuView.menus) {
			if (m.getId().equalsIgnoreCase("m20")) {
				lblBrothers.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m21")) {
				lblCuvee89.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m22")) {
				lblLamarca.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m23")) {
				lblMakedonik.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m23")) {
				lblMakedonik.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m24")) {
				lblRon.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m25")) {
				lblUnruly.setText(m.getName());
			}
		}

		GroupLayout gl_rootPane = new GroupLayout(rootPane);
		gl_rootPane.setHorizontalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
				.createSequentialGroup()
				.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rootPane.createSequentialGroup().addGap(162)
								.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_rootPane.createSequentialGroup().addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblSelect,
														GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_rootPane.createSequentialGroup()
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 44,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(labelCount))))
						.addComponent(selectPanel, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_rootPane.createSequentialGroup().addGap(179).addComponent(btnSelect,
								GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(22, Short.MAX_VALUE)));
		gl_rootPane.setVerticalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
				.createSequentialGroup().addContainerGap()
				.addComponent(selectPanel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_rootPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(lblSelect))
				.addGap(18)
				.addGroup(gl_rootPane.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
						.addComponent(labelCount))
				.addGap(18).addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
				.addGap(25)));

		lblBrothers_1 = new JLabel();

		lblCuvee89_1 = new JLabel();

		lblLamarca_1 = new JLabel();

		lblMakedonik_1 = new JLabel();

		lblRon_1 = new JLabel();

		lblUnruly_1 = new JLabel();

		for (Menu m : menuView.menus) {
			if (m.getId().equalsIgnoreCase("m20")) {
				lblBrothers_1.setText(String.valueOf(m.getPrice()));
			}
			if (m.getId().equalsIgnoreCase("m21")) {
				lblCuvee89_1.setText(String.valueOf(m.getPrice()));
			}
			if (m.getId().equalsIgnoreCase("m22")) {
				lblLamarca_1.setText(String.valueOf(m.getPrice()));
			}
			if (m.getId().equalsIgnoreCase("m23")) {
				lblMakedonik_1.setText(String.valueOf(m.getPrice()));
			}
			if (m.getId().equalsIgnoreCase("m24")) {
				lblRon_1.setText(String.valueOf(m.getPrice()));
			}
			if (m.getId().equalsIgnoreCase("m25")) {
				lblUnruly_1.setText(String.valueOf(m.getPrice()));
			}
		}

		GroupLayout gl_selectPanel = new GroupLayout(selectPanel);
		gl_selectPanel.setHorizontalGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
				.createSequentialGroup()
				.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
						.createSequentialGroup().addGap(34)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBrothers, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnMakedonik, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.TRAILING,
												gl_selectPanel.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_selectPanel.createSequentialGroup().addGap(12)
																.addComponent(lblMakedonik_1, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addComponent(lblMakedonik, Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE, 95,
																GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(85).addComponent(lblRon,
										GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(50)
										.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(btnCuvee89, GroupLayout.PREFERRED_SIZE, 115,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnRon, GroupLayout.PREFERRED_SIZE, 115,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(70).addComponent(lblRon_1,
										GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_selectPanel.createSequentialGroup().addGroup(gl_selectPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(59).addComponent(lblBrothers,
										GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(79).addComponent(lblBrothers_1,
										GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
								.addGap(85)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_selectPanel.createSequentialGroup().addGap(12).addComponent(
												lblCuvee89_1, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(lblCuvee89, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE))))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_selectPanel.createSequentialGroup().addGap(46)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnUnruly, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLamarca, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_selectPanel.createSequentialGroup().addGap(70)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUnruly, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUnruly_1, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_selectPanel.createSequentialGroup().addGap(76).addComponent(lblLamarca_1,
								GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_selectPanel.createSequentialGroup().addGap(65).addComponent(lblLamarca,
								GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
				.addGap(324)));
		gl_selectPanel.setVerticalGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnBrothers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCuvee89, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnLamarca, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.UNRELATED, 24, Short.MAX_VALUE)
				.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_selectPanel.createSequentialGroup()
								.addComponent(lblBrothers, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblBrothers_1,
										GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_selectPanel.createSequentialGroup()
								.addComponent(lblCuvee89, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblCuvee89_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_selectPanel.createSequentialGroup()
								.addComponent(lblLamarca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblLamarca_1,
										GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
				.addGap(52)
				.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMakedonik, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRon, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUnruly, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
						.createSequentialGroup()
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMakedonik, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnruly, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMakedonik_1, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnruly_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_selectPanel.createSequentialGroup()
								.addComponent(lblRon, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblRon_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
				.addGap(22)));
		selectPanel.setLayout(gl_selectPanel);
		rootPane.setLayout(gl_rootPane);
	}
}
