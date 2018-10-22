package nth.reflect.fw.ui.swing.tab.form.proppanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;

import nth.reflect.fw.ui.swing.properygrid.SwingUtil;
import nth.reflect.fw.ui.swing.style.ColorUtil;
import nth.reflect.fw.ui.tab.form.FormTab;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public  class PropertyPanel extends JPanel implements nth.reflect.fw.ui.tab.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationMessages> {
	private static final long serialVersionUID = -1361779111256295050L;
	private FocusListener focusListener;
	private final PropertyValueModel propertyValueModel;
	public static final int HIGH_FIELD_HEIGHT = 200;
	private final PropertyField propertyField;
	private final PropertyLabel propertyLabel;
	private final PropertyValidationMessages propertyValidationMessages;

	public PropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		this.propertyValueModel = propertyValueModel;
		
		setLayout(null); // NOTE no layout manager! Layout is handled by the
							// PropertyGridLayout
		setToolTipText(propertyValueModel.getPropertyInfo().getDescription());
		setBackground(ColorUtil.getLightColor());

		propertyLabel = new PropertyLabel();
		add(propertyLabel);

		this.propertyField = propertyField;
		add((Component)propertyField);//FIXME Get rid of casting to Component!
		
		propertyValidationMessages=new PropertyValidationMessages();
		add(propertyValidationMessages);
		
		// make sure that the propertyField is visible in the scroll pane when
		// it (or one of its children) receives focus
		registerFocusListerner((Component)propertyField);//FIXME Get rid of casting to Component!

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
	public PropertyValidationMessages getPropertyValidationMessages() {
		return propertyValidationMessages;
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



//
//	@Override
//	public void updateVisibility() {
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				boolean visible = propertyValueModel.getPropertyInfo().isEnabled(propertyValueModel.getDomainObject());
//				label.setVisible(visible);
//				valueComponent.setVisible(visible);
//			}
//		});
//	}





	

	
}
