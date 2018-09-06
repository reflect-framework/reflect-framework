package nth.reflect.ui.vaadin.css;

import java.awt.Color;
import java.util.HashMap;

import com.vaadin.flow.component.Component;

/**
 * Style properties class in fluent interface style, because JavaFx does not
 * have a CSS API yet.
 * 
 * @author nilsth
 *
 */
public class StyleBuilder extends HashMap<String, String> {
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

	public void setFor(Component component) {
		if (component != null) {
			component.getElement().setAttribute("style", toString());
		}
	}

	public StyleBuilder setProperty(String propertyName, String propertyValue) {
		put(propertyName, propertyValue);
		return this;
	}

	public StyleBuilder setWidth(int width, SizeUnit sizeUnit) {
		put("width", sizeUnit.asString(width));
		return this;
	}

	public StyleBuilder setHeight(int height, SizeUnit sizeUnit) {
		put("height", sizeUnit.asString(height));
		return this;
	}

	// public StyleBuilder setBorderWidth(int width) {
	// put("border-width", SizeUnit.PX.asString(width));
	// return this;
	// }

	public StyleBuilder setBackground(Color color) {
		put("background-color", getRGB(color));
		return this;
	}

	public StyleBuilder setBackground(String colorVariableName) {
		put("background-color", colorVariableName);
		return this;
	}

	public StyleBuilder setPadding(int padding) {
		put("padding", SizeUnit.PX.asString(padding));
		return this;
	}

	public StyleBuilder setPadding(int top, int right, int bottom, int left) {
		StringBuilder padding = new StringBuilder();
		padding.append(SizeUnit.PX.asString(top));
		padding.append(" ");
		padding.append(SizeUnit.PX.asString(right));
		padding.append(" ");
		padding.append(SizeUnit.PX.asString(bottom));
		padding.append(" ");
		padding.append(SizeUnit.PX.asString(left));
		put("padding", padding.toString());
		return this;
	}

	//
	// public StyleBuilder setCellSize(int size) {
	// put("cell-size", SizeUnit.PX.asString(size));
	// return this;
	// }
	//
	// public StyleBuilder setSize(int size) {
	// put("size", SizeUnit.PX.asString(size));
	// return this;
	// }
	//
	// public StyleBuilder setMinHeight(int minHeight) {
	// put("min-height", SizeUnit.PX.asString(minHeight));
	// return this;
	// }
	//
	// public StyleBuilder setMaxHeight(int maxHeight) {
	// put("max-height", SizeUnit.PX.asString(maxHeight));
	// return this;
	// }
	//
	// public StyleBuilder setMinWidth(int minWidth) {
	// put("min-width", SizeUnit.PX.asString(minWidth));
	// return this;
	// }
	//
	// public StyleBuilder setAlignment(Pos pos) {
	// put("alignment", pos.name().toLowerCase().replace("_", "-"));
	// return this;
	// }
	//
	// public StyleBuilder setFill(Color color) {
	// put("fill", getRGB(color));
	// return this;
	// }
	//
	// public StyleBuilder setFill(String colorVariableName) {
	// put("fill", colorVariableName);
	// return this;
	// }
	//
	public StyleBuilder setColor(Color color) {
		put("color", getRGB(color));
		return this;
	}

	public StyleBuilder setColor(String colorVariableName) {
		put("color", colorVariableName);
		return this;
	}

	//
	// public StyleBuilder setOpacity(String opacity) {
	// put("opacity", opacity);
	// return this;
	// }
	//
	// public StyleBuilder setFillWidth(boolean fillWidth) {
	// put("fill-width", Boolean.toString(fillWidth).toLowerCase());
	// return this;
	// }
	//
	public StyleBuilder setFont(String fontFamily) {
		put("font-family", getQuoted(fontFamily));
		return this;
	}

	public StyleBuilder setFontSize(int fontSize, SizeUnit sizeUnit) {
		put("font-size", sizeUnit.asString(fontSize));
		return this;
	}

	//
	// public void setFocusColor(String colorVariableName) {
	// put("focus-color", colorVariableName);
	//
	// }
	//
	//
	// public StyleBuilder setBorderColor(String color) {
	// put("border-color", color);
	// return this;
	// }
	//
	// public StyleBuilder setBorderColor(String topColor, String rightColor,
	// String bottomColor,
	// String leftColor) {
	// StringBuilder colors = new StringBuilder();
	// colors.append(topColor);
	// colors.append(" ");
	// colors.append(rightColor);
	// colors.append(" ");
	// colors.append(bottomColor);
	// colors.append(" ");
	// colors.append(leftColor);
	// put("border-color", colors.toString());
	// return this;
	// }
	//
	// public StyleBuilder setBorderWidth(int top, int right, int bottom, int
	// left) {
	// StringBuilder widths = new StringBuilder();
	// widths.append(top);
	// widths.append(" ");
	// widths.append(right);
	// widths.append(" ");
	// widths.append(bottom);
	// widths.append(" ");
	// widths.append(left);
	// put("border-width", widths.toString());
	// return this;
	// }
	//
	// public StyleBuilder setSpacing(int spacing) {
	// put("spacing", Integer.toString(spacing) + "px");
	// return this;
	// }
	//
	// /**
	// * This hides the outer focus border
	// *
	// * @param insets
	// * @return
	// */
	// public StyleBuilder setBackgroundInsets(int insets) {
	// put("background-insets", Integer.toString(insets) + "px");
	// return this;
	// }
	//
	// public StyleBuilder setFontWeight(FontWeight fontWeight) {
	// put("font-weight", fontWeight.name().toLowerCase());
	// return this;
	// }
	//
	public StyleBuilder setOverflow(Overflow overflow) {
		put("overflow", overflow.toString());
		return this;
	}

	public StyleBuilder setPosition(Position position) {
		put("position", position.toString());
		return this;
	}

	public StyleBuilder setDisplay(Display display) {
		put("display", display.toString());
		return this;

	}

	public StyleBuilder setZIndex(int zIndex) {
		put("z-index", Integer.toString(zIndex));
		return this;
	}

	public StyleBuilder setCursor(Cursor cursor) {
		put("cursor", cursor.toString());
		return this;
	}

	public StyleBuilder setRight(int size, SizeUnit sizeUnit) {
		put("right", sizeUnit.asString(size));
		return this;
	}

}