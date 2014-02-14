package nth.introspect.ui.commandline.demo.dom;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.userinterface.DownloadStream;
/**
 * Note that this class is derived form the TestsService class in the IntrospectTestDomain project<br>
 * It is simplified so it is suited for the command line user interface (Which is limited in functionality) 
 * @author nilsth
 *
 */
public class TestService {

	private List<Test> tests;

	public TestService() {
		Test test = new Test();
		test.setMyBoolean(true);
		test.setMyByte((byte) 1);
		test.setMyChar('z');

		test.setMyDouble(2);
		test.setMyFloat((float) 4.1);
		test.setMyInt(1001);
		test.setMyLong(28136821);
		test.setMyShort((short) 12);
		test.setMyText("text");
		test.setMyTextArea("textArea");
		test.setMyPassWord("myPassWord");
		test.setMyDate(new Date());
		// test.setMyTime(new Date());
		test.setMyDateTime(new Date());

		tests = new ArrayList<Test>();
		tests.add(test);
	}

	@GenericReturnType(Test.class)
	public List<Test> allTests() {
		return tests;
	}

	public void createTest(Test test) {
		tests.add(test);
	}

	public Test createTestParameterFactory() {
		return new Test();
	}

	@FormMode(FormModeType.showParameterThenClose)
	public void viewTest(Test test) {
	}

	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	public void modifyTest(Test test) {
	}

	@FormMode(FormModeType.showParameterThenExecuteMethodOrCancel)
	public void deleteTest(Test test) {
		tests.remove(test);
	}

	public int countTest() {
		return tests.size();
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
		File file = new File("Test.txt");
		try {
			InputStream inputStream = new ByteArrayInputStream(text.getBytes("UTF-8"));
			return new DownloadStream(file, inputStream);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
