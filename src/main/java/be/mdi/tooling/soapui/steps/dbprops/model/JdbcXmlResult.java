package be.mdi.tooling.soapui.steps.dbprops.model;

import java.util.ArrayList;
import java.util.List;

import groovy.util.Node;
import groovy.xml.QName;

/**
 * 
 * @author Matthias.Dirickx
 *
 */
public class JdbcXmlResult {
	
	private static final QName CHILD_NAME = new QName("ResultSet");
	
	private List<JdbcXmlResultSet> resultSets;
	
	public JdbcXmlResult(Node rootNode) {
		
		resultSets = new ArrayList<JdbcXmlResultSet>();
		
		@SuppressWarnings("unchecked")
		List<Node> childNodes = rootNode.getAt(CHILD_NAME);
		for( Node n : childNodes) {
			resultSets.add(new JdbcXmlResultSet(n));
		}
	}
	
	public List<JdbcXmlResultSet> getResultSets() {
		return resultSets;
	}
	
	public JdbcXmlResultSet getResultSet(int index) {
		return resultSets.get(index);
	}
	
	public int size() {
		return resultSets.size();
	}
}
