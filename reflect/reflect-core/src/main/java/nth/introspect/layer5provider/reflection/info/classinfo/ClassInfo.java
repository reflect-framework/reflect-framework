package nth.introspect.layer5provider.reflection.info.classinfo;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.filter.FilterUtil;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModelFactory;
import nth.introspect.layer5provider.reflection.behavior.title.TitleModel;
import nth.introspect.layer5provider.reflection.behavior.validation.ValidationMethodFactory;
import nth.introspect.layer5provider.reflection.info.NameInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfoFactory;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfoFactory;
import nth.introspect.layer5provider.reflection.info.property.TableVisibleFilter;

/**
 * Provides information on a {@link ServiceObject} or {@link DomainObject}.<br>
 * 
 * @author nilsth
 * 
 */
public class ClassInfo implements NameInfo {

	private final String simpleName;
	private final String canonicalName;
	private final DescriptionModel descriptionModel;
	private final Class<?> objectClass;
	private final DisplayNameModel displayNameModel;
	private final TitleModel titleModel;
	private final IconModel iconModel;
	private final List<Method> validationMethods;
	private final List<PropertyInfo> propertyInfosSorted;
	private final List<ActionMethodInfo> actionMethodInfosSorted;

	public ClassInfo(ProviderContainer providerContainer, Class<?> objectClass) {
		LanguageProvider languageProvider = providerContainer
				.get(LanguageProvider.class);
		ReflectionProvider reflectionProvider = providerContainer
				.get(ReflectionProvider.class);
		PathProvider pathProvider = providerContainer.get(PathProvider.class);
		this.simpleName = objectClass.getSimpleName();
		this.canonicalName = objectClass.getCanonicalName();
		this.objectClass = objectClass;
		this.displayNameModel = new DisplayNameModel(languageProvider,
				objectClass, simpleName, canonicalName);
		this.descriptionModel = new DescriptionModel(languageProvider,
				objectClass, simpleName, canonicalName);
		this.titleModel = new TitleModel(reflectionProvider);
		this.iconModel = IconModelFactory.create(objectClass,
				pathProvider.getImagePath());
		this.validationMethods = ValidationMethodFactory.create(objectClass);
		this.propertyInfosSorted = PropertyInfoFactory.createSorted(
				providerContainer, objectClass);
		this.actionMethodInfosSorted= ActionMethodInfoFactory.createSorted(providerContainer, this);
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public String getCanonicalName() {
		return canonicalName;
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}

	public String getDisplayName() {
		return displayNameModel.getText();
	}

	public String getDescription() {
		return descriptionModel.getText();
	}

	public URI getIconURI(Object obj) {
		return iconModel.getURI(obj);
	}

	public String getTitle(Object obj) {
		return titleModel.getTitle(obj);
	}

	@Override
	public String toString() {
		return canonicalName;
	}

	public List<Method> getAllValidationMethods() {
		return validationMethods;
	}

	public List<PropertyInfo> getPropertyInfosSorted() {
		return propertyInfosSorted;
	}

	public List<PropertyInfo> getPropertyInfosSortedAnsVisibleInTable() {
		List<PropertyInfo> sortedPropertyInfosVisibleInTable = FilterUtil
				.filter(propertyInfosSorted, new TableVisibleFilter());
		return sortedPropertyInfosVisibleInTable;
	}

	public PropertyInfo getPropertyInfo(String propertyName) {
		for (PropertyInfo propertyInfo : propertyInfosSorted) {
			if (propertyInfo.getSimpleName().equals(propertyName)) {
				return propertyInfo;
			}
		}
		return null;
	}

	public List<ActionMethodInfo> getActionMethodInfosSorted() {
		return actionMethodInfosSorted;
	}

	public ActionMethodInfo getActionMethodInfo(String methodName) {
		for (ActionMethodInfo actionMethodInfo : actionMethodInfosSorted) {
			if (actionMethodInfo.getSimpleName().equals(methodName)) {
				return actionMethodInfo;
			}
		}
		return null;
	}
	
	public List<ActionMethodInfo> getActionMethodInfos(
			Filter<ActionMethodInfo> methodInfoFilter) {
		List<ActionMethodInfo> foundMethodInfos = FilterUtil.filter(actionMethodInfosSorted,
				methodInfoFilter);
		return foundMethodInfos;
	}


}
