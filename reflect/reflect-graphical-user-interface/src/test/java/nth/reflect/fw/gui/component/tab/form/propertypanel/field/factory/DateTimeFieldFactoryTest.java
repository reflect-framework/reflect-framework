package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class DateTimeFieldFactoryTest extends FieldFactoryTest {

	private DateTimeFieldFactory dateTimeFieldFactory;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		dateTimeFieldFactory = createFieldFactory();

	}

	protected DateTimeFieldFactory createFieldFactory() {
		return new DateTimeFieldFactory() {
			@Override
			public Optional<PropertyField> create(PropertyFieldFactoryInfo makeInformation) {
				return Optional.empty();
			}
		};
	}

	private void assertGetDateTimeFieldModeType(String getterMethodName, Optional<DateTimeFieldModeType> expectedMode) {
		PropertyInfo propertyInfo = createPropertyInfo(getterMethodName);
		Optional<DateTimeFieldModeType> result = dateTimeFieldFactory.getDateTimeFieldModeType(propertyInfo);
		assertThat(result)
				.describedAs("%s.getDateTimeFieldModeType(PropertyInfo for %s.%s) resulted in a incorrect return value",
						DateTimeFieldFactory.class.getSimpleName(), DomainObject.class.getSimpleName(),
						getterMethodName)
				.isEqualTo(expectedMode);
	}

	
	// =========================== WRONG TYPE =================================
	@Test
	public void testCanCreate_givenWrongType_thenReturnFalse() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_TEXT, false);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenWrongType_thenReturnOptionalEmpty() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_TEXT, Optional.empty());
	}

	// ====================== LOCAL DATE - TIME ============================

	@Test
	public void testCanCreate_givenLocalDate_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_LOCAL_DATE, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenLocalDate_thenReturnOptionalDate() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_LOCAL_DATE, Optional.of(DateTimeFieldModeType.DATE));
	}

	@Test
	public void testCanCreate_givenLocalTime_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_LOCAL_TIME, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenLocalTime_thenReturnOptionalTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_LOCAL_TIME, Optional.of(DateTimeFieldModeType.TIME));
	}

	@Test
	public void testCanCreate_givenLocalDateTime_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_LOCAL_DATE_TIME, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenLocalDateTime_thenReturnOptionalDateTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_LOCAL_DATE_TIME,
				Optional.of(DateTimeFieldModeType.DATE_TIME));
	}

	// =========================== DATE =================================
	@Test
	public void testCanCreate_givenDate_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_DATE, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenDate_thenReturnOptionalDateTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_DATE, Optional.of(DateTimeFieldModeType.DATE_TIME));
	}

	@Test
	public void testCanCreate_givenDateWithDateFormat_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_DATE_WITH_DATE_FORMAT, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenDateWithDateFormat_thenReturnOptionalDate() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_DATE_WITH_DATE_FORMAT,
				Optional.of(DateTimeFieldModeType.DATE));
	}

	@Test
	public void testCanCreate_givenDateWithTimeFormat_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_DATE_WITH_TIME_FORMAT, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenDateWithTimeFormat_thenReturnOptionalTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_DATE_WITH_TIME_FORMAT,
				Optional.of(DateTimeFieldModeType.TIME));
	}

	@Test
	public void testCanCreate_givenDateWithDateTimeFormat_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_DATE_WITH_DATE_TIME_FORMAT, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenDateWithDateTimeFormat_thenReturnOptionalDateTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_DATE_WITH_DATE_TIME_FORMAT,
				Optional.of(DateTimeFieldModeType.DATE_TIME));
	}

	@Test
	public void testCanCreate_givenDateWithDateAnnotation_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_DATE_WITH_DATE_ANNOTATION, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenDateWithDateAnnotation_thenReturnOptionalDate() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_DATE_WITH_DATE_ANNOTATION,
				Optional.of(DateTimeFieldModeType.DATE));
	}

	@Test
	public void testCanCreate_givenDateWithTimeAnnotation_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_DATE_WITH_TIME_ANNOTATION, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenDateWithTimeAnnotation_thenReturnOptionalTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_DATE_WITH_TIME_ANNOTATION,
				Optional.of(DateTimeFieldModeType.TIME));
	}

	@Test
	public void testCanCreate_givenDateWithDateTimeAnnotation_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_DATE_WITH_DATE_TIME_ANNOTATION, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenDateWithDateTimeAnnotation_thenReturnOptionalDateTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_DATE_WITH_DATE_TIME_ANNOTATION,
				Optional.of(DateTimeFieldModeType.DATE_TIME));
	}

	// =========================== CALENDAR =================================

	@Test
	public void testCanCreate_givenCalendar_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_CALENDAR, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenCalendar_thenReturnOptionalDateTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_CALENDAR, Optional.of(DateTimeFieldModeType.DATE_TIME));
	}

	@Test
	public void testCanCreate_givenCalendarWithDateFormat_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_CALENDAR_WITH_DATE_FORMAT, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenCalendarWithDateFormat_thenReturnOptionalDate() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_CALENDAR_WITH_DATE_FORMAT,
				Optional.of(DateTimeFieldModeType.DATE));
	}

	@Test
	public void testCanCreate_givenCalendarWithTimeFormat_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_CALENDAR_WITH_TIME_FORMAT, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenCalendarWithTimeFormat_thenReturnOptionalTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_CALENDAR_WITH_TIME_FORMAT,
				Optional.of(DateTimeFieldModeType.TIME));
	}

	@Test
	public void testCanCreate_givenCalendarWithDateTimeFormat_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_FORMAT, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenCalendarWithDateTimeFormat_thenReturnOptionalDateTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_FORMAT,
				Optional.of(DateTimeFieldModeType.DATE_TIME));
	}

	@Test
	public void testCanCreate_givenCalendarWithDateAnnotation_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_CALENDAR_WITH_DATE_ANNOTATION, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenCalendarWithDateAnnotation_thenReturnOptionalDate() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_CALENDAR_WITH_DATE_ANNOTATION,
				Optional.of(DateTimeFieldModeType.DATE));
	}

	@Test
	public void testCanCreate_givenCalendarWithTimeAnnotation_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_CALENDAR_WITH_TIME_ANNOTATION, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenCalendarWithTimeAnnotation_thenReturnOptionalTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_CALENDAR_WITH_TIME_ANNOTATION,
				Optional.of(DateTimeFieldModeType.TIME));
	}

	@Test
	public void testCanCreate_givenCalendarWithDateTimeAnnotation_thenReturnTrue() {
		assertCanCreate(dateTimeFieldFactory,DomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_ANNOTATION, true);
	}

	@Test
	public void testGetDateTimeFieldModeType_givenCalendarWithDateTimeAnnotation_thenReturnOptionalDateTime() {
		assertGetDateTimeFieldModeType(DomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_ANNOTATION,
				Optional.of(DateTimeFieldModeType.DATE_TIME));
	}

}
