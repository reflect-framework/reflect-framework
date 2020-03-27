package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.title.TitleModel;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationMethodFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfoFactory;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfoFactory;

/**
 * Provides information on a {@link DomainObject}.<br>
 * 
 * @author nilsth
 * 
 */
public class DomainClassInfo extends ClassInfo {

	private final TitleModel titleModel;
	private final FontIconModel fontIconModel;
	private final List<Method> validationMethods;
	private final List<PropertyInfo> propertyInfosSorted;
	private final List<ActionMethodInfo> actionMethodInfosSorted;

	public DomainClassInfo(ProviderContainer providerContainer, Class<?> domainClass) {
		super(providerContainer, domainClass);
		ReflectionProvider reflectionProvider = providerContainer.get(ReflectionProvider.class);
		this.titleModel = new TitleModel(reflectionProvider);
		this.fontIconModel = FontIconModelFactory.create(domainClass);
		this.validationMethods = ValidationMethodFactory.create(domainClass);
		this.propertyInfosSorted = PropertyInfoFactory.createSorted(providerContainer, domainClass);
		this.actionMethodInfosSorted = ActionMethodInfoFactory.createSorted(providerContainer, this);
	}

	public URL getFontIconUrl(Object obj) {
		return fontIconModel.getFontIconUrl(obj);
	}

	public String getTitle(Object obj) {
		return titleModel.getTitle(obj);
	}

	public List<Method> getAllValidationMethods() {
		return validationMethods;
	}

	public List<PropertyInfo> getPropertyInfosSorted() {
		return propertyInfosSorted;
	}

	public List<PropertyInfo> getPropertyInfosSortedAndVisibleInTable() {
		List<PropertyInfo> sortedPropertyInfosVisibleInTable = propertyInfosSorted.stream()
				.filter(propertyInfo -> propertyInfo.isVisibleInTable()).collect(Collectors.toList());
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

	public List<ActionMethodInfo> getActionMethodInfos(Predicate<ActionMethodInfo> filter) {
		List<ActionMethodInfo> filteredActionMethods = actionMethodInfosSorted.stream().filter(filter)
				.collect(Collectors.toList());
		return filteredActionMethods;
	}

}
