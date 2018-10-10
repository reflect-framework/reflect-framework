package nth.reflect.fw.javafx.control.view.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.control.button.RfxContentButton;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.view.form.proppanel.PropertyPanel;
import nth.reflect.fw.javafx.control.view.form.proppanel.PropertyValidationMessages;
import nth.reflect.fw.javafx.control.window.RfxWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.component.FormViewStyle;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormMode;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyPanelFactory;

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
public class PropertiesPanel extends VBox {

	// TODO see:
	// https://material.io/guidelines/components/text-fields.html#text-fields-layout

	private static final int PADDING = 8;
	public static final double HORIZONTAL_GAP = 16;
	private static final double VERTICAL_GAP = 8;
	private static final double LABEL_MAX_WIDTH_FOR_WIDE_SCREEN = 200;
	private static final double FIELD_WIDTH_MAX_FOR_WIDE_WINDOW = RfxWindow.WINDOW_FAIRLY_WIDE_BINDING;
	private static final int LABEL_FONT_SIZE = 12;
	private Map<PropertyInfo, Label> labels;
	private Map<PropertyInfo, Region> fields;
	private Map<PropertyInfo, RfxContentButton> fieldMenuButtons;
	private final BooleanBinding windowExtraWideBinding;
	private double totalHeight;
	private double totalWidtht;
	private final List<PropertyPanel> propertyPanels;

	public PropertiesPanel(FormView formView) {
		super();

		getStyleClass().add(RfxStyleSheet.createStyleClassName(PropertiesPanel.class));
		// initCellsFirstStyle();
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
		
		List<PropertyInfo> propertyInfos = getPropertyInfos(formView, userInterfaceContainer);

		RfxWindow rfxWindow = userInterfaceContainer.get(RfxWindow.class);
		windowExtraWideBinding = rfxWindow.getExtraWideBinding();

		propertyPanels=createPropertyPanels(formView, propertyInfos);
		
		addAllPropertyPanels();
		
		updateAllPropertyPanels();

		setPadding(new Insets(PADDING));
	}

	private List<PropertyInfo> getPropertyInfos(FormView formView, UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		Object domainObject = formView.getDomainObject();
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
			
			PropertyValidationMessages propertyValidationMessages = propertyPanel.getPropertyValidationMessages();
			List<String> validationMessages = getRandomValidationMessages();
			if (validationMessages.size()==0) {
				propertyValidationMessages.setManaged(false);
			} else {
				propertyValidationMessages.setManaged(true);
				propertyValidationMessages.setMessages(validationMessages);
			}
		}
	}

	/**
	 * FIXME this is test code. Replace with real validation code
	 * @return
	 */
	private List<String> getRandomValidationMessages() {
		switch (ThreadLocalRandom.current().nextInt(4)) {
		case 0:
			return Arrays.asList("Required");
		case 1:
			return Arrays.asList("Required","May not be empty");
		default:
			return new ArrayList<>();
		}
	}

	/**
	 * Creating the children in order of display order (for the proper focus
	 * order)
	 * 
	 * @param reflectionProvider
	 * @param formView
	 * @param propertyInfos 
	 * @return 
	 */
	private List<PropertyPanel> createPropertyPanels(FormView formView, List<PropertyInfo> propertyInfos) {
		List<PropertyPanel> propertyPanels=new ArrayList<>();
		BufferedDomainValueModel domainValueModel = formView.getDomainValueModel();
		FormMode formMode = formView.getFormMode();
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyPanel propertyPanel = createPropertyPanel(formView, domainValueModel, formMode, propertyInfo);
			propertyPanels.add(propertyPanel);
		}
		return propertyPanels;
	}

//	private Label createLabel(PropertyInfo propertyInfo) {
//		Label label = new Label(propertyInfo.getDisplayName());
//		Font font = RfxFontFactory.create(MaterialFont.getRobotoRegular(LABEL_FONT_SIZE));
//		label.setFont(font);
//		label.setPrefHeight(Label.USE_COMPUTED_SIZE);
//		label.setWrapText(true);
//		label.setStyle(new RfxStyleProperties().setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND2()).toString());
//		getChildren().add(label);
//		return label;
//	}

	private PropertyPanel createPropertyPanel(FormView formView, BufferedDomainValueModel domainValueModel, FormMode formMode,
			PropertyInfo propertyInfo) {
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
		RfxUserinterfaceController userinterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		PropertyPanelFactory<PropertyPanel> propertyPanelFactory = userinterfaceController.getPropertyPanelFactory();
		PropertyValueModel propertyValueModel = new PropertyValueModel(domainValueModel, propertyInfo, formMode);
		PropertyPanel propertyPanel = propertyPanelFactory.createPropertyPanel(formView, propertyValueModel);
		return propertyPanel;
	}

//	/**
//	 * @return 0: preferred totalHeight already set in {@link RfxWindow}
//	 */
//	@Override
//	protected double computePrefHeight(double width) {
//		layoutChildren();
//		System.out.println("h" + totalHeight);
//		return totalHeight;
//	}

//	@Override
//	protected void layoutChildren() {
//		if (windowExtraWideBinding.get()) {
//			layoutChildrenForWideWindow();
//		} else {
//			layoutChildrenForNarrowWindow();
//		}
//
//	}
//
//	private void layoutChildrenForNarrowWindow() {
//		final Insets insets = getInsets();
//		double width = getWidth();
//
//		final double insideX = insets.getLeft();
//		final double insideY = insets.getTop();
//		final double insideWidth = width - insideX - insets.getRight();
//
//		double x = insideX;
//		double y = insideY;
//
//		for (PropertyPanel propertyPanel : propertyPanels) {
//			// TODO invisible properties
//			// TODO disabled properties
//			PropertyLabel label = propertyPanel.getPropertyLabel();
//			label.setAlignment(Pos.TOP_LEFT);
//			double labelWidth = insideWidth;
//			double labelHeight = label.prefHeight(labelWidth);
//			label.resize(labelWidth, labelHeight);
//			x = insideX;
//			positionInArea(label, x, y, labelWidth, labelHeight,
//					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
//			y += labelHeight;
//
////			RfxContentButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
////			double fieldMenuButtonWidth = 0;
////			double fieldMenuButtonHeight = 0;
////			if (fieldMenuButton != null) {
////				fieldMenuButtonWidth = fieldMenuButton.prefWidth(-1);
////				fieldMenuButtonHeight = fieldMenuButton.prefHeight(-1);
////			}
//
//			Node field = (Node) propertyPanel.getPropertyField();
//			double fieldWidth = insideWidth; //- fieldMenuButtonWidth;
//			double fieldHeight = field.prefHeight(fieldWidth);
//			field.resize(fieldWidth, fieldHeight);
//			positionInArea(field, x, y, fieldWidth, fieldHeight,
//					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
//
////			if (fieldMenuButton != null) {
////				fieldMenuButton.resize(fieldMenuButtonWidth, fieldMenuButtonHeight);
////				x = insideX + VERTICAL_GAP + fieldWidth;
////				positionInArea(fieldMenuButton, x, y, fieldMenuButtonWidth, fieldMenuButtonHeight,
////						0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
////			}
//
//			y += fieldHeight;// Math.max(fieldHeight, fieldMenuButtonHeight);
//			y += HORIZONTAL_GAP;
//
//		}
//		totalHeight = y;
//		totalWidtht = width;
//	}
//
//	private void layoutChildrenForWideWindow() {
//		final Insets insets = getInsets();
//		double width = getWidth();
//
//		final double insideX = insets.getLeft();
//		final double insideY = insets.getTop();
//		final double insideWidth = width - insideX - insets.getRight();
//
//		double x = insideX;
//		double y = insideY;
//
//		double labelWidth = computeLabelWidthForWideWindow();
//
//for (PropertyPanel propertyPanel : propertyPanels) {
//		// TODO invisible properties
//			// TODO disabled properties
//			Label label = propertyPanel.getPropertyLabel();
//			label.setAlignment(Pos.TOP_RIGHT);
//			double labelHeight = label.prefHeight(labelWidth);
//			label.resize(labelWidth, labelHeight);
//			x = insideX;
//			positionInArea(label, x, y + PADDING, labelWidth, labelHeight,
//					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
//
////			RfxContentButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
////			double fieldMenuButtonWidth = 0;
////			double fieldMenuButtonHeight = 0;
////			if (fieldMenuButton != null) {
////				fieldMenuButtonWidth = fieldMenuButton.prefWidth(-1);
////				fieldMenuButtonHeight = fieldMenuButton.prefHeight(-1);
////			}
//
//			Node field = (Node) propertyPanel.getPropertyField();
//			//VERTICAL_GAP - fieldMenuButtonWidth
//			double fieldWidth = Math.min(insideWidth - labelWidth - VERTICAL_GAP ,
//					FIELD_WIDTH_MAX_FOR_WIDE_WINDOW);
//			double fieldHeight = field.prefHeight(fieldWidth);
//			field.resize(fieldWidth, fieldHeight);
//			x = insideX + labelWidth + VERTICAL_GAP;
//			positionInArea(field, x, y, fieldWidth, fieldHeight,
//					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
//
////			if (fieldMenuButton != null) {
////				fieldMenuButton.resize(fieldMenuButtonWidth, fieldMenuButtonHeight);
////				x = insideX + labelWidth + VERTICAL_GAP + fieldWidth;
////				positionInArea(fieldMenuButton, x, y, fieldMenuButtonWidth, fieldMenuButtonHeight,
////						0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
////			}
//
//			y += Math.max(labelHeight, fieldHeight);
//			y += HORIZONTAL_GAP;
//		}
//		totalHeight = y;
//		totalWidtht = width;
//	}
//
//	private double computeLabelWidthForWideWindow() {
//		double maxLabelWidth = 0;
//		for (PropertyPanel propertyPanel : propertyPanels) {
//			// TODO ignore invisible properties
//			Label label = propertyPanel.getPropertyLabel();
//			double labelWidth = label.prefWidth(-1);
//			maxLabelWidth = Math.max(maxLabelWidth, labelWidth);
//		}
//		return Math.min(maxLabelWidth, LABEL_MAX_WIDTH_FOR_WIDE_SCREEN);
//	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(PropertiesPanel.class)).getProperties()
		.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND()).setSpacing(FormViewStyle.getSpacing());
	}

}
