package nth.introspect.junit.generic.titlebuilder;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import nth.introspect.generic.titlebuilder.TitleBuilder;

import org.junit.Test;

public class TitleBuilderTest {

	private static final String FORMATED_DATE = "1970-01-01";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String SEPARATOR = "-";
	private static final String TEST = "test";
	private static final String NULL_STRING = null;
	private static final String EMPTY_STRING = "";
	private static final Object NULL_OBJECT = null;
	private static final Date NULL_DATE = null;
	private static final String SPACE = " ";
	private static final Number NULL_NUMBER = null;
	private static final String NUMBER_FORMAT = "###.##";
	private static final String FORMATED_NUMBER = "1,46";
	private static final TitleBuilderTestEnum NULL_ENUM = null;

	
	@Test
	public void titleBuilderEmptyConstructor() {
		TitleBuilder titleBuilder = new TitleBuilder();
		String title = titleBuilder.append(1).append("2").toString();
		assertEquals("1 2", title);
	}

	@Test
	public void titleBuilderConstructorWithSeparator() {
		TitleBuilder titleBuilder = new TitleBuilder(SEPARATOR);
		String title = titleBuilder.append(1).append("2").toString();
		assertEquals("1-2", title);
	}

	@Test
	public void appendText() {
		TitleBuilder titleBuilder = new TitleBuilder();
		titleBuilder.append(EMPTY_STRING);
		assertEquals(EMPTY_STRING, titleBuilder.toString());
		titleBuilder.append(NULL_STRING);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(TEST);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.append(EMPTY_STRING);
		assertEquals(TEST, titleBuilder.toString());
	
		titleBuilder.append(NULL_STRING);
		assertEquals(TEST, titleBuilder.toString());
		
		titleBuilder.append(TEST);
		assertEquals(TEST+SPACE+TEST, titleBuilder.toString());

	}

	@Test
	public void appendObject() {
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append(NULL_OBJECT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(this);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.append(NULL_OBJECT);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.append(this);
		assertEquals(TEST+SPACE+TEST, titleBuilder.toString());

	}

	@Test
	public void appendSeparatorAndText() {
		TitleBuilder titleBuilder = new TitleBuilder();
		titleBuilder.append(SEPARATOR, EMPTY_STRING);
		assertEquals(EMPTY_STRING, titleBuilder.toString());
		titleBuilder.append(SEPARATOR,NULL_STRING);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, TEST);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, EMPTY_STRING);
		assertEquals(TEST, titleBuilder.toString());
		titleBuilder.append(SEPARATOR, NULL_STRING);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, TEST);
		assertEquals(TEST+SEPARATOR+TEST, titleBuilder.toString());
	}

	@Test
	public void appendSeparatorAndObject() {
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append(SEPARATOR, NULL_OBJECT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, this);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.append(NULL_OBJECT);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, this);
		assertEquals(TEST+SEPARATOR+TEST, titleBuilder.toString());
	}

	@Test
	public void appendDateWithFormatPattern() {
		Date date=new Date();
		date.setTime(0);
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append( NULL_DATE, DATE_FORMAT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(date,DATE_FORMAT);
		assertEquals(FORMATED_DATE, titleBuilder.toString());

		titleBuilder.append( NULL_DATE, DATE_FORMAT);
		assertEquals(FORMATED_DATE, titleBuilder.toString());

		titleBuilder.append(date,DATE_FORMAT);
		assertEquals(FORMATED_DATE+SPACE+FORMATED_DATE, titleBuilder.toString());

	}

	@Test
	public void appendSeparatorAndDateWithFormatPattern() {
		Date date=new Date();
		date.setTime(0);
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append(SEPARATOR, NULL_DATE, DATE_FORMAT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, date,DATE_FORMAT);
		assertEquals(FORMATED_DATE, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, NULL_DATE, DATE_FORMAT);
		assertEquals(FORMATED_DATE, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, date,DATE_FORMAT);
		assertEquals(FORMATED_DATE+SEPARATOR+FORMATED_DATE, titleBuilder.toString());
	}

	@Test
	public void appendNumberWithFormatPattern() {
		Double number=new Double(1.4567);
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append( NULL_NUMBER, NUMBER_FORMAT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(number,NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER, titleBuilder.toString());

		titleBuilder.append( NULL_NUMBER, NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER, titleBuilder.toString());

		titleBuilder.append(number,NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER+SPACE+FORMATED_NUMBER, titleBuilder.toString());
	}

	@Test
	public void appendSeparatorAndNumberWithFormatPattern() {
		Double number=new Double(1.4567);
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append(SEPARATOR, NULL_NUMBER, NUMBER_FORMAT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(SEPARATOR,number,NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER, titleBuilder.toString());

		titleBuilder.append(SEPARATOR, NULL_NUMBER, NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER, titleBuilder.toString());

		titleBuilder.append(SEPARATOR,number,NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER+SEPARATOR+FORMATED_NUMBER, titleBuilder.toString());
	}

	@Test
	public void appendEnumeration() {
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append( NULL_ENUM);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(TitleBuilderTestEnum.ONE);
		assertEquals("One", titleBuilder.toString());

		titleBuilder.append( NULL_ENUM);
		assertEquals("One", titleBuilder.toString());
		
		titleBuilder.append(TitleBuilderTestEnum.TWO);
		assertEquals("One Two", titleBuilder.toString());

		
		titleBuilder.append(TitleBuilderTestEnum.TWENTY_ONE);
		assertEquals("One Two Twenty one", titleBuilder.toString());
	}


	@Test
	public void appendSeparatorAndEnumeration() {
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.append(SEPARATOR, NULL_ENUM);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.append(SEPARATOR,TitleBuilderTestEnum.ONE);
		assertEquals("One", titleBuilder.toString());

		titleBuilder.append(SEPARATOR, NULL_ENUM);
		assertEquals("One", titleBuilder.toString());
		
		titleBuilder.append(SEPARATOR,TitleBuilderTestEnum.TWO);
		assertEquals("One-Two", titleBuilder.toString());

		
		titleBuilder.append(SEPARATOR,TitleBuilderTestEnum.TWENTY_ONE);
		assertEquals("One-Two-Twenty one", titleBuilder.toString());
	}

	
	
	@Test
	public void contactText() {
		TitleBuilder titleBuilder = new TitleBuilder();
		titleBuilder.contact(EMPTY_STRING);
		assertEquals(EMPTY_STRING, titleBuilder.toString());
		
		titleBuilder.contact(NULL_STRING);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.contact(TEST);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.contact(EMPTY_STRING);
		assertEquals(TEST, titleBuilder.toString());
		
		titleBuilder.contact(NULL_STRING);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.contact(TEST);
		assertEquals(TEST+TEST, titleBuilder.toString());

	}

	@Test
	public void contactObject() {
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.contact(NULL_OBJECT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.contact(this);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.contact(NULL_OBJECT);
		assertEquals(TEST, titleBuilder.toString());

		titleBuilder.contact(this);
		assertEquals(TEST+TEST, titleBuilder.toString());

	}

	@Test
	public void contactDateWithFormatPattern() {
		Date date=new Date();
		date.setTime(0);
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.contact( NULL_DATE, DATE_FORMAT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.contact(date,DATE_FORMAT);
		assertEquals(FORMATED_DATE, titleBuilder.toString());

		titleBuilder.contact( NULL_DATE, DATE_FORMAT);
		assertEquals(FORMATED_DATE, titleBuilder.toString());

		titleBuilder.contact(date,DATE_FORMAT);
		assertEquals(FORMATED_DATE+FORMATED_DATE, titleBuilder.toString());
	}

	@Test
	public void contactNumberWithFormatPattern() {
		Double number=new Double(1.4567);
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.contact( NULL_NUMBER, NUMBER_FORMAT);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.contact(number,NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER, titleBuilder.toString());

		titleBuilder.contact( NULL_NUMBER, NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER, titleBuilder.toString());

		titleBuilder.contact(number,NUMBER_FORMAT);
		assertEquals(FORMATED_NUMBER + FORMATED_NUMBER, titleBuilder.toString());	
		}

	@Test
	public void contactEnumeration() {
		TitleBuilder titleBuilder=new TitleBuilder();
		titleBuilder.contact( NULL_ENUM);
		assertEquals(EMPTY_STRING, titleBuilder.toString());

		titleBuilder.contact(TitleBuilderTestEnum.ONE);
		assertEquals("One", titleBuilder.toString());

		titleBuilder.contact( NULL_ENUM);
		assertEquals("One", titleBuilder.toString());
		
		titleBuilder.contact(TitleBuilderTestEnum.TWO);
		assertEquals("OneTwo", titleBuilder.toString());

		
		titleBuilder.contact(TitleBuilderTestEnum.TWENTY_ONE);
		assertEquals("OneTwoTwenty one", titleBuilder.toString());
	}

	@Override
	public String toString() {
		return TEST;
	}

	
	
}
