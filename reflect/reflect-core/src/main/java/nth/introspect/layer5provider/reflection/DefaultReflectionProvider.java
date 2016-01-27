package nth.introspect.layer5provider.reflection;

import java.util.HashMap;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfoFactory;
import nth.introspect.layer5provider.reflection.info.userinterfacemethod.ConfirmActionMethodParameterInfo;
import nth.introspect.layer5provider.reflection.info.userinterfacemethod.EditActionMethodParameterInfo;
import nth.introspect.layer5provider.reflection.info.userinterfacemethod.ShowActionMethodResultInfo;

public class DefaultReflectionProvider implements ReflectionProvider {

	// TODO Continue code review here
	private final HashMap<Class<?>, ClassInfo> classInfos;
	private final HashMap<Class<?>, EditActionMethodParameterInfo> editActionMethodParameterInfos;
	private final HashMap<Class<?>, ConfirmActionMethodParameterInfo> confirmActionMethodParameterInfos;
	private final HashMap<Class<?>, ShowActionMethodResultInfo> showActionMethodresultInfos;
	private final ProviderContainer providerContainer;

	public DefaultReflectionProvider(ProviderContainer providerContainer) {
		this.providerContainer = providerContainer;
		classInfos = new HashMap<>();
		editActionMethodParameterInfos = new HashMap<>();
		confirmActionMethodParameterInfos = new HashMap<>();
		showActionMethodresultInfos = new HashMap<>();
	}

	public ClassInfo getClassInfo(Class<?> objectClass) {
		if (!classInfos.containsKey(objectClass)) {
			classInfos.put(objectClass,
					ClassInfoFactory.create(providerContainer, objectClass));
		}
		return classInfos.get(objectClass);
	}

	@Override
	public EditActionMethodParameterInfo getEditActionMethodParameterInfo(
			ActionMethodInfo actionMethodInfo) {
		Class<?> methodParameterType=actionMethodInfo.getParameterType().getType();
		if (!editActionMethodParameterInfos.containsKey(methodParameterType)) {
			IntrospectApplication introspectApplication = providerContainer
					.get(IntrospectApplication.class);
			Class<? extends UserInterfaceController> userInterfaceControllerClass = introspectApplication
					.getUserInterfaceControllerClass();
			editActionMethodParameterInfos.put(methodParameterType,
					new EditActionMethodParameterInfo(
							userInterfaceControllerClass, actionMethodInfo));
		}
		return editActionMethodParameterInfos.get(methodParameterType);
	}

	@Override
	public ConfirmActionMethodParameterInfo getConfirmActionMethodParameterInfo(
			ActionMethodInfo actionMethodInfo) {
		Class<?> methodParameterType=actionMethodInfo.getParameterType().getType();
		if (!confirmActionMethodParameterInfos.containsKey(methodParameterType)) {
			IntrospectApplication introspectApplication = providerContainer
					.get(IntrospectApplication.class);
			Class<? extends UserInterfaceController> userInterfaceControllerClass = introspectApplication
					.getUserInterfaceControllerClass();
			confirmActionMethodParameterInfos.put(methodParameterType,
					new ConfirmActionMethodParameterInfo(
							userInterfaceControllerClass, actionMethodInfo));
		}
		return confirmActionMethodParameterInfos.get(methodParameterType);
	}
	
	@Override
	public ShowActionMethodResultInfo getShowActionMethodResultInfo(
			ActionMethodInfo actionMethodInfo) {
		Class<?> methodParameterType=actionMethodInfo.getParameterType().getType();
		if (!showActionMethodresultInfos.containsKey(methodParameterType)) {
			IntrospectApplication introspectApplication = providerContainer
					.get(IntrospectApplication.class);
			Class<? extends UserInterfaceController> userInterfaceControllerClass = introspectApplication
					.getUserInterfaceControllerClass();
			showActionMethodresultInfos.put(methodParameterType,
					new ShowActionMethodResultInfo(
							userInterfaceControllerClass, actionMethodInfo));
		}
		return showActionMethodresultInfos.get(methodParameterType);
	}
}
