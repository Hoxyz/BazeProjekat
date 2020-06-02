package resource.implementation;

import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.AttributeType;

public class Attribute extends DBNodeComposite {

	private AttributeType attributeType;
	private int length;
//	TODO
//	inRelationWith da predstavi vezu: npr Employees.DeptID pokazuje na Departments.DeptID
//	htela da ako je neki atribut strani kljuc on "pokazuje" na neki primarni kljuc iz neke
//	strane tabele
	private Attribute inRelationWith;
	
	public Attribute(String name, DBNode parent) {
		super(name, parent);
	}
	
	public Attribute(String name, DBNode parent, AttributeType attributeType, int length) {
		super(name, parent);
        this.attributeType = attributeType;
        this.length = length;
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
