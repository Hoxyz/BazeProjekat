package gui.tree;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.Entity;

public class EntityNode extends DefaultMutableTreeNode {

	private Entity entity;
	private Vector<AttributeNode> attributeNodes;
	
	public Entity getEntity() {
		return entity;
	}
	
	public EntityNode(Entity entity) {
		super();
		
		this.entity = entity;
		attributeNodes = new Vector<AttributeNode>();
				
		for (DBNode attribute : entity.getChildren()) {
			AttributeNode attributeNode = new AttributeNode((Attribute) attribute);
			attributeNodes.add(attributeNode);
			attributeNode.setParent(this);
		}
	}

	@Override
	public String toString() {
		return entity.toString();
	}

	@Override
	public TreeNode getChildAt(int index) {
		return (TreeNode) attributeNodes.get(index);
	}
	
	@Override
	public int getChildCount() {
		return entity.getChildren().size();
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
		if (entity.getChildren().size() == 0) {
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
	
	public int getAttributeIndex(AttributeNode a) {
		return attributeNodes.indexOf(a);
	}
	
}
