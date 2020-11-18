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

public class Cafe extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel rootPane, btn2;
	private JButton btnSelect, btnHome, btn3, btn5, btn6, btn1, btn4;
	private JLabel lblCam, lblHome, lblLatte, lblLobby, lblMatcha, lblMocha, lblSelect, labelCount;
	private MenuView bill;
	int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0;
	private JLabel lblCam_1;
	private JLabel lblLobby_1;
	private JLabel lblHome_1;
	private JLabel lblLatte_1;
	private JLabel lblMatcha_1;
	private JLabel lblMocha_1;

	public Cafe(JFrame jFrame, boolean modal) {
		this.setLocationRelativeTo(null);
		this.setTitle("Cafe"); // name
		bill = (MenuView) jFrame;
		addControl();// design function
		addEvent(); // event function
	}

	// event function
	private void addEvent() {

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count1;
				String countMenu = String.valueOf(count1);
				labelCount.setText(countMenu);
				lblSelect.setText(lblCam.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count2;
				String countMenu = String.valueOf(count2);
				labelCount.setText(countMenu);
				lblSelect.setText(lblHome.getText());
				count1 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count3;
				String countMenu = String.valueOf(count3);
				labelCount.setText(countMenu);
				lblSelect.setText(lblLatte.getText());
				count2 = 0;
				count1 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count4;
				String countMenu = String.valueOf(count4);
				labelCount.setText(countMenu);
				lblSelect.setText(lblLobby.getText());
				count2 = 0;
				count3 = 0;
				count1 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count5;
				String countMenu = String.valueOf(count5);
				labelCount.setText(countMenu);
				lblSelect.setText(lblMatcha.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count1 = 0;
				count6 = 0;
			}
		});

		btn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count6;
				String countMenu = String.valueOf(count6);
				labelCount.setText(countMenu);
				lblSelect.setText(lblMocha.getText());
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

	// design function
	public void addControl() {

		setBounds(100, 100, 526, 678);
		rootPane = new JPanel();
		rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(rootPane);

		btn2 = new JPanel();

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

		btn1 = new JButton(new ImageIcon("images/cafe/cappuccino_m.jpg"));
		btn4 = new JButton(new ImageIcon("images/cafe/lobby_m.jpg"));
		btnHome = new JButton(new ImageIcon("images/cafe/homemade_m.jpg"));
		btn3 = new JButton(new ImageIcon("images/cafe/latte_m.png"));
		btn5 = new JButton(new ImageIcon("images/cafe/matcha_m.png"));
		btn6 = new JButton(new ImageIcon("images/cafe/mocha_m.png"));

		lblCam = new JLabel();
		lblHome = new JLabel();
		lblLatte = new JLabel();
		lblLobby = new JLabel();
		lblMatcha = new JLabel();
		lblMocha = new JLabel();

		for (Menu m : bill.menus) {
			if (m.getId().equalsIgnoreCase("m7")) {
				lblCam.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m8")) {
				lblHome.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m9")) {
				lblLatte.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m10")) {
				lblLobby.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m11")) {
				lblMatcha.setText(m.getName());
			} else if (m.getId().equalsIgnoreCase("m12")) {
				lblMocha.setText(m.getName());
			}
		}

		GroupLayout gl_rootPane = new GroupLayout(rootPane);
		gl_rootPane.setHorizontalGroup(
			gl_rootPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rootPane.createSequentialGroup()
					.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rootPane.createSequentialGroup()
							.addGap(162)
							.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_rootPane.createSequentialGroup()
									.addGap(5)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSelect, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_rootPane.createSequentialGroup()
									.addGap(9)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelCount))))
						.addGroup(gl_rootPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rootPane.createSequentialGroup()
							.addGap(186)
							.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_rootPane.setVerticalGroup(
			gl_rootPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rootPane.createSequentialGroup()
					.addGap(38)
					.addComponent(btn2, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(gl_rootPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelect)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_rootPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(labelCount))
					.addGap(18)
					.addComponent(btnSelect, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
					.addContainerGap())
		);

		lblCam_1 = new JLabel();
		lblLobby_1 = new JLabel();
		lblHome_1 = new JLabel();
		lblLatte_1 = new JLabel();
		lblMatcha_1 = new JLabel();
		lblMocha_1 = new JLabel();

		for (Menu m : bill.menus) {
			if (m.getId().equalsIgnoreCase("m7")) {
				lblCam_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m8")) {
				lblHome_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m9")) {
				lblLatte_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m10")) {
				lblLobby_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m11")) {
				lblMatcha_1.setText(String.valueOf(m.getPrice()));
			} else if (m.getId().equalsIgnoreCase("m12")) {
				lblMocha_1.setText(String.valueOf(m.getPrice()));
			}
		}

		GroupLayout gl_btn2 = new GroupLayout(btn2);
		gl_btn2.setHorizontalGroup(gl_btn2.createParallelGroup(Alignment.LEADING).addGroup(gl_btn2
				.createSequentialGroup()
				.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING).addGroup(gl_btn2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING)
								.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_btn2.createSequentialGroup().addGap(21)
										.addComponent(lblCam, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
										.addGap(4))))
						.addGroup(gl_btn2.createSequentialGroup().addContainerGap().addComponent(btn4,
								GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING,
								gl_btn2.createSequentialGroup().addGap(45).addComponent(lblCam_1,
										GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
				.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING).addGroup(gl_btn2.createSequentialGroup()
						.addGap(41)
						.addGroup(gl_btn2.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_btn2.createSequentialGroup().addGap(12).addComponent(lblHome,
										GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
						.addGap(42))
						.addGroup(Alignment.TRAILING, gl_btn2.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblHome_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(54)))
				.addGroup(gl_btn2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_btn2.createSequentialGroup()
								.addGroup(gl_btn2.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_btn2.createSequentialGroup()
												.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 115,
														GroupLayout.PREFERRED_SIZE)
												.addGap(5))
										.addGroup(gl_btn2.createSequentialGroup()
												.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING)
														.addComponent(lblLatte_1, GroupLayout.PREFERRED_SIZE, 78,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblLatte, GroupLayout.PREFERRED_SIZE, 78,
																GroupLayout.PREFERRED_SIZE))
												.addGap(19)))
								.addContainerGap(29, Short.MAX_VALUE))
						.addGroup(gl_btn2.createSequentialGroup()
								.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGap(20))))
				.addGroup(
						gl_btn2.createSequentialGroup().addGap(37)
								.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblLobby_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblLobby, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
								.addGap(78)
								.addGroup(gl_btn2.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblMatcha_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblMatcha, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
								.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblMocha_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblMocha, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
								.addContainerGap(43, Short.MAX_VALUE)));
		gl_btn2.setVerticalGroup(gl_btn2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_btn2.createSequentialGroup().addGap(18)
						.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING)
								.addComponent(btnHome, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn3, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCam, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLatte, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHome_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCam_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLatte_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(32)
						.addGroup(gl_btn2.createParallelGroup(Alignment.TRAILING)
								.addComponent(btn5, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn4, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn6, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_btn2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_btn2.createSequentialGroup()
										.addComponent(lblLobby, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblLobby_1,
												GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_btn2.createSequentialGroup()
										.addComponent(lblMatcha, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblMatcha_1,
												GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_btn2.createSequentialGroup()
										.addComponent(lblMocha, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblMocha_1,
												GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(26, Short.MAX_VALUE)));
		btn2.setLayout(gl_btn2);
		rootPane.setLayout(gl_rootPane);
	}
}
