package be.mdi.tooling.soapui.steps.dbprops.enums;


public enum AxisType {
	ROW("Row"),
	COLUMN("Column");
	
	private String name;
	
	private AxisType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}