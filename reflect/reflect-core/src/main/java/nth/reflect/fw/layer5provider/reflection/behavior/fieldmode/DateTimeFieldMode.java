package nth.reflect.fw.layer5provider.reflection.behavior.fieldmode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;

/**
 * <p>
 * {@link DomainObject}s are rendered by the user interface layer as fields when
 * a {@link DomainObject} is displayed in a {@link FormTab}. The {@link FormTab}
 * will determine in what type of field these {@link DomainObjectProperty}s are
 * displayed (textbox, combobox, etc). When the {@link DomainObjectProperty}
 * represents a date or time (Date, Calendar, LocalDate, LocalTime) , you can
 * specify a specific field type using the {@link DateTimeFieldMode} annotation.
 * The {@link DateTimeFieldMode} annotation needs to be put before the getter
 * method of a {@link DomainObjectProperty}.<br>
 * Note that the {@link DateTimeFieldMode} can not be changed during
 * runtime.<br>
 * Note that the {@link DateTimeFieldMode} can also be specified with the
 * {@link Format} annotation.
 * </p>
 * <p>
 * Syntax: @{@link DateTimeFieldMode}({@link DateTimeFieldModeType} mode)
 * </p>
 * <p>
 * Examples of alternative {@link DateTimeFieldModeType}s:
 * <ul>
 * <li>{@link DateTimeFieldModeType#DATE}: renders a date only field</li>
 * <li>{@link DateTimeFieldModeType#TIME}: renders a time only field </li>
 * <li>{@link DateTimeFieldModeType#DATE_TIME}: renders a date and time field</li>
 * </ul>
 * </p>
 * <p>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DateTimeFieldMode {
	public DateTimeFieldModeType mode();
}
