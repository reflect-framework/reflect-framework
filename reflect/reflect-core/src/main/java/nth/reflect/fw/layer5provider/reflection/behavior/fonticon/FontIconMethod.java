package nth.reflect.fw.layer5provider.reflection.behavior.fonticon;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrl;

/**
 * <p>
 * Instead of the {@link FontIcon} annotation you can also define the icon with a
 * {@link FontIconMethod}. This allows you to change the icon dynamically during
 * runtime, based on the object state (e.g. when the {@link DomainObject} Person is a male
 * or female).
 * </p>
 * <p>
 * Syntax: public string &lt;className or {@link ActionMethod}Name&gt;FontIcon()
 * </p>
 * 
 * The return value must be an {@link FontIconUrl}.
 * 
 * TODO EXAMPLE customer male or female icon
 * 
 * @author nilsth
 *
 */
public class FontIconMethod extends BehavioralMethod {

	@Override
	public String getBehavioralName() {
		return "FontIcon";
	}

	@Override
	public Class<?> getReturnType() {
		return String.class;
	}

}
