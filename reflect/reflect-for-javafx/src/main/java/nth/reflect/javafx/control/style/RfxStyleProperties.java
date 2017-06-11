package nth.reflect.javafx.control.style;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.basic.Color;
import nth.introspect.ui.style.basic.Font;

public class RfxStyleProperties extends HashMap<String,String> {
	private static final long serialVersionUID = 7266905658046903895L;

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
		for (String propertyName : keySet()) {
			css.append(propertyName);
			css.append(": ");
			String propertyValue = get(propertyName);
			css.append(propertyValue);
			css.append(";");
		}
		return css.toString();
	}

	public RfxStyleProperties setBorderWidth(int width) {
		put("-fx-border-width", getPixels(width));
		return this;
	}

	public RfxStyleProperties setBackground(Color color) {
		put("-fx-background-color", getRGB(color));
		return this;
	}

	public RfxStyleProperties setBackground(String colorVariableName) {
		put("-fx-background-color", colorVariableName);
		return this;
	}


	public RfxStyleProperties setPadding(int padding) {
		put("-fx-padding", getPixels(padding));
		return this;
	}

	public RfxStyleProperties setPadding(int top, int right, int bottom, int left) {
		StringBuilder padding = new StringBuilder();
		padding.append(getPixels(top));
		padding.append(" ");
		padding.append(getPixels(right));
		padding.append(" ");
		padding.append(getPixels(bottom));
		padding.append(" ");
		padding.append(getPixels(left));
		put("-fx-padding", padding.toString());
		return this;
	}

	public RfxStyleProperties setCellSize(int size) {
		put("-fx-cell-size", getPixels(size));
		return this;
	}

	
	public RfxStyleProperties setMinHeight(int minHeight) {
		put("-fx-min-height", getPixels(minHeight));
		return this;
	}

	public RfxStyleProperties setHeight(int height) {
		put("-fx-height", getPixels(height));
		return this;
	}

	public RfxStyleProperties setMaxHeight(int maxHeight) {
		put("-fx-max-height", getPixels(maxHeight));
		return this;
	}

	public RfxStyleProperties setMinWidth(int minWidth) {
		put("-fx-min-width", getPixels(minWidth));
		return this;
	}

	public RfxStyleProperties setAlignment(Pos pos) {
		put("-fx-alignment", pos.name().toLowerCase().replace("_", "-"));
		return this;
	}


	public RfxStyleProperties setFill(Color color) {
		put("-fx-fill", getRGB(color));
		return this;
	}

	
	public RfxStyleProperties setTextFill(Color color) {
		put("-fx-text-fill", getRGB(color));
		return this;
	}

	public RfxStyleProperties setOpacity(String opacity) {
		put("-fx-opacity", opacity);
		return this;
	}
	
	
	public RfxStyleProperties setFillWidth(boolean fillWidth) {
		put("-fx-fill-width", Boolean.toString(fillWidth).toLowerCase());
		return this;
	}

	public RfxStyleProperties setFont(Font font) {
		put("-fx-font-family", getQuoted(font.getName()));
		setFontSize(font.getSize());
		return this;
	}

	public RfxStyleProperties setFont(String fontFamily) {
		put("-fx-font-family", getQuoted(fontFamily));
		return this;
	}

	
	public RfxStyleProperties setFontSize(int fontSize) {
		put("-fx-font-size", Integer.toString(fontSize)+"px");
		return this;
	}

	public void setColorVariables(String colorSetName, MaterialColorSet colorSet) {
		put(colorSetName+MaterialColorSet.FOREGROUND1, getRGB(colorSet.getForeground1()));
		put(colorSetName+MaterialColorSet.FOREGROUND2, getRGB(colorSet.getForeground2()));
		put(colorSetName+MaterialColorSet.FOREGROUND3, getRGB(colorSet.getForeground3()));
		put(colorSetName+MaterialColorSet.BACKGROUND, getRGB(colorSet.getBackground()));
		put(colorSetName+MaterialColorSet.BACKGROUND_HIGHLIGHTED, getRGB(colorSet.getBackgroundHighLighted()));
	}



	
}