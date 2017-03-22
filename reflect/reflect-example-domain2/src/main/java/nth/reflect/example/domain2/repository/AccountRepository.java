package nth.reflect.example.domain2.repository;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import nth.introspect.generic.xml.XmlConverter;
import nth.introspect.infrastructure.xmlfilerepository.XmlFileRepository;

public class AccountRepository extends XmlFileRepository{
	
	public AccountRepository(XmlConverter xmlConverter) throws MalformedURLException, URISyntaxException {
		super(xmlConverter, AccountRepository.class.getSimpleName(), true);
	}
}
