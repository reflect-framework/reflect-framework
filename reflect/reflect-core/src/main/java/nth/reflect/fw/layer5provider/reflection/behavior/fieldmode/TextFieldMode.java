package nth.reflect.fw.layer5provider.reflection.behavior.fieldmode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;

/**
 * <p>
 * {@link DomainObject}s are rendered by the user interface layer as fields when
 * a {@link DomainObject} is displayed in a {@link FormTab}. The {@link FormTab}
 * will determine in what type of field these {@link DomainObjectProperty}s are
 * displayed (textbox, combobox, etc). When the {@link DomainObjectProperty} is
 * a Text (String, number, etc) , you can select an alternative field type using
 * the {@link TextFieldMode} annotation. The {@link TextFieldMode} annotation
 * needs to be put before the getter method of a {@link DomainObjectProperty}.
 * Note that the format can not be changed during runtime.
 * </p>
 * <p>
 * Syntax: @TextFieldMode(FieldModeType mode)
 * </p>
 * <p>
 * Examples of alternative {@link FieldModeType}s:
 * <ul>
 * <li>{@link TextFieldModeType#SINGLE_LINE}: renders a text field to edit a
 * string with a single line</li>
 * <li>{@link TextFieldModeType#PASSWORD}: renders a field to edit a password
 * (obscuring the input so that other users can not read the secret
 * password)</li>
 * <li>{@link TextFieldModeType#MILTI_LINE}: renders a field to edit a string,
 * with multiple lines</li>
 * </ul>
 * </p>
 * <p>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TextFieldMode {
	public TextFieldModeType mode();
}
