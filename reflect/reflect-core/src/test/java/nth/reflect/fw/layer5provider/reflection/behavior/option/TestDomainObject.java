package nth.reflect.fw.layer5provider.reflection.behavior.option;

import java.util.Arrays;
import java.util.List;

public class TestDomainObject {

	private String noOptionsProperty;
	private String stringProperty;
	private TestEnum enumProperty;
	private TestEnum limitedEnumProperty;
	private List<String> stringPropertyOptions = Arrays.asList("Red", "Blue", "Green");
	private List<TestEnum> limitedEnumPropertyOptions = Arrays.asList(TestEnum.A, TestEnum.C);

	public String getStringProperty() {
		return stringProperty;
	}

	public List<String> stringPropertyOptions() {
		return stringPropertyOptions;
	}

	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}

	public TestEnum getEnumProperty() {
		return enumProperty;
	}

	public void setEnumProperty(TestEnum enumProperty) {
		this.enumProperty = enumProperty;
	}

	public TestEnum getLimitedEnumProperty() {
		return limitedEnumProperty;
	}

	public List<TestEnum> limitedEnumPropertyOptions() {
		return limitedEnumPropertyOptions;
	}

	public void setLimitedEnumProperty(TestEnum limitedEnumProperty) {
		this.limitedEnumProperty = limitedEnumProperty;
	}

	public String getNoOptionsProperty() {
		return noOptionsProperty;
	}

	public void setNoOptionsProperty(String noOptionsProperty) {
		this.noOptionsProperty = noOptionsProperty;
	}

}
