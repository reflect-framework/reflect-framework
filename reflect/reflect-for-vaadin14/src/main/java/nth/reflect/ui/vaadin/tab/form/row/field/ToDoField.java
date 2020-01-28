package nth.reflect.ui.vaadin.tab.form.row.field;

import java.util.Optional;

import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.item.Item;
/**
 * Place holder for {@link PropertyField}s that are not implemented yet.
 * @author nilsth
 *
 */
public class ToDoField extends Div implements PropertyField  {

	private static final long serialVersionUID = 713404265998956165L;

	public ToDoField(PropertyValueModel propertyValueModel) {
		String canonicalName = propertyValueModel.getPropertyInfo().getTypeInfo().getType().getCanonicalName();
		setText(canonicalName);
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setEnabled(boolean enabled) {
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
