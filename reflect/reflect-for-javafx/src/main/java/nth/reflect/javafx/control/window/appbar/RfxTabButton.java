package nth.reflect.javafx.control.window.appbar;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.ui.style.DisplayScale;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialColors;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * TODO: move to {@link nth.reflect.javafx.control.window.appbar} package
 * @author nilsth
 *
 */
public class RfxTabButton extends RfxButton {

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
		setColorSet(MaterialColors.getPrimaryColorSet());
		setMinHeight(HEIGHT);
		setPadding(new Insets(0, PADDING, 0, PADDING));
		setMaxWidth(MAX_WIDTH);
		String style=new RfxStyleProperties().setFont(MaterialFont.getBody2(DisplayScale.NONE_DENSE)).toString();
		setStyle(style);
		
		When whenTabSelected = Bindings.when(rfxWindow.getSelectedTabProperty().isEqualTo(tab));
		
		ObservableValue<Color> textColorBinding = createTextColorBinding(whenTabSelected);
		textFillProperty().bind(textColorBinding);
		
		ObservableValue<Border> borderBinding = createBorderBinding(whenTabSelected);
		borderProperty().bind(borderBinding);
		
		setOnAction(this::onAction);
	}

	private ObservableValue<Border> createBorderBinding(When whenTabSelected) {
		Border foregroundUnderlineBorder = createForegroundUnderlineBorder();
		Border backgroundUnderlineBorder = createBackgroundUnderlineBorder();
		ObservableValue<Border> borderBinding = whenTabSelected.then(foregroundUnderlineBorder)
				.otherwise(backgroundUnderlineBorder);
		return borderBinding;
	}

	private ObservableValue<Color> createTextColorBinding(
			When whenTabSelected) {
		MaterialColorSet colorSet = MaterialColors.getPrimaryColorSet();
		Color selectedTabColor = RfxColorFactory.create(colorSet.getForeground1());
		Color unselectedTabColor = RfxColorFactory.create(colorSet.getForeground2());
		ObservableValue<Color> textColorBinding=whenTabSelected.then(selectedTabColor).otherwise(unselectedTabColor);
		return textColorBinding;
	}
	
	private void onAction(ActionEvent event) {
		rfxWindow.getSelectedTabProperty().set(tab);
	}

	private Border createForegroundUnderlineBorder() {
		Color foregroundColor = RfxColorFactory
				.create(MaterialColors.getAccentColorSet().getBackground());
		Border foregroundUnderlineBorder = createUnderlineBorder(foregroundColor);
		return foregroundUnderlineBorder;
	}

	private Border createBackgroundUnderlineBorder() {
		Color backgroundColor = Color.TRANSPARENT;
		Border backgroundUnderlineBorder = createUnderlineBorder(backgroundColor);
		return backgroundUnderlineBorder;
	}

	private Border createUnderlineBorder(Color lineColor) {
		BorderStroke borderStroke = new BorderStroke(null, null, lineColor, null, null, null,
				BorderStrokeStyle.SOLID, null, CornerRadii.EMPTY, new BorderWidths(0, 0, 3, 0),
				Insets.EMPTY);
		Border border = new Border(borderStroke);
		return border;
	}

	public View getTab() {
		return tab;
	}

}
