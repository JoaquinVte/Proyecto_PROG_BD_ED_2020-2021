package com.mordor.lloguer.view;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;

import com.alee.extended.date.WebDateField;
import com.mordor.lloguer.model.Customer;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFCustomer extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldEmail;
	private JTextField textFieldDNI;
	private WebDateField wdfBirthday;
	private JTextField textFieldCP;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldAddress;
	private JLabel lblLicensePhoto;
	private JComboBox<String> cbLicense;
	private JButton btnAccion;
	private JButton btnCancel;
	
	private Customer customer;
	private byte[] image;
	private JLabel lblClickOnThe;

	
	public JIFCustomer(Customer customer) {
		this();
		
		this.customer = customer;
		this.setImage(customer.getFoto());
		textFieldEmail.setText(customer.getEmail());
		textFieldDNI.setText(customer.getDNI());
		textFieldCP.setText(customer.getCP());
		textFieldName.setText(customer.getNombre());
		textFieldSurname.setText(customer.getApellidos());
		textFieldAddress.setText(customer.getDomicilio());
		cbLicense.setSelectedItem(customer.getCarnet());
		wdfBirthday.setDate(customer.getFechaNac());
	}
	
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
		
		lblLicensePhoto = new JLabel();
		lblLicensePhoto.setPreferredSize(new Dimension(410, 150));
		lblLicensePhoto.setIcon(new ImageIcon(JIFCustomer.class.getResource("/com/mordor/lloguer/assets/default_license.png")));
		panelSuperior.add(lblLicensePhoto, "cell 1 1,alignx center,aligny center");
		
		lblClickOnThe = new JLabel("Click on the picture to change it");
		panelSuperior.add(lblClickOnThe, "cell 1 2,alignx center");
		
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior);
		panelInferior.setLayout(new MigLayout("", "[grow][][grow][][grow][]", "[16.00][][][][][36.00]"));
		
		JLabel lblDni = new JLabel("DNI");
		panelInferior.add(lblDni, "cell 1 1,alignx left");
		
		textFieldDNI = new JTextField();
		panelInferior.add(textFieldDNI, "cell 2 1,growx");
		textFieldDNI.setColumns(10);
		
		JLabel lblEmail = new JLabel("email");
		panelInferior.add(lblEmail, "cell 3 1,alignx left");
		
		textFieldEmail = new JTextField();
		panelInferior.add(textFieldEmail, "cell 4 1,growx");
		textFieldEmail.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panelInferior.add(lblName, "cell 1 2,alignx left");
		
		textFieldName = new JTextField();
		panelInferior.add(textFieldName, "cell 2 2,growx");
		textFieldName.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday");
		panelInferior.add(lblBirthday, "cell 3 2,alignx left");
		
		wdfBirthday = new WebDateField();
		panelInferior.add(wdfBirthday, "cell 4 2,growx");
		
		JLabel lblSurname = new JLabel("Surname");
		panelInferior.add(lblSurname, "cell 1 3,alignx left");
		
		textFieldSurname = new JTextField();
		panelInferior.add(textFieldSurname, "cell 2 3,growx");
		textFieldSurname.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("Driving license");
		panelInferior.add(lblDrivingLicense, "cell 3 3,alignx left");
		
		cbLicense = new JComboBox<String>();
		cbLicense.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "Z"}));
		panelInferior.add(cbLicense, "cell 4 3,alignx left");
		
		JLabel lblAddress = new JLabel("Address");
		panelInferior.add(lblAddress, "cell 1 4,alignx left");
		
		JLabel lblCp = new JLabel("CP");
		panelInferior.add(lblCp, "cell 3 4,alignx left");
		
		textFieldCP = new JTextField();
		panelInferior.add(textFieldCP, "cell 4 4,alignx left");
		textFieldCP.setColumns(10);
		
		textFieldAddress = new JTextField();
		panelInferior.add(textFieldAddress, "cell 2 4,growx");
		textFieldAddress.setColumns(10);
		
		JPanel panel = new JPanel();
		panelInferior.add(panel, "cell 0 5 5 1,grow");
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAccion = new JButton("Add");
		panel.add(btnAccion);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancel);

	}

	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public JTextField getTextFieldDNI() {
		return textFieldDNI;
	}

	public WebDateField getWdfBirthday() {
		return wdfBirthday;
	}

	public JTextField getTextFieldCP() {
		return textFieldCP;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public JTextField getTextFieldSurname() {
		return textFieldSurname;
	}

	public JTextField getTextFieldAddress() {
		return textFieldAddress;
	}

	public JLabel getLblLicensePhoto() {
		return lblLicensePhoto;
	}

	public JComboBox<String> getCbLicense() {
		return cbLicense;
	}

	public JButton getBtnAction() {
		return btnAccion;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;

		if (image != null) {
			try {
				BufferedImage ima = null;
				InputStream in = new ByteArrayInputStream(image);

				ima = ImageIO.read(in);

				ImageIcon icono = new ImageIcon(ima);
				Image imageToResize = icono.getImage();
				Image nuevaResized = imageToResize.getScaledInstance(410, 150, java.awt.Image.SCALE_SMOOTH);
				
				lblLicensePhoto.setIcon(new ImageIcon(nuevaResized));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			lblLicensePhoto.setIcon(new ImageIcon(JIFCustomer.class.getResource("/com/mordor/lloguer/assets/default_license.png")));
		}
	}

}
