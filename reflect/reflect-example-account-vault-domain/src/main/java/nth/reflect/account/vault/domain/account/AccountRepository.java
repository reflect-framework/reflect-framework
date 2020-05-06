package nth.reflect.account.vault.domain.account;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import nth.reflect.infra.generic.xml.XmlConverter;
import nth.reflect.infra.xmlfilerepository.XmlFileRepository;

public class AccountRepository extends XmlFileRepository{
	
	public AccountRepository(XmlConverter xmlConverter) throws MalformedURLException, URISyntaxException {
		super(xmlConverter, AccountRepository.class.getSimpleName(), true);
	}
}
