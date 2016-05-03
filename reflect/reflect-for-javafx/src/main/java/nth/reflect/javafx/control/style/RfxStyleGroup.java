package nth.reflect.javafx.control.style;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import nth.introspect.ui.style.basic.Color;
import nth.introspect.ui.style.basic.Font;

public class RfxStyleGroup {

	private final Map<String, String> properties;
	private final RfxStyleSelector selector;

	public RfxStyleGroup(RfxStyleSelector selector) {
		this.selector = selector;
		this.properties = new HashMap<>();
	}

	private String getRGB(Color color) {
		if (color == null) {
			return "transparent";
		}
		StringBuilder css = new StringBuilder();
		css.append("rgb(");
		css.append(color.getRed());
		css.append(",");
		css.append(color.getGreen());
		css.append(",");
		css.append(color.getBlue());
		css.append(",");
		css.append(color.getAlpha() / 255.0);
		css.append(")");
		return css.toString();
	}

	private String getQuoted(String text) {
		StringBuilder css = new StringBuilder();
		css.append('"');
		css.append(text);
		css.append('"');
		return css.toString();
	}
	
	private String getPixels(int size) {
		StringBuilder css = new StringBuilder();
		css.append(size);
		css.append("px");
		return css.toString();
	}

	@Override
	public String toString() {
		StringBuilder css = new StringBuilder();
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

	public RfxStyleGroup setPadding(int top, int right, int bottom, int left) {
		StringBuilder padding = new StringBuilder();
		padding.append(getPixels(top));
		padding.append(" ");
		padding.append(getPixels(right));
		padding.append(" ");
		padding.append(getPixels(bottom));
		padding.append(" ");
		padding.append(getPixels(left));
		properties.put("-fx-padding", padding.toString());
		return this;
	}

	public RfxStyleGroup setMinHeight(int minHeight) {
		properties.put("-fx-min-height", getPixels(minHeight));
		return this;
	}

	public RfxStyleGroup setHeight(int height) {
		properties.put("-fx-height", getPixels(height));
		return this;
	}

	public RfxStyleGroup setMaxHeight(int maxHeight) {
		properties.put("-fx-max-height", getPixels(maxHeight));
		return this;
	}

	public RfxStyleGroup setMinWidth(int minWidth) {
		properties.put("-fx-min-width", getPixels(minWidth));
		return this;
	}

	public RfxStyleGroup setAlignment(Pos pos) {
		properties.put("-fx-alignment", pos.name().toLowerCase().replace("_", "-"));
		return this;
	}

	public RfxStyleGroup setTextFill(Color color) {
		properties.put("-fx-text-fill", getRGB(color));
		return this;
	}

	public RfxStyleGroup setFillWidth(boolean fillWidth) {
		properties.put("-fx-fill-width", Boolean.toString(fillWidth).toLowerCase());
		return this;
	}

	public RfxStyleGroup setFont(Font font) {
		properties.put("-fx-font-family", getQuoted(font.getName()));
		setFontSize(font.getSize());
		return this;
	}

	public RfxStyleGroup setFontSize(int fontSize) {
		properties.put("-fx-font-size", Integer.toString(fontSize)+"px");
		return this;
	}

	
}