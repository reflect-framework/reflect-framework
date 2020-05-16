package nth.reflect.fw.swing.tab.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.item.method.FormOkItem;
import nth.reflect.fw.gui.item.tab.CancelItem;
import nth.reflect.fw.gui.item.tab.CloseThisTabItem;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldFactoryNotFoundException;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.swing.item.button.ItemButton;
import nth.reflect.fw.swing.properygrid.PropertyGrid;
import nth.reflect.fw.swing.tab.Tab;
import nth.reflect.fw.swing.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.swing.tab.form.proppanel.PropertyPanelFactory;

@SuppressWarnings("serial")
public class FormTab extends Tab implements nth.reflect.fw.gui.component.tab.form.FormTab {

	private final ActionMethodInfo actionMethodInfo;
	private final Object methodOwner;
	private final BufferedDomainValueModel domainValueModel;
	private final Object methodParameterValue;
	private final FormMode formMode;
	private final Object domainObject;
	private final UserInterfaceContainer userInterfaceContainer;
	private final List<PropertyPanel> propertyPanels;

	public FormTab(UserInterfaceContainer userInterfaceContainer, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		this.domainObject = domainObject;
		this.formMode = formMode;
		setLayout(new BorderLayout());

		domainValueModel = new BufferedDomainValueModel(userInterfaceContainer, domainObject, formMode);

		propertyPanels = createPropertyPanels();

		updatePropertyPanels();

		add(createPropertyGrid(), BorderLayout.CENTER);

		add(createButtonBar(), BorderLayout.SOUTH);

		setFocusToFirstField();
	}

	private List<PropertyInfo> getPropertyInfos(Object domainObject) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainObject.getClass());
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		return propertyInfos;
	}

	private PropertyGrid createPropertyGrid() {
		PropertyGrid propertyGrid = new PropertyGrid();
		for (PropertyPanel propertyPanel : propertyPanels) {
			propertyGrid.addPropertyRow(propertyPanel);
		}
		return propertyGrid;
	}

	private void updatePropertyPanels() {
		for (PropertyPanel propertyPanel : propertyPanels) {
			propertyPanel.onRefresh();
		}
	}

	private void setFocusToFirstField() {
		for (PropertyPanel propertyPanel : propertyPanels) {
			Component field = (Component) propertyPanel.getPropertyField();
			if (FormMode.EDIT == formMode && propertyPanel.isVisible() && field.isEnabled()) {
				setFocus(field);
				return;
			}
		}
	}

	private List<PropertyPanel> createPropertyPanels() {
		List<PropertyInfo> propertyInfos = getPropertyInfos(domainObject);

		List<PropertyPanel> propertyPanels = new ArrayList<>();
		PropertyFieldProvider propertyFieldProvider = userInterfaceContainer.get(PropertyFieldProvider.class);
		PropertyPanelFactory propertyPanelFactory = new PropertyPanelFactory(propertyFieldProvider);
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyValueModel propertyValueModel = new PropertyValueModel(domainValueModel, propertyInfo);
			try {
				PropertyPanel propertyPanel = propertyPanelFactory.createPropertyPanel(this, propertyValueModel);
				propertyPanels.add(propertyPanel);
			} catch (PropertyFieldFactoryNotFoundException e) {
			}
		}
		return propertyPanels;
	}

	private void setFocus(Component fieldToGetFocus) {
		// set focus to first enabled field
		if (fieldToGetFocus != null) {
			final Component field = fieldToGetFocus;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					field.requestFocus();
				}
			});

		}
	}

	private Component createButtonBar() {
		JPanel buttonBar = new JPanel();
		buttonBar.setAlignmentX(JToolBar.CENTER_ALIGNMENT);
		buttonBar.add(Box.createHorizontalGlue());

		switch (formMode) {
		case READ_ONLY:
			buttonBar.add(createCloseButton());
			break;
		case EDIT:
			buttonBar.add(createOkButton());
			buttonBar.add(createCancelButton());
			break;
		default:
			break;
		}

		buttonBar.add(Box.createHorizontalGlue());
		return buttonBar;
	}

	public JButton createCloseButton() {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		CloseThisTabItem closeItem = new CloseThisTabItem(languageProvider, tabs, this);
		return new ItemButton(closeItem);
	}

	public JButton createCancelButton() {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		CancelItem cancelItem = new CancelItem(languageProvider, tabs, this);
		return new ItemButton(cancelItem);
	}

	public JButton createOkButton() {
		FormOkItem okItem = new FormOkItem(this, methodOwner, actionMethodInfo, domainValueModel);
		return new ItemButton(okItem);
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
	public ReadOnlyValueModel getDomainValueModel() {
		return domainValueModel;
	}

	@Override
	public void onRefresh() {
		updatePropertyPanels();
	}

	@Override
	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}

	@Override
	public Object getMethodOwner() {
		return methodOwner;
	}

	@Override
	public FormMode getFormMode() {
		return formMode;
	}

	@Override
	public Object getMethodParameter() {
		return methodParameterValue;
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
