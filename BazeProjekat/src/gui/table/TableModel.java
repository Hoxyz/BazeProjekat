package gui.table;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import resource.data.Row;

public class TableModel extends DefaultTableModel {
	
	private List<Row> rows;
	
	public List<Row> getRows() {
		return rows;
	}
	
    private void updateModel(){

        int columnCount = rows.get(1).getFields().keySet().size();

        Vector columnVector = DefaultTableModel.convertToVector(rows.get(1).getFields().keySet().toArray());
        Vector dataVector = new Vector(columnCount);

        for (int i = 0; i < rows.size(); i++){
            dataVector.add(DefaultTableModel.convertToVector(rows.get(i).getFields().values().toArray()));
        }
        setDataVector(dataVector, columnVector);
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        updateModel();
    }

	
}
