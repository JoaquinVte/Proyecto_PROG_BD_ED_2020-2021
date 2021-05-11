package com.mordor.lloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;

import com.alee.api.resource.Resource;
import com.alee.extended.date.WebDateField;
import com.alee.extended.svg.SvgIcon;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class JIFCustomer extends JInternalFrame {
	private JTextField textFieldEmail;
	private JTextField textFieldDNI;
	private WebDateField wdfBirthday;
	private JTextField textFieldCP;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldAddress;
	private JTextField textFieldClientId;


	/**
	 * Create the frame.
	 */
	public JIFCustomer() {
		setTitle("Customer");
		setFrameIcon(new ImageIcon(JIFCustomer.class.getResource("/com/mordor/lloguer/assets/user.png")));
		setClosable(true);
		setBounds(100, 100, 576, 513);
		getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior);
		panelSuperior.setLayout(new MigLayout("", "[grow][410px][grow]", "[grow][150px][grow]"));
		
		JLabel lblLicensePhoto = new JLabel();
		lblLicensePhoto.setPreferredSize(new Dimension(410, 150));
		lblLicensePhoto.setIcon(new ImageIcon(JIFCustomer.class.getResource("/com/mordor/lloguer/assets/default_license.png")));
		panelSuperior.add(lblLicensePhoto, "cell 1 1,alignx center,aligny center");
		
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior);
		panelInferior.setLayout(new MigLayout("", "[grow][][grow][][grow][]", "[][16.00][][][][][36.00]"));
		
		JLabel lblClientid = new JLabel("ClientId");
		panelInferior.add(lblClientid, "cell 1 0,alignx left");
		
		textFieldClientId = new JTextField();
		panelInferior.add(textFieldClientId, "cell 2 0,alignx left");
		textFieldClientId.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		panelInferior.add(lblDni, "cell 1 2,alignx left");
		
		textFieldDNI = new JTextField();
		panelInferior.add(textFieldDNI, "cell 2 2,growx");
		textFieldDNI.setColumns(10);
		
		JLabel lblEmail = new JLabel("email");
		panelInferior.add(lblEmail, "cell 3 2,alignx left");
		
		textFieldEmail = new JTextField();
		panelInferior.add(textFieldEmail, "cell 4 2,growx");
		textFieldEmail.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panelInferior.add(lblName, "cell 1 3,alignx left");
		
		textFieldName = new JTextField();
		panelInferior.add(textFieldName, "cell 2 3,growx");
		textFieldName.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday");
		panelInferior.add(lblBirthday, "cell 3 3,alignx left");
		
		wdfBirthday = new WebDateField();
		panelInferior.add(wdfBirthday, "cell 4 3,growx");
		
		JLabel lblSurname = new JLabel("Surname");
		panelInferior.add(lblSurname, "cell 1 4,alignx left");
		
		textFieldSurname = new JTextField();
		panelInferior.add(textFieldSurname, "cell 2 4,growx");
		textFieldSurname.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("Driving license");
		panelInferior.add(lblDrivingLicense, "cell 3 4,alignx left");
		
		JComboBox cbLicense = new JComboBox();
		panelInferior.add(cbLicense, "cell 4 4,alignx left");
		
		JLabel lblAddress = new JLabel("Address");
		panelInferior.add(lblAddress, "cell 1 5,alignx left");
		
		JLabel lblCp = new JLabel("CP");
		panelInferior.add(lblCp, "cell 3 5,alignx left");
		
		textFieldCP = new JTextField();
		panelInferior.add(textFieldCP, "cell 4 5,alignx left");
		textFieldCP.setColumns(10);
		
		textFieldAddress = new JTextField();
		panelInferior.add(textFieldAddress, "cell 2 5,growx");
		textFieldAddress.setColumns(10);
		
		JPanel panel = new JPanel();
		panelInferior.add(panel, "cell 0 6 5 1,grow");
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnAdd = new JButton("Add");
		panel.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel);

	}

}
