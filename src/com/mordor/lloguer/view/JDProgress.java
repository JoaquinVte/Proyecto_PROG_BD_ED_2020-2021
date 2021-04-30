package com.mordor.lloguer.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDProgress extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public JDProgress() {
		setResizable(false);
		setBounds(100, 100, 450, 220);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[25px][][][grow][][30px]"));
		{
			JLabel lblRetrivingDataFrom = new JLabel("Retrieving data from server");
			lblRetrivingDataFrom.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
			getContentPane().add(lblRetrivingDataFrom, "cell 0 1,alignx center");
		}
		{
			JLabel lblPleaseWait = new JLabel("Please wait");
			getContentPane().add(lblPleaseWait, "cell 0 2,alignx center");
		}
		{
			JProgressBar progressBar = new JProgressBar();
			progressBar.setIndeterminate(true);
			getContentPane().add(progressBar, "cell 0 4,growx");
		}
		{
			JButton btnNewButton = new JButton("Ok");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			getContentPane().add(btnNewButton, "cell 0 5,alignx center,aligny center");
		}
	}

}
