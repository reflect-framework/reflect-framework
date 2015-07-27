package nth.introspect.ui.item.method;

import java.net.URI;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.generic.filter.LogicFilter;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.method.filter.LinkedToPropertyFilter;
import nth.introspect.layer5provider.reflection.info.method.filter.ParameterTypeFilter;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.item.HierarchicalItem;
import nth.introspect.ui.view.FormView;

public class PropertyMethodOwnerItem extends HierarchicalItem {

	private FormView formView;

	public PropertyMethodOwnerItem (FormView formView, ReadOnlyValueModel parameterValueModel) {
		this(formView, parameterValueModel,null);
	}

	public PropertyMethodOwnerItem(FormView formView,
			ReadOnlyValueModel parameterValueModel,
			PropertyInfo propertyToExclude) {
		super(formView.getuserInterfaceContainer().getLanguageProvider());
		this.formView = formView;
		pupulateChildren(formView, parameterValueModel, propertyToExclude);
	}

	public void pupulateChildren(FormView formView,
			ReadOnlyValueModel parameterValueModel,
			PropertyInfo propertyInfo) {
		ReadOnlyValueModel domainValueModel = formView.getDomainValueModel();
		Class<?> domainClass = domainValueModel.getValueType();
		Class<?> parameterClass = parameterValueModel.getValueType();

		ReflectionProvider reflectionProvider=formView.getuserInterfaceContainer().getReflectionProvider();
		List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(domainClass);
		for (PropertyInfo otherPropertyInfo : propertyInfos) {

			if (otherPropertyInfo != propertyInfo) {

				LogicFilter<MethodInfo> filter = new LogicFilter<MethodInfo>(
						new LinkedToPropertyFilter(otherPropertyInfo));
				filter.and(new ParameterTypeFilter(parameterClass));

				List<MethodInfo> propertyMethods = reflectionProvider.getMethodInfos(domainClass, filter);
				for (MethodInfo propertyMethodInfo : propertyMethods) {
					PropertyMethodItem propertyMethodItem = new PropertyMethodItem(
							formView, otherPropertyInfo, propertyMethodInfo,
							parameterValueModel, true);
					getChildren().add(propertyMethodItem);
				}
			}
		}
	}

	@Override
	public URI getIconURI() {
		return formView.getViewIconURI();
	}

	@Override
	public String getText() {
		// TODO return TitleUtil.createTitle(methodInfo, methodParameter,
		// false);
		return formView.getViewTitle();// using description instead of title
										// because the title could be truncated
	}

	@Override
	public boolean isVisible() {
		return hasChildren();
	}

	@Override
	public String getDescription() {
		return formView.getViewDescription();
	}

}
