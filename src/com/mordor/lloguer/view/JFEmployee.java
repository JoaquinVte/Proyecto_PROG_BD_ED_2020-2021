package com.mordor.lloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.extended.date.WebDateField;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JFEmployee extends JInternalFrame {
	private JTextField tFDNI;
	private JTextField tFName;
	private JTextField tFSurname;
	private JTextField tFAddress;
	private JTextField tFCP;
	private JTextField tFEmail;
	private WebDateField wdfBirthday;
	private JTextField tFPosition;
	private JButton btnSave;
	private JButton btnCancel;

	/**
	 * Create the frame.
	 */
	public JFEmployee() {
		setTitle("Employee");
		setBounds(100, 100, 413, 386);
		
		JPanel panelData = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelData, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelData, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelData.setLayout(new MigLayout("", "[10px][104.00][grow][10px]", "[][][][][][][][][][][grow]"));
		
		JLabel lblDni = new JLabel("DNI");
		panelData.add(lblDni, "cell 1 1,alignx left");
		
		tFDNI = new JTextField();
		panelData.add(tFDNI, "cell 2 1,growx");
		tFDNI.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panelData.add(lblName, "cell 1 2,alignx left");
		
		tFName = new JTextField();
		panelData.add(tFName, "cell 2 2,growx");
		tFName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		panelData.add(lblSurname, "cell 1 3,alignx left");
		
		tFSurname = new JTextField();
		panelData.add(tFSurname, "cell 2 3,growx");
		tFSurname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		panelData.add(lblAddress, "cell 1 4,alignx left");
		
		tFAddress = new JTextField();
		panelData.add(tFAddress, "cell 2 4,growx");
		tFAddress.setColumns(10);
		
		JLabel lblCp = new JLabel("CP");
		panelData.add(lblCp, "cell 1 5,alignx left");
		
		tFCP = new JTextField();
		panelData.add(tFCP, "cell 2 5,growx");
		tFCP.setColumns(10);
		
		JLabel lblEmail = new JLabel("email");
		panelData.add(lblEmail, "cell 1 6,alignx left");
		
		tFEmail = new JTextField();
		panelData.add(tFEmail, "cell 2 6,growx");
		tFEmail.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday");
		panelData.add(lblBirthday, "cell 1 7,alignx left");
		
		wdfBirthday = new WebDateField();
		panelData.add(wdfBirthday, "cell 2 7,growx");
		
		JLabel lblPosition = new JLabel("Job position");
		panelData.add(lblPosition, "cell 1 8,alignx left");
		
		tFPosition = new JTextField();
		panelData.add(tFPosition, "cell 2 8,growx");
		tFPosition.setColumns(10);
		
		JPanel panelButtons = new JPanel();
		panelData.add(panelButtons, "cell 1 10 2 1,growx");
		panelButtons.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		
		btnSave = new JButton("Save");
		panelButtons.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		getContentPane().setLayout(groupLayout);

	}

	public JTextField gettFDNI() {
		return tFDNI;
	}

	public JTextField gettFName() {
		return tFName;
	}

	public JTextField gettFSurname() {
		return tFSurname;
	}

	public JTextField gettFAddress() {
		return tFAddress;
	}

	public JTextField gettFCP() {
		return tFCP;
	}

	public JTextField gettFEmail() {
		return tFEmail;
	}

	public WebDateField getWdfBirthday() {
		return wdfBirthday;
	}

	public JTextField gettFPosition() {
		return tFPosition;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
	
}
