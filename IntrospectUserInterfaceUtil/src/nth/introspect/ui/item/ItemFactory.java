package nth.introspect.ui.item;

import java.util.ArrayList;
import java.util.List;

import sun.awt.geom.AreaOp.IntOp;
import nth.introspect.Introspect;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.filter.EqualsFilter;
import nth.introspect.filter.Filter;
import nth.introspect.filter.LogicFilter;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.method.filter.LinkedToPropertyFilter;
import nth.introspect.provider.domain.info.method.filter.NoParameterOrParameterFactoryFilter;
import nth.introspect.provider.domain.info.method.filter.ParameterTypeFilter;
import nth.introspect.provider.domain.info.method.filter.ReturnTypeFilter;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.item.method.MethodItem;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.item.method.PropertyMethodItem;
import nth.introspect.ui.item.method.PropertyMethodOwnerItem;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.introspect.ui.view.TableView;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class ItemFactory {

	public static List<MethodOwnerItem> createMenuViewItems(UserInterfaceContainer introspectOuterContainer) {
		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		List<Object> serviceObjects = introspectOuterContainer.getServiceObjects();

		for (Object serviceObject : serviceObjects) {
			MethodOwnerItem item = new MethodOwnerItem(introspectOuterContainer, serviceObject,
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
		DomainInfoProvider domainInfoProvider = formView.getIntrospectOuterContainer().getDomainInfoProvider();
		// TODO does methodOwner needs to be a value model??? We now assume the
		// menu will be created when a field is selected.
		Object methodOwner = formView.getDomainValueModel().getValue();
		LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(
				new NoParameterOrParameterFactoryFilter());
		filter.or(new ParameterTypeFilter(parameterType));
		filter.and(new LinkedToPropertyFilter(propertyInfo));
		List<MethodInfo> methodInfos = domainInfoProvider.getMethodInfos(
				domainType, filter);
		for (MethodInfo methodInfo : methodInfos) {
			PropertyMethodItem item = new PropertyMethodItem(formView,
					propertyInfo, methodInfo, parameterModel,false);
			// MethodItem item = new MethodItem(methodOwner,
			// methodInfo,parameterModel);
			items.add(item);
		}

		items.addAll(createPropertyOwnerItems(parameterModel, propertyInfo));

		// service object methods
		filter = new LogicFilter<MethodInfo>(new ParameterTypeFilter(
				parameterType));
		filter.or(new ReturnTypeFilter(parameterType));
		filter.andNot(new EqualsFilter<MethodInfo>(methodInfoToExclude));
		UserInterfaceContainer introspectOuterContainer=formView.getIntrospectOuterContainer();
		items.addAll(createServiceObjectItems(introspectOuterContainer, serviceObject, parameterModel,
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
		// domainInfoProvider domainInfoProvider=Introspect.getdomainInfoProvider();
		// LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(new
		// NoParameterOrParameterFactoryFilter());
		// filter.or(new ParameterTypeFilter(parameterType));
		// List<MethodInfo> methodInfos =
		// domainInfoProvider.getMethodInfos(parameterType, filter);
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
		UserInterfaceContainer introspectOuterContainer=tableView.getIntrospectOuterContainer();
		items.addAll(createServiceObjectItems(introspectOuterContainer , serviceObject, parameterModel,
				filter));

		return items;
	}

	private static List<MethodOwnerItem> createServiceObjectItems(UserInterfaceContainer introspectOuterContainer,
			Object serviceObjectToStartWith, ReadOnlyValueModel parameterModel,
			Filter<MethodInfo> filter) {

		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		// create MethodOwnerItem for first service object
		MethodOwnerItem item = new MethodOwnerItem(introspectOuterContainer, serviceObjectToStartWith,
				filter, parameterModel);
		items.add(item);

		// create MethodOwnerItem for other service objects
		List<Object> serviceObjects = introspectOuterContainer.getServiceObjects();
		for (Object serviceObject : serviceObjects) {
			if (serviceObject != serviceObjectToStartWith) {

				item = new MethodOwnerItem(introspectOuterContainer,serviceObject, filter,
						parameterModel);
				items.add(item);
			}
		}
		return items;
	}

	private static List<Item> createPropertyOwnerItems(
			ReadOnlyValueModel paramaterModel, PropertyInfo propertyInfo) {
		List<Item> items = new ArrayList<Item>();

		UserInterfaceProvider<?> userInterfaceProvider = Introspect
				.getUserInterfaceProvider();
		ViewContainer viewContainer = userInterfaceProvider.getViewContainer();
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
