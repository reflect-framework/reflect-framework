package nth.reflect.infra.xmlfilerepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.generic.filter.FilterUtil;
import nth.reflect.fw.layer5provider.url.application.ApplicationUrl;
import nth.reflect.infra.generic.xml.XmlConverter;

/**
 * A very simple data access class for the {@link ReflectFramework} that can
 * be extended to store and read objects in an object database (an xml file)
 * 
 * @author nilsth
 * 
 * @param <T>
 *            type of the domain objects to be stored
 */
// TODO implement DataAccess interface
// TODO move this class to a new ReflectInfrastructureSimpleXmlRepositority
// TODO make crypto optional
public class XmlFileRepository {

	private final XmlConverter xmlConverter;


	private static final String PASS_PHRASE = "89evJEWIJ9$*&(#J @E2DD(*ehhlju,>/x hw**3rh1~~@();hye";
	private List<Object> domainObjects;
	private Boolean xmlIndent;
	private File databaseFile;

	/**
	 * See {@link XmlFileRepository}
	 * 
	 * @param domainClass
	 *            Type of the domain objects that this data access object
	 *            handles
	 * @param xmlIndent
	 *            True if the data in the XML file needs to be indented (indents
	 *            make the XML easier to read by a human, but result in more
	 *            data to store and process)
	 * @throws MalformedURLException 
	 * @throws URISyntaxException 
	 */
	// TODO add parameter String encryptionKey
	public XmlFileRepository(XmlConverter xmlConverter, String databaseName,
			Boolean xmlIndent) throws MalformedURLException, URISyntaxException {
		this.xmlConverter = xmlConverter;
		this.xmlIndent = xmlIndent;
		String databaseFileName = databaseName + ".db";
		String relativePath = "";
		URL url=new ApplicationUrl(relativePath,databaseFileName).toInternalURL();
		databaseFile = new File(url.toURI());
		domainObjects = new ArrayList<Object>();
	}

	public List<Object> getAll() throws Exception {
		if (domainObjects.size() == 0) {
			// only reads the database file once, assuming that the
			// domainObjects reference in this class is always kept synchronized
			// with the database file. So we are dangerously assuming this class
			// is the only class that makes changes to the database file!
			String xml = readXmlDatabaseFile();
			domainObjects = (List<Object>) xmlConverter.unmarshal(xml);
		}
		return domainObjects;
	}

	public List<?> getAll(Filter<Object> filter) throws Exception {
		domainObjects = getAll();
		return FilterUtil.filter(domainObjects, filter);
	}

	private String readXmlDatabaseFile() throws InvalidKeyException,
			InvalidAlgorithmParameterException, Exception {
		if (databaseFile.exists()) {
			FileInputStream fileInputStream = new FileInputStream(
					databaseFile);
			CipherInputStream cipherInputStream = CipherUtil
					.createCipherInputStream(PASS_PHRASE, fileInputStream);
			return new Scanner(cipherInputStream).useDelimiter("\\Z").next();
		} else {
			return "";
		}
	}

	public void persist(Object domainObject) throws Exception {
		if (!domainObjects.contains(domainObject)) {
			// new domain object to be added.
			domainObjects.add(domainObject);
		}

		persistAll();
	}

	public void persistAll() throws Exception {
		String xml = xmlConverter.marshal(domainObjects, xmlIndent);
		FileOutputStream fileOutputStream = new FileOutputStream(
				databaseFile);
		CipherOutputStream cipherOutputStream = CipherUtil
				.createCipherOutputStream(PASS_PHRASE,fileOutputStream);
		PrintWriter printWriter = new PrintWriter(cipherOutputStream);
		printWriter.print(xml);
		printWriter.close();
	}

	public void delete(Object domainObject) throws Exception {
		domainObjects.remove(domainObject);
		persistAll();
	}
	
	/**
	 * TODO return generic type
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<?> getAll(final Class<?> type) throws Exception {
		Filter<Object> typeFilter = new Filter<Object>() {

			@Override
			public boolean isMatch(Object obj) {
				return type == obj.getClass();
			}
		};
		return getAll(typeFilter);
	}

}
