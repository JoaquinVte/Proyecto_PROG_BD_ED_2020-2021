package com.mordor.lloguer.view;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JIFVehicles extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnCancel;
	private JPVehicle panelCar;
	private JPVehicle panelVan;
	private JPVehicle panelTruck;
	private JPVehicle panelMinibus;
	private JButton btnSelect;

	/**
	 * Create the frame.
	 */
	public JIFVehicles() {
		setFrameIcon(new ImageIcon(JIFVehicles.class.getResource("/com/mordor/lloguer/assets/vehicle.png")));
		setResizable(true);
		setClosable(true);
		setTitle("Vehicle");
		setBounds(100, 100, 966, 662);
		getContentPane().setLayout(new MigLayout("", "[856px,grow]", "[441.00px,grow][]"));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		panelCar = new JPVehicle();
		tabbedPane.addTab("Car", null, panelCar, null);
		
		panelVan = new JPVehicle();
		tabbedPane.addTab("Van", null, panelVan, null);
		
		panelTruck = new JPVehicle();
		tabbedPane.addTab("Truck", null, panelTruck, null);
		
		panelMinibus = new JPVehicle();
		tabbedPane.addTab("Minibus", null, panelMinibus, null);
		
		JPanel panelButtons = new JPanel();
		getContentPane().add(panelButtons, "cell 0 1,grow");
		panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnSelect = new JButton("Select");
		btnSelect.setVisible(false);
		btnSelect.setEnabled(false);
		panelButtons.add(btnSelect);
		
		btnAdd = new JButton("Add");
		panelButtons.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		panelButtons.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		panelButtons.add(btnEdit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panelButtons.add(btnCancel);
		
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
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

	public JPVehicle getPanelCar() {
		return panelCar;
	}

	public JPVehicle getPanelVan() {
		return panelVan;
	}

	public JPVehicle getPanelTruck() {
		return panelTruck;
	}

	public JPVehicle getPanelMinibus() {
		return panelMinibus;
	}

	public JButton getBtnSelect() {
		return btnSelect;
	}

}
