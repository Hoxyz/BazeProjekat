package controller.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.TreePath;

import gui.MainFrame;
import gui.tree.EntityNode;
import gui.tree.InformationResourceTree;

public class TreeMouseAdapter extends MouseAdapter {

	InformationResourceTree irTree;
	
	public TreeMouseAdapter(InformationResourceTree irTree) {
		this.irTree = irTree;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TreePath treePath = irTree.getPathForLocation(e.getX(), e.getY());
			
			if (treePath != null) {
				Object entityObj = treePath.getLastPathComponent();
				if (entityObj instanceof EntityNode) {
					MainFrame.getInstance().getTablePane().openTable(((EntityNode) entityObj).getEntity());
				}
			}
		}
	}
}
