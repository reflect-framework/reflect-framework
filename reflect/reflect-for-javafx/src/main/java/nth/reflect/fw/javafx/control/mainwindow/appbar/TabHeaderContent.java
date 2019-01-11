package nth.reflect.fw.javafx.control.mainwindow.appbar;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.button.PrimaryButton;
import nth.reflect.fw.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.fw.javafx.control.style.FontFactory;
import nth.reflect.fw.javafx.control.style.StyleProperties;
import nth.reflect.fw.javafx.control.tab.Tab;

/**
 * TODO: this is an experimental Class to try and replace {@link RfxTabButton}
 * so we can add a close button
 * 
 * @author nilsth
 *
 */
public class TabHeaderContent extends HBox {

	private static final int MIN_HEIGHT = 34;
	private static final int MAX_TEXT_WIDTH = 400;// 264;
	private static final int PADDING = 12;
	private static final String SELECTED_STYLE = createSelectedStyle();
	private static final String UNSELECTED_STYLE = createUnselectedStyle();
	private final Tab tab;
	private final Tabs<Tab> tabs;
	private final PrimaryButton closeButton;

	public TabHeaderContent(Tabs<Tab> tabs, Tab tab) {
		this.tabs = tabs;
		this.tab = tab;
		
		setFocusTraversable(true);

		Label label = createLabel(tab);
		getChildren().add(label);

		closeButton = createCloseButton();
		getChildren().add(closeButton);

		setOnMouseClicked(this::onHeaderClicked);
	}

	private Label createLabel(Tab tab) {
		Label label = new Label(tab.getDisplayName());
		label.setFont(FontFactory.create(MaterialFont.getRobotoMedium(14)));
		String style = new StyleProperties().setTextFill(ReflectColorName.PRIMARY.FOREGROUND())
				.setAlignment(Pos.CENTER_LEFT).toString();
		label.setStyle(style);
		label.setMinHeight(MIN_HEIGHT);
		label.setMaxWidth(MAX_TEXT_WIDTH);
		return label;
	}

	private PrimaryButton createCloseButton() {
		PrimaryButton closeButton = new PrimaryButton(FontAwesomeIconName.CLOSE);
		closeButton.setOnAction(this::onCloseButtonPressed);
		return closeButton;

	}

	private void onHeaderClicked(MouseEvent event) {
		tabs.setSelected(tab);
	}

	private void onCloseButtonPressed(ActionEvent event) {
		tabs.remove(tab);
		event.consume();
	}

	private static String createUnselectedStyle() {
		String unselectedTabStyle = new StyleProperties().setTextFill(ReflectColorName.PRIMARY.FOREGROUND())
				.setPadding(0, PADDING, 0, PADDING).setMinHeight(MIN_HEIGHT).setBorderWidth(0, 0, 4, 0)
				.setBorderColor(ReflectColorName.PRIMARY.BACKGROUND()).setFont(MaterialFont.getRobotoMedium(14))
				.toString();
		return unselectedTabStyle;
	}

	private static String createSelectedStyle() {
		String selectedTabStyle = new StyleProperties().setTextFill(ReflectColorName.PRIMARY.FOREGROUND())
				.setPadding(0, 0, 0, PADDING).setMinHeight(MIN_HEIGHT).setBorderWidth(0, 0, 4, 0)
				.setBorderColor(ReflectColorName.ACCENT.BACKGROUND()).setFont(MaterialFont.getRobotoMedium(14))
				.toString();
		return selectedTabStyle;
	}

	public void setSelected(boolean selected) {
		closeButton.setVisible(selected);
		closeButton.setManaged(selected);
		setStyle(selected ? SELECTED_STYLE : UNSELECTED_STYLE);
	}
}
