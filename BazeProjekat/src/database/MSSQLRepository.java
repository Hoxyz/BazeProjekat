package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.settings.Settings;
import resource.DBNode;
import resource.data.Row;
import resource.enums.AttributeType;
import resource.enums.ConstraintType;
import resource.implementation.Attribute;
import resource.implementation.AttributeConstraint;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

public class MSSQLRepository implements Repository {

	private Settings settings;
    private Connection connection;
    
    public Connection getConnection () {
    	return connection;
    }
    
    public Settings getSettings () {
    	return settings;
    }
    
    public MSSQLRepository (Settings settings) {
        this.settings = settings;
    }
    
    private void initConnection () throws SQLException, ClassNotFoundException{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        String ip = (String) settings.getParameter("mssql_ip");
        String database = (String) settings.getParameter("mssql_database");
        String username = (String) settings.getParameter("mssql_username");
        String password = (String) settings.getParameter("mssql_password");
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+"/"+database,username,password);
    }
    
    private void closeConnection () {
        try{
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }
    
    @Override
    public DBNode getSchema () {
        try{
            this.initConnection();

            DatabaseMetaData metaData = connection.getMetaData();
            InformationResource ir = new InformationResource("RAF_BP_Primer");

            String tableType[] = {"TABLE"};

            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);

            while (tables.next()) {

                String tableName = tables.getString("TABLE_NAME");
                
                if (Character.isLowerCase(tableName.charAt(0))) {
                	continue; // :)
                }
                
                Entity newTable = new Entity(tableName, ir);
                ir.addChild(newTable);

                //Koje atribute imaja ova tabela?

                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);
                
                while (columns.next()){
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
                    Attribute attribute = new Attribute(columnName, newTable, AttributeType.valueOf(columnType.toUpperCase().split(" ")[0]), columnSize);
                    newTable.addChild(attribute);
                }
                
                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableName);
                
                while (primaryKeys.next()) {
                	String columnName = primaryKeys.getString("COLUMN_NAME");
                	String pkName = primaryKeys.getString("PK_NAME");
                	AttributeConstraint attrConstraint = new AttributeConstraint(pkName, ((Attribute)newTable.getChildByName(columnName)), ConstraintType.PRIMARY_KEY);
                	((Attribute)newTable.getChildByName(columnName)).addChild(attrConstraint);
                }
                
                ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
                
                while (foreignKeys.next()) {
                	String fkTableName = foreignKeys.getString("FKTABLE_NAME");
                	String columnName = foreignKeys.getString("FKCOLUMN_NAME");
                	String fkName = foreignKeys.getString("FK_NAME");
                	AttributeConstraint attrConstraint = new AttributeConstraint(fkName, ((Attribute)newTable.getChildByName(columnName)), ConstraintType.FOREIGN_KEY);
                	((Attribute)newTable.getChildByName(columnName)).addChild(attrConstraint);
                }
            }
            
            tables = metaData.getTables(connection.getCatalog(), null, null, tableType);
            
            while (tables.next()) {
            	String tableName = tables.getString("TABLE_NAME");
            	ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
            	
                while (foreignKeys.next()) {
                	String fkName = foreignKeys.getString("FKCOLUMN_NAME");
                	String fkTableName = foreignKeys.getString("PKTABLE_NAME");
                	String fkReferencedName = foreignKeys.getString("PKCOLUMN_NAME");
                	
                	// foreign key in this table
                	Attribute attribute1 = (Attribute)((Entity)ir.getChildByName(tableName)).getChildByName(fkName);
                	// foreign key in other table
                	Attribute attribute2 = (Attribute)((Entity)ir.getChildByName(fkTableName)).getChildByName(fkReferencedName);
                	
                	attribute1.setInRelationWith(attribute2);
                	
                	System.out.println(tableName + "." + fkName + " TO " + fkTableName + "." + fkReferencedName);
                	//attribute2.setInRelationWith(attribute1);
                	
                	((Entity)ir.getChildByName(tableName)).addRelation((Entity)ir.getChildByName(fkTableName));
                	((Entity)ir.getChildByName(fkTableName)).addRelation((Entity)ir.getChildByName(tableName));
                }
            }
            
            return ir;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return null;
    }

    @Override
    public List<Row> get (String from) {

        List<Row> rows = new ArrayList<>();
        try{
            this.initConnection();

            String query = "SELECT * FROM " + from;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Row row = new Row();
                row.setName(from);

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }
    
    public Map<String, String> getColumns (String from) {
    	Map<String, String> columns = new HashMap<>();
    	
    	try {
    		this.initConnection();
    		
    		String query = "SELECT * FROM " + from;
    		PreparedStatement preparedStatement = connection.prepareStatement(query);
    		ResultSet rs = preparedStatement.executeQuery();
    		
    		while(rs.next()) {
    			ResultSetMetaData resultSetMetaData = rs.getMetaData();
    			for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
    				columns.put(resultSetMetaData.getColumnName(i), resultSetMetaData.getColumnTypeName(i));
    			}
    		}
    	} 
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		this.closeConnection();
    	}
    	return columns;
    }
    
    public List<Row> SelectQuery (String query, List<Object> values) {
		List<Row> rows = new ArrayList<Row>();
		
    	try {
    		this.initConnection();
    		
			if(connection == null) {
				System.out.println("null");
				return null;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			
			for(int i = 0; i < values.size(); i++) {
				ps.setObject(i+1, values.get(i));
			}
			
			ResultSet resultRows = ps.executeQuery();
			
			ResultSetMetaData rsMetaData = resultRows.getMetaData();
			
			while (resultRows.next()) {
				Row row = new Row();
				
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					row.addField(rsMetaData.getColumnName(i), resultRows.getObject(i));
				}
				
				rows.add(row);
			}
			
    	} catch (Exception e) {
    		e.printStackTrace();
    		rows = null;
    	} finally {
    		closeConnection();
    	}
    	return rows;
    }
    
    public void addRowQuery (String query, List<Object> values) {
    	try {
    		this.initConnection();
    		
			if(connection == null) {
				System.out.println("null");
				return;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			
			for(int i = 0; i < values.size(); i++) {
				ps.setObject(i+1, values.get(i));
			}
			
			ps.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		closeConnection();
    	}
    }
    
    public void removeRowQuery (String query, List<Object> values) {
    	try {
    		this.initConnection();
    		
			if(connection == null) {
				System.out.println("null");
				return;
			}
			PreparedStatement ps = connection.prepareStatement(query);
			
			for(int i = 0; i < values.size(); i++) {
				ps.setObject(i+1, values.get(i));
			}
			
			ps.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		closeConnection();
    	}
    }
}
