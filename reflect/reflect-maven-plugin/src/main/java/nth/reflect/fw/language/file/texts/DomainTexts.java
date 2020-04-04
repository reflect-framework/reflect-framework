package nth.reflect.fw.language.file.texts;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class DomainTexts extends Texts {

	private static final long serialVersionUID = 5337633032141935109L;
	private final ReflectionProvider reflectionProvider;

	public DomainTexts(DependencyInjectionContainer container) {
		reflectionProvider = container.get(ReflectionProvider.class);
		Set<Class<?>> domainClasses = findAllDomainClasses(container);
		for (Class<?> domainClass : domainClasses) {
			put(domainClass);
		}
	}

	private void put(Class<?> domainClass) {
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);

		put(domainClassInfo);

		List<ActionMethodInfo> actionMethodInfos = domainClassInfo.getActionMethodInfosSorted();
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			put(actionMethodInfo);
		}

		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		for (PropertyInfo propertyInfo : propertyInfos) {
			put(propertyInfo);
		}

		for (PropertyInfo propertyInfo : propertyInfos) {
			List<ActionMethodInfo> propertyActionMethodInfos = propertyInfo.getActionMethodInfos();
			for (ActionMethodInfo actionMethodInfo : propertyActionMethodInfos) {
				put(actionMethodInfo);
			}
		}

		putPropertiesFromTranslatableStringsFromStaticFields(domainClass);
	}

	private Set<Class<?>> findAllDomainClasses(DependencyInjectionContainer container) {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		Set<Class<?>> foundDomainClasses = new HashSet();
		ServiceContainer serviceContainer = container.get(ServiceContainer.class);
		List<Class<?>> serviceClasses = serviceContainer.getAllClasses();
		for (Class<?> serviceClass : serviceClasses) {
			ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(serviceClass);
			append(foundDomainClasses, serviceClassInfo);
		}
		return foundDomainClasses;
	}

	private void append(Set<Class<?>> foundDomainClasses, ServiceClassInfo serviceClassInfo) {
		List<ActionMethodInfo> actionMethodInfos = serviceClassInfo.getActionMethodInfosSorted();
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			append(foundDomainClasses, actionMethodInfo);
		}
	}

	private void append(Set<Class<?>> foundDomainClasses, ActionMethodInfo actionMethodInfo) {
		TypeInfo returnTypeInfo = actionMethodInfo.getReturnTypeInfo();
		append(foundDomainClasses, returnTypeInfo);
		TypeInfo parameterInfo = actionMethodInfo.getFirstParameterTypeInfo();
		append(foundDomainClasses, parameterInfo);
	}

	private void append(Set<Class<?>> foundDomainClasses, TypeInfo typeInfo) {
		if (typeInfo.isDomainClass()) {
			append(foundDomainClasses, typeInfo.getType());
		} else {
			Optional<TypeInfo> arrayOrCollectionTypeInfo = typeInfo.getArrayOrCollectionTypeInfo();
			if (arrayOrCollectionTypeInfo.isPresent() && arrayOrCollectionTypeInfo.get().isDomainClass()) {
				append(foundDomainClasses, arrayOrCollectionTypeInfo.get().getType());
			}
		}

	}

	private void append(Set<Class<?>> foundDomainClasses, Class<?> domainClass) {
		if (!foundDomainClasses.contains(domainClass)) {
			foundDomainClasses.add(domainClass);

			DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
			List<ActionMethodInfo> actionMethods = domainClassInfo.getActionMethodInfosSorted();
			for (ActionMethodInfo actionMethodInfo : actionMethods) {
				append(foundDomainClasses, actionMethodInfo);
			}

			List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
			for (PropertyInfo propertyInfo : propertyInfos) {
				append(foundDomainClasses, propertyInfo);
			}
		}
	}

	private void append(Set<Class<?>> foundDomainClasses, PropertyInfo propertyInfo) {
		TypeInfo typeInfo = propertyInfo.getTypeInfo();
		append(foundDomainClasses, typeInfo);
		List<ActionMethodInfo> actionMethodInfos = propertyInfo.getActionMethodInfos();
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			append(foundDomainClasses, actionMethodInfo);
		}
	}

}
