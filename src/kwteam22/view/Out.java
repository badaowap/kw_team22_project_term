package kwteam22.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{79, 79, 53, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 20, 29, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
				{
					lblNewLabel_1 = new JLabel(" ");
					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
					gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_1.gridx = 1;
					gbc_lblNewLabel_1.gridy = 0;
					contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
				}
		
				JLabel lblNewLabel = new JLabel("종류하시겠습니까?");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel.gridwidth = 2;
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		{
			cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
		}
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 2;
		contentPanel.add(cancelButton, gbc_cancelButton);
		{
			okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_okButton.gridx = 2;
		gbc_okButton.gridy = 2;
		contentPanel.add(okButton, gbc_okButton);
	}
}
