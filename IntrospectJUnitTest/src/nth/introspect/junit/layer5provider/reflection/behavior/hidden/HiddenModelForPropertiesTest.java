package nth.introspect.junit.layer5provider.reflection.behavior.hidden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

import com.acme.web.shop.provider.authorization.AcmeAuthorizationProvider;

public class HiddenModelForPropertiesTest {

	private ReflectionProvider reflectionProvider;
	
		@Before
		public void setUp() throws Exception {
			IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {
				
				@Override
				public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
					return AcmeAuthorizationProvider.class;
				}
			};
			DependencyInjectionContainer container = application.createContainer();
			AcmeAuthorizationProvider authorizationProvider = container.get(AcmeAuthorizationProvider.class);
			authorizationProvider.login("carla", "password1");
			reflectionProvider = container.get(ReflectionProvider.class);
		}
		
		@Test
		public void propertyHiddenInTable() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenInTable");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyHiddenInForm() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenInForm");
			assertTrue(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}

		@Test
		public void propertyHiddenInFormAndTable() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenInFormAndTable");
			assertFalse(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}

		@Test
		public void propertyHiddenNotInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenNotInRole");
			assertFalse(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyVisibleInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyVisibleInRole");
			assertTrue(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		
		@Test
		public void propertyCollection() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyCollection");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		
		@Test
		public void propertyHiddenMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenMethod");
			assertTrue(propertyInfo.isVisibleInTable());//true: can not be set dynamically
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyVisibleMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyVisibleMethod");
			assertTrue(propertyInfo.isVisibleInTable());//true: can not be set dynamically
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyHiddenInTableHiddenInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenInTableHiddenInRole");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
	
		
		@Test
		public void propertyHiddenInTableVisibleInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenInTableVisibleInRole");
			assertTrue(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
	

		@Test
		public void propertyHiddenInTableHiddenMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenInTableHiddenMethod");
			assertFalse(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}
		

		@Test
		public void propertyHiddenInTableVisibleMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(HiddenModelForPropertiesTestObject.class, "propertyHiddenInTableVisibleMethod");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
}
