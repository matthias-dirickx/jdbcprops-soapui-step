package be.mdi.tooling.soapui.steps.dbprops.listeners;

import java.util.ArrayList;
import java.util.List;

import be.mdi.tooling.soapui.steps.dbprops.JdbcPropsTestStep;
import be.mdi.tooling.soapui.steps.dbprops.enums.ScopeType;

public class LaunchLevelRegister {
	
	private ScopeType launchLevel;
	private List<JdbcPropsTestStep> subscriptionList;
	
	private LaunchLevelRegister() {
		subscriptionList = new ArrayList<JdbcPropsTestStep>();
	}
	
	private static class Holder {
		private static LaunchLevelRegister reg = new LaunchLevelRegister();
	}
	
	public static boolean setLaunchLevel(ScopeType scopeType) {
		boolean isSet = false;
		if( Holder.reg.launchLevel == null ) {
			Holder.reg.launchLevel = scopeType;
			isSet =  true;
		} else {
			isSet = false;
		}
		return isSet;
	}
	
	public static void resetIfType(ScopeType scopeType) {
		Holder.reg.killProperties();
		if( Holder.reg.launchLevel == scopeType ) {
			Holder.reg.launchLevel = null;
		}
	}
	
	public static void subscribe(JdbcPropsTestStep jdbcPropsTestStep) {
		Holder.reg.subscriptionList.add(jdbcPropsTestStep);
	}
	
	private void killProperties() {
		for( JdbcPropsTestStep step : subscriptionList ) {
			step.killProperties();
		}
	}
}
