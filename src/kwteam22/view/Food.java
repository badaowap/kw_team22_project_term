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

public class Food extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel rootPane, selectPanel;
	private JButton btnSelect, btnBagel, btnChesse, btnBowl, btnBurrito, btnGeneva, btnPizza;
	private JLabel lblSelect, labelCount, lblBagel, lblBowl, lblBurrito, lblChesse, lblGeneva, lblPizza;
	private MenuView bill;
	int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0;
	private JLabel lblBagel_1;
	private JLabel lblBowl_1;
	private JLabel lblBurrito_1;
	private JLabel lblGeneva_1;
	private JLabel lblChesse_1;
	private JLabel lblPizza_1;

	public Food(JFrame jFrame, boolean modal) {
		this.setTitle("Food");
		bill = (MenuView) jFrame;
		addControl();
		addEvent();
	}

	private void addEvent() {
		btnBagel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count1;
				String countMenu = String.valueOf(count1);
				labelCount.setText(countMenu);
				lblSelect.setText(lblBagel.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnBowl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count2;
				String countMenu = String.valueOf(count2);
				labelCount.setText(countMenu);
				lblSelect.setText(lblBowl.getText());
				count1 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnBurrito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count3;
				String countMenu = String.valueOf(count3);
				labelCount.setText(countMenu);
				lblSelect.setText(lblBurrito.getText());
				count2 = 0;
				count1 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnGeneva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count4;
				String countMenu = String.valueOf(count4);
				labelCount.setText(countMenu);
				lblSelect.setText(lblGeneva.getText());
				count2 = 0;
				count3 = 0;
				count1 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnChesse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count5;
				String countMenu = String.valueOf(count5);
				labelCount.setText(countMenu);
				lblSelect.setText(lblChesse.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count1 = 0;
				count6 = 0;
			}
		});

		btnPizza.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count6;
				String countMenu = String.valueOf(count6);
				labelCount.setText(countMenu);
				lblSelect.setText(lblPizza.getText());
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
			bill.addMenu(lblSelect.getText(), Integer.parseInt(labelCount.getText()));
			lblSelect.setText("Selected menu");
			labelCount.setText("0");
			bill.mapTable = bill.mapTable;
			bill.showData();
			this.dispose();
		}

	}

	public void addControl() {

		setBounds(100, 100, 542, 635);
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
		lblSelect.setFont(new Font("Dialog", Font.BOLD, 14));

		labelCount = new JLabel("0");

		btnBagel = new JButton(new ImageIcon("images/food/Bagel_m.png"));

		btnBowl = new JButton(new ImageIcon("images/food/Bean and Rice Bowl_m.png"));
		// ten va anh menu
		btnBurrito = new JButton(new ImageIcon("images/food/Burrito_m.png"));

		btnChesse = new JButton(new ImageIcon("images/food/chesse_m.jpg"));

		btnGeneva = new JButton(new ImageIcon("images/food/Geneva Panini_m.png"));

		btnPizza = new JButton(new ImageIcon("images/food/pizza_m.jpg"));

		lblBagel = new JLabel();
		lblBowl = new JLabel();
		lblBurrito = new JLabel();
		lblChesse = new JLabel();
		lblGeneva = new JLabel();
		lblPizza = new JLabel();
		for (Menu m : bill.menus) {
			if (m.getId().equalsIgnoreCase("m13")) {
				lblBagel.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m14")) {
				lblBowl.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m15")) {
				lblBurrito.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m16")) {
				lblChesse.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m17")) {
				lblGeneva.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m18")) {
				lblPizza.setText(m.getName());
			}
		}

		GroupLayout gl_rootPane = new GroupLayout(rootPane);
		gl_rootPane.setHorizontalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
				.createSequentialGroup()
				.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rootPane.createSequentialGroup().addGap(162)
								.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_rootPane.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblSelect,
														GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_rootPane.createSequentialGroup()
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 44,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(labelCount))))
						.addGroup(gl_rootPane.createSequentialGroup().addGap(201).addComponent(btnSelect,
								GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rootPane.createSequentialGroup().addContainerGap().addComponent(selectPanel,
								GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(43, Short.MAX_VALUE)));
		gl_rootPane.setVerticalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
				.createSequentialGroup()
				.addComponent(selectPanel, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE).addGap(38)
				.addGroup(gl_rootPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(lblSelect))
				.addGap(8)
				.addGroup(gl_rootPane.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
						.addComponent(labelCount))
				.addGap(18).addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
				.addGap(8)));

		lblBagel_1 = new JLabel();

		lblBowl_1 = new JLabel();

		lblBurrito_1 = new JLabel();

		lblGeneva_1 = new JLabel();

		lblChesse_1 = new JLabel();

		lblPizza_1 = new JLabel();

		for (Menu m : bill.menus) {
			if (m.getId().equalsIgnoreCase("m13")) {
				lblBagel_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m14")) {
				lblBowl_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m15")) {
				lblBurrito_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m16")) {
				lblChesse_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m17")) {
				lblGeneva_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m18")) {
				lblPizza_1.setText(String.valueOf(m.getPrice()));
			}
		}

		GroupLayout gl_selectPanel = new GroupLayout(selectPanel);
		gl_selectPanel.setHorizontalGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
				.createSequentialGroup()
				.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
						.createSequentialGroup()
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
								.createSequentialGroup().addGap(18)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnBagel, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnChesse, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(50)
										.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblBagel_1, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblBagel, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
								.createSequentialGroup().addGap(55)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnGeneva, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBowl, GroupLayout.PREFERRED_SIZE, 115,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(88)
										.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblBowl_1, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblBowl, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_selectPanel
								.createSequentialGroup().addGap(76)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBurrito_1, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBurrito, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_selectPanel.createSequentialGroup().addGap(53)
										.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(btnPizza, GroupLayout.PREFERRED_SIZE, 115,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnBurrito, GroupLayout.PREFERRED_SIZE, 115,
														GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_selectPanel.createSequentialGroup().addGap(43)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblChesse, GroupLayout.PREFERRED_SIZE, 55,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblChesse_1, GroupLayout.PREFERRED_SIZE, 55,
												GroupLayout.PREFERRED_SIZE))
								.addGap(123)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblGeneva, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGeneva_1, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE))
								.addGap(110)
								.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 57,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPizza_1, GroupLayout.PREFERRED_SIZE, 43,
												GroupLayout.PREFERRED_SIZE))
								.addGap(30)))
				.addContainerGap(22, Short.MAX_VALUE)));
		gl_selectPanel.setVerticalGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selectPanel.createSequentialGroup().addGap(18)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBagel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBowl, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBurrito, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_selectPanel.createSequentialGroup()
										.addComponent(lblBagel, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(lblBagel_1, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_selectPanel.createSequentialGroup()
										.addComponent(lblBowl, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblBowl_1,
												GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_selectPanel.createSequentialGroup()
										.addComponent(lblBurrito, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblBurrito_1,
												GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_selectPanel.createSequentialGroup()
										.addComponent(btnChesse, GroupLayout.PREFERRED_SIZE, 123,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblChesse,
												GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_selectPanel.createSequentialGroup()
										.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnPizza, GroupLayout.PREFERRED_SIZE, 123,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnGeneva, GroupLayout.PREFERRED_SIZE, 123,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblGeneva, GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 23,
														GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChesse_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGeneva_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPizza_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(23, Short.MAX_VALUE)));
		selectPanel.setLayout(gl_selectPanel);
		rootPane.setLayout(gl_rootPane);
	}
}
