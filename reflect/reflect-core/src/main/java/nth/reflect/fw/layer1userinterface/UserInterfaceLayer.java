package nth.reflect.fw.layer1userinterface;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.documentation.ReflectApplicationProjects;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;

/**
 * 
 * The {@link UserInterfaceLayer} is also know as presentation layer (although I
 * think that 'presentation layer' is a poor name, because it is responsible for
 * so much more). The {@link UserInterfaceLayer} contains the
 * {@link UserInterfaceController}.
 * <p>
 * The {@link UserInterfaceContainer} is an {@link DependencyInjectionContainer}
 * that represents the {@link UserInterfaceLayer} and holds and manages the
 * {@link UserInterfaceController}.
 * </p>
 *
 * <p>
 * Note that this layer is the top layer, which means it may know all the
 * objects in the lower layers but not visa versa! See
 * {@link ReflectArchitecture}
 * </p>
 * 
 * <p>
 * The {@link ReflectFramework} has multiple user interface implementations. See
 * the {@link ReflectApplicationProjects} section to learn what types of user
 * interfaces are available, or download all the sources of the
 * {@link ReflectFramework} projects and see the class hierarchy of the
 * {@link UserInterfaceController} class to find all the different user
 * interface implementations.
 * </p>
 * 
 * <h2>User Interface Controller</h2> {@insert UserInterfaceController}
 * 
 * <h2>Graphical User Interface
 * Controller</h2>{@insert GraphicalUserinterfaceController}
 * 
 * <h2>Graphical User Interface Main Window</h2>{@insert MainWindow}
 * 
 * <h2>Graphical User Interface Application Bar</h2>{@insert ApplicationBar}
 * 
 * <h2>Graphical User Interface Main Menu</h2>{@insert MainMenu}
 * 
 * <h2>Graphical User Interface Tabs</h2>{@insert Tab}
 * 
 * <h2>Graphical User Interface Design
 * Rules</h2>{@insert ReflectGraphicalDesignRules}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface UserInterfaceLayer extends ReflectDocumentationInterface {
}
