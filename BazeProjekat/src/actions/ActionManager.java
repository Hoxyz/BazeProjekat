package actions;

public class ActionManager {
	
	private OpenAddDialogAction openAddDialogAction;
	
	public ActionManager() {
		InitializeActions();
	}
	
	private void InitializeActions() {
		openAddDialogAction = new OpenAddDialogAction();
	}
	
	public OpenAddDialogAction getOpenAddDialogAction() {
		return openAddDialogAction;
	}
}
