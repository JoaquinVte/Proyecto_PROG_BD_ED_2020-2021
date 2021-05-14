package com.mordor.lloguer.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class JPVehicle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textFieldRegistration;
	private JTextField textFieldModel;
	private JComboBox<String> comboBoxEngine;
	private JComboBox<String> comboBoxLicense;
	private JPanel panelSearch;

	/**
	 * Create the panel.
	 */
	public JPVehicle() {
		setLayout(new MigLayout("", "[grow]", "[41.00][grow]"));
		
		panelSearch = new JPanel();
		add(panelSearch, "cell 0 0,grow");
		panelSearch.setLayout(new MigLayout("", "[][][][grow][][97.00][][51.00]", "[]"));
		
		JLabel lblNewLabel = new JLabel("Registration");
		panelSearch.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textFieldRegistration = new JTextField();
		textFieldRegistration.setText("");
		panelSearch.add(textFieldRegistration, "cell 1 0,growx");
		textFieldRegistration.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		panelSearch.add(lblModel, "cell 2 0,alignx trailing");
		
		textFieldModel = new JTextField();
		panelSearch.add(textFieldModel, "cell 3 0,growx");
		textFieldModel.setColumns(10);
		
		JLabel lblEngine = new JLabel("Engine");
		panelSearch.add(lblEngine, "cell 4 0,alignx trailing");
		
		comboBoxEngine = new JComboBox<String>();
		panelSearch.add(comboBoxEngine, "cell 5 0,growx");
		
		JLabel lblLicense = new JLabel("License");
		panelSearch.add(lblLicense, "cell 6 0,alignx trailing");
		
		comboBoxLicense = new JComboBox<String>();
		panelSearch.add(comboBoxLicense, "cell 7 0,growx");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

	public JTable getTable() {
		return table;
	}

	public JTextField getTextFieldRegistration() {
		return textFieldRegistration;
	}

	public JTextField getTextFieldModel() {
		return textFieldModel;
	}

	public JComboBox<String> getComboBoxEngine() {
		return comboBoxEngine;
	}

	public JComboBox<String> getComboBoxLicense() {
		return comboBoxLicense;
	}

	public JPanel getPanelSearch() {
		return panelSearch;
	}

}
