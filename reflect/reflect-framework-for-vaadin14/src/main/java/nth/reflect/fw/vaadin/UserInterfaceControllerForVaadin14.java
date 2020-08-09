package nth.reflect.fw.vaadin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.component.notification.Notification;

import nth.reflect.fw.gui.GraphicalUserInterfaceController;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.vaadin.dialog.Dialog;
import nth.reflect.fw.vaadin.tab.form.row.PropertyPanel;
import nth.reflect.fw.vaadin.tab.form.row.PropertyPanelFactory;

public class UserInterfaceControllerForVaadin14
		extends GraphicalUserInterfaceController<nth.reflect.fw.vaadin.tab.Tab, PropertyPanel> {

	private final PropertyPanelFactory propertyPanelFactory;
	private final Dialog dialog;

	public UserInterfaceControllerForVaadin14(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		PropertyFieldProvider propertyFieldProvider = userInterfaceContainer.get(PropertyFieldProvider.class);
		propertyPanelFactory = new PropertyPanelFactory(propertyFieldProvider);
		dialog = new Dialog();
	}

	/**
	 * We do not have to create the {@link ReflectApplicationForVaadin}, because it
	 * is created by the Vaadin framework when is receives a
	 * {@link HttpServletRequest}.
	 */
	@Override
	public void launch() {
	}

	@Override
	public void showProgressDialog(TranslatableString taskDescription, int currentValue, int maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeProgressDialog() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMessage(TranslatableString message) {
		String translatedMessage = message.getTranslation(languageProvider);
		Notification notification = new Notification(translatedMessage, 3000);
		notification.open();
	}

	@Override
	public void showDialog(TranslatableString title, TranslatableString message, List<Item> items) {
		String translatedTitle = title.getTranslation(languageProvider);
		String translatedMessage = message.getTranslation(languageProvider);
		dialog.open(translatedTitle, translatedMessage, items);
	}

	@Override
	public nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory<PropertyPanel> getPropertyPanelFactory() {
		return propertyPanelFactory;
	}

}
