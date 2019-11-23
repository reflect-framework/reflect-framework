package nth.reflect.fw.layer5provider.reflection.behavior.option;

import java.util.Arrays;
import java.util.List;

public class OptionsEnumModel implements OptionsModel {

	private List<?> options;

	@SuppressWarnings("rawtypes")
	public OptionsEnumModel(Class<? extends Enum> enumType) {
		Enum[] enumConstants = enumType.getEnumConstants();
		options = Arrays.asList(enumConstants);
	}

	@Override
	public boolean hasOptions() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getOptions(Object domainObject) {
		return (List<Object>) options;
	}

}
