package nth.introspect.ui.swing.view.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URI;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import sun.awt.geom.AreaOp.IntOp;
import nth.introspect.Introspect;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.item.method.FormOkItem;
import nth.introspect.ui.item.tab.CancelItem;
import nth.introspect.ui.item.tab.CloseThisTabItem;
import nth.introspect.ui.swing.item.button.ItemButton;
import nth.introspect.ui.swing.properygrid.PropertyGrid;
import nth.introspect.ui.swing.properygrid.PropertyRow;
import nth.introspect.ui.swing.properygrid.SwingUtil;
import nth.introspect.ui.swing.view.SwingView;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.view.FormMode;

@SuppressWarnings("serial")
public class FormView extends SwingView implements
		nth.introspect.ui.view.FormView { // implements ReadOnlyValueModel {

	private final MethodInfo methodInfo;
	private final Object methodOwner;
	private final BufferedDomainValueModel domainValueModel;
	private final Object methodParameterValue;
	private final FormMode formMode;
	private final Object domainObject;
	private final UserInterfaceContainer userInterfaceContainer;
	private final ReflectionProvider reflectionProvider;
	private final PathProvider pathProvider;

	public FormView(UserInterfaceContainer userInterfaceContainer, PathProvider pathProvider, Object methodOwner, MethodInfo methodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.pathProvider = pathProvider;
		this.methodOwner = methodOwner;
		this.methodInfo = methodInfo;
		this.methodParameterValue = methodParameterValue;
		this.domainObject = domainObject;
		this.formMode = formMode;
		setLayout(new BorderLayout());

		reflectionProvider=userInterfaceContainer.getReflectionProvider();
		List<PropertyInfo> propertyInfos = reflectionProvider
				.getPropertyInfos(domainObject.getClass());

		domainValueModel = new BufferedDomainValueModel(userInterfaceContainer, reflectionProvider, domainObject, formMode);

		PropertyGrid propertyGrid = new PropertyGrid();
		add(propertyGrid, BorderLayout.CENTER);
		Component fieldToGetFocus = null;
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyRow propertyRow = new PropertyRow(this, reflectionProvider, pathProvider, domainValueModel,
					propertyInfo, formMode);
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
		ViewContainer viewContainer = userInterfaceContainer.getUserInterfaceController().getViewContainer();
		LanguageProvider languageProvider=userInterfaceContainer.getLanguageProvider();
		CloseThisTabItem closeItem = new CloseThisTabItem(pathProvider, languageProvider, viewContainer, this);
		return new ItemButton(closeItem);
	}

	public JButton createCancelButton() {
		ViewContainer viewContainer = userInterfaceContainer.getUserInterfaceController().getViewContainer();
		LanguageProvider languageProvider=userInterfaceContainer.getLanguageProvider();
		CancelItem cancelItem = new CancelItem(pathProvider, languageProvider, viewContainer, this);
		return new ItemButton(cancelItem);
	}

	public JButton createOkButton() {
		ViewContainer viewContainer = userInterfaceContainer.getUserInterfaceController().getViewContainer();
		FormOkItem okItem = new FormOkItem( this, methodOwner, methodInfo,
				domainValueModel);
		return new ItemButton(okItem);
	}

	@Override
	public String getViewTitle() {
		return TitleUtil.createTitle(reflectionProvider, methodInfo, domainValueModel.getValue(),
				true);
	}

	@Override
	public String getViewDescription() {
		return methodInfo.getDescription();
	}

	@Override
	public URI getViewIconURI() {
		return methodInfo.getIconURI(methodOwner);
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
	public MethodInfo getMethodInfo() {
		return methodInfo;
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
	public UserInterfaceContainer getuserInterfaceContainer() {
		return userInterfaceContainer;
	}
}
