package com.mordor.lloguer.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.view.JFMain;
import com.mordor.lloguer.view.JIFLogin;

public class MainController implements ActionListener {
	
	private JFMain view;
	private Model model;
	
	public MainController(JFMain view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		inicialize();
	}

	private void inicialize() {
		
		// Hacemos que la aplicacion se vea maximizada
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();		

		view.setMaximumSize(dim);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//view.setLocationRelativeTo(null);// Si establecieramos un tamaño determinado con esto lo centramos en pantalla
		
		// Añadimos ActionListener
		view.getBtnLogin().addActionListener(this);
		
		// Añadimos ActionCommand
		view.getBtnLogin().setActionCommand("Open JIFLogin");
		
	}
	
	public void go() {
		view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String command = arg0.getActionCommand();
		
		if(command.equals("Open JIFLogin")) {
			openJIFLogin();
		}
		
		
	}

	private void openJIFLogin() {
		
		JIFLogin jifLogin = new JIFLogin();
		
		// Centramos el iframe
		Dimension deskSize = view.getDesktopPane().getSize();
		Dimension ifSize = jifLogin.getSize();
		jifLogin.setLocation((deskSize.width - ifSize.width) / 2, (deskSize.height - ifSize.height) / 2);
		
		view.getDesktopPane().add(jifLogin);
		jifLogin.setVisible(true);
		
	}
	

}
