package nth.introspect.ui.item;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.filter.EqualsFilter;
import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.filter.LogicFilter;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.LinkedToPropertyFilter;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.NoParameterOrParameterFactoryFilter;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.ReturnTypeFilter;
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

		ServiceContainer serviceContainer = userInterfaceContainer.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();

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
		ActionMethodInfo methodInfoToExclude = formView.getMethodInfo();
		Class<?> domainType = formView.getDomainValueModel().getValueType();
		Class<?> parameterType = parameterModel.getValueType();
		Object serviceObject = formView.getMethodOwner();

		// add property methods
		ReflectionProvider reflectionProvider = formView.getuserInterfaceContainer().get(ReflectionProvider.class);
		// TODO does methodOwner needs to be a value model??? We now assume the
		// menu will be created when a field is selected.
		Object methodOwner = formView.getDomainValueModel().getValue();
		LogicFilter<ActionMethodInfo> filter = new LogicFilter<ActionMethodInfo>(
				new NoParameterOrParameterFactoryFilter());
		filter.or(new ParameterTypeFilter(parameterType));
		filter.and(new LinkedToPropertyFilter(propertyInfo));
		List<ActionMethodInfo> actionMethodInfos = reflectionProvider.getMethodInfos(
				domainType, filter);
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			PropertyMethodItem item = new PropertyMethodItem(formView,
					propertyInfo, actionMethodInfo, parameterModel,false);
			// MethodItem item = new MethodItem(methodOwner,
			// methodInfo,parameterModel);
			items.add(item);
		}

		ViewContainer viewContainer = formView.getuserInterfaceContainer().get(UserInterfaceController.class).getViewContainer();
		items.addAll(createPropertyOwnerItems(viewContainer, parameterModel, propertyInfo));

		// service object methods
		filter = new LogicFilter<ActionMethodInfo>(new ParameterTypeFilter(
				parameterType));
		filter.or(new ReturnTypeFilter(parameterType));
		filter.andNot(new EqualsFilter<ActionMethodInfo>(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer=formView.getuserInterfaceContainer();
		items.addAll(createServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel,
				filter));

		return items;

	}

	public static List<Item> createTableViewRowItems(TableView tableView) {
		List<Item> items = new ArrayList<Item>();

		// get info from table view
		ActionMethodInfo methodInfoToExclude = tableView.getMethodInfo();
		ReadOnlyValueModel parameterModel = tableView.getSelectedRowModel();
		Class<?> parameterType = parameterModel.getValueType();
		Object serviceObject = tableView.getMethodOwner();

		// property methods
		// reflectionProvider reflectionProvider=Introspect.getreflectionProvider();
		// LogicFilter<ActionMethodInfo> filter = new LogicFilter<ActionMethodInfo>(new
		// NoParameterOrParameterFactoryFilter());
		// filter.or(new ParameterTypeFilter(parameterType));
		// List<ActionMethodInfo> methodInfos =
		// reflectionProvider.getMethodInfos(parameterType, filter);
		// for (ActionMethodInfo methodInfo : methodInfos) {
		// item=new MethodItem(methodOwner, methodInfo,
		// methodParameterValueModel) TODO methodOwner needs to be a value
		// model!!!
		// items.add(item)
		// }

		ViewContainer viewContainer = tableView.getuserInterfaceContainer().get(UserInterfaceController.class).getViewContainer();
		items.addAll(createPropertyOwnerItems(viewContainer, parameterModel, null));

		// create filter for service object items
		Class<?> domainType = parameterModel.getValueType();
		LogicFilter<ActionMethodInfo> filter = new LogicFilter<ActionMethodInfo>(
				new ParameterTypeFilter(domainType));
		filter.andNot(new EqualsFilter<ActionMethodInfo>(methodInfoToExclude));
		UserInterfaceContainer userInterfaceContainer=tableView.getuserInterfaceContainer();
		items.addAll(createServiceObjectItems(userInterfaceContainer , serviceObject, parameterModel,
				filter));

		return items;
	}

	private static List<MethodOwnerItem> createServiceObjectItems(UserInterfaceContainer userInterfaceContainer,
			Object serviceObjectToStartWith, ReadOnlyValueModel parameterModel,
			Filter<ActionMethodInfo> filter) {

		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		// create MethodOwnerItem for first service object
		MethodOwnerItem item = new MethodOwnerItem(userInterfaceContainer, serviceObjectToStartWith,
				filter, parameterModel);
		items.add(item);

		// create MethodOwnerItem for other service objects
		ServiceContainer serviceContainer =userInterfaceContainer.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();
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
