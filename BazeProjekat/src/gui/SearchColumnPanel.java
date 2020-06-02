package gui;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import resource.data.Pair;
import resource.enums.AttributeType;
import resource.implementation.Attribute;

public class SearchColumnPanel extends JPanel {

	private static final String[] numericOperators = { "<", ">", "=" };
	
	private JComboBox<Object> columnBox;
	private JComboBox<Object> operatorBox;
	private JTextField textField;
	
	private static boolean AttributeIsNumeric(AttributeType attrType) {
		return attrType == AttributeType.INT || attrType == AttributeType.NUMERIC || 
				attrType == AttributeType.BIGINT || attrType == AttributeType.FLOAT || attrType == AttributeType.REAL;
	}
	
	public SearchColumnPanel(List<Attribute> columns) {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		columnBox = new JComboBox<Object>(columns.toArray());
		columnBox.setMaximumSize(new Dimension(200, 30));
		add(columnBox);
		
		add(Box.createRigidArea(new Dimension(5, 0)));
		
		operatorBox = new JComboBox<Object>(numericOperators);
		operatorBox.setMaximumSize(new Dimension(50, 30));
		add(operatorBox);
		
		if (columns.size() == 0 || !AttributeIsNumeric(columns.get(0).getAttributeType())) {
			operatorBox.setVisible(false);
		}
		
		add(Box.createRigidArea(new Dimension(5, 0)));
		
		textField = new JTextField();
		textField.setMaximumSize(new Dimension(200, 30));
		add(textField);
		
		columnBox.addItemListener(new ItemListener() {
			// Controller?
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					AttributeType attrType = ((Attribute)e.getItem()).getAttributeType();
					boolean isNumeric = AttributeIsNumeric(attrType);
					
					if (isNumeric) {
						operatorBox.setVisible(true);
						SearchColumnPanel.this.invalidate();
					}
					else {
						operatorBox.setVisible(false);
						SearchColumnPanel.this.invalidate();
					}
				}
			}
		});
	}
	
	public Pair<String, Object> getParams () {
		Pair<String, Object> params = new Pair<String, Object> (columnBox.getSelectedItem().toString(), textField.getText());
		if (operatorBox.isVisible()) {
			params.setFirst(params.getFirst() + operatorBox.getSelectedItem().toString() + "?");
		}
		else {
			params.setFirst(params.getFirst() + " LIKE ?");
		}
		
		return params;
	}
}
