package app;

import database.Database;
import database.DatabaseImplementation;
import database.MSSQLRepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import gui.table.TableModel;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import resource.implementation.InformationResource;
import utils.Constants;

public class AppCore extends PublisherImplementation {
	
	private Database database;
    private Settings settings;
    private TableModel tableModel;
    
    //TODO
    private InformationResource ir;
    
    public InformationResource getInformationResource() {
    	return ir;
    }
    
    public AppCore() {
        this.settings = initSettings();
        this.database = new DatabaseImplementation(new MSSQLRepository(this.settings));
        tableModel = new TableModel();
        ir = new InformationResource("Test");
    }

    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mssql_ip", Constants.MSSQL_IP);
        settingsImplementation.addParameter("mssql_database", Constants.MSSQL_DATABASE);
        settingsImplementation.addParameter("mssql_username", Constants.MSSQL_USERNAME);
        settingsImplementation.addParameter("mssql_password", Constants.MSSQL_PASSWORD);
        return settingsImplementation;
    }


    public void loadResource(){
        ir = (InformationResource) this.database.loadResource();
        this.NotifySubscribers(new Notification(NotificationCode.RESOURCE_LOADED, ir));
    }

    public void readDataFromTable(String fromTable){

        tableModel.setRows(this.database.readDataFromTable(fromTable));

        //Zasto ova linija moze da ostane zakomentarisana?
        //this.NotifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.getTableModel()));
    }


    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

	public Database getDatabase() {
		return database;
	}
}
