package nth.reflect.fw.layer5provider.reflection.behavior;

import java.lang.reflect.Method;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * <p>
 * {@link BehavioralMethod}s are recognized by the {@link ReflectFramework} and
 * dynamically define how {@link DomainObject}s, {@link DomainObjectProperty}s
 * or {@link ActionMethod}s behave (how they act and how they displayed). The
 * {@link ReflectFramework} will call these {@link BehavioralMethod}s when the
 * user interface is updated to get the current behavioral values depending on
 * the state of the object (property values)
 * </p>
 * 
 * <h3>Behavioral Method Convention</h3>
 * <ul>
 * <li>Syntax: &lt;memberName&gt;&lt;behaviourName&gt;<br>
 * &lt;memberName&gt;= can be a {@link Class}Name, a
 * {@link DomainObjectProperty} Name or a {@link ActionMethod}Name<br>
 * &lt;behaviourName&gt;= A behavior like Icon, Hidden, Disabled, Validation,
 * etc</li>
 * <li>{@link BehavioralMethod}s do NOT have any parameters</li>
 * <li>{@link BehavioralMethod}s ALWAYS return a value (see
 * {@link BehavioralMethod#returnType()} of the different implementations</li>
 * <li>{@link BehavioralMethod}s are public</li>
 * <li>{@link BehavioralMethod}s are not static</li>
 * <li>{@link BehavioralMethod}s are normally located after the declaration of
 * the member:</li>
 * <ul>
 * <li>{@link BehavioralMethod}s for classes: are located after the constructors
 * </li>
 * <li>{@link BehavioralMethod}s for {@link DomainObjectProperty}: are located
 * after the getter and setter methods of the properties</li>
 * <li>{@link BehavioralMethod}s for {@link ActionMethod}: are located after the
 * {@link ActionMethod}</li>
 * </ul>
 * </ul>
 * 
 * @author nilsth
 *
 */
public abstract class BehavioralMethod {

	public abstract String getBehavioralName();

	public abstract Class<?> getReturnType();

	public boolean isValid(Method method) {
		boolean nameEndsWithSuffix = method.getName().endsWith(getBehavioralName());
		boolean returnTypeMatches = getReturnType().isAssignableFrom(method.getReturnType());
		return nameEndsWithSuffix && returnTypeMatches;
	}

	public Method findFor(Class<?> methodOwner) {
		String behavioralMethodName = createBehaviorMethodName(methodOwner.getSimpleName());
		return find(methodOwner, behavioralMethodName);
	}

	/**
	 * 
	 * @param method
	 *            can be a GetterMethod (for a {@link DomainObjectProperty}) or
	 *            a {@link ActionMethod}
	 * @return
	 */
	public Method findFor(Method method) {
		String behavioralMethodName;
		if (PropertyInfo.isGetterMethod(method)) {
			String propertyName = createPropertyName(method);
			behavioralMethodName = createBehaviorMethodName(propertyName);
		} else {
			behavioralMethodName = createBehaviorMethodName(method.getName());
		}
		return find(method.getDeclaringClass(), behavioralMethodName);
	}

	private Method find(Class<?> methodOwner, String behavioralMethodName) {
		Class<?>[] NO_PARAMETERS = new Class<?>[0];
		try {
			Method method = methodOwner.getMethod(behavioralMethodName, NO_PARAMETERS);
			if (getReturnType().isAssignableFrom(method.getReturnType())) {
				return method;// found
			} else {
				return null;// incorrect return type
			}
		} catch (Exception e) {
			return null;// method does not exist
		}
	}

	private String createPropertyName(Method getterMethod) {
		String propertyName;
		String methodName = getterMethod.getName();
		if (methodName.startsWith(PropertyInfo.IS_PREFIX)) {
			propertyName = methodName.substring(PropertyInfo.IS_PREFIX.length());
		} else {
			propertyName = methodName.substring(PropertyInfo.GET_PREFIX.length());
		}
		return StringUtil.firstCharToLowerCase(propertyName);
	}

	private String createBehaviorMethodName(String owner) {
		StringBuffer methodName = new StringBuffer();
		methodName.append(StringUtil.firstCharToLowerCase(owner));
		methodName.append(StringUtil.firstCharToUpperCase(getBehavioralName()));
		return methodName.toString();
	}

}
