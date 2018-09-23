package nth.reflect.fw.ui.view.form.propertypanel;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.ui.style.component.ReflectGuiComponent;
import nth.reflect.fw.ui.view.FormView;

/**
 * <p>
 * {@link PropertyValidationMessages} is part of a {@link PropertyPanel} which is part
 * of a {@link FormView}. A {@link PropertyValidationMessages} shows validation
 * violation messages, so that the user is informed on what {@link PropertyField}
 * need to be changed before an {@link ActionMethod} can be executed.
 * <p>
 * 
 * <p>
 * A {@link PropertyLabelStyle} usually extends a multi-line label with red text. The
 * label is hidden or empty when all validation rules are valid. The label shows
 * a human readable text for each validation rule violation (one line per
 * violation).
 * </p>
 * 
 * <p>
 * The {@link #setMessages(List)} and {@link #setVisible(boolean)} are called from the {@link FormView}
 * </p>
 * @author nilsth
 *
 */
public interface PropertyValidationMessages extends ReflectGuiComponent {

	public void setMessages(List<String> messages);

	public void setVisible(boolean visible);
}
