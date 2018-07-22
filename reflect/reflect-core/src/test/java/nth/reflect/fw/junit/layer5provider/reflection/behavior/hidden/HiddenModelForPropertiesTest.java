package nth.reflect.fw.junit.layer5provider.reflection.behavior.hidden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

import org.junit.Before;
import org.junit.Test;

import com.acme.web.shop.provider.authorization.AcmeAuthorizationProvider;

public class HiddenModelForPropertiesTest {

	
		private ClassInfo classInfo;


		@Before
		public void setUp() throws Exception {
			ReflectApplicationForJUnit application = new ReflectApplicationForJUnit() {
				
				@Override
				public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
					return AcmeAuthorizationProvider.class;
				}
			};
			DependencyInjectionContainer container = application.createContainer();
			AcmeAuthorizationProvider authorizationProvider = container.get(AcmeAuthorizationProvider.class);
			authorizationProvider.login("carla", "password1");
			ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
			classInfo=reflectionProvider.getClassInfo(HiddenModelForPropertiesTestObject.class);
		}
		
		@Test
		public void propertyHiddenInTable() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo( "propertyHiddenInTable");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyHiddenInForm() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenInForm");
			assertTrue(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}

		@Test
		public void propertyHiddenInFormAndTable() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenInFormAndTable");
			assertFalse(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}

		@Test
		public void propertyHiddenNotInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenNotInRole");
			assertFalse(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyVisibleInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyVisibleInRole");
			assertTrue(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		
		@Test
		public void propertyCollection() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyCollection");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		
		@Test
		public void propertyHiddenMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenMethod");
			assertTrue(propertyInfo.isVisibleInTable());//true: can not be set dynamically
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyVisibleMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyVisibleMethod");
			assertTrue(propertyInfo.isVisibleInTable());//true: can not be set dynamically
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
		@Test
		public void propertyHiddenInTableHiddenInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenInTableHiddenInRole");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
	
		
		@Test
		public void propertyHiddenInTableVisibleInRole() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenInTableVisibleInRole");
			assertTrue(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
	

		@Test
		public void propertyHiddenInTableHiddenMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenInTableHiddenMethod");
			assertFalse(propertyInfo.isVisibleInTable());
			assertFalse(propertyInfo.isVisibleInForm(obj));
		}
		

		@Test
		public void propertyHiddenInTableVisibleMethod() {
			HiddenModelForPropertiesTestObject obj=new HiddenModelForPropertiesTestObject();
			PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyHiddenInTableVisibleMethod");
			assertFalse(propertyInfo.isVisibleInTable());
			assertTrue(propertyInfo.isVisibleInForm(obj));
		}
		
}
