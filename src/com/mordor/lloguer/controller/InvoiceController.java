package com.mordor.lloguer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

	private Invoice invoice;

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

		matm = new MyAquilerTableModel(alquileres, vehicles);
		view.getTableDetalles().setModel(matm);

		view.getBtnNewInvoce().addActionListener(this);
		view.getBtnNextInvoice().addActionListener(this);
		view.getBtnPreviousInvoice().addActionListener(this);

		view.getBtnNewInvoce().setActionCommand("New invoice");
		view.getBtnNextInvoice().setActionCommand("Next invoice");
		view.getBtnPreviousInvoice().setActionCommand("Previous invoice");

	}

	public void go() {

		loadDataFromServer();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		if (command.equals("New invoice")) {
			newInvoice();
		} else if (command.equals("Next invoice")) {
			nextInvoice();
		} else if (command.equals("Previous invoice")) {
			previousInvoice();
		}

	}

	private void previousInvoice() {

		if (invoice != null) {
			
			int index = facturas.indexOf(invoice);
			
			if (index > 0) {
				invoice = facturas.get(index - 1);
				showInvoice(invoice);
			}
		}
	}

	private void nextInvoice() {
		
		if (invoice != null) {

			int index = facturas.indexOf(invoice);

			if (index < facturas.size()-1) {
				invoice = facturas.get(index + 1);
				showInvoice(invoice);
			}
		}
	}

	private void showInvoice(Invoice invoice) {

		if (invoice != null) {

			Customer customer = clientes.stream().filter((c) -> c.getIdCliente() == invoice.getClienteId()).findFirst()
					.get();

			view.getTxtFieldNombre().setText(customer.getNombre());
			view.getTxtFieldApellidos().setText(customer.getApellidos());
			view.getTxtFieldDNI().setText(customer.getDNI());

			view.getTxtFieldNumeroFactura().setText("" + invoice.getId());
			view.getWebDateFieldFechaFactura().setDate(invoice.getFecha());
			view.getTxtFieldSuma().setText("" + invoice.getImporteBase());
			float impuestos = 0;
			if(invoice.getImporteIva()!=0)
				impuestos =  invoice.getImporteIva()-invoice.getImporteBase();
			view.getTxtFieldImpuestos().setText("" +impuestos);
			view.getTxtFieldTotal().setText("" + invoice.getImporteIva());

			matm.setNewData(alquileres.stream().filter((a) -> a.getFacturaId() == invoice.getId())
					.collect(Collectors.toList()));

		}
	}

	private boolean existeCliente(String dni) {
		if (dni == null)
			return false;
		else {
			Optional<Customer> optional = clientes.stream().filter((c) -> c.getDNI().compareToIgnoreCase(dni) == 0)
					.findFirst();
			return optional.isPresent();
		}
	}

	private boolean existeVehiculo(String matricula) {
		if (matricula == null)
			return false;
		else {
			Optional<Vehicle> optional = vehicles.stream()
					.filter((v) -> v.getMatricula().compareToIgnoreCase(matricula) == 0).findFirst();
			return optional.isPresent();
		}
	}

	private void newInvoice() {

		String dni = JOptionPane.showInternalInputDialog(view, "Enter the customer's ID:");

		if (existeCliente(dni)) {

			String matricula = JOptionPane.showInternalInputDialog(view, "Enter the vehicle registration:");

			if (existeVehiculo(matricula)) {
				Rent rent = new Rent(matricula, new Date(System.currentTimeMillis()),
						new Date(System.currentTimeMillis()), 11);
				try {
					model.addInvoice(dni, rent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
				JOptionPane.showMessageDialog(view, "The vehicle with that registration does not exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(view, "The client with that identifier does not exist.", "Error",
					JOptionPane.ERROR_MESSAGE);

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
				clientes = model.getCustomers();
				this.publish(5);
				facturas = model.getInvoices();
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

					Optional<Invoice> optional = facturas.stream().findFirst();

					if (optional.isPresent()) {
						invoice = optional.get();
					} else
						invoice = null;

					showInvoice(invoice);

					MainController.addJInternalFrame(view);

				}
			}

		};

		task.execute();
	}

	private class MyAquilerTableModel extends MyTableModel<Rent> {

		private List<Vehicle> dataVehiculo;

		public MyAquilerTableModel(List<Rent> dataAlquiler, List<Vehicle> dataVehiculo) {
			super(Arrays.asList("Matricula", "Modelo", "Precio", "F.Inicio", "F.Fin"), dataAlquiler);
			this.dataVehiculo = dataVehiculo;
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 0:
				return data.get(row).getVehiculoMatricula();
			case 1:
				return dataVehiculo.stream()
						.filter((v) -> v.getMatricula().equals(data.get(row).getVehiculoMatricula())).findFirst().get()
						.getMarca();
			case 2:
				return data.get(row).getPrecio();
			case 3:
				return data.get(row).getFechaInicio();
			case 4:
				return data.get(row).getFechaFin();
			}
			return null;
		}

	}

}
