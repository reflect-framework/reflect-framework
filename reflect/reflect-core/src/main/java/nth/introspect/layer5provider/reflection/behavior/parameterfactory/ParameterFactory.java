package nth.introspect.layer5provider.reflection.behavior.parameterfactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * When adding the {@link ParameterFactory} Annotation before an
 * {@link ActionMethod}, the {@link UserInterfaceController} know's it is
 * allowed to try to instantiate a new DomainObject. This {@link DomainObject}
 * can then be edited by the user (depending how the {@link ActionMethod} is
 * annotated, see {@link ExecutionMode}) after which it is passed as method
 * parameter when the {@link ActionMethod} is invoked.
 * </p>
 * <p>
 * Note that the method parameter (a {@link DomainObject}) can only be
 * instantiated by an {@link ParameterFactory} annotation if it has a public
 * constructor without parameters. If not use the ...ParameterFactory method.
 * </p>
 * <p>
 * Syntax: @ParameterFactory
 * </p>
 * <p>
 * TODO EXAMPLE find customer with paramfactory annotation
 * </p>
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParameterFactory {
}
