package resource.data;

import java.util.List;
import java.util.Vector;

public class Row {
	
	private String name;
	
    private Vector<Pair<String, Object>> fields;

    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    public List<Pair<String, Object>> getFields() {
    	return fields;
    }
    
    public Object[] getColumnNames() {
    	Object[] columnNames = new Object[fields.size()];
    	for(int i = 0; i < fields.size(); i++) {
    		columnNames[i] = fields.get(i).getFirst();
    	}
    	return columnNames;
    }
    
    public Object[] getValuesObjects() {
    	Object[] values = new Object[fields.size()];
    	for(int i = 0; i < fields.size(); i++) {
    		values[i] = fields.get(i).getSecond();
    	}
    	return values;
    }
    
    public Vector<Object> getValuesVector() {
    	Vector<Object> values = new Vector<>();
    	for(Pair<String, Object> p : fields) {
    		values.add(p.getSecond());
    	}
    	return values;
    }
    
    public Row() {
        this.fields = new Vector<Pair<String, Object>>();
    }
    
    public void changeField(int index, Object value) {
    	fields.get(index).setSecond(value);
    }

    public void addField(String fieldName, Object value) {
        this.fields.add(new Pair(fieldName, value));
    }

    public void removeField(String fieldName) {
        this.fields.remove(fieldName);
    }
    
    
    
}
