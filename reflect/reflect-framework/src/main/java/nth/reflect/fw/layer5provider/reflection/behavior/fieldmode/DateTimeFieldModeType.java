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

/**
 * Supports:
 * <ul>
 * <li>{@link java.util.Date}</li>
 * <li>{@link java.util.Calendar}</li>
 * <li>{@link java.time.LocalDate}</li>
 * <li>{@link java.time.LocalTime}</li>
 * <li>{@link java.time.LocalDateTime}</li>
 * </ul>
 * 
 * @author nilsth
 *
 */
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

		DateTimeFieldModeType fromFormatPattern = getFromFormatPattern(propertyInfo);
		return fromFormatPattern;
	}

	private static DateTimeFieldModeType getFromFormatPattern(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		if (formatPattern == null) {
			return DATE_TIME;
		}
		boolean hasDate = false;
		boolean hasTime = false;
		if (StringUtil.containsCharacters(formatPattern, PLACE_HOLDERS_FOR_DATE)) {
			hasDate = true;
		}
		if (StringUtil.containsCharacters(formatPattern, PLACE_HOLDERS_FOR_TIME)) {
			hasTime = true;
		}
		if (hasDate && hasTime) {
			return DATE_TIME;
		} else if (hasDate) {
			return DATE;
		} else if (hasTime) {
			return TIME;
		}
		return DATE_TIME;
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
