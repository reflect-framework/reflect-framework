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
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.view.ViewContainer;
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
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

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
	private final DomainInfoProvider domainInfoProvider;
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

		domainInfoProvider=userInterfaceContainer.getDomainInfoProvider();
		List<PropertyInfo> propertyInfos = domainInfoProvider
				.getPropertyInfos(domainObject.getClass());

		domainValueModel = new BufferedDomainValueModel(userInterfaceContainer, domainInfoProvider, domainObject, formMode);

		PropertyGrid propertyGrid = new PropertyGrid();
		add(propertyGrid, BorderLayout.CENTER);
		Component fieldToGetFocus = null;
		for (PropertyInfo propertyInfo : propertyInfos) {
			PropertyRow propertyRow = new PropertyRow(this, domainInfoProvider, pathProvider, domainValueModel,
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
		return TitleUtil.createTitle(domainInfoProvider, methodInfo, domainValueModel.getValue(),
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
