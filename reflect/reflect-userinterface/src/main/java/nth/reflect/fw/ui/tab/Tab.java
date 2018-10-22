package nth.reflect.fw.ui.tab;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.ui.style.component.ReflectGuiComponent;
import nth.reflect.fw.ui.tab.form.FormTab;
import nth.reflect.fw.ui.tab.table.TableTab;

/**
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
 */
// TODO describe the concepts above in ReflectDocumentation interfaces and
// change the concepts above to (@link...}
public interface Tab {

	public String getDisplayName();

	public String getDescription();

	public void onSelected();

}
