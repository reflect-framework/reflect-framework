package nth.reflect.javafx.control.formview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.geom.Vec2d;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.ui.view.FormMode;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.formview.field.FieldFactory;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxDomainPropertyPane extends Pane {

	private static final double LABEL_PREF_WIDTH = 60;
	private static final double LABEL_PREF_HEIGHT = 20;
	private static final double PADDING = 8;
	private static final double HORIZONTAL_GAP = 8;
	private static final double VERTICAL_GAP = 8;
	private static final double MAX_LABEL_WIDTH_FOR_WIDE_SCREEN = 200;
	private static final double FIELD_WIDTH_MAX_FOR_WIDE_WINDOW = RfxWindow.WINDOW_FAIRLY_WIDE_BINDING;
	private Map<PropertyInfo, Label> labels;
	private Map<PropertyInfo, Region> fields;
	private Map<PropertyInfo, RfxButton> fieldMenuButtons;
	private final List<PropertyInfo> propertyInfos;
	private final BooleanBinding windowExtraWideBinding;

	private static Vec2d TEMP_VEC2D = new Vec2d();

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
	 * Creating the children in order of display order (for the proper focus order)
	 * @param reflectionProvider
	 * @param formView
	 */
	private void populateWithLabelsFieldsAndFieldMenuButtons(ReflectionProvider reflectionProvider, RfxFormView formView) {

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

			RfxButton fieldMenuButton = createFieldMenuButton(propertyInfo);
			fieldMenuButtons.put(propertyInfo, fieldMenuButton);
		}
	}

	private Map<PropertyInfo, Label> createLables(List<PropertyInfo> propertyInfos) {
		Map<PropertyInfo, Label> labels = new HashMap<>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			Label label = createLabel(propertyInfo);
			labels.put(propertyInfo, label);
		}
		return labels;
	}

	private Label createLabel(PropertyInfo propertyInfo) {
		Label label = new Label(propertyInfo.getDisplayName());
		Font font = RfxFontFactory.create(MaterialFont.getBody1());
		label.setFont(font);
		label.setPrefHeight(Label.USE_COMPUTED_SIZE);
		label.setWrapText(true);
		// TODO bold!!!
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

	// private Region createField(PropertyInfo propertyInfo) {
	// RfxTextField textField = new RfxTextField();
	// textField.setFont(RfxFontFactory.create(MaterialFont.getBody1()));
	// textField.setMinWidth(0);
	// textField.setPrefWidth(0);
	// getChildren().add(textField);
	// return textField;
	// }

	// private Map<PropertyInfo, RfxButton>
	// createFieldMenuButtons(List<PropertyInfo> propertyInfos) {
	// Map<PropertyInfo, RfxButton> fieldMenuButtons = new HashMap<>();
	// for (PropertyInfo propertyInfo : propertyInfos) {
	// RfxButton fieldMenuButton = createFieldMenuButton(propertyInfo);
	// fieldMenuButtons.put(propertyInfo, fieldMenuButton);
	// }
	// return fieldMenuButtons;
	// }

	private RfxButton createFieldMenuButton(PropertyInfo propertyInfo) {
		RfxButton fieldMenuButton = new RfxButton(FontAwesomeIconName.ELLIPSIS_V);
		getChildren().add(fieldMenuButton);
		return fieldMenuButton;
	}

	@Override
	protected double computeMinWidth(double height) {
		// Label label = labels.get(propertyInfos.get(0));
		// double labelMinWidth = computeChildMinAreaWidth(label, -1, -1,
		// false);
		// Region field = fields.get(propertyInfos.get(0));
		// double fieldMinWidth = computeChildMinAreaWidth(field, -1, -1,
		// false);
		// final Insets insets = getInsets();
		// return insets.getLeft() + labelMinWidth + fieldMinWidth +
		// insets.getRight();
		return LABEL_PREF_WIDTH * 2;
	}

	@Override
	protected double computeMinHeight(double width) {
		// Label label = labels.get(propertyInfos.get(0));
		// double labelMinHeight = computeChildMinAreaHeight(label, -1, -1);
		// Region field = fields.get(propertyInfos.get(0));
		// double fieldMinHeight = computeChildMinAreaHeight(field, -1, -1);
		// final Insets insets = getInsets();
		// return insets.getTop() + Math.max(labelMinHeight, fieldMinHeight) +
		// insets.getBottom();
		return LABEL_PREF_HEIGHT * 2;
	}

	@Override
	protected double computePrefWidth(double height) {
		// Label label = labels.get(propertyInfos.get(0));
		// double labelPrefWidth = computeChildPrefAreaWidth(label, -1, -1,
		// false);
		// Region field = fields.get(propertyInfos.get(0));
		// double fieldPrefWidth = computeChildPrefAreaWidth(field, -1, -1,
		// false);
		// final Insets insets = getInsets();
		// return insets.getLeft() + labelPrefWidth + fieldPrefWidth +
		// insets.getRight();
		// return widthProperty().get();
		return 0;
	}

	@Override
	protected double computePrefHeight(double width) {
		// Label label = labels.get(propertyInfos.get(0));
		// double labelPrefHeight = computeChildPrefAreaHeight(label, -1, -1);
		// Region field = fields.get(propertyInfos.get(0));
		// double fieldPrefHeight = computeChildPrefAreaHeight(label, -1, -1);
		// final Insets insets = getInsets();
		// return insets.getTop() + Math.max(labelPrefHeight, fieldPrefHeight) +
		// insets.getBottom();
		// return heightProperty().get();
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
		double height = getHeight();

		// final double minWidth = minWidth(-1);
		// final double minHeight = minHeight(-1);
		// width = width < minWidth ? minWidth : width;
		// height = height < minHeight ? minHeight : height;

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
			double labelHeight = LABEL_PREF_HEIGHT;
			label.resize(labelWidth, labelHeight);
			x = insideX;
			positionInArea(label, x, y, labelWidth, labelHeight,
					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());
			y += labelHeight;

			RfxButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
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
		double height = getHeight();

		// final double minWidth = minWidth(-1);
		// final double minHeight = minHeight(-1);
		// width = width < minWidth ? minWidth : width;
		// height = height < minHeight ? minHeight : height;

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
			positionInArea(label, x, y, labelWidth, labelHeight,
					0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, isSnapToPixel());

			RfxButton fieldMenuButton = fieldMenuButtons.get(propertyInfo);
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
		return Math.min(maxLabelWidth, MAX_LABEL_WIDTH_FOR_WIDE_SCREEN);
	}

	// private double getAreaHeight(Node child, double width, boolean minimum) {
	// if (child != null && child.isManaged()) {
	// return minimum ? computeChildMinAreaHeight(child, -1, width)
	// : computeChildPrefAreaHeight(child, -1, width);
	// }
	// return 0;
	// }

	/**
	 * Returns the size of a Node that should be placed in an area of the
	 * specified size, bounded in it's min/max size, respecting bias.
	 *
	 * @param node
	 *            the node
	 * @param areaWidth
	 *            the width of the bounding area where the node is going to be
	 *            placed
	 * @param areaHeight
	 *            the height of the bounding area where the node is going to be
	 *            placed
	 * @param fillWidth
	 *            if Node should try to fill the area width
	 * @param fillHeight
	 *            if Node should try to fill the area height
	 * @param result
	 *            Vec2d object for the result or null if new one should be
	 *            created
	 * @return Vec2d object with width(x parameter) and height (y parameter)
	 */
	static Vec2d boundedNodeSizeWithBias(Node node, double areaWidth, double areaHeight,
			boolean fillWidth, boolean fillHeight, Vec2d result) {
		if (result == null) {
			result = new Vec2d();
		}

		Orientation bias = node.getContentBias();

		double childWidth = 0;
		double childHeight = 0;

		if (bias == null) {
			childWidth = boundedSize(node.minWidth(-1),
					fillWidth ? areaWidth : Math.min(areaWidth, node.prefWidth(-1)),
					node.maxWidth(-1));
			childHeight = boundedSize(node.minHeight(-1),
					fillHeight ? areaHeight : Math.min(areaHeight, node.prefHeight(-1)),
					node.maxHeight(-1));

		} else if (bias == Orientation.HORIZONTAL) {
			childWidth = boundedSize(node.minWidth(-1),
					fillWidth ? areaWidth : Math.min(areaWidth, node.prefWidth(-1)),
					node.maxWidth(-1));
			childHeight = boundedSize(node.minHeight(childWidth),
					fillHeight ? areaHeight : Math.min(areaHeight, node.prefHeight(childWidth)),
					node.maxHeight(childWidth));

		} else { // bias == VERTICAL
			childHeight = boundedSize(node.minHeight(-1),
					fillHeight ? areaHeight : Math.min(areaHeight, node.prefHeight(-1)),
					node.maxHeight(-1));
			childWidth = boundedSize(node.minWidth(childHeight),
					fillWidth ? areaWidth : Math.min(areaWidth, node.prefWidth(childHeight)),
					node.maxWidth(childHeight));
		}

		result.set(childWidth, childHeight);
		return result;
	}

	/**
	 * Computes the value based on the given min and max values. We encode in
	 * this method the logic surrounding various edge cases, such as when the
	 * min is specified as greater than the max, or the max less than the min,
	 * or a pref value that exceeds either the max or min in their extremes.
	 * <p/>
	 * If the min is greater than the max, then we want to make sure the
	 * returned value is the min. In other words, in such a case, the min
	 * becomes the only acceptable return value.
	 * <p/>
	 * If the min and max values are well ordered, and the pref is less than the
	 * min then the min is returned. Likewise, if the values are well ordered
	 * and the pref is greater than the max, then the max is returned. If the
	 * pref lies between the min and the max, then the pref is returned.
	 *
	 *
	 * @param min
	 *            The minimum bound
	 * @param pref
	 *            The value to be clamped between the min and max
	 * @param max
	 *            the maximum bound
	 * @return the size bounded by min, pref, and max.
	 */
	static double boundedSize(double min, double pref, double max) {
		double a = pref >= min ? pref : min;
		double b = min >= max ? min : max;
		return a <= b ? a : b;
	}

	double computeChildMinAreaWidth(Node child, double baselineComplement, double height,
			boolean fillHeight) {
		double alt = -1;
		if (height != -1 && child.isResizable() && child.getContentBias() == Orientation.VERTICAL) { // width
																										// depends
																										// on
																										// height
			double bo = child.getBaselineOffset();
			final double contentHeight = bo == BASELINE_OFFSET_SAME_AS_HEIGHT
					&& baselineComplement != -1 ? height - baselineComplement : height;
			if (fillHeight) {
				alt = snapSize(
						boundedSize(child.minHeight(-1), contentHeight, child.maxHeight(-1)));
			} else {
				alt = snapSize(boundedSize(child.minHeight(-1), child.prefHeight(-1),
						Math.min(child.maxHeight(-1), contentHeight)));
			}
		}
		return snapSize(child.minWidth(alt));
	}

	double computeChildPrefAreaWidth(Node child, double baselineComplement, double height,
			boolean fillHeight) {
		double alt = -1;
		if (height != -1 && child.isResizable() && child.getContentBias() == Orientation.VERTICAL) { // width
																										// depends
																										// on
																										// height
			double bo = child.getBaselineOffset();
			final double contentHeight = bo == BASELINE_OFFSET_SAME_AS_HEIGHT
					&& baselineComplement != -1 ? height - baselineComplement : height;
			if (fillHeight) {
				alt = snapSize(
						boundedSize(child.minHeight(-1), contentHeight, child.maxHeight(-1)));
			} else {
				alt = snapSize(boundedSize(child.minHeight(-1), child.prefHeight(-1),
						Math.min(child.maxHeight(-1), contentHeight)));
			}
		}
		return snapSize(
				boundedSize(child.minWidth(alt), child.prefWidth(alt), child.maxWidth(alt)));
	}

	double computeChildMinAreaHeight(Node child, double minBaselineComplement, double width) {
		double alt = -1;
		if (child.isResizable() && child.getContentBias() == Orientation.HORIZONTAL) { // height
																						// depends
																						// on
																						// width
			alt = snapSize(width != -1 ? boundedSize(child.minWidth(-1), width, child.maxWidth(-1))
					: child.maxWidth(-1));
		}

		// For explanation, see computeChildPrefAreaHeight
		if (minBaselineComplement != -1) {
			double baseline = child.getBaselineOffset();
			if (child.isResizable() && baseline == BASELINE_OFFSET_SAME_AS_HEIGHT) {
				return snapSize(child.minHeight(alt)) + minBaselineComplement;
			} else {
				return baseline + minBaselineComplement;
			}
		} else {
			return snapSize(child.minHeight(alt));
		}
	}

	double computeChildPrefAreaHeight(Node child, double prefBaselineComplement, double width) {
		double alt = -1;
		if (child.isResizable() && child.getContentBias() == Orientation.HORIZONTAL) { // height
																						// depends
																						// on
																						// width
			alt = snapSize(boundedSize(child.minWidth(-1),
					width != -1 ? width : child.prefWidth(-1), child.maxWidth(-1)));
		}

		if (prefBaselineComplement != -1) {
			double baseline = child.getBaselineOffset();
			if (child.isResizable() && baseline == BASELINE_OFFSET_SAME_AS_HEIGHT) {
				// When baseline is same as height, the preferred height of the
				// node will be above the baseline, so we need to add
				// the preferred complement to it
				return snapSize(boundedSize(child.minHeight(alt), child.prefHeight(alt),
						child.maxHeight(alt))) + +prefBaselineComplement;
			} else {
				// For all other Nodes, it's just their baseline and the
				// complement.
				// Note that the complement already contain the Node's preferred
				// (or fixed) height
				return baseline + prefBaselineComplement;
			}
		} else {
			return snapSize(
					boundedSize(child.minHeight(alt), child.prefHeight(alt), child.maxHeight(alt)));
		}
	}

	/**
	 * If snapToPixel is true, then the value is rounded using Math.round.
	 * Otherwise, the value is simply returned. This method will surely be JIT'd
	 * under normal circumstances, however on an interpreter it would be better
	 * to inline this method. However the use of Math.round here, and Math.ceil
	 * in snapSize is not obvious, and so for code maintenance this logic is
	 * pulled out into a separate method.
	 *
	 * @param value
	 *            The value that needs to be snapped
	 * @param snapToPixel
	 *            Whether to snap to pixel
	 * @return value either as passed in or rounded based on snapToPixel
	 */
	private static double snapSpace(double value, boolean snapToPixel) {
		return snapToPixel ? Math.round(value) : value;
	}
}
