package nth.reflect.fw.ui.item.method.menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.LinkedToPropertyFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.NoParameterOrParameterFactoryFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ReturnTypeFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;
import nth.reflect.fw.ui.item.method.PropertyMethodItem;
import nth.reflect.fw.ui.item.method.PropertyMethodOwnerItem;
import nth.reflect.fw.ui.view.FormMode;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;

/**
 * <p>
 * {@link FormFieldMenuItems} are displayed on a {@link PropertyField}s that
 * represent a {@link DomainObject} or a {@link Collection} or {@link Array} of
 * {@link DomainObject}s
 * </p>
 * <p>
 * There are 2 types of {@link FormFieldMenuItems}:
 * <ul>
 * <li>{@link FormFieldMenuItems} for
 * {@link DomainObjectPropertyActionMethod}s</li>
 * <li>{@link MethodOwnerItem}s that contain {@link ServiceObjectActionMethod}s
 * that take the {@link DomainObject} as a parameter</li>
 * </ul>
 * </p>
 * 
 * @author nilsth
 *
 */
public class FormFieldMenuItems extends UnmodifiableCollection<Item> {

	private static final long serialVersionUID = -8380826298117283745L;

	public FormFieldMenuItems(FormView formView,
			ReadOnlyValueModel parameterModel, PropertyInfo propertyInfo) {
		super(createFieldMenuItems(formView,parameterModel,  propertyInfo));
	}

	private static Collection<? extends Item> createFieldMenuItems(FormView formView,
				ReadOnlyValueModel parameterModel, PropertyInfo propertyInfo) {
			List<Item> items = new ArrayList<Item>();

			// get info from form view
			ActionMethodInfo methodInfoToExclude = formView.getMethodInfo();
			Class<?> domainType = formView.getDomainValueModel().getValueType();
			Class<?> parameterType = parameterModel.getValueType();
			Object serviceObject = formView.getMethodOwner();

			// add property methods
			ReflectionProvider reflectionProvider = formView.getUserInterfaceContainer()
					.get(ReflectionProvider.class);
			// TODO does methodOwner needs to be a value model??? We now assume the
			// menu will be created when a field is selected.
			Predicate<ActionMethodInfo> filter=new NoParameterOrParameterFactoryFilter().or(new ParameterTypeFilter(parameterType)).and(new LinkedToPropertyFilter(propertyInfo));
			ClassInfo classInfo = reflectionProvider.getClassInfo(domainType);
			List<ActionMethodInfo> actionMethodInfos = classInfo.getActionMethodInfos(filter);
			for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
				PropertyMethodItem item = new PropertyMethodItem(formView, propertyInfo,
						actionMethodInfo, parameterModel, false);
				// MethodItem item = new MethodItem(methodOwner,
				// methodInfo,parameterModel);
				items.add(item);
			}

			@SuppressWarnings("rawtypes")
			ViewController viewController = formView.getUserInterfaceContainer()
					.get(GraphicalUserinterfaceController.class).getViewController();
			items.addAll(new PropertyMethodOwnerItems(viewController, parameterModel, propertyInfo));

			// service object methods
			filter=new ParameterTypeFilter(parameterType).or(new ReturnTypeFilter(parameterType)).and(actionMethod -> !actionMethod.equals(methodInfoToExclude));
			UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
			items.addAll(new ServiceObjectItems(userInterfaceContainer, serviceObject, parameterModel,
					filter));

			return items;

		}
	

}

