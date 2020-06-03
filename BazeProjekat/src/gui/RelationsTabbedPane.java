package gui;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import gui.table.TableModel;
import resource.DBNode;
import resource.data.Pair;
import resource.data.Row;
import resource.implementation.Attribute;
import resource.implementation.Entity;

public class RelationsTabbedPane extends TableTabbedPane {
	
	private Entity relatedTable;
	
	public RelationsTabbedPane() {
		Initialize();
	}
	
	public void openRelations(Entity table) {
		this.relatedTable = table;
		this.removeAll();
		
		for(Entity relation : table.getRelations()) {
			this.openTable(relation);
			getTableWindow(relation).setEditable(false);
		}
	}
	
	public void ShowRelatedRowsWith (Row row) {
		row.getFields().stream().forEachOrdered(field -> System.out.print(field.getFirst() + ":" + field.getSecond() + " "));
		
		for (Map.Entry<Entity, EntityPanel> pair : openTables.entrySet()) {
			EntityPanel ep = (EntityPanel) pair.getValue();
			
			RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>() {
			    public boolean include (Entry<? extends TableModel, ? extends Integer> entry) {
			    	Row currentRow = ((TableModel)entry.getModel()).getRows().get(entry.getIdentifier());
			    	
			    	for (DBNode node : relatedTable.getChildren()) {
			    		Attribute attr = (Attribute) node;
			    		Attribute relatedAttr = attr.getInRelationWith();
			    		
			    		if (pair.getKey().getChildren().contains(relatedAttr)) {
			    			Object currentValue = currentRow.getValueByFieldName(relatedAttr.getName());
			    			Object selectedValue = row.getValueByFieldName(attr.getName());
	    					if (currentValue != null && selectedValue != null && currentValue.equals(selectedValue)) {
	    						return true;
	    					}
			    		}
			    	}
			    	
			    	for (DBNode node : pair.getKey().getChildren()) {
			    		Attribute attr = (Attribute) node;
			    		Attribute relatedAttr = attr.getInRelationWith();
			    		
			    		if (relatedTable.getChildren().contains(relatedAttr)) {
			    			Object currentValue = currentRow.getValueByFieldName(attr.getName());
			    			Object selectedValue = row.getValueByFieldName(relatedAttr.getName());
	    					if (currentValue != null && selectedValue != null && currentValue.equals(selectedValue)) {
	    						return true;
	    					}
			    		}
			    	}
			    	
			    	return false;
			    }
			};
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>((TableModel) pair.getValue().getTable().getModel());
			sorter.setRowFilter(filter);
			pair.getValue().getTable().setRowSorter(sorter);
		}
	}
	
	@Override
	protected void onSetSelectedIndex(int index) {
		// :)
	}
}
