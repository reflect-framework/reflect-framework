package nth.reflect.fw.swing.tab.form.proppanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JPanel;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyIconButton;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.swing.properygrid.SwingUtil;
import nth.reflect.fw.swing.style.ColorUtil;

public class PropertyPanel extends JPanel implements
		nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationLabel> {
	private static final long serialVersionUID = -1361779111256295050L;
	private FocusListener focusListener;
	private final PropertyValueModel propertyValueModel;
	public static final int HIGH_FIELD_HEIGHT = 200;
	private final PropertyField propertyField;
	private final PropertyLabel propertyLabel;
	private final PropertyValidationLabel propertyValidationLabel;

	public PropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		this.propertyValueModel = propertyValueModel;

		setLayout(null); // NOTE no layout manager! Layout is handled by the
							// PropertyGridLayout
		setToolTipText(propertyValueModel.getPropertyInfo().getDescription().getTranslation());
		setBackground(ColorUtil.getLightColor());

		propertyLabel = new PropertyLabel();
		add(propertyLabel);

		this.propertyField = propertyField;
		add((Component) propertyField);// FIXME Get rid of casting to Component!

		propertyValidationLabel = new PropertyValidationLabel();
		add(propertyValidationLabel);

		// make sure that the propertyField is visible in the scroll pane when
		// it (or one of its children) receives focus
		registerFocusListerner((Component) propertyField);// FIXME Get rid of
															// casting to
															// Component!

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

	@Override
	public PropertyLabel getPropertyLabel() {

		return propertyLabel;
	}

	@Override
	public PropertyField getPropertyField() {
		return propertyField;
	}

	@Override
	public PropertyValidationLabel getPropertyValidationLabel() {
		return propertyValidationLabel;
	}

	@Override
	public PropertyValueModel getPropertyValueModel() {
		return propertyValueModel;
	}

	@Override
	public void setVisible(Boolean visible) {
		super.setVisible(visible);

	}

	@Override
	public void setDescription(String description) {
		setToolTipText(description);
	}

	@Override
	public void setEnabled(Boolean enabled) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PropertyIconButton> getPropertyIconButtons() {
		// TODO Auto-generated method stub
		return null;
	}

	//
	// @Override
	// public void updateVisibility() {
	// SwingUtilities.invokeLater(new Runnable() {
	//
	// @Override
	// public void run() {
	// boolean visible =
	// propertyValueModel.getPropertyInfo().isEnabled(propertyValueModel.getDomainObject());
	// label.setVisible(visible);
	// valueComponent.setVisible(visible);
	// }
	// });
	// }

}
