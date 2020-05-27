package gui;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTabbedPane;

import resource.implementation.Entity;

public class TableTabbedPane extends JTabbedPane {
	
	private HashMap<Entity, EntityPanel> openTables;
	private Vector<Entity> panelTabIndex;

	public TableTabbedPane() {
		Initialize();
	}

	public TableTabbedPane(int tabPlacement) {
		super(tabPlacement);
		Initialize();
	}

	public TableTabbedPane(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
		Initialize();
	}

	private void Initialize() {
		openTables = new HashMap<Entity, EntityPanel>();
		panelTabIndex = new Vector<Entity>();
	}

	public Entity getCurrentDocument() {
		return panelTabIndex.elementAt(getSelectedIndex());
	}

	public void openTable(Entity entity) {
		if (!openTables.containsKey(entity)) {
			EntityPanel panel = new EntityPanel(entity);
			addTab(panel.getName(), panel);
			openTables.put(entity, panel);
			panelTabIndex.add(entity);
		} else {
			int index = panelTabIndex.indexOf(entity);
			setSelectedIndex(index);
		}
	}

	public void closeTable(Entity entity) {
		int index = panelTabIndex.indexOf(entity);
		if (0 <= index && index <= panelTabIndex.size()) {
			removeTabAt(index);
			panelTabIndex.remove(entity);
			openTables.remove(entity);
		}
	}
}
