package nth.reflect.fw.javafx.control.table;

import java.util.List;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.ui.item.ItemFactory;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;

public class RfxTableInfoForFormViewProperty extends RfxTableInfo {

	private final PropertyValueModel propertyValueModel;
	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;
	private final FormView formView;

	public RfxTableInfoForFormViewProperty(FormView formView,
			PropertyValueModel propertyValueModel) {
		this.formView = formView;
		this.propertyValueModel = propertyValueModel;
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
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
	public List<Item> getRowMenuItems(Object selectedObject) {
		List<Item> items = ItemFactory.createFormViewRelationalFieldItems(formView, propertyValueModel, propertyValueModel.getPropertyInfo());
		return items;
	}

}
