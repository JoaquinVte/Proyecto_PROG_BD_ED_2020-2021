package com.mordor.lloguer.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.table.WebTable;
import com.mordor.lloguer.model.Employee;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.view.JDProgress;
import com.mordor.lloguer.view.JIFEmployees;

public class EmployeesController implements ActionListener {

	private JIFEmployees view;
	private Model model;
	private WebTable webtable;
	
	// Progress Dialog
	private JDProgress jdp;
	
	public EmployeesController(JIFEmployees view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		initialize();
	}
	
	private void initialize() {
		
		webtable = view.getTable();
		
		// Add ActionListener
		view.getCbAttribute().addActionListener(this);
		view.getCbDirection().addActionListener(this);
		
		
		// Add ActionCommand
		view.getCbAttribute().setActionCommand("Change search");
		view.getCbDirection().setActionCommand("Change search");
		
	}

	public void go() {
			
		showProgressDialog();
		
		DefaultTableModel dtm = (DefaultTableModel) webtable.getModel();
		
		SwingWorker<Void,Void> task = new SwingWorker<Void,Void>(){
			
			@Override
			protected Void doInBackground() throws Exception {
				
				List<Employee> employees = model.getEmployees().stream().sorted((e1,e2)->e1.getDNI().compareTo(e2.getDNI())).collect(Collectors.toList());
								
				for(Employee employee : employees) {
					dtm.addRow(new Object[] {
						employee.getDNI(),
						employee.getNombre(),
						employee.getApellidos(),
						employee.getDomicilio(),
						employee.getCP(),
						employee.getEmail(),
						employee.getFechaNac(),
						employee.getCargo()
					});
				}				
				
				return null;
			}
			
			@Override
			protected void done() {
				
				jdp.dispose();
				
				view.setVisible(true);
				
			}
			
		};
		
		task.execute();
		
	}
	
	private void showProgressDialog() {

		jdp = new JDProgress();

		// Centramos el dialog
		Dimension deskSize = view.getDesktopPane().getSize();
		Dimension ifSize = jdp.getSize();
		jdp.setLocation((deskSize.width - ifSize.width) / 2, (deskSize.height - ifSize.height) / 2);

		jdp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String command = arg0.getActionCommand();
		
		if(command.equals("Change search")) {
			changeQuery();
		}
		
	}

	private void changeQuery() {
		
		showProgressDialog();
		
		DefaultTableModel dtm = (DefaultTableModel) webtable.getModel();
		while(dtm.getRowCount()>0) {
			dtm.removeRow(0);
		}
		
		SwingWorker<Void,Void> task = new SwingWorker<Void,Void>(){
			
			@Override
			protected Void doInBackground() throws Exception {
				
				String field = view.getCbAttribute().getSelectedItem().toString();
				int direction = ((view.getCbDirection().getSelectedItem().toString().compareToIgnoreCase("Ascending")==0))?Model.ASCENDING:Model.DESCENDING;
				
				List<Employee> employees = model.getEmployeesByField(field, direction);
								
				for(Employee employee : employees) {
					dtm.addRow(new Object[] {
						employee.getDNI(),
						employee.getNombre(),
						employee.getApellidos(),
						employee.getDomicilio(),
						employee.getCP(),
						employee.getEmail(),
						employee.getFechaNac(),
						employee.getCargo()
					});
				}				
				
				return null;
			}
			
			@Override
			protected void done() {
				
				jdp.dispose();
				
				view.setVisible(true);
				
			}
			
		};
		
		task.execute();
		
	}
}
