package nth.introspect.layer5provider.reflection.behavior.displayname;

import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * <p>
 * {@link Class}Names, {@link DomainObjectProperty}Names and
 * {@link ActionMethod}Names in the <a
 * href="https://en.wikipedia.org/wiki/Codebase">codebase</a> use names such as
 * OrderService, orderLines, addOrderLine (using no spaces, <a
 * href="https://nl.wikipedia.org/wiki/CamelCase">camelCase</a> and no special
 * characters). If the user is a an human, more user friendly names are needed
 * such as “Orders” “Order lines” and “Add order line”. The
 * {@link IntrospectFramework} will therefore convert these names to a human
 * readable format when needed.
 * </p>
 * <p>
 * The {@link IntrospectFramework} supports DisplayNames for multiple languages.
 * The {@link LanguageProvider} is used to get the default displayNames from a
 * language specific property files. For more information see
 * {@link LanguageProvider}.
 * </p>
 * <p>
 * It is recommended that the Description of {@link ServiceObject}s is the
 * plural form of the {@link DomainObject} that the {@link ServiceObject}
 * represents. In example: The displayName of CustomerService is “Customers”.
 * The {@link IntrospectFramework} creates the plural Description for
 * {@link ServiceObject}s by removing the “Service” suffix and append an “s” at
 * the end (or replace the last “y” with “ies”). Use the Description annotation
 * if the default plural form is incorrect.
 * 
 * 
 * @author nilsth
 *
 */
public class DisplayNameDefaultModel {
	// TODO
}
