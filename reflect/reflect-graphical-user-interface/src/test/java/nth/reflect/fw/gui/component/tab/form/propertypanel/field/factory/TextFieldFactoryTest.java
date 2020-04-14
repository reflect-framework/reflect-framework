package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class TextFieldFactoryTest extends FieldFactoryTest {

	private TextFieldFactory textFieldFactory;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		textFieldFactory = createTextFieldFactory();
	}

	private TextFieldFactory createTextFieldFactory() {
		return new TextFieldFactory() {

			@Override
			public PropertyField create(PropertyFieldFactoryInfo makeInformation) {
				return null;
			}
		};
	}

	@Test
	public void testCanCreate_givenDomainOject_mustReturnFalse() {
		assertCanCreate(textFieldFactory, FullFeatureDomainObject.GET_MY_DOMAIN_OBJECT, false);
	}

	@Test
	public void testCanCreate_givenString_mustReturnTrue() {
		assertCanCreate(textFieldFactory, FullFeatureDomainObject.GET_MY_TEXT, true);
	}

	@Test
	public void testCanCreate_givenSimpleInt_mustReturnTrue() {
		assertCanCreate(textFieldFactory, FullFeatureDomainObject.GET_MY_PRIMITIVE_INT, true);
	}

	@Test
	public void testCanCreate_givenInteger_mustReturnTrue() {
		assertCanCreate(textFieldFactory, FullFeatureDomainObject.GET_MY_INTEGER, true);
	}

	@Test
	public void testCanCreate_givenSimpleChar_mustReturnTrue() {
		assertCanCreate(textFieldFactory, FullFeatureDomainObject.GET_MY_PRIMITIVE_CHAR, true);
	}

	@Test
	public void testCanCreate_givenCharacter_mustReturnTrue() {
		assertCanCreate(textFieldFactory, FullFeatureDomainObject.GET_MY_CHARACTER, true);
	}

	@Test
	public void testIsCharacterType_givenInteger_mustReturnFalse() {
		assertThat(TextFieldFactory.isCharacterType(Integer.class)).isEqualTo(false);
	}

	@Test
	public void testIsCharacterType_givenPrimitiveChar_mustReturnTrue() {
		assertThat(TextFieldFactory.isCharacterType(char.class)).isEqualTo(true);
	}

	@Test
	public void testIsCharacterType_givenCharacter_mustReturnTrue() {
		assertThat(TextFieldFactory.isCharacterType(Character.class)).isEqualTo(true);
	}

	@Test
	public void testIsStringType_givenInteger_MustReturnFalse() {
		assertThat(TextFieldFactory.isStringType(Integer.class)).isEqualTo(false);
	}

	@Test
	public void testIsStringType_givenString_MustReturnTrue() {
		assertThat(TextFieldFactory.isStringType(String.class)).isEqualTo(true);
	}

	@Test
	public void testIsNumberType_givenString_mustReturnFalse() {
		assertThat(TextFieldFactory.isNumberType(String.class)).isEqualTo(false);
	}

	@Test
	public void testIsNumberType_givenPrimitiveInt_mustReturnTrue() {
		assertThat(TextFieldFactory.isNumberType(int.class)).isEqualTo(true);
	}

	@Test
	public void testIsNumberType_givenShort_mustReturnTrue() {
		assertThat(TextFieldFactory.isNumberType(Short.class)).isEqualTo(true);
	}

	@Test
	public void testGetTextFieldModeType_givenInteger_mustReturnSingleLine() {
		PropertyInfo propertyInfo = createPropertyInfo(FullFeatureDomainObject.GET_MY_INTEGER);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.SINGLE_LINE);
	}

	@Test
	public void testGetTextFieldModeType_givenText_mustReturnSingleLine() {
		PropertyInfo propertyInfo = createPropertyInfo(FullFeatureDomainObject.GET_MY_TEXT);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.SINGLE_LINE);
	}

	@Test
	public void testGetTextFieldModeType_givenTextArea_mustReturnMiltiLine() {
		PropertyInfo propertyInfo = createPropertyInfo(FullFeatureDomainObject.GET_MY_TEXT_AREA);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.MILTI_LINE);
	}

	@Test
	public void testGetTextFieldModeType_givenPassword_mustReturnPassword() {
		PropertyInfo propertyInfo = createPropertyInfo(FullFeatureDomainObject.GET_MY_PASSWORD);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.PASSWORD);
	}

}
