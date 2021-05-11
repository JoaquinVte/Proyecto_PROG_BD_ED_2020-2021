package com.mordor.lloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class JIFClientes extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFClientes frame = new JIFClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JIFClientes() {
		setBounds(100, 100, 450, 300);

	}

}
