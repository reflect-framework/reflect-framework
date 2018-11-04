package nth.reflect.ui.vaadin.button;

public enum ButtonType {
	
	TEXT,
	ICON;

	public String getClassName() {
		StringBuilder className=new StringBuilder( "reflect-");
		className.append(name().toLowerCase());
		className.append("-button");
		return className.toString();
	}
}
