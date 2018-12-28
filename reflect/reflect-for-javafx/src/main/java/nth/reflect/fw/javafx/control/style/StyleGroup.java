package nth.reflect.fw.javafx.control.style;

public class StyleGroup {

	private final StyleProperties properties;
	private final StyleSelector selector;

	public StyleGroup(StyleSelector selector) {
		this.selector = selector;
		this.properties = new StyleProperties();
	}

	public StyleProperties getProperties() {
		return properties;
	}

	public StyleSelector getSelector() {
		return selector;
	}

	@Override
	public String toString() {
		StringBuilder css = new StringBuilder();
		css.append(selector);
		css.append(" {");
		css.append(properties);
		css.append("}");
		return css.toString();
	}


}