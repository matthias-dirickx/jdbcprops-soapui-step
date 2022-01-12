package be.mdi.tooling.soapui.steps.dbprops.enums;

public enum ScopeType {
	TESTCASE("Test Case"),
	TESTSUITE("Test Suite"),
	PROJECT("Project");
	
	private String name;
	
	private ScopeType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
