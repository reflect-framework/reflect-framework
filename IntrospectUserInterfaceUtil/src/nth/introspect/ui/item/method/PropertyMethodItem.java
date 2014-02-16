package nth.introspect.ui.item.method;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.view.FormView;
import nth.introspect.ui.item.Item;
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class PropertyMethodItem extends Item {

	private PropertyInfo propertyInfo;
	private ReadOnlyValueModel propertyOwnerModel;
	private MethodInfo propertyMethodInfo;
	private ReadOnlyValueModel parameterValueModel;
	private FormView formView;

	public PropertyMethodItem(FormView formView, PropertyInfo propertyInfo, MethodInfo propertyMethodInfo, ReadOnlyValueModel parameterValueModel) {
		this.formView = formView;
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
				UserInterfaceProvider<?> userInterfaceProvider = Introspect.getUserInterfaceProvider();
				Object propertyOwner = propertyOwnerModel.getValue();
				Object methodParameter = parameterValueModel.getValue();
				userInterfaceProvider.excuteMethod(propertyOwner, propertyMethodInfo, methodParameter);
				userInterfaceProvider.getViewContainer().selectView(formView);
			}
		};
	}

	@Override
	public String getText() {
		// text format: propertyName: propertyMethodName
		StringBuffer text = new StringBuffer();
		text.append(propertyInfo.getText());
		text.append(": ");
		text.append(TitleUtil.createTitle(propertyMethodInfo, parameterValueModel.getValue(), false));
		return text.toString();
	}

}
