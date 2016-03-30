package nth.reflect.javafx.control.style;


import javafx.scene.Node;
import javafx.scene.control.Control;

public class RfxStyleSelector {

	private final StringBuilder selector;

	private RfxStyleSelector() {
		selector = new StringBuilder();
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
		String className = RfxStyleSheet.createStyleClassName(controlClass);
		selector.append(".");
		selector.append(className);
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
