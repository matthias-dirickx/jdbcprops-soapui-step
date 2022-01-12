package be.mdi.tooling.soapui.steps.dbprops.model;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import groovy.util.Node;
import groovy.xml.QName;

public class JdbcXmlResultSet {
	private static final QName CHILD_NAME = new QName("Row");
	
	private Map<Integer, JdbcXmlRow> rows;
	private String[] columnNames;
	
	public JdbcXmlResultSet(Node resultSetNode) {
		
		rows = new LinkedHashMap<Integer, JdbcXmlRow>();
		
		@SuppressWarnings("unchecked")
		List<Node> childNodes = resultSetNode.getAt(CHILD_NAME);
		for( Node n : childNodes) {
			JdbcXmlRow row = new JdbcXmlRow(n);
			rows.put(row.getRowNumber(), row);
		}
		
		if( rows.size() > 0 ) {
			// Take the first row, and get the column values
			// Row numbers start at 1
			columnNames = rows.get(1).getValues().keySet().toArray(new String[] {});
		}
	}
	
	public JdbcXmlRow[] getRows() {
		return rows.values().toArray(new JdbcXmlRow[] {});
	}
	
	public JdbcXmlRow getRow(Integer i) {
		return rows.get(i);
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public String[] getColumnValues(int index) {
		return getColumnValues(columnNames[index]);
	}
	
	public String[] getColumnValues(String columnName) {
		List<String> valuesList = new ArrayList<String>();
		
		for( JdbcXmlRow r : getRows() ) {
			valuesList.add(r.getField(columnName));
		}
		
		return valuesList.toArray(new String[] {});
		
	}
	
	public Map<String, String[]> getValuesPerColumn() {
		Map<String, String[]> valuesPerColumn = new LinkedHashMap<String, String[]>();
		for( int i=0; i < columnNames.length; i++) {
			valuesPerColumn.put(columnNames[i], getColumnValues(i));
		}
		return valuesPerColumn;
	}
}
