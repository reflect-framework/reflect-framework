package nth.introspect.layer5provider.reflection.info.valuemodel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * TODO make obsolete (use reflection to determine the generic type)
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GenericReturnType {
	public Class<?> value();
}
