package nth.reflect.javafx.control.style;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Control;
import nth.introspect.ui.style.basic.Color;


public class RfxStyleGroup {

	private final Map<String,String> properties;
	private final String selector;
	
	public RfxStyleGroup(Class<? extends Control> controlClass, String ... selectors) {
		selector=getSelector(controlClass, selectors);
		properties=new HashMap<>();
	}

	private String getSelector(Class<? extends Control> controlClass, String[] selectors) {
		StringBuilder selector=new StringBuilder();
		selector.append(RfxStyleSheet.getStyleClass(controlClass));
		for (String suffix : selectors) {
			suffix=suffix.trim();
			if (suffix.startsWith(":")) {
				selector.append(suffix);
			} else {
				selector.append(" ");
				selector.append(suffix);
			}
		}
		return selector.toString();
	}
	
	private String getRGB(Color color) {
		if (color==null) {
			return "null";
		}
		StringBuilder css=new StringBuilder();
		css.append("rgb(");
		css.append(color.getRed());
		css.append(",");
		css.append(color.getGreen());
		css.append(",");
		css.append(color.getBlue());
		css.append(",");
		css.append(color.getAlpha()/255.0);
		css.append(")");
		return css.toString();
	}

	private String getPixels(int size) {
		StringBuilder css=new StringBuilder();
		css.append(size);
		css.append("px");
		return css.toString();
	}

	@Override
	public String toString() {
		StringBuilder css=new StringBuilder();
		css.append(selector);
		css.append(" {");
		for (String propertyName : properties.keySet()) {
			css.append(propertyName);
			css.append(": ");
			String propertyValue = properties.get(propertyName);
			css.append(propertyValue);
			css.append(";");
		}
		css.append("}");
		return css.toString();
	}

	public RfxStyleGroup setBorderWidth(int width) {
		properties.put("-fx-border-width", getPixels(width));
		return this;
	}

	public RfxStyleGroup setBackground(Color color) {
		properties.put("-fx-background-color", getRGB(color));
		return this;
	}

	public RfxStyleGroup setPadding(int padding) {
		properties.put("-fx-padding", getPixels(padding));
		return this;
	}
}
