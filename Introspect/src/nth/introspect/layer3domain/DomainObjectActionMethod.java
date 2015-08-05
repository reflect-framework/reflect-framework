package nth.introspect.layer3domain;

import nth.introspect.documentation.Documentation;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that to do something
 * with or for the given {@link DomainObject}. In example: an ShoppingCart
 * object may have an ActionMethod such as checkout().
 * </p>
 * <h3>Domain Object Menu</h3>
 * <p>
 * {@insert DomainObjectMenu}
 */
public interface DomainObjectActionMethod extends Documentation {

}
