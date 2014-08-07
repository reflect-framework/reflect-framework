package nth.introspect.container.inject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.container.IntrospectContainer;

/**
 * This annotation is placed on fields where links to other objects need to be injected by the {@link IntrospectContainer}.
 * You could also use any other annotation named inject (like javax.inject.Inject)
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)//TODO move to constructor injection
public @interface Inject {

}
