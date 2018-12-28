package nth.reflect.fw.javafx.control.tab.form;

import nth.reflect.fw.generic.util.TitleUtil;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;
import nth.reflect.fw.ui.component.tab.form.FormMode;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;

public class FormTab extends Tab implements nth.reflect.fw.ui.component.tab.form.FormTab {

	private final UserInterfaceContainer userInterfaceContainer;
	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final Object domainObject;
	private final FormMode formMode;
	private final ReflectionProvider reflectionProvider;
	private final BufferedDomainValueModel domainValueModel;

	public FormTab(UserInterfaceContainer userInterfaceContainer, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {

		this.userInterfaceContainer = userInterfaceContainer;
		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		this.domainObject = domainObject;
		this.formMode = formMode;
		domainValueModel = new BufferedDomainValueModel(userInterfaceContainer, domainObject, formMode);

		ValidationProvider validationProvider = userInterfaceContainer.get(ValidationProvider.class);

		PropertyGrid domainPropertyPane = new PropertyGrid(validationProvider, this);
		setCenter(domainPropertyPane);

		getStyleClass().add(StyleSheet.createStyleClassName(FormTab.class));
		// TODO new VerticalFlingScroller(scrollPane);

		// VBox vbox=new VBox();
		// vbox.setPrefHeight(javafx.scene.control.Control.USE_COMPUTED_SIZE);
		// vbox.setMaxHeight(Double.POSITIVE_INFINITY);
		// vbox.getChildren().add(scrollPane);

	}

	@Override
	public String getDisplayName() {
		return TitleUtil.createTitle(reflectionProvider, actionMethodInfo, domainValueModel.getValue());
	}

	@Override
	public void onSelected() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getMethodOwner() {
		return methodOwner;
	}

	@Override
	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}

	@Override
	public Object getMethodParameter() {
		return methodParameterValue;
	}

	@Override
	public BufferedDomainValueModel getDomainValueModel() {
		return domainValueModel;
	}

	@Override
	public FormMode getFormMode() {
		return formMode;
	}

	@Override
	public Object getDomainObject() {
		return domainObject;
	}

	@Override
	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(FormTab.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND());
	}

}
