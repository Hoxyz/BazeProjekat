package gui;

import resource.implementation.Entity;

public class RelationsTabbedPane extends TableTabbedPane {

	public RelationsTabbedPane() {
		Initialize();
	}
	
	public void openRelations(Entity table) {
		this.removeAll();
		
		for(Entity relation : table.getRelations()) {
			this.openTable(relation);
		}
	}
	
	@Override
	protected void onSetSelectedIndex(int index) {
		// :)
	}
	
}
