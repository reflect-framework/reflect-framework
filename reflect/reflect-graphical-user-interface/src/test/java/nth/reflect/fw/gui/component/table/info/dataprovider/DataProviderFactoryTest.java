package nth.reflect.fw.gui.component.table.info.dataprovider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;

import nth.reflect.fw.gui.component.table.info.dataprovider.exception.GettingTableValuesException;
import nth.reflect.fw.gui.component.table.info.dataprovider.exception.MethodReturnTypeNotSupportedException;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

public class DataProviderFactoryTest {

	private UserInterfaceContainer container;
	private Query query;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().addServiceClass(FellowshipService.class).createContainer();
		query = new Query();
	}

	@Test
	public void testCreateForGridTab_givenArray_mustGiveData() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.ARRAY_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).containsExactlyInAnyOrder(FellowshipService.ALL);
	}

	@Test
	public void testCreateForGridTab_givenNullArray_mustGiveEmptyStream() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.NULL_ARRAY_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).hasSize(0);
	}

	@Test
	public void testCreateForGridTab_givenCollection_mustGiveData() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.COLLECTION_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).containsExactlyInAnyOrder(FellowshipService.ALL);
	}

	@Test
	public void testCreateForGridTab_givenNullCollection_mustGiveEmptyStream() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.NULL_COLLECTION_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).hasSize(0);
	}

	@Test
	public void testCreateForGridTab_givenIterator_mustGiveData() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.ITERATOR_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).containsExactlyInAnyOrder(FellowshipService.ALL);
	}

	@Test
	public void testCreateForGridTab_givenNullIterator_mustGiveEmptyStream() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.NULL_ITERATOR_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).hasSize(0);
	}

	@Test
	public void testCreateForGridTab_givenStream_mustGiveData() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.STREAM_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).containsExactlyInAnyOrder(FellowshipService.ALL);
	}

	@Test
	public void testCreateForGridTab_givenNullStream_mustGiveData() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.NULL_STREAM_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).hasSize(0);
	}

	@Test
	public void testCreateForGridTab_givenDataProvider_mustGiveData() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.DATA_PROVIDER_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).containsExactlyInAnyOrder(FellowshipService.ALL);
	}

	@Test
	public void testCreateForGridTab_givenNullDataProvider_mustGiveData() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.NULL_DATA_PROVIDER_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(formTab);
		Stream<Person> stream = dataProvider.fetch(query);
		assertThat(stream).hasSize(0);
	}

	@Test
	public void testCreateForGridTab_givenException_mustThrowException() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.EXCEPTION_METHOD);
		assertThrows(GettingTableValuesException.class, () -> {
			DataProviderFactory.createFor(formTab);
		});
	}

	@Test
	public void testCreateForGridTab_givenString_mustException() {
		FellowshipGridTab formTab = new FellowshipGridTab(container, FellowshipService.STRING_METHOD);
		assertThrows(MethodReturnTypeNotSupportedException.class, () -> {
			DataProviderFactory.createFor(formTab);
		});
	}

	@Test
	public void testCreateForFormTab_givenArray_mustGiveData() {
		FellowshipPropertyValueModel propertyValueModel = new FellowshipPropertyValueModel(container,
				FellowshipService.ARRAY_METHOD);
		DataProvider dataProvider = DataProviderFactory.createFor(propertyValueModel);
		Stream<String> stream = dataProvider.fetch(query);
		assertThat(stream).containsExactlyInAnyOrder(Person.CHILDREN);
	}

}
