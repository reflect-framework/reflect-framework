package nth.reflect.fw.gui.item.method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.util.collection.UnmodifiableCollection;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.NoParameterOrParameterFactoryFilter;

public class ServiceObjectItems extends UnmodifiableCollection<MethodOwnerItem> {

	private static final long serialVersionUID = 1263712774995387618L;

	public ServiceObjectItems(UserInterfaceContainer userInterfaceContainer) {
		super(createServiceObjectItems(userInterfaceContainer));
	}

	public ServiceObjectItems(UserInterfaceContainer userInterfaceContainer, Object serviceObject,
			ReadOnlyValueModel parameterModel, Predicate<ActionMethodInfo> filter) {
		super(createServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel, filter));
	}

	private static Collection<? extends MethodOwnerItem> createServiceObjectItems(
			UserInterfaceContainer userInterfaceContainer, Object serviceObjectToStartWith,
			ReadOnlyValueModel parameterModel, Predicate<ActionMethodInfo> filter) {
		List<MethodOwnerItem> items = new ArrayList<MethodOwnerItem>();

		// create MethodOwnerItem for first service object
		MethodOwnerItem item = new MethodOwnerItem(userInterfaceContainer, serviceObjectToStartWith, filter,
				parameterModel);
		items.add(item);

		// create MethodOwnerItem for other service objects
		ServiceContainer serviceContainer = userInterfaceContainer.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();
		for (Object serviceObject : serviceObjects) {
			if (serviceObject != serviceObjectToStartWith) {

				item = new MethodOwnerItem(userInterfaceContainer, serviceObject, filter, parameterModel);
				items.add(item);
			}
		}
		return items;
	}

	private static Collection<? extends MethodOwnerItem> createServiceObjectItems(
			UserInterfaceContainer userInterfaceContainer) {
		ServiceContainer serviceContainer = userInterfaceContainer.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();

		List<MethodOwnerItem> items = new ArrayList<>();
		for (Object serviceObject : serviceObjects) {
			MethodOwnerItem item = new MethodOwnerItem(userInterfaceContainer, serviceObject,
					new NoParameterOrParameterFactoryFilter(), null);

			items.add(item);
		}
		return items;
	}

}
