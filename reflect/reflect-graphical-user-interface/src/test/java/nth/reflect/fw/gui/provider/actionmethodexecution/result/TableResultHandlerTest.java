package nth.reflect.fw.gui.provider.actionmethodexecution.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.table.TableTab;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.AllFeatureServiceObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class TableResultHandlerTest {

	private TableResultHandler resultHandler;
	private UserInterfaceContainer container;
	private AllFeatureServiceObject serviceObject;
	private ServiceClassInfo serviceClassInfo;

	@Before
	public void setUp() throws Exception {
		resultHandler = createTableResultHandler();
		container = new ReflectApplicationForJUnit().addServiceClass(AllFeatureServiceObject.class).createContainer();
		serviceObject = new AllFeatureServiceObject();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(serviceObject.getClass());

	}

	private TableResultHandler createTableResultHandler() {
		return new TableResultHandler() {

			@Override
			public TableTab createTableTab(UserInterfaceContainer container, Object methodOwner,
					ActionMethodInfo actionMethodInfo, Object methodParameter) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Test
	public void testCanProcess_GivenReturnValueDomainObject_mustReturnFalse() {
		ActionMethodInfo methodInfo = serviceClassInfo.getActionMethodInfo(AllFeatureServiceObject.VIEW_METHOD_NAME);
		boolean actual = resultHandler.canProcess(methodInfo);
		assertThat(actual).isFalse();
	}

	@Test
	public void testCanProcess_GivenReturnValueList_mustReturnTrue() {
		ActionMethodInfo methodInfo = serviceClassInfo.getActionMethodInfo(AllFeatureServiceObject.ALL_METHOD_NAME);
		boolean actual = resultHandler.canProcess(methodInfo);
		assertThat(actual).isTrue();
	}

}
