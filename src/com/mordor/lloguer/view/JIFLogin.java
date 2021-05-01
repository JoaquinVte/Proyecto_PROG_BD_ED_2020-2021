package com.mordor.lloguer.view;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JProgressBar;

public class JIFLogin extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFieldLogin;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblError;
	private JProgressBar progressBar;

	/**
	 * Create the frame.
	 */
	public JIFLogin() {
		setClosable(true);
		setBounds(100, 100, 450, 269);
		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[40.00][50px][50px][50px][60px][10px][59.00,grow][30px]"));
		
		JLabel lblLogin = new JLabel("Member Login");
		lblLogin.setFont(new Font("DejaVu Sans", Font.PLAIN, 28));
		getContentPane().add(lblLogin, "cell 1 0,alignx center,aligny top");
		
		txtFieldLogin = new JTextField();
		txtFieldLogin.setHorizontalAlignment(JTextField.CENTER);
		txtFieldLogin.setToolTipText("DNI");
		getContentPane().add(txtFieldLogin, "cell 1 2,growx");
		txtFieldLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(JPasswordField.CENTER);
		getContentPane().add(passwordField, "cell 1 3,growx");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(lblError, "cell 1 4,growx");
				
		progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setIndeterminate(true);
		getContentPane().add(progressBar, "cell 1 5,growx");
		
		btnLogin = new JButton("Login");
		btnLogin.setMnemonic(KeyEvent.VK_ACCEPT);
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setFont(new Font("DejaVu Sans", Font.PLAIN, 24));
		getContentPane().add(btnLogin, "cell 1 6,grow");

	}

	public JTextField getTxtFieldLogin() {
		return txtFieldLogin;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JLabel getLblError() {
		return lblError;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
}
