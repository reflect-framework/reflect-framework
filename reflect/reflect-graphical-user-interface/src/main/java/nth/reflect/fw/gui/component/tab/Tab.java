package nth.reflect.fw.gui.component.tab;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.table.TableTab;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE Tabs
 * <p>
 * A {@link Tab} is a {@link ReflectGuiComponent} on which some kind of
 * information is displayed. This is often a {@link FormTab} or a
 * {@link TableTab} that show the results or parameter of a
 * {@link ActionMethod}. A graphical user interface of a
 * {@link ReflectApplication} can have multiple tabs at the same time (see
 * <a href="https://en.wikipedia.org/wiki/Tab_(GUI)">tabbed document interface
 * (TDI)</a>). Only one {@link Tab} wil be displayed at a time. Each {@link Tab}
 * will have a TabHeader that is displayed in the TabHeaderBar. You can select
 * another {@link Tab} by clicking on one of the other TabHeaders or the
 * TabSelectionButton
 * </p>
 * 
 * <p>
 * A {@link GraphicalUserInterfaceApplication} can contain multiple types of
 * tabs:
 * 
 * <h3>Table Tab</h3>{@insert TableTab}
 * 
 * <h3>Form Tab</h3>{@insert FormTab}
 * 
 * <h3>Customized tabs</h3>
 * <p>
 * The {@link TableTab} and {@link FormTab} are normally used to select a
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
 * </p>
 */

// TODO add Javadoc to explain how to implement a CustomTab
public interface Tab {

	public String getDisplayName();

	public String getDescription();

	public void onSelected();

}
