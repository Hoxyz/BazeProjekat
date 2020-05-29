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

import database.DatabaseImplementation;
import database.MSSQLRepository;
import resource.implementation.Entity;

public class AddDialog {
	
	private static AddDialog instance = null;
	
	private List<String> columnNames;
	private List<String> columnValues;
	
	private List<JTextField> textFields;
	
	private AddDialog() {
		super();
		columnNames = new ArrayList<>();
		columnValues = new ArrayList<>();
		textFields = new ArrayList<>();
	}
	
	public static AddDialog getInstance() {
		if(instance == null) {
			instance = new AddDialog();
		}
		return instance;
	}
	
	public List<String> getColumnNames() {
		return columnNames;
	}
	
	public List<String> getColumnValues() {
		for(JTextField textField : textFields) {
			columnValues.add(textField.getText());
		}
		return columnValues;
	}
	
	public void openAddDialog(Entity table) {
		JPanel mainPanel = new JPanel();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		Map<String, String> columns = ((MSSQLRepository)((DatabaseImplementation)
				MainFrame.getInstance().getAppCore().getDatabase()).getRepository()).getColumns(table.getName());
		
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
		
		JButton buttonAdd = new JButton(MainFrame.getInstance().getActionManager().getAddRowAction());

		mainPanel.add(panel);
		mainPanel.add(buttonAdd);
		
		
		JDialog dialog = new JDialog();
		
		dialog.add(mainPanel, BorderLayout.CENTER);
					
		dialog.pack();
		dialog.revalidate();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
}
