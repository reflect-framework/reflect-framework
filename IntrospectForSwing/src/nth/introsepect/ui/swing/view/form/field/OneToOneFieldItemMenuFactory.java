package nth.introsepect.ui.swing.view.form.field;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.method.filter.LinkedToPropertyFilter;
import nth.introspect.provider.domain.info.method.filter.ParameterTypeFilter;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.valuemodel.PropertyValueModel;
/**
 * @deprecated use {@link ItemFactory}
 * @author nilsth
 *
 */
public class OneToOneFieldItemMenuFactory {
	// TODO refactor to Introspect package and let all user interface implementations use this factory
	
	public static List<Item> create(PropertyValueModel propertyValueModel) {
		DomainProvider domainProvider = Introspect.getDomainProvider();
		List<Item> items = new ArrayList<Item>();

		
		// create menu item for methods of domain object that are linked to property
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Object domainObject = propertyValueModel.getDomainObject();
		MethodOwnerItem item = new MethodOwnerItem(domainObject, new LinkedToPropertyFilter(propertyInfo), propertyValueModel);
		item.setText(propertyInfo.getText());
		item.setDescription(propertyInfo.getDescription());
		items.add(item);

		// create menu
		List<Object> serviceObjects = domainProvider.getServiceObjects();
		for (Object serviceObject : serviceObjects) {
			Class<?> propertyClass = propertyInfo.getPropertyType().getType();
			item = new MethodOwnerItem(serviceObject, new ParameterTypeFilter(propertyClass), propertyValueModel);
			items.add(item);
		}
		return items;
	}
}
