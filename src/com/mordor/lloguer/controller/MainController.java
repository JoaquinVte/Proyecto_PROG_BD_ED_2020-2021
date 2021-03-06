package com.mordor.lloguer.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mordor.lloguer.config.MyConfig;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.view.JFMain;
import com.mordor.lloguer.view.JIFInvoice;
import com.mordor.lloguer.view.JIFCustomers;
import com.mordor.lloguer.view.JIFEmployees;
import com.mordor.lloguer.view.JIFLogin;
import com.mordor.lloguer.view.JIFPreferences;
import com.mordor.lloguer.view.JIFVehicles;

import net.sf.jasperreports.swing.JRViewer;

public class MainController implements ActionListener {

	private JFMain view;
	private Model model;
	
	// JIFrames
	private JIFLogin jifLogin;
	private JIFEmployees jifEmployees;
	private JIFCustomers jifCustomers;
	private JIFPreferences jifPreferences;

	// Controllers
	public static EmployeesController employeesController;
	
	// DesktopPane
	private static JDesktopPane desktopPane;

	public MainController(JFMain view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		desktopPane = view.getDesktopPane();

		inicialize();
	}

	private void inicialize() {

		// Hacemos que la aplicacion se vea maximizada
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		view.setMaximumSize(dim);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// view.setLocationRelativeTo(null);// Si establecieramos un tamaño determinado
		// con esto lo centramos en pantalla

		// Añadimos ActionListener
		view.getBtnLogin().addActionListener(this);
		view.getBtnLogout().addActionListener(this);
		view.getBtnEmployees().addActionListener(this);
		view.getBtnCustomers().addActionListener(this);
		view.getBtnVehicle().addActionListener(this);
		view.getMntmPreferences().addActionListener(this);
		view.getBtnInvoice().addActionListener(this);
		
		// Añadimos ActionCommand
		view.getBtnLogin().setActionCommand("Open JIFLogin");
		view.getBtnLogout().setActionCommand("Logout");
		view.getBtnEmployees().setActionCommand("Open JIFEmployees");
		view.getBtnCustomers().setActionCommand("Open JIFCustomers");
		view.getBtnVehicle().setActionCommand("Open JIFVehicle");
		view.getMntmPreferences().setActionCommand("Open JIFPreferences");
		view.getBtnInvoice().setActionCommand("Open JIFInvoice");

	}

	public void go() {
		view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String command = arg0.getActionCommand();

		if (command.equals("Open JIFLogin")) {
			openJIFLogin();
		} else if (command.equals("Open JIFPreferences")) {
			openJIFPreferences();
		} else if (command.equals("Save preferences")) {
			savePreferences();
		} else if (command.equals("Login")) {
			login();
		} else if (command.equals("Logout")) {
			logout();
		} else if (command.equals("Open JIFEmployees")) {
			openJIFEmployees();
		} else if (command.equals("Open JIFVehicle")) {
			openJIFVehicles();
		} else if (command.equals("Open JIFCustomers")) {
			openJIFCustomers();
		} else if (command.equals("Open JIFInvoice")) {
			openJIFInvoice();
		}

	}

	



	private void savePreferences() {

		MyConfig.getInstancia().setDriver(jifPreferences.gettFDriver().getText());
		MyConfig.getInstancia().setURL(jifPreferences.gettFURL().getText());
		MyConfig.getInstancia().setUsername(jifPreferences.gettFUsername().getText());
		MyConfig.getInstancia().setPassword(String.valueOf(jifPreferences.gettFPassword().getPassword()));

		JOptionPane.showMessageDialog(jifPreferences, "Your configuration has been saved", "Information",
				JOptionPane.INFORMATION_MESSAGE);
		jifPreferences.dispose();

	}

	private void openJIFPreferences() {
		if (!isOpen(jifPreferences)) {
			jifPreferences = new JIFPreferences();

			jifPreferences.gettFDriver().setText(MyConfig.getInstancia().getDriver());
			jifPreferences.gettFURL().setText(MyConfig.getInstancia().getURL());
			jifPreferences.gettFUsername().setText(MyConfig.getInstancia().getUsername());
			jifPreferences.gettFPassword().setText(MyConfig.getInstancia().getPassword());

			// Add Listener
			jifPreferences.getBtnSave().addActionListener(this);

			// Add command
			jifPreferences.getBtnSave().setActionCommand("Save preferences");

			centrar(jifPreferences);
			view.getDesktopPane().add(jifPreferences);
			jifPreferences.setVisible(true);
		}

	}

	private void openJIFLogin() {

		if (!isOpen(jifLogin)) {
			jifLogin = new JIFLogin();

			// Centramos el iframe
			centrar(jifLogin);
			
			Action action = new AbstractAction() {
			    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
			    public void actionPerformed(ActionEvent e)
			    {
			         login();
			    }
			};
			
			jifLogin.getTxtFieldLogin().setAction(action);
			jifLogin.getPasswordField().setAction(action);
			


			// Añadimos ActionListeners
			jifLogin.getBtnLogin().addActionListener(this);

			// Añadimos ActionCommands
			jifLogin.getBtnLogin().setActionCommand("Login");

			view.getDesktopPane().add(jifLogin);
			jifLogin.setVisible(true);
		}

	}

	private void openJIFEmployees() {
		if (!isOpen(jifEmployees)) {
			
			jifEmployees = new JIFEmployees();
			
			employeesController = new EmployeesController(jifEmployees, model);
			employeesController.go();
		}
	}
	private void openJIFCustomers() {
		
		if (!isOpen(jifEmployees)) {
			
			jifCustomers = new JIFCustomers();			
			CustomersController customersController = new CustomersController(jifCustomers, model);
			customersController.go();
		}
		
	}
	
	private void openJIFVehicles() {
		
		JIFVehicles jifVehicles = new JIFVehicles();
		VehiclesController vc = new VehiclesController(jifVehicles,model);
		vc.go();
		
	}
	
	private void openJIFInvoice() {
		
		JIFInvoice jifInvoice = new JIFInvoice();
		InvoiceController ac = new InvoiceController(jifInvoice,model);
		ac.go();
		
	}

	private void logout() {

		int opcion = JOptionPane.showConfirmDialog(view, "Are you sure to close the session?", "Logout",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			// Cerramos todos los JInternalFrames
			for (Component component : view.getDesktopPane().getComponents()) {
				if (component instanceof JInternalFrame)
					((JInternalFrame) component).dispose();
			}

			// Deshabilitamos todos los botones de la JToolBar
			for (Component component : view.getToolBar().getComponents()) {
				if (component instanceof JButton)
					component.setEnabled(false);
			}

			view.getBtnLogin().setEnabled(true);
		}

	}

	private void login() {

		

		String login = jifLogin.getTxtFieldLogin().getText();
		String password = String.valueOf(jifLogin.getPasswordField().getPassword());

		SwingWorker<Boolean, Void> taskLogin = new SwingWorker<Boolean, Void>() {
			/*
			 * Main task. Executed in background thread.
			 */
			@Override
			public Boolean doInBackground() {

				jifLogin.setError("");

				jifLogin.getProgressBar().setVisible(true);

				Boolean isLogin = false;

				try {

					isLogin = model.authenticate(login, password);

				} catch (Exception e) {
					jifLogin.setError(e.getMessage());
					//JOptionPane.showMessageDialog(jifLogin, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				return isLogin;

			}

			/*
			 * Executed in event dispatch thread
			 */
			public void done() {

				jifLogin.getProgressBar().setVisible(false);

				Boolean login;

				try {
					login = get();

					if (login) {

						// Login correcto segun rol se deberia habilitar las opciones pertinentes
						JOptionPane.showMessageDialog(jifLogin, "Login Successful", "Login",
								JOptionPane.INFORMATION_MESSAGE);
						view.getBtnLogin().setEnabled(false);
						view.getBtnLogout().setEnabled(true);
						view.getBtnEmployees().setEnabled(true);
						view.getBtnCustomers().setEnabled(true);
						view.getBtnVehicle().setEnabled(true);
						jifLogin.dispose();

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			}
		};
		taskLogin.execute();

	}
	
	static void addJInternalFrame(JInternalFrame jif) {		
		desktopPane.add(jif);
		centrar(jif);
		jif.setVisible(true);
		try {
			jif.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void addJasperPrint(JRViewer jrViewer) {
		
		desktopPane.add(jrViewer);
		
//		Dimension deskSize = desktopPane.getSize();
//		Dimension ifSize = jrViewer.getSize();
//		
//		jrViewer.setLocation((deskSize.width - ifSize.width) / 2, (deskSize.height - ifSize.height) / 2);
		
		jrViewer.setVisible(true);
		
	}

	static boolean isOpen(JInternalFrame jif) {
		boolean existe = false;
		JInternalFrame[] frames = desktopPane.getAllFrames();
		for (JInternalFrame frame : frames)
			if (frame == jif)
				existe = true;

		return existe;
	}

	static void centrar(JInternalFrame jif) {
		Dimension deskSize = desktopPane.getSize();
		Dimension ifSize = jif.getSize();
		jif.setLocation((deskSize.width - ifSize.width) / 2, 
				(deskSize.height - ifSize.height) / 2);
	}

}
