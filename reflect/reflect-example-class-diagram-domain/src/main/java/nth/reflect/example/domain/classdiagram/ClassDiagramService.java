package nth.reflect.example.domain.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class ClassDiagramService {

	private final List<Class<?>> serviceClasses;
	private final ReflectionProvider reflectionProvider;
	private final ReflectApplication reflectApplication;

	public ClassDiagramService(ReflectApplication reflectApplication, ReflectionProvider reflectionProvider) {
		this.reflectApplication = reflectApplication;
		this.reflectionProvider = reflectionProvider;
		serviceClasses = new ArrayList<Class<?>>();
		serviceClasses.add(ClassDiagram.class);
	}

	public List<ClassFeature> allServiceClasses() {
		List<ClassFeature> classFeatures = new ArrayList<ClassFeature>();
		for (Class<?> serviceClass : serviceClasses) {
			classFeatures.add(new ClassFeature(serviceClass));
		}
		return classFeatures;
	}

	public List<ClassFeature> allClasses() {
		Set<Class<?>> foundClasses = new HashSet<Class<?>>();

		for (Class<?> serviceClass : serviceClasses) {
			// Task task=notificationProvider.addNewTask("Finding classes",
			// index, maxValue);
			getReferencedClasses(serviceClass, foundClasses);
		}

		List<ClassFeature> classFeatures = new ArrayList<ClassFeature>();
		for (Class<?> clazz : foundClasses) {
			classFeatures.add(new ClassFeature(clazz));
		}
		Collections.sort(classFeatures, new Comparator<ClassFeature>() {

			@Override
			public int compare(ClassFeature classFeature1, ClassFeature classFeature2) {
				return classFeature1.toString().compareTo(classFeature2.toString());
			}
		});

		// notificationProvider.remove(task)

		return classFeatures;
	}

	private void getReferencedClasses(Class<? extends Object> type, Set<Class<?>> foundClasses) {
		TypeInfo typeInfo = new TypeInfo(reflectApplication, type, type);
		if (type != null && !foundClasses.contains(type) && !typeInfo.isJavaType() && !typeInfo.isVoid()) {
			foundClasses.add(type);
			System.out.println(type.getCanonicalName());

			DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(type);

			List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
			for (PropertyInfo propertyInfo : propertyInfos) {
				Class<?> propertyType = propertyInfo.getTypeInfo().getType();
				// recursive call

				getReferencedClasses(propertyType, foundClasses);
			}

			List<ActionMethodInfo> actionMethodInfos = domainClassInfo.getActionMethodInfosSorted();
			for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
				Class<?> returnType = actionMethodInfo.getReturnTypeInfo().getGenericTypeInfo().get().getType();
				// recursive call
				getReferencedClasses(returnType, foundClasses);

				Class<?> parameterType = actionMethodInfo.getFirstParameterTypeInfo().getGenericTypeInfo().get().getType();
				// recursive call
				getReferencedClasses(parameterType, foundClasses);
			}
		}
	}

	// @FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	// public ClassDiagram testClassDiagram() {
	// ClassDiagram classDiagram = new ClassDiagram(AllTypeDomainObject.class);
	// return classDiagram;
	// }

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public ClassDiagram openClassDiagram(ClassFeature classFeature) {
		Class<?> objectClass = classFeature.getType();
		if (objectClass == null) {
			// a method without a return value (void) or without a parameter
			// does not have a type!
			throw new RuntimeException(classFeature.toString() + " does not have a type reference.");
		} else {
			ClassDiagram classDiagram = new ClassDiagram(reflectionProvider, objectClass);
			return classDiagram;
		}
	}

	// public void createClassDiagramReport() {
	// // TODO
	// }
}
