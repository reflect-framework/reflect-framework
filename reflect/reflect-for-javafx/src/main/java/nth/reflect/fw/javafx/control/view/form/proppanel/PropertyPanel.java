package nth.reflect.fw.javafx.control.view.form.proppanel;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.layout.BorderPane;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.view.form.FormView;
import nth.reflect.fw.javafx.control.view.form.PropertiesPanel;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.component.PropertyPanelStyle;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

public class PropertyPanel extends BorderPane implements
		nth.reflect.fw.ui.view.form.propertypanel.PropertyPanel<PropertyLabel, PropertyField, PropertyValidationMessages> {

	private final PropertyValidationMessages propertyValidationMessages;
	private final PropertyValueModel propertyValueModel;
	private final FormView formView;
	private final PropertyLabelAndFieldPanel labelAndFieldPanel;

	public PropertyPanel(FormView formView, PropertyValueModel propertyValueModel, PropertyField propertyField) {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertyPanel.class));

		labelAndFieldPanel=new PropertyLabelAndFieldPanel(formView, propertyValueModel, propertyField);
		setCenter(labelAndFieldPanel);
		
		this.formView = (FormView) formView;
		this.propertyValueModel = propertyValueModel;

		propertyValidationMessages = new PropertyValidationMessages();
		setBottom(propertyValidationMessages);
		
		setWidth(propertyField.getPropertyFieldWidth());
	}

	private void setWidth(PropertyFieldWidth propertyFieldWidth) {
		switch (propertyFieldWidth) {
		case FULL:
			DoubleBinding fullWidth = formView.widthProperty().subtract(2*PropertiesPanel.HORIZONTAL_GAP);
			prefWidthProperty().bind(fullWidth);
			break;
		case SMALL:
		default:
			setMaxWidth(PropertyPanelStyle.getMaxSmallWidth());
			setMinWidth(PropertyPanelStyle.getMinSmallWidth());
			break;
		}
		
	}

	@Override
	public PropertyLabel getPropertyLabel() {
		return labelAndFieldPanel.getPropertyLabel();
	}

	@Override
	public PropertyField getPropertyField() {
		return labelAndFieldPanel.getPropertyField();
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
		// Does nothing: Borderpanel does not have a setToolTip method
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertyPanel.class)).getProperties()
		.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND());
	}

}
