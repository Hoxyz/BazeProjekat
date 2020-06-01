package actions;

public class ActionManager {
	
	private OpenAddDialogAction openAddDialogAction;
	private OpenUpdateDialogAction openUpdateDialogAction;
	private RemoveSelectedRowsAction removeSelectedRowsAction;
	
	public ActionManager() {
		InitializeActions();
	}
	
	private void InitializeActions() {
		openAddDialogAction = new OpenAddDialogAction();
		openUpdateDialogAction = new OpenUpdateDialogAction();
		removeSelectedRowsAction = new RemoveSelectedRowsAction();
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
}
