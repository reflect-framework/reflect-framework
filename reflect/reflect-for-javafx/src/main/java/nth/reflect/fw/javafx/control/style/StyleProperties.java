package nth.reflect.fw.javafx.control.style;

import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.text.FontWeight;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.gui.style.ReflectColorSet;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.gui.style.basic.Font;

/**
 * Style properties class in fluent interface style, because JavaFx does not have a CSS API yet. 
 * @author nilsth
 *
 */
public class StyleProperties extends HashMap<String,String> {
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

	public StyleProperties setBorderWidth(int width) {
		put("-fx-border-width", getPixels(width));
		return this;
	}

	public StyleProperties setBackground(Color color) {
		put("-fx-background-color", getRGB(color));
		return this;
	}

	public StyleProperties setBackground(String colorVariableName) {
		put("-fx-background-color", colorVariableName);
		return this;
	}


	public StyleProperties setPadding(int padding) {
		put("-fx-padding", getPixels(padding));
		return this;
	}

	public StyleProperties setPadding(int top, int right, int bottom, int left) {
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

	public StyleProperties setCellSize(int size) {
		put("-fx-cell-size", getPixels(size));
		return this;
	}

	public StyleProperties setSize(int size) {
		put("-fx-size", getPixels(size));
		return this;
	}

	
	public StyleProperties setMinHeight(int minHeight) {
		put("-fx-min-height", getPixels(minHeight));
		return this;
	}

	public StyleProperties setHeight(int height) {
		put("-fx-height", getPixels(height));
		return this;
	}

	public StyleProperties setMaxHeight(int maxHeight) {
		put("-fx-max-height", getPixels(maxHeight));
		return this;
	}

	public StyleProperties setMinWidth(int minWidth) {
		put("-fx-min-width", getPixels(minWidth));
		return this;
	}

	public StyleProperties setAlignment(Pos pos) {
		put("-fx-alignment", pos.name().toLowerCase().replace("_", "-"));
		return this;
	}


	public StyleProperties setFill(Color color) {
		put("-fx-fill", getRGB(color));
		return this;
	}

	public StyleProperties setFill(String colorVariableName) {
		put("-fx-fill", colorVariableName);
		return this;
	}

	
	public StyleProperties setTextFill(Color color) {
		put("-fx-text-fill", getRGB(color));
		return this;
	}

	public StyleProperties setTextFill(String colorVariableName) {
		put("-fx-text-fill", colorVariableName);
		return this;
	}

	public StyleProperties setOpacity(String opacity) {
		put("-fx-opacity", opacity);
		return this;
	}
	
	
	public StyleProperties setFillWidth(boolean fillWidth) {
		put("-fx-fill-width", Boolean.toString(fillWidth).toLowerCase());
		return this;
	}

	public StyleProperties setFont(Font font) {
		put("-fx-font-family", getQuoted(font.getName()));
		setFontSize(font.getSize());
		return this;
	}

	public StyleProperties setFont(String fontFamily) {
		put("-fx-font-family", getQuoted(fontFamily));
		return this;
	}

	
	public StyleProperties setFontSize(int fontSize) {
		put("-fx-font-size", Integer.toString(fontSize)+"px");
		return this;
	}


	public void setFocusColor(String colorVariableName) {
		put("-fx-focus-color",colorVariableName);
		
	}

	public  StyleProperties setProperty(String propertyName, String propertyValue) {
		put(propertyName, propertyValue);
		return this;
	}

	
	public void setColorVariables(ReflectColorName colorSetName, ReflectColorSet colorSet) {
		put(colorSetName.BACKGROUND(), getRGB(colorSet.getBackground()));
		put(colorSetName.BACKGROUND_12(), getRGB(colorSet.getBackground12()));
		put(colorSetName.BACKGROUND_20(), getRGB(colorSet.getBackground20()));
		put(colorSetName.FOREGROUND(), getRGB(colorSet.getForeground()));
	}

	public  StyleProperties setBorderColor(String color) {
		put ("-fx-border-color", color);
		return this;
	}
	
	public StyleProperties setBorderColor(String topColor, String rightColor,
			String bottomColor, String leftColor) {
		StringBuilder colors=new StringBuilder();
		colors.append(topColor);
		colors.append(" ");
		colors.append(rightColor);
		colors.append(" ");
		colors.append(bottomColor);
		colors.append(" ");
		colors.append(leftColor);
		put ("-fx-border-color", colors.toString());
		return this;
	}


	public StyleProperties setBorderWidth(int top, int right, int bottom, int left) {
		StringBuilder widths=new StringBuilder();
		widths.append(top);
		widths.append(" ");
		widths.append(right);
		widths.append(" ");
		widths.append(bottom);
		widths.append(" ");
		widths.append(left);
		put ("-fx-border-width", widths.toString());
		return this;
	}

	public StyleProperties setSpacing(int spacing) {
		put("-fx-spacing", Integer.toString(spacing)+"px");
		return this;
	}

	/**
	 * This hides the outer focus border
	 * @param insets
	 * @return
	 */
	public StyleProperties setBackgroundInsets(int insets) {
		put("-fx-background-insets",Integer.toString(insets)+"px");
		return this;
	}

	public StyleProperties setFontWeight(FontWeight fontWeight) {
		put("-fx-font-weight", fontWeight.name().toLowerCase());
		return this;
	}

	public StyleProperties setWrapText(boolean wrapText) {
		put("-fx-text-wrap", Boolean.toString(wrapText));
		return this;
	}







	
}