package nth.reflect.fw.javafx.control.tab.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.propertypanel.FormTabStyle;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.fw.gui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueChangeListener;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldFactoryNotFoundException;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.UserinterfaceControllerForJavaFX;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyValidationLabel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;

/**
 * {@link PropertyGrid} contains {@link PropertyPanel}s. It shows scroll bars if
 * not all {@link PropertyPanel}s fit in the viewport.
 * 
 * @author nilsth
 *
 */
public class PropertyGrid extends ScrollPane implements PropertyValueChangeListener {

	// TODO see:
	// https://material.io/guidelines/components/text-fields.html#text-fields-layout

	private static final String CONTENT = "content";
	private final List<PropertyPanel> propertyPanels;
	private final ValidationProvider validationProvider;
	private final BufferedDomainValueModel domainValueModel;
	private final Map<String, PropertyValidationLabel> propertyValidationLabels;

	public PropertyGrid(ValidationProvider validationProvider, FormTab formTab) {
		super();
		this.validationProvider = validationProvider;
		domainValueModel = formTab.getDomainValueModel();

		getStyleClass().add(StyleSheet.createStyleClassName(PropertyGrid.class));
		setFitToWidth(true);

		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();

		List<PropertyInfo> propertyInfos = getPropertyInfos(formTab, userInterfaceContainer);

		propertyPanels = createPropertyPanels(formTab, propertyInfos);

		propertyValidationLabels = getPropertyValidationLabels(propertyPanels);

		VBox content = createContent(formTab);
		setContent(content);

		onRefresh();
	}

	private HBox createButtonBar(FormTab formTab) {
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(FormTabStyle.SPACING);
		buttonBox.getChildren().add(new FormOkButton(formTab));
		buttonBox.getChildren().add(new FormCancelButton(formTab));
		return buttonBox;
	}

	private Map<String, PropertyValidationLabel> getPropertyValidationLabels(List<PropertyPanel> propertyPanels) {
		Map<String, PropertyValidationLabel> propertyValidationLabels = new HashMap<>();
		for (PropertyPanel propertyPanel : propertyPanels) {
			String propertyName = propertyPanel.getPropertyValueModel().getPropertyInfo().getSimpleName();
			PropertyValidationLabel propertyValidationLabel = propertyPanel.getPropertyValidationLabel();
			propertyValidationLabels.put(propertyName, propertyValidationLabel);
		}
		return propertyValidationLabels;
	}

	private List<PropertyInfo> getPropertyInfos(FormTab formTab, UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		Object domainObject = formTab.getDomainObject();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainObject.getClass());
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		return propertyInfos;
	}

	private VBox createContent(FormTab formTab) {
		VBox content = new VBox();
		content.getStyleClass().add(StyleSheet.createStyleClassName(PropertyGrid.class, CONTENT));
		for (PropertyPanel propertyPanel : propertyPanels) {
			content.getChildren().add(propertyPanel);
		}
		if (formTab.getFormMode() == FormMode.EDIT) {
			HBox buttonBar = createButtonBar(formTab);
			content.getChildren().add(buttonBar);
		}
		return content;
	}

	public void onRefresh() {
		for (PropertyPanel propertyPanel : propertyPanels) {
			propertyPanel.onRefresh();
		}
		validateDomainObject();
	}

	/**
	 * Creating the children in order of display order (for the proper focus order)
	 * 
	 * @param reflectionProvider
	 * @param formTab
	 * @param propertyInfos
	 * @return
	 */
	private List<PropertyPanel> createPropertyPanels(FormTab formTab, List<PropertyInfo> propertyInfos) {
		List<PropertyPanel> propertyPanels = new ArrayList<>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			try {
				PropertyPanel propertyPanel = createPropertyPanel(formTab, domainValueModel, propertyInfo);
				propertyPanels.add(propertyPanel);
			} catch (PropertyFieldFactoryNotFoundException e) {
			}

		}
		return propertyPanels;
	}

	private PropertyPanel createPropertyPanel(FormTab formTab, BufferedDomainValueModel domainValueModel,
			PropertyInfo propertyInfo) {
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		UserinterfaceControllerForJavaFX userinterfaceController = userInterfaceContainer
				.get(UserinterfaceControllerForJavaFX.class);
		PropertyPanelFactory<PropertyPanel> propertyPanelFactory = userinterfaceController.getPropertyPanelFactory();
		PropertyValueModel propertyValueModel = new PropertyValueModel(domainValueModel, propertyInfo);
		propertyValueModel.addListener(this);
		PropertyPanel propertyPanel = propertyPanelFactory.createPropertyPanel(formTab, propertyValueModel);
		return propertyPanel;
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet
				.addStyleGroup(StyleSelector.createFor(PropertyGrid.class))
				.getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND())
				.setPadding(FormTabStyle.PADDING);
		styleSheet
				.addStyleGroup(
						StyleSelector.createFor(PropertyGrid.class).append(StyleSelector.createFor("> .viewport")))
				.getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND());
		styleSheet
				.addStyleGroup(StyleSelector.createFor(PropertyGrid.class, CONTENT))
				.getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND())
				.setSpacing(FormTabStyle.SPACING);
	}

	@Override
	public void onPropertyValueChange() {
		// updateAllPropertyPanels();
		validateDomainObject();
	}

	private void validateDomainObject() {
		Set<ConstraintViolation<Object>> constraints = validationProvider.validate(domainValueModel.getValue());
		for (PropertyValidationLabel validationLabel : propertyValidationLabels.values()) {
			validationLabel.clearMessage();
		}

		for (ConstraintViolation<Object> constraintViolation : constraints) {
			String propertyName = constraintViolation.getPropertyPath().toString();
			PropertyValidationLabel validationLabel = propertyValidationLabels.get(propertyName);
			validationLabel.addMessage(constraintViolation.getMessage());
		}
	}

}
