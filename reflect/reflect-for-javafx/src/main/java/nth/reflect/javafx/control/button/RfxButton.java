package nth.reflect.javafx.control.button;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialColors;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.javafx.control.style.RfxColorFactory;

public class RfxButton extends JFXButton implements RfxControl {

	private static final int ICON_SIZE = 17;
	private RfxFontIconName fontIconName;
	private MaterialColorSet colorSet;

	public RfxButton() {
		super();
		init();
	}

	public RfxButton(String text) {
		super(text);
		init();
	}

	public RfxButton(RfxFontIconName fontIconName) {
		super();
		init();
		this.fontIconName = fontIconName;
		setFontIconName(fontIconName);
	}

	public RfxButton(Item item, MaterialColorSet colorSet) {
		super();
		init();
		setColorSet(colorSet);
		setText(item.getText());
		setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				item.getAction().run();
			}
		});
	}

	private void setFontIconName(RfxFontIconName fontIconName) {
		this.fontIconName = fontIconName;
		updateColors();
	}

	public RfxButton(String text, Node node) {
		super(text, node);
		init();
	}

	public void setColorSet(MaterialColorSet colorSet) {
		this.colorSet = colorSet;
		updateColors();
	}

	private void updateColors() {
		setBackground(
				new Background(new BackgroundFill(RfxColorFactory.create(colorSet.getBackground()),
						CornerRadii.EMPTY, Insets.EMPTY)));
		ripplerFillProperty().set(RfxColorFactory.create(colorSet.getBackgroundHighLighted()));
		Color foreColor = (isDisabled()) ? colorSet.getForeground3() : colorSet.getForeground1();
		setTextFill(RfxColorFactory.create(foreColor));
		if (fontIconName == null) {
			setGraphic(null);
		} else {
			setGraphic(new RfxFontIcon(fontIconName, ICON_SIZE, foreColor));
		}
	}

	private void init() {
		//TODO create a button that only uses the style sheets:  getStylesheets().add(RfxStyleSheet.createStyleClassName(RfxButton.class));
		setColorSet(MaterialColors.getContentColorSet());
		disabledProperty().addListener((observable, oldValue, newValue) -> {
			updateColors();
		});
	}

}
