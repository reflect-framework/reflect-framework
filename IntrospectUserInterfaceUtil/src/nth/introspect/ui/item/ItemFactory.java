package nth.introspect.ui.item;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.filter.EqualsFilter;
import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.filter.LogicFilter;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.method.filter.LinkedToPropertyFilter;
import nth.introspect.layer5provider.reflection.info.method.filter.NoParameterOrParameterFactoryFilter;
import nth.introspect.layer5provider.reflection.info.method.filter.ParameterTypeFilter;
import nth.introspect.layer5provider.reflection.info.method.filter.ReturnTypeFilter;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.item.method.PropertyMethodItem;
import nth.introspect.ui.item.method.PropertyMethodOwnerItem;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.introspect.ui.view.TableView;

public class ItemFactory {

	public static List<MethodOwnerItem> createMenuViewItems(UserInterfaceContainer userInterfaceContainer) {
		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		List<Object> serviceObjects = userInterfaceContainer.getServiceObjects();

		for (Object serviceObject : serviceObjects) {
			MethodOwnerItem item = new MethodOwnerItem(userInterfaceContainer, serviceObject,
					new NoParameterOrParameterFactoryFilter(), null);
			items.add(item);
		}
		return items;
	}

	public static List<Item> createFormViewRelationalFieldItems(
			FormView formView, ReadOnlyValueModel parameterModel,
			PropertyInfo propertyInfo) {
		List<Item> items = new ArrayList<Item>();

		// get info from form view
		MethodInfo methodInfoToExclude = formView.getMethodInfo();
		Class<?> domainType = formView.getDomainValueModel().getValueType();
		Class<?> parameterType = parameterModel.getValueType();
		Object serviceObject = formView.getMethodOwner();

		// add property methods
		ReflectionProvider reflectionProvider = formView.getuserInterfaceContainer().getReflectionProvider();
		// TODO does methodOwner needs to be a value model??? We now assume the
		// menu will be created when a field is selected.
		Object methodOwner = formView.getDomainValueModel().getValue();
		LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(
				new NoParameterOrParameterFactoryFilter());
		filter.or(new ParameterTypeFilter(parameterType));
		filter.and(new LinkedToPropertyFilter(propertyInfo));
		List<MethodInfo> methodInfos = reflectionProvider.getMethodInfos(
				domainType, filter);
		for (MethodInfo methodInfo : methodInfos) {
			PropertyMethodItem item = new PropertyMethodItem(formView,
					propertyInfo, methodInfo, parameterModel,false);
			// MethodItem item = new MethodItem(methodOwner,
			// methodInfo,parameterModel);
			items.add(item);
		}

		ViewContainer viewContainer = formView.getuserInterfaceContainer().getUserInterfaceController().getViewContainer();
		items.addAll(createPropertyOwnerItems(viewContainer, parameterModel, propertyInfo));

		// service object methods
		filter = new LogicFilter<MethodInfo>(new ParameterTypeFilter(
				parameterType));
		filter.or(new ReturnTypeFilter(parameterType));
		filter.andNot(new EqualsFilter<MethodInfo>(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer=formView.getuserInterfaceContainer();
		items.addAll(createServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel,
				filter));

		return items;

	}

	public static List<Item> createTableViewRowItems(TableView tableView) {
		List<Item> items = new ArrayList<Item>();

		// get info from table view
		MethodInfo methodInfoToExclude = tableView.getMethodInfo();
		ReadOnlyValueModel parameterModel = tableView.getSelectedRowModel();
		Class<?> parameterType = parameterModel.getValueType();
		Object serviceObject = tableView.getMethodOwner();

		// property methods
		// reflectionProvider reflectionProvider=Introspect.getreflectionProvider();
		// LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(new
		// NoParameterOrParameterFactoryFilter());
		// filter.or(new ParameterTypeFilter(parameterType));
		// List<MethodInfo> methodInfos =
		// reflectionProvider.getMethodInfos(parameterType, filter);
		// for (MethodInfo methodInfo : methodInfos) {
		// item=new MethodItem(methodOwner, methodInfo,
		// methodParameterValueModel) TODO methodOwner needs to be a value
		// model!!!
		// items.add(item)
		// }

		ViewContainer viewContainer = tableView.getuserInterfaceContainer().getUserInterfaceController().getViewContainer();
		items.addAll(createPropertyOwnerItems(viewContainer, parameterModel, null));

		// create filter for service object items
		Class<?> domainType = parameterModel.getValueType();
		LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(
				new ParameterTypeFilter(domainType));
		filter.andNot(new EqualsFilter<MethodInfo>(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer=tableView.getuserInterfaceContainer();
		items.addAll(createServiceObjectItems(userInterfaceContainer , serviceObject, parameterModel,
				filter));

		return items;
	}

	private static List<MethodOwnerItem> createServiceObjectItems(UserInterfaceContainer userInterfaceContainer,
			Object serviceObjectToStartWith, ReadOnlyValueModel parameterModel,
			Filter<MethodInfo> filter) {

		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		// create MethodOwnerItem for first service object
		MethodOwnerItem item = new MethodOwnerItem(userInterfaceContainer, serviceObjectToStartWith,
				filter, parameterModel);
		items.add(item);

		// create MethodOwnerItem for other service objects
		List<Object> serviceObjects = userInterfaceContainer.getServiceObjects();
		for (Object serviceObject : serviceObjects) {
			if (serviceObject != serviceObjectToStartWith) {

				item = new MethodOwnerItem(userInterfaceContainer,serviceObject, filter,
						parameterModel);
				items.add(item);
			}
		}
		return items;
	}

	private static List<Item> createPropertyOwnerItems(ViewContainer<View> viewContainer,
			ReadOnlyValueModel paramaterModel, PropertyInfo propertyInfo) {
		List<Item> items = new ArrayList<Item>();

		for (int index = 0; index < viewContainer.getViewCount(); index++) {
			View view = (View) viewContainer.getView(index);
			if (view instanceof FormView) {
				FormView formView = (FormView) view;
				if (FormMode.EDIT_MODE==formView.getFormMode()) {
					PropertyMethodOwnerItem item = new PropertyMethodOwnerItem(
							formView, paramaterModel, propertyInfo);
					items.add(item);
				}
			}
		}
		return items;
	}
}
