package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldFactoryNotFoundException;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
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
			public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel)
					throws PropertyFieldFactoryNotFoundException {
				return null;
			}
		};
	}

	@Test
	public void testCanCreate_givenDomainOject_mustReturnFalse() {
		assertCanCreate(textFieldFactory, AllFeatureDomainObject.GET_MY_DOMAIN_OBJECT, true);
		// StringConverters without fromString must be readonly fields
	}

	@Test
	public void testCanCreate_givenString_mustReturnTrue() {
		assertCanCreate(textFieldFactory, AllFeatureDomainObject.GET_MY_TEXT, true);
	}

	@Test
	public void testCanCreate_givenSimpleInt_mustReturnTrue() {
		assertCanCreate(textFieldFactory, AllFeatureDomainObject.GET_MY_PRIMITIVE_INT, true);
	}

	@Test
	public void testCanCreate_givenInteger_mustReturnTrue() {
		assertCanCreate(textFieldFactory, AllFeatureDomainObject.GET_MY_INTEGER, true);
	}

	@Test
	public void testCanCreate_givenSimpleChar_mustReturnTrue() {
		assertCanCreate(textFieldFactory, AllFeatureDomainObject.GET_MY_PRIMITIVE_CHAR, true);
	}

	@Test
	public void testCanCreate_givenCharacter_mustReturnTrue() {
		assertCanCreate(textFieldFactory, AllFeatureDomainObject.GET_MY_CHARACTER, true);
	}

	@Test
	public void testGetTextFieldModeType_givenInteger_mustReturnSingleLine() {
		PropertyInfo propertyInfo = createPropertyInfo(AllFeatureDomainObject.GET_MY_INTEGER);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.SINGLE_LINE);
	}

	@Test
	public void testGetTextFieldModeType_givenText_mustReturnSingleLine() {
		PropertyInfo propertyInfo = createPropertyInfo(AllFeatureDomainObject.GET_MY_TEXT);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.SINGLE_LINE);
	}

	@Test
	public void testGetTextFieldModeType_givenTextArea_mustReturnMiltiLine() {
		PropertyInfo propertyInfo = createPropertyInfo(AllFeatureDomainObject.GET_MY_TEXT_AREA);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.MILTI_LINE);
	}

	@Test
	public void testGetTextFieldModeType_givenPassword_mustReturnPassword() {
		PropertyInfo propertyInfo = createPropertyInfo(AllFeatureDomainObject.GET_MY_PASSWORD);
		TextFieldModeType textFieldModeType = TextFieldFactory.getTextFieldModeType(propertyInfo);
		assertThat(textFieldModeType).isEqualTo(TextFieldModeType.PASSWORD);
	}

}
