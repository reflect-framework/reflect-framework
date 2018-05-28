package nth.introspect.documentation;

import nth.introspect.IntrospectApplication;
import nth.introspect.IntrospectFramework;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureObject;

/**
 * <p>
 * Depending on your application you are creating, you might need different
 * InfrasturcoreObjects to communicate with databases (repositories), mail
 * servers (email clients), web servers (e.g. SOAP or restful clients), etc. The
 * {@link IntrospectFramework} comes with several
 * {@link IntrospectInfrastructureProjects} that you can optionally use and/or
 * extend.
 * <p>
 * Before you can use an {@link InfrastructureObject}, you need to register an
 * {@link InfrastructureObject} by overwriting the
 * {@link IntrospectApplication#getInfrastructureClasses()} method.
 * </p>
 * <p>
 * U can use an {@link InfrastructureObject} in a {@link DomainObject} or
 * {@link ServiceObject} by adding the {@link InfrastructureObject} as a
 * parameter of the constructor and linking it to a private final field.
 * </p>
 * 
 * <p>
 * The following sections will describe the optional
 * {@link IntrospectInfrastructureProjects} that come with the
 * {@link IntrospectFramework}.
 * <p>
 * TODO
 * </p>
 * 
 * @author nilsth
 *
 */
public interface IntrospectInfrastructureProjects extends Documentation {

}
