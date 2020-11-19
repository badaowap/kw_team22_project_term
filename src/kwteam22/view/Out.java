package kwteam22.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Out extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private MenuView menuView;
	//private Admin admin;
	private BillView infor;
	private JLabel lblNewLabel_1;

	/**
	 * @wbp.parser.constructor
	 */
	public Out(JFrame parent, boolean modal) {
		this.setTitle("Exit system");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menuView = (MenuView) parent;
		menuView.setVisible(false);
		addControl();
		addEvent();
	}

	public Out(JDialog parent, boolean modal) {
		/*
		 * this.setTitle("Exit system"); admin = (Admin) parent;
		 * admin.setVisible(false); addControl(); addEvent();
		 */
	}
	
	public Out(JDialog parent) {
		this.setTitle("Exit system");
		infor = (BillView) parent;
		infor.setVisible(false);
		addControl();
		addEvent();
	}

	void addEvent() {
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOkEvent(e);
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelEvent(e);
			}
		});

	}

	protected void btnCancelEvent(ActionEvent e) {
		menuView.dispose();
		MenuView menu = new MenuView(false, MenuView.loginCus, null);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		this.dispose();
	}

	protected void btnOkEvent(ActionEvent e) {
		System.exit(0);
	}

	void addControl() {
		setBounds(100, 100, 330, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
				{
					lblNewLabel_1 = new JLabel(" ");
				}
		
				JLabel lblNewLabel = new JLabel("종료하시겠습니까?");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		{
			cancelButton = new JButton("Cancel");
			cancelButton.setFont(new Font("Dialog", Font.BOLD, 14));
			cancelButton.setBackground(Color.LIGHT_GRAY);
			cancelButton.setActionCommand("Cancel");
		}
		{
			okButton = new JButton("OK");
			okButton.setFont(new Font("Dialog", Font.BOLD, 14));
			okButton.setBackground(Color.LIGHT_GRAY);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(117)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(79)
							.addComponent(cancelButton)
							.addGap(5)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(65, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(99, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(73))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel_1)
					.addGap(5)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
