package be.mdi.tooling.soapui.steps.dbprops.listeners;

import be.mdi.tooling.soapui.steps.dbprops.enums.ScopeType;
import com.eviware.soapui.model.testsuite.ProjectRunContext;
import com.eviware.soapui.model.testsuite.ProjectRunListener;
import com.eviware.soapui.model.testsuite.ProjectRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.model.testsuite.TestSuiteRunner;

public class JdbcPropsLaunchProjectListener implements ProjectRunListener {

	@Override
	public void afterRun(ProjectRunner runner, ProjectRunContext context) {
		LaunchLevelRegister.resetIfType(ScopeType.PROJECT);
	}

	@Override
	public void afterTestSuite(ProjectRunner runner, ProjectRunContext context, TestSuiteRunner suiteRunner) {
		// Do nothing here
	}

	@Override
	public void beforeRun(ProjectRunner runner, ProjectRunContext context) {
		LaunchLevelRegister.setLaunchLevel(ScopeType.PROJECT);
	}

	@Override
	public void beforeTestSuite(ProjectRunner runner, ProjectRunContext context, TestSuite testSuite) {
		// Do nothing here
	}

}
