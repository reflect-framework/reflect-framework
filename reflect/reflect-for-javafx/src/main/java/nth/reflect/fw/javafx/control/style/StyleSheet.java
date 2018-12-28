package nth.reflect.fw.javafx.control.style;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;

public class StyleSheet {

	private final List<StyleGroup> styleGroups;

	public StyleSheet() {
		styleGroups = new ArrayList<>();
	}

	public StyleGroup addStyleGroup(StyleSelector styleSelector) {
		StyleGroup styleGroup = new StyleGroup(styleSelector);
		styleGroups.add(styleGroup);
		return styleGroup;
	}

	public void addToScene(Scene scene) {
		scene.getStylesheets().setAll(new StyleSheetUrl().toString());
	}

	public static String createStyleClassName(Class<? extends Node> controlClass) {
		StringBuilder styleClassName = new StringBuilder();
		String controlClassName = controlClass.getSimpleName();
		char previousCh = 0;
		for (char ch : controlClassName.toCharArray()) {
			if (Character.isLowerCase(previousCh) && Character.isUpperCase(ch)) {
				styleClassName.append("-");
			}
			styleClassName.append(Character.toLowerCase(ch));
			previousCh = ch;
		}
		return styleClassName.toString();
	}

	public static String createStyleClassName(Class<? extends Node> controlClass, String suffix) {
		StringBuilder styleClassName = new StringBuilder();
		styleClassName.append(createStyleClassName(controlClass));

		if (suffix != null && suffix.trim().length() > 0) {
			suffix = suffix.trim().toLowerCase().replace(" ", "-");
			styleClassName.append("-");
			styleClassName.append(suffix);
		}
		return styleClassName.toString();
	}

	@Override
	public String toString() {
		StringBuilder css = new StringBuilder();
		for (StyleGroup styleGroup : styleGroups) {
			css.append(styleGroup);
			css.append("\n");
		}
		return css.toString();
	}

}
