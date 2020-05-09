package nth.reflect.fw.layer5provider.url.servicemethod;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ServiceMethodUrlCanNotBeOpenedException extends TranslatableException {

	private static final long serialVersionUID = 5914006309441439996L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ServiceMethodUrlCanNotBeOpenedException.class.getCanonicalName() + ".message",
			"%1$s can not be opened. It can ony be used as a reference to a %2$s, which will be called by the %3$s when an %1$s is return by an other %2$s");

	public ServiceMethodUrlCanNotBeOpenedException() {
		super(MESSAGE
				.withParameters(ServiceMethodUrl.class.getSimpleName(), ServiceObjectActionMethod.class.getSimpleName(),
						ReflectFramework.class.getSimpleName()));
	}

}
