package nth.introspect.ui.item;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.filter.EqualsFilter;
import nth.introspect.filter.Filter;
import nth.introspect.filter.LogicFilter;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.method.filter.NoParameterOrParameterFactoryFilter;
import nth.introspect.provider.domain.info.method.filter.ParameterTypeFilter;
import nth.introspect.provider.domain.info.method.filter.ReturnTypeFilter;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.view.FormView;
import nth.introspect.provider.userinterface.view.TableView;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.item.method.PropertyMethodOwnerItem;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class ItemFactory {

	public static List<MethodOwnerItem> createMenuViewItems() {
		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		DomainProvider domainProvider = Introspect.getDomainProvider();
		List<Object> serviceObjects = domainProvider.getServiceObjects();

		for (Object serviceObject : serviceObjects) {
			MethodOwnerItem item = new MethodOwnerItem(serviceObject,
					new NoParameterOrParameterFactoryFilter(), null);
			items.add(item);
		}
		return items;
	}

	public static List<Item> createFormViewRelationalFieldItems(
			FormView formView, PropertyValueModel propertyValueModel) {
		List<Item> items = new ArrayList<Item>();

		// get info from form view
		MethodInfo methodInfoToExclude = formView.getMethodInfo();
		PropertyInfo propertyToExclude = propertyValueModel.getPropertyInfo();
		ReadOnlyValueModel parameterModel = propertyValueModel;
		Class<?> parameterType = parameterModel.getValueType();
		Object serviceObject = formView.getServiceObject();

		// property methods
		// DomainProvider domainProvider=Introspect.getDomainProvider();
		// LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(new
		// NoParameterOrParameterFactoryFilter());
		// filter.or(new ParameterTypeFilter(parameterType));
		// List<MethodInfo> methodInfos =
		// domainProvider.getMethodInfos(parameterType, filter);
		// for (MethodInfo methodInfo : methodInfos) {
		// item=new MethodItem(methodOwner, methodInfo,
		// methodParameterValueModel) TODO methodOwner needs to be a value
		// model!!!
		// items.add(item)
		// }

		items.addAll(createPropertyOwnerItems(parameterModel, propertyToExclude));

		Class<?> domainType = parameterModel.getValueType();
		LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(
				new ParameterTypeFilter(domainType));
		filter.or(new ReturnTypeFilter(parameterType));
		filter.andNot(new EqualsFilter<MethodInfo>(methodInfoToExclude));
		items.addAll(createServiceObjectItems(serviceObject, parameterModel,
				filter));

		return items;

	}

	public static List<Item> createTableViewRowItems(TableView tableView) {
		List<Item> items = new ArrayList<Item>();

		// get info from table view
		MethodInfo methodInfoToExclude = tableView.getMethodInfo();
		ReadOnlyValueModel parameterModel = tableView.getSelectedRowModel();
		Class<?> parameterType = parameterModel.getValueType();
		Object serviceObject = tableView.getServiceObject();

		// property methods
		// DomainProvider domainProvider=Introspect.getDomainProvider();
		// LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(new
		// NoParameterOrParameterFactoryFilter());
		// filter.or(new ParameterTypeFilter(parameterType));
		// List<MethodInfo> methodInfos =
		// domainProvider.getMethodInfos(parameterType, filter);
		// for (MethodInfo methodInfo : methodInfos) {
		// item=new MethodItem(methodOwner, methodInfo,
		// methodParameterValueModel) TODO methodOwner needs to be a value
		// model!!!
		// items.add(item)
		// }

		items.addAll(createPropertyOwnerItems(parameterModel, null));

		// create filter for service object items
		Class<?> domainType = parameterModel.getValueType();
		LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(
				new ParameterTypeFilter(domainType));
		filter.andNot(new EqualsFilter<MethodInfo>(methodInfoToExclude));
		items.addAll(createServiceObjectItems(serviceObject, parameterModel,
				filter));

		return items;
	}

	private static List<MethodOwnerItem> createServiceObjectItems(
			Object serviceObjectToStartWith, ReadOnlyValueModel parameterModel,
			Filter<MethodInfo> filter) {

		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		// create MethodOwnerItem for first service object
		MethodOwnerItem item = new MethodOwnerItem(serviceObjectToStartWith,
				filter, parameterModel);
		items.add(item);

		// create MethodOwnerItem for other service objects
		DomainProvider domainProvider = Introspect.getDomainProvider();
		List<Object> serviceObjects = domainProvider.getServiceObjects();
		for (Object serviceObject : serviceObjects) {
			if (serviceObject != serviceObjectToStartWith) {

				item = new MethodOwnerItem(serviceObject, filter,
						parameterModel);
				items.add(item);
			}
		}
		return items;
	}

	private static List<Item> createPropertyOwnerItems(
			ReadOnlyValueModel paramaterModel, PropertyInfo propertyToExclude) {
		List<Item> items = new ArrayList<Item>();

		UserInterfaceProvider<?> userInterfaceProvider = Introspect
				.getUserInterfaceProvider();
		ViewContainer viewContainer = userInterfaceProvider.getViewContainer();
		for (int index = 0; index < viewContainer.getViewCount(); index++) {
			View view = (View) viewContainer.getView(index);
			if (view instanceof FormView) {
				FormView formView = (FormView) view;
				FormModeType formMode = formView.getFormMode();
				if (FormModeType.editParameterThenExecuteMethodOrCancel == formMode) {
					PropertyMethodOwnerItem item = new PropertyMethodOwnerItem(
							formView, paramaterModel,propertyToExclude);
					items.add(item);
				}
			}
		}
		return items;
	}
}
