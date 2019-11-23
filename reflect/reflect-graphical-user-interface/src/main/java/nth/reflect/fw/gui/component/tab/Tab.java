package nth.reflect.fw.gui.component.tab;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.grid.GridTab;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE Tabs
 * <p>
 * A {@link Tab} is a {@link ReflectGuiComponent} on which some kind of
 * information is displayed. This is often a {@link FormTab} or a
 * {@link GridTab} that show the results or parameter of a {@link ActionMethod}.
 * A graphical user interface of a {@link ReflectApplication} can have multiple
 * tabs at the same time (see
 * <a href="https://en.wikipedia.org/wiki/Tab_(GUI)">tabbed document interface
 * (TDI)</a>). Only one {@link Tab} wil be displayed at a time. Each {@link Tab}
 * will have a TabHeader that is displayed in the TabHeaderBar. You can select
 * another {@link Tab} by clicking on one of the other TabHeaders or the
 * TabSelectionButton
 * </p>
 * 
 * <p>
 * A {@link GraphicalUserInterfaceApplication} can contain multiple types of
 * tabs.
 */

// TODO add Javadoc to explain how to implement a CustomTab
public interface Tab {

	public String getDisplayName();

	public String getDescription();

	public void onRefresh();

}
