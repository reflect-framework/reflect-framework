package nth.introspect.layer5provider.reflection.behavior.parameterfactory;

import nth.introspect.layer2service.MainMenu;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * The parameter factory allows you to create an object for an
 * {@link ActionMethod}. This object can then be edited by the user (depending
 * how the {@link ActionMethod} is annotated, see {@link ExecutionMode}
 * annotation) after which it is passed as method parameter when the
 * {@link ActionMethod} is invoked.
 * </p>
 * <p>
 * The {@link MainMenu} will display all {@link ActionMethod}s of all registered
 * {@link ServiceObject}s that can directly be executed (without the need of an
 * opened {@link DomainObject}). This means that only {@link ActionMethod}s that
 * either have no method parameter or have an {@link ParameterFactory} are
 * displayed as menu items in the {@link MainMenu}.
 * <p>
 * 
 * <h3>ParameterFactory Annotation</h3>
 * <p>
 * {@insert ParameterFactory}
 * </p>
 * 
 * 
 * <h3>ParameterFactory Method</h3>
 * <p>
 * {@insert ParameterFactoryMethodModel}
 * </p>
 * 
 * @author nilsth
 *
 */
public class ParameterFactoryModelFactory {
	// TODO
}
