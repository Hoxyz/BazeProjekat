package gui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import actions.UpdateRowAction;
import database.DatabaseImplementation;
import database.MSSQLRepository;
import gui.MainFrame;
import resource.data.Pair;
import resource.data.Row;
import resource.implementation.Entity;

public class TableModel extends DefaultTableModel {
	
	private Entity entity;
	private List<Row> rows;
	
	private boolean editable;
	
	public TableModel(Entity entity) {
		this.entity = entity;
		rows = new ArrayList<Row>();
		editable = true;
	}
	
	public List<Row> getRows() {
		return rows;
	}
	
	public void setRows(List<Row> rows) {
		this.rows = rows;
		updateModel();
	}
	
	public void setEditable (boolean editable) {
		this.editable = editable;
	}
	
    private void updateModel(){
        int columnCount = rows.get(1).getFields().size();

        Vector columnVector = DefaultTableModel.convertToVector(rows.get(1).getColumnNames());
        Vector dataVector = new Vector(columnCount);

        for (int i = 0; i < rows.size(); i++){
            dataVector.add(DefaultTableModel.convertToVector(rows.get(i).getValuesObjects()));
        }
        setDataVector(dataVector, columnVector);
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
    	System.out.println("Column " + col);
    	
    	List<String> columnNames = new ArrayList<String>();
		List<Object> initialValues = new ArrayList<Object>();
		List<Object> values = new ArrayList<Object>();
		
		for (Pair<String, Object> pair : rows.get(row).getFields()) {
			columnNames.add(pair.getFirst());
			initialValues.add(pair.getSecond());
			values.add(pair.getSecond());
		}
		
		values.set(col, value);
		
		UpdateRowAction.UpdateRow(entity.getName(), columnNames, initialValues, values);
		
    	rows.get(row).changeField(col, value);
    	fireTableCellUpdated(row, col);
    	updateModel();
    }
    
    @Override
    public boolean isCellEditable (int row, int col) {
    	return editable;
    }
}
