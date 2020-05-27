package resource;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class DBNode {
	
	private String name;
	private DBNode parent;
	
	public String getName() {
		return name;
	}
	
	public DBNode getParent() {
		return parent;
	}
	
	public DBNode() {
		this.name = "no name";
		this.parent = null;
	}
	
	public DBNode(String name, DBNode parent) {
		this.name = name;
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
