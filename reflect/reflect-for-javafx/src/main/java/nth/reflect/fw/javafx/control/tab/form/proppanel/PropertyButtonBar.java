package nth.reflect.fw.javafx.control.tab.form.proppanel;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.stage.PopupWindow.AnchorLocation;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelStyle;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.item.method.menu.FormFieldMenuItems;
import nth.reflect.fw.javafx.control.button.ContentButton;
import nth.reflect.fw.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreePanel;
import nth.reflect.fw.javafx.control.popup.PopupWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class PropertyButtonBar extends HBox {

	private final ContentButton actionMethodButton;
	private final FormTab formTab;
	private final PropertyValueModel propertyValueModel;

	public PropertyButtonBar(FormTab formTab, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		this.formTab = formTab;
		this.propertyValueModel = propertyValueModel;
		Optional<Item> selectionItem = propertyField.getSelectionItem();
		if (selectionItem.isPresent()) {
			ContentButton selectionButton = createSelectionButton(selectionItem.get());
			getChildren().add(selectionButton);
		}

		actionMethodButton = createActionMethodButton();
		getChildren().add(actionMethodButton);

		// TODO hide when no buttons are visible

	}

	private ContentButton createActionMethodButton() {
		ContentButton selectionButton = new ContentButton(FontAwesomeIconName.ELLIPSIS_V);
		selectionButton.setOnAction(this::onActionMethodButton);
		return selectionButton;
	}

	public void onActionMethodButton(ActionEvent actionEvent) {
		showActionMethodPopUp();
	}

	private void showActionMethodPopUp() {
		FormFieldMenuItems items = new FormFieldMenuItems(formTab, propertyValueModel,
				propertyValueModel.getPropertyInfo());
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		PopupWindow popupMenu = new PopupWindow();
		popupMenu.getContent().clear();
		ItemTreePanel itemPanel = new ItemTreePanel(items, languageProvider, popupMenu);
		popupMenu.getContent().add(itemPanel);
		Parent properyPanel = this.getParent();
		double menuWidth = properyPanel.getBoundsInLocal().getWidth() - (PropertyPanelStyle.PADDING_LEFT_RIGHT * 2);
		itemPanel.setPrefWidth(menuWidth);
		Bounds buttonBounds = actionMethodButton.localToScreen(actionMethodButton.getBoundsInLocal());
		popupMenu.setAnchorLocation(AnchorLocation.CONTENT_TOP_RIGHT);
		popupMenu.show(actionMethodButton, buttonBounds.getMinX() + buttonBounds.getWidth(),
				buttonBounds.getMinY() + buttonBounds.getHeight());
		// popupMenu.show(this, x, y);
	}

	private ContentButton createSelectionButton(Item item) {
		ContentButton selectionButton = new ContentButton(FontAwesomeIconName.CARET_DOWN);
		selectionButton.setOnAction(e -> item.getAction());
		return selectionButton;
	}

}
