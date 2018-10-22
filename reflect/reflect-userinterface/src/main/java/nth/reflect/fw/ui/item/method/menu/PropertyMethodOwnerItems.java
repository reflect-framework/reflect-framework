package nth.reflect.fw.ui.item.method.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.item.method.PropertyMethodOwnerItem;
import nth.reflect.fw.ui.tab.Tab;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.form.FormMode;
import nth.reflect.fw.ui.tab.form.FormTab;

public class PropertyMethodOwnerItems extends UnmodifiableCollection<PropertyMethodOwnerItem> {

	private static final long serialVersionUID = 5020795698213380376L;

	public PropertyMethodOwnerItems(Tabs<Tab> tabs, ReadOnlyValueModel paramaterModel, PropertyInfo propertyInfo) {
		super(createPropertyMethodOwnerItems(tabs, paramaterModel, propertyInfo));
	}

	private static Collection<? extends PropertyMethodOwnerItem> createPropertyMethodOwnerItems(Tabs<Tab> tabs,
			ReadOnlyValueModel paramaterModel, PropertyInfo propertyInfo) {
		List<PropertyMethodOwnerItem> items = new ArrayList<>();
		for (Tab tab : tabs) {
			if (tab instanceof FormTab) {
				FormTab formTab = (FormTab) tab;
				if (FormMode.EDIT_MODE == formTab.getFormMode()) {
					PropertyMethodOwnerItem item = new PropertyMethodOwnerItem(formTab, paramaterModel, propertyInfo);
					items.add(item);
				}
			}
		}
		return items;
	}
}
