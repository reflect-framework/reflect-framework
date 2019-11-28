package nth.reflect.ui.vaadin.button;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

import nth.reflect.fw.layer1userinterface.item.Item;

/**
 * Component representing a <code>&lt;button&gt;</code> element.
 */
@SuppressWarnings("serial")
@Tag(Tag.BUTTON)
@HtmlImport("/frontend/styles/reflect-button.html")
public class Button extends HtmlContainer implements ClickNotifier<Button> {

	private static final ButtonType DEFAULT_TYPE = ButtonType.TEXT;
	private static final ButtonColor DEFAULT_COLOR = ButtonColor.CONTENT;
	private final ButtonType buttonType;
	private final ButtonColor buttonColor;

	/**
	 * Creates a new empty button.
	 */
	public Button() {
		this(DEFAULT_TYPE, DEFAULT_COLOR);
	}

	public Button(ButtonType buttonType, ButtonColor buttonColor) {
		super();
		this.buttonType = buttonType;
		this.buttonColor = buttonColor;
		addClassNames();
		setFocusable(true);
	}

	public void setFocusable(boolean focusable) {
		String tabIndex = focusable ? "0" : "-1";
		getElement().setAttribute("tabindex", tabIndex);
	}

	private void addClassNames() {
		getClassNames().add("reflect-button");
		getClassNames().add(buttonType.getClassName());
		getClassNames().add(buttonColor.getClassName());
		
	}

	/**
	 * Creates a new button with the given child components.
	 *
	 * @param components
	 *            the child components
	 */
	public Button(Component... components) {
		this(DEFAULT_TYPE, DEFAULT_COLOR);
		add(components);
	}

	public Button(ButtonType buttonType, ButtonColor buttonColor, Item item) {
		this(buttonType, buttonColor);
		setText(item.getText());
		// TODO icon
		getElement().addEventListener("click", e -> item.getAction().run());
	}

}
