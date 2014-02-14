package nth.introspect.provider.domain.info.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.IntrospectionInfo;
import nth.introspect.provider.domain.info.type.MethodParameterType;
import nth.introspect.provider.domain.info.type.MethodReturnType;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.provider.domain.info.valuemodel.factories.AnnotationValueModelFactory;
import nth.introspect.provider.domain.info.valuemodel.factories.MethodValueModelFactory;
import nth.introspect.provider.domain.info.valuemodel.impl.SimpleValue;
import nth.introspect.provider.domain.info.valuemodel.impl.TextValue;
import nth.introspect.provider.path.id.MethodIconID;
import nth.introspect.valuemodel.ValueModels;

/**
 * Provides information on a bean method.<br>
 * This class is inspired by the MethodDiscriptor class, which I do not use because it is not implemented by Android
 * 
 * @author nilsth
 * 
 */

public class MethodInfo implements IntrospectionInfo {

	private ValueModels valueModels;
	public final static String TEXT = "text";
	public final static String DESCRIPTION = "description";
	public final static String ORDER = "order";
	public final static String VISIBLE = "visible";
	public final static String ENABLED = "enabled";
	public final static String ICON = "icon";
	public final static String PARAMETER_FACTORY = "parameterFactory";
	public final static String PARAMETER_MODIFIER = "parameterModifier";
	public final static String FORM_MODE = "formMode";// TODO change to userInterfaceMode???
	public final static String ACCESS_KEY = "accessKey";
	public final String[] ANNOTATION_NAMES = new String[] {ICON, ORDER, VISIBLE, ENABLED, RETURN_CLASS, FORM_MODE};
	public final static String[] METHOD_NAMES = new String[] {PARAMETER_FACTORY, ICON, VISIBLE, ENABLED};
	public static final String RETURN_CLASS = "returnClass";

	private final String name;
	private final String namePath;
	private final Method method;
	private final String linkedPropertyName;
	private final MethodParameterType parameterType; 
	private final MethodReturnType returnType; 

	public enum FormModeType {
		//TODO rename to executionMode
		//TODO rename executeMethodDirectly to EXECUTE_METHOD_DIRECTLY
		//TODO rename executeMethodAfterConformation to EXECUTE_METHOD_AFTER_CONFORMATION
		//TODO rename editParameterThenExecuteMethodOrCancel to EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL
		//TODO Remove showParameterThenExecuteMethodOrCancel: (same as EXECUTE_METHOD_AFTER_CONFORMATION)
		//TODO Remove showParameterThenClose: (same as EXECUTE_METHOD_DIRECTLY of a method that returns a value)
		executeMethodDirectly, executeMethodAfterConformation, editParameterThenExecuteMethodOrCancel, showParameterThenExecuteMethodOrCancel, showParameterThenClose
	}

	public MethodInfo(Method method) {
		this(method, null);
	}

	public MethodInfo(Method method, String linkedPropertyName) {
		this.method = method;
		this.linkedPropertyName = linkedPropertyName;
		this.name = method.getName();
		this.namePath = getNamePath(method);
		this.returnType = new MethodReturnType(method);
		this.parameterType = new MethodParameterType(method);
		this.valueModels = new ValueModels();

		String regExpToRemoveFromDefaultValue = linkedPropertyName == null ? null : "^" + linkedPropertyName;

		// create default value getters
		valueModels.put(TEXT, new TextValue(this, TEXT, regExpToRemoveFromDefaultValue));
		valueModels.put(DESCRIPTION, new TextValue(this, DESCRIPTION, regExpToRemoveFromDefaultValue));
		// valueModels.put(ACCESS_KEY, new AccessKeyValue(this, NAME));
		// valueModels.put(ICON, new IconValue(this));
		valueModels.put(ICON, new SimpleValue(new MethodIconID(method)));
		valueModels.put(ORDER, new SimpleValue(0));
		valueModels.put(VISIBLE, new SimpleValue(true));
		valueModels.put(ENABLED, new SimpleValue(true));
		valueModels.put(FORM_MODE, new SimpleValue(FormModeType.editParameterThenExecuteMethodOrCancel));

		// create value getters from annotations
		// TODO add a value getter for domainclass from EJB annotations (when available) because Introspect cannot determine the generic type of a collection
		valueModels.putAll(AnnotationValueModelFactory.create(this, ANNOTATION_NAMES));

		// //create method value getters
		valueModels.putAll(MethodValueModelFactory.create(this, METHOD_NAMES));

		// create xml value getters
		// TODO valueModels.putAll(XmlValueModelFactory.create( this));

		
		// overwrite form mode when necessary
		if (TypeCategory.NONE== getParameterType().getTypeCategory() && getFormMode() != FormModeType.executeMethodAfterConformation) {
			valueModels.put(FORM_MODE, new SimpleValue(FormModeType.executeMethodDirectly));
		}

	}

	private String getNamePath(Method method) {
		StringBuffer namePath = new StringBuffer();
		namePath.append(method.getDeclaringClass().getCanonicalName());
		namePath.append(".");
		namePath.append(method.getName());
		return namePath.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	public String getNamePath() {
		return namePath;
	}

	public Method getMethod() {
		return method;
	}

	public String getText() {
		return valueModels.getStringValue(TEXT);
	}

	public String getDescription() {
		return valueModels.getStringValue(DESCRIPTION);
	}

	// public String getIcon(Object serviceObject) {
	// return valueModels.getStringValue(ICON, serviceObject);
	// }

	public CharSequence getIconID(Object introspectedObject) {
		Object value = valueModels.getValue(ICON, introspectedObject);
		if (value == null) {
			return null;
		}
		return (CharSequence) value;
	}

	public URI getIconURI(Object introspectedObject) {
		return Introspect.getPathProvider().getImagePath(getIconID(introspectedObject));
	}

	public Integer getOrder() {
		return valueModels.getIntegerValue(ORDER);
	}

	public Boolean isVisible(Object serviceObject) {
		return valueModels.getBooleanValue(VISIBLE, serviceObject);
	}

	public Boolean isEnabled(Object serviceObject) {
		return valueModels.getBooleanValue(ENABLED, serviceObject);
	}

	public FormModeType getFormMode() {//TODO getRefactor to getExecutionMode (see also TODO in formModeType)
		return (FormModeType) valueModels.getValue(FORM_MODE);
	}

	public void setFormMode(FormModeType formMode) {
		valueModels.put(FORM_MODE,new SimpleValue(formMode));
	}
	
	public MethodParameterType getParameterType() {
		return parameterType;
	}

	public MethodReturnType getReturnType() {
		return returnType;
	}


	public boolean hasParameterFactory() {
		return valueModels.containsKey(PARAMETER_FACTORY);
	}

	public Object createMethodParameter(Object introspectedObject) throws InstantiationException, IllegalAccessException {
		switch (getParameterType().getTypeCategory()) {
		case NONE:
			return null;
		case DOMAIN_TYPE:
			if (hasParameterFactory()) {
				// get parameter value from parameter factory method
				return valueModels.getValue(PARAMETER_FACTORY, introspectedObject);
			} else {
				// try to create a parameter value from the parameter class
				Class<?> parameterClass = getParameterType().getType();
				return parameterClass.newInstance();
			}
			// TODO case collectionType
		default:
			break;
		}

		return null;
	}

	public Object invoke(Object methodOwner, Object methodParameter) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Object[] arguments = null;
		switch (getParameterType().getTypeCategory()) {
		case NONE:
			arguments = null;
			break;
		case DOMAIN_TYPE:
			arguments = new Object[] {methodParameter};
			break;
		default:
			break;
		}
		return method.invoke(methodOwner, arguments);
	}

	public String getLinkedPropertyName() {
		return linkedPropertyName;
	}

	@Override
	public String toString() {
		return namePath;
	}

	

}
