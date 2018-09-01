package nth.reflect.fw.ui.swing.view.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URL;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import nth.reflect.fw.generic.util.TitleUtil;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.item.method.FormOkItem;
import nth.reflect.fw.ui.item.tab.CancelItem;
import nth.reflect.fw.ui.item.tab.CloseThisTabItem;
import nth.reflect.fw.ui.swing.item.button.ItemButton;
import nth.reflect.fw.ui.swing.properygrid.PropertyGrid;
import nth.reflect.fw.ui.swing.properygrid.PropertyRow;
import nth.reflect.fw.ui.swing.properygrid.SwingUtil;
import nth.reflect.fw.ui.swing.view.SwingView;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.ui.view.FormMode;

@SuppressWarnings("serial")
public class FormView extends SwingView implements
		nth.reflect.fw.ui.view.FormView { // implements ReadOnlyValueModel {

	private final ActionMethodInfo actionMethodInfo;
	private final Object methodOwner;
	private final BufferedDomainValueModel domainValueModel;
	private final Object methodParameterValue;
	private final FormMode formMode;
	private final Object domainObject;
	private final UserInterfaceContainer userInterfaceContainer;
	private final ReflectionProvider reflectionProvider;

	public FormView(UserInterfaceContainer userInterfaceContainer,
			Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		this.domainObject = domainObject;
		this.formMode = formMode;
		setLayout(new BorderLayout());

		reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainObject
				.getClass());
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();

		domainValueModel = new BufferedDomainValueModel(userInterfaceContainer,
				reflectionProvider, domainObject, formMode);

		PropertyGrid propertyGrid = new PropertyGrid();
		add(propertyGrid, BorderLayout.CENTER);
		Component fieldToGetFocus = null;
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyRow propertyRow = new PropertyRow(this, reflectionProvider,
					domainValueModel, propertyInfo, formMode);
			if (fieldToGetFocus == null && FormMode.EDIT_MODE == formMode
					&& propertyInfo.isEnabled(domainObject)) {
				// XXX also check for field visibility?
				fieldToGetFocus = propertyRow.getField();
			}
			propertyGrid.addPropertyRow(propertyRow);
		}

		add(createButtonBar(), BorderLayout.SOUTH);

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
		case READ_ONLY_MODE:
			buttonBar.add(createCloseButton());
			break;
		case EDIT_MODE:
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
		ViewController viewController = userInterfaceController
				.getViewController();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CloseThisTabItem closeItem = new CloseThisTabItem(languageProvider,
				viewController, this);
		return new ItemButton(closeItem);
	}

	public JButton createCancelButton() {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		ViewController viewController = userInterfaceController
				.getViewController();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CancelItem cancelItem = new CancelItem(languageProvider, viewController,
				this);
		return new ItemButton(cancelItem);
	}

	public JButton createOkButton() {
		FormOkItem okItem = new FormOkItem(this, methodOwner, actionMethodInfo,
				domainValueModel);
		return new ItemButton(okItem);
	}

	@Override
	public String getViewTitle() {
		return TitleUtil.createTitle(reflectionProvider, actionMethodInfo,
				domainValueModel.getValue());
	}

	@Override
	public String getViewDescription() {
		return actionMethodInfo.getDescription();
	}

	@Override
	public URL getViewIconURL() {
		return actionMethodInfo.getFontIconUrl(methodOwner);
	}

	@Override
	public ReadOnlyValueModel getDomainValueModel() {
		return domainValueModel;
	}

	@Override
	public void onViewActivate() {
		SwingUtil.refreshComponentAndItsChildren(this);
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
