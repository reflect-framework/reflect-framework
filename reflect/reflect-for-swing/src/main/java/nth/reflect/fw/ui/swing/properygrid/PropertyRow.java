package nth.reflect.fw.ui.swing.properygrid;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.swing.style.ColorUtil;
import nth.reflect.fw.ui.swing.view.form.field.FieldFactory;
import nth.reflect.fw.ui.swing.view.form.field.ManyToOneOrManyField;
import nth.reflect.fw.ui.swing.view.form.field.TextAreaField;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormMode;
import nth.reflect.fw.ui.view.FormView;

public class PropertyRow extends JPanel {
	private static final long serialVersionUID = -1361779111256295050L;
	private final FieldWidth fieldWidth;
	private WrapingLabel label;
	private final Component field;
	private JPanel fieldAndValidatorPanel;
	private Component validator;
	private FocusListener focusListener;
	private PropertyValueModel propertyValueModel;
	private BufferedDomainValueModel domainValueModel;
	private final PropertyInfo propertyInfo;
	public static final int HIGH_FIELD_HEIGHT = 200;

	public enum FieldWidth {
		half, full
	};



	public PropertyRow(FormView formView, ReflectionProvider reflectionProvider, BufferedDomainValueModel domainValueModel, PropertyInfo propertyInfo, FormMode formMode) {
		this.domainValueModel = domainValueModel;
		this.propertyInfo = propertyInfo;
		// create value model to bind domainObject and SWING field
		
		propertyValueModel = new PropertyValueModel(domainValueModel, propertyInfo, formMode);
		
		this.field = FieldFactory.create(formView, reflectionProvider, propertyValueModel);// To be created by a factory class (implementation of ReflectFormFieldFactory<Field
		if (field instanceof ManyToOneOrManyField || field instanceof TextAreaField) {//TODO get field width from a Field interface?
			this.fieldWidth = FieldWidth.full;
		} else {
			this.fieldWidth = FieldWidth.half;
		}
		this.validator = null;//TODO get field validation result component from a Field interface?
		setLayout(null); // NOTE no layout manager! Layout is handled by the PropertyGridLayout
		setToolTipText(propertyInfo.getDescription());

		setBackground(ColorUtil.getLightColor());
		label = new WrapingLabel(propertyInfo.getDisplayName());
		label.setToolTipText(propertyInfo.getDescription());
		label.setForeground(ColorUtil.getDark());
		// label.setDisplayedMnemonic() is set by propertyGrid.addPropertyRow
		label.setLabelFor(field);
		add(label);

		// make sure that the field is visible in the scroll pane when it (or one of its children) receives focus
		registerFocusListerner(field);

		fieldAndValidatorPanel = new JPanel(null);
		fieldAndValidatorPanel.setBackground(ColorUtil.getLightColor());
		fieldAndValidatorPanel.add(field);
		fieldAndValidatorPanel.add(getValidator());
		add(fieldAndValidatorPanel);
	}

	private void registerFocusListerner(Component component) {
		component.addFocusListener(getFocusListerner());
		if (component instanceof Container) {
			Container container = (Container) component;
			for (Component child : container.getComponents()) {
				registerFocusListerner(child);// Recursive
			}
		}
	}

	private FocusListener getFocusListerner() {
		if (focusListener == null) {
			focusListener = new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
				}

				@Override
				public void focusGained(FocusEvent e) {
					SwingUtil.scrollToComponent((Component) e.getSource());
				}
			};
		}
		return focusListener;
	}

	public FieldWidth getFieldWidth() {
		return fieldWidth;
	}

	public JLabel getLabel() {
		return label;
	}

	public Component getField() {
		return field;
	}

	public JPanel getFieldAndValidatorPanel() {
		return fieldAndValidatorPanel;
	}

	public Component getValidator() {
		if (validator == null) {
			validator = new JLabel();
		}
		return validator;
	}

}
