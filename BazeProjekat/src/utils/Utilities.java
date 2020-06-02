package utils;

import resource.enums.AttributeType;

public class Utilities {
	
	private Utilities () {}
	
	public static boolean AttributeIsNumeric(AttributeType attrType) {
		return attrType == AttributeType.INT || attrType == AttributeType.NUMERIC || 
				attrType == AttributeType.BIGINT || attrType == AttributeType.FLOAT || attrType == AttributeType.REAL;
	}
}
