package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import actions.ReportAction;
import controller.itemListeners.AvgCountItemListener;
import resource.implementation.Attribute;
import resource.implementation.Entity;

public class ReportDialog extends JDialog {
	
	private JComboBox<Object> avgCountComboBox;
	private JComboBox<Object> columnComboBox;
	
	private JScrollPane scrollPane;
	private JPanel mainPanel;
	private List<GroupByComboBox> groupByComboBoxes;
	
	private Entity entity;
	
	private List<Attribute> columns;
	
	public ReportDialog (Entity entity) {
		this.entity = entity;
		
		columns = new ArrayList<Attribute>();
		entity.getChildren().stream().forEachOrdered(x -> columns.add(((Attribute)x)));
		
		setTitle(entity.getName() + " Report");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		scrollPane = new JScrollPane (mainPanel);
		scrollPane.setPreferredSize(new Dimension(500, 520));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.PAGE_AXIS));
		wrapperPanel.add(scrollPane);
		
		JButton reportButton = new JButton(new ReportAction("Generate report", this));
		reportButton.setAlignmentX(CENTER_ALIGNMENT);
		wrapperPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		wrapperPanel.add(reportButton);
		wrapperPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		avgCountComboBox = new JComboBox<Object>(new String[] {"COUNT", "AVG"});
		columnComboBox = new JComboBox<Object>(columns.toArray());
		avgCountComboBox.addItemListener(new AvgCountItemListener(columns, columnComboBox));
		avgCountComboBox.setSelectedIndex(0);
		
		JPanel columnPanel = new JPanel();
		columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.LINE_AXIS));
		columnPanel.setPreferredSize(new Dimension(475, 30));
		columnPanel.setMaximumSize(new Dimension(475, 30));
		columnPanel.add(avgCountComboBox);
		columnPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		columnPanel.add(columnComboBox);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(columnPanel);
		
		JLabel groupByLabel = new JLabel("GROUP BY");
		groupByLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(groupByLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		groupByComboBoxes = new ArrayList<GroupByComboBox>();
		GroupByComboBox cb = new GroupByComboBox(columns, this);
		groupByComboBoxes.add(cb);
		
		mainPanel.add(cb);
		
		add(wrapperPanel);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public String getTableName () {
		return entity.getName();
	}
	
	public List<String> getColumns () {
		List<String> columnNames = new ArrayList<String>();
		columnNames.add(avgCountComboBox.getSelectedItem().toString() + "(" + columnComboBox.getSelectedItem().toString() + ")");
		columnNames.set(0, columnNames.get(0) + " as \"" + columnNames.get(0) + "\"");
		
		for (GroupByComboBox cb : groupByComboBoxes) {
			if (cb.getSelectedItem() != "" && !columnNames.contains(cb.getSelectedItem().toString())) {
				columnNames.add(cb.getSelectedItem().toString());
			}
		}
		
		return columnNames;
	}
	
	public List<String> getGrouping() {
		List<String> groupColumns = new ArrayList<String>();
		
		for(GroupByComboBox cb : groupByComboBoxes) {
			if (cb.getSelectedItem() != "" && !groupColumns.contains(cb.getSelectedItem().toString())) {
				groupColumns.add(cb.getSelectedItem().toString());
			}
		}
		
		return groupColumns;
	}
	
	public void addCB () {
		GroupByComboBox cb = new GroupByComboBox(columns, this);
		
		groupByComboBoxes.add(cb);
		mainPanel.add(cb);
		
		pack();
		repaint();
	}
	
	public void removeCB (GroupByComboBox cb) {
		if (groupByComboBoxes.contains(cb)) {
			groupByComboBoxes.remove(cb);
			mainPanel.remove(cb);
			
			pack();
			repaint();
		}
	}
}
