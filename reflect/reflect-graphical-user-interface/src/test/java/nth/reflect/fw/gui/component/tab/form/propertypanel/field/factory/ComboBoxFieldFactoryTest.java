package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.DomainObject;

public class ComboBoxFieldFactoryTest extends FieldFactoryTest {

	private ComboBoxFieldFactory comboBoxFieldFactory;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		comboBoxFieldFactory = createFieldFactory();
	}

	protected ComboBoxFieldFactory createFieldFactory() {
		return new ComboBoxFieldFactory() {
			@Override
			public Optional<PropertyField> create(PropertyFieldFactoryInfo makeInformation) {
				return Optional.empty();
			}
		};
	}

	@Test
	public void testCanCreate_forEnumProperty_shouldReturnTrue() throws NoSuchMethodException, SecurityException {
		assertCanCreate(comboBoxFieldFactory, DomainObject.GET_MY_ENUM, true);
	}

	@Test
	public void testCanCreate_forPropertyWithOptionsMethod_shouldReturnTrue()
			throws NoSuchMethodException, SecurityException {
		assertCanCreate(comboBoxFieldFactory, DomainObject.GET_MY_CHOICE, true);
	}

	@Test
	public void testCanCreate_forPropertyWithoutOptions_shouldReturnFalse()
			throws NoSuchMethodException, SecurityException {
		assertCanCreate(comboBoxFieldFactory, DomainObject.GET_MY_TEXT, false);
	}

}
