package nth.reflect.fw.gui.component.table.info.dataprovider;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.vaadin.flow.data.provider.DataProvider;

public class FellowshipService {

	public static final Person GANDALF = new Person("Gandalf");
	public static final Person FRODO = new Person("Frodo");
	public static final Person SAM = new Person("Sam");
	public static final Person ARAGORN = new Person("Aragorn");
	public static final Person LEGOLAS = new Person("Legolas");
	public static final Person GIMILI = new Person("Gimli");
	public static final Person PIPPIN = new Person("Pippin");
	public static final Person MERRY = new Person("Merry");
	public static final Person BOROMIR = new Person("Boromir");
	public static final Person[] ALL = new Person[] { GANDALF, FRODO, SAM, ARAGORN, LEGOLAS, GIMILI, PIPPIN, MERRY,
			BOROMIR };

	public static final String ARRAY_METHOD = "arrayMethod";

	public Person[] arrayMethod() {
		return ALL;
	}

	public static final String NULL_ARRAY_METHOD = "nullArrayMethod";

	public Person[] nullArrayMethod() {
		return null;
	}

	public static final String COLLECTION_METHOD = "collectionMethod";

	public List<Person> collectionMethod() {
		return Arrays.asList(ALL);
	}

	public static final String NULL_COLLECTION_METHOD = "nullColectionMethod";

	public List<Person> nullColectionMethod() {
		return null;
	}

	public static final String ITERATOR_METHOD = "iteratorMethod";

	public Iterator<Person> iteratorMethod() {
		return collectionMethod().iterator();
	}

	public static final String NULL_ITERATOR_METHOD = "nullIteratorMethod";

	public Iterator<Person> nullIteratorMethod() {
		return null;
	}

	public static final String STREAM_METHOD = "streamMethod";

	public Stream<Person> streamMethod() {
		return collectionMethod().stream();
	}

	public static final String NULL_STREAM_METHOD = "nullStreamMethod";

	public Stream<Person> nullStreamMethod() {
		return null;
	}

	public static final String DATA_PROVIDER_METHOD = "dataProviderMethod";

	public DataProvider dataProviderMethod() {
		return DataProvider.ofItems(ALL);
	}

	public static final String NULL_DATA_PROVIDER_METHOD = "nullDataProviderMethod";

	public DataProvider nullDataProviderMethod() {
		return null;
	}

	public static final String EXCEPTION_METHOD = "exceptionMethod";

	public DataProvider exceptionMethod() {
		throw new RuntimeException("Could not connect to database");
	}

	public static final String STRING_METHOD = "stringMethod";

	public String stringMethod() {
		return collectionMethod().toString();
	}

	@Override
	public String toString() {
		return FellowshipService.class.getSimpleName();
	};
}
