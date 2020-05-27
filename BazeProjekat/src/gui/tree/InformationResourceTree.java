package gui.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import controller.tree.InformationResourceTreeController;
import controller.tree.TreeMouseAdapter;

public class InformationResourceTree extends JTree {

	public InformationResourceTree() {
		addTreeSelectionListener(new InformationResourceTreeController());
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		setEditable(true);
		setInvokesStopCellEditing(true);
		addMouseListener(new TreeMouseAdapter(this));
	}
	
	@Override
	public void startEditingAtPath(TreePath path) {
//		TreeUI tree = getUI();
//
//        if(tree != null) {
//        	((InformationResourceTreeCellEditor)cellEditor).StartEditing();
//            tree.startEditingAtPath(this, path);
//        }
	}

//	public void addProject(ProjectNode projectNode) {
//		WorkspaceModel model = (WorkspaceModel) getModel();
//		((WorkspaceNode) model.getRoot()).addProject(projectNode);
////		SwingUtilities.updateComponentTreeUI(this);
//	}
	
//	public ProjectNode getSelectedProject() {
//		TreePath selectedPath = getSelectionPath();
//		
//		if (selectedPath == null) {
//			return null;
//		}
//		
//		for (Object object : selectedPath.getPath()) {
//			if(object instanceof ProjectNode) {
//				return (ProjectNode) object;
//			}
//		}
//		return null;
//	}
	
}
