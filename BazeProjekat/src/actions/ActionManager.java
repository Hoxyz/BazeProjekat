package actions;

public class ActionManager {
	
	private OpenAddDialogAction openAddDialogAction;
	private RemoveSelectedRowsAction removeSelectedRowsAction;
	
	public ActionManager() {
		InitializeActions();
	}
	
	private void InitializeActions() {
		openAddDialogAction = new OpenAddDialogAction();
		removeSelectedRowsAction = new RemoveSelectedRowsAction();
	}
	
	public OpenAddDialogAction getOpenAddDialogAction() {
		return openAddDialogAction;
	}
	
	public RemoveSelectedRowsAction getRemoveSelectedRowsAction () {
		return removeSelectedRowsAction;
	}
}
