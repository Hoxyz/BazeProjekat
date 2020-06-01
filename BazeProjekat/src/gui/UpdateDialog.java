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
import actions.UpdateRowAction;
import database.DatabaseImplementation;
import database.MSSQLRepository;
import resource.data.Pair;
import resource.data.Row;
import resource.implementation.Entity;

public class UpdateDialog {
	
	private List<JTextField> textFields;
	private List<Object> initialValues;
	
	public UpdateDialog(Entity entity, Row row) {
		textFields = new ArrayList<>();
		
		JPanel mainPanel = new JPanel();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		List<String> columnNames = new ArrayList<String>();
		initialValues = new ArrayList<Object>();
		
		for (Pair<String, Object> pair : row.getFields()) {
			columnNames.add(pair.getFirst());
			JLabel label = new JLabel(pair.getFirst());
			
			initialValues.add(pair.getSecond());
			JTextField textField = new JTextField(pair.getSecond().toString());
			textFields.add(textField);
			
			JPanel columnPanel = new JPanel();
			columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.PAGE_AXIS));
			columnPanel.add(label);
			columnPanel.add(textField);
			
			panel.add(columnPanel, BorderLayout.CENTER);
			panel.add(Box.createRigidArea(new Dimension(5, 0)));
		}
		
		JButton buttonUpdate = new JButton(new UpdateRowAction("Update", entity, columnNames, this));

		mainPanel.add(panel);
		mainPanel.add(buttonUpdate);
		
		JDialog dialog = new JDialog();
		
		dialog.add(mainPanel, BorderLayout.CENTER);
					
		dialog.pack();
		dialog.revalidate();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public List<Object> getInitialValues() {
		return initialValues;
	}
	
	public List<Object> getColumnValues() {
		List<Object> columnValues = new ArrayList<Object>();
		
		for(JTextField textField : textFields) {
			columnValues.add(textField.getText());
		}
		return columnValues;
	}
}