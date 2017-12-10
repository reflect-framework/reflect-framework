package nth.reflect.javafx.control.style;


import javafx.scene.Node;

public class RfxStyleSelector {

	private final StringBuilder selector;

	private RfxStyleSelector() {
		selector = new StringBuilder();
	}

	public static RfxStyleSelector createFor(String selector) {
		RfxStyleSelector styleSelector = new RfxStyleSelector();
		styleSelector.selector.append(selector);
		return styleSelector;
	}
	
	public static RfxStyleSelector createFor(Class<? extends Node> controlClass, String suffix) {
		RfxStyleSelector styleSelector = new RfxStyleSelector();
		styleSelector.append(controlClass, suffix);
		return styleSelector;
	}
	
	public static RfxStyleSelector createFor(Class<? extends Node> controlClass) {
		RfxStyleSelector styleSelector = new RfxStyleSelector();
		styleSelector.append(controlClass);
		return styleSelector;
	}

	public RfxStyleSelector append(Class<? extends Node> controlClass) {
		if (selector.length()>0) {
			selector.append(" ");
		}
		selector.append(".");
		String className = RfxStyleSheet.createStyleClassName(controlClass);
		selector.append(className);
		return this;
	}

	public RfxStyleSelector append(Class<? extends Node> controlClass, String suffix) {
		if (selector.length()>0) {
			selector.append(" ");
		}
		String className = RfxStyleSheet.createStyleClassName(controlClass, suffix);
		selector.append(".");
		selector.append(className);
		return this;
	}


	public RfxStyleSelector append(RfxStyleSelector styleSelectorToAppend) {
		if (selector.length()>0) {
			selector.append(" ");
		}
		selector.append(styleSelectorToAppend);
		return this;
	}
	
	public RfxStyleSelector appendChild(Class<? extends Node> controlClass) {
		if (selector.length()>0) {
			selector.append(" > ");
		}
		selector.append(".");
		String className = RfxStyleSheet.createStyleClassName(controlClass);
		selector.append(className);
		return this;
	}


	public RfxStyleSelector appendChild(String childSelector) {
		if (selector.length()>0) {
			selector.append(" > ");
		}
		selector.append(".");
		selector.append(childSelector);
		return this;
	}
	
	public RfxStyleSelector appendFilled() {
		selector.append(":filled");
		return this;
	}

	public RfxStyleSelector appendSelected() {
		selector.append(":selected");
		return this;
	}

	public RfxStyleSelector appendFocused() {
		selector.append(":focused");
		return this;
	}

	public RfxStyleSelector appendPressed() {
		selector.append(":pressed");
		return this;
	}
	
	@Override
	public String toString() {
		return selector.toString();
	}



}
