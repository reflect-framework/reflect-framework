package nth.reflect.fw.javafx.control.style;


import javafx.scene.Node;

public class StyleSelector {

	private final StringBuilder selector;

	private StyleSelector() {
		selector = new StringBuilder();
	}

	public static StyleSelector createFor(String selector) {
		StyleSelector styleSelector = new StyleSelector();
		styleSelector.selector.append(selector);
		return styleSelector;
	}
	
	public static StyleSelector createFor(Class<? extends Node> controlClass, String suffix) {
		StyleSelector styleSelector = new StyleSelector();
		styleSelector.append(controlClass, suffix);
		return styleSelector;
	}
	
	public static StyleSelector createFor(Class<? extends Node> controlClass) {
		StyleSelector styleSelector = new StyleSelector();
		styleSelector.append(controlClass);
		return styleSelector;
	}

	public StyleSelector append(Class<? extends Node> controlClass) {
		if (selector.length()>0) {
			selector.append(" ");
		}
		selector.append(".");
		String className = StyleSheet.createStyleClassName(controlClass);
		selector.append(className);
		return this;
	}

	public StyleSelector append(Class<? extends Node> controlClass, String suffix) {
		if (selector.length()>0) {
			selector.append(" ");
		}
		String className = StyleSheet.createStyleClassName(controlClass, suffix);
		selector.append(".");
		selector.append(className);
		return this;
	}


	public StyleSelector append(StyleSelector styleSelectorToAppend) {
		if (selector.length()>0) {
			selector.append(" ");
		}
		selector.append(styleSelectorToAppend);
		return this;
	}
	
	public StyleSelector appendChild(Class<? extends Node> controlClass) {
		if (selector.length()>0) {
			selector.append(" > ");
		}
		selector.append(".");
		String className = StyleSheet.createStyleClassName(controlClass);
		selector.append(className);
		return this;
	}


	public StyleSelector appendChild(String childSelector) {
		if (selector.length()>0) {
			selector.append(" ");
		}
		selector.append(".");
		selector.append(childSelector);
		return this;
	}
	
	public StyleSelector appendFilled() {
		selector.append(":filled");
		return this;
	}

	public StyleSelector appendSelected() {
		selector.append(":selected");
		return this;
	}

	public StyleSelector appendFocused() {
		selector.append(":focused");
		return this;
	}

	public StyleSelector appendPressed() {
		selector.append(":pressed");
		return this;
	}
	
	@Override
	public String toString() {
		return selector.toString();
	}



}
