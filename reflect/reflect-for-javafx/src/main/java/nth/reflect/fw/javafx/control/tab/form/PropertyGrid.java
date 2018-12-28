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
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyValidationLabel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.style.component.FormTabStyle;
import nth.reflect.fw.ui.tab.form.FormMode;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.ui.valuemodel.PropertyValueChangeListener;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

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

		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertyGrid.class));
		setFitToWidth(true);

		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();

		List<PropertyInfo> propertyInfos = getPropertyInfos(formTab, userInterfaceContainer);

		propertyPanels = createPropertyPanels(formTab, propertyInfos);

		propertyValidationLabels = getPropertyValidationLabels(propertyPanels);

		VBox content = createContent(formTab);
		setContent(content);

		updateAllPropertyPanels();
	}

	private HBox createButtonBar(FormTab formTab) {
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(FormTabStyle.getSpacing());
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
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainObject.getClass());
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		return propertyInfos;
	}

	private VBox createContent(FormTab formTab) {
		VBox content = new VBox();
		content.getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertyGrid.class, CONTENT));
		for (PropertyPanel propertyPanel : propertyPanels) {
			content.getChildren().add(propertyPanel);
		}
		if (formTab.getFormMode() == FormMode.EDIT_MODE) {
			HBox buttonBar = createButtonBar(formTab);
			content.getChildren().add(buttonBar);
		}
		return content;
	}

	public void updateAllPropertyPanels() {
		for (PropertyPanel propertyPanel : propertyPanels) {
			propertyPanel.updateFromPropertyValueModel();
		}
		validateDomainObject();
	}

	/**
	 * Creating the children in order of display order (for the proper focus
	 * order)
	 * 
	 * @param reflectionProvider
	 * @param formTab
	 * @param propertyInfos
	 * @return
	 */
	private List<PropertyPanel> createPropertyPanels(FormTab formTab, List<PropertyInfo> propertyInfos) {
		List<PropertyPanel> propertyPanels = new ArrayList<>();
		FormMode formMode = formTab.getFormMode();
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyPanel propertyPanel = createPropertyPanel(formTab, domainValueModel, formMode, propertyInfo);
			propertyPanels.add(propertyPanel);
		}
		return propertyPanels;
	}

	private PropertyPanel createPropertyPanel(FormTab formTab, BufferedDomainValueModel domainValueModel,
			FormMode formMode, PropertyInfo propertyInfo) {
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		RfxUserinterfaceController userinterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		PropertyPanelFactory<PropertyPanel> propertyPanelFactory = userinterfaceController.getPropertyPanelFactory();
		PropertyValueModel propertyValueModel = new PropertyValueModel(domainValueModel, propertyInfo, formMode);
		propertyValueModel.addListener(this);
		PropertyPanel propertyPanel = propertyPanelFactory.createPropertyPanel(formTab, propertyValueModel);
		return propertyPanel;
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertyGrid.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND()).setPadding(FormTabStyle.getPadding());
		styleSheet
				.addStyleGroup(RfxStyleSelector.createFor(PropertyGrid.class)
						.append(RfxStyleSelector.createFor("> .viewport")))
				.getProperties().setBackground(ReflectColorName.CONTENT.BACKGROUND());
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertyGrid.class, CONTENT)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND()).setSpacing(FormTabStyle.getSpacing());
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
