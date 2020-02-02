package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyFieldWidth;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

@SuppressWarnings({ "serial", "rawtypes" })
public class ComboBoxField extends JComboBox implements PropertyField {

	private final PropertyValueModel propertyValueModel;

	public ComboBoxField(FormTab formTab, PropertyValueModel propertyValueModel) {
		this.propertyValueModel = propertyValueModel;
		Class<?> valueType = propertyValueModel.getValueType();
		if (valueType.isEnum()) {
			initForEnums(propertyValueModel, valueType);
		} else {
			UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
			ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
			initForDomainObjects(propertyValueModel, reflectionProvider);
		}

		addActionListener(createPropertyValueModelUpdater());
	}

	private ActionListener createPropertyValueModelUpdater() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getSelectedItem());
				}
			}
		};
	}

	@SuppressWarnings({ "unchecked" })
	private void initForDomainObjects(final PropertyValueModel propertyValueModel,
			ReflectionProvider reflectionProvider) {
		Vector<Object> listValues = new Vector<Object>();
		listValues.add(null);
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Object domainObject = propertyValueModel.getDomainObject();
		List<Object> values = propertyInfo.getOptions(domainObject);
		listValues.addAll(values);
		setModel(new DefaultComboBoxModel(listValues));
		setRenderer(createObjectRenderer(reflectionProvider));
	}

	@SuppressWarnings({ "unchecked" })
	private void initForEnums(final PropertyValueModel propertyValueModel, Class<?> valueType) {
		Format format = propertyValueModel.getPropertyInfo().getFormat();

		Vector<Object> listValues = new Vector<Object>();
		listValues.add(null);
		Object[] enumValues = valueType.getEnumConstants();
		for (Object enumValue : enumValues) {
			listValues.add(enumValue);
		}
		setModel(new DefaultComboBoxModel(listValues));
		setRenderer(createEnumRenderer(format));
	}

	private ListCellRenderer createObjectRenderer(final ReflectionProvider reflectionProvider) {
		return new BasicComboBoxRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				String text = "";
				if (value != null) {
					DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(value.getClass());
					text = domainClassInfo.getTitle(value);
				}
				setText(text);
				return this;
			}

		};
	}

	private ListCellRenderer createEnumRenderer(final Format format) {
		return new BasicComboBoxRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				String text = format.format(value);
				setText(text);

				return this;
			}

		};
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		setSelectedItem(propertyValue);
	}

	@Override
	public Optional<Item> getSelectionItem() {
		return Optional.empty();
	}

}
