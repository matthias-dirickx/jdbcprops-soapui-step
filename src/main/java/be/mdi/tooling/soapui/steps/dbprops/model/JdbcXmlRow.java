package be.mdi.tooling.soapui.steps.dbprops.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import groovy.util.Node;

public class JdbcXmlRow {
	
	private static final String ROW_NUMBER_ATTRIBUTE_NAME = "rowNumber";
	
	private Map<String, String> columns;
	private int rowNumber;
	
	public JdbcXmlRow(Node n) {
		columns = new LinkedHashMap<String, String>();
		@SuppressWarnings("unchecked")
		Iterator<Node> iter = n.iterator();
		while( iter.hasNext() ) {
			Node nextNode = iter.next();
			String colName = nextNode.name().toString();
			
			// In the groovy.util.Node implementation all value items
			// seem to be returned as arrays when they come from an XML.
			String colValue = getValueWithoutBrackets(nextNode.value().toString());
			
			columns.put(colName, getValueWithoutBrackets(colValue));
		}
		
		@SuppressWarnings("unchecked")
		Map<String, String> attributes = n.attributes();
		rowNumber = Integer.parseInt(attributes.get(ROW_NUMBER_ATTRIBUTE_NAME));
	}
	
	private String getValueWithoutBrackets(String val) {
		return val.replaceAll("^\\[", "").replaceAll("\\]$", "");
	}
	
	public String getField(String name) {
		return columns.get(name);
	}
	
	public Map<String, String> getValues() {
		return columns;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
}
