package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import database.DatabaseImplementation;
import database.MSSQLRepository;
import gui.EntityPanel;
import gui.MainFrame;
import gui.TableTabbedPane;
import resource.DBNode;
import resource.data.Row;
import resource.implementation.Entity;

public class RemoveSelectedRowsAction extends AbstractAction {

	public RemoveSelectedRowsAction() {
		putValue(NAME, "Remove");
		putValue(SHORT_DESCRIPTION, "Izbrisi odabrane redove");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TableTabbedPane tabbedPane = MainFrame.getInstance().getTablePane();
		Entity entity = tabbedPane.getCurrentTable();
		EntityPanel panel = tabbedPane.getTableWindow(entity);
		
		List<String> columnNames = new ArrayList<String>();
		String query = "DELETE FROM " + entity.getName() + " WHERE ";
		
		for (DBNode node : entity.getChildren()) {
			columnNames.add(node.getName());
		}
		query += columnNames.stream().collect(Collectors.joining("=? AND "));
		query += "=?";
		
		int[] selectedRows = panel.getSelectedRows();
		for (int index : selectedRows) {
			Row row = panel.getRow(index);
						
			List<Object> values = row.getValuesVector();
			
			((MSSQLRepository) ((DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase())
					.getRepository()).removeRowQuery(query, values);
		}
		
		panel.Refresh();
	}

}
