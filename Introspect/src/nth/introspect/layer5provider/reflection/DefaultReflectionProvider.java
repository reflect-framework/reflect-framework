package nth.introspect.layer5provider.reflection;

import java.util.HashMap;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.filter.FilterUtil;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfoFactory;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfoFactory;

public class DefaultReflectionProvider implements ReflectionProvider {
	private final HashMap<Class<?>, ClassInfo> classInfos;
	private final HashMap<Class<?>, List<ActionMethodInfo>> methodInfosPerClass;
	private final ProviderContainer providerContainer;

	public DefaultReflectionProvider(ProviderContainer providerContainer) {
		this.providerContainer = providerContainer;
		
		classInfos = new HashMap<Class<?>, ClassInfo>();
		methodInfosPerClass = new HashMap<Class<?>, List<ActionMethodInfo>>();
	}


	public ClassInfo getClassInfo(Class<?> objectClass) {
		if (!classInfos.containsKey(objectClass)) {
			classInfos.put(objectClass,
					ClassInfoFactory.create(providerContainer, objectClass));
		}
		return classInfos.get(objectClass);
	}

	/**
	 * @return A collection of ActionInfos that hold information on what actions
	 *         menus in table view and form vieuws should be generated for a
	 *         given service or domain class.
	 */
@Override
	public List<ActionMethodInfo> getMethodInfos(Class<?> objectClass) {
		if (!methodInfosPerClass.containsKey(objectClass)) {
			methodInfosPerClass.put(objectClass,
					ActionMethodInfoFactory.create(providerContainer, objectClass));
		}
		return methodInfosPerClass.get(objectClass);
	}

	@Override
	public List<ActionMethodInfo> getMethodInfos(Class<?> objectClass,
			Filter<ActionMethodInfo> methodInfoFilter) {
		List<ActionMethodInfo> actionMethodInfos = getMethodInfos(objectClass);
		List<ActionMethodInfo> foundMethodInfos = FilterUtil.filter(actionMethodInfos,
				methodInfoFilter);
		return foundMethodInfos;
	}







}
