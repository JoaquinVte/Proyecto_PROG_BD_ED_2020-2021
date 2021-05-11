package com.mordor.lloguer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class MyTableModel<T> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String[] COLUMN_NAMES;
	protected List<T> data;

	public MyTableModel(String[] COLUMN_NAMES, List<T> data) {
		super();
		this.COLUMN_NAMES = COLUMN_NAMES;
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
	public abstract Object getValueAt(int arg0, int arg1);

	public T getRow(int row) {
		return data.get(row);
	}

	public void addRow(T employee) {
		data.add(employee);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public ArrayList<T> getElementsAtRows(int[] rows) {
		ArrayList<T> employees = new ArrayList<T>();

		for (int row : rows)
			employees.add(getElementAtRow(row));

		return employees;
	}

	public T getElementAtRow(int row) {
		if (row < 0 || row >= data.size())
			return null;
		else
			return data.get(row);
	}

	public void removeRow(int row) {
		data.remove(row);
		this.fireTableRowsDeleted(row, row);
	}

	public void removeElement(T employee) {
		int row = data.indexOf(employee);
		if (row >= 0) {
			removeRow(row);
		}
	}

}
