package nth.introspect.layer5provider.reflection.info.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

import nth.introspect.generic.valuemodel.ValueModels;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.path.id.MethodIconID;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.behavior.order.OrderFactory;
import nth.introspect.layer5provider.reflection.info.NameInfo;
import nth.introspect.layer5provider.reflection.info.type.MethodParameterType;
import nth.introspect.layer5provider.reflection.info.type.MethodReturnType;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;
import nth.introspect.layer5provider.reflection.info.valuemodel.factories.AnnotationValueModelFactory;
import nth.introspect.layer5provider.reflection.info.valuemodel.factories.MethodValueModelFactory;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.SimpleValue;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.TextValue;

/**
 * Provides information on a bean method.<br>
 * This class is inspired by the MethodDiscriptor class, which I do not use because it is not implemented by Android
 * 
 * @author nilsth
 * 
 */

public class MethodInfo implements NameInfo {

	private ValueModels valueModels;
	public final static String TEXT = "text";
	public final static String DESCRIPTION = "description";
	public final static String VISIBLE = "visible";
	public final static String ENABLED = "enabled";
	public final static String ICON = "icon";
	public final static String PARAMETER_FACTORY = "parameterFactory";
	public final static String PARAMETER_MODIFIER = "parameterModifier";
	public final static String EXECUTION_MODE = "executionMode";
	public final static String ACCESS_KEY = "accessKey";
	public final String[] ANNOTATION_NAMES = new String[] {ICON, VISIBLE, ENABLED, RETURN_CLASS, EXECUTION_MODE};
	public final static String[] METHOD_NAMES = new String[] {PARAMETER_FACTORY, ICON, VISIBLE, ENABLED};
	public static final String RETURN_CLASS = "returnClass";

	private final String simpleName;
	private final String canonicalName;
	private final Method method;
	private final String linkedPropertyName;
	private MethodParameterType parameterType; 
	private final MethodReturnType returnType;
	private final PathProvider pathProvider; 
	private final double order;

	
	
	public MethodInfo(PathProvider pathProvider, LanguageProvider languageProvider, Method method) {
		this(pathProvider, languageProvider, method, null);
	}

	public MethodInfo(PathProvider pathProvider, LanguageProvider languageProvider, Method method, String linkedPropertyName) {
		this.pathProvider = pathProvider;
		this.method = method;
		this.linkedPropertyName = linkedPropertyName;
		this.simpleName = method.getName();
		this.canonicalName = getCanonicalName(method);
		this.returnType = new MethodReturnType(method);
		this.parameterType = new MethodParameterType(method);
		this.order=OrderFactory.getOrderSequenceNumber(method);
		this.valueModels = new ValueModels();

		String regExpToRemoveFromDefaultValue = linkedPropertyName == null ? null : "^" + linkedPropertyName;

		// create default value getters
		
		valueModels.put(TEXT, new TextValue(this,languageProvider, TEXT, regExpToRemoveFromDefaultValue));
		valueModels.put(DESCRIPTION, new TextValue(this,languageProvider, DESCRIPTION, regExpToRemoveFromDefaultValue));
		// valueModels.put(ACCESS_KEY, new AccessKeyValue(this, NAME));
		// valueModels.put(ICON, new IconValue(this));
		valueModels.put(ICON, new SimpleValue(new MethodIconID(pathProvider, method)));
		valueModels.put(VISIBLE, new SimpleValue(true));
		valueModels.put(ENABLED, new SimpleValue(true));
		valueModels.put(EXECUTION_MODE, new SimpleValue(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL));

		// create value getters from annotations
		// TODO add a value getter for domainclass from EJB annotations (when available) because Introspect cannot determine the generic type of a collection
		valueModels.putAll(AnnotationValueModelFactory.create(this, ANNOTATION_NAMES));

		// //create method value getters
		valueModels.putAll(MethodValueModelFactory.create(this, METHOD_NAMES));

		// create xml value getters
		// TODO valueModels.putAll(XmlValueModelFactory.create( this));

		
		// overwrite form mode when necessary
		if (TypeCategory.NONE== getParameterType().getTypeCategory() && getExecutionMode() != ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION) {
			valueModels.put(EXECUTION_MODE, new SimpleValue(ExecutionModeType.EXECUTE_METHOD_DIRECTLY));
		}

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

	public CharSequence getIconID(Object obj) {
		Object value = valueModels.getValue(ICON, obj);
		if (value == null) {
			return null;
		}
		return (CharSequence) value;
	}

	public URI getIconURI(Object obj) {
		return pathProvider.getImagePath(getIconID(obj));
	}

	public double getOrder() {
		return order;
	}

	public Boolean isVisible(Object serviceObject) {
		return valueModels.getBooleanValue(VISIBLE, serviceObject);
	}

	public Boolean isEnabled(Object serviceObject) {
		return valueModels.getBooleanValue(ENABLED, serviceObject);
	}

	public ExecutionModeType getExecutionMode() {
		return (ExecutionModeType) valueModels.getValue(EXECUTION_MODE);
	}

	public void setFormMode(ExecutionModeType formMode) {
		valueModels.put(EXECUTION_MODE,new SimpleValue(formMode));
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

	public Object createMethodParameter(Object obj) throws InstantiationException, IllegalAccessException {
		switch (getParameterType().getTypeCategory()) {
		case NONE:
			return null;
		case DOMAIN_TYPE:
			if (hasParameterFactory()) {
				// get parameter value from parameter factory method
				return valueModels.getValue(PARAMETER_FACTORY, obj);
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
		return canonicalName;
	}

	

}
