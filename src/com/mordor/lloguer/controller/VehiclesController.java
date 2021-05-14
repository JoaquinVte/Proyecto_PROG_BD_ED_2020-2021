package com.mordor.lloguer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingWorker;

import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.view.JIFProgressInformation;
import com.mordor.lloguer.view.JIFVehicles;

public class VehiclesController implements ActionListener {
	
	private JIFVehicles view;
	private Model model;
	
	public VehiclesController(JIFVehicles jifVehicle, Model model) {
		super();
		this.view = jifVehicle;
		this.model = model;
		
		initialize();
	}

	private void initialize() {
		
		view.getBtnAdd().addActionListener(this);
		view.getBtnDelete().addActionListener(this);
		view.getBtnEdit().addActionListener(this);
		
		
		
	}
	
	public void go() {
		
		SwingWorker<Void,Integer> task = new SwingWorker<Void,Integer>(){

			JIFProgressInformation jifpi = new JIFProgressInformation(this,"Retrieving data from server...");
			
			@Override
			protected Void doInBackground() throws Exception {
				
				MainController.addJInternalFrame(jifpi);
				jifpi.getProgressBar().setMaximum(4);
				
				
				
				
				
				
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
		        
		     }
			
		};
		
		MainController.addJInternalFrame(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
