package nth.introspect.ui.item.method;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.ui.item.Item;
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class PropertyMethodItem extends Item {

	private PropertyInfo propertyInfo;
	private ReadOnlyValueModel propertyOwnerValueModel;
	private MethodInfo propertyMethodInfo;
	private ReadOnlyValueModel parameterValueModel;

	public PropertyMethodItem(ReadOnlyValueModel propertyOwnerValueModel, PropertyInfo propertyInfo, MethodInfo propertyMethodInfo, ReadOnlyValueModel parameterValueModel) {
		this.propertyOwnerValueModel = propertyOwnerValueModel;
		this.propertyInfo = propertyInfo;
		this.propertyMethodInfo = propertyMethodInfo;
		this.parameterValueModel = parameterValueModel;
		setAction(createAction());
	}

	private Action createAction() {
		return new Action() {

			@Override
			public void run() {
				UserInterfaceProvider<?> userInterfacePort = Introspect.getUserInterfaceProvider();
				Object propertyOwner = propertyOwnerValueModel.getValue();
				Object methodParameter = parameterValueModel.getValue();
				userInterfacePort.excuteMethod(propertyOwner, propertyMethodInfo, methodParameter);
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
