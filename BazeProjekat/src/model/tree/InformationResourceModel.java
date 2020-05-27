package model.tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import gui.tree.AttributeNode;
import gui.tree.EntityNode;
import gui.tree.InformationResourceNode;
import gui.tree.AttributeConstraintNode;

public class InformationResourceModel extends DefaultTreeModel {

	public InformationResourceModel() {
		super(new InformationResourceNode());
	}

	public InformationResourceModel(TreeNode root) {
		super(root);
	}

	public InformationResourceModel(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof InformationResourceNode) {
			return ((InformationResourceNode) parent).getChildAt(index);
		}
		if (parent instanceof EntityNode) {
			return ((EntityNode) parent).getChildAt(index);
		}
		if (parent instanceof AttributeNode) {
			return ((AttributeNode) parent).getChildAt(index);
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof InformationResourceNode) {
			return ((InformationResourceNode) parent).getInformationResource().getChildren().size();
		}
		if (parent instanceof EntityNode) {
			return ((EntityNode) parent).getEntity().getChildren().size();
		}
		if (parent instanceof AttributeNode) {
			return ((AttributeNode) parent).getAttribute().getChildren().size();
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof InformationResourceNode) {
			return ((InformationResourceNode) node).isLeaf();
		}
		if (node instanceof EntityNode) {
			return ((EntityNode) node).isLeaf();
		}
		if (node instanceof AttributeNode) {
			return ((AttributeNode) node).isLeaf();
		}
		return true;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// Moguc exception u slucaju kada child nije prikladne klase
		if (parent instanceof InformationResourceNode) {
			return ((InformationResourceNode) parent).getEntityIndex((EntityNode) child);
		}
		if (parent instanceof EntityNode) {
			return ((EntityNode) parent).getAttributeIndex((AttributeNode) child);
		}
		if (parent instanceof AttributeNode) {
			return ((AttributeNode) parent).getAttributeConstraintIndex((AttributeConstraintNode) child);
		}
		return -1;
	}
	
	
	
}
