package database;

import java.util.List;

import resource.DBNode;
import resource.data.Row;

public interface Database {
	DBNode loadResource();
    List<Row> readDataFromTable(String tableName);
}
