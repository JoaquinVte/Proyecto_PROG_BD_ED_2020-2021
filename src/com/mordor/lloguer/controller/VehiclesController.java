package com.mordor.lloguer.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mordor.lloguer.model.Camion;
import com.mordor.lloguer.model.Coche;
import com.mordor.lloguer.model.Furgoneta;
import com.mordor.lloguer.model.Microbus;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.model.Vehicle;
import com.mordor.lloguer.view.JIFProgressInformation;
import com.mordor.lloguer.view.JIFVehicles;
import com.mordor.lloguer.view.JPVehicle;

public class VehiclesController implements ActionListener, DocumentListener {

	private JIFVehicles view;
	private Model model;

	private ArrayList<Coche> cars;
	private ArrayList<Camion> trucks;
	private ArrayList<Furgoneta> vans;
	private ArrayList<Microbus> minibus;

	private MyCarTableModel mctm;
	private MyTruckTableModel mttm;
	private MyVanTableModel mvtm;
	private MyMinibusTableModel mmtm;

	public VehiclesController(JIFVehicles jifVehicle, Model model) {
		super();
		this.view = jifVehicle;
		this.model = model;

		initialize();
	}

	private void initialize() {

		mctm = new MyCarTableModel(new ArrayList<Coche>());
		mttm = new MyTruckTableModel(new ArrayList<Camion>());
		mvtm = new MyVanTableModel(new ArrayList<Furgoneta>());
		mmtm = new MyMinibusTableModel(new ArrayList<Microbus>());

		view.getPanelCar().getTable().setModel(mctm);
		view.getPanelMinibus().getTable().setModel(mmtm);
		view.getPanelTruck().getTable().setModel(mttm);
		view.getPanelVan().getTable().setModel(mvtm);

		view.getBtnAdd().addActionListener(this);
		view.getBtnDelete().addActionListener(this);
		view.getBtnEdit().addActionListener(this);

		view.getBtnAdd().setActionCommand("Open the vehicle form for add");
		view.getBtnDelete().setActionCommand("Delete vehicle");
		view.getBtnEdit().setActionCommand("Open the vehicle form for edit");

		for (Component c : view.getTabbedPane().getComponents())
			if (c instanceof JPVehicle) {
				JPVehicle jpv = (JPVehicle) c;
				jpv.getTextFieldModel().getDocument().addDocumentListener(this);
				jpv.getTextFieldRegistration().getDocument().addDocumentListener(this);

				jpv.getComboBoxEngine().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							update(jpv.getComboBoxEngine());
						}
					}
				});
				jpv.getComboBoxLicense().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							update(jpv.getComboBoxLicense());
						}
					}
				});
			}
	}

	public void go() {

		SwingWorker<Void, Integer> task = new SwingWorker<Void, Integer>() {

			JIFProgressInformation jifpi = new JIFProgressInformation(this, "Retrieving data from server...");

			@Override
			protected Void doInBackground() throws Exception {

				MainController.addJInternalFrame(jifpi);
				jifpi.getProgressBar().setIndeterminate(false);
				jifpi.getProgressBar().setMaximum(4);

				try {
					cars = model.getCars();
					this.publish(1);
					trucks = model.getTrucks();
					this.publish(2);
					vans = model.getVan();
					this.publish(3);
					minibus = model.getMinibus();
					this.publish(4);

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void process(List<Integer> chunks) {
				for (int number : chunks) {
					jifpi.getProgressBar().setValue(number);
					jifpi.getLblInformation().setText("Retrieving data from server..." + number + "/4");
				}
			}

			@Override
			protected void done() {
				jifpi.dispose();

				if (!isCancelled()) {

					mctm.setNewData(cars);
					mvtm.setNewData(vans);
					mmtm.setNewData(minibus);
					mttm.setNewData(trucks);

					setDataToCbx(cars.stream().map((e) -> e.getMotor()).collect(Collectors.toSet()),
							view.getPanelCar().getComboBoxEngine());
					setDataToCbx(vans.stream().map((e) -> e.getMotor()).collect(Collectors.toSet()),
							view.getPanelVan().getComboBoxEngine());
					setDataToCbx(minibus.stream().map((e) -> e.getMotor()).collect(Collectors.toSet()),
							view.getPanelMinibus().getComboBoxEngine());
					setDataToCbx(trucks.stream().map((e) -> e.getMotor()).collect(Collectors.toSet()),
							view.getPanelTruck().getComboBoxEngine());

					setDataToCbx(cars.stream().map((e) -> e.getCarnet()).collect(Collectors.toSet()),
							view.getPanelCar().getComboBoxLicense());
					setDataToCbx(vans.stream().map((e) -> e.getCarnet()).collect(Collectors.toSet()),
							view.getPanelVan().getComboBoxLicense());
					setDataToCbx(minibus.stream().map((e) -> e.getCarnet()).collect(Collectors.toSet()),
							view.getPanelMinibus().getComboBoxLicense());
					setDataToCbx(trucks.stream().map((e) -> e.getCarnet()).collect(Collectors.toSet()),
							view.getPanelTruck().getComboBoxLicense());

					MainController.addJInternalFrame(view);
				}
			}
		};

		task.execute();
	}

	private void setDataToCbx(Set<String> data, JComboBox jcb) {
		DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<String>();

		dcbm.addElement("All");

		for (String d : data)
			dcbm.addElement(d);

		jcb.setModel(dcbm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command.equals("Update search")) {
			update(e.getSource());
		} else if (command.equals("Open the vehicle form for add")) {

			int index = view.getTabbedPane().getSelectedIndex();
			if (index == 0) {
				System.out.println("Car");
			} else if (index == 1) {
				System.out.println("Van");
			} else if (index == 2) {
				System.out.println("Truck");
			} else if (index == 3) {
				System.out.println("Minibus");
			}

		} else if (command.equals("Delete vehicle")) {

		} else if (command.equals("Open the vehicle form for edit")) {

		}
	}

	public List<?> filter(List<? extends Vehicle> data, JPVehicle jp) {

		String engineSelected = jp.getComboBoxEngine().getSelectedItem().toString();
		String licenseSelected = jp.getComboBoxLicense().getSelectedItem().toString();

		List<Vehicle> newData = new ArrayList<Vehicle>(data.stream()
				.filter((c) -> c.getMatricula().toUpperCase()
						.contains(jp.getTextFieldRegistration().getText().toUpperCase()))
				.filter((c) -> c.getMarca().toUpperCase().contains(jp.getTextFieldModel().getText().toUpperCase()))
				.filter((c) -> c.getMotor().toUpperCase().equals(engineSelected.toUpperCase())
						|| engineSelected.equals("All"))
				.filter((c) -> c.getCarnet().toUpperCase().equals(licenseSelected.toUpperCase())
						|| licenseSelected.equals("All"))
				.collect(Collectors.toList()));

		setDataToCbx(newData.stream().map((e) -> e.getMotor()).collect(Collectors.toSet()), jp.getComboBoxEngine());
		setDataToCbx(newData.stream().map((e) -> e.getCarnet()).collect(Collectors.toSet()), jp.getComboBoxLicense());

		ItemListener[] itemsCBXEngine = jp.getComboBoxEngine().getItemListeners();
		ItemListener[] itemsCBXLicense = jp.getComboBoxLicense().getItemListeners();
		
		for (ItemListener i : itemsCBXEngine)
			jp.getComboBoxEngine().removeItemListener(i);
		for (ItemListener i : itemsCBXLicense)
			jp.getComboBoxLicense().removeItemListener(i);
		

		jp.getComboBoxEngine().setSelectedItem(engineSelected);
		jp.getComboBoxLicense().setSelectedItem(licenseSelected);

		for (ItemListener i : itemsCBXEngine)
			jp.getComboBoxEngine().addItemListener(i);
		for (ItemListener i : itemsCBXLicense)
			jp.getComboBoxLicense().addItemListener(i);

		return newData;
	}

	private void update(Object component) {

		// Recover the JTextField that trigger the document event
		if (view.getPanelCar().contains(component)) {

			mctm.setNewData(filter(cars, view.getPanelCar()).stream().map(v -> (Coche) v).collect(Collectors.toList()));

		} else if (view.getPanelTruck().contains(component)) {

			mttm.setNewData(
					filter(trucks, view.getPanelTruck()).stream().map(v -> (Camion) v).collect(Collectors.toList()));

		} else if (view.getPanelVan().contains(component)) {

			mvtm.setNewData(
					filter(vans, view.getPanelVan()).stream().map(v -> (Furgoneta) v).collect(Collectors.toList()));

		} else if (view.getPanelMinibus().contains(component)) {

			mmtm.setNewData(filter(minibus, view.getPanelMinibus()).stream().map(v -> (Microbus) v)
					.collect(Collectors.toList()));

		}
	}

	private class MyVehicleTableModel<T extends Vehicle> extends MyTableModel<T> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyVehicleTableModel(List<String> COLUMN_NAMES, List<T> data) {
			super(new ArrayList<String>(Arrays
					.asList(new String[] { "Matricula", "Marca", "Color", "Motor", "Cilindrada", "Estado", "Carnet" })),
					data);

			columnNames.addAll(COLUMN_NAMES);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 0:
				return data.get(row).getMatricula();
			case 1:
				return data.get(row).getMarca();
			case 2:
				return data.get(row).getColor();
			case 3:
				return data.get(row).getMotor();
			case 4:
				return data.get(row).getCilindrada();
			case 5:
				return data.get(row).getEstado();
			case 6:
				return data.get(row).getCarnet();
			}
			return null;
		}
	}

	private class MyCarTableModel extends MyVehicleTableModel<Coche> {

		public MyCarTableModel(List<Coche> data) {
			super(Arrays.asList("Plazas", "Puertas"), data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 7:
				return data.get(row).getNumPlazas();
			case 8:
				return data.get(row).getNumPuertas();
			default:
				return super.getValueAt(row, col);
			}
		}

	}

	private class MyTruckTableModel extends MyVehicleTableModel<Camion> {

		public MyTruckTableModel(List<Camion> data) {
			super(Arrays.asList("MMA", "NumRuedas"), data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 7:
				return data.get(row).getMMA();
			case 8:
				return data.get(row).getNumRuedas();
			default:
				return super.getValueAt(row, col);
			}
		}
	}

	private class MyVanTableModel extends MyVehicleTableModel<Furgoneta> {

		public MyVanTableModel(List<Furgoneta> data) {
			super(Arrays.asList("MMA"), data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 7:
				return data.get(row).getMMA();
			default:
				return super.getValueAt(row, col);
			}
		}
	}

	private class MyMinibusTableModel extends MyVehicleTableModel<Microbus> {

		public MyMinibusTableModel(List<Microbus> data) {
			super(Arrays.asList("Medida", "NumPlazas"), data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 7:
				return data.get(row).getMedida();
			case 8:
				return data.get(row).getNumPlazas();
			default:
				return super.getValueAt(row, col);
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		update((Component) e.getDocument().getProperty("owner"));
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		update((Component) e.getDocument().getProperty("owner"));
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		update((Component) e.getDocument().getProperty("owner"));
	}

}
