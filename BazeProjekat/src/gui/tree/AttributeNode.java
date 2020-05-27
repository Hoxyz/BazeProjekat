package gui.tree;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.AttributeConstraint;

public class AttributeNode extends DefaultMutableTreeNode {

	private Attribute attribute;
	private Vector<AttributeConstraintNode> attributeConstraintNodes;
	
	public Attribute getAttribute() {
		return attribute;
	}
	
	public AttributeNode(Attribute attribute) {
		super();
		
		this.attribute = attribute;
		attributeConstraintNodes = new Vector<>();
		
		for (DBNode attributeConstraint : attribute.getChildren()) {
			AttributeConstraintNode attributeConstraintNode = new AttributeConstraintNode((AttributeConstraint) attributeConstraint);
			attributeConstraintNodes.add(attributeConstraintNode);
			attributeConstraintNode.setParent(this);
		}
	}
	
	@Override
	public String toString() {
		return attribute.toString();
	}

	@Override
	public TreeNode getChildAt(int index) {
		return (TreeNode) attribute.getChildren().get(index);
	}
	
	@Override
	public int getChildCount() {
		return attribute.getChildren().size();
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
		if (attribute.getChildren().size() == 0) {
			return true;
		}
		return false;
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
	
	public int getAttributeConstraintIndex(AttributeConstraintNode child) {
		return attributeConstraintNodes.indexOf(child);
	}
	
}
