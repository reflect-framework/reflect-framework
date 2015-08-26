package nth.introspect.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethods;
import nth.introspect.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.introspect.layer5provider.reflection.behavior.disabled.DisabledModel;
import nth.introspect.layer5provider.reflection.behavior.disabled.DisabledModelFactory;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeFactory;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenModelFactory;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModelFactory;
import nth.introspect.layer5provider.reflection.behavior.order.OrderFactory;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModel;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModelFactory;
import nth.introspect.layer5provider.reflection.info.NameInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.MethodParameterType;
import nth.introspect.layer5provider.reflection.info.type.MethodReturnType;

/**
 * <p>
 * Provides (reflection) information on a {@link ActionMethod}. This class is inspired
 * by the MethodDiscriptor class, which is not use because it is not implemented
 * by Android
 * </p>
 * 
 * @author nilsth
 * 
 */

public class ActionMethodInfo implements NameInfo {

	private final String simpleName;
	private final String canonicalName;
	private final Method actionMethod;
	private final String linkedPropertyName;
	private final MethodParameterType parameterType;
	private final MethodReturnType returnType;
	private final double order;
	private final DisplayNameModel displayNameModel;
	private final DescriptionModel descriptionModel;
	private final DisabledModel disabledModel;
	private final HiddenModel hiddenModel;
	private final ParameterFactoryModel parameterFactoryModel;
	private  ExecutionModeType executionMode; 
	private final IconModel iconModel;

	public ActionMethodInfo(ProviderContainer providerContainer, Method method) {
		this(providerContainer, method, null);
	}

	public ActionMethodInfo(ProviderContainer providerContainer, Method method,
			String linkedPropertyName) {
		LanguageProvider languageProvider = providerContainer
				.get(LanguageProvider.class);
		AuthorizationProvider authorizationProvider = providerContainer
				.get(AuthorizationProvider.class);

		PathProvider pathProvider = providerContainer.get(PathProvider.class);
		this.actionMethod = method;
		this.linkedPropertyName = linkedPropertyName;
		this.simpleName = method.getName();
		this.canonicalName = getCanonicalName(method);
		this.displayNameModel = new DisplayNameModel(languageProvider, method,
				simpleName, canonicalName, linkedPropertyName);
		this.descriptionModel = new DescriptionModel(languageProvider, method,
				simpleName, canonicalName, linkedPropertyName);
		this.returnType = new MethodReturnType(method);
		this.parameterType = new MethodParameterType(method);
		this.order = OrderFactory.create(method);
		this.disabledModel = DisabledModelFactory.create(authorizationProvider,
				method);
		this.hiddenModel = HiddenModelFactory.create(authorizationProvider,
				method);
		this.parameterFactoryModel = ParameterFactoryModelFactory.create(
				method, parameterType.getType());
		this.executionMode = ExecutionModeFactory.create(method, canonicalName);
		this.iconModel = IconModelFactory.create(method,
				pathProvider.getImagePath());

	}

	private String getCanonicalName(Method method) {
		StringBuffer conicalName = new StringBuffer();
		conicalName.append(method.getDeclaringClass().getCanonicalName());
		conicalName.append(".");
		conicalName.append(method.getName());
		return conicalName.toString();
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	public String getCanonicalName() {
		return canonicalName;
	}

	public Method getActionMethod() {
		return actionMethod;
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

	public double getOrder() {
		return order;
	}

	public Boolean isVisible(Object obj) {
		return !hiddenModel.isHiddenActionMethod(obj);
	}

	public Boolean isEnabled(Object obj) {
		return !disabledModel.isDisabled(obj);
	}

	public ExecutionModeType getExecutionMode() {
		return executionMode;
	}

	public void setExecutionMode(ExecutionModeType executeMethod) {
		this.executionMode=executeMethod;
	}

	public MethodParameterType getParameterType() {
		return parameterType;
	}

	public MethodReturnType getReturnType() {
		return returnType;
	}

	public boolean hasParameterFactory() {
		return parameterFactoryModel != null;
	}

	public Object createMethodParameter(Object obj)
			throws InstantiationException, IllegalAccessException {
		if (hasParameterFactory()) {
			return parameterFactoryModel.createNewMethodParameter(obj);
		} else {
			throw new NoParameterFactoryException(getCanonicalName());
		}
	}

	public Object invoke(Object methodOwner, Object methodParameter)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Object[] arguments = null;
		switch (getParameterType().getTypeCategory()) {
		case NONE:
			arguments = null;
			break;
		case DOMAIN_TYPE:
			arguments = new Object[] { methodParameter };
			break;
		default:
			break;
		}
		return actionMethod.invoke(methodOwner, arguments);
	}

	public String getLinkedPropertyName() {
		return linkedPropertyName;
	}

	@Override
	public String toString() {
		return canonicalName;
	}

	public static boolean isActionMethod(Method method,
			List<PropertyInfo> propertyInfos) {
		return !isMethodOfObjectClass(method)
				&& !isGetterOrSetterMethod(method, propertyInfos)
				&& !BehavioralMethods.isBehavioralMethod(method);
	}

	private static boolean isGetterOrSetterMethod(Method method,
			List<PropertyInfo> propertyInfos) {
		for (PropertyInfo propertyInfo : propertyInfos) {
			if (method.equals(propertyInfo.getGetterMethod())
					|| method.equals(propertyInfo.getSetterMethod())) {
				return true;
			}
		}
		return false;
	}

	private static boolean isMethodOfObjectClass(Method method) {
		return Object.class == method.getDeclaringClass();
	}

}
