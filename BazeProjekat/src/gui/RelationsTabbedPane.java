package gui;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.RowFilter;

import resource.data.Row;
import resource.implementation.Entity;

public class RelationsTabbedPane extends TableTabbedPane {
	
	public RelationsTabbedPane() {
		Initialize();
	}
	
	public void openRelations(Entity table) {
		this.removeAll();
		
		for(Entity relation : table.getRelations()) {
			this.openTable(relation);
			getTableWindow(relation).setEditable(false);
		}
	}
	
	public void ShowRelatedRowsWith (Row row) {
		for (Map.Entry<Entity, EntityPanel> pair : openTables.entrySet()) {
			EntityPanel ep = (EntityPanel) pair.getValue();
			
			RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
			    public boolean include (Entry entry) {
			    	Row currentRow = (Row) (entry.getValue(0));
			    	
			    return false;
			    }
			};
		}
	}
	
	@Override
	protected void onSetSelectedIndex(int index) {
		// :)
	}
}
