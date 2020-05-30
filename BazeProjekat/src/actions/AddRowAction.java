package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.AddDialog;
import gui.MainFrame;
import gui.table.TableModel;
import observer.Notification;
import observer.enums.NotificationCode;
import resource.implementation.Entity;
import resource.implementation.InformationResource;
import database.MSSQLRepository;
import database.Repository;
import database.DatabaseImplementation;

public class AddRowAction extends AbstractAction {
	
	private Entity entity;
	List<String> columnNames;
	AddDialog addDialog;
	
	public AddRowAction(String name, Entity entity, List<String> columnNames, AddDialog addDialog) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_F);
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Dodaj novi red");
		
		this.entity = entity;
		this.columnNames = columnNames;
		this.addDialog = addDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String tableName = entity.getName();
		
		String query = "INSERT INTO " + tableName + " (";

		for(int i = 0; i < columnNames.size(); i++) {
			if(i == columnNames.size() - 1) {
				query += columnNames.get(i);
			}
			else {
				query += columnNames.get(i) + ", ";
			}
		}
		
		query += ") VALUES (";
		
		List<String> columnValues = addDialog.getColumnValues();
		
		for(int i = 0; i < columnValues.size(); i++) {
			if(i == columnValues.size() - 1) {
				query += "?";
			}
			else {
				query += "?, ";
			}
		}
		
		query += ")";
		
		((MSSQLRepository) ((DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase())
				.getRepository()).addRowQuery(query, columnValues);
		MainFrame.getInstance().getTablePane().getTableWindow(entity).Refresh();
	}
	
}
