package nth.reflect.fw.generic.translatablestring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

/**
 * The {@link Translatable} annotation is used to mark string constants so that they can be used to generate a Language propery file. See {@link LanguageProvider}
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Translatable {

}
