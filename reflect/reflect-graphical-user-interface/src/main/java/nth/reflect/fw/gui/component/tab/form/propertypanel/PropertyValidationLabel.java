package nth.reflect.fw.gui.component.tab.form.propertypanel;

import java.util.List;

import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link PropertyValidationLabel} is part of a {@link PropertyPanel} which is
 * part of a {@link FormTab}. A {@link PropertyValidationLabel} shows validation
 * violation messages, so that the user is informed on what
 * {@link PropertyField} need to be changed before an {@link ActionMethod} can
 * be executed.
 * <p>
 * 
 * <p>
 * A {@link PropertyLabelStyle} usually extends a multi-line label with red
 * text. The label is hidden or empty when all validation rules are valid. The
 * label shows a human readable text for each validation rule violation (one
 * line per violation).
 * </p>
 * 
 * <p>
 * The {@link #setMessages(List)} and {@link #setVisible(boolean)} are called
 * from the {@link FormTab}
 * </p>
 * 
 * @author nilsth
 *
 */
public interface PropertyValidationLabel extends ReflectGuiComponent {

	public void addMessage(String message);

	public void clearMessage();

	public void setVisible(boolean visible);
}
