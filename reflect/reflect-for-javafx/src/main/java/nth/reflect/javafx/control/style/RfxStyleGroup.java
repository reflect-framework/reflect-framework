package nth.reflect.javafx.control.style;

import java.util.Map;

public class RfxStyleGroup {

	private final RfxStyleProperties properties;
	private final RfxStyleSelector selector;

	public RfxStyleGroup(RfxStyleSelector selector) {
		this.selector = selector;
		this.properties = new RfxStyleProperties();
	}

	public RfxStyleProperties getProperties() {
		return properties;
	}

	public RfxStyleSelector getSelector() {
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