package com.mordor.lloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class JIFLogin extends JInternalFrame {
	private JTextField txtFieldLogin;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public JIFLogin() {
		setClosable(true);
		setBounds(100, 100, 450, 269);
		getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][50px][][][50px][59.00,grow][30px]"));
		
		JLabel lblLogin = new JLabel("Member Login");
		lblLogin.setFont(new Font("DejaVu Sans", Font.PLAIN, 28));
		getContentPane().add(lblLogin, "cell 1 0,alignx center");
		
		txtFieldLogin = new JTextField();
		txtFieldLogin.setHorizontalAlignment(JTextField.CENTER);
		txtFieldLogin.setToolTipText("DNI");
		getContentPane().add(txtFieldLogin, "cell 1 2,growx");
		txtFieldLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(JPasswordField.CENTER);
		getContentPane().add(passwordField, "cell 1 3,growx");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setFont(new Font("DejaVu Sans", Font.PLAIN, 24));
		getContentPane().add(btnLogin, "cell 1 5,grow");

	}

}
