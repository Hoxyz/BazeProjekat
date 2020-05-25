package database;

import java.util.List;

import resource.DBNode;
import resource.data.Row;

public class DatabaseImplementation implements Database {

	private Repository repository;
	
	public Repository getRepository() {
		return repository;
	}
	
	public DatabaseImplementation(Repository repository) {
		this.repository = repository;
	}
	
	@Override
	public DBNode loadResource() {
		return this.repository.getSchema();
	}

	@Override
	public List<Row> readDataFromTable(String tableName) {
		return repository.get(tableName);
	}

}
