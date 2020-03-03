package nth.reflect.fw.layer3domain;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.behavior.ObjectBehavior;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldMode;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldMode;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.format.Format;

/**
 * <p>
 * {@link DomainObject}s represent entities; the nouns of the domain. If your
 * application domain is a sales application it’s likely that your domain model
 * contains {@link DomainObject}s such as: customers, products and orders.
 * </p>
 * <p>
 * {@link DomainObject}s are created by a developer or are reused from an
 * existing application that needs to be re-written. They can be created from
 * scratch or generated from a schema (in example from a
 * <a href="http://en.wikipedia.org/wiki/Database_schema">database schema</a>,
 * <a href="http://nl.wikipedia.org/wiki/XML_Schema">XML schema</a> or
 * <a href="http://en.wikipedia.org/wiki/Web_Services_Description_Language">web
 * service</a>)
 * </p>
 * 
 * <h3>Naming</h3>
 * <p>
 * {@link DomainObject}s names are nouns, such as customer, product and order.
 * They basically describe the things that are important in your application.
 * {@link DomainObject}s names are part of the
 * <a href="http://martinfowler.com/bliki/UbiquitousLanguage.html">Ubiquitous
 * Language</a>: in terms understood by both users and developers.
 * </p>
 * 
 * <h3>Presentation</h3>
 * <p>
 * An {@link UserInterfaceController} can display domain objects in 3 ways:<br>
 * <ul>
 * <li>Domain object as form:<br>
 * <img src="ObjectAsForm.png"></li>
 * <li>Domain object as a field in a form:<br>
 * <img src="ObjectAsField.png"></li>
 * <li>Domain object as a row in a table:<br>
 * <img src="ObjectAsTable.png"></li>
 * </ul>
 * </p>
 *
 * <h3>Construction</h3>
 * <p>
 * The principle of “naked objects” is that any
 * <a href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java
 * Object' (POJO)</a> can function as a domain object. In other words: a domain
 * class does not have to inherit from any special class, nor implement any
 * particular interface, nor have any specific attributes.
 * </p>
 *
 * {@link DomainObject}s can be created by different objects e.g.:
 * <ul>
 * <li>By a {@link ServiceObjectActionMethod} such as
 * customerService.createNewCustomer()
 * <li>By a {@link DomainObjectActionMethod} such as
 * shoppingCart.lineItemCreate(LineItem lineItem)</li>
 * <li>By a {@link DomainContainer} such as
 * domainContainer.getObject(Customer.class)</li>
 * <li>By a {@link InfrastructureObject} method such as
 * orderRepository.allOpenOrders() or
 * shoppingCartFactory.createForCustomer(Customer customer).</li>
 * </ul>
 * <br>
 * There are 2 ways to create new {@link DomainObject}s:
 * <ul>
 * <li>Creating a new {@link DomainObject} with the new keyword:<br>
 * In example: PropertyActionMethod order=new PropertyActionMethod()</li>
 * <li>Creating a domain object using Dependency Injection:<br>
 * Sometimes you want a new {@link DomainObject} to have references to other
 * objects (being other {@link DomainObject}s, {@link InfrastructureObject}s or
 * {@link Provider}Objects). In example: A Customer object needs a references to
 * a ShoppingCartFactory object. The Customer object can therefore be created by
 * the {@link DomainContainer} with Customer customer=(Customer)
 * domainContainer.getObject(Customer.class). The ShopingCart object will
 * automatically be injected as a constructor parameter of the Customer class.
 * In order to create {@link DomainObject}s using dependency injection you need
 * to:</li>
 * <ul>
 * <li>Add the reference object as a parameter in the constructor and link it to
 * a private field, so that it can be used throughout the class.</li>
 * <li>Make sure that the referenced object type is known by the
 * {@link ReflectFramework}: Its class must be returned by the
 * {@link ReflectApplication#getDomainClasses()} method.</li>
 * <li>The object that creates the {@link DomainObject} needs to have a
 * reference to the {@link DomainContainer} as a constructor parameter, so that
 * that the {@link DomainContainer#get(Class)} method can be called to get or
 * create the {@link DomainObject} with the required dependencies injected by
 * the {@link ReflectFramework}</li>
 * </ul>
 * </ul>
 * 
 * <h3>Domain object members</h3>
 * <p>
 * Domain objects contain:
 * <ul>
 * <li>Getter and setter methods (and possible fields): that define <a href=
 * "http://en.wikipedia.org/wiki/Property_(programming)">properties</a></li>
 * <li>{@link DomainObjectActionMethod}s: that define user actions</li>
 * <li>Methods: that define {@link ObjectBehavior}</li>
 * <li>Annotations: that define {@link ObjectBehavior}</li>
 * </ul>
 * These members are discussed in more detail in the following paragraphs.
 * </p>
 * 
 * <h2>Properties</h2> {@insert DomainObjectProperty}
 * 
 * <h2>Action Methods</h2>
 * <p>
 * Domain objects can contain action methods (see
 * {@link DomainObjectActionMethod} section)
 * </p>
 * 
 * @author Nils ten Hoeve
 * 
 */

public class DomainObject {

	/**
	 * The contents of this class serves several purposes:
	 * <ul>
	 * <li>An example of a {@link DomainObject}</li>
	 * <li>A show case of all types that are supported by the
	 * {@link ReflectFramework} by default</li>
	 * <li>A test class for JUnit testing</li>
	 * </ul>
	 * 
	 * Note that the purpose of the public static final String fields is only for
	 * Junit testing only. Normally you would not need them.
	 */

	// private DomainObject myDomainObject;//TODO this will cause an infinite loop
	// in the ReflectionProvider
	private boolean myPrimitiveBoolean;
	private byte myPrimitiveByte;
	private short myPrimitiveShort;
	private int myPrimitiveInt;
	private long myPrimitiveLong;
	private float myPrimitiveFloat;
	private double myPrimitiveDouble;
	private char myPrimitiveChar;
	private int[] myPrimitiveIntArray;
	private String[] myStringArray;
	private DomainObject[] myDomainObjectArray;
	private Set<String> myStringSet = new HashSet<String>();
	private List<DomainObject> myDomainObjectList = new ArrayList<DomainObject>();
	private Boolean myBoolean;
	private Byte myByte;
	private Short myShort;
	private Integer myInteger;
	private Long myLong;
	private Float myFloat;
	private Double myDouble;
	private Character myCharacter;
	private AtomicInteger myAtomicInteger;
	private AtomicLong myAttomicLong;
	private BigDecimal myBigDecimal;
	private BigInteger myBigInteger;
	private String myText;
	private String myTextArea;
	private String myPassWord;
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
	private MyEnum myEnum;
	private String myChoice;
	private DomainObject myDomainObject;

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

	public long getMyPrimitiveLong() {
		return myPrimitiveLong;
	}

	public void setMyPrimitiveLong(long myPrimitiveLong) {
		this.myPrimitiveLong = myPrimitiveLong;
	}

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

	public Long getMyLong() {
		return myLong;
	}

	public void setMyLong(Long myLong) {
		this.myLong = myLong;
	}

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

	public AtomicInteger getMyAtomicInteger() {
		return myAtomicInteger;
	}

	public void setMyAtomicInteger(AtomicInteger myAtomicInteger) {
		this.myAtomicInteger = myAtomicInteger;
	}

	public AtomicLong getMyAttomicLong() {
		return myAttomicLong;
	}

	public void setMyAttomicLong(AtomicLong myAttomicLong) {
		this.myAttomicLong = myAttomicLong;
	}

	public BigDecimal getMyBigDecimal() {
		return myBigDecimal;
	}

	public void setMyBigDecimal(BigDecimal myBigDecimal) {
		this.myBigDecimal = myBigDecimal;
	}

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

	public DomainObject[] getMyDomainObjectArray() {
		return myDomainObjectArray;
	}

	public void setMyDomainObjectArray(DomainObject[] myDomainObjectArray) {
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

	public List<DomainObject> getMyDomainObjectList() {
		return myDomainObjectList;
	}

	public void setMyDomainObjectList(List<DomainObject> myDomainObjectList) {
		this.myDomainObjectList = myDomainObjectList;
	}

	public static final String GET_MY_DOMAIN_OBJECT = "getMyDomainObject";

	public DomainObject getMyDomainObject() {
		return myDomainObject;
	}

	public void setMyDomainObject(DomainObject myDomainObject) {
		this.myDomainObject = myDomainObject;
	}

	@Override
	public String toString() {
		return new TitleBuilder().append(DomainObject.class.getSimpleName()).append(myPrimitiveInt).toString();
	}
}
