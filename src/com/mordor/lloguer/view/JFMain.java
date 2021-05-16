package com.mordor.lloguer.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.managers.style.StyleId;

import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JFMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLogin;
	private JDesktopPane desktopPane;
	private JButton btnLogout;
	private JButton btnEmployees;
	private JToolBar toolBar;
	private JMenuItem mntmPreferences;
	private JButton btnCustomers;
	private JButton btnVehicle;

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
				int opcion = JOptionPane.showInternalConfirmDialog(null, "Are you sure to exit?", "Exit", JOptionPane.YES_NO_OPTION);
				if (opcion==JOptionPane.YES_OPTION) System.exit(1);
			}
		});
		mntmExit.setMnemonic(KeyEvent.VK_E);
		
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic('E');
		menuBar.add(mnEdit);
		
		mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/settings.png")));
		mnEdit.add(mntmPreferences);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		//desktopPane.putClientProperty ( StyleId.STYLE_PROPERTY, StyleId.desktoppaneTransparent );
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		toolBar = new JToolBar(SwingConstants.HORIZONTAL);		
		toolBar.setEnabled(false);
        toolBar.putClientProperty ( StyleId.STYLE_PROPERTY, StyleId.toolbarAttachedNorth);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/login.png")));
		toolBar.add(btnLogin);
		
		btnLogout = new JButton("");
		btnLogout.setEnabled(false);
		btnLogout.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/logout.png")));
		toolBar.add(btnLogout);
		
		toolBar.addSeparator(new Dimension(20,10));
		
		btnEmployees = new JButton("");
		btnEmployees.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/employee.png")));
		toolBar.add(btnEmployees);
		
		btnCustomers = new JButton("");
		btnCustomers.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/customers.png")));
		toolBar.add(btnCustomers);
		
		btnVehicle = new JButton("");
		btnVehicle.setIcon(new ImageIcon(JFMain.class.getResource("/com/mordor/lloguer/assets/vehicle.png")));
		toolBar.add(btnVehicle);
	}
	

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
	public JMenuItem getMntmPreferences() {
		return mntmPreferences;
	}
	public JButton getBtnLogin() {
		return btnLogin;
	}
	public JButton getBtnLogout() {
		return btnLogout;
	}
	public JButton getBtnEmployees() {
		return btnEmployees;
	}	
	public JButton getBtnCustomers() {
		return btnCustomers;
	}
	public JToolBar getToolBar() {
		return toolBar;
	}
	public JButton getBtnVehicle() {
		return btnVehicle;
	}	
}
