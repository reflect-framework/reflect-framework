package nth.reflect.all.feature.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject.MyEnum;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.Disabled;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

/**
 * {@link DomainObject} class to test all the supported
 * {@link DomainObjectProperty} types where all properties are hidden
 * (visible=false)
 * 
 * @author nilsth
 *
 */
public class DomainObjectWithDisabledProperties extends AllFeatureDomainObject {

	@Order(value = 1)
	@Override
	public boolean isMyPrimitiveBoolean() {
		return super.isMyBoolean();
		/*
		 * TODO: BehavioralMethods have the following issue: When a getterMethod is in a
		 * super class and the BehavioralMethod is in a subclass it wont find the
		 * BehavioralMethod because the findFor(Method method) will use the
		 * declaringClass of the method to find the BehavioralMethod. This can be solved
		 * by overriding the getterMethod in the subclass but this is not so obvious.
		 * Better solution would be if pass the ownerClass as a parameter.
		 */
	}

	public boolean myBooleanDisabled() {
		return true;
	}

	@Override
	@Disabled
	public byte getMyPrimitiveByte() {
		return super.getMyPrimitiveByte();
	}

	@Override
	@Disabled
	public short getMyPrimitiveShort() {
		return super.getMyPrimitiveShort();
	}

	@Override
	@Disabled
	public int getMyPrimitiveInt() {
		return super.getMyPrimitiveInt();
	}

	@Override
	@Disabled
	public long getMyPrimitiveLong() {
		return super.getMyPrimitiveLong();
	}

	@Override
	@Disabled
	public float getMyPrimitiveFloat() {
		return super.getMyPrimitiveFloat();
	}

	@Disabled
	@Override
	public double getMyPrimitiveDouble() {
		return super.getMyPrimitiveDouble();
	}

	@Disabled
	@Override
	public char getMyPrimitiveChar() {
		return super.getMyPrimitiveChar();
	}

	@Disabled
	@Override
	public String getMyText() {
		return super.getMyText();
	}

	@Disabled
	@Override
	public String getMyTextArea() {
		return super.getMyTextArea();
	}

	@Disabled
	@Override
	public String getMyPassWord() {
		return super.getMyPassWord();
	}

	@Override
	@Disabled
	public Date getMyDate() {
		return super.getMyDate();
	}

	@Disabled
	@Override
	public Date getMyDateWithDateFormat() {
		return super.getMyDateWithTimeFormat();
	}

	@Disabled
	@Override
	public Date getMyDateWithDateTimeFormat() {
		return super.getMyDateWithDateTimeFormat();
	}

	@Disabled
	@Override
	public MyEnum getMyEnum() {
		return super.getMyEnum();
	}

	@Disabled
	@Override
	public Calendar getMyCalendar() {
		return super.getMyCalendar();
	}

	@Disabled
	@Override
	public BigDecimal getMyBigDecimal() {
		return super.getMyBigDecimal();
	}

	@Disabled
	@Override
	public BigInteger getMyBigInteger() {
		return super.getMyBigInteger();
	}

	@Disabled
	@Override
	public LocalDate getMyLocalDate() {
		return super.getMyLocalDate();
	}

	@Disabled
	@Override
	public LocalTime getMyLocalTime() {
		return super.getMyLocalTime();
	}

	@Disabled
	@Override
	public LocalDateTime getMyLocalDateTime() {
		return super.getMyLocalDateTime();
	}

}
