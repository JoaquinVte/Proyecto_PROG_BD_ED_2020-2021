package com.mordor.lloguer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mordor.lloguer.model.Customer;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.view.JIFCustomers;
import com.mordor.lloguer.view.JIFProgressInformation;

public class CustomersController implements ActionListener, DocumentListener {
	
	private JIFCustomers view;
	private Model model;
	
	private ArrayList<Customer> customers;
	private JIFProgressInformation jifProgress;
	
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
		view.getBtnAdd().setActionCommand("Add customer");
		view.getBtnDelete().setActionCommand("Delete customer");
		view.getBtnEdit().setActionCommand("Edit customer");
		view.getCbSearchDrivingLicense().setActionCommand("Update search");
		
		fillTable();
	}
	
	public void go() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		
		if(command.equals("Add customer")) {
			
		} else if(command.equals("Edit customer")) {
			
		} else if(command.equals("Update search")) {
			update();
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

}






















