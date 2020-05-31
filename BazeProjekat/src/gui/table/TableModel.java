package gui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import resource.data.Row;

public class TableModel extends DefaultTableModel {
	
	private List<Row> rows;
	
	public TableModel() {
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
    	rows.get(row).changeField(col, value);
    	fireTableCellUpdated(row, col);
    	updateModel();
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	return true;
    }
}
