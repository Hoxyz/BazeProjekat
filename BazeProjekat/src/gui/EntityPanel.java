package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import gui.table.TableModel;
import resource.data.Row;
import resource.implementation.Entity;

public class EntityPanel extends JPanel {
	
	private Entity entity;
	private JScrollPane scrollPane;
	private JTable table;
	private TableModel tableModel;
	
	public EntityPanel (Entity entity) {
		this.entity = entity;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		tableModel = new TableModel(entity);
		tableModel.setRows(MainFrame.getInstance().getAppCore().getDatabase().readDataFromTable(entity.getName()));
		
		table = new JTable();
		table.setModel(tableModel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(new Dimension(1920, 1080));
		
		add(scrollPane, BorderLayout.EAST);
	}
	
	public String getName () {
		return entity.getName();
	}
	
	public int[] getSelectedRows () {
		if(table == null) {
			return null;
		}
		return table.getSelectedRows();
	}
	
	public Row getRow (int index) {
		return tableModel.getRows().get(index);
	}
	
	public JTable getTable () {
		return table;
	}
	
	public void Refresh () {
		tableModel.setRows(MainFrame.getInstance().getAppCore().getDatabase().readDataFromTable(entity.getName()));
		tableModel.fireTableDataChanged();
	}
	
	public void setEditable (boolean editable) {
		tableModel.setEditable (editable);
	}
	
}
