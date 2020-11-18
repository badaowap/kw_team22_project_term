package kwteam22.view;

import java.awt.Color;
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
import java.awt.Font;

public class Drink extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel rootPane, selectPanel;
	private JButton btnSelect, buttonAfri, buttonPer, buttonBunda, buttonCoca, buttonNgo, buttonSprite;
	private JLabel lblSelect, labelCount, lblAfri, lblBundaberg, lblCocacola, lblPerrier, lblngo, lblSprite;
	private MenuView bill;
	int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0; // click chon thi tu dong tang len 1
	private JLabel lblAfri_1;
	private JLabel lblPerrier_1;
	private JLabel lblBundaberg_1;
	private JLabel lblCocacola_1;
	private JLabel lblngo_1;
	private JLabel lblSprite_1;

	public Drink(JFrame jFrame, boolean modal) {
		this.setLocationRelativeTo(null);
		this.setTitle("Drink");
		bill = (MenuView) jFrame;
		addControl(); // GUI 설계 함수
		addEvent(); // 이벤트 처리 함수
	}

	private void addEvent() {
		btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelect(e);
			}
		});

		buttonAfri.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count1;
				String countMenu = String.valueOf(count1);
				labelCount.setText(countMenu);
				lblSelect.setText(lblAfri.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		buttonBunda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count2;
				String countMenu = String.valueOf(count2);
				labelCount.setText(countMenu);
				lblSelect.setText(lblBundaberg.getText());
				count1 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		buttonCoca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count3;
				String countMenu = String.valueOf(count3);
				labelCount.setText(countMenu);
				lblSelect.setText(lblCocacola.getText());
				count2 = 0;
				count1 = 0;
				count4 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		buttonNgo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count4;
				String countMenu = String.valueOf(count4);
				labelCount.setText(countMenu);
				lblSelect.setText(lblngo.getText());
				count2 = 0;
				count3 = 0;
				count1 = 0;
				count5 = 0;
				count6 = 0;
			}
		});

		buttonPer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count5;
				String countMenu = String.valueOf(count5);
				labelCount.setText(countMenu);
				lblSelect.setText(lblPerrier.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count1 = 0;
				count6 = 0;
			}
		});

		buttonSprite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				++count6;
				String countMenu = String.valueOf(count6);
				labelCount.setText(countMenu);
				lblSelect.setText(lblSprite.getText());
				count2 = 0;
				count3 = 0;
				count4 = 0;
				count5 = 0;
				count1 = 0;
			}
		});

	}

	protected void btnSelect(ActionEvent e) { // Dink에서 문걸을 주문하게 된다면 MenuView의 table에 들어갈것이다.
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

		setBounds(100, 100, 490, 579);
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
		GroupLayout gl_rootPane = new GroupLayout(rootPane);
		gl_rootPane.setHorizontalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addGroup(gl_rootPane
				.createSequentialGroup()
				.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rootPane.createSequentialGroup().addGap(162)
								.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_rootPane.createSequentialGroup().addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblSelect,
														GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_rootPane.createSequentialGroup()
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 44,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(labelCount))))
						.addGroup(gl_rootPane.createSequentialGroup().addContainerGap().addComponent(selectPanel,
								GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rootPane.createSequentialGroup().addGap(179).addComponent(btnSelect,
								GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(24, Short.MAX_VALUE)));
		gl_rootPane.setVerticalGroup(gl_rootPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rootPane.createSequentialGroup()
						.addComponent(selectPanel, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_rootPane.createParallelGroup(Alignment.LEADING).addComponent(lblSelect)
								.addGroup(gl_rootPane.createSequentialGroup().addComponent(lblNewLabel).addGap(8)
										.addGroup(gl_rootPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(label_1).addComponent(labelCount))))
						.addGap(18).addComponent(btnSelect, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE).addGap(6)));
		buttonAfri = new JButton(new ImageIcon("images/nuoc_ngot/afri_m.jpg"));

		buttonPer = new JButton(new ImageIcon("images/nuoc_ngot/perrier_m.jpg"));
		// ten va anh menu
		buttonBunda = new JButton(new ImageIcon("images/nuoc_ngot/bunda_m.jpg"));

		buttonCoca = new JButton(new ImageIcon("images/nuoc_ngot/cocacola_m.jpg"));

		buttonNgo = new JButton(new ImageIcon("images/nuoc_ngot/ngo_m.jpg"));

		buttonSprite = new JButton(new ImageIcon("images/nuoc_ngot/sprite_m.jpg"));

		lblAfri = new JLabel();
		lblBundaberg = new JLabel();
		lblCocacola = new JLabel();
		lblPerrier = new JLabel();
		lblngo = new JLabel();
		lblSprite = new JLabel();

		for (Menu m : bill.menus) {
			if (m.getId().equalsIgnoreCase("m1")) {
				lblAfri.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m2")) {
				lblBundaberg.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m3")) {
				lblCocacola.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m5")) {
				lblPerrier.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m4")) {
				lblngo.setText(m.getName());
			}
			if (m.getId().equalsIgnoreCase("m6")) {
				lblSprite.setText(m.getName());
			}
		}

		lblAfri_1 = new JLabel();

		lblPerrier_1 = new JLabel();

		lblBundaberg_1 = new JLabel();

		lblCocacola_1 = new JLabel();

		lblngo_1 = new JLabel();

		lblSprite_1 = new JLabel();
		
		for(Menu m : bill.menus) {
			if(m.getId().equalsIgnoreCase("m1")) {
				lblAfri_1.setText(String.valueOf(m.getPrice()));
			}
			if(m.getId().equalsIgnoreCase("m2")) {
				lblBundaberg_1.setText(String.valueOf(m.getPrice()));
			}
			if(m.getId().equalsIgnoreCase("m3")) {
				lblCocacola_1.setText(String.valueOf(m.getPrice()));
			}
			if(m.getId().equalsIgnoreCase("m5")) {
				lblPerrier_1.setText(String.valueOf(m.getPrice()));
			}
			if(m.getId().equalsIgnoreCase("m4")) {
				lblngo_1.setText(String.valueOf(m.getPrice()));
			}
			if(m.getId().equalsIgnoreCase("m6")) {
				lblSprite_1.setText(String.valueOf(m.getPrice()));
			}
		}

		GroupLayout gl_selectPanel = new GroupLayout(selectPanel);
		gl_selectPanel.setHorizontalGroup(
			gl_selectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selectPanel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonAfri, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPer, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_selectPanel.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAfri_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAfri, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_selectPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonBunda, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonNgo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_selectPanel.createSequentialGroup()
							.addGap(66)
							.addComponent(lblBundaberg_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_selectPanel.createSequentialGroup()
							.addGap(55)
							.addComponent(lblBundaberg, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_selectPanel.createSequentialGroup()
							.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(buttonCoca, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonSprite, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addGap(22))
						.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(Alignment.TRAILING, gl_selectPanel.createSequentialGroup()
								.addGap(12)
								.addComponent(lblCocacola_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(44))
							.addGroup(Alignment.TRAILING, gl_selectPanel.createSequentialGroup()
								.addComponent(lblCocacola, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addGap(33)))))
				.addGroup(gl_selectPanel.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPerrier, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPerrier_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addGap(94)
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblngo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblngo_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblSprite, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSprite_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_selectPanel.setVerticalGroup(
			gl_selectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selectPanel.createSequentialGroup()
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_selectPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(buttonAfri, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonBunda, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonCoca, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAfri, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCocacola, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBundaberg, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBundaberg_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCocacola_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonPer, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonNgo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonSprite, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_selectPanel.createSequentialGroup()
							.addGap(176)
							.addComponent(lblAfri_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPerrier, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblngo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSprite, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_selectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPerrier_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblngo_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSprite_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		selectPanel.setLayout(gl_selectPanel);
		rootPane.setLayout(gl_rootPane);
	}
}
