package com.mordor.lloguer.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.editors.WebDateEditor;
import com.mordor.lloguer.model.Employee;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.view.JDProgress;
import com.mordor.lloguer.view.JFEmployee;
import com.mordor.lloguer.view.JIFEmployees;

public class EmployeesController implements ActionListener, TableModelListener {

	private JIFEmployees view;
	private Model model;
	private WebTable webtable;

	// Progress Dialog
	private JDProgress jdp;

	// JInternalFrame
	private JFEmployee jifEmployee;

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
		view.getBtnAdd().addActionListener(this);
		view.getBtnDelete().addActionListener(this);
		view.getBtnClose().addActionListener(this);

		// Add ActionCommand
		view.getCbAttribute().setActionCommand("Change search");
		view.getCbDirection().setActionCommand("Change search");
		view.getBtnAdd().setActionCommand("Open employee form");
		view.getBtnDelete().setActionCommand("Delete employee");
		view.getBtnClose().setActionCommand("Close jifEmployee");

	}

	public void go() {

		showProgressDialog();

		SwingWorker<Void, Void> task = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				List<Employee> employees = model.getEmployees().stream()
						.sorted((e1, e2) -> e1.getDNI().compareTo(e2.getDNI())).collect(Collectors.toList());

				MyEmployeeTableModel metm = new MyEmployeeTableModel(employees);

				webtable.setModel(metm);

				metm.addTableModelListener(MainController.employeesController);

				webtable.setDefaultEditor(Date.class, new WebDateEditor());

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

		if (command.equals("Change search")) {
			changeQuery();
		} else if (command.equals("Open employee form")) {
			openEmployeeForm();
		} else if (command.equals("Add new employee")) {
			addNewEmployee();
		} else if (command.equals("Delete employee")) {
			deleteEmployee();
		} else if (command.equals("Cancel add new employee")) {
			cancelAddNewEmployee();
		}

	}

	private void deleteEmployee() {
		
		int option = JOptionPane.showConfirmDialog(view, "Are you sure yout want to remove the employee?", "Confirm",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {

			Employee employee = ((MyEmployeeTableModel)webtable.getModel()).getEmployeeAtRow(webtable.getSelectedRow());

			System.out.println(employee);
		}
		
	}

	private void cancelAddNewEmployee() {

		int option = JOptionPane.showConfirmDialog(view, "Are you sure to cancel?", "Confirm",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {

			jifEmployee.dispose();

		}

	}

	private void addNewEmployee() {

		int option = JOptionPane.showConfirmDialog(view, "Are you sure to add the new employee?", "Confirm",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {

			Employee employee;

			String DNI = jifEmployee.gettFDNI().getText();
			String nombre = jifEmployee.gettFName().getText().toString();
			String apellidos = jifEmployee.gettFSurname().getText().toString();
			String domicilio = jifEmployee.gettFAddress().getText().toString();
			String CP = jifEmployee.gettFCP().getText().toString();
			String email = jifEmployee.gettFEmail().getText().toString();
			Date fechaNac = null;
			String cargo = jifEmployee.gettFPosition().getText().toString();

			if (jifEmployee.getWdfBirthday().getDate() != null)
				fechaNac = new Date(jifEmployee.getWdfBirthday().getDate().getTime());

			employee = new Employee(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo);

			SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {

					boolean added = false;

					added = model.addEmployee(employee);

					return added;
				}

				@Override
				protected void done() {

					boolean added;

					try {
						added = get();

						if (added) {

							JOptionPane.showMessageDialog(jifEmployee, "Employee added sucessfully", "Information",
									JOptionPane.INFORMATION_MESSAGE);

							((MyEmployeeTableModel) webtable.getModel()).addRow(employee);
							jifEmployee.dispose();
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(jifEmployee, "The employee has not been added! Check the fields.", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			};
			task.execute();
		}

	}

	private void openEmployeeForm() {

		if (jifEmployee == null) {
			jifEmployee = new JFEmployee();

			jifEmployee.getBtnSave().addActionListener(this);
			jifEmployee.getBtnCancel().addActionListener(this);

			jifEmployee.getBtnSave().setActionCommand("Add new employee");
			jifEmployee.getBtnCancel().setActionCommand("Cancel add new employee");

			MainController.addJInternalFrame(jifEmployee);
			
			jifEmployee.setVisible(true);
		}

	}

	private void changeQuery() {

		showProgressDialog();

		SwingWorker<Void, Void> task = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				String field = view.getCbAttribute().getSelectedItem().toString();
				int direction = ((view.getCbDirection().getSelectedItem().toString()
						.compareToIgnoreCase("Ascending") == 0)) ? Model.ASCENDING : Model.DESCENDING;

				List<Employee> employees = model.getEmployeesByField(field, direction);

				MyEmployeeTableModel metm = new MyEmployeeTableModel(employees);

				webtable.setModel(metm);

				metm.addTableModelListener(MainController.employeesController);

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

	private class MyEmployeeTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private final String[] COLUMN_NAMES = { "DNI", "Nombre", "Apellidos", "Domicilio", "CP", "email", "fechaNac",
				"Cargo" };
		List<Employee> data;

		public MyEmployeeTableModel(List<Employee> data) {
			this.data = data;
		}

		public String getColumnName(int col) {
			return COLUMN_NAMES[col];
		}

		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 0)
				return false;
			else
				return true;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {

			switch (arg1) {
			case 0:
				return data.get(arg0).getDNI();
			case 1:
				return data.get(arg0).getNombre();
			case 2:
				return data.get(arg0).getApellidos();
			case 3:
				return data.get(arg0).getDomicilio();
			case 4:
				return data.get(arg0).getCP();
			case 5:
				return data.get(arg0).getEmail();
			case 6:
				return data.get(arg0).getFechaNac();
			case 7:
				return data.get(arg0).getCargo();
			default:
				return null;
			}
		}

		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 7:
				return String.class;
			case 6:
				return Date.class;
			default:
				return String.class;
			}
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

			switch (columnIndex) {
			case 0:
				data.get(rowIndex).setDNI(aValue.toString());
				break;
			case 1:
				data.get(rowIndex).setNombre(aValue.toString());
				break;
			case 2:
				data.get(rowIndex).setApellidos(aValue.toString());
				break;
			case 3:
				data.get(rowIndex).setDomicilio(aValue.toString());
				break;
			case 4:
				data.get(rowIndex).setCP(aValue.toString());
				break;
			case 5:
				data.get(rowIndex).setEmail(aValue.toString());
				break;
			case 6:
				data.get(rowIndex).setFechaNac(new Date(((java.util.Date) aValue).getTime()));
				break;
			case 7:
				data.get(rowIndex).setCargo(aValue.toString());
				break;
			}

			fireTableCellUpdated(rowIndex, columnIndex);
		}

		public Employee getRow(int row) {
			return data.get(row);
		}

		public void addRow(Employee employee) {
			data.add(employee);
			fireTableRowsInserted(data.size() - 1, data.size() - 1);
		}
		
		public Employee getEmployeeAtRow(int row) {
			if(row<0 || row>=data.size())
				return null;
			else
				return data.get(row);
		}

		public void removeRow(int row) {
			data.remove(row);
			this.fireTableRowsDeleted(row, row);
		}

	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		if (arg0.getType() == TableModelEvent.UPDATE) {
			MyEmployeeTableModel dtm = (MyEmployeeTableModel) webtable.getModel();
			if (model.updateEmployee(dtm.getRow(arg0.getFirstRow())))
				JOptionPane.showMessageDialog(view, "Employee updated", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
