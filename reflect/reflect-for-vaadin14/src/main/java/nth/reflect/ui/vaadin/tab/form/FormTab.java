package nth.reflect.ui.vaadin.tab.form;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.fw.gui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.item.method.FormOkItem;
import nth.reflect.fw.gui.item.tab.CancelItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.ui.vaadin.UserInterfaceControllerForVaadin14;
import nth.reflect.ui.vaadin.button.Button;
import nth.reflect.ui.vaadin.button.ButtonTheme;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.SizeUnit;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.tab.Tab;
import nth.reflect.ui.vaadin.tab.form.row.PropertyPanel;

@SuppressWarnings("serial")
public class FormTab extends Tab implements nth.reflect.fw.gui.component.tab.form.FormTab {

	private static final int PADDING = 20;
	private final UserInterfaceContainer userInterfaceContainer;
	private final Object actionMethodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final Object domainObject;
	private final FormMode formMode;
	private final BufferedDomainValueModel domainValueModel;
	private final ReflectionProvider reflectionProvider;
	private final List<PropertyPanel> propertyPanels;

	public FormTab(UserInterfaceContainer userInterfaceContainer, Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue, Object domainObject, FormMode formMode) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.actionMethodOwner = actionMethodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		this.domainObject = domainObject;
		this.formMode = formMode;

		this.reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		UserInterfaceControllerForVaadin14 userinterfaceController = userInterfaceContainer
				.get(UserInterfaceControllerForVaadin14.class);
		PropertyPanelFactory<PropertyPanel> formRowFactory = userinterfaceController.getPropertyPanelFactory();

		this.domainValueModel = new BufferedDomainValueModel(userInterfaceContainer, domainObject, formMode);

		propertyPanels = createFormRows(formRowFactory);

		FormLayout formLayout = createFormLayout();
		add(formLayout);
	}

	private void updateFormRows() {
		for (PropertyPanel propertyPanel : propertyPanels) {
			propertyPanel.onRefresh();
		}
	}

	private List<PropertyPanel> createFormRows(PropertyPanelFactory<PropertyPanel> formRowFactory) {
		List<PropertyPanel> propertyPanels = new ArrayList<>();
		DomainClassInfo domainInfo = reflectionProvider.getDomainClassInfo(domainObject.getClass());
		List<PropertyInfo> propertyInfos = domainInfo.getPropertyInfosSorted();
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyValueModel propertyValueModel = new PropertyValueModel(domainValueModel, propertyInfo, formMode);
			PropertyPanel propertyPanel = formRowFactory.createPropertyPanel(this, propertyValueModel);
			propertyPanels.add(propertyPanel);
		}
		return propertyPanels;
	}

	private Component createOkCancelButtonGroup() {
		HorizontalLayout okCancelButtonGroup = new HorizontalLayout();
		new StyleBuilder().setPadding(PADDING, 0, 0, 0).setFor(okCancelButtonGroup);

		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		UserInterfaceControllerForVaadin14 userInterfaceController = userInterfaceContainer
				.get(UserInterfaceControllerForVaadin14.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();

		FormOkItem okItem = new FormOkItem(this, actionMethodOwner, actionMethodInfo, domainValueModel);
		Button okButton = new Button(ButtonTheme.PRIMARY, okItem);
		okCancelButtonGroup.add(okButton);

		CancelItem<Tab> cancelItem = new CancelItem<>(languageProvider, tabs, this);
		Button cancelButton = new Button(ButtonTheme.PRIMARY, cancelItem);
		okCancelButtonGroup.add(cancelButton);

		return okCancelButtonGroup;
	}

	private FormLayout createFormLayout() {

		FormLayout formLayout = new FormLayout();
		new StyleBuilder().setHeight(100, SizeUnit.PERCENT).setOverflow(Overflow.AUTO).setPadding(PADDING)
				.setFor(formLayout);

		for (PropertyPanel propertyPanel : propertyPanels) {
			formLayout.add(propertyPanel);
		}

		if (formMode == FormMode.EDIT) {
			Component buttonGroup = createOkCancelButtonGroup();
			formLayout.add(buttonGroup);
		}

		formLayout.setResponsiveSteps(new ResponsiveStep("0", 1));
		return formLayout;
	}

	@Override
	public String getDisplayName() {
		return actionMethodInfo.getDisplayName().getTranslation();
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.getDescription().getTranslation();
	}

	@Override
	public void onRefresh() {
		updateFormRows();
	}

	@Override
	public Object getMethodOwner() {
		return actionMethodOwner;
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
	public ReadOnlyValueModel getDomainValueModel() {
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

}
