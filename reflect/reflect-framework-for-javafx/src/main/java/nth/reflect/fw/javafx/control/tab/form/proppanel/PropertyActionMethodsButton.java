package nth.reflect.fw.javafx.control.tab.form.proppanel;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.stage.PopupWindow.AnchorLocation;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyIconButton;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.menu.PropertyPanelMenuItems;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.javafx.control.button.ContentButton;
import nth.reflect.fw.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreePanel;
import nth.reflect.fw.javafx.control.popup.PopupWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class PropertyActionMethodsButton extends ContentButton implements PropertyIconButton {
	private final PropertyPanelMenuItems items;
	private final UserInterfaceContainer userInterfaceContainer;

	@SuppressWarnings("restriction")
	public PropertyActionMethodsButton(FormTab formTab, PropertyValueModel propertyValueModel) {
		super(FontAwesomeIconName.ELLIPSIS_V);
		setOnAction(this::onAction);
		items = new PropertyPanelMenuItems(formTab, propertyValueModel, propertyValueModel.getPropertyInfo());
		userInterfaceContainer = formTab.getUserInterfaceContainer();
		hideIfThereAreNoVisibleItems();
	}

	private void hideIfThereAreNoVisibleItems() {
		boolean hasVisibleItems = items.hasVisibleItems();
		setVisible(hasVisibleItems);
		setManaged(hasVisibleItems);
	}

	public void onAction(ActionEvent actionEvent) {
		if (items.hasVisibleItems()) {
			showActionMethodPopUp();
		}
	}

	private void showActionMethodPopUp() {
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		PopupWindow popupMenu = new PopupWindow();
		popupMenu.getContent().clear();
		ItemTreePanel itemPanel = new ItemTreePanel(items, languageProvider, popupMenu);
		popupMenu.getContent().add(itemPanel);
		Parent propertyPanel = getParent().getParent();
		double menuWidth = propertyPanel.getBoundsInLocal().getWidth() - (PropertyPanelStyle.PADDING_LEFT_RIGHT * 2);
		itemPanel.setPrefWidth(menuWidth);
		Bounds buttonBounds = localToScreen(getBoundsInLocal());
		popupMenu.setAnchorLocation(AnchorLocation.CONTENT_TOP_RIGHT);
		popupMenu.show(this, buttonBounds.getMinX() + buttonBounds.getWidth(),
				buttonBounds.getMinY() + buttonBounds.getHeight());
	}

	@Override
	public void onRefresh() {
		hideIfThereAreNoVisibleItems();
	}

}
