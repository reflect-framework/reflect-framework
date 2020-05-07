package nth.reflect.example.domain.contact;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class EventGenerator extends RandomGenerator<List<Event>> {

	@Override
	public List<Event> generate() {
		List<Event> events=new ArrayList<>();
		if (Random.bool().generate()) {
			events.add(createBirthDay());
		}
		if (Random.bool().generate()) {
			events.add(createAnniversary());
		}
		return events;
	}

	private Event createAnniversary() {
		Event event = new Event();
		event.setName("Anniversary");
		Period minimumAge = Period.ofYears(1);
		Period maximumAge = Period.ofYears(9);
		event.setDate(Random.localDate().forPeriodInThePast(minimumAge, maximumAge).generate());
		return event;
	}

	private Event createBirthDay() {
		Event event = new Event();
		event.setName("Birthday");
		Period minimumAge = Period.ofYears(10);
		Period maximumAge = Period.ofYears(80);
		event.setDate(Random.localDate().forPeriodInThePast(minimumAge, maximumAge).generate());
		return event;
	}

}
