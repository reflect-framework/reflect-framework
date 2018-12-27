package nth.reflect.fw.javafx.control.tab.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyValidationLabel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
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
 * Property pane to display or edit the {@link DomainObjectProperty}s,
 * including:<br>
 * <ul>
 * <li>a label that represents the {@link DomainObjectProperty} name (above
 * field for narrow window, left of field for wide window)</li>
 * <li>a field to display or edit the {@link DomainObjectProperty} value</li>
 * <li>a button to open a context menu with
 * {@link DomainObjectPropertyActionMethod}s</li>
 * </ul>
 * For styling see <a href=
 * "https://material.io/guidelines/components/text-fields.html#text-fields-layout">Google
 * Material Design Guidelines</a>
 * 
 * @author nilsth
 *
 */
public class PropertiesPanel extends VBox implements PropertyValueChangeListener {

	// TODO see:
	// https://material.io/guidelines/components/text-fields.html#text-fields-layout

	private static final int PADDING = 8;
	public static final double HORIZONTAL_GAP = 16;
	private final List<PropertyPanel> propertyPanels;
	private final ValidationProvider validationProvider;
	private final BufferedDomainValueModel domainValueModel;
	private final Map<String, PropertyValidationLabel> propertyValidationLabels;

	public PropertiesPanel(ValidationProvider validationProvider, FormTab formTab) {
		super();
		this.validationProvider = validationProvider;
		domainValueModel = formTab.getDomainValueModel();

		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertiesPanel.class));
		// initCellsFirstStyle();
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();

		List<PropertyInfo> propertyInfos = getPropertyInfos(formTab, userInterfaceContainer);

		propertyPanels = createPropertyPanels(formTab, propertyInfos);

		propertyValidationLabels = getPropertyValidationLabels(propertyPanels);

		addAllPropertyPanels();

		addOkAndCancelButtons(formTab);

		updateAllPropertyPanels();

		setPadding(new Insets(PADDING));
	}

	private void addOkAndCancelButtons(FormTab formTab) {
		if (formTab.getFormMode() == FormMode.EDIT_MODE) {
			HBox buttonBox = new HBox();
			buttonBox.getChildren().add(new FormOkButton(formTab));
			buttonBox.getChildren().add(new FormCancelButton(formTab));
			getChildren().add(buttonBox);
		}
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

	private void addAllPropertyPanels() {
		for (PropertyPanel propertyPanel : propertyPanels) {
			getChildren().add(propertyPanel);
		}
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

	// private Label createLabel(PropertyInfo propertyInfo) {
	// Label label = new Label(propertyInfo.getDisplayName());
	// Font font =
	// RfxFontFactory.create(MaterialFont.getRobotoRegular(LABEL_FONT_SIZE));
	// label.setFont(font);
	// label.setPrefHeight(Label.USE_COMPUTED_SIZE);
	// label.setWrapText(true);
	// label.setStyle(new
	// RfxStyleProperties().setTextFill(ReflectColorName.CONTENT.FOREGROUND2()).toString());
	// getChildren().add(label);
	// return label;
	// }

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

	// /**
	// * @return 0: preferred totalHeight already set in {@link RfxWindow}
	// */
	// @Override
	// protected double computePrefHeight(double width) {
	// layoutChildren();
	// System.out.println("h" + totalHeight);
	// return totalHeight;
	// }

	// @Override
	// protected void layoutChildren() {
	// if (windowExtraWideBinding.get()) {
	// layoutChildrenForWideWindow();
	// } else {
	// layoutChildrenForNarrowWindow();
	// }
	//
	// }
	//
	// private void layoutChildrenForNarrowWindow() {
	// final Insets insets = getInsets();
	// double width = getWidth();
	//
	// final double insideX = insets.getLeft();
	// final double insideY = insets.getTop();
	// final double insideWidth = width - insideX - insets.getRight();
	//
	// double x = insideX;
	// double y = insideY;
	//
	// for (PropertyPanel propertyPanel : propertyPanels) {
	// // TODO invisible properties
	// // TODO disabled properties
	// PropertyLabel label = propertyPanel.getPropertyLabel();
	// label.setAlignment(Pos.TOP_LEFT);
	// double labelWidth = insideWidth;
	// double labelHeight = label.prefHeight(labelWidth);
	// label.resize(labelWidth, labelHeight);
	// x = insideX;
	// positionInArea(label, x, y, labelWidth, labelHeight,
	// 0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP,
	// isSnapToPixel());
	// y += labelHeight;
	//
	//// RfxContentButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
	//// double fieldMenuButtonWidth = 0;
	//// double fieldMenuButtonHeight = 0;
	//// if (fieldMenuButton != null) {
	//// fieldMenuButtonWidth = fieldMenuButton.prefWidth(-1);
	//// fieldMenuButtonHeight = fieldMenuButton.prefHeight(-1);
	//// }
	//
	// Node field = (Node) propertyPanel.getPropertyField();
	// double fieldWidth = insideWidth; //- fieldMenuButtonWidth;
	// double fieldHeight = field.prefHeight(fieldWidth);
	// field.resize(fieldWidth, fieldHeight);
	// positionInArea(field, x, y, fieldWidth, fieldHeight,
	// 0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP,
	// isSnapToPixel());
	//
	//// if (fieldMenuButton != null) {
	//// fieldMenuButton.resize(fieldMenuButtonWidth, fieldMenuButtonHeight);
	//// x = insideX + VERTICAL_GAP + fieldWidth;
	//// positionInArea(fieldMenuButton, x, y, fieldMenuButtonWidth,
	// fieldMenuButtonHeight,
	//// 0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP,
	// isSnapToPixel());
	//// }
	//
	// y += fieldHeight;// Math.max(fieldHeight, fieldMenuButtonHeight);
	// y += HORIZONTAL_GAP;
	//
	// }
	// totalHeight = y;
	// totalWidtht = width;
	// }
	//
	// private void layoutChildrenForWideWindow() {
	// final Insets insets = getInsets();
	// double width = getWidth();
	//
	// final double insideX = insets.getLeft();
	// final double insideY = insets.getTop();
	// final double insideWidth = width - insideX - insets.getRight();
	//
	// double x = insideX;
	// double y = insideY;
	//
	// double labelWidth = computeLabelWidthForWideWindow();
	//
	// for (PropertyPanel propertyPanel : propertyPanels) {
	// // TODO invisible properties
	// // TODO disabled properties
	// Label label = propertyPanel.getPropertyLabel();
	// label.setAlignment(Pos.TOP_RIGHT);
	// double labelHeight = label.prefHeight(labelWidth);
	// label.resize(labelWidth, labelHeight);
	// x = insideX;
	// positionInArea(label, x, y + PADDING, labelWidth, labelHeight,
	// 0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP,
	// isSnapToPixel());
	//
	//// RfxContentButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
	//// double fieldMenuButtonWidth = 0;
	//// double fieldMenuButtonHeight = 0;
	//// if (fieldMenuButton != null) {
	//// fieldMenuButtonWidth = fieldMenuButton.prefWidth(-1);
	//// fieldMenuButtonHeight = fieldMenuButton.prefHeight(-1);
	//// }
	//
	// Node field = (Node) propertyPanel.getPropertyField();
	// //VERTICAL_GAP - fieldMenuButtonWidth
	// double fieldWidth = Math.min(insideWidth - labelWidth - VERTICAL_GAP ,
	// FIELD_WIDTH_MAX_FOR_WIDE_WINDOW);
	// double fieldHeight = field.prefHeight(fieldWidth);
	// field.resize(fieldWidth, fieldHeight);
	// x = insideX + labelWidth + VERTICAL_GAP;
	// positionInArea(field, x, y, fieldWidth, fieldHeight,
	// 0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP,
	// isSnapToPixel());
	//
	//// if (fieldMenuButton != null) {
	//// fieldMenuButton.resize(fieldMenuButtonWidth, fieldMenuButtonHeight);
	//// x = insideX + labelWidth + VERTICAL_GAP + fieldWidth;
	//// positionInArea(fieldMenuButton, x, y, fieldMenuButtonWidth,
	// fieldMenuButtonHeight,
	//// 0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP,
	// isSnapToPixel());
	//// }
	//
	// y += Math.max(labelHeight, fieldHeight);
	// y += HORIZONTAL_GAP;
	// }
	// totalHeight = y;
	// totalWidtht = width;
	// }
	//
	// private double computeLabelWidthForWideWindow() {
	// double maxLabelWidth = 0;
	// for (PropertyPanel propertyPanel : propertyPanels) {
	// // TODO ignore invisible properties
	// Label label = propertyPanel.getPropertyLabel();
	// double labelWidth = label.prefWidth(-1);
	// maxLabelWidth = Math.max(maxLabelWidth, labelWidth);
	// }
	// return Math.min(maxLabelWidth, LABEL_MAX_WIDTH_FOR_WIDE_SCREEN);
	// }

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertiesPanel.class)).getProperties()
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
