package nth.reflect.ui.vaadin.css;

public enum WhiteSpace {
	NORMAL,NOWRAP,PRE,PRE_LINE,PRE_WRAP,INITIAL,INHERIT;
	
	@Override
	public String toString() {
		return this.name().toLowerCase().replace("_", "-");
	}
}
