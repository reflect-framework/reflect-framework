package nth.reflect.fw.layer5provider.reflection.behavior.applicationicon;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethod;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrl;

/**
 * <p>
 * Instead of the {@link FontIcon} annotation you can also define the icon with a
 * {@link ApplicationIconMethod}. This allows you to change the icon dynamically during
 * runtime, based on state (e.g. when the {@link DomainObject} Person is a male
 * or female).
 * </p>
 * <p>
 * Syntax: public string &lt;ApplicationClassName&gt;ApplicationIcon()
 * </p>
 * 
 * The return value must be an a URL to the {@link ApplicationIcon} file, e.g. a {@link ClassResourceUrl}.
 * 
 * TODO EXAMPLE of a changing application icon
 * 
 * @author nilsth
 *
 */
public class ApplicationIconMethod extends BehavioralMethod{

	@Override
	public String getBehavioralName() {
		return "ApplicationIcon";
	}

	@Override
	public Class<?> getReturnType() {
		return String.class;
	}

}
