package nth.reflect.fw.layer5provider.reflection.behavior.description;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.reflect.util.english.plural.EnglishPlural;

public class PersonService {

	public static final String DISPLAY_NAME = StringUtil
			.firstCharToUpperCase(
					EnglishPlural.fromSingularNoun(PersonService.class.getSimpleName().replace("Service", "")));
	public static final String DESCRIPTION = DISPLAY_NAME;

	public static final String FIND_METHOD_NAME = "find";

	@Order(value = 1)
	@ParameterFactory
	public List<Person> find(Person person) {
		return new ArrayList();
	}

	@Override
	public String toString() {
		return PersonService.class.getSimpleName();
	};
}
