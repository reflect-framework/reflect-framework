package nth.reflect.javafx.control.window.appbar;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.ui.style.DisplayScale;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.control.button.RfxPrimaryButton;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * TODO: move to {@link nth.reflect.javafx.control.window.appbar} package
 * @author nilsth
 *
 */
public class RfxTabButton extends RfxPrimaryButton {

	private static final int MAX_WIDTH = 400;//264;
	private static final int PADDING = 12;
	private static final double HEIGHT = 34;
	private final View tab;
	private final RfxWindow rfxWindow;

	public RfxTabButton(RfxWindow rfxWindow, View tab) {
		super(tab.getViewTitle());
		this.rfxWindow = rfxWindow;
		this.tab = tab;
		setButtonType(ButtonType.FLAT);
		setMinHeight(HEIGHT);
		setPadding(new Insets(0, PADDING, 0, PADDING));
		setMaxWidth(MAX_WIDTH);
		
		When whenTabSelected = Bindings.when(rfxWindow.getSelectedTabProperty().isEqualTo(tab));
		ObservableValue<String> styleBinding = createStyleBinding(whenTabSelected);
		styleProperty().bind(styleBinding);
		
		setOnAction(this::onAction);
	}


	private ObservableValue<String> createStyleBinding(
			When whenTabSelected) {
		String selectedTabStyle = new RfxStyleProperties().setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1())
				.setBorderWidth(0,0,4,0)
				.setBorderColor(MaterialColorSetCssName.ACCENT.BACKGROUND())
				.setFont(MaterialFont.getBody2(DisplayScale.NONE_DENSE)).toString();
		String unselectedTabStyle = new RfxStyleProperties().setTextFill(MaterialColorSetCssName.PRIMARY.FOREGROUND2())
				.setBorderWidth(0,0,4,0)
				.setBorderColor(MaterialColorSetCssName.PRIMARY.BACKGROUND())
				.setFont(MaterialFont.getBody2(DisplayScale.NONE_DENSE)).toString();
		ObservableValue<String> styleBinding=whenTabSelected.then(selectedTabStyle).otherwise(unselectedTabStyle);
		return styleBinding;
	}
	
	private void onAction(ActionEvent event) {
		rfxWindow.getSelectedTabProperty().set(tab);
	}


	public View getTab() {
		return tab;
	}

}
