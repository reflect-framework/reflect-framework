package nth.reflect.fw.ui.component.applicationbar;

import nth.reflect.fw.ui.component.ReflectGuiComponent;
import nth.reflect.fw.ui.component.mainwindow.MainWindow;
import nth.reflect.fw.ui.style.ReflectDisplayHeight;

/**
 * TODO PICTURE {@link ApplicationBar}
 * <p>
 * A {@link ApplicationBar} is a {@link ReflectGuiComponent}. It is located at
 * the top over the whole width of the {@link MainWindow}. It provides content
 * and actions related to the current screen. It’s used for branding, screen
 * titles, navigation, and actions.
 * </p>
 * 
 * <h3>Principles</h3>
 * <ul>
 * <li>Persistent: Top app bars appear at the top of each screen in an app, and
 * can disappear upon scroll when the {@link ReflectDisplayHeight#SMALL}.</li>
 * <li>Guiding: Top app bars provide a reliable way to guide users through an
 * app.</li>
 * <li>Consistent: Top app bars have a consistent position and content to
 * increase familiarity.</li>
 * </ul>
 * 
 * <h3>Anatomy</h3>
 * <p>
 * The recommended placement of elements in a top app bar for left-to-right
 * languages is:
 * <ul>
 * <li>Place navigation on the far left</li>
 * <li>Place any titles to the right of navigation</li>
 * <li>Place contextual actions to the right of navigation</li>
 * <li>Place an overflow menu (if used) to the far right</li>
 * </ul>
 * </p>
 * 
 * @author nilsth
 *
 */

public interface ApplicationBar extends ReflectGuiComponent {

}
