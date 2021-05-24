package com.mordor.lloguer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mordor.lloguer.model.Rent;
import com.mordor.lloguer.model.Customer;
import com.mordor.lloguer.model.Invoice;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.model.Vehicle;
import com.mordor.lloguer.view.JIFInvoice;
import com.mordor.lloguer.view.JIFProgressInformation;

public class InvoiceController implements ActionListener {
	
	private JIFInvoice view;
	private Model model;	
	 
	private ArrayList<Rent> alquileres;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Invoice> facturas;
	private ArrayList<Customer> clientes;
	
	private MyAquilerTableModel matm;
	
	
	public InvoiceController(JIFInvoice view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		inicialize();
	}

	private void inicialize() {
		
		alquileres = new ArrayList<Rent>();
		vehicles = new ArrayList<Vehicle>();
		
		matm = new MyAquilerTableModel(alquileres,vehicles);
		view.getTableDetalles().setModel(matm);
		
		view.getBtnNewInvoce().addActionListener(this);
		view.getBtnNewInvoce().setActionCommand("New invoice");
		
	}
	
	public void go() {
				
		loadDataFromServer();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		if(command.equals("New invoice")) {
			newInvoice();
		}
		
	}
	
	private void newInvoice() {

		String dni = JOptionPane.showInternalInputDialog(view, "Enter the customer's ID:");
		
		if(dni!=null) {
			String matricula = JOptionPane.showInternalInputDialog(view, "Enter the car registration:");
			
			//if(matricula)
		}
		
	}

	private void loadDataFromServer() {
		SwingWorker<Void, Integer> task = new SwingWorker<Void, Integer>() {

			JIFProgressInformation jifpi = new JIFProgressInformation(this, "Retrieving data from server...");

			@Override
			protected Void doInBackground() throws Exception {

				MainController.addJInternalFrame(jifpi);
				jifpi.getProgressBar().setMaximum(7);
				jifpi.getProgressBar().setIndeterminate(false);

				vehicles.addAll(model.getCars());
				this.publish(1);
				vehicles.addAll(model.getTrucks());
				this.publish(2);
				vehicles.addAll(model.getVan());
				this.publish(3);
				vehicles.addAll(model.getMinibus());
				this.publish(4);
				clientes.addAll(model.getCustomers());
				this.publish(5);
				facturas.addAll(model.getInvoices());
				this.publish(6);
				alquileres.addAll(model.getRents());
				this.publish(7);

				return null;
			}

			@Override
			protected void process(List<Integer> chunks) {
				for (int number : chunks) {
					jifpi.getProgressBar().setValue(number);
					jifpi.getLblInformation().setText("Retrieving data from server..." + number + "/7");
				}
			}

			@Override
			protected void done() {
				jifpi.dispose();

				if (!isCancelled()) {
															
					MainController.addJInternalFrame(view);
				}
			}

		};
		
		task.execute();
	}
	
	private class MyAquilerTableModel extends MyTableModel<Rent>{
		
		private List<Vehicle> dataVehiculo;

		public MyAquilerTableModel(List<Rent> dataAlquiler, List<Vehicle> dataVehiculo) {
			super(Arrays.asList("Matricula","Modelo","Precio","F.Inicio","F.Fin"), dataAlquiler);
			this.dataVehiculo = dataVehiculo;
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
				case 0: return data.get(row).getVehiculoMatricula();
				case 1: return dataVehiculo.stream().filter((v)->v.getMatricula().equals(data.get(row).getVehiculoMatricula())).findFirst().get().getMarca();
				case 2: return data.get(row).getPrecio();
				case 3: return data.get(row).getFechaInicio();
				case 4: return data.get(row).getFechaFin();			
			}
			return null;
		}
		
	}


}
