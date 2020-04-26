package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;

public class ManyToOneOrManyFieldFactoryTest extends FieldFactoryTest {

	private ManyToOneOrManyFieldFactory manyToOneOrManyFieldFactory;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		manyToOneOrManyFieldFactory = createManyToOneOrManyFieldFactory();
	}

	private ManyToOneOrManyFieldFactory createManyToOneOrManyFieldFactory() {
		return new ManyToOneOrManyFieldFactory() {
			@Override
			public PropertyField create(PropertyFieldFactoryInfo makeInformation) {
				return null;
			}
		};
	}

	@Test
	public void testCanCreate_givenString_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, AllFeatureDomainObject.GET_MY_TEXT, false);
	}

	@Test
	public void testCanCreate_givenSimpleIntArray_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, AllFeatureDomainObject.GET_MY_PRIMITIVE_INT_ARRAY, false);
	}

	@Test
	public void testCanCreate_givenStringArray_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, AllFeatureDomainObject.GET_MY_STRING_ARRAY, false);
	}

	@Test
	public void testCanCreate_givenFullFeatureDomainObjectArray_mustReturnTrue() {
		assertCanCreate(manyToOneOrManyFieldFactory, AllFeatureDomainObject.GET_MY_DOMAIN_OBJECT_ARRAY, true);
	}

	@Test
	public void testCanCreate_givenStringCollection_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, AllFeatureDomainObject.GET_MY_STRING_SET, false);
	}

	@Test
	public void testCanCreate_givenFullFeatureDomainObjectCollection_mustReturnTrue() {
		assertCanCreate(manyToOneOrManyFieldFactory, AllFeatureDomainObject.GET_MY_DOMAIN_OBJECT_LIST, true);
	}

}
