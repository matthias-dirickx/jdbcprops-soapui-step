package be.mdi.tooling.soapui.steps.dbprops;

import com.eviware.soapui.config.TestStepConfig;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStep;
import com.eviware.soapui.impl.wsdl.teststeps.registry.WsdlTestStepFactory;

/**
 * The actual factory class that creates new EMailTestSteps from scratch or an XMLBeans config.
 */

public class JdbcPropsTestStepFactory extends WsdlTestStepFactory
{
	private static final String JDBC_PROPS_STEP = "jdbc-props-step";

	public JdbcPropsTestStepFactory()
	{
		super( JDBC_PROPS_STEP, "JDBC Properties TestStep", "Sets database query results as properties.", "email.png" );
	}

	public WsdlTestStep buildTestStep( WsdlTestCase testCase, TestStepConfig config, boolean forLoadTest )
	{
		return new JdbcPropsTestStep( testCase, config, forLoadTest );
	}

	public TestStepConfig createNewTestStep( WsdlTestCase testCase, String name )
	{
		TestStepConfig testStepConfig = TestStepConfig.Factory.newInstance();
		testStepConfig.setType( JDBC_PROPS_STEP );
		testStepConfig.setName( name );
		return testStepConfig;
	}

	public boolean canCreate()
	{
		return true;
	}
}
