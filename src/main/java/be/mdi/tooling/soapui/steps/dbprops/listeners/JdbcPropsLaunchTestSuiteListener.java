package be.mdi.tooling.soapui.steps.dbprops.listeners;

import be.mdi.tooling.soapui.steps.dbprops.enums.ScopeType;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestSuiteRunContext;
import com.eviware.soapui.model.testsuite.TestSuiteRunListener;
import com.eviware.soapui.model.testsuite.TestSuiteRunner;

public class JdbcPropsLaunchTestSuiteListener implements TestSuiteRunListener {

	@Override
	public void afterRun(TestSuiteRunner runner, TestSuiteRunContext context) {
		LaunchLevelRegister.resetIfType(ScopeType.TESTSUITE);
	}

	@Override
	public void afterTestCase(TestSuiteRunner runner, TestSuiteRunContext context, TestCaseRunner testCaseRunner) {
		// Do nothing here.
	}

	@Override
	public void beforeRun(TestSuiteRunner runner, TestSuiteRunContext context) {
		LaunchLevelRegister.setLaunchLevel(ScopeType.TESTSUITE);
	}

	@Override
	public void beforeTestCase(TestSuiteRunner runner, TestSuiteRunContext context, TestCase testCase) {
		// Do nothing here.
	}

}
