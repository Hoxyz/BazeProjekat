package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;

import database.DatabaseImplementation;
import database.MSSQLRepository;
import gui.MainFrame;
import gui.QueryResultFrame;
import gui.ReportDialog;
import resource.data.Row;

public class ReportAction extends AbstractAction {

	private ReportDialog dialog;

	public ReportAction (String name, ReportDialog dialog) {
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Dodaj novi red");
		
		this.dialog = dialog;
	}
	
	public static List<Row> TableReport (String tableName, List<String> columns, List<String> groupColumns) {
		String query = "SELECT ";
		query += columns.stream().collect(Collectors.joining(", "));
		query += " FROM " + tableName;
		
		if (groupColumns.size() > 0) {
			query += " GROUP BY ";
			query += groupColumns.stream().collect(Collectors.joining(", "));
		}
		
		return ((MSSQLRepository) ((DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase())
				.getRepository()).SelectQuery(query, new ArrayList<Object>());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Row> results = TableReport(dialog.getTableName(), dialog.getColumns(), dialog.getGrouping());
		if (results.size() > 0) {
			new QueryResultFrame(results);
		}
		else {
			// TODO: JOptionPane "No results found!"
		}
	}

}
