package nth.reflect.all.feature.domain;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject.MyEnum;

public class DomainObjectRandomGenerator extends RandomGenerator<AllFeatureDomainObject> {

	@Override
	public AllFeatureDomainObject generate() {
		AllFeatureDomainObject domainObject = new AllFeatureDomainObject();
		// Numbers
		domainObject.setMyBigDecimal(Random.bigDecimal().generate());
		domainObject.setMyBigInteger(Random.bigInteger().generate());

		domainObject.setMyPrimitiveByte(Random.byte_().generate());
		domainObject.setMyByte(Random.byte_().generate());

		domainObject.setMyPrimitiveDouble(Random.double_().generate());
		domainObject.setMyDouble(Random.double_().generate());

		domainObject.setMyPrimitiveFloat(Random.float_().generate());
		domainObject.setMyFloat(Random.float_().generate());

		domainObject.setMyPrimitiveInt(Random.integer().generate());
		domainObject.setMyInteger(Random.integer().generate());

		domainObject.setMyPrimitiveLong(Random.long_().generate());
		domainObject.setMyLong(Random.long_().generate());

		domainObject.setMyPrimitiveShort(Random.short_().generate());
		domainObject.setMyShort(Random.short_().generate());

		// Other Java
		domainObject.setMyPrimitiveBoolean(Random.bool().generate());
		domainObject.setMyBoolean(Random.bool().generate());
		domainObject.setMyPrimitiveChar(Random.character().generate());
		domainObject.setMyCharacter(Random.character().generate());
		domainObject.setMyText(Random.sentence().generate());
		domainObject.setMyTextArea(Random.chapter().generate());
		domainObject.setMyPassWord(Random.word().generate());
		domainObject.setMyChoice(Random.word().generate());
		domainObject.setMyFile(new File("c:/" + Random.word().generate() + ".txt"));
		domainObject.setMyPath(Paths.get("c:/" + Random.word().generate() + ".txt"));
		try {
			URI uri = new URI("mailto:" + Random.emailAddress().generate());
			domainObject.setMyUri(uri);
		} catch (URISyntaxException e) {
		}
		try {
			URL url = new URL("https://" + Random.companyName().generate().replace(" ", "_") + ".com");
			domainObject.setMyUrl(url);
		} catch (Exception e) {
		}

		// Local Date Time
		domainObject.setMyLocalDate(Random.localDate().generate());
		domainObject.setMyLocalTime(Random.localTime().generate());
		domainObject.setMyLocalDateTime(Random.localDateTime().generate());

		// Date
		domainObject.setMyDate(Random.date().generate());
		domainObject.setMyDateWithDateFormat(Random.date().generate());
		domainObject.setMyDateWithTimeFormat(Random.date().generate());
		domainObject.setMyDateWithDateTimeFormat(Random.date().generate());
		domainObject.setMyDateWithDateAnnotation(Random.date().generate());
		domainObject.setMyDateWithTimeAnnotation(Random.date().generate());
		domainObject.setMyDateWithDateTimeAnnotation(Random.date().generate());

		// Calendar
		domainObject.setMyCalendar(Random.calendar().generate());
		domainObject.setMyCalendarWithTimeFormat(Random.calendar().generate());
		domainObject.setMyCalendarWithDateFormat(Random.calendar().generate());
		domainObject.setMyCalendarWithDateTimeFormat(Random.calendar().generate());
		domainObject.setMyCalendarWithDateAnnotation(Random.calendar().generate());
		domainObject.setMyCalendarWithTimeAnnotation(Random.calendar().generate());
		domainObject.setMyCalendarWithDateTimeAnnotation(Random.calendar().generate());

		// Domain Type
		domainObject.setMyEnum((MyEnum) Random.fromEnum(AllFeatureDomainObject.MyEnum.class).generate());
		domainObject.setMyDomainObject(domainObject);

		return domainObject;
	}

}
