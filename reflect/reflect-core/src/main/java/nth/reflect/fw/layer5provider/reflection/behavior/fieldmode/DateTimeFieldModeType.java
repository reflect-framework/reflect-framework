package nth.reflect.fw.layer5provider.reflection.behavior.fieldmode;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public enum DateTimeFieldModeType {
	DATE, TIME, DATE_TIME;

	private static final String PLACE_HOLDERS_FOR_TIME = "aHkKhmsSzZX";
	private static final String PLACE_HOLDERS_FOR_DATE = "GyYMwWDdFEu";

	public static Optional<DateTimeFieldModeType> getFor(PropertyInfo propertyInfo) {
		Class<?> propertyType = propertyInfo.getTypeInfo().getType();
		if (LocalDate.class.isAssignableFrom(propertyType)) {
			return Optional.of(DATE);
		} else if (LocalTime.class.isAssignableFrom(propertyType)) {
			return Optional.of(TIME);
		} else if (LocalDateTime.class.isAssignableFrom(propertyType)) {
			return Optional.of(DATE_TIME);
		}

		if (propertyType.isAssignableFrom(Date.class) || propertyType.isAssignableFrom(Calendar.class)) {
			return Optional.of(getForDateOrCalendar(propertyInfo));
		}
		return Optional.empty();
	}

	private static DateTimeFieldModeType getForDateOrCalendar(PropertyInfo propertyInfo) {
		Optional<DateTimeFieldModeType> fromAnnotation = getFromAnnotation(propertyInfo);
		if (fromAnnotation.isPresent()) {
			return fromAnnotation.get();
		}

		Optional<DateTimeFieldModeType> fromFormatPattern = getFromFormatPattern(propertyInfo);
		if (fromFormatPattern.isPresent()) {
			return fromFormatPattern.get();
		}
		// no additional information for Date or Calendar type so assume DATE_TIME
		return DATE_TIME;
	}

	private static Optional<DateTimeFieldModeType> getFromFormatPattern(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		boolean hasDate = false;
		boolean hasTime = false;
		if (StringUtil.containsCharacters(formatPattern, PLACE_HOLDERS_FOR_DATE)) {
			hasDate = true;
		}
		if (StringUtil.containsCharacters(formatPattern, PLACE_HOLDERS_FOR_TIME)) {
			hasTime = true;
		}
		if (hasDate && hasTime) {
			return Optional.of(DATE_TIME);
		} else if (hasDate) {
			return Optional.of(DATE);
		} else if (hasTime) {
			return Optional.of(TIME);
		}
		return Optional.empty();
	}

	private static Optional<DateTimeFieldModeType> getFromAnnotation(PropertyInfo propertyInfo) {
		Method getterMethod = propertyInfo.getGetterMethod();
		DateTimeFieldMode dateTimeFieldMode = getterMethod.getAnnotation(DateTimeFieldMode.class);
		if (dateTimeFieldMode == null) {
			return Optional.empty();
		} else {
			return Optional.of(dateTimeFieldMode.mode());
		}
	}

}
