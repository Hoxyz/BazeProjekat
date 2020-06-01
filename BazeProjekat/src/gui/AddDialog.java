package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.AddRowAction;
import database.DatabaseImplementation;
import database.MSSQLRepository;
import resource.implementation.Entity;

public class AddDialog {
	
	private List<JTextField> textFields;
	
	public AddDialog(Entity table) {
		textFields = new ArrayList<>();
		
		JPanel mainPanel = new JPanel();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		Map<String, String> columns = ((MSSQLRepository)((DatabaseImplementation)
				MainFrame.getInstance().getAppCore().getDatabase()).getRepository()).getColumns(table.getName());
		
		List<String> columnNames = new ArrayList<String>();
		
		for(Map.Entry<String, String> column : columns.entrySet()) {
			JLabel label = new JLabel(column.getKey());
			columnNames.add(column.getKey());
			JTextField textField = new JTextField(column.getValue());
			textFields.add(textField);
			JPanel columnPanel = new JPanel();
			columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.PAGE_AXIS));
			columnPanel.add(label);
			columnPanel.add(textField);
			panel.add(columnPanel, BorderLayout.CENTER);
			panel.add(Box.createRigidArea(new Dimension(5, 0)));
		}
		
		JButton buttonAdd = new JButton(new AddRowAction("Add", table, columnNames, this));

		mainPanel.add(panel);
		mainPanel.add(buttonAdd);
		
		JDialog dialog = new JDialog();
		
		dialog.add(mainPanel, BorderLayout.CENTER);
					
		dialog.pack();
		dialog.revalidate();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public List<Object> getColumnValues() {
		List<Object> columnValues = new ArrayList<Object>();
		for(JTextField textField : textFields) {
			columnValues.add(textField.getText());
		}
		return columnValues;
	}
}
