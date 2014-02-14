package nth.introspect.domain.xmltest;

import java.net.URISyntaxException;
import java.util.Collection;

import nth.introspect.Introspect;
import nth.introspect.domain.person.PersonService;
import nth.introspect.initializator.IntrospectInitializer;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.DefaultDomainProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.info.InfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;
import nth.introspect.util.xml.XmlUtil;

public class XmlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IntrospectInitializer initializer = new IntrospectInitializer() {

			@Override
			public ValidationProvider createValidationProvider() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public UserInterfaceProvider<?> createUserInterfaceProvider() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public PathProvider createPathProvider() {
				try {
					return new DefaultPathProvider();
				} catch (URISyntaxException e) {
					return null;
				}
			}

			@Override
			public LanguageProvider createLanguageProvider() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public InfoProvider createInfoProvider() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DomainProvider createDomainProvider() {
				return new DefaultDomainProvider();
			}

			@Override
			public AuthorizationProvider createAuthorizationProvider() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void addServiceClass(Class<?> serviceClass) {
				// TODO Auto-generated method stub
			}
		};
		Introspect.init(initializer);

		PersonService personService=new PersonService();
		try {
			String xml = XmlUtil.marshal(personService.allPersons(), true);
			System.out.println(xml);
			
			Collection<?> objects = XmlUtil.unmarshal(xml);//TODO does not work with new approach
			System.out.println(objects);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
