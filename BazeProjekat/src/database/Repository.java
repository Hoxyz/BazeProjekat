package database;

import java.util.List;

import resource.DBNode;
import resource.data.Row;

public interface Repository {
	DBNode getSchema();
    List<Row> get(String from);
}
