package nth.introspect.domain.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nth.introspect.Introspect;
import nth.introspect.domain.test.TestsService;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.util.TypeUtil;

public class ClassDiagramService {

	private final List<Class<?>> serviceClasses;

	public ClassDiagramService() {
		serviceClasses = new ArrayList<Class<?>>();
		serviceClasses.add(Introspect.class);
		serviceClasses.add(TestsService.class);
	}

	@GenericReturnType(ClassFeature.class)
	public List<ClassFeature> allServiceClasses() {
		List<ClassFeature> classFeatures = new ArrayList<ClassFeature>();
		for (Class<?> serviceClass : serviceClasses) {
			classFeatures.add(new ClassFeature(serviceClass));
		}
		return classFeatures;
	}

	@GenericReturnType(ClassFeature.class)
	public List<ClassFeature> allClasses() {
		Set<Class<?>> foundClasses = new HashSet<Class<?>>();

		UserInterfaceProvider<?> userInterfacePort = Introspect.getUserInterfaceProvider();
		int index = 0;
		int maxValue = serviceClasses.size();
		for (Class<?> serviceClass : serviceClasses) {
			userInterfacePort.showProgressDialog("Finding classes", index, maxValue);
			getReferencedClasses(serviceClass, foundClasses);
			index++;
		}

		List<ClassFeature> classFeatures = new ArrayList<ClassFeature>();
		for (Class<?> clazz : foundClasses) {
			classFeatures.add(new ClassFeature(clazz));
		}
		Comparator<? super ClassFeature> s;
		Collections.sort(classFeatures, new Comparator<ClassFeature>() {

			@Override
			public int compare(ClassFeature classFeature1, ClassFeature classFeature2) {
				return classFeature1.toString().compareTo(classFeature2.toString());
			}
		});

		userInterfacePort.showProgressDialog("Finding classes", maxValue, maxValue);

		return classFeatures;
	}

	private void getReferencedClasses(Class<? extends Object> clazz, Set<Class<?>> foundClasses) {
		if (clazz != null && !foundClasses.contains(clazz) && !TypeUtil.isJavaType(clazz) && !TypeUtil.isVoidType(clazz)) {
			foundClasses.add(clazz);
			System.out.println(clazz.getCanonicalName());

			DomainProvider domainProvider = Introspect.getDomainProvider();

			List<PropertyInfo> propertyInfos = domainProvider.getPropertyInfos(clazz);
			for (PropertyInfo propertyInfo : propertyInfos) {
				Class<?> propertyType = propertyInfo.getPropertyType().getType();
				getReferencedClasses(propertyType, foundClasses);// recursive call
			}

			List<MethodInfo> methodInfos = domainProvider.getMethodInfos(clazz);
			for (MethodInfo methodInfo : methodInfos) {
				Class<?> returnType = methodInfo.getReturnType().getTypeOrGenericCollectionType();
				getReferencedClasses(returnType, foundClasses);// recursive call

				Class<?> parameterType = methodInfo.getParameterType().getTypeOrGenericCollectionType();
				getReferencedClasses(parameterType, foundClasses);// recursive call
			}
		}
	}

	// @FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	// public ClassDiagram testClassDiagram() {
	// ClassDiagram classDiagram = new ClassDiagram(Test.class);
	// return classDiagram;
	// }

	@FormMode(FormModeType.executeMethodDirectly)
	public ClassDiagram openClassDiagram(ClassFeature classFeature) {
		Class<?> introspectedClass = classFeature.getType();
		if (introspectedClass == null) {
			// a method without a return value (void) or without a parameter does not have a type!
			throw new RuntimeException(classFeature.toString() + " does not have a type reference.");
		} else {
			ClassDiagram classDiagram = new ClassDiagram(introspectedClass);
			return classDiagram;
		}
	}

	// public void createClassDiagramReport() {
	// // TODO
	// }
}
