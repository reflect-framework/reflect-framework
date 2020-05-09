package nth.reflect.fw.gui.component;

import nth.reflect.fw.documentation.ReflectApplicationProjects;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.gui.component.table.Table;

/**
 * <p>
 * A {@link ReflectStyleClass} is a class with static methods that graphical
 * user interface components can use to get styling information. The goal is to
 * unify styling across the different {@link ReflectApplicationProjects}.
 * </p>
 *
 * <p>
 * {@link ReflectStyleClass}es are named: &lt;{@link ReflectGuiComponent}
 * name&gt;Style. E.g. the GridStyle for the {@link Table} component
 * </p>
 * 
 * <p>
 * Measurements are in pixels unless otherwise specified
 * </p>
 * 
 * @author nilsth
 *
 */
public interface ReflectStyleClass extends ReflectDocumentationInterface {

}
