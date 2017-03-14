package nth.introspect.ui.style;

import nth.introspect.ui.style.basic.Color;

public enum ContentColor {
	BLACK,WHITE;
	
	
	public Color getColor() {
		if (this==ContentColor.BLACK) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}
}
