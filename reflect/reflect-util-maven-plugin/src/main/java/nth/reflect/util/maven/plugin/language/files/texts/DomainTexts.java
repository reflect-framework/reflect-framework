package nth.reflect.util.maven.plugin.language.files.texts;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class DomainTexts extends Texts {

	private static final long serialVersionUID = 5337633032141935109L;
	private final ReflectionProvider reflectionProvider;
	private LanguageProvider languageProvider;

	public DomainTexts(DependencyInjectionContainer container) {
		reflectionProvider = container.get(ReflectionProvider.class);
		languageProvider = container.get(LanguageProvider.class);
		Set<Class<?>> domainClasses = findAllDomainClasses(container);
		for (Class<?> domainClass : domainClasses) {
			put(domainClass);
		}
	}

	private void put(Class<?> domainClass) {
		if (domainClass.isEnum()) {
			putEnumValues((Class<? extends Enum<?>>) domainClass);
		} else {
			putDomainObject(domainClass);
		}
	}

	private void putDomainObject(Class<?> domainClass) {
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

	private void putEnumValues(Class<? extends Enum<?>> enumerationType) {
		for (Enum<?> enumConstant : enumerationType.getEnumConstants()) {
			String key = languageProvider.getKey(enumConstant);
			String defaultEnglish = languageProvider.getDefaultValueFromKey(key);
			put(key, defaultEnglish);
		}

		putPropertiesFromTranslatableStringsFromStaticFields(enumerationType);
	}

	private Set<Class<?>> findAllDomainClasses(DependencyInjectionContainer container) {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		Set<Class<?>> foundDomainClasses = new HashSet();
		ServiceContainer serviceContainer = container.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();
		for (Object serviceObject : serviceObjects) {
			ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(serviceObject.getClass());
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
		if (!foundDomainClasses.contains(typeInfo.getType())) {// prevent (endless) round trips
			if (typeInfo.isDomainClass()) {
				append(foundDomainClasses, typeInfo.getType());
			} else {
				Optional<TypeInfo> arrayOrCollectionTypeInfo = typeInfo.getGenericTypeInfo();
				if (arrayOrCollectionTypeInfo.isPresent() && arrayOrCollectionTypeInfo.get().isDomainClass()) {
					append(foundDomainClasses, arrayOrCollectionTypeInfo.get().getType());
				}
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
