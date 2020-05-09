package nth.reflect.fw.layer5provider.reflection.behavior.format;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
/**
 * <p>
 * Some property types such as {@link Date}, time, {@link Number} can be
 * formatted with help of the {@link Format} annotation. Note that the format
 * can NOT be changed during runtime.
 * </p>
 * The {@link Format} annotation can be added before the getter method of a
 * {@link DomainObjectProperty}. </p>
 * <p>
 * Syntax: @Format(string pattern)
 * </p>
 * <p>
 * Please see the JavaDoc of the {@link SimpleDateFormat} and
 * {@link DecimalFormat} formatters to learn more about the patterns that can be
 * used. See the {@link FormatFactory} to learn how the formating
 * works for the different {@link DomainObjectProperty} types.
 * 
 * </p>
 * TODO example
 * 
 * 
 * @author nilsth
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Format {
	public String pattern();
}
