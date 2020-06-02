package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.AddDialog;
import gui.MainFrame;
import resource.implementation.Entity;
import database.MSSQLRepository;
import database.DatabaseImplementation;

public class AddRowAction extends AbstractAction {
	
	private Entity entity;
	List<String> columnNames;
	AddDialog addDialog;
	
	public AddRowAction(String name, Entity entity, List<String> columnNames, AddDialog addDialog) {
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Dodaj novi red");
		
		this.entity = entity;
		this.columnNames = columnNames;
		this.addDialog = addDialog;
	}
	
	public static void AddRow(String tableName, List<String> columnNames, List<Object> values) {
		String query = "INSERT INTO " + tableName + " (";
		query += columnNames.stream().collect(Collectors.joining(", "));
		query += ") VALUES (";
		for (int i = 1; i < values.size(); i++) {
			query += "?, ";
		}
		query += "?)";
		
		// TODO: Wrap in a try/catch block
		((MSSQLRepository) ((DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase())
				.getRepository()).addRowQuery(query, values);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AddRow(entity.getName(), columnNames, addDialog.getColumnValues());
		MainFrame.getInstance().getTablePane().getTableWindow(entity).Refresh();
	}
	
}
