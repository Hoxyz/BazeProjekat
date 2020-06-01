package gui;

import resource.implementation.Entity;

public class RelationsTabbedPane extends TableTabbedPane {

	public void openRelations(Entity table) {
		this.removeAll();
		
		if(table == null) {
			System.out.println("table je null");
			return;
		}
		for(Entity relation : table.getRelations()) {
			this.openTable(relation);
		}
	}
	
	@Override
	protected void onSetSelectedIndex(int index) {
		// :)
	}
	
}
