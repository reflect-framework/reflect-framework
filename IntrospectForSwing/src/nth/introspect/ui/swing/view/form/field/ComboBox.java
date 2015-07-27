package nth.introspect.ui.swing.view.form.field;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import nth.introspect.generic.util.TitleUtil;
import nth.introspect.generic.valuemodel.ReadWriteValueModel;
import nth.introspect.layer1userinterface.controller.Refreshable;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.valuemodel.PropertyValueModel;

@SuppressWarnings("serial")
public class ComboBox extends JComboBox implements Refreshable {

	private ReadWriteValueModel readWriteValueModel;

	public ComboBox(final PropertyValueModel propertyValueModel,
			ReflectionProvider reflectionProvider, PathProvider pathProvider,
			LanguageProvider languageProvider) {
		this.readWriteValueModel = propertyValueModel;

		Class<?> valueType = propertyValueModel.getValueType();
		if (valueType.isEnum()) {
			initForEnums(propertyValueModel, valueType);
		} else {
			initForDomainObjects(propertyValueModel, reflectionProvider);
		}

		refresh();

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getSelectedItem());
				}
			}
		});
	}

	private void initForDomainObjects(
			final PropertyValueModel propertyValueModel,
			ReflectionProvider reflectionProvider) {
		Vector<Object> listValues = new Vector<Object>();
		listValues.add(null);
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Object domainObject = propertyValueModel.getDomainObject();
		List<Object> values = propertyInfo.getValues(domainObject);
		listValues.addAll(values);
		setModel(new DefaultComboBoxModel(listValues));
		setRenderer(createObjectRenderer(reflectionProvider));
	}

	private void initForEnums(final PropertyValueModel propertyValueModel,
			Class<?> valueType) {
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

	private ListCellRenderer createObjectRenderer(
			final ReflectionProvider reflectionProvider) {
		return new BasicComboBoxRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				String text = "";
				if (value != null) {
					ClassInfo classInfo = reflectionProvider.getClassInfo(value
							.getClass());
					text = classInfo.getTitle(value);
				}
				setText(text);
				return this;
			}

		};
	}

	private ListCellRenderer createEnumRenderer(final Format format) {
		return new BasicComboBoxRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				String text = format.format(value);
				setText(text);

				return this;
			}

		};
	}

	@Override
	public void refresh() {
		setSelectedItem(readWriteValueModel.getValue());
		setEnabled(readWriteValueModel.canSetValue());
	}
}
