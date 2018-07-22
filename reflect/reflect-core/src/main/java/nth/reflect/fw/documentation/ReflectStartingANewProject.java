package nth.reflect.fw.documentation;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureLayer;

/**
 * <p>
 * How you create a new {@link ReflectApplication} depends on the type of
 * application type that you want to create.
 * </p>
 * <p>
 * First decide on the type of application you want to create. Then look up the
 * corresponding paragraph in the {@link ReflectApplicationProjects} section.
 * There you will learn how to create a java project that contains an
 * application class that extends the {@link ReflectApplication} class. This
 * class will initialize and start the {@link ReflectFramework}. This class
 * also contains methods that you need to implement to provide the
 * {@link ServiceObject} classes and {@link InfrastructureLayer} classes that
 * are required in the application.
 * </p>
 * 
 * @author nilsth
 *
 */
public interface ReflectStartingANewProject extends ReflectDocumentationInterface {

}
