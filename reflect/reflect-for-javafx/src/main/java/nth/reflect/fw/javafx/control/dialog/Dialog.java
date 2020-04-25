package nth.reflect.fw.javafx.control.dialog;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import nth.reflect.fw.gui.component.table.TableStyle;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.button.PrimaryButton;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;

public class Dialog extends JFXDialog {

	public Dialog(StackPane owner, String title, String message, List<Item> items) {
		super(owner, createContent(owner, title, message), DialogTransition.CENTER, true);

		getStyleClass().add(StyleSheet.createStyleClassName(Dialog.class));

		setLayoutX(owner.getWidth() / 2);
		setLayoutY(owner.getHeight() / 2);

		addActionButtons(items);
	}

	private void addActionButtons(List<Item> items) {
		List<PrimaryButton> actionButtons = new ArrayList<>();
		for (Item item : items) {
			PrimaryButton button = new PrimaryButton(item);
			button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
				@Override
				public void handle(javafx.event.ActionEvent event) {
					close();
					item.getAction().run();
				}
			});
			actionButtons.add(button);
		}
		JFXDialogLayout content = (JFXDialogLayout) getContent();
		content.setActions(actionButtons);
	}

	private static JFXDialogLayout createContent(StackPane owner, String title, String message) {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(title));

		ScrollPane body = createTextBody(owner, message);
		content.setBody(body);
		return content;
	}

	private static ScrollPane createTextBody(StackPane owner, String message) {
		ScrollPane body = new ScrollPane();
		Text text = new Text(message);
		text.wrappingWidthProperty().bind(body.widthProperty().subtract(20));
		body.setContent(text);
		double textWidth = text.getBoundsInParent().getWidth() + 200;
		double maxWidth = owner.getWidth() * 0.5;
		body.setMinWidth(Math.min(textWidth, maxWidth));
		double textHeight = text.getBoundsInLocal().getHeight() + 20;
		double maxHeight = owner.getHeight() * 0.7;
		body.setMinHeight(Math.min(textHeight, maxHeight));
		body.getStyleClass()
				.add(StyleSheet.createStyleClassName(Dialog.class, StyleSheet.createStyleClassName(ScrollPane.class)));

		// TODO: BackgroundColor > .viewport and hide focus line
		return body;
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(Dialog.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND()).setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setFont(MaterialFont.getRobotoRegular(TableStyle.FONT_SIZE)).setPadding(0);

		// Remove annoying border
		styleSheet.addStyleGroup(StyleSelector.createFor(Dialog.class).append(ScrollPane.class)).getProperties()
				.setBorderWidth(0).setPadding(0).setBackgroundInsets(0);

		// scroll pane viewport background white
		styleSheet
				.addStyleGroup(StyleSelector.createFor(Dialog.class).append(ScrollPane.class)
						.append(StyleSelector.createFor("> .viewport")))
				.getProperties().setBackground(ReflectColorName.CONTENT.BACKGROUND())
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND());

	}

}
