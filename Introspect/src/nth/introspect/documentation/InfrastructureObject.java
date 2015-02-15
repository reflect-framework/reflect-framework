package nth.introspect.documentation;

import nth.introspect.Introspect;

/**
 * {@link InfrastructureObject}s do not need to implement any interface or
 * superclass.<br>
 * <br>
 * {@link InfrastructureObject}s are placed in the {@link InfrastructureLayer}
 * and provide technical capabilities for the higher layers, such as:
 * <ul>
 * <li>Communicating with an database (classes are named ...Repository, i.e:
 * CustomerRepository, OrderRepository, etc)</li>
 * <li>Communicating with an other system (classes are named ...Client, i.e:
 * EmailClient, GoogleMapsClient, SOAPClient, etc)</li>
 * <li>Logging (classes are named ...Logger)</li>
 * <li>Creating value object (classes are named ...Factory, i.e: ReportFactory,
 * etc)</li>
 * </ul>
 * {@link InfrastructureObject}s can be new classes that are being developed, classes in an existing application, or existing {@link Introspect} classes or {@link InfrastructureObject}s from other open source projects 
 * @author Nils ten Hoeve
 * 
 */
public interface InfrastructureObject extends Documentation{

}
