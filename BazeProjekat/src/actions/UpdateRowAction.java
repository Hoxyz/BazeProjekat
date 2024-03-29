package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import database.DatabaseImplementation;
import database.MSSQLRepository;
import gui.MainFrame;
import gui.UpdateDialog;
import resource.implementation.Entity;

public class UpdateRowAction extends AbstractAction {
	
	private Entity entity;
	private List<String> columnNames;
	private UpdateDialog updateDialog;

	public UpdateRowAction(String name, Entity entity, List<String> columnNames, UpdateDialog updateDialog) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_F);
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Dodaj novi red");
		
		this.entity = entity;
		this.columnNames = columnNames;
		this.updateDialog = updateDialog;
	}
	
	public static void UpdateRow(String tableName, List<String> columnNames, List<Object> initialValues, List<Object> values) {
		String query = "UPDATE " + tableName + " SET ";
		query += columnNames.stream().collect(Collectors.joining("=?, "));
		query += "=? WHERE ";
		query += columnNames.stream().collect(Collectors.joining("=? AND "));
		query += "=?";
		
		List<Object> preparedStatementValues = new ArrayList<Object>();
		preparedStatementValues.addAll(values);
		preparedStatementValues.addAll(initialValues);
		
		// TODO: Wrap in a try/catch block
		try {
			((MSSQLRepository) ((DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase())
					.getRepository()).removeRowQuery(query, preparedStatementValues);
		}
		catch (Exception e) {
			JDialog errorDialog = new JDialog(MainFrame.getInstance(), "Greska", true);
        	errorDialog.add(new JLabel("Nemoguce azurirati selektovani red / red nije selektovan.", SwingConstants.CENTER));
        	errorDialog.setSize(480, 180);
        	errorDialog.setLocationRelativeTo(null);
        	errorDialog.setVisible(true);
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		UpdateRow(entity.getName(), columnNames, updateDialog.getInitialValues(), updateDialog.getColumnValues());
		MainFrame.getInstance().getTablePane().getTableWindow(entity).Refresh();
	}

}
