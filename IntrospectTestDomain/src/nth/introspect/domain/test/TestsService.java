package nth.introspect.domain.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

public class TestsService {
	private List<Test> tests;

	public TestsService() {
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
		Test test = new Test();
		test.setMyBoolean(true);
		test.setMyByte((byte) 1);
		test.setMyChar('n');

		test.setMyDouble(2);
		test.setMyFloat((float) 4.1);
		test.setMyInt(1001);
		test.setMyLong(28136821);
		test.setMyShort((short) 12);
		test.setMyText("new text");
		test.setMyTextArea("new textArea");
		test.setMyPassWord("new myPassWord");
		test.setMyDate(new Date());
		// test.setMyTime(new Date());
		test.setMyDateTime(new Date());
		return test;

	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Test viewTest(Test test) {
		return test;
	}

	@ExecutionMode(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyTest(Test test) {
	}

	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteTest(Test test) {
		tests.remove(test);
	}

	public int countTest() {
		return tests.size();
	}

	public URI aboutTheDeveloper() {
		try {
			return new URI("http://www.linkedin.com/pub/nils-ten-hoeve/a/4b4/915");
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
