package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import resource.enums.AttributeType;

public class SearchDialog extends JDialog {
	
	private JScrollPane scrollPane;
	private JPanel mainPanel;
	
	public SearchDialog () {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		scrollPane = new JScrollPane (mainPanel);
		scrollPane.setPreferredSize(new Dimension(480, 520));
		
		add(scrollPane);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		List<String> temp = new ArrayList<String>();
		temp.add("country_id");
		temp.add("country_name");
		temp.add("region_id");
		
		mainPanel.add(new SearchColumnPanel(temp, AttributeType.NUMERIC));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new JComboBox<Object>(new String[] {"", "AND", "OR"}));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new SearchColumnPanel(temp, AttributeType.NUMERIC));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new JComboBox<Object>(new String[] {"", "AND", "OR"}));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new SearchColumnPanel(temp, AttributeType.NUMERIC));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new JComboBox<Object>(new String[] {"", "AND", "OR"}));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new SearchColumnPanel(temp, AttributeType.NUMERIC));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new JComboBox<Object>(new String[] {"", "AND", "OR"}));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new SearchColumnPanel(temp, AttributeType.NUMERIC));
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
