package com.mordor.lloguer.view;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import com.alee.extended.image.WebImage;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.alee.managers.style.StyleId;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFPreferences extends JInternalFrame {
	private WebPasswordField tFPassword;
	private WebTextField tFUsername;
	private JTextField tFURL;
	private JTextField tFDriver;
	private JButton btnSave;

	/**
	 * Create the frame.
	 */
	public JIFPreferences() {
		setTitle("Preferences");
		setClosable(true);
		setFrameIcon(new ImageIcon(JIFPreferences.class.getResource("/com/mordor/lloguer/assets/settings.png")));
		setBounds(100, 100, 421, 247);
		getContentPane().setLayout(new MigLayout("", "[20px][80.00][grow][20px]", "[][25px][25px][25px][25px][10px][grow]"));
		
		JLabel lblDriver = new JLabel("Driver");
		getContentPane().add(lblDriver, "cell 1 1,alignx left");
		
		tFDriver = new JTextField();
		getContentPane().add(tFDriver, "cell 2 1,growx");
		tFDriver.setColumns(10);
		
		JLabel lblUrl = new JLabel("URL");
		getContentPane().add(lblUrl, "cell 1 2,alignx left");
		
		tFURL = new JTextField();
		getContentPane().add(tFURL, "cell 2 2,growx");
		tFURL.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		getContentPane().add(lblUsername, "cell 1 3,alignx left");
		
		tFUsername = new WebTextField();
		tFUsername.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(tFUsername, "cell 2 3,growx");
		tFUsername.setColumns(10);

		tFUsername.setTrailingComponent( new WebImage ( StyleId.of ( "trailing" ), new ImageIcon(JIFPreferences.class.getResource("/com/mordor/lloguer/assets/user.png")) ) );
		
		JLabel lblPassword = new JLabel("Password");
		getContentPane().add(lblPassword, "cell 1 4");
		
		tFPassword = new WebPasswordField();
		tFPassword.setHorizontalAlignment(SwingConstants.CENTER);
		tFPassword.setTrailingComponent ( new WebImage ( StyleId.of ( "trailing" ), new ImageIcon(JIFPreferences.class.getResource("/com/mordor/lloguer/assets/key.png")) ) );
		
		getContentPane().add(tFPassword, "cell 2 4,growx");
		tFPassword.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 1 6 2 1,alignx trailing,growy");
		
		btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnClose);

	}

	public WebPasswordField gettFPassword() {
		return tFPassword;
	}

	public WebTextField gettFUsername() {
		return tFUsername;
	}

	public JTextField gettFURL() {
		return tFURL;
	}

	public JTextField gettFDriver() {
		return tFDriver;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

}
