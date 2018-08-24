package nth.reflect.fw.layer5provider.reflection.behavior.fonticon;

import java.net.URL;

/**
 * <p>
 * The default is no {@link FontIcon}
 * </p>
 * @author nilsth
 *
 */
public class FontIconNoUrlModel implements FontIconModel {


	private static FontIconNoUrlModel instance;

	static {
		instance=new FontIconNoUrlModel();
	}
	
	public static FontIconNoUrlModel getInstance() {
		return instance;
	}
	
	
	private FontIconNoUrlModel() {
	}
	
	@Override
	public URL getFontIconUrl(Object obj) {
		return null;
	}

}
