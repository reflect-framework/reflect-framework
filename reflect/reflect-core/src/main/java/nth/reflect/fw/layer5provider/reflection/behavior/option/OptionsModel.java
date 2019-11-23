package nth.reflect.fw.layer5provider.reflection.behavior.option;

import java.util.List;

/**
 * See {@link OptionsModelFactory}
 * 
 * @author nilsth
 *
 */
public interface OptionsModel {

	// TODO filter, e.g. auto completion

	// TODO multiple options possible

	public boolean hasOptions();

	public List<Object> getOptions(Object domainObject);
}
