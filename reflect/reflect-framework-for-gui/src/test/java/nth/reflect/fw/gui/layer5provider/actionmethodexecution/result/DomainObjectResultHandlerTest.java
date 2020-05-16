package nth.reflect.fw.gui.layer5provider.actionmethodexecution.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.layer5provider.actionmethodexecution.result.DomainObjectResultHandler;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.AllFeatureServiceObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class DomainObjectResultHandlerTest {

	private DomainObjectResultHandler resultHandler;
	private UserInterfaceContainer container;
	private AllFeatureServiceObject serviceObject;
	private ServiceClassInfo serviceClassInfo;
	private ProviderContainer providerContainer;

	@Before
	public void setUp() throws Exception {
		resultHandler = createFormResultHandler();
		container = new ReflectApplicationForJUnit().addServiceClass(AllFeatureServiceObject.class).createContainer();
		serviceObject = new AllFeatureServiceObject();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		providerContainer = container.get(ProviderContainer.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(serviceObject.getClass());
	}

	private DomainObjectResultHandler createFormResultHandler() {
		return new DomainObjectResultHandler() {

			@Override
			public Tab createFormTab(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
					Object methodParameter, Object methodResult, FormMode formMode) {
				return null;
			}
		};
	}

	@Test
	public void testCanProcess_GivenReturnValueDomainObject_mustReturnTrue() {
		ActionMethodInfo methodInfo = serviceClassInfo.getActionMethodInfo(AllFeatureServiceObject.VIEW_METHOD_NAME);
		boolean actual = resultHandler.canProcess(providerContainer, methodInfo);
		assertThat(actual).isTrue();
	}

	@Test
	public void testCanProcess_GivenReturnValueList_mustReturnFalse() {
		ActionMethodInfo methodInfo = serviceClassInfo.getActionMethodInfo(AllFeatureServiceObject.ALL_METHOD_NAME);
		boolean actual = resultHandler.canProcess(providerContainer, methodInfo);
		assertThat(actual).isFalse();
	}

}
