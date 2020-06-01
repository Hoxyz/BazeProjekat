package gui;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

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

public class SearchColumnPanel extends JPanel {

	private static final String[] numericOperators = { "<", ">", "=" };
	
	private JComboBox<Object> columnBox;
	private JComboBox<Object> operatorBox;
	private JTextField textField;
	
	public SearchColumnPanel(List<String> columnNames, AttributeType attrType) {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		columnBox = new JComboBox<Object>(columnNames.toArray());
		columnBox.setMaximumSize(new Dimension(200, 30));
		add(columnBox);
		
		add(Box.createRigidArea(new Dimension(5, 0)));
		
		operatorBox = new JComboBox<Object>(numericOperators);
		operatorBox.setMaximumSize(new Dimension(50, 30));
		add(operatorBox);
		
		add(Box.createRigidArea(new Dimension(5, 0)));
		
		textField = new JTextField();
		textField.setMaximumSize(new Dimension(200, 30));
		add(textField);
	}
	
	public Pair<String, Object> getParams () {
		return new Pair<String, Object> (columnBox.getSelectedItem().toString() + operatorBox.getSelectedItem().toString(), 
				textField.getText());
	}
}
