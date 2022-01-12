package be.mdi.tooling.soapui.steps.dbprops.listeners;

import be.mdi.tooling.soapui.steps.dbprops.enums.ScopeType;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunListener;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;

public class JdbcPropsLaunchTestCaseRunListener implements TestRunListener {

	@Override
	public void afterRun(TestCaseRunner runner, TestCaseRunContext context) {
		LaunchLevelRegister.setLaunchLevel(ScopeType.TESTCASE);
	}

	@Override
	public void afterStep(TestCaseRunner runner, TestCaseRunContext context, TestStepResult testStepResult) {
		// Do nothing here.
	}

	@Override
	public void beforeRun(TestCaseRunner runner, TestCaseRunContext context) {
		LaunchLevelRegister.resetIfType(ScopeType.TESTCASE);
	}

	@Override
	public void beforeStep(TestCaseRunner runner, TestCaseRunContext context) {
		// Do nothing here.
	}

	@Override
	public void beforeStep(TestCaseRunner runner, TestCaseRunContext context, TestStep testStep) {
		// Do nothing here.
	}

}
