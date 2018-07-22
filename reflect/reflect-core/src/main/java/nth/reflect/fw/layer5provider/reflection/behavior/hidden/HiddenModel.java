package nth.reflect.fw.layer5provider.reflection.behavior.hidden;

/**
 * See {@link HiddenModelFactory}
 * @author nilsth
 *
 */

public interface  HiddenModel {
	boolean isPropertyHiddenInForm(Object obj);
	
	boolean isPropertyHiddenInTable();

	boolean isHiddenActionMethod(Object obj);
}
