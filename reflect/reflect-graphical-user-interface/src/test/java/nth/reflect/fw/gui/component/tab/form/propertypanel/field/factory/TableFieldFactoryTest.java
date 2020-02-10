package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.DomainObject;

public class TableFieldFactoryTest extends FieldFactoryTest{

	private TableFieldFactory tableFieldFactory;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tableFieldFactory = createTavleFieldFactory();
	}

	private TableFieldFactory createTavleFieldFactory() {
		return new TableFieldFactory() {
			@Override
			public Optional<PropertyField> create(PropertyFieldFactoryInfo makeInformation) {
				return Optional.empty();
			}
		};
	}

	@Test
	public void testCanCreate_givenString_mustReturnFalse() {
		assertCanCreate(tableFieldFactory, DomainObject.GET_MY_TEXT, false);
	}

	@Test
	public void testCanCreate_givenSimpleIntArray_mustReturnTrue() {
		assertCanCreate(tableFieldFactory, DomainObject.GET_MY_SIMPLE_INT_ARRAY, true);
	}

	@Test
	public void testCanCreate_givenStringArray_mustReturnTrue() {
		assertCanCreate(tableFieldFactory, DomainObject.GET_MY_STRING_ARRAY, true);
	}

	@Test
	public void testCanCreate_givenDomainObjectArray_mustReturnFalse() {
		assertCanCreate(tableFieldFactory, DomainObject.GET_MY_DOMAIN_OBJECT_ARRAY, false);
	}
	
	@Test
	public void testCanCreate_givenStringCollection_mustReturnTrue() {
		assertCanCreate(tableFieldFactory, DomainObject.GET_MY_STRING_SET, true);
	}
	
	@Test
	public void testCanCreate_givenDomainObjectCollection_mustReturnFalse() {
		assertCanCreate(tableFieldFactory, DomainObject.GET_MY_DOMAIN_OBJECT_LIST, false);
	}



}
