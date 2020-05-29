package actions;

public class ActionManager {
	
	private OpenAddDialogAction openAddDialogAction;
	private AddRowAction addRowAction;
	
	public ActionManager() {
		InitializeActions();
	}
	
	private void InitializeActions() {
		addRowAction = new AddRowAction();
		openAddDialogAction = new OpenAddDialogAction();
	}
	
	public OpenAddDialogAction getOpenAddDialogAction() {
		return openAddDialogAction;
	}
	
	public AddRowAction getAddRowAction() {
		return addRowAction;
	}
	
}
