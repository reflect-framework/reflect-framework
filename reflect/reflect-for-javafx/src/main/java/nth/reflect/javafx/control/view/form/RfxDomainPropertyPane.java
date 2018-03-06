package nth.reflect.javafx.control.view.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer3domain.DomainObjectPropertyActionMethod;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.ui.view.FormMode;
import nth.reflect.javafx.control.button.RfxContentButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.view.form.field.FieldFactory;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Property pane to display or edit the {@link DomainObjectProperty}s,
 * including:<br>
 * <ul>
 * <li>
 * a label that represents the {@link DomainObjectProperty} name (above field
 * for narrow window, left of field for wide window)
 * </li>
 * <li>
 * a field to display or edit the {@link DomainObjectProperty} value
 * </li>
 * <li>
 * a button to open a context menu with
 * {@link DomainObjectPropertyActionMethod}s
 * </li>
 * </ul> For styling see <a href=
 * "https://material.io/guidelines/components/text-fields.html#text-fields-layout">Google
 * Material Design Guidelines</a>
 * 
 * @author nilsth
 *
 */
public class RfxDomainPropertyPane extends Pane {

	// TODO see:
	// https://material.io/guidelines/components/text-fields.html#text-fields-layout

	private static final int PADDING = 8;
	private static final double HORIZONTAL_GAP = 16;
	private static final double VERTICAL_GAP = 8;
	private static final double LABEL_MAX_WIDTH_FOR_WIDE_SCREEN = 200;
	private static final double FIELD_WIDTH_MAX_FOR_WIDE_WINDOW = RfxWindow.WINDOW_FAIRLY_WIDE_BINDING;
	private static final int LABEL_FONT_SIZE = 12;
	private Map<PropertyInfo, Label> labels;
	private Map<PropertyInfo, Region> fields;
	private Map<PropertyInfo, RfxContentButton> fieldMenuButtons;
	private final List<PropertyInfo> propertyInfos;
	private final BooleanBinding windowExtraWideBinding;

	public RfxDomainPropertyPane(RfxFormView formView) {
		super();
		// initCellsFirstStyle();
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		Object domainObject = formView.getDomainObject();
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainObject.getClass());
		propertyInfos = classInfo.getPropertyInfosSorted();

		RfxWindow rfxWindow = userInterfaceContainer.get(RfxWindow.class);
		windowExtraWideBinding = rfxWindow.getExtraWideBinding();

		populateWithLabelsFieldsAndFieldMenuButtons(reflectionProvider, formView);

		setPadding(new Insets(PADDING));
	}

	/**
	 * Creating the children in order of display order (for the proper focus
	 * order)
	 * 
	 * @param reflectionProvider
	 * @param formView
	 */
	private void populateWithLabelsFieldsAndFieldMenuButtons(ReflectionProvider reflectionProvider,
			RfxFormView formView) {

		labels = new HashMap<>();
		fields = new HashMap<>();
		fieldMenuButtons = new HashMap<>();
		BufferedDomainValueModel domainValueModel = formView.getDomainValueModel();
		FormMode formMode = formView.getFormMode();
		for (PropertyInfo propertyInfo : propertyInfos) {
			Label label = createLabel(propertyInfo);
			labels.put(propertyInfo, label);

			Region field = createField(reflectionProvider, formView, domainValueModel, formMode,
					propertyInfo);
			fields.put(propertyInfo, field);

			RfxContentButton fieldMenuButton = createFieldMenuButton(propertyInfo);
			fieldMenuButtons.put(propertyInfo, fieldMenuButton);
		}
	}

	private Label createLabel(PropertyInfo propertyInfo) {
		Label label = new Label(propertyInfo.getDisplayName());
		Font font = RfxFontFactory.create(MaterialFont.getRobotoRegular(LABEL_FONT_SIZE));
		label.setFont(font);
		label.setPrefHeight(Label.USE_COMPUTED_SIZE);
		label.setWrapText(true);
		label.setStyle(new RfxStyleProperties()
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND2()).toString());
		getChildren().add(label);
		return label;
	}

	private Region createField(ReflectionProvider reflectionProvider, RfxFormView formView,
			BufferedDomainValueModel domainValueModel, FormMode formMode,
			PropertyInfo propertyInfo) {
		PropertyValueModel propertyValueModel = new PropertyValueModel(domainValueModel,
				propertyInfo, formMode);
		Region field = FieldFactory.create(formView, reflectionProvider, propertyValueModel);
		getChildren().add(field);
		return field;
	}

	private RfxContentButton createFieldMenuButton(PropertyInfo propertyInfo) {
		RfxContentButton fieldMenuButton = new RfxContentButton(FontAwesomeIconName.ELLIPSIS_V);
		getChildren().add(fieldMenuButton);
		return fieldMenuButton;
	}

	/**
	 * @return 0: minimum width already set in {@link RfxWindow}
	 */
	@Override
	protected double computeMinWidth(double height) {
		return 0;
	}

	/**
	 * @return 0: minimum height already set in {@link RfxWindow}
	 */
	@Override
	protected double computeMinHeight(double width) {
		return 0;
	}

	/**
	 * @return 0: preferred width already set in {@link RfxWindow}
	 */

	@Override
	protected double computePrefWidth(double height) {
		return 0;
	}

	/**
	 * @return 0: preferred height already set in {@link RfxWindow}
	 */
	@Override
	protected double computePrefHeight(double width) {
		return 0;
	}

	@Override
	protected void layoutChildren() {
		if (windowExtraWideBinding.get()) {
			layoutChildrenForWideWindow();
		} else {
			layoutChildrenForNarrowWindow();
		}

	}

	private void layoutChildrenForNarrowWindow() {
		final Insets insets = getInsets();
		double width = getWidth();

		final double insideX = insets.getLeft();
		final double insideY = insets.getTop();
		final double insideWidth = width - insideX - insets.getRight();

		double x = insideX;
		double y = insideY;

		for (PropertyInfo propertyInfo : propertyInfos) {
			// TODO invisible properties
			// TODO disabled properties
			Label label = labels.get(propertyInfo);
			label.setAlignment(Pos.TOP_LEFT);
			double labelWidth = insideWidth;
			double labelHeight = label.prefHeight(labelWidth);
			label.resize(labelWidth, labelHeight);
			x = insideX;
			positionInArea(label, x, y, labelWidth, labelHeight,
					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
			y += labelHeight;

			RfxContentButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
			double fieldMenuButtonWidth = 0;
			double fieldMenuButtonHeight = 0;
			if (fieldMenuButton != null) {
				fieldMenuButtonWidth = fieldMenuButton.prefWidth(-1);
				fieldMenuButtonHeight = fieldMenuButton.prefHeight(-1);
			}

			Region field = fields.get(propertyInfo);
			double fieldWidth = insideWidth - fieldMenuButtonWidth;
			double fieldHeight = field.prefHeight(fieldWidth);
			field.resize(fieldWidth, fieldHeight);
			positionInArea(field, x, y, fieldWidth, fieldHeight,
					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());

			if (fieldMenuButton != null) {
				fieldMenuButton.resize(fieldMenuButtonWidth, fieldMenuButtonHeight);
				x = insideX + VERTICAL_GAP + fieldWidth;
				positionInArea(fieldMenuButton, x, y, fieldMenuButtonWidth, fieldMenuButtonHeight,
						0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
			}

			y += Math.max(fieldHeight, fieldMenuButtonHeight);
			y += HORIZONTAL_GAP;

		}

	}

	private void layoutChildrenForWideWindow() {
		final Insets insets = getInsets();
		double width = getWidth();

		final double insideX = insets.getLeft();
		final double insideY = insets.getTop();
		final double insideWidth = width - insideX - insets.getRight();

		double x = insideX;
		double y = insideY;

		double labelWidth = computeLabelWidthForWideWindow();

		for (PropertyInfo propertyInfo : propertyInfos) {
			// TODO invisible properties
			// TODO disabled properties
			Label label = labels.get(propertyInfo);
			label.setAlignment(Pos.TOP_RIGHT);
			double labelHeight = label.prefHeight(labelWidth);
			label.resize(labelWidth, labelHeight);
			x = insideX;
			positionInArea(label, x, y + PADDING, labelWidth, labelHeight,
					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());

			RfxContentButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
			double fieldMenuButtonWidth = 0;
			double fieldMenuButtonHeight = 0;
			if (fieldMenuButton != null) {
				fieldMenuButtonWidth = fieldMenuButton.prefWidth(-1);
				fieldMenuButtonHeight = fieldMenuButton.prefHeight(-1);
			}

			Region field = fields.get(propertyInfo);
			double fieldWidth = Math.min(
					insideWidth - labelWidth - VERTICAL_GAP - fieldMenuButtonWidth,
					FIELD_WIDTH_MAX_FOR_WIDE_WINDOW);
			double fieldHeight = field.prefHeight(fieldWidth);
			field.resize(fieldWidth, fieldHeight);
			x = insideX + labelWidth + VERTICAL_GAP;
			positionInArea(field, x, y, fieldWidth, fieldHeight,
					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());

			if (fieldMenuButton != null) {
				fieldMenuButton.resize(fieldMenuButtonWidth, fieldMenuButtonHeight);
				x = insideX + labelWidth + VERTICAL_GAP + fieldWidth;
				positionInArea(fieldMenuButton, x, y, fieldMenuButtonWidth, fieldMenuButtonHeight,
						0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
			}

			y += Math.max(labelHeight, fieldHeight);
			y += HORIZONTAL_GAP;
		}

	}

	private double computeLabelWidthForWideWindow() {
		double maxLabelWidth = 0;
		for (PropertyInfo propertyInfo : propertyInfos) {
			// TODO ignore invisible properties
			Label label = labels.get(propertyInfo);
			double labelWidth = label.prefWidth(-1);
			maxLabelWidth = Math.max(maxLabelWidth, labelWidth);
		}
		return Math.min(maxLabelWidth, LABEL_MAX_WIDTH_FOR_WIDE_SCREEN);
	}

}
