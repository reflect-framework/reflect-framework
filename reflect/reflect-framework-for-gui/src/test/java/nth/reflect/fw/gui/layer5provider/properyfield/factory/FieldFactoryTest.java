package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import org.junit.Before;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.DateTimeFieldFactory;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class FieldFactoryTest {

	private UserInterfaceContainer container;

	@Before
	public void setUp() throws Exception {
		createProviderContainer();
	}

	private void createProviderContainer() {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		container = application.createContainer();
	}

	public PropertyValueModel createPropertyValueModel(UserInterfaceContainer userInterfaceContainer,
			String getterMethodName) {
		PropertyInfo propertyInfo = createPropertyInfo(getterMethodName);
		Object domainObject = new AllFeatureDomainObject();
		BufferedDomainValueModel bufferedDomainValueModel = new BufferedDomainValueModel(userInterfaceContainer,
				domainObject, FormMode.EDIT);
		PropertyValueModel propertyValueModel = new PropertyValueModel(bufferedDomainValueModel, propertyInfo);
		return propertyValueModel;
	}

	private FormTab createFormTab() {
		FormTab formTab = new FormTab() {

			@Override
			public void onRefresh() {
			}

			@Override
			public String getDisplayName() {
				return null;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public Object getMethodParameter() {
				return null;
			}

			@Override
			public Object getMethodOwner() {
				return null;
			}

			@Override
			public ActionMethodInfo getMethodInfo() {
				return null;
			}

			@Override
			public UserInterfaceContainer getUserInterfaceContainer() {
				return container;
			}

			@Override
			public FormMode getFormMode() {
				return FormMode.EDIT;
			}

			@Override
			public ReadOnlyValueModel getDomainValueModel() {
				return null;
			}

			@Override
			public Object getDomainObject() {
				return null;
			}
		};
		return formTab;
	}

	public PropertyInfo createPropertyInfo(String getterMethodName) {
		Method getterMethod;
		try {
			getterMethod = getMethod(getterMethodName);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		ProviderContainer providerContainer = container.get(ProviderContainer.class);
		PropertyInfo propertyInfo = new PropertyInfo(providerContainer, DomainObject.class, getterMethod);
		return propertyInfo;
	}

	private Method getMethod(String getterMethodName) throws NoSuchMethodException {
		Method getterMethod = AllFeatureDomainObject.class.getMethod(getterMethodName);
		return getterMethod;
	}

	public void assertCanCreate(PropertyFieldFactory fieldFactory, String getterMethodName,
			boolean expectedReturnValue) {
		FormTab formTab = createFormTab();
		PropertyValueModel propertyValueModel = createPropertyValueModel(container, getterMethodName);
		boolean result = fieldFactory.canCreate(formTab, propertyValueModel);
		assertThat(result)
				.describedAs("%s.canCreate(PropertyFieldFactoryInfo for %s.%s) resulted in a incorrect return value",
						DateTimeFieldFactory.class.getSimpleName(), DomainObject.class.getSimpleName(),
						getterMethodName)
				.isEqualTo(expectedReturnValue);
	}

}
