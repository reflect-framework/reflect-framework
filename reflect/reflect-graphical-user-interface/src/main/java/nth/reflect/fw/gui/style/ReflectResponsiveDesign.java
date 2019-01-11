package nth.reflect.fw.gui.style;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.gui.component.applicationbar.ApplicationBar;
import nth.reflect.fw.gui.component.grid.Grid;
import nth.reflect.fw.gui.component.mainmenu.MainMenu;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.title.TitleModel;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link ReflectFramework} applications have a
 * <a href="https://en.wikipedia.org/wiki/Responsive_web_design">responsive
 * design</a>: a graphical user interface that optimizes its layout and its
 * <a href=
 * "https://en.wikipedia.org/wiki/List_of_graphical_user_interface_elements">components</a>,
 * for a variety of devices and screen sizes, in order to maximize
 * <a href="https://en.wikipedia.org/wiki/Usability">usability</a> and
 * <a href="https://en.wikipedia.org/wiki/User_experience">user experience</a>.
 * </p>
 * 
 * <p>
 * The size of the {@link ReflectApplication} could normal be anywhere between a
 * small smart phone in portrait orientation until a desktop monitor in
 * landscape orientation.
 * </p>
 * 
 * <h3>Different display widths</h3> The layout and components vary depending on
 * the {@link ReflectDisplayWidth}:
 * <ul>
 * <li>{@link ReflectDisplayWidth#SMALL}:</li>
 * <ul>
 * <li>The application title in the {@link ApplicationBar} is only visible when
 * the {@link MainMenu} is displayed (overlapping the tab headers because there
 * is no space to show both of them at the same time).</li>
 * <li>The {@link ActionMethod}s of the active {@link Tab} are displayed as
 * OverflowMenu after clicking the Overflow icon on the {@link ApplicationBar}
 * (because there is no space to show the {@link ActionMethod}s in the
 * {@link ApplicationBar}).</li>
 * <li>The {@link MainMenu} is displayed as a <a href=
 * "https://material.io/design/components/navigation-drawer.html">navigation
 * drawer</a> (overlapping the content area (tab panels) because there is no
 * space to show both of them at the same time).</li>
 * <li>{@link Grid}s only have one column that show the {@link DomainObject}
 * {@link TitleModel} because there is no space to show more columns</li>
 * <li>{@link FormTab}s show all {@link PropertyPanel}'s underneath each
 * other</li>
 * </ul>
 * <li>{@link ReflectDisplayWidth#MEDIUM}:</li>
 * <ul>
 * <li>The application title in the {@link ApplicationBar} is only visible when
 * the {@link MainMenu} is displayed (overlapping the tab headers because there
 * is no space to show both of them at the same time).</li>
 * <li>The {@link ActionMethod}s of the active {@link Tab} are displayed as
 * OverflowMenu after clicking the OverflowMenu icon on the
 * {@link ApplicationBar} (because there is no space to show the
 * {@link ActionMethod}s in the {@link ApplicationBar}).</li>
 * <li>The {@link MainMenu} is displayed as a <a href=
 * "https://material.io/design/components/navigation-drawer.html">navigation
 * drawer</a> (overlapping the content area (tab panels) because there is no
 * space to show both of them at the same time).</li>
 * <li>{@link Grid}s only can have a few columns, depending on the available
 * width</li>
 * <li>{@link FormTab}s could have more {@link PropertyPanel}'s next to each
 * other, depending on the available width.</li>
 * </ul>
 * <li>{@link ReflectDisplayWidth#LARGE}:</li>
 * <ul>
 * <li>The application title is visible in the {@link ApplicationBar} when the
 * {@link MainMenu} is displayed.</li>
 * <li>The {@link ActionMethod}s of the active {@link Tab} are displayed on the
 * {@link ApplicationBar} or in a OverflowMenu after clicking the OverflowMenu
 * icon on the {@link ApplicationBar} (if there is no space to show all the
 * {@link ActionMethod}s in the {@link ApplicationBar}).</li>
 * <li>The {@link MainMenu} is displayed on the left when it is not hidden
 * (shifting the content area (tab panels) to the right so that both are
 * visible)</li>
 * <li>{@link Grid}s only can have multiple columns, depending on the available
 * width</li>
 * <li>{@link FormTab}s could have more {@link PropertyPanel}'s next to each
 * other, depending on the available width</li>
 * </ul>
 * </ul>
 * <h3>Different display heights</h3> The layout and components vary depending
 * on the {@link ReflectDisplayHeight}:
 * <ul>
 * <li>{@link ReflectDisplayHeight#SMALL}:</li>
 * <ul>
 * <li>The {@link ApplicationBar} can scroll out of sight to make more space for
 * the {@link Tab}s</li>
 * </ul>
 * <li>{@link ReflectDisplayHeight#MEDIUM}:</li>
 * <ul>
 * <li>The {@link ApplicationBar} is always visible on top</li>
 * </ul>
 * <li>{@link ReflectDisplayHeight#LARGE}:</li>
 * <ul>
 * <li>The {@link ApplicationBar} is always visible on top and may have more
 * height</li>
 * <li>The {@link ActionMethod}s of the active {@link Tab} can either be
 * displayed on the higher {@link ApplicationBar} or on a ToolBar on the top of
 * the {@link Tab}</li>
 * </ul>
 * </ul>
 * 
 * @author nilsth
 *
 */
public interface ReflectResponsiveDesign extends ReflectDocumentationInterface {

}
