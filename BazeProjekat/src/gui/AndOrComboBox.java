package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class AndOrComboBox extends JComboBox<Object> {
	
	private SearchColumnPanel referencedPanel;
	
	public AndOrComboBox(SearchDialog parentDialog) {
		super(new Object[] {"", "AND", "OR"});
		setMinimumSize(new Dimension(100, 40));
		setPreferredSize(new Dimension(100, 40));
		setMaximumSize(new Dimension(100, 40));
		
		setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (!"".equals(e.getItem()) && referencedPanel == null) {
						referencedPanel = parentDialog.AddSearchColumnPanel();
					}
					else if ("".equals(e.getItem()) && referencedPanel != null) {
						System.out.println("enter");
						parentDialog.RemoveComponent(referencedPanel);
						parentDialog.RemoveComponent(AndOrComboBox.this);
					}
				}
			}
		});
	}
}
