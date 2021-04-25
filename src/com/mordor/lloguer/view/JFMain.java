package com.mordor.lloguer.view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JFMain extends JFrame {

	private JPanel contentPane;
	private JButton btnLogin;
	private JDesktopPane desktopPane;

	/**
	 * Create the frame.
	 */
	public JFMain() {
		setTitle("Mordor LLoguer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 593);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/exit.png")));
		mntmExit.setToolTipText("Exit application");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		mntmExit.setMnemonic(KeyEvent.VK_E);
		
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic('E');
		menuBar.add(mnEdit);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/settings.png")));
		mnEdit.add(mntmPreferences);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/login.png")));
		toolBar.add(btnLogin);
		
		JButton btnLogout = new JButton("");
		btnLogout.setEnabled(false);
		btnLogout.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/logout.png")));
		toolBar.add(btnLogout);
	}
	

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}


	public JButton getBtnLogin() {
		return btnLogin;
	}
	
}
