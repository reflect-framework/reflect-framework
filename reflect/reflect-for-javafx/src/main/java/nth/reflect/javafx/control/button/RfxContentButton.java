package nth.reflect.javafx.control.button;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.javafx.control.fonticon.RfxFontIconName;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

/**
 * {@link RfxControl} button in the content color, see {@link ReflectApplicationForJavaFX#getContentColor()
 * @author nilsth
 *
 */
public class RfxContentButton extends JFXButton implements RfxControl {

	private static final int ICON_SIZE = 17;
	private RfxFontIconName fontIconName;
	private MaterialColorSet colorSet;

	public RfxContentButton() {
		super();
		addStyleClass();
	}

	public RfxContentButton(String text) {
		super(text);
		addStyleClass();
	}

	public RfxContentButton(RfxFontIconName fontIconName) {
		super();
		addStyleClass();
		setFontIconName(fontIconName);
	}

	public RfxContentButton(Item item) {
		super();
		addStyleClass();
//		setColorSet(colorSet);
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
		if (fontIconName == null) {
			setGraphic(null);
		} else {
			setGraphic(new RfxFontIcon(fontIconName, ICON_SIZE, MaterialColorSetCssName.CONTENT.FOREGROUND1()));
		}
//		updateColors();
	}

	public RfxContentButton(String text, Node node) {
		super(text, node);
		addStyleClass();
	}

//	public void setColorSet(MaterialColorSet colorSet) {
//		this.colorSet = colorSet;
//		updateColors();
//	}

//	private void updateColors() {
//		setBackground(
//				new Background(new BackgroundFill(RfxColorFactory.create(colorSet.getBackground()),
//						CornerRadii.EMPTY, Insets.EMPTY)));
//		ripplerFillProperty().set(RfxColorFactory.create(colorSet.getBackgroundHighLighted()));
//		Color foreColor = (isDisabled()) ? colorSet.getForeground3() : colorSet.getForeground1();
//		setTextFill(RfxColorFactory.create(foreColor));
//		if (fontIconName == null) {
//			setGraphic(null);
//		} else {
//			setGraphic(new RfxFontIcon(fontIconName, ICON_SIZE, foreColor));
//		}
//	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxContentButton.class));
	}

	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		//TODO what to gove RfxContentButton for colour (CONTENT??? tnak remove RfxFormButton)
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class)).getProperties()
		.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
		.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1());
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentButton.class).append(RfxStyleSelector.createFor("jfx-rippler"))).getProperties().
		put("-fx-rippler-fill",MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED());
	}
	
}
