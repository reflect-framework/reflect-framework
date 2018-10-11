package nth.reflect.fw.ui.item.method.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.item.method.PropertyMethodOwnerItem;
import nth.reflect.fw.ui.view.FormMode;
import nth.reflect.fw.ui.view.FormView;

public class PropertyMethodOwnerItems extends UnmodifiableCollection<PropertyMethodOwnerItem> {

	private static final long serialVersionUID = 5020795698213380376L;

	public PropertyMethodOwnerItems(ViewController<View> viewContainer, ReadOnlyValueModel paramaterModel,
			PropertyInfo propertyInfo) {
		super(createPropertyMethodOwnerItems(viewContainer, paramaterModel, propertyInfo));
	}

	private static Collection<? extends PropertyMethodOwnerItem> createPropertyMethodOwnerItems(
			ViewController<View> viewContainer, ReadOnlyValueModel paramaterModel, PropertyInfo propertyInfo) {
		List<PropertyMethodOwnerItem> items = new ArrayList<>();

		for (int index = 0; index < viewContainer.getViewCount(); index++) {
			View view = (View) viewContainer.getView(index);
			if (view instanceof FormView) {
				FormView formView = (FormView) view;
				if (FormMode.EDIT_MODE == formView.getFormMode()) {
					PropertyMethodOwnerItem item = new PropertyMethodOwnerItem(formView, paramaterModel, propertyInfo);
					items.add(item);
				}
			}
		}
		return items;
	}
}
