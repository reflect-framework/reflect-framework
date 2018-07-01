package nth.reflect.javafx.control.table;

import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.ui.view.FormView;

public class RfxTableInfoForFormViewProperty extends RfxTableInfo {

	private final PropertyValueModel propertyValueModel;
	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;

	public RfxTableInfoForFormViewProperty(FormView formView, PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
	}

	@Override
	public ObservableList<Object> getItems() {
		List<Object> list = (List<Object>) propertyValueModel.getValue();
		// TODO create a createObservableList for all types, and that can be
		// updated when needed
		return new ObservableListWrapper<Object>(list);
	}

	@Override
	public Class<?> getItemType() {
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
	public List<MethodOwnerItem> getRowMenuItems(Object selectedObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
