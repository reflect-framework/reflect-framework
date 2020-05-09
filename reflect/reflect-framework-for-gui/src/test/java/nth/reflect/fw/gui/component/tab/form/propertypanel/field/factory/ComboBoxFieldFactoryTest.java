package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;

public class ComboBoxFieldFactoryTest extends FieldFactoryTest {

	private ComboBoxFieldFactory comboBoxFieldFactory;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		comboBoxFieldFactory = createFieldFactory();
	}

	protected ComboBoxFieldFactory createFieldFactory() {
		return new ComboBoxFieldFactory() {
			@Override
			public PropertyField create(PropertyFieldFactoryInfo makeInformation) {
				return null;
			}
		};
	}

	@Test
	public void testCanCreate_forEnumProperty_shouldReturnTrue() throws NoSuchMethodException, SecurityException {
		assertCanCreate(comboBoxFieldFactory, AllFeatureDomainObject.GET_MY_ENUM, true);
	}

	@Test
	public void testCanCreate_forPropertyWithOptionsMethod_shouldReturnTrue()
			throws NoSuchMethodException, SecurityException {
		assertCanCreate(comboBoxFieldFactory, AllFeatureDomainObject.GET_MY_CHOICE, true);
	}

	@Test
	public void testCanCreate_forPropertyWithoutOptions_shouldReturnFalse()
			throws NoSuchMethodException, SecurityException {
		assertCanCreate(comboBoxFieldFactory, AllFeatureDomainObject.GET_MY_TEXT, false);
	}

}