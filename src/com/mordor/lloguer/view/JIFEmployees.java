package com.mordor.lloguer.view;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.table.WebTable;
import com.alee.managers.style.StyleId;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class JIFEmployees extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnClose;
	private WebComboBox cbAttribute;
	private WebComboBox cbDirection;

	/**
	 * Create the frame.
	 */
	public JIFEmployees() {
		setFrameIcon(new ImageIcon(JIFEmployees.class.getResource("/com/mordor/lloguer/assets/employee.png")));
		setMaximumSize(new Dimension(1000, 500));
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 1000, 454);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[fill][51.00,grow][33.00]"));
		
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior, "cell 0 0,grow");
		panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblSortBy = new JLabel("Sort by");
		panelSuperior.add(lblSortBy);
		
		cbAttribute = new WebComboBox(StyleId.comboboxUndecorated);		
		cbAttribute.setModel(new DefaultComboBoxModel<String>(new String[] {"DNI", "Nombre", "Apellidos", "Domicilio", "CP", "email", "fechaNac", "Cargo"}));
		panelSuperior.add(cbAttribute);
		
		JSeparator separator = new JSeparator();
		separator.setVisible(true);
		separator.setPreferredSize(new Dimension(20, 0));
		panelSuperior.add(separator);
		
		JLabel lblDirection = new JLabel("Direction");
		panelSuperior.add(lblDirection);
				
		cbDirection = new WebComboBox(StyleId.comboboxUndecorated);		
		cbDirection.setModel(new DefaultComboBoxModel(new String[] {"Ascending", "Descending"}));
		panelSuperior.add(cbDirection);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, "cell 0 1,grow");
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		table = new WebTable();
//		table.setEditable(false);
		table.optimizeColumnWidths(true);
        table.setOptimizeRowHeight(true);
        
        
		scrollPane.setViewportView(table);
		
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, "cell 0 2,grow");
		panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAdd = new JButton("Add");
		panelInferior.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		panelInferior.add(btnDelete);
		
		btnClose = new JButton("Close");
		panelInferior.add(btnClose);

	}

	public WebTable getTable() {
		return table;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public WebComboBox getCbAttribute() {
		return cbAttribute;
	}

	public WebComboBox getCbDirection() {
		return cbDirection;
	}
	
}
