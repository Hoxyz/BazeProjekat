package actions;

public class ActionManager {
	
	private OpenAddDialogAction openAddDialogAction;
	private OpenUpdateDialogAction openUpdateDialogAction;
	private RemoveSelectedRowsAction removeSelectedRowsAction;
	
	private OpenSearchDialogAction openSearchDialogAction;
	private OpenReportDialogAction openReportDialogAction;
	
	public ActionManager() {
		InitializeActions();
	}
	
	private void InitializeActions() {
		openAddDialogAction = new OpenAddDialogAction();
		openUpdateDialogAction = new OpenUpdateDialogAction();
		removeSelectedRowsAction = new RemoveSelectedRowsAction();
		
		openSearchDialogAction = new OpenSearchDialogAction();
		openReportDialogAction = new OpenReportDialogAction();
	}
	
	public OpenAddDialogAction getOpenAddDialogAction() {
		return openAddDialogAction;
	}
	
	public OpenUpdateDialogAction getOpenUpdateDialogAction () {
		return openUpdateDialogAction;
	}
	
	public RemoveSelectedRowsAction getRemoveSelectedRowsAction () {
		return removeSelectedRowsAction;
	}
	
	public OpenSearchDialogAction getOpenSearchDialogAction () {
		return openSearchDialogAction;
	}
	
	public OpenReportDialogAction getOpenReportDialogAction () {
		return openReportDialogAction;
	}
}
