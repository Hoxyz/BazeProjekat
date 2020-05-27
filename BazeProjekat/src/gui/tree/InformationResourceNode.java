package gui.tree;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import resource.DBNode;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

public class InformationResourceNode extends DefaultMutableTreeNode {
	
	private InformationResource ir;
	private Vector<EntityNode> entityNodes;
	
	public InformationResource getInformationResource() {
		return ir;
	}
	
	public InformationResourceNode() {
		super();
	}
	
	public InformationResourceNode(InformationResource ir) {
		super();
		this.ir = ir;
		
		for (DBNode entity : ir.getChildren()) {
			EntityNode entityNode = new EntityNode((Entity) entity);
			entityNodes.add(entityNode);
			entityNode.setParent(this);
		}
	}
	
	@Override
	public String toString() {
		return ir.getName();
	}

	@Override
	public TreeNode getChildAt(int index) {
		return (TreeNode) ir.getChildren().get(index);
	}

	@Override
	public int getChildCount() {
		return ir.getChildren().size();
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		//TODO
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		if (ir.getChildren().size() == 0) {
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
		//TODO
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		//TODO
	}

	@Override
	public void remove(MutableTreeNode node) {
		//TODO
	}

	@Override
	public void remove(int index) {
		//TODO
	}
	
	public int getEntityIndex(EntityNode e) {
		return entityNodes.indexOf(e);
	}
	
}
