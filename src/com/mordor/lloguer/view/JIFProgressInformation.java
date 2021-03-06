package com.mordor.lloguer.view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JIFProgressInformation extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SwingWorker<?, ?> task;
	private JButton btnButton;
	private JProgressBar progressBar;
	private JLabel lblInformation;

	public JIFProgressInformation(SwingWorker<?, ?> task, String info) {

		setFrameIcon(new ImageIcon(JIFProgressInformation.class.getResource("/com/mordor/lloguer/assets/clock.png")));

		this.task = task;

		setResizable(false);
		setBounds(100, 100, 450, 220);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[25px][][][grow][][30px]"));

		lblInformation = new JLabel(info);
		lblInformation.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(lblInformation, "cell 0 1,alignx center");

		JLabel lblPleaseWait = new JLabel("Please wait");
		getContentPane().add(lblPleaseWait, "cell 0 2,alignx center");

		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		getContentPane().add(progressBar, "cell 0 4,growx");

		// Si no hay tarea asociada, se añade un boton Ok que solo cierra el JIF
		// En caso contrario, se añade un boton Cancel que cancela la tarea asociada.
		if (task == null) {
			btnButton = new JButton("Ok");
			btnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		} else {
			
			btnButton = new JButton("Cancel");
			btnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					task.cancel(true);
				}
			});
			
		}		

		getContentPane().add(btnButton, "cell 0 5,alignx center,aligny center");

	}

	public SwingWorker<?, ?> getTask() {
		return task;
	}

	public JButton getBtnButton() {
		return btnButton;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JLabel getLblInformation() {
		return lblInformation;
	}

}
