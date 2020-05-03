package nth.reflect.fw.layer3domain;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.stream.DownloadStream;

/**
 * The contents of this class serves several purposes:
 * <ul>
 * <li>An example of a {@link ServiceObject}</li>
 * <li>A show case of all types that are supported by the
 * {@link ReflectFramework} by default</li>
 * <li>A test class for (JUnit) testing</li>
 * </ul>
 */
public class AllFeatureServiceObject {

	private List<AllFeatureDomainObject> allTypes;

	public AllFeatureServiceObject() {
		AllFeatureDomainObject domainObject = new AllFeatureDomainObject();
		domainObject.setMyBoolean(true);
		domainObject.setMyByte((byte) 1);
		domainObject.setMyCharacter('z');
		domainObject.setMyDouble(2d);
		domainObject.setMyFloat((float) 4.1);
		domainObject.setMyInteger(1001);
		domainObject.setMyLong(28136821l);
		domainObject.setMyShort((short) 12);
		domainObject.setMyText("text");
		domainObject.setMyTextArea("textArea");
		domainObject.setMyPassWord("myPassWord");
		domainObject.setMyDate(new Date());
		domainObject.setMyDate(new Date());
		allTypes = new ArrayList<AllFeatureDomainObject>();
		allTypes.add(domainObject);
	}

	public static final String ALL_METHOD_NAME = "all";

	public List<AllFeatureDomainObject> all() {
		return allTypes;
	}

	public void createTest(AllFeatureDomainObject allType) {
		allTypes.add(allType);
	}

	public AllFeatureDomainObject createTestParameterFactory() {
		return new AllFeatureDomainObject();
	}

	public static final String VIEW_METHOD_NAME = "view";

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public AllFeatureDomainObject view(AllFeatureDomainObject domainObject) {
		return domainObject;
	}

	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyTest(AllFeatureDomainObject allType) {
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteTest(AllFeatureDomainObject allType) {
		allTypes.remove(allType);
	}

	public int countTest() {
		return allTypes.size();
	}

	public URI aboutTheDeveloper() {
		try {
			return new URI("http://nl.linkedin.com/in/nilstenhoeve/");
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public DownloadStream downloadTestFile() {
		String text = "This is a test";
		File file = new File("AllFeatureDomainObject.txt");
		try {
			InputStream inputStream = new ByteArrayInputStream(text.getBytes("UTF-8"));
			return new DownloadStream(file, inputStream);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
