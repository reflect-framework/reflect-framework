package nth.introspect.domain.xmltest;

import java.net.URISyntaxException;
import java.util.Collection;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.domain.person.PersonService;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.info.DefaultDomainInfoProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;
import nth.introspect.util.xml.XmlConverter;

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
//			public domainInfoProvider createdomainInfoProvider() {
//				return new DefaultdomainInfoProvider();
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
//			String xml = XmlConverter.marshal(personService.allPersons(), true);
//			System.out.println(xml);
//			
//			Collection<?> objects = XmlConverter.unmarshal(xml);//TODO does not work with new approach
//			System.out.println(objects);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
