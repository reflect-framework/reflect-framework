package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.DomainObject;

public class CheckBoxFieldFactoryTest extends FieldFactoryTest {

	private CheckBoxFieldFactory checkBoxFieldFactory;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		checkBoxFieldFactory = createFieldFactory();
	}
	
	protected CheckBoxFieldFactory createFieldFactory() {
		return new CheckBoxFieldFactory() {
			@Override
			public Optional<PropertyField> create(PropertyFieldFactoryInfo makeInformation) {
				return Optional.empty();
			}
		};
	}

	@Test
	public void testCanCreate_forSimpleBooleanProperty_mustReturnTrue() throws NoSuchMethodException, SecurityException {
		assertCanCreate(checkBoxFieldFactory, DomainObject.IS_MY_PRIMITIVE_BOOLEAN, true);
	}

	@Test
	public void testCanCreate_forBooleanProperty_mustReturnTrue() throws NoSuchMethodException, SecurityException {
		assertCanCreate(checkBoxFieldFactory, DomainObject.IS_MY_BOOLEAN, true);
	}

	@Test
	public void testCanCreate_forStringProperty_mustReturnFalse() throws NoSuchMethodException, SecurityException {
		assertCanCreate(checkBoxFieldFactory, DomainObject.GET_MY_TEXT, false);
	}

}
