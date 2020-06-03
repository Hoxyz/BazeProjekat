package resource.implementation;

import java.util.ArrayList;
import java.util.List;

import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.AttributeType;

public class Attribute extends DBNodeComposite {

	private AttributeType attributeType;
	private int length;

	private Attribute inRelationWith;
	private List<Attribute> referencedBy;
	
	public Attribute(String name, DBNode parent) {
		super(name, parent);
		referencedBy = new ArrayList<Attribute>();
	}
	
	public Attribute(String name, DBNode parent, AttributeType attributeType, int length) {
		super(name, parent);
        this.attributeType = attributeType;
        this.length = length;
        referencedBy = new ArrayList<Attribute>();
	}
	
	public void addReference(Attribute attr) {
		referencedBy.add(attr);
	}
	
	public void setInRelationWith (Attribute inRelationWith) {
		this.inRelationWith = inRelationWith;
	}
	
	public Attribute getInRelationWith () {
		return inRelationWith;
	}
	
	@Override
	public void addChild(DBNode child) {
		if(child != null && child instanceof AttributeConstraint) {
			AttributeConstraint attributeConstraint = (AttributeConstraint) child;
			this.getChildren().add(attributeConstraint);
		}
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public AttributeType getAttributeType() {
		return attributeType;
	}
}
