package gui;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import resource.implementation.Attribute;

public class GroupByComboBox extends JComboBox<Object> {
	
	private List<Attribute> columns;
	
	public GroupByComboBox(List<Attribute> columns, ReportDialog reportDialog) {
		super(columns.toArray());
		insertItemAt("", 0);
		setSelectedIndex(0);
		
		this.columns = columns;
		
		setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		setMaximumSize(new Dimension(300, 30));
		
		addItemListener(new ItemListener() {
			
			private Object lastSelected;
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					lastSelected = e.getItem();
				}
				else if (e.getStateChange() == ItemEvent.SELECTED) {
					if (lastSelected instanceof Attribute && e.getItem().equals("")) {
						reportDialog.removeCB(GroupByComboBox.this);
					}
					else if (lastSelected.equals("") && e.getItem() instanceof Attribute) {
						reportDialog.addCB();
					}
				}
			}
		});
	}
}
