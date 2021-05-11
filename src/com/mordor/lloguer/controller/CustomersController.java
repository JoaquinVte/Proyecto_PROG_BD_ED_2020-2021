package com.mordor.lloguer.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.mordor.lloguer.model.Customer;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.view.JIFCustomers;
import com.mordor.lloguer.view.JIFProgressInformation;
import com.mordor.lloguer.view.JIFCustomer;

public class CustomersController implements ActionListener, DocumentListener, MouseListener {
	
	private JIFCustomers view;
	private Model model;
	
	private ArrayList<Customer> customers;
	private JIFProgressInformation jifProgress;
	private JIFCustomer jifCustomer;
	
	private JLabel photo;
	
	public CustomersController(JIFCustomers view, Model model) {
		super();
		
		this.view = view;
		this.model = model;
		
		inicialize();
	}
	
	private void inicialize() {
				
		// Add ActionListener
		view.getBtnAdd().addActionListener(this);
		view.getBtnDelete().addActionListener(this);
		view.getBtnEdit().addActionListener(this);
		view.getTextFSearchName().getDocument().addDocumentListener(this);
		view.getTextFSearchSurname().getDocument().addDocumentListener(this);
		view.getTxtFSearchDNI().getDocument().addDocumentListener(this);
		view.getCbSearchDrivingLicense().addActionListener(this);
		
		// Add ActionCommand
		view.getBtnAdd().setActionCommand("Open empty form customer");
		view.getBtnDelete().setActionCommand("Delete customer");
		view.getBtnEdit().setActionCommand("Open form customer to edit");
		view.getCbSearchDrivingLicense().setActionCommand("Update search");
		
		fillTable();
	}
	
	public void go() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		
		if(command.equals("Open empty form customer")) {
			openJIFCustomer();
		} else if(command.equals("Add customer")) {
			addCustomer();
		} else if(command.equals("Update search")) {
			update();
		} else if(command.equals("Open form customer to edit")) {
			openJIFCutomerToEdit();
		}
	}
	
	private void openJIFCutomerToEdit() {
		
		if (!MainController.isOpen(jifCustomer)) {

			MyCustomerTableModel mtm = (MyCustomerTableModel) view.getTable().getModel();
			Customer customer = mtm.getRow(view.getTable().getSelectedRow());

			jifCustomer = new JIFCustomer(customer);

			MainController.addJInternalFrame(jifCustomer);

			jifCustomer.getLblLicensePhoto().addMouseListener(this);
			jifCustomer.getBtnAdd().addActionListener(this);

			jifCustomer.getBtnAdd().setActionCommand("Add customer");
			photo = jifCustomer.getLblLicensePhoto(); // Guardamos la referencia de la etiqueta para identificar quien
														// lanza el evento de clic
			
		}

	}

	private void addCustomer() {
		
		Customer customer;

		String DNI = jifCustomer.getTextFieldDNI().getText();
		String name = jifCustomer.getTextFieldName().getText();
		String surname = jifCustomer.getTextFieldSurname().getText();
		String address = jifCustomer.getTextFieldAddress().getText();
		String CP = jifCustomer.getTextFieldCP().getText();
		String email = jifCustomer.getTextFieldEmail().getText();
		Date birthday = null;
		char license = jifCustomer.getCbLicense().getSelectedItem().toString().charAt(0);

		if (jifCustomer.getWdfBirthday().getDate() != null)
			birthday = new Date(jifCustomer.getWdfBirthday().getDate().getTime());
		
		customer = new Customer(0, DNI, name, surname, address, CP, email, birthday, license, jifCustomer.getImage());
		
		try {
			model.addCustomer(customer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openJIFCustomer() {
		
		if(!MainController.isOpen(jifCustomer)) {
			jifCustomer = new JIFCustomer();
			
			MainController.addJInternalFrame(jifCustomer);
			
			jifCustomer.getLblLicensePhoto().addMouseListener(this);
			jifCustomer.getBtnAdd().addActionListener(this);
			
			jifCustomer.getBtnAdd().setActionCommand("Add customer");
			photo = jifCustomer.getLblLicensePhoto(); // Guardamos la referencia de la etiqueta para identificar quien lanza el evento de clic
			
		}
		
	}

	private void fillTable() {

		SwingWorker<ArrayList<Customer>,Void> task = new SwingWorker<ArrayList<Customer>,Void>(){

			@Override
			protected ArrayList<Customer> doInBackground() throws Exception {
				
				return model.getCustomers();
			}
			
			@Override
			protected void done() {
				
				jifProgress.dispose();
				
				if(!isCancelled()) {
					
					try {
						customers = get();
						
						MyCustomerTableModel mctm = new MyCustomerTableModel(customers);
						view.getTable().setModel(mctm);
						
						MainController.addJInternalFrame(view);
						
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		};
		
		jifProgress = new JIFProgressInformation(task,"Retrieving data from server.");
		MainController.addJInternalFrame(jifProgress);
		task.execute();
		
		
	}
	
	private class MyCustomerTableModel extends MyTableModel<Customer> {

		public MyCustomerTableModel( List<Customer> data) {
			super(new String[]{"id", "DNI", "Nombre", "Apellidos", "Domicilio", "CP", "email", "fechaNac", "Carnet" }, data);
			// TODO Auto-generated constructor stub
		}
		
		public void setNewData(List<Customer> data) {
			super.data = data;
			fireTableDataChanged();
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			switch(arg1) {
				case 0: return data.get(arg0).getId();
				case 1: return data.get(arg0).getDNI();
				case 2: return data.get(arg0).getNombre();
				case 3:	return data.get(arg0).getApellidos();
				case 4: return data.get(arg0).getDomicilio();
				case 5:	return data.get(arg0).getCP();
				case 6:	return data.get(arg0).getEmail();
				case 7:	return data.get(arg0).getFechaNac();
				case 8:	return data.get(arg0).getCarnet();
				default: return null;
			}
		}	
		
	}
	
	private void update() {
		
		MyCustomerTableModel mctm = new MyCustomerTableModel(customers.stream()
				.filter((c)->c.getDNI().toUpperCase().contains(view.getTxtFSearchDNI().getText().toUpperCase()))
				.filter((c)-> c.getNombre().toUpperCase().contains(view.getTextFSearchName().getText().toUpperCase()))
				.filter((c)-> c.getApellidos().toUpperCase().contains(view.getTextFSearchSurname().getText().toUpperCase()))
				.filter((c)-> c.getCarnet() == view.getCbSearchDrivingLicense().getSelectedItem().toString().charAt(0) || view.getCbSearchDrivingLicense().getSelectedItem().toString().equals("All"))
				.collect(Collectors.toList()));
		
		view.getTable().setModel(mctm);
	}
	


	@Override
	public void insertUpdate(DocumentEvent e) {update();}

	@Override
	public void removeUpdate(DocumentEvent e) {update();}

	@Override
	public void changedUpdate(DocumentEvent e) {update();}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getComponent().equals(photo)) {
			
			JFileChooser jfch = new JFileChooser();
			int option = jfch.showOpenDialog(jifCustomer);
			
			if(option == JFileChooser.APPROVE_OPTION) {
				try {
				InputStream inte = new FileInputStream(jfch.getSelectedFile());
				byte[] imgFoto = new byte[(int) jfch.getSelectedFile().length()];				
				inte.read(imgFoto);
		
				SerialBlob fotoBLOB = new SerialBlob(imgFoto);
				
				InputStream in = new ByteArrayInputStream(imgFoto);
				BufferedImage image = ImageIO.read(in);
		
				ImageIcon icono = new ImageIcon(image);
		
				jifCustomer.setImage(imgFoto);
				
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}






















