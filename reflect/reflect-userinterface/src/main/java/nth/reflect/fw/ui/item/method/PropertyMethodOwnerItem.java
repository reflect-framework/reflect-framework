package nth.reflect.fw.ui.item.method;

import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.LinkedToPropertyFilter;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.ParameterTypeFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.item.HierarchicalItem;
import nth.reflect.fw.ui.tab.form.FormMode;
import nth.reflect.fw.ui.tab.form.FormTab;

/**
 * Represents open {@link FormTab}s that are in {@link FormMode#EDIT_MODE} and
 * have {@link PropertyMethodItem}s that take a given parameter type.
 * 
 * @author nilsth
 *
 */
public class PropertyMethodOwnerItem extends HierarchicalItem {

	private FormTab formTab;

	public PropertyMethodOwnerItem(FormTab formTab, ReadOnlyValueModel parameterValueModel) {
		this(formTab, parameterValueModel, null);
	}

	public PropertyMethodOwnerItem(FormTab formTab, ReadOnlyValueModel parameterValueModel,
			PropertyInfo propertyToExclude) {
		super(formTab.getUserInterfaceContainer().get(LanguageProvider.class));
		this.formTab = formTab;
		pupulateChildren(formTab, parameterValueModel, propertyToExclude);
	}

	public void pupulateChildren(FormTab formTab, ReadOnlyValueModel parameterValueModel, PropertyInfo propertyInfo) {
		ReadOnlyValueModel domainValueModel = formTab.getDomainValueModel();
		Class<?> domainClass = domainValueModel.getValueType();
		Class<?> parameterClass = parameterValueModel.getValueType();

		ReflectionProvider reflectionProvider = formTab.getUserInterfaceContainer().get(ReflectionProvider.class);
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		for (PropertyInfo otherPropertyInfo : propertyInfos) {

			if (otherPropertyInfo != propertyInfo) {
				Predicate<ActionMethodInfo> filter = new LinkedToPropertyFilter(otherPropertyInfo)
						.and(new ParameterTypeFilter(parameterClass));
				List<ActionMethodInfo> propertyMethods = classInfo.getActionMethodInfos(filter);
				for (ActionMethodInfo propertyMethodInfo : propertyMethods) {
					PropertyMethodItem propertyMethodItem = new PropertyMethodItem(formTab, otherPropertyInfo,
							propertyMethodInfo, parameterValueModel, true);
					getChildren().add(propertyMethodItem);
				}
			}
		}
	}

	@Override
	public String getText() {
		// TODO return TitleUtil.createTitle(methodInfo, methodParameter,
		// false);
		return formTab.getDisplayName();// using description instead of
										// MaterialAppBarTitle
										// because the MaterialAppBarTitle could
										// be truncated
	}

	@Override
	public boolean isVisible() {
		return hasChildren();
	}

	@Override
	public String getDescription() {
		return formTab.getDescription();
	}

}
