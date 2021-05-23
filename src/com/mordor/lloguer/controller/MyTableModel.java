package com.mordor.lloguer.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;
/**
 * 
 * @author joaalsai
 *
 * @param <T> Data type that store the table
 */
public abstract class MyTableModel<T> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<String> columnNames;
	protected List<T> data;

	/**
	 * 
	 * @param columnNames Column names
	 * @param data Table data
	 */
	public MyTableModel(List<String> columnNames, List<T> data) {
		super();
		this.columnNames = columnNames;
		this.data = data;

	}

	public String getColumnName(int col) {
		return columnNames.get(col);
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	/**
	 * Add a T element to the table
	 * 
	 * @param element Element to add
	 */
	public void addElement(T element) {
		data.add(element);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	/**
	 * Get the T elements placent on the rows passed by parameter
	 * 
	 * @param rows Table rows
	 * @return Collection with the elements placea at the rows
	 */
	public ArrayList<T> getElementsAtRows(int[] rows) {
		ArrayList<T> employees = new ArrayList<T>();

		for (int row : rows)
			employees.add(getElementAtRow(row));

		return employees;
	}
	
	/**
	 * Change the table data
	 * 
	 * @param data New data
	 */
	public void setNewData(List<T> data) {
		this.data = data;
		fireTableDataChanged();
	}

	/**
	 * Get the T element at row
	 * @param row Table row
	 * @return The element at row
	 */
	public T getElementAtRow(int row) {
		if (row < 0 || row >= data.size())
			return null;
		else
			return data.get(row);
	}

	/**
	 * Remove the element at row passed by parameter
	 * 
	 * @param row Table row
	 * @return The element removed
	 */
	public T removeElementAtRow(int row) {
		if (row < 0 || row >= data.size())
			return null;
		else {
			this.fireTableRowsDeleted(row, row);
			return data.remove(row);
		}
	}	
	
	/**
	 * Set a comparator to sort the table
	 * @param comparator Comparator used to sort the table
	 */
	public void setComparator(Comparator<T> comparator) {
		Collections.sort(data, comparator);
		fireTableDataChanged();
	}

	/**
	 * Remove one element from the table
	 * @param element Element to remove
	 * @return True if the element has been removed or false otherwise.
	 */
	public boolean removeElement(T element) {
		int row = data.indexOf(element);
		if (row >= 0) {
			removeElementAtRow(row);
			return true;
		} else
			return false;
	}
	

	@Override
	public abstract Object getValueAt(int row, int col);

}
