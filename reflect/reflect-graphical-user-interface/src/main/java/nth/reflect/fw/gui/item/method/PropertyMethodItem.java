package nth.reflect.fw.gui.item.method;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * {@link DomainObjectPropertyActionMethod}s can be wrapped in a
 * {@link PropertyMethodItem}, e.g. so that they can be used as menu item.
 * 
 * @author nilsth
 *
 */
public class PropertyMethodItem extends MethodItem {

	private final ReadOnlyValueModel propertyOwnerModel;
	private final ActionMethodInfo propertyMethodInfo;
	private final ReadOnlyValueModel parameterValueModel;
	private final FormTab formTab;

	public PropertyMethodItem(FormTab formTab, PropertyInfo propertyInfo, ActionMethodInfo propertyMethodInfo,
			ReadOnlyValueModel parameterValueModel, boolean showPropertyName) {
		super(formTab.getUserInterfaceContainer(), formTab.getDomainValueModel().getValue(), propertyMethodInfo,
				parameterValueModel);
		this.formTab = formTab;
		this.propertyOwnerModel = formTab.getDomainValueModel();
		this.propertyMethodInfo = propertyMethodInfo;
		this.parameterValueModel = parameterValueModel;
		setAction(createAction());
	}

	private Action createAction() {
		return new Action() {

			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				@SuppressWarnings("rawtypes")
				GraphicalUserinterfaceController userInterfaceController = formTab.getUserInterfaceContainer()
						.get(GraphicalUserinterfaceController.class);
				Object propertyOwner = propertyOwnerModel.getValue();
				Object methodParameter = parameterValueModel.getValue();

				userInterfaceController.getTabs().setSelected(formTab);
				userInterfaceController.processActionMethod(propertyOwner, propertyMethodInfo, methodParameter);
			}
		};
	}

	@Override
	public String getText() {
		// // text format: propertyName: propertyMethodName
		// StringBuffer text = new StringBuffer();
		// if (showPropertyName) {
		// text.append(propertyInfo.getDisplayName());
		// text.append(": ");
		// }
		// Object parameterValue = null;
		// if (!propertyMethodInfo.hasParameterFactory() &&
		// propertyMethodInfo.hasParameter()) {
		// parameterValue = parameterValueModel.getValue();
		// }
		// ReflectionProvider reflectionProvider =
		// formTab.getUserInterfaceContainer().get(ReflectionProvider.class);
		// text.append(TitleUtil.createTitle(reflectionProvider,
		// propertyMethodInfo, parameterValue));
		// return text.toString();
		return propertyMethodInfo.getDisplayName();
	}

	/**
	 * Hide this item when form is not in edit mode or if property method should be
	 * hidden
	 */
	@Override
	public boolean isVisible() {
		boolean methodIsEnabled = propertyMethodInfo.isEnabled(propertyOwnerModel.getValue());
		boolean methodIsVisible = propertyMethodInfo.isVisible(propertyOwnerModel.getValue());
		boolean hasNoParameter = !propertyMethodInfo.hasParameter();
		boolean hasParameterFactory = propertyMethodInfo.hasParameterFactory();
		boolean canGetParameterValue = parameterValueModel.canGetValue();

		boolean formInEditMode = FormMode.EDIT == formTab.getFormMode();
		boolean methodDoesNotChangeDomainObjects = propertyMethodInfo.isReadOnly();

		// TODO check for type as well?
		return methodIsVisible && methodIsEnabled && (hasNoParameter || hasParameterFactory || canGetParameterValue)
				&& (methodDoesNotChangeDomainObjects || formInEditMode);

	}

}
