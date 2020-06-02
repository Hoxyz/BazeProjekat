package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import actions.SearchAction;
import gui.tree.AttributeNode;
import resource.data.Pair;
import resource.enums.AttributeType;
import resource.implementation.Attribute;
import resource.implementation.Entity;

public class SearchDialog extends JDialog {
	
	private JScrollPane scrollPane;
	private JPanel mainPanel;
	
	private Entity entity;
	
	private List<Attribute> columns;
	
	private List<SearchColumnPanel> columnPanels;
	private List<AndOrComboBox> andOrComboBoxes;
	
	public SearchDialog (Entity entity) {
		this.entity = entity;
		
		setTitle(entity.getName() + " Search");
		
		columnPanels = new ArrayList<SearchColumnPanel>();
		andOrComboBoxes = new ArrayList<AndOrComboBox>();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.PAGE_AXIS));
		wrapperPanel.add(mainPanel);
		wrapperPanel.add(new JButton(new SearchAction("Search", this)));
		
		scrollPane = new JScrollPane (wrapperPanel);
		scrollPane.setPreferredSize(new Dimension(500, 520));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		add(scrollPane);
		
		// Main panel setup
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		columns = new ArrayList<Attribute>();
		entity.getChildren().stream().forEachOrdered(x -> columns.add(((Attribute)x)));
		
		SearchColumnPanel columnPanel = new SearchColumnPanel(columns);
		columnPanels.add(columnPanel);
		
		mainPanel.add(columnPanel);
		
		AndOrComboBox andOrComboBox = new AndOrComboBox(this);
		andOrComboBoxes.add(andOrComboBox);
		
		mainPanel.add(andOrComboBox);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public String getTableName () {
		return entity.getName();
	}
	
	public List<Attribute> getColumns() {
		return columns;
	}
	
	public List<Pair<String, Object>> getParameters() {
		List<Pair<String, Object>> params = new ArrayList<Pair<String,Object>>();
		
		for(SearchColumnPanel panel : columnPanels) {
			params.add(panel.getParams());
		}
		
		return params;
	}
	
	public List<String> getLogicalOperators() {
		List<String> logicalOperators = new ArrayList<String>();
		
		for(AndOrComboBox cb : andOrComboBoxes) {
			if (cb.getSelectedItem().toString() != "") {
				logicalOperators.add(cb.getSelectedItem().toString());
			}
		}
		
		return logicalOperators;
	}
	
	public SearchColumnPanel AddSearchColumnPanel () {
		SearchColumnPanel panel = new SearchColumnPanel(columns);
		columnPanels.add(panel);
		
		mainPanel.add(panel);
		
		AndOrComboBox andOrComboBox = new AndOrComboBox(this);
		andOrComboBoxes.add(andOrComboBox);
		
		mainPanel.add(andOrComboBox);
		
		pack();
		
		return panel;
	}
	
	public void RemoveComponent(SearchColumnPanel panel) {
		if (panel.getParent() == mainPanel) {
			mainPanel.remove(panel);
			columnPanels.remove(panel);
			
			pack();
			repaint();
		}
	}
	
	public void RemoveComponent(AndOrComboBox comboBox) {
		if (comboBox.getParent() == mainPanel) {
			System.out.println("DELETE");
			mainPanel.remove(comboBox);
			andOrComboBoxes.remove(comboBox);
			
			pack();
			repaint();
		}
	}
}
