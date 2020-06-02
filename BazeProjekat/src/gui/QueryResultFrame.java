package gui;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import resource.data.Row;

public class QueryResultFrame extends JFrame {
	
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	
	public QueryResultFrame(List<Row> rows) {
		tableModel = new DefaultTableModel();
		
		// Wow
		tableModel.setColumnIdentifiers(rows.get(0).getColumnNames());
		
		for(Row row : rows) {
			tableModel.addRow(row.getValuesObjects());
		}
		
		JTable table = new JTable(tableModel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
