package resource.implementation;

import java.util.ArrayList;
import java.util.List;

import resource.DBNode;
import resource.DBNodeComposite;

public class Entity extends DBNodeComposite {
	
	List<Entity> relations;
	
	public List<Entity> getRelations () {
		return relations;
	}
	
	public void addRelation (Entity table) {
		relations.add(table);
	}
	
	public void removeRelation (Entity table) {
		relations.remove(table);
	}
	
	public Entity (String name, DBNode parent) {
        super(name, parent);
        relations = new ArrayList<Entity>();
    }
	
    @Override
    public void addChild (DBNode child) {
        if (child != null && child instanceof Attribute){
            Attribute attribute = (Attribute) child;
            this.getChildren().add(attribute);
        }
    }
    
    @Override
    public String toString () {
    	return super.toString();
    }
}
