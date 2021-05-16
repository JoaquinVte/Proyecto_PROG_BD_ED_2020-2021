package com.mordor.lloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class JIFJasper extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public JIFJasper() {
		setResizable(true);
		setFrameIcon(new ImageIcon(JIFJasper.class.getResource("/com/mordor/lloguer/assets/printer.png")));
		setMaximizable(true);
		setClosable(true);
		setTitle("Report");
		setBounds(100, 100, 959, 570);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

	}

}
