package nth.reflect.fw.layer5provider.reflection.behavior.description;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * You can override the default description value for the English language, by
 * putting the {@link Description} annotation before the getter method of a
 * {@link DomainObjectProperty} or before an {@link ActionMethod}
 * </p>
 * TODO EXAMPLE
 * <p>
 * Note that the {@link Description} annotation is intended for the English
 * language only. Use the Description default if you want to use multiple
 * languages
 * </p>
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Description {
	public String englishDescription();
}
