package nth.reflect.fw.documentation;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;

/**
 * <p>
 * Depending on your application you are creating, you might need different
 * InfrasturcoreObjects to communicate with databases (repositories), mail
 * servers (email clients), web servers (e.g. SOAP or restful clients), etc. The
 * {@link ReflectFramework} comes with several
 * {@link ReflectInfrastructureProjects} that you can optionally use and/or
 * extend.
 * <p>
 * Before you can use an {@link InfrastructureObject}, you need to register an
 * {@link InfrastructureObject} by overwriting the
 * {@link ReflectApplication#getInfrastructureClasses()} method.
 * </p>
 * <p>
 * You can use an {@link InfrastructureObject} in a {@link DomainObject} or
 * {@link ServiceObject} by adding the {@link InfrastructureObject} as a
 * parameter of the constructor and linking it to a private final field.
 * </p>
 * 
 * <p>
 * The following sections will describe the optional
 * {@link ReflectInfrastructureProjects} that come with the
 * {@link ReflectFramework}.
 * <p>
 * TODO
 * </p>
 * 
 * @author nilsth
 *
 */
public interface ReflectInfrastructureProjects extends ReflectDocumentationInterface {

}
