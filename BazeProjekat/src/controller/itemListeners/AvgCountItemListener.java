package controller.itemListeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import resource.implementation.Attribute;
import utils.Utilities;

public class AvgCountItemListener implements ItemListener {
	
	private JComboBox<Object> columnBox;
	
	private List<Attribute> columns;
	private List<Attribute> numericColumns;
	
	public AvgCountItemListener(List<Attribute> columns, JComboBox<Object> columnBox) {
		this.columns = columns;
		this.columnBox = columnBox;
		
		numericColumns = new ArrayList<Attribute>();
		for (Attribute column : columns) {
			if (Utilities.AttributeIsNumeric(column.getAttributeType())) {
				numericColumns.add(column);
			}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getItem() == "AVG") {
				columnBox.removeAllItems();
				numericColumns.stream().forEachOrdered(column -> columnBox.addItem(column));
			}
			else if (e.getItem() == "COUNT") {
				columnBox.removeAllItems();
				columns.stream().forEachOrdered(column -> columnBox.addItem(column));
			}
			else {
				System.out.println("AvgCountItemListener error: " + e.getItem());
			}
		}
	}
}
