package nth.introspect.ui.item.method;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.type.MethodParameterType;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class PropertyMethodItem extends MethodItem {

	private PropertyInfo propertyInfo;
	private ReadOnlyValueModel propertyOwnerModel;
	private MethodInfo propertyMethodInfo;
	private ReadOnlyValueModel parameterValueModel;
	private FormView formView;
	private boolean showPropertyName;

	public PropertyMethodItem(FormView formView, PropertyInfo propertyInfo,
			MethodInfo propertyMethodInfo,
			ReadOnlyValueModel parameterValueModel, boolean showPropertyName) {
		super(formView.getDomainValueModel().getValue(), propertyMethodInfo, parameterValueModel);
		this.formView = formView;
		this.showPropertyName = showPropertyName;
		this.propertyOwnerModel = formView.getDomainValueModel();
		this.propertyInfo = propertyInfo;
		this.propertyMethodInfo = propertyMethodInfo;
		this.parameterValueModel = parameterValueModel;
		setAction(createAction());
	}

	private Action createAction() {
		return new Action() {

			@Override
			public void run() {
				UserInterfaceProvider<?> userInterfaceProvider = Introspect
						.getUserInterfaceProvider();
				Object propertyOwner = propertyOwnerModel.getValue();
				Object methodParameter = parameterValueModel.getValue();
				
 				userInterfaceProvider.getViewContainer().setSelectView(formView);
				userInterfaceProvider.startExecution(propertyOwner, propertyMethodInfo, methodParameter);
			}
		};
	}

	
	@Override
	public String getText() {
		// text format: propertyName: propertyMethodName
		StringBuffer text = new StringBuffer();
		if (showPropertyName) {
			text.append(propertyInfo.getText());
			text.append(": ");
		}
		Object parameterValue = null;
		if (!propertyMethodInfo.hasParameterFactory() &&   propertyMethodInfo.getParameterType().getTypeCategory()!=TypeCategory.NONE ) {
			parameterValue = parameterValueModel.getValue();
		}
		text.append(TitleUtil.createTitle(propertyMethodInfo,
				parameterValue, false));
		return text.toString();
	}


	@Override
	public boolean isEnabled() {
		return propertyMethodInfo.isEnabled(propertyOwnerModel.getValue());
	}
	
	/**
	 * Hide this item when form is not in edit mode or if property method should be hidden
	 */
	@Override
	public boolean isVisible() {
		return FormMode.EDIT_MODE== formView.getFormMode() && propertyMethodInfo.isVisible(propertyOwnerModel.getValue());
	}
	

}
