package gui.tree;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import resource.implementation.AttributeConstraint;

public class AttributeConstraintNode extends DefaultMutableTreeNode {

	private AttributeConstraint attributeConstraint;
	
	public AttributeConstraintNode(AttributeConstraint attributeConstraint) {
		this.attributeConstraint = attributeConstraint;
	}
	
	@Override
	public String toString() {
		return attributeConstraint.toString();
	}

	@Override
	public TreeNode getChildAt(int index) {
		return null;
	}
	
	@Override
	public int getChildCount() {
		return 0;
	}
	
	@Override
	public TreeNode getParent() {
		return parent;
	}
	
	@Override
	public int getIndex(TreeNode node) {
		//TODO
		return 0;
	}
	
	@Override
	public boolean getAllowsChildren() {
		//TODO
		return true;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}
	
	@Override
	public Enumeration<TreeNode> children() {
		return null;
	}

	@Override
	public void add(MutableTreeNode newChild) {
		//TODO ?
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		//TODO ?
	}

	@Override
	public void remove(MutableTreeNode node) {
		//TODO ?
	}

	@Override
	public void remove(int index) {
		//TODO 
	}
	
	@Override
	public void removeFromParent() {
		//TODO
	}
	
}
