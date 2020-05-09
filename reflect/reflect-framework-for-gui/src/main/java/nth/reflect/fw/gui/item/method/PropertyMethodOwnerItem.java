package nth.reflect.fw.gui.item.method;

import java.util.List;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.item.HierarchicalItem;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * Represents open {@link FormTab}s that are in {@link FormMode#EDIT} and have
 * {@link PropertyMethodItem}s that take a given parameter type.
 * 
 * @author nilsth
 *
 */
public class PropertyMethodOwnerItem extends HierarchicalItem {

	private final FormTab formTab;

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

		ReflectionProvider reflectionProvider = formTab.getUserInterfaceContainer().get(ReflectionProvider.class);
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		for (PropertyInfo otherPropertyInfo : propertyInfos) {

			if (otherPropertyInfo != propertyInfo) {
				List<ActionMethodInfo> propertyMethods = propertyInfo.getActionMethodInfos();
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
		// using displayname instead of MaterialAppBarTitle because the
		// MaterialAppBarTitle could be truncated
		return formTab.getDisplayName();
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
