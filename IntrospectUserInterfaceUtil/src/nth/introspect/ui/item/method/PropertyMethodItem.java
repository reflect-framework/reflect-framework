package nth.introspect.ui.item.method;

import nth.introspect.Introspect;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.MethodParameterType;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;

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
		super(formView.getuserInterfaceContainer(),  formView.getDomainValueModel().getValue(), propertyMethodInfo, parameterValueModel);
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
				UserInterfaceController<?> userInterfaceController = formView.getuserInterfaceContainer().getUserInterfaceController();
				Object propertyOwner = propertyOwnerModel.getValue();
				Object methodParameter = parameterValueModel.getValue();
				
 				userInterfaceController.getViewContainer().setSelectedView(formView);
				userInterfaceController.startExecution(propertyOwner, propertyMethodInfo, methodParameter);
			}
		};
	}

	
	@Override
	public String getText() {
		// text format: propertyName: propertyMethodName
		StringBuffer text = new StringBuffer();
		if (showPropertyName) {
			text.append(propertyInfo.getDisplayName());
			text.append(": ");
		}
		Object parameterValue = null;
		if (!propertyMethodInfo.hasParameterFactory() &&   propertyMethodInfo.getParameterType().getTypeCategory()!=TypeCategory.NONE ) {
			parameterValue = parameterValueModel.getValue();
		}
		ReflectionProvider reflectionProvider=formView.getuserInterfaceContainer().getReflectionProvider();
		text.append(TitleUtil.createTitle(reflectionProvider,propertyMethodInfo,
				parameterValue, false));
		return text.toString();
	}


	@Override
	public boolean isEnabled() {
		boolean methodIsEnabled = propertyMethodInfo.isEnabled(propertyOwnerModel.getValue());
		boolean hasNoParameter=propertyMethodInfo.getParameterType().getTypeCategory()==TypeCategory.NONE;
		boolean hasParameterFactory = propertyMethodInfo.hasParameterFactory();
		boolean canGetParameterValue = parameterValueModel.canGetValue() ;//TODO check for type as well?
		return methodIsEnabled && ( hasNoParameter || hasParameterFactory || canGetParameterValue);
	}
	
	/**
	 * Hide this item when form is not in edit mode or if property method should be hidden
	 */
	@Override
	public boolean isVisible() {
		return FormMode.EDIT_MODE== formView.getFormMode() && propertyMethodInfo.isVisible(propertyOwnerModel.getValue());
	}
	

}
