package nth.introspect.domain.test;

import java.util.Date;

import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FieldMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.Format;

public class Test {
	private boolean myBoolean;
	private Test myTest;
	private byte myByte;
	private short myShort;
	private int myInt;
	private long myLong;
	private float myFloat;
	private double myDouble;
	private char myChar;
	private String myText;
	private String myTextArea;
	private String myPassWord;
	private Date myDate;
	private Date myTime;
	private Date myDateTime;

	private enum PickOrder {
		first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth
	}
	private PickOrder myEnum;

	public boolean isMyBoolean() {
		return myBoolean;
	}

	public void setMyBoolean(boolean myBoolean) {
		this.myBoolean = myBoolean;
	}

	@Format("00")
	public byte getMyByte() {
		return myByte;
	}

	public void setMyByte(byte myByte) {
		this.myByte = myByte;
	}

	public short getMyShort() {
		return myShort;
	}

	public void setMyShort(short myShort) {
		this.myShort = myShort;
	}

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public long getMyLong() {
		return myLong;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public float getMyFloat() {
		return myFloat;
	}

	public void setMyFloat(float myFloat) {
		this.myFloat = myFloat;
	}

	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public char getMyChar() {
		return myChar;
	}

	public void setMyChar(char myChar) {
		this.myChar = myChar;
	}

	public String getMyText() {
		return myText;
	}

	public void setMyText(String myText) {
		this.myText = myText;
	}

	@FieldMode(FieldModeType.TEXT_AREA)
	public String getMyTextArea() {
		return myTextArea;
	}

	public void setMyTextArea(String myTextArea) {
		this.myTextArea = myTextArea;
	}

	@FieldMode(FieldModeType.PASSWORD)
	public String getMyPassWord() {
		return myPassWord;
	}

	public void setMyPassWord(String myPassWord) {
		this.myPassWord = myPassWord;
	}

	public Date getMyDate() {
		return myDate;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	@FieldMode(FieldModeType.TIME)
	public Date getMyTime() {
		return myTime;
	}

	public void setMyTime(Date myTime) {
		this.myTime = myTime;
	}

	@FieldMode(FieldModeType.DATE_TIME)
	public Date getMyDateTime() {
		return myDateTime;
	}

	public void setMyDateTime(Date myDateTime) {
		this.myDateTime = myDateTime;
	}

	public PickOrder getMyEnum() {
		return myEnum;
	}

	public void setMyEnum(PickOrder myEnum) {
		this.myEnum = myEnum;
	}

	@FieldMode(FieldModeType.ONE_TO_ONE_OR_MANY)
	public Test getMyTest() {
		return myTest;
	}

	public void setMyTest(Test myTest) {
		this.myTest = myTest;
	}

}
