package nth.reflect.fw.layer1userinterface;

import javafx.scene.control.Tab;
import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.documentation.ReflectApplicationProjects;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObject;

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
 * <h2>Main
 * Window</h2>{@insert nth.reflect.fw.gui.component.mainwindow.MainWindow}
 * 
 * <h2>Application
 * Bar</h2>{@insert nth.reflect.fw.gui.component.applicationbar.ApplicationBar}
 * 
 * <h2>Main Menu</h2>{@insert nth.reflect.fw.gui.component.mainmenu.MainMenu}
 * 
 * <h2>Tabs</h2>{@insert nth.reflect.fw.gui.component.tab.Tab}
 * 
 * <h2>Grid Tab</h2>{@insert nth.reflect.fw.gui.component.tab.grid.GridTab}
 * 
 * <h2>Form Tab</h2>{@insert nth.reflect.fw.gui.component.tab.form.FormTab}
 * 
 * <h2>Customized tabs</h2>
 * <p>
 * The {@link GridTab} and {@link FormTab} are normally used to select a
 * {@link DomainObject} and view or manipulate it. You can create your own
 * {@link Tab} and configure it to be used in your {@link ReflectApplication} if
 * you need a different representation of the {@link DomainObject}s. Typical
 * examples are:
 * <ul>
 * <li>A <a href="https://en.wikipedia.org/wiki/Blog">Blog</a> where a list of
 * Comments ({@link DomainObject}s) are displayed in a alternative manner (See
 * <a href=
 * "https://phraseapp.com/blog/posts/10-must-read-blogs-for-software-developer/">this
 * blog example</a>)</li>
 * <li>A map that represents information from a MapInformation
 * ({@link DomainObject} (See this <a href=
 * "https://www.google.com/maps/place/Amsterdam/@52.3545653,4.7585408,11z/data=!3m1!4b1!4m5!3m4!1s0x47c63fb5949a7755:0x6600fd4cb7c0af8d!8m2!3d52.3679843!4d4.9035614">this
 * map example</a>)</li>
 * </ul>
 * </p>
 * 
 * <h2>Graphical Design
 * Rules</h2>{@insert nth.reflect.fw.gui.style.ReflectGraphicalDesignRules}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface UserInterfaceLayer extends ReflectDocumentationInterface {
}
