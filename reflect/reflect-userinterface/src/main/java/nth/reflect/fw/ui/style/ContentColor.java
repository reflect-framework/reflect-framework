package nth.reflect.fw.ui.style;

import nth.reflect.fw.ui.style.basic.Color;

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
