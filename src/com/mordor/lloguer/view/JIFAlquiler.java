package com.mordor.lloguer.view;

import javax.swing.JInternalFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.alee.extended.date.WebDateField;
import com.alee.laf.table.WebTable;

import javax.swing.JSeparator;

public class JIFAlquiler extends JInternalFrame {

	private JTextField txtFieldNombre;
	private JTextField txtFieldApellidos;
	private JTextField txtFieldDNI;
	private JTextField txtFieldNumeroFactura;
	private WebDateField webDateFieldFechaFactura;
	private JTextField txtFieldSuma;
	private JTextField txtFieldImpuestos;
	private JTextField txtFieldTotal;
	private JTable tableDetalles;
	private JButton btnPreviousInvoice;
	private JButton btnNextInvoice;
	private JButton btnNewInvoce;
	private JButton btnRemoveInvoice;
	private JButton btnClient;
	private JButton btnPrint;
	private JButton btnPdf;
	private JButton btnAddDetail;
	private JButton btnRemoveDetail;
	private JButton btnCerrar;
	private JButton btnCheck;

	/**
	 * Create the frame.
	 */
	public JIFAlquiler() {
		setFrameIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/invoice.png")));
		setTitle("Facturas");
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 810, 739);

		JPanel panelSuperior = new JPanel();

		JPanel panelBotonesInferior = new JPanel();
		panelBotonesInferior.setBorder(null);
		panelBotonesInferior.setForeground(new Color(128, 128, 128));

		JPanel panelCentral = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panelCentral,
								GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panelSuperior,
								GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(16).addComponent(panelBotonesInferior,
								GroupLayout.PREFERRED_SIZE, 766, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panelSuperior, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelCentral, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelBotonesInferior,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		JPanel panelDetalles = new JPanel();
		panelDetalles.setBorder(
				new TitledBorder(null, "Detalle", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(59, 59, 59)));
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addComponent(panelDetalles, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE));
		gl_panelCentral.setVerticalGroup(
				gl_panelCentral.createParallelGroup(Alignment.LEADING).addGroup(gl_panelCentral.createSequentialGroup()
						.addGap(5).addComponent(panelDetalles, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)));

		JPanel panelTabla = new JPanel();

		JPanel panelTotal = new JPanel();
		panelTotal.setBorder(
				new TitledBorder(null, "Resumen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panelTabla.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		tableDetalles = new WebTable();
		tableDetalles.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tableDetalles.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableDetalles);
		panelTotal.setLayout(new MigLayout("", "[][]", "[][][]"));

		JLabel lblTotal = new JLabel("Suma");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		panelTotal.add(lblTotal, "cell 0 0,alignx left");

		txtFieldSuma = new JTextField();
		txtFieldSuma.setEnabled(false);
		txtFieldSuma.setEditable(false);
		panelTotal.add(txtFieldSuma, "cell 1 0,growx");
		txtFieldSuma.setColumns(10);

		JLabel lblImpuestos = new JLabel("Impuestos");
		lblImpuestos.setHorizontalAlignment(SwingConstants.LEFT);
		panelTotal.add(lblImpuestos, "cell 0 1,alignx left");

		txtFieldImpuestos = new JTextField();
		txtFieldImpuestos.setEnabled(false);
		txtFieldImpuestos.setEditable(false);
		panelTotal.add(txtFieldImpuestos, "cell 1 1,growx");
		txtFieldImpuestos.setColumns(10);

		JLabel lblTotalImputestos = new JLabel("Total + Imputestos");
		lblTotalImputestos.setHorizontalAlignment(SwingConstants.LEFT);
		panelTotal.add(lblTotalImputestos, "cell 0 2,alignx trailing");

		txtFieldTotal = new JTextField();
		txtFieldTotal.setEnabled(false);
		txtFieldTotal.setEditable(false);
		panelTotal.add(txtFieldTotal, "cell 1 2,growx");
		txtFieldTotal.setColumns(10);
		panelDetalles.setLayout(new MigLayout("", "[668px,grow][]", "[301px][136px]"));
		panelDetalles.add(panelTabla, "cell 0 0,grow");

		JToolBar toolBar = new JToolBar();
		panelDetalles.add(toolBar, "cell 1 0 1 2,growy");
		toolBar.setForeground(new Color(192, 192, 192));
		toolBar.setBorder(null);
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);

		btnNewInvoce = new JButton("");
		btnNewInvoce.setToolTipText("Añadir factura");
		btnNewInvoce.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/invoiceAdd.png")));
		toolBar.add(btnNewInvoce);

		btnRemoveInvoice = new JButton("");
		btnRemoveInvoice.setToolTipText("Eliminar factura");
		btnRemoveInvoice
				.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/invoiceRemove.png")));
		toolBar.add(btnRemoveInvoice);

		toolBar.addSeparator(new Dimension(20,10));

		btnClient = new JButton("");
		btnClient.setToolTipText("Cambiar cliente de la factura");
		btnClient.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/usuarios.png")));
		toolBar.add(btnClient);
		
		btnCheck = new JButton("");
		btnCheck.setToolTipText("Confirm vehicle");
		btnCheck.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/check.png")));
		toolBar.add(btnCheck);
		
		toolBar.addSeparator(new Dimension(20,10));

		btnAddDetail = new JButton("");
		btnAddDetail.setToolTipText("Añadir detalle");
		btnAddDetail.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/Add.png")));
		toolBar.add(btnAddDetail);

		btnRemoveDetail = new JButton("");
		btnRemoveDetail.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/Remove.png")));
		btnRemoveDetail.setToolTipText("Eliminar detalle");
		toolBar.add(btnRemoveDetail);
		
		toolBar.addSeparator(new Dimension(20,10));

		btnPrint = new JButton("");
		btnPrint.setToolTipText("Abrir visor de report");
		btnPrint.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/Printer.png")));
		toolBar.add(btnPrint);

		btnPdf = new JButton("");
		btnPdf.setToolTipText("Exportar a pdf");
		btnPdf.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/pdf.png")));
		toolBar.add(btnPdf);
		panelDetalles.add(panelTotal, "cell 0 1,alignx right,aligny top");
		panelCentral.setLayout(gl_panelCentral);
		panelBotonesInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setToolTipText("Cerrar ventana");
		panelBotonesInferior.add(btnCerrar);
		panelSuperior.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelCliente = new JPanel();
		panelSuperior.add(panelCliente);
		panelCliente.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelCliente.setLayout(new MigLayout("", "[][grow]", "[][][]"));

		JLabel lblNombre = new JLabel("Nombre");
		panelCliente.add(lblNombre, "cell 0 0,alignx trailing");

		txtFieldNombre = new JTextField();
		txtFieldNombre.setEnabled(false);
		txtFieldNombre.setEditable(false);
		panelCliente.add(txtFieldNombre, "cell 1 0,growx");
		txtFieldNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		panelCliente.add(lblApellidos, "cell 0 1,alignx trailing");

		txtFieldApellidos = new JTextField();
		txtFieldApellidos.setEnabled(false);
		txtFieldApellidos.setEditable(false);
		panelCliente.add(txtFieldApellidos, "cell 1 1,growx");
		txtFieldApellidos.setColumns(10);

		JLabel lblDni = new JLabel("DNI");
		panelCliente.add(lblDni, "cell 0 2,alignx trailing");

		txtFieldDNI = new JTextField();
		txtFieldDNI.setEnabled(false);
		txtFieldDNI.setEditable(false);
		panelCliente.add(txtFieldDNI, "cell 1 2,growx");
		txtFieldDNI.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSuperior.add(panel);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][grow]"));

		JLabel lblFacturaN = new JLabel("Factura Nº");
		panel.add(lblFacturaN, "cell 0 0,alignx trailing");

		txtFieldNumeroFactura = new JTextField();
		txtFieldNumeroFactura.setEnabled(false);
		txtFieldNumeroFactura.setEditable(false);
		panel.add(txtFieldNumeroFactura, "cell 1 0,growx");
		txtFieldNumeroFactura.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha");
		panel.add(lblFecha, "cell 0 1,alignx trailing");

		webDateFieldFechaFactura = new WebDateField();
		webDateFieldFechaFactura.setEnabled(false);
		panel.add(webDateFieldFechaFactura, "cell 1 1,growx");


		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 0 2 2 1,alignx center,aligny center");
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnPreviousInvoice = new JButton("");
		panel_2.add(btnPreviousInvoice);
		btnPreviousInvoice.setToolTipText("Factura anterior");
		btnPreviousInvoice.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/leftArrow.png")));

		btnNextInvoice = new JButton("");
		panel_2.add(btnNextInvoice);
		btnNextInvoice.setToolTipText("Siguiente factura");
		btnNextInvoice.setIcon(new ImageIcon(JIFAlquiler.class.getResource("/com/mordor/lloguer/assets/rightArrow.png")));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 2,grow");
		getContentPane().setLayout(groupLayout);

		// Establecemos alineacion de la tabla detalles
//		DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
//		tcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
//		tableDetalles.getColumnModel().getColumn(0).setCellRenderer(tcrRight);
//		tableDetalles.getColumnModel().getColumn(2).setCellRenderer(tcrRight);
//		tableDetalles.getColumnModel().getColumn(3).setCellRenderer(tcrRight);
//		tableDetalles.getColumnModel().getColumn(4).setCellRenderer(tcrRight);

	}

	public JTextField getTxtFieldNombre() {
		return txtFieldNombre;
	}

	public JTextField getTxtFieldApellidos() {
		return txtFieldApellidos;
	}

	public JTextField getTxtFieldDNI() {
		return txtFieldDNI;
	}

	public JTextField getTxtFieldNumeroFactura() {
		return txtFieldNumeroFactura;
	}

	public WebDateField getWebDateFieldFechaFactura() {
		return webDateFieldFechaFactura;
	}

	public JTextField getTxtFieldSuma() {
		return txtFieldSuma;
	}

	public JTextField getTxtFieldImpuestos() {
		return txtFieldImpuestos;
	}

	public JTextField getTxtFieldTotal() {
		return txtFieldTotal;
	}

	public JTable getTableDetalles() {
		return tableDetalles;
	}

	public JButton getBtnPreviousInvoice() {
		return btnPreviousInvoice;
	}

	public JButton getBtnNextInvoice() {
		return btnNextInvoice;
	}

	public JButton getBtnNewInvoce() {
		return btnNewInvoce;
	}

	public JButton getBtnRemoveInvoice() {
		return btnRemoveInvoice;
	}

	public JButton getBtnClient() {
		return btnClient;
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}

	public JButton getBtnPdf() {
		return btnPdf;
	}

	public JButton getBtnAddDetail() {
		return btnAddDetail;
	}

	public JButton getBtnRemoveDetail() {
		return btnRemoveDetail;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public JButton getBtnCheck() {
		return btnCheck;
	}
}