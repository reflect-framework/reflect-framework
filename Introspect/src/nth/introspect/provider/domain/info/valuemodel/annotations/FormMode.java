package nth.introspect.provider.domain.info.valuemodel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FormMode {//TODO rename to MethodExecutionMode??
	public FormModeType value();
}
