package controller.tree;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class InformationResourceTreeController implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		System.out.println(path);
	}
	
}
