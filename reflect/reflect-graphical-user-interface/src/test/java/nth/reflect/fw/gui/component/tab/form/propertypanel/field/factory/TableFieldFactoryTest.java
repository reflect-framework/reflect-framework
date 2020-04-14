package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;

public class TableFieldFactoryTest extends FieldFactoryTest {

	private TableFieldFactory tableFieldFactory;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tableFieldFactory = createTavleFieldFactory();
	}

	private TableFieldFactory createTavleFieldFactory() {
		return new TableFieldFactory() {
			@Override
			public PropertyField create(PropertyFieldFactoryInfo makeInformation) {
				return null;
			}
		};
	}

	@Test
	public void testCanCreate_givenString_mustReturnFalse() {
		assertCanCreate(tableFieldFactory, FullFeatureDomainObject.GET_MY_TEXT, false);
	}

	@Test
	public void testCanCreate_givenSimpleIntArray_mustReturnTrue() {
		assertCanCreate(tableFieldFactory, FullFeatureDomainObject.GET_MY_PRIMITIVE_INT_ARRAY, true);
	}

	@Test
	public void testCanCreate_givenStringArray_mustReturnTrue() {
		assertCanCreate(tableFieldFactory, FullFeatureDomainObject.GET_MY_STRING_ARRAY, true);
	}

	@Test
	public void testCanCreate_givenFullFeatureDomainObjectArray_mustReturnFalse() {
		assertCanCreate(tableFieldFactory, FullFeatureDomainObject.GET_MY_DOMAIN_OBJECT_ARRAY, false);
	}

	@Test
	public void testCanCreate_givenStringCollection_mustReturnTrue() {
		assertCanCreate(tableFieldFactory, FullFeatureDomainObject.GET_MY_STRING_SET, true);
	}

	@Test
	public void testCanCreate_givenFullFeatureDomainObjectCollection_mustReturnFalse() {
		assertCanCreate(tableFieldFactory, FullFeatureDomainObject.GET_MY_DOMAIN_OBJECT_LIST, false);
	}

}
