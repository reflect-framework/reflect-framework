package nth.reflect.all.feature.commandline.dom;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.stream.DownloadStream;

/**
 * Note that this class is derived form the TestsService class in the
 * ReflectTestDomain project<br>
 * It is simplified so it is suited for the command line user interface (Which
 * is limited in functionality)
 * 
 * @author nilsth
 *
 */
public class AllFeatureServiceObject {

	private List<AllFeatureDomainObject> allTypes;

	public AllFeatureServiceObject() {
		AllFeatureDomainObject allType = new AllFeatureDomainObject();
		allType.setMyBoolean(true);
		allType.setMyByte((byte) 1);
		allType.setMyChar('z');

		allType.setMyDouble(2);
		allType.setMyFloat((float) 4.1);
		allType.setMyInt(1001);
		allType.setMyLong(28136821);
		allType.setMyShort((short) 12);
		allType.setMyText("text");
		allType.setMyTextArea("textArea");
		allType.setMyPassWord("myPassWord");
		allType.setMyDate(new Date());
		// test.setMyTime(new Date());
		allType.setMyDateTime(new Date());

		allTypes = new ArrayList<AllFeatureDomainObject>();
		allTypes.add(allType);
	}

	public List<AllFeatureDomainObject> allTests() {
		return allTypes;
	}

	public void createTest(AllFeatureDomainObject allType) {
		allTypes.add(allType);
	}

	public AllFeatureDomainObject createTestParameterFactory() {
		return new AllFeatureDomainObject();
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public AllFeatureDomainObject viewTest(AllFeatureDomainObject allType) {
		return allType;
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
		File file = new File("AllFeartureDomainObject.txt");
		try {
			InputStream inputStream = new ByteArrayInputStream(text.getBytes("UTF-8"));
			return new DownloadStream(file, inputStream);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
