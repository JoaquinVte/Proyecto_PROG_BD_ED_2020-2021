package com.mordor.lloguer.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.mordor.lloguer.model.Rent;
import com.alee.laf.table.editors.WebDateEditor;
import com.mordor.lloguer.model.Customer;
import com.mordor.lloguer.model.Invoice;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.model.Vehicle;
import com.mordor.lloguer.view.JIFInvoice;
import com.mordor.lloguer.view.JIFJasper;
import com.mordor.lloguer.view.JIFProgressInformation;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class InvoiceController implements ActionListener, TableModelListener {

	private JIFInvoice view;
	private Model model;

	private ArrayList<Rent> alquileres;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Invoice> facturas;
	private ArrayList<Customer> clientes;

	private InvoiceController invoiceController;

	private Invoice invoice;

	private MyAquilerTableModel matm;

	public InvoiceController(JIFInvoice view, Model model) {
		super();
		this.view = view;
		this.model = model;

		invoiceController = this;

		inicialize();
	}

	private void inicialize() {

		alquileres = new ArrayList<Rent>();
		vehicles = new ArrayList<Vehicle>();

		view.getTableDetalles().setDefaultEditor(Date.class, new WebDateEditor());

		view.getBtnNewInvoce().addActionListener(this);
		view.getBtnRemoveInvoice().addActionListener(this);
		view.getBtnNextInvoice().addActionListener(this);
		view.getBtnPreviousInvoice().addActionListener(this);
		view.getBtnRemoveDetail().addActionListener(this);
		view.getBtnAddDetail().addActionListener(this);
		view.getBtnPrint().addActionListener(this);
		view.getBtnPdf().addActionListener(this);

		view.getBtnNewInvoce().setActionCommand("New invoice");
		view.getBtnRemoveInvoice().setActionCommand("Remove invoice");
		view.getBtnNextInvoice().setActionCommand("Next invoice");
		view.getBtnPreviousInvoice().setActionCommand("Previous invoice");
		view.getBtnRemoveDetail().setActionCommand("Remove rent");
		view.getBtnAddDetail().setActionCommand("Add rent");
		view.getBtnPrint().setActionCommand("Open jasperviewer");
		view.getBtnPdf().setActionCommand("Create pdf file");

	}

	public void go() {

		loadDataFromServer();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		if (command.equals("New invoice")) {
			newInvoice();
		} else if (command.equals("Remove invoice")) {
			removeInvoice();
		} else if (command.equals("Next invoice")) {
			nextInvoice();
		} else if (command.equals("Previous invoice")) {
			previousInvoice();
		} else if (command.equals("Remove rent")) {
			removeRent();
		} else if (command.equals("Add rent")) {
			addRent();
		} else if (command.equals("Open jasperviewer")) {
			openJasperViewer();
		} else if (command.equals("Create pdf file")) {
			createPdfFile();
		}

	}

	private void createPdfFile() {

		JFileChooser jfc = new JFileChooser();
		int option = jfc.showSaveDialog(jfc);

		if (option == JFileChooser.APPROVE_OPTION) {
			String file = jfc.getSelectedFile().getAbsolutePath();
			if (!file.endsWith(".pdf")) {
				file += ".pdf";
			}
			MyCustomTask myCustomTask = new MyCustomTask(file);			
			myCustomTask.execute();
		}

	}

	private void openJasperViewer() {

		SwingWorker<JasperPrint, Void> task = new SwingWorker<JasperPrint, Void>() {

			JIFProgressInformation jifProgress;

			@Override
			protected JasperPrint doInBackground() throws Exception {

				jifProgress = new JIFProgressInformation(this, "Reading invoice from server.");
				MainController.addJInternalFrame(jifProgress);

				JasperPrint jasperPrint = null;

				try {

					// Path of your report source.
					String reportJRXML = "/com/mordor/lloguer/reports/reportFactura.jrxml";

					InputStream reportFile = null;
					reportFile = getClass().getResourceAsStream(reportJRXML);

					// Compile the jrxml file
					JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);

					// We pass the necessary parameters
					HashMap<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("paraWhere", "Factura.idfactura=" + invoice.getId());

					// Produce the report (fill the report with data)
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, model.getConnection());

				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return jasperPrint;
			}

			protected void done() {

				jifProgress.dispose();

				try {
					if (!isCancelled()) {

						JasperPrint jasperPrint = get();

						JRViewer jrViewer = new JRViewer(jasperPrint);
						jrViewer.setSize(new Dimension(500, 400));

						// Create the JInterFrame that contains the JRViewer
						JIFJasper jifj = new JIFJasper();
						jifj.add(jrViewer);

						MainController.addJInternalFrame(jifj);
					}
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};

		task.execute();

	}

	private void removeInvoice() {

		if (invoice == null) {
			JOptionPane.showMessageDialog(view, "There are not any invoice to remove", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			int option = JOptionPane.showConfirmDialog(view, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {

				SwingWorker<Void, Integer> task = new SwingWorker<Void, Integer>() {

					JIFProgressInformation jifProgress;

					@Override
					protected Void doInBackground() throws Exception {
						List<Rent> rents = alquileres.stream().filter((r) -> r.getFacturaId() == invoice.getId())
								.collect(Collectors.toList());

						jifProgress = new JIFProgressInformation(this, "Removing the invoice...");
						jifProgress.getProgressBar().setIndeterminate(false);
						jifProgress.getProgressBar().setMaximum(rents.size());

						MainController.addJInternalFrame(jifProgress);

						int i = 0;
						for (Rent rent : rents) {
							if (model.deleteRent(rent)) {
								alquileres.remove(rent);
								publish(i);
							}
						}

						return null;
					}

					@Override
					protected void process(List<Integer> chunks) {
						for (int number : chunks) {
							jifProgress.getProgressBar().setValue(number);
							jifProgress.getLblInformation().setText("Removed " + number + " rents");
							showCurrentInvoice();
						}
					}

					@Override
					protected void done() {

						jifProgress.dispose();
						if (!isCancelled()) {
							facturas.remove(invoice);
							invoice = facturas.get(0);
							showCurrentInvoice();
						} else {
							loadDataFromServer();
						}
					}

				};

				task.execute();
			}
		}

	}

	private void addRent() {

		String matricula = JOptionPane.showInternalInputDialog(view, "Enter the vehicle registration:");
		Vehicle car;

		if ((car = existeVehiculo(matricula)) != null) {

			SwingWorker<Invoice, Void> task = new SwingWorker<Invoice, Void>() {

				@Override
				protected Invoice doInBackground() throws Exception {

					Date today = new Date(System.currentTimeMillis());
					Date tomorrow;

					Calendar c = Calendar.getInstance();
					c.setTime(today);
					c.add(Calendar.DATE, 1);
					tomorrow = new Date(c.getTimeInMillis());

					Rent rent = new Rent(matricula, today, tomorrow, car.getPrecioDia());
					try {

						Customer cliente = clientes.stream()
								.filter((cli) -> cli.getIdCliente() == invoice.getClienteId()).findFirst().get();

						if (model.addRent(invoice, cliente, rent)) {

							Invoice i = model.getInvoice(invoice.getId());
							invoice.setFecha(i.getFecha());
							invoice.setImporteBase(i.getImporteBase());
							invoice.setImporteIva(i.getImporteIva());

							alquileres.add(model.getRents().stream()
									.filter((r) -> r.getIdAlquiler() == rent.getIdAlquiler()).findFirst().get());

							showCurrentInvoice();
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}

					return invoice;
				}

			};
			task.execute();

		} else if (matricula != null)
			JOptionPane.showMessageDialog(view, "The vehicle with that registration does not exists.", "Error",
					JOptionPane.ERROR_MESSAGE);

	}

	private void removeRent() {

		if (view.getTableDetalles().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(view, "You have to select a row before", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			int option = JOptionPane.showConfirmDialog(view, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				SwingWorker<Void, Void> task = new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {

						Rent rent = matm.getElementAtRow(view.getTableDetalles().getSelectedRow());
						if (model.deleteRent(rent)) {

							alquileres.remove(rent);
							facturas = model.getInvoices();

							if (!facturas.contains(invoice)) {
								invoice = facturas.get(0);
							} else {
								invoice = facturas.get(facturas.indexOf(invoice));
							}

							showCurrentInvoice();
						}

						return null;
					}

				};
				task.execute();
			}
		}

	}

	private void previousInvoice() {

		if (invoice != null) {

			int index = facturas.indexOf(invoice);

			if (index > 0) {
				invoice = facturas.get(index - 1);
				showCurrentInvoice();
			}
		}
	}

	private void nextInvoice() {

		if (invoice != null) {

			int index = facturas.indexOf(invoice);

			if (index < facturas.size() - 1) {
				invoice = facturas.get(index + 1);
				showCurrentInvoice();
			}
		}
	}

	private void showCurrentInvoice() {

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
			if (invoice.getImporteIva() != 0)
				impuestos = invoice.getImporteIva() - invoice.getImporteBase();

			impuestos = ((int) (impuestos * 100)) / 100f;
			view.getTxtFieldImpuestos().setText("" + impuestos);
			view.getTxtFieldTotal().setText("" + invoice.getImporteIva());

			matm = new MyAquilerTableModel(
					alquileres.stream().filter((a) -> a.getFacturaId() == invoice.getId()).collect(Collectors.toList()),
					vehicles);
			matm.addTableModelListener(this);
			view.getTableDetalles().setModel(matm);

		} else {

			view.getTxtFieldNombre().setText("");
			view.getTxtFieldApellidos().setText("");
			view.getTxtFieldDNI().setText("");

			view.getTxtFieldNumeroFactura().setText("");
			view.getWebDateFieldFechaFactura().setDate(null);
			view.getTxtFieldSuma().setText("");
			view.getTxtFieldImpuestos().setText("");
			view.getTxtFieldTotal().setText("");

			matm = new MyAquilerTableModel(new ArrayList<Rent>(), vehicles);
			view.getTableDetalles().setModel(matm);
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

	private Vehicle existeVehiculo(String matricula) {
		if (matricula == null)
			return null;
		else {
			Optional<Vehicle> optional = vehicles.stream()
					.filter((v) -> v.getMatricula().compareToIgnoreCase(matricula) == 0).findFirst();
			if (optional.isPresent())
				return optional.get();
			else
				return null;
		}
	}

	private void newInvoice() {

		String dni = JOptionPane.showInternalInputDialog(view, "Enter the customer's ID:").toUpperCase();

		if (existeCliente(dni)) {

			String matricula = JOptionPane.showInternalInputDialog(view, "Enter the vehicle registration:").toUpperCase();
			Vehicle car;

			if ((car = existeVehiculo(matricula)) != null) {

				SwingWorker<Invoice, Void> task = new SwingWorker<Invoice, Void>() {

					@Override
					protected Invoice doInBackground() throws Exception {

						Date today = new Date(System.currentTimeMillis());
						Date tomorrow;

						Calendar c = Calendar.getInstance();
						c.setTime(today);
						c.add(Calendar.DATE, 1);
						tomorrow = new Date(c.getTimeInMillis());

						Rent rent = new Rent(matricula, today, tomorrow, car.getPrecioDia());
						try {
							Invoice i = model.addInvoice(dni, rent);

							if (i != null) {

								facturas.add(i);
								invoice = i;

								alquileres.add(model.getRents().stream()
										.filter((r) -> r.getIdAlquiler() == rent.getIdAlquiler()).findFirst().get());

								showCurrentInvoice();
							}

						} catch (Exception e) {
							JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}

						return invoice;
					}

				};
				task.execute();

			} else if (matricula != null)
				JOptionPane.showMessageDialog(view, "The vehicle with that registration does not exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
		} else if (dni != null)
			JOptionPane.showMessageDialog(view, "The client with that identifier does not exist.", "Error",
					JOptionPane.ERROR_MESSAGE);

	}

	private void loadDataFromServer() {
		SwingWorker<Void, Integer> task = new SwingWorker<Void, Integer>() {

			JIFProgressInformation jifpi = new JIFProgressInformation(this, "Retrieving data from server...");

			@Override
			protected Void doInBackground() throws Exception {

				view.setVisible(false);
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

					showCurrentInvoice();

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
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex <= 2)
				return false;
			else
				return true;
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

			switch (columnIndex) {

			case 3:
				data.get(rowIndex).setFechaInicio(new Date(((java.util.Date) aValue).getTime()));
				break;
			case 4:
				data.get(rowIndex).setFechaFin(new Date(((java.util.Date) aValue).getTime()));
				break;
			}

			fireTableCellUpdated(rowIndex, columnIndex);
		}

		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
			case 1:
			case 2:
				return String.class;
			case 3:
			case 4:
				return Date.class;
			default:
				return String.class;
			}
		}

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

	@Override
	public void tableChanged(TableModelEvent arg0) {

		if (arg0.getType() == TableModelEvent.UPDATE) {
			SwingWorker<Void, Void> task = new SwingWorker<Void, Void>() {

				@Override
				protected Void doInBackground() throws Exception {

					try {
						Rent rent = matm.getElementAtRow(arg0.getFirstRow());

						if (model.updateRent(rent)) {

							Invoice i = model.getInvoice(rent.getFacturaId());
							Rent re = model.getRents().stream().filter((r) -> r.getIdAlquiler() == rent.getIdAlquiler())
									.findFirst().get();

							if (i != null) {

								invoice.setFecha(i.getFecha());
								invoice.setImporteBase(i.getImporteBase());
								invoice.setImporteIva(i.getImporteIva());
								showCurrentInvoice();
							}
							if (re != null) {
								rent.setPrecio(re.getPrecio());
							}

							showCurrentInvoice();

						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						// Reload rent
					}

					return null;
				}
			};
			System.out.println("actualizando");
			task.execute();
		}

	}

	private class MyCustomTask extends SwingWorker<JasperPrint, Void> {

		JIFProgressInformation jifProgress;
		private String file;
		
		public MyCustomTask(String file) {			
			this.file = file;
		}

		@Override
		protected JasperPrint doInBackground() throws Exception {

			jifProgress = new JIFProgressInformation(this, "Reading invoice from server.");
			MainController.addJInternalFrame(jifProgress);
			
			JasperPrint jasperPrint = null;

			try {

				// Path of your report source.
				String reportJRXML = "/com/mordor/lloguer/reports/reportFactura.jrxml";

				InputStream reportFile = null;
				reportFile = getClass().getResourceAsStream(reportJRXML);

				// Compile the jrxml file
				JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);

				// We pass the necessary parameters
				HashMap<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("paraWhere", "Factura.idfactura=" + invoice.getId());

				// Produce the report (fill the report with data)
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, model.getConnection());

			} catch (JRException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, "Documento no se ha generado\n" + e.getMessage(), "Error",
						JOptionPane.INFORMATION_MESSAGE, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return jasperPrint;
		}

		protected void done() {
			jifProgress.dispose();

			try {
				if (!isCancelled()) {

					JasperPrint jasperPrint = get();
					// Export pdf file
					JasperExportManager.exportReportToPdfFile(jasperPrint, file);
					JOptionPane.showMessageDialog(view, "Documento generado correctamente", "Info",
							JOptionPane.INFORMATION_MESSAGE, null);
				}

			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
