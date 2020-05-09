package nth.reflect.fw.javafx.control.tab.form.proppanel;

import java.util.Optional;

import javafx.scene.layout.HBox;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.javafx.control.button.ContentButton;
import nth.reflect.fw.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.fw.layer1userinterface.item.Item;

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

		actionMethodButton = new PropertyActionMethodsButton(formTab, propertyValueModel);
		getChildren().add(actionMethodButton);

		// TODO hide when no buttons are visible

	}

	private ContentButton createSelectionButton(Item item) {
		ContentButton selectionButton = new ContentButton(FontAwesomeIconName.CARET_DOWN);
		selectionButton.setOnAction(e -> item.getAction());
		return selectionButton;
	}

}
