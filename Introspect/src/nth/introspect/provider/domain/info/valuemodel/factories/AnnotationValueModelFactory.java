package nth.introspect.provider.domain.info.valuemodel.factories;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.valuemodel.annotations.Enabled;
import nth.introspect.provider.domain.info.valuemodel.impl.SimpleValue;
import nth.introspect.util.StringUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;
import nth.introspect.valuemodel.ValueModels;

public class AnnotationValueModelFactory {

	private static final String VALUE = "value";
	private static final Class<?>[] SIGNATURE = new Class[0];
	private static final Object[] ARGUMENTS = new Object[0];
	
	public static HashMap<String, ReadOnlyValueModel> create(PropertyInfo propertyInfo, String[] names) {
		return create(propertyInfo.getReadMethod(),names);
	}

	public static Map< String, ReadOnlyValueModel> create(MethodInfo methodInfo, String[] names) {
		return create(methodInfo.getMethod(),names);
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, ReadOnlyValueModel> create(Method method, String[] names) {
		ValueModels valueModels = new ValueModels();
		String packageName = Enabled.class.getPackage().getName();
		for (String name : names) {
			try {
				StringBuffer annotationName = new StringBuffer(packageName);
				annotationName.append(".");
				annotationName.append(StringUtil.firstCharToUpperCase(name));
				Class<? extends Annotation> annotationClass = (Class<? extends Annotation>) Class.forName(annotationName.toString());
				Annotation annotation = method.getAnnotation(annotationClass);
				Method annotationValueMethod = annotation.getClass().getMethod(VALUE, SIGNATURE);
				Object value = annotationValueMethod.invoke(annotation, ARGUMENTS);
				valueModels.put(name, new SimpleValue(value));
			} catch (Exception e) {
				// The method does not the right type of annotation. Bad luck but no show stopper.
			}
		}
		return valueModels;
	}

	

}
