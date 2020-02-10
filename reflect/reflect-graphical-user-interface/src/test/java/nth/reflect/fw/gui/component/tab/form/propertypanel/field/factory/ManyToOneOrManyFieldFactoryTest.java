package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.DomainObject;

public class ManyToOneOrManyFieldFactoryTest extends FieldFactoryTest{

	private ManyToOneOrManyFieldFactory manyToOneOrManyFieldFactory;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		manyToOneOrManyFieldFactory= createManyToOneOrManyFieldFactory();
	}

	private ManyToOneOrManyFieldFactory createManyToOneOrManyFieldFactory() {
		return  new ManyToOneOrManyFieldFactory() {
			@Override
			public Optional<PropertyField> create(PropertyFieldFactoryInfo makeInformation) {
				return Optional.empty();
			}
		};
	}

	@Test
	public void testCanCreate_givenString_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, DomainObject.GET_MY_TEXT, false);
	}

	@Test
	public void testCanCreate_givenSimpleIntArray_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, DomainObject.GET_MY_SIMPLE_INT_ARRAY, false);
	}

	@Test
	public void testCanCreate_givenStringArray_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, DomainObject.GET_MY_STRING_ARRAY, false);
	}

	@Test
	public void testCanCreate_givenDomainObjectArray_mustReturnTrue() {
		assertCanCreate(manyToOneOrManyFieldFactory, DomainObject.GET_MY_DOMAIN_OBJECT_ARRAY, true);
	}
	
	@Test
	public void testCanCreate_givenStringCollection_mustReturnFalse() {
		assertCanCreate(manyToOneOrManyFieldFactory, DomainObject.GET_MY_STRING_SET, false);
	}
	
	
	@Test
	public void testCanCreate_givenDomainObjectCollection_mustReturnTrue() {
		assertCanCreate(manyToOneOrManyFieldFactory, DomainObject.GET_MY_DOMAIN_OBJECT_LIST, true);
	}

}
