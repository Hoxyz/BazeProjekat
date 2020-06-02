package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;

import database.DatabaseImplementation;
import database.MSSQLRepository;
import gui.MainFrame;
import gui.QueryResultFrame;
import gui.SearchDialog;
import resource.data.Pair;
import resource.data.Row;
import resource.implementation.Attribute;

public class SearchAction extends AbstractAction {
	
	private SearchDialog dialog;

	public SearchAction (String name, SearchDialog dialog) {
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Dodaj novi red");
		
		this.dialog = dialog;
	}
	
	public static List<Row> SearchTable(String tableName, List<Attribute> columns, List<Pair<String, Object>> parameters, List<String> logicalOperators) {
		List<Object> values = new ArrayList<Object>();
		
		String query = "SELECT ";
		query += columns.stream().map(column -> column.toString()).collect(Collectors.joining(", "));
		query += " FROM " + tableName + " WHERE ";
		
		for (int i = 0; i < parameters.size(); i++) {
			query += parameters.get(i).getFirst();
			values.add(parameters.get(i).getSecond());
			
			if (i < logicalOperators.size()) {
				query += " " + logicalOperators.get(i) + " ";
			}
		}
		
		return ((MSSQLRepository) ((DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase())
				.getRepository()).SelectQuery(query, values);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Row> results = SearchTable(dialog.getTableName(), dialog.getColumns(), dialog.getParameters(), dialog.getLogicalOperators());
		if (results.size() > 0) {
			new QueryResultFrame(results);
		}
		else {
			// TODO: JOptionPane "No results found!"
		}
	}
}
