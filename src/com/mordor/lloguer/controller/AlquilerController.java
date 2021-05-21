package com.mordor.lloguer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;

import com.mordor.lloguer.model.Alquiler;
import com.mordor.lloguer.model.Factura;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.model.Vehicle;
import com.mordor.lloguer.view.JIFAlquiler;
import com.mordor.lloguer.view.JIFProgressInformation;

public class AlquilerController {
	
	private JIFAlquiler view;
	private Model model;	
	 
	private ArrayList<Alquiler> alquileres;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Factura> facturas;
	
	private MyAquilerTableModel matm;
	
	
	public AlquilerController(JIFAlquiler view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		inicialize();
	}

	private void inicialize() {
		
		alquileres = new ArrayList<Alquiler>();
		vehicles = new ArrayList<Vehicle>();
		
		matm = new MyAquilerTableModel(alquileres,vehicles);
		view.getTableDetalles().setModel(matm);
		
	}
	
	public void go() {
				
		loadDataFromServer();
		
	}
	
	private void loadDataFromServer() {
		SwingWorker<Void, Integer> task = new SwingWorker<Void, Integer>() {

			JIFProgressInformation jifpi = new JIFProgressInformation(this, "Retrieving data from server...");

			@Override
			protected Void doInBackground() throws Exception {

				MainController.addJInternalFrame(jifpi);
				jifpi.getProgressBar().setMaximum(5);

				try {
					
					
//					vehicles.addAll(model.getCars());
//					this.publish(1);
//					vehicles.addAll(model.getTrucks());
//					this.publish(2);
//					vehicles.addAll(model.getVan());
//					this.publish(3);
//					vehicles.addAll(model.getMinibus());
//					this.publish(4);
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void process(List<Integer> chunks) {
				for (int number : chunks) {
					jifpi.getProgressBar().setValue(number);
					jifpi.getLblInformation().setText("Retrieving data from server..." + number + "/5");
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
	
	private class MyAquilerTableModel extends MyTableModel<Alquiler>{
		
		private List<Vehicle> dataVehiculo;

		public MyAquilerTableModel(List<Alquiler> dataAlquiler, List<Vehicle> dataVehiculo) {
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
