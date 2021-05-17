package com.mordor.lloguer.view;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.laf.table.WebTable;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.Dimension;

public class JIFCustomers extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFSearchDNI;
	private WebTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnCancel;
	private JTextField textFSearchSurname;
	private JTextField textFSearchName;
	private JComboBox<String> cbSearchDrivingLicense;
	private JButton btnPrint;

	/**
	 * Create the frame.
	 */
	public JIFCustomers() {
		setFrameIcon(new ImageIcon(JIFCustomers.class.getResource("/com/mordor/lloguer/assets/customers.png")));
		setClosable(true);
		setTitle("Customers");
		setBounds(100, 100, 913, 471);
		
		JPanel panelSearch = new JPanel();
		
		JPanel panelTable = new JPanel();
		
		JPanel panelButtons = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
						.addComponent(panelTable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
						.addComponent(panelButtons, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelSearch, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAdd = new JButton("Add");
		panelButtons.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		panelButtons.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		panelButtons.add(btnEdit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelButtons.add(btnCancel);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new WebTable();
		table.optimizeColumnWidths(true);
        table.setRowHeight(30);
		scrollPane.setViewportView(table);
		panelSearch.setLayout(new MigLayout("", "[44.00px][124px][][grow][][grow][][][59.00]", "[30.00px]"));
		
		JLabel lblSearch = new JLabel("DNI");
		panelSearch.add(lblSearch, "cell 0 0,alignx left,aligny center");
		
		txtFSearchDNI = new JTextField();
		panelSearch.add(txtFSearchDNI, "cell 1 0,alignx left,aligny center");
		txtFSearchDNI.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panelSearch.add(lblName, "cell 2 0,alignx trailing,aligny center");
		
		textFSearchName = new JTextField();
		panelSearch.add(textFSearchName, "cell 3 0,growx,aligny center");
		textFSearchName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		panelSearch.add(lblSurname, "cell 4 0,alignx trailing,aligny center");
		
		textFSearchSurname = new JTextField();
		panelSearch.add(textFSearchSurname, "cell 5 0,growx,aligny center");
		textFSearchSurname.setColumns(10);
		
		JLabel lblType = new JLabel("driving license");
		panelSearch.add(lblType, "cell 6 0,alignx trailing,aligny center");
		
		cbSearchDrivingLicense = new JComboBox<String>();
		cbSearchDrivingLicense.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "A", "B", "C", "D", "E", "Z"}));
		panelSearch.add(cbSearchDrivingLicense, "flowx,cell 7 0,growx,aligny center");
		
		btnPrint = new JButton("");
		btnPrint.setPreferredSize(new Dimension(20, 20));
		btnPrint.setIcon(new ImageIcon(JIFCustomers.class.getResource("/com/mordor/lloguer/assets/printer.png")));
		panelSearch.add(btnPrint, "cell 8 0,alignx right,aligny center");
		getContentPane().setLayout(groupLayout);

	}

	public JTextField getTxtFSearchDNI() {
		return txtFSearchDNI;
	}

	public JTextField getTextFSearchSurname() {
		return textFSearchSurname;
	}

	public JTextField getTextFSearchName() {
		return textFSearchName;
	}

	public JComboBox<String> getCbSearchDrivingLicense() {
		return cbSearchDrivingLicense;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public WebTable getTable() {
		return table;
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}
}
