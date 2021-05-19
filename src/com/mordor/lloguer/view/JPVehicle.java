package com.mordor.lloguer.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.alee.laf.table.WebTable;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;

public class JPVehicle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebTable table;
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
		panelSearch.setLayout(new MigLayout("", "[][][][grow][][123.00][][51.00]", "[]"));
		
		JLabel lblNewLabel = new JLabel("Registration");
		panelSearch.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textFieldRegistration = new JTextField();
		textFieldRegistration.setText("");
		textFieldRegistration.getDocument().putProperty("owner", textFieldRegistration);
		panelSearch.add(textFieldRegistration, "cell 1 0,growx");
		textFieldRegistration.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		panelSearch.add(lblModel, "cell 2 0,alignx trailing");
		
		textFieldModel = new JTextField();
		textFieldModel.getDocument().putProperty("owner", textFieldModel);
		panelSearch.add(textFieldModel, "cell 3 0,growx");
		textFieldModel.setColumns(10);
		
		JLabel lblEngine = new JLabel("Engine");
		panelSearch.add(lblEngine, "cell 4 0,alignx trailing");
		
		comboBoxEngine = new JComboBox<String>();
		comboBoxEngine.setPreferredSize(new Dimension(150, 24));
		comboBoxEngine.setModel(new DefaultComboBoxModel(new String[] {"All"}));
		panelSearch.add(comboBoxEngine, "cell 5 0,alignx right");
		
		JLabel lblLicense = new JLabel("License");
		panelSearch.add(lblLicense, "cell 6 0,alignx trailing");
		
		comboBoxLicense = new JComboBox<String>();
		comboBoxLicense.setModel(new DefaultComboBoxModel(new String[] {"All", "A", "B", "C", "D", "E", "F", "Z"}));
		panelSearch.add(comboBoxLicense, "cell 7 0,alignx right");
		
		JPanel panelTable = new JPanel();
		add(panelTable, "cell 0 1,grow");
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new WebTable();
		table.setOptimizeRowHeight(true);
		scrollPane.setViewportView(table);

	}

	public WebTable getTable() {
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

	public boolean contains(Component c) {
		return c == table || c == textFieldRegistration || c == textFieldModel || c == comboBoxEngine
				|| c == comboBoxLicense;
	}

}
