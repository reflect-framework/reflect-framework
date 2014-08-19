package nth.introspect.domain.xmltest;

import java.net.URISyntaxException;
import java.util.Collection;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.domain.person.PersonService;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.DefaultDomainProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;
import nth.introspect.provider.version.VersionProvider;
import nth.introspect.util.xml.XmlUtil;

public class XmlTest {

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		IntrospectApplication initializer = new IntrospectApplication(new XmlTest()) {
//
//			@Override
//			public ValidationProvider getValidationProviderClass() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public UserInterfaceProvider<?> createUserInterfaceProvider() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public PathProvider getPathProviderClass() {
//				try {
//					return new DefaultPathProvider(this);
//				} catch (URISyntaxException e) {
//					return null;
//				}
//			}
//
//			@Override
//			public LanguageProvider getLanguageProviderClass() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public DomainProvider createDomainProvider() {
//				return new DefaultDomainProvider();
//			}
//
//			@Override
//			public AuthorizationProvider getAuthorizationProviderClass() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//
//			@Override
//			public VersionProvider getVersionProviderClass() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//		Introspect.init(initializer);
//
//		PersonService personService=new PersonService();
//		try {
//			String xml = XmlUtil.marshal(personService.allPersons(), true);
//			System.out.println(xml);
//			
//			Collection<?> objects = XmlUtil.unmarshal(xml);//TODO does not work with new approach
//			System.out.println(objects);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
