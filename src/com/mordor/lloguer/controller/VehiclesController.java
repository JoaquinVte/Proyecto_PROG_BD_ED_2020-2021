package com.mordor.lloguer.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
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
		
		for(Component jpv : view.getTabbedPane().getComponents())
			if(jpv instanceof JPVehicle) {
			   for(Component c : ((JPVehicle)jpv).getPanelSearch().getComponents()) {
				   if(c instanceof JTextField)
					   ((JTextField)c).getDocument().addDocumentListener(this);
				   else if(c instanceof JComboBox)
					   ((JComboBox)c).addActionListener(this);
			   }				   
			}
		
	}
	
	public void go() {
		
		SwingWorker<Void,Integer> task = new SwingWorker<Void,Integer>(){

			JIFProgressInformation jifpi = new JIFProgressInformation(this,"Retrieving data from server...");
			
			@Override
			protected Void doInBackground() throws Exception {
				
				MainController.addJInternalFrame(jifpi);
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
					

					MainController.addJInternalFrame(view);
				}
			}
			
		};
		
		task.execute();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private abstract class MyVehicleTableModel<T extends Vehicle> extends MyTableModel<T> {

		
		public MyVehicleTableModel(String[] COLUMN_NAMES, List<T> data) {
			super(COLUMN_NAMES, data);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 0: return data.get(row).getMatricula();
			case 1: return data.get(row).getMarca();
			case 2: return data.get(row).getColor();
			case 3: return data.get(row).getMotor();
			case 4: return data.get(row).getCilindrada();
			case 5: return data.get(row).getEstado();
			case 6: return data.get(row).getCarnet();		
			}
			return null;
		}
		
	}
	
	private class MyCarTableModel extends MyVehicleTableModel<Coche> {

		public MyCarTableModel(List data) {
			super(new String[] { "Matricula", "Marca", "Color", "Motor", "Cilindrada", "Estado", "Carnet", "Plazas", "Puertas" }, data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 7: return data.get(row).getNumPlazas();
			case 8: return data.get(row).getNumPuertas();	
			default: return super.getValueAt(row, col);
			}
		}
		
	}
	
	private class MyTruckTableModel extends MyVehicleTableModel<Camion> {

		public MyTruckTableModel(List data) {
			super(new String[] { "Matricula", "Marca", "Color", "Motor", "Cilindrada", "Estado", "Carnet", "MMA", "NumRuedas" }, data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 7: return data.get(row).getMMA();
			case 8: return data.get(row).getNumRuedas();		
			default: return super.getValueAt(row, col);
			}
		}
		
	}
	
	private class MyVanTableModel extends MyVehicleTableModel<Furgoneta> {

		public MyVanTableModel(List data) {
			super(new String[] { "Matricula", "Marca", "Color", "Motor", "Cilindrada", "Estado", "Carnet", "MMA" }, data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 7: return data.get(row).getMMA();	
			default: return super.getValueAt(row, col);
			}
		}
		
	}
	private class MyMinibusTableModel extends MyVehicleTableModel<Microbus> {

		public MyMinibusTableModel(List data) {
			super(new String[] { "Matricula", "Marca", "Color", "Motor", "Cilindrada", "Estado", "Carnet", "Medida", "NumPlazas" }, data);
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 7: return data.get(row).getMedida();
			case 8: return data.get(row).getNumPlazas();		
			default: return super.getValueAt(row, col);
			}
		}
		
	}
	
	private void update() {
		
		mctm.setNewData(cars.stream()
				.filter(null));
		
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {update();}

	@Override
	public void removeUpdate(DocumentEvent e) {update();}
	
	@Override
	public void changedUpdate(DocumentEvent e) {update();}

}
