package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTabbedPane;

import controller.itemListeners.RowMouseListener;
import resource.implementation.Entity;

public class TableTabbedPane extends JTabbedPane {
	
	protected HashMap<Entity, EntityPanel> openTables;
	protected Vector<Entity> panelTabIndex;

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
	
	protected void onSetSelectedIndex(int index) {
		MainFrame.getInstance().getRelationsPane().openRelations(panelTabIndex.get(index));		
	}
	
	@Override
	public void setSelectedIndex(int arg0) {
		super.setSelectedIndex(arg0);
		onSetSelectedIndex(arg0);
	}

	protected void Initialize() {
		openTables = new HashMap<Entity, EntityPanel>();
		panelTabIndex = new Vector<Entity>();
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					if (panelTabIndex.size() > 0) {
						if (panelTabIndex.get(getSelectedIndex()) != null) {
							closeTable(panelTabIndex.get(getSelectedIndex()));
						}
					}
				}
			}
		});
	}

	public Entity getCurrentTable() {
		if (getSelectedIndex() >= 0) {
			return panelTabIndex.elementAt(getSelectedIndex());
		}
		return null;
	}
	
	public EntityPanel getTableWindow(Entity entity) {
		if (openTables.containsKey(entity)) {
			return openTables.get(entity);
		}
		return null;
	}

	public void openTable(Entity entity) {
		if (!openTables.containsKey(entity)) {
			EntityPanel panel = new EntityPanel(entity);
			if (!(this instanceof RelationsTabbedPane)) {
				panel.getTable().addMouseListener(new RowMouseListener(panel.getTable()));
			}
			openTables.put(entity, panel);
			panelTabIndex.add(entity);
			
			addTab(panel.getName(), panel);
			setSelectedIndex(panelTabIndex.size() - 1);
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
	
	@Override
	public void removeAll() {
		super.removeAll();
		
		openTables = new HashMap<Entity, EntityPanel>();
		panelTabIndex = new Vector<Entity>();
	}
}
