package nth.reflect.fw.layer3domain;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldMode;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldMode;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

/**
 * The contents of this class serves several purposes:
 * <ul>
 * <li>An example of a {@link FullFeatureDomainObject}</li>
 * <li>A show case of all types that are supported by the
 * {@link ReflectFramework} by default</li>
 * <li>A test class for (JUnit) testing</li>
 * </ul>
 * 
 * Note that the purpose of the public static final String fields is only for
 * Junit testing only. Normally you would not need them.
 */

public class FullFeatureDomainObject implements DomainObject {

	public static final Object NR_OF_PROPERTIES = 52;

	// Java Numbers
	private byte myPrimitiveByte;
	private Byte myByte;
	private short myPrimitiveShort;
	private Short myShort;
	private int myPrimitiveInt;
	private Integer myInteger;
	private long myPrimitiveLong;
	private Long myLong;
	private float myPrimitiveFloat;
	private Float myFloat;
	private double myPrimitiveDouble;
	private Double myDouble;
	private BigDecimal myBigDecimal;
	private BigInteger myBigInteger;

	// Java Date & Time
	private Date myDate;
	private Date myDateWithDateFormat;
	private Date myDateWithTimeFormat;
	private Date myDateWithDateTimeFormat;
	private Date myDateWithDateAnnotation;
	private Date myDateWithTimeAnnotation;
	private Date myDateWithDateTimeAnnotation;
	private Calendar myCalendar;
	private Calendar myCalendarWithDateFormat;
	private Calendar myCalendarWithTimeFormat;
	private Calendar myCalendarWithDateTimeFormat;
	private Calendar myCalendarWithDateAnnotation;
	private Calendar myCalendarWithTimeAnnotation;
	private Calendar myCalendarWithDateTimeAnnotation;
	private LocalDate myLocalDate;
	private LocalTime myLocalTime;
	private LocalDateTime myLocalDateTime;

	// Other Java Types
	private boolean myPrimitiveBoolean;
	private Boolean myBoolean;
	private char myPrimitiveChar;
	private Character myCharacter;
	private String myText;
	private String myTextArea;
	private String myPassWord;
	private String myChoice;
	private URI myUri;
	private URL myUrl;
	private File myFile;
	private Path myPath;

	// Arrays
	private int[] myPrimitiveIntArray;
	private String[] myStringArray;
	private FullFeatureDomainObject[] myDomainObjectArray;

	// Collections
	private Set<String> myStringSet = new HashSet<String>();
	private List<FullFeatureDomainObject> myDomainObjectList = new ArrayList<FullFeatureDomainObject>();

	// Domain
	private MyEnum myEnum;
	private FullFeatureDomainObject myDomainObject;
	private TranslatableString myTranslatableString;

	// annotated
	private Date myAnnotatedDate;

	public enum MyEnum {
		first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth
	}

	public static final String IS_MY_PRIMITIVE_BOOLEAN = "isMyPrimitiveBoolean";

	public boolean isMyPrimitiveBoolean() {
		return myPrimitiveBoolean;
	}

	public void setMyPrimitiveBoolean(boolean myPrimitiveBoolean) {
		this.myPrimitiveBoolean = myPrimitiveBoolean;
	}

	public static final String GET_MY_URI = "getMyUri";

	public URI getMyUri() {
		return myUri;
	}

	public void setMyUri(URI myUri) {
		this.myUri = myUri;
	}

	public static final String GET_MY_URL = "getMyUrl";

	public URL getMyUrl() {
		return myUrl;
	}

	public void setMyUrl(URL myUrl) {
		this.myUrl = myUrl;
	}

	public static final String GET_MY_FILE = "getMyFile";

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public static final String GET_MY_PATH = "getMyPath";

	public Path getMyPath() {
		return myPath;
	}

	public void setMyPath(Path myPath) {
		this.myPath = myPath;
	}

	public static final String GET_MY_PRIMITIVE_BYTE = "getMyPrimitiveByte";

	public byte getMyPrimitiveByte() {

		return myPrimitiveByte;
	}

	public void setMyPrimitiveByte(byte myPrimitiveByte) {
		this.myPrimitiveByte = myPrimitiveByte;
	}

	public static final String GET_MY_PRIMITIVE_SHORT = "getMyPrimitiveShort";

	public short getMyPrimitiveShort() {
		return myPrimitiveShort;
	}

	public void setMyPrimitiveShort(short myPrimitiveShort) {
		this.myPrimitiveShort = myPrimitiveShort;
	}

	public static final String GET_MY_PRIMITIVE_INT = "getMyPrimitiveInt";

	public int getMyPrimitiveInt() {
		return myPrimitiveInt;
	}

	public void setMyPrimitiveInt(int myPrimitiveInt) {
		this.myPrimitiveInt = myPrimitiveInt;
	}

	public static final String GET_MY_PRIMITIVE_LONG = "getMyPrimitiveLong";

	public long getMyPrimitiveLong() {
		return myPrimitiveLong;
	}

	public void setMyPrimitiveLong(long myPrimitiveLong) {
		this.myPrimitiveLong = myPrimitiveLong;
	}

	public static final String GET_MY_PRIMITIVE_FLOAT = "getMyPrimitiveFloat";

	public float getMyPrimitiveFloat() {
		return myPrimitiveFloat;
	}

	public void setMyPrimitiveFloat(float myPrimitiveFloat) {
		this.myPrimitiveFloat = myPrimitiveFloat;
	}

	public static final String GET_MY_PRIMITIVE_DOUBLE = "getMyPrimitiveDouble";

	public double getMyPrimitiveDouble() {
		return myPrimitiveDouble;
	}

	public void setMyPrimitiveDouble(double myPrimitiveDouble) {
		this.myPrimitiveDouble = myPrimitiveDouble;
	}

	public static final String GET_MY_PRIMITIVE_CHAR = "getMyPrimitiveChar";

	public char getMyPrimitiveChar() {
		return myPrimitiveChar;
	}

	public void setMyPrimitiveChar(char myPrimitiveChar) {
		this.myPrimitiveChar = myPrimitiveChar;
	}

	public static final String IS_MY_BOOLEAN = "isMyBoolean";

	public Boolean isMyBoolean() {
		return myBoolean;
	}

	public void setMyBoolean(Boolean myBoolean) {
		this.myBoolean = myBoolean;
	}

	public static final String GET_MY_BYTE = "getMyByte";

	public Byte getMyByte() {
		return myByte;
	}

	public void setMyByte(Byte myByte) {
		this.myByte = myByte;
	}

	public static final String GET_MY_SHORT = "getMyShort";

	public Short getMyShort() {
		return myShort;
	}

	public void setMyShort(Short myShort) {
		this.myShort = myShort;
	}

	public static final String GET_MY_INTEGER = "getMyInteger";

	public Integer getMyInteger() {
		return myInteger;
	}

	public void setMyInteger(Integer myInteger) {
		this.myInteger = myInteger;
	}

	public static final String GET_MY_LONG = "getMyLong";

	public Long getMyLong() {
		return myLong;
	}

	public void setMyLong(Long myLong) {
		this.myLong = myLong;
	}

	public static final String GET_MY_FLOAT = "getMyFloat";

	public Float getMyFloat() {
		return myFloat;
	}

	public void setMyFloat(Float myFloat) {
		this.myFloat = myFloat;
	}

	public static final String GET_MY_DOUBLE = "getMyDouble";

	public Double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(Double myDouble) {
		this.myDouble = myDouble;
	}

	public static final String GET_MY_CHARACTER = "getMyCharacter";

	public Character getMyCharacter() {
		return myCharacter;
	}

	public void setMyCharacter(Character myCharacter) {
		this.myCharacter = myCharacter;
	}

	public static final String GET_MY_TEXT = "getMyText";

	public String getMyText() {
		return myText;
	}

	public void setMyText(String myText) {
		this.myText = myText;
	}

	public static final String GET_MY_TEXT_AREA = "getMyTextArea";

	@TextFieldMode(mode = TextFieldModeType.MILTI_LINE)
	public String getMyTextArea() {
		return myTextArea;
	}

	public void setMyTextArea(String myTextArea) {
		this.myTextArea = myTextArea;
	}

	public static final String GET_MY_PASSWORD = "getMyPassWord";

	@TextFieldMode(mode = TextFieldModeType.PASSWORD)
	public String getMyPassWord() {
		return myPassWord;
	}

	public void setMyPassWord(String myPassWord) {
		this.myPassWord = myPassWord;
	}

	public static final String GET_MY_DATE = "getMyDate";

	public Date getMyDate() {
		return myDate;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	public static final String GET_MY_DATE_WITH_DATE_FORMAT = "getMyDateWithDateFormat";

	@Format(pattern = "dd-MM-yyyy")
	public Date getMyDateWithDateFormat() {
		return myDateWithDateFormat;
	}

	public void setMyDateWithDateFormat(Date myDateWithDateFormat) {
		this.myDateWithDateFormat = myDateWithDateFormat;
	}

	public static final String GET_MY_DATE_WITH_TIME_FORMAT = "getMyDateWithTimeFormat";

	@Format(pattern = "HH:mm:ss")
	public Date getMyDateWithTimeFormat() {
		return myDateWithTimeFormat;
	}

	public void setMyDateWithTimeFormat(Date myDateWithTimeFormat) {
		this.myDateWithTimeFormat = myDateWithTimeFormat;
	}

	public static final String GET_MY_DATE_WITH_DATE_TIME_FORMAT = "getMyDateWithDateTimeFormat";

	@Format(pattern = "dd-MM-yyyy HH:mm:ss")
	public Date getMyDateWithDateTimeFormat() {
		return myDateWithDateTimeFormat;
	}

	public void setMyDateWithDateTimeFormat(Date myDateWithDateTimeFormat) {
		this.myDateWithDateTimeFormat = myDateWithDateTimeFormat;
	}

	public static final String GET_MY_DATE_WITH_DATE_ANNOTATION = "getMyDateWithDateAnnotation";

	@DateTimeFieldMode(mode = DateTimeFieldModeType.DATE)
	public Date getMyDateWithDateAnnotation() {
		return myDateWithDateAnnotation;
	}

	public void setMyDateWithDateAnnotation(Date myDateWithDateAnnotation) {
		this.myDateWithDateAnnotation = myDateWithDateAnnotation;
	}

	public static final String GET_MY_DATE_WITH_TIME_ANNOTATION = "getMyDateWithTimeAnnotation";

	@DateTimeFieldMode(mode = DateTimeFieldModeType.TIME)
	public Date getMyDateWithTimeAnnotation() {
		return myDateWithTimeAnnotation;
	}

	public void setMyDateWithTimeAnnotation(Date myDateWithTimeAnnotation) {
		this.myDateWithTimeAnnotation = myDateWithTimeAnnotation;
	}

	public static final String GET_MY_DATE_WITH_DATE_TIME_ANNOTATION = "getMyDateWithDateTimeAnnotation";

	@DateTimeFieldMode(mode = DateTimeFieldModeType.DATE_TIME)
	public Date getMyDateWithDateTimeAnnotation() {
		return myDateWithDateTimeAnnotation;
	}

	public void setMyDateWithDateTimeAnnotation(Date myDateWithDateTimeAnnotation) {
		this.myDateWithDateTimeAnnotation = myDateWithDateTimeAnnotation;
	}

	public static final String GET_MY_CALENDAR_WITH_DATE_FORMAT = "getMyCalendarWithDateFormat";

	@Format(pattern = "dd-MM-yyyy")
	public Calendar getMyCalendarWithDateFormat() {
		return myCalendarWithDateFormat;
	}

	public void setMyCalendarWithDateFormat(Calendar myCalendarWithDateFormat) {
		this.myCalendarWithDateFormat = myCalendarWithDateFormat;
	}

	public static final String GET_MY_CALENDAR_WITH_TIME_FORMAT = "getMyCalendarWithTimeFormat";

	@Format(pattern = "HH:mm:ss")
	public Calendar getMyCalendarWithTimeFormat() {
		return myCalendarWithTimeFormat;
	}

	public void setMyCalendarWithTimeFormat(Calendar myCalendarWithTimeFormat) {
		this.myCalendarWithTimeFormat = myCalendarWithTimeFormat;
	}

	public static final String GET_MY_CALENDAR_WITH_DATE_TIME_FORMAT = "getMyCalendarWithDateTimeFormat";

	@Format(pattern = "dd-MM-yyyy HH:mm:ss")
	public Calendar getMyCalendarWithDateTimeFormat() {
		return myCalendarWithDateTimeFormat;
	}

	public void setMyCalendarWithDateTimeFormat(Calendar myCalendarWithDateTimeFormat) {
		this.myCalendarWithDateTimeFormat = myCalendarWithDateTimeFormat;
	}

	public static final String GET_MY_CALENDAR = "getMyCalendar";

	public Calendar getMyCalendar() {
		return myCalendar;
	}

	public void setMyCalendar(Calendar myCalendar) {
		this.myCalendar = myCalendar;
	}

	public static final String GET_MY_CALENDAR_WITH_DATE_ANNOTATION = "getMyCalendarWithDateAnnotation";

	@DateTimeFieldMode(mode = DateTimeFieldModeType.DATE)
	public Calendar getMyCalendarWithDateAnnotation() {
		return myCalendarWithDateAnnotation;
	}

	public void setMyCalendarWithDateAnnotation(Calendar myCalendarWithDateAnnotation) {
		this.myCalendarWithDateAnnotation = myCalendarWithDateAnnotation;
	}

	public static final String GET_MY_CALENDAR_WITH_TIME_ANNOTATION = "getMyCalendarWithTimeAnnotation";

	@DateTimeFieldMode(mode = DateTimeFieldModeType.TIME)
	public Calendar getMyCalendarWithTimeAnnotation() {
		return myCalendarWithTimeAnnotation;
	}

	public void setMyCalendarWithTimeAnnotation(Calendar myCalendarWithTimeAnnotation) {
		this.myCalendarWithTimeAnnotation = myCalendarWithTimeAnnotation;
	}

	public static final String GET_MY_CALENDAR_WITH_DATE_TIME_ANNOTATION = "getMyCalendarWithDateTimeAnnotation";

	@DateTimeFieldMode(mode = DateTimeFieldModeType.DATE_TIME)
	public Calendar getMyCalendarWithDateTimeAnnotation() {
		return myCalendarWithDateTimeAnnotation;
	}

	public void setMyCalendarWithDateTimeAnnotation(Calendar myCalendarWithDateTimeAnnotation) {
		this.myCalendarWithDateTimeAnnotation = myCalendarWithDateTimeAnnotation;
	}

	public static final String GET_MY_ENUM = "getMyEnum";

	public MyEnum getMyEnum() {
		return myEnum;
	}

	public void setMyEnum(MyEnum myEnum) {
		this.myEnum = myEnum;
	}

	public static final String GET_MY_BIG_DECIMAL = "getMyBigDecimal";

	public BigDecimal getMyBigDecimal() {
		return myBigDecimal;
	}

	public void setMyBigDecimal(BigDecimal myBigDecimal) {
		this.myBigDecimal = myBigDecimal;
	}

	public static final String GET_MY_BIG_INTEGER = "getMyBigInteger";

	public BigInteger getMyBigInteger() {
		return myBigInteger;
	}

	public void setMyBigInteger(BigInteger myBigInteger) {
		this.myBigInteger = myBigInteger;
	}

	public static final String GET_MY_LOCAL_DATE = "getMyLocalDate";

	public LocalDate getMyLocalDate() {
		return myLocalDate;
	}

	public void setMyLocalDate(LocalDate myLocalDate) {
		this.myLocalDate = myLocalDate;
	}

	public static final String GET_MY_LOCAL_TIME = "getMyLocalTime";

	public LocalTime getMyLocalTime() {
		return myLocalTime;
	}

	public void setMyLocalTime(LocalTime myLocalTime) {
		this.myLocalTime = myLocalTime;
	}

	public static final String GET_MY_LOCAL_DATE_TIME = "getMyLocalDateTime";

	public LocalDateTime getMyLocalDateTime() {
		return myLocalDateTime;
	}

	public void setMyLocalDateTime(LocalDateTime myLocalDateTime) {
		this.myLocalDateTime = myLocalDateTime;
	}

	public static final String GET_MY_CHOICE = "getMyChoice";

	public String getMyChoice() {
		return myChoice;
	}

	public void setMyChoice(String myChoice) {
		this.myChoice = myChoice;
	}

	public List<String> myChoiceOptions() {
		List<String> options = Arrays.stream(MyEnum.values()).map(Enum::name).collect(Collectors.toList());
		return options;
	}

	public static final String GET_MY_PRIMITIVE_INT_ARRAY = "getMyPrimitiveIntArray";

	public int[] getMyPrimitiveIntArray() {
		return myPrimitiveIntArray;
	}

	public void setMyPrimitiveIntArray(int[] myPrimitiveIntArray) {
		this.myPrimitiveIntArray = myPrimitiveIntArray;
	}

	public static final String GET_MY_STRING_ARRAY = "getMyStringArray";

	public String[] getMyStringArray() {
		return myStringArray;
	}

	public void setMyStringArray(String[] myStringArray) {
		this.myStringArray = myStringArray;
	}

	public static final String GET_MY_DOMAIN_OBJECT_ARRAY = "getMyDomainObjectArray";

	public FullFeatureDomainObject[] getMyDomainObjectArray() {
		return myDomainObjectArray;
	}

	public void setMyDomainObjectArray(FullFeatureDomainObject[] myDomainObjectArray) {
		this.myDomainObjectArray = myDomainObjectArray;
	}

	public static final String GET_MY_STRING_SET = "getMyStringSet";

	public Set<String> getMyStringSet() {
		return myStringSet;
	}

	public void setMyStringSet(Set<String> myStringSet) {
		this.myStringSet = myStringSet;
	}

	public static final String GET_MY_DOMAIN_OBJECT_LIST = "getMyDomainObjectList";

	public List<FullFeatureDomainObject> getMyDomainObjectList() {
		return myDomainObjectList;
	}

	public void setMyDomainObjectList(List<FullFeatureDomainObject> myDomainObjectList) {
		this.myDomainObjectList = myDomainObjectList;
	}

	public static final String MY_DOMAIN_OBJECT_LIST_ADD = "myDomainObjectListAdd";

	@ParameterFactory
	public void myDomainObjectListAdd(FullFeatureDomainObject domainObject) {
		myDomainObjectList.add(domainObject);
	}

	public static final String ACTION_METHOD = "actionMethod";

	@ParameterFactory
	public void actionMethod(FullFeatureDomainObject domainObject) {
		myDomainObjectList.add(domainObject);
	}

	public static final String GET_MY_DOMAIN_OBJECT = "getMyDomainObject";

	public FullFeatureDomainObject getMyDomainObject() {
		return myDomainObject;
	}

	public void setMyDomainObject(FullFeatureDomainObject myDomainObject) {
		this.myDomainObject = myDomainObject;
	}

	public static final String GET_MY_TRANSLATABLE_STRING = "getMyTranslatableString";

	public TranslatableString getMyTranslatableString() {
		return myTranslatableString;
	}

	public void setMyTranslatableString(TranslatableString myTranslatableString) {
		this.myTranslatableString = myTranslatableString;
	}

	public static final String GET_MY_ANNOTATED_DATE = "getMyAnnotatedDate";
	public static final double GET_MY_ANNOTATED_DATE_ORDER = 12;

	@Order(GET_MY_ANNOTATED_DATE_ORDER)
	@Hidden(propertyHiddenFor = HiddenFor.TABLES_AND_FORMS)
	public Date getMyAnnotatedDate() {
		return myAnnotatedDate;
	}

	public void setMyAnnotatedDate(Date myAnnotatedDate) {
		this.myAnnotatedDate = myAnnotatedDate;
	}

	public static final String GET_MY_ANNOTATED_DATE_TODAY = "myAnnotatedDateToday";

	public void myAnnotatedDateToday() {
		this.myAnnotatedDate = new Date();
	}

	@Override
	public String toString() {
		return new TitleBuilder()
				.append(FullFeatureDomainObject.class.getSimpleName())
				.append(myPrimitiveInt)
				.toString();
	}

}
