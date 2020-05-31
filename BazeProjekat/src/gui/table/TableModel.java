package gui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import database.DatabaseImplementation;
import database.MSSQLRepository;
import gui.MainFrame;
import resource.DBNode;
import resource.data.Pair;
import resource.data.Row;
import resource.implementation.Entity;

public class TableModel extends DefaultTableModel {
	
	private Entity entity;
	private List<Row> rows;
	
	public TableModel(Entity entity) {
		this.entity = entity;
		rows = new ArrayList<Row>();
	}
	
	public List<Row> getRows() {
		return rows;
	}
	
	public void setRows(List<Row> rows) {
		this.rows = rows;
		updateModel();
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
    	List<String> columnNames = new ArrayList<String>();
		String query = "UPDATE " + entity.getName() + " SET " + rows.get(row).getFields().get(col).getFirst() + "=? WHERE ";
		
		List<Object> values = new ArrayList<Object>();
		values.add(value);
		
		for (Pair<String, Object> pair : rows.get(row).getFields()) {
			columnNames.add(pair.getFirst());
			values.add(pair.getSecond());
		}
		query += columnNames.stream().collect(Collectors.joining("=? AND "));
		query += "=?";
		
		((MSSQLRepository) ((DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase())
				.getRepository()).removeRowQuery(query, values);
		
    	rows.get(row).changeField(col, value);
    	fireTableCellUpdated(row, col);
    	updateModel();
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	return true;
    }
}
