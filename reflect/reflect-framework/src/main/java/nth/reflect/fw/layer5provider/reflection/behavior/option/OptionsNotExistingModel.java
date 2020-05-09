package nth.reflect.fw.layer5provider.reflection.behavior.option;

import java.util.Collections;
import java.util.List;

public class OptionsNotExistingModel implements OptionsModel {

	@Override
	public boolean hasOptions() {
		return false;
	}

	@Override
	public List<Object> getOptions(Object domainObject) {
		return Collections.emptyList();
	}

}
