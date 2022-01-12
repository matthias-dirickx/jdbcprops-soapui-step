package be.mdi.tooling.soapui.steps.dbprops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.mdi.tooling.soapui.steps.dbprops.enums.AxisType;
import be.mdi.tooling.soapui.steps.dbprops.enums.ScopeType;
import be.mdi.tooling.soapui.steps.dbprops.model.JdbcXmlResult;
import be.mdi.tooling.soapui.steps.dbprops.model.JdbcXmlResultSet;
import be.mdi.tooling.soapui.steps.dbprops.model.JdbcXmlRow;
import groovy.util.Node;
import groovy.util.XmlParser;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.config.TestStepConfig;
import com.eviware.soapui.impl.wsdl.AbstractTestPropertyHolderWsdlModelItem;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.teststeps.JdbcRequestTestStep;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStepResult;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStepWithProperties;
import com.eviware.soapui.model.propertyexpansion.PropertyExpansion;
import com.eviware.soapui.model.propertyexpansion.PropertyExpansionContainer;
import com.eviware.soapui.model.propertyexpansion.PropertyExpansionUtils;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.model.testsuite.TestStepResult.TestStepStatus;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.xml.XmlObjectConfigurationBuilder;
import com.eviware.soapui.support.xml.XmlObjectConfigurationReader;

import be.mdi.tooling.soapui.steps.dbprops.listeners.LaunchLevelRegister;


public class JdbcPropsTestStep extends WsdlTestStepWithProperties implements PropertyExpansionContainer
{
	private String sourceStep;
	private String suffix;
	private AxisType axisType;
	private ScopeType validityScope;
	private boolean limitOne;
	private boolean cleanPropertiesAfterRun;
	
	private Map<ScopeType, List<String>> killList;
	
	private AbstractTestPropertyHolderWsdlModelItem<?> targetModelItem;
	private JdbcXmlResult jdbcXmlResult;

	protected JdbcPropsTestStep( WsdlTestCase testCase, TestStepConfig config, boolean forLoadTest )
	{
		super( testCase, config, true, forLoadTest );

		if( !forLoadTest )
		{
			setIcon( UISupport.createImageIcon( "email.png" ) );
		}
		
		// Default values
		// Overridden by config
		suffix = "";
		axisType = AxisType.ROW;
		validityScope = ScopeType.TESTCASE;
		limitOne = true;
		cleanPropertiesAfterRun = true;

		if( config.getConfig() != null )
		{
			readConfig( config );
		}
		
		LaunchLevelRegister.subscribe(this);
		initKillList();
	}
	
	private void initKillList()
	{
		killList = new HashMap<ScopeType, List<String>>();
		for( ScopeType scopeType : ScopeType.values()) {
			killList.put(scopeType, new ArrayList<String>());
		}
	}

	private void readConfig( TestStepConfig config )
	{
		XmlObjectConfigurationReader reader = new XmlObjectConfigurationReader( config.getConfig() );
		sourceStep = reader.readString("sourceStep", sourceStep);
		suffix = reader.readString( "suffix", "" );
		validityScope = ScopeType.valueOf(reader.readString( "validityScope", ScopeType.TESTCASE.name()));
		limitOne = reader.readBoolean("limitOne", true);
		axisType = AxisType.valueOf(reader.readString("axisType", AxisType.COLUMN.name()));
		cleanPropertiesAfterRun = reader.readBoolean("cleanPropertiesAfterRun", true);
	}

	private void updateConfig()
	{
		XmlObjectConfigurationBuilder builder = new XmlObjectConfigurationBuilder();
		builder.add( "sourceStep", sourceStep );
		builder.add( "suffix", suffix );
		builder.add( "validityScope", validityScope.name());
		builder.add( "limitOne", limitOne);
		builder.add( "axisType", axisType.name());
		builder.add( "cleanPropertiesAfterRun", cleanPropertiesAfterRun);
		getConfig().setConfig( builder.finish() );
	}

	public String getSuffix()
	{
		return suffix;
	}

	public void setSuffix( String subject )
	{
		String old = this.suffix;
		this.suffix = subject;
		updateConfig();
		notifyPropertyChanged( "suffix", old, subject );
	}

	public ScopeType getValidityScope()
	{
		return validityScope;
	}

	public void setValidityScope( ScopeType validityScope )
	{
		ScopeType old = this.validityScope;
		this.validityScope = validityScope;
		updateConfig();
		notifyPropertyChanged( "validityScope", old, validityScope );
	}

	public boolean getLimitOne()
	{
		return limitOne;
	}

	public void setLimitOne( boolean limitOne )
	{
		boolean old = this.limitOne;
		this.limitOne = limitOne;
		updateConfig();
		notifyPropertyChanged( "limitOne", old, limitOne );
	}
	
	public boolean getCleanPropertiesAfterRun() {
		return cleanPropertiesAfterRun;
	}

	public void setCleanPropertiesAfterRun(boolean cleanPropertiesAfterRun)
	{
		boolean old = this.cleanPropertiesAfterRun;
		this.cleanPropertiesAfterRun = cleanPropertiesAfterRun;
		updateConfig();
		notifyPropertyChanged( "cleanPropertiesAfterRun", old, cleanPropertiesAfterRun);
	}

	public String getSourceStep()
	{
		return sourceStep;
	}

	public void setSourceStep( String sourceStep )
	{
		String old = this.sourceStep;
		this.sourceStep = sourceStep;
		updateConfig();
		notifyPropertyChanged( "sourceStep", old, sourceStep );
	}

	public AxisType getAxisType()
	{
		return axisType;
	}

	public void setAxisType( AxisType axisType )
	{
		AxisType old = this.axisType;
		this.axisType = axisType;
		updateConfig();
		notifyPropertyChanged( "axisType", old, axisType );
	}
	
	private AbstractTestPropertyHolderWsdlModelItem<?> getTargetModelItem(ScopeType validityScope)
	{
		AbstractTestPropertyHolderWsdlModelItem<?> targetModelItem;
		switch ( validityScope ) {
		case TESTCASE:
			targetModelItem = getTestCase();
			break;
		case TESTSUITE:
			targetModelItem = getTestCase().getTestSuite();
			break;
		case PROJECT:
			targetModelItem = getTestCase().getTestSuite().getProject();
			break;
		default:
			targetModelItem = getTestCase();
		}
		return targetModelItem;
	}

	public TestStepResult run( TestCaseRunner testRunner, TestCaseRunContext context )
	{
		WsdlTestStepResult result = new WsdlTestStepResult( this );
		result.startTimer();

		try
		{
			// Retrieve the response from the selected JDBC Step.
			JdbcRequestTestStep jdbcStep = (JdbcRequestTestStep) getTestCase().getTestStepByName(sourceStep);
			String responseString = jdbcStep.getResponseContent();
			
			XmlParser parser = new XmlParser();
			Node root = parser.parseText(responseString);
			
			// Marshall it into a format we can work with
			jdbcXmlResult = new JdbcXmlResult(root);
			
			// Set the ModelItem used according to the scope
			targetModelItem = getTargetModelItem(validityScope);
			
			// Get the axis, and based on that set property root names.
			switch( axisType ) {
			case ROW:
				setRowAxisProperties();
				break;
			case COLUMN:
				setColumnAxisProperties();
				break;
			default:	
			}
			
			result.setStatus( TestStepStatus.OK );
		}
		catch( Exception ex )
		{
			SoapUI.logError( ex );
			result.setError( ex );
			result.setStatus( TestStepStatus.FAILED );
		}

		result.stopTimer();

		return result;
	}
	
	private String getFullPropertyName(String rootPropertyName, int index)
	{
		String propertyName = rootPropertyName;
		if( !suffix.isEmpty() ) {
			propertyName = String.format("%s_%s", propertyName, suffix);
		}
		if( !limitOne ) {
			propertyName = String.format("%s_%s", propertyName, Integer.toString(index));
		}
		return propertyName;
	}
	
	private void setRowAxisProperties()
	{
		// For now only get the first resultset
		JdbcXmlResultSet rs = jdbcXmlResult.getResultSet(0);
		
		//The column names are the property variables
		if( limitOne ) {
			// Only take the first one
			JdbcXmlRow row = rs.getRows()[0];
			setARowProperty(row);
		} else {
			// Iterate over the collection and append them all
			for( JdbcXmlRow row : rs.getRows() ) {
				setARowProperty(row);
			}
		}
	}
	
	private void setARowProperty(JdbcXmlRow row)
	{
		for( String f : row.getValues().keySet() ) {
			String propertyName = getFullPropertyName(f, row.getRowNumber());
			genericSetPropertyValue(propertyName, row.getField(f));
		}
	}
	
	private void setColumnAxisProperties()
	{
		// For now only get the first resultset
		JdbcXmlResultSet rs = jdbcXmlResult.getResultSet(0);
		String[] cols = rs.getColumnNames();
		String[] propRootNames = rs.getColumnValues(0);
		
		/*
		 * Column 1 is the property name.
		 * Subsequent columns are value iterations.
		 */
		// Do the first one
		setAColumnProperty(propRootNames, rs.getColumnValues(1), 1);
		
		// Do the rest if limitOne is false
		if( !limitOne ) {
			for( int i=2; i<cols.length; i++ ) {
				setAColumnProperty(propRootNames, rs.getColumnValues(i), i);
			}
		}
	}
	
	private void setAColumnProperty(String[] propRootNames, String[] propValues, Integer propertiesColumnIndex)
	{
		for(int i=0; i<propRootNames.length; i++ ) {
			String propertyName = getFullPropertyName(propRootNames[i], propertiesColumnIndex);
		    String propertyValue = propValues[i];
		    genericSetPropertyValue(propertyName, propertyValue);
		}
	}
	
	private void genericSetPropertyValue(String propertyName, String propertyValue)
	{
	    targetModelItem.setPropertyValue(propertyName, propertyValue);
	    killList.get(validityScope).add(propertyName);
	}
	
	public void killProperties()
	{
		if( cleanPropertiesAfterRun ) {
			for( ScopeType scopeType : killList.keySet() ) {
				AbstractTestPropertyHolderWsdlModelItem<?> targetModelItem = getTargetModelItem(scopeType);
				for( String propName : killList.get(scopeType) ) {
					targetModelItem.removeProperty(propName);
				}
			}
		}
	}

	public PropertyExpansion[] getPropertyExpansions()
	{
		List<PropertyExpansion> result = new ArrayList<PropertyExpansion>();
		result.addAll( PropertyExpansionUtils.extractPropertyExpansions( this, this, "suffix" ) );
		return result.toArray( new PropertyExpansion[result.size()] );
	}
}
