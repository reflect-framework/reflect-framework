package nth.reflect.fw.javafx.control.table;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.ui.item.method.menu.FormFieldMenuItems;
import nth.reflect.fw.ui.tab.form.FormTab;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class TableInfoForFormTabProperty extends TableInfo {

	private final PropertyValueModel propertyValueModel;
	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;
	private final FormTab formTab;

	public TableInfoForFormTabProperty(FormTab formTab,
			PropertyValueModel propertyValueModel) {
		this.formTab = formTab;
		this.propertyValueModel = propertyValueModel;
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
	}

	@Override
	public Object getValues() {
		return propertyValueModel.getValue();
	}

	@Override
	public Class<?> getValuesType() {
		return propertyValueModel.getValueType();
	}

	@Override
	public ReflectionProvider getReflectionProvider() {
		return reflectionProvider;
	}

	@Override
	public LanguageProvider getLanguageProvider() {
		return languageProvider;
	}

	@Override
	public FormFieldMenuItems getRowMenuItems(Object selectedObject) {
		FormFieldMenuItems items = new FormFieldMenuItems(formTab, propertyValueModel, propertyValueModel.getPropertyInfo());
		return items;
	}

}
