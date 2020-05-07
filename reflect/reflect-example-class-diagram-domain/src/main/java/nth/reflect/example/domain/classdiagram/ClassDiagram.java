package nth.reflect.example.domain.classdiagram;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class ClassDiagram {

	private ClassFeature name;
	private ClassFeature superclass;
	private final List<ClassFeature> interfaces;
	private final List<ClassFeature> properties;
	private final List<ClassFeature> methods;

	public ClassDiagram() {
		interfaces = new ArrayList<ClassFeature>();
		properties = new ArrayList<ClassFeature>();
		methods = new ArrayList<ClassFeature>();
	}

	public ClassDiagram(ReflectionProvider reflectionProvider, Class<?> objectClass) {
		this();// initialize lists

		// name
		setName(new ClassFeature(objectClass));

		// superclass
		Class<?> superClass = objectClass.getSuperclass();
		if (superClass != null) {
			setSuperclass(new ClassFeature(superClass));
		}

		// interfaces
		Class<?>[] interfaces = objectClass.getInterfaces();
		for (Class<?> interfaze : interfaces) {
			getInterfaces().add(new ClassFeature(interfaze));
		}

		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(objectClass);

		// properties
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		for (PropertyInfo propertyInfo : propertyInfos) {
			getProperties().add(new ClassFeature(propertyInfo));
		}

		// methods
		List<ActionMethodInfo> actionMethodInfos = domainClassInfo.getActionMethodInfosSorted();
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			getMethods().add(new ClassFeature(actionMethodInfo));
		}
	}

	@Order(value = 1)
	public ClassFeature getName() {
		return name;
	}

	public void setName(ClassFeature name) {
		this.name = name;
	}

	@Order(value = 2)
	public ClassFeature getSuperclass() {
		return superclass;
	}

	public void setSuperclass(ClassFeature superclass) {
		this.superclass = superclass;
	}

	@Order(value = 3)
	public List<ClassFeature> getInterfaces() {
		return interfaces;
	}

	@Order(value = 4)
	public List<ClassFeature> getProperties() {
		return properties;
	}

	@Order(value = 5)
	public List<ClassFeature> getMethods() {
		return methods;
	}

}
