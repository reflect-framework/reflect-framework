package nth.reflect.fw.javafx.control.window.appbar;

import com.jfoenix.controls.JFXRippler;

import nth.reflect.fw.javafx.control.style.RfxStyleProperties;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.tab.Tabs;

/**
 * Wraps {@link RfxTabHeaderContent} into a {@link JFXRippler} to give it a rippler effect
 * @author nilsth
 *
 */
public class RfxTabHeader extends JFXRippler {

	private final Tab tab;

	public RfxTabHeader(Tabs<Tab> tabs, Tab tab) {
		super(new RfxTabHeaderContent(tabs, tab));
		this.tab = tab;
		initStyle();
	}

	private void initStyle() {
		RfxStyleProperties properties=new RfxStyleProperties();
		properties.put("-jfx-rippler-fill", MaterialColorSetCssName.PRIMARY.BACKGROUND_HIGHLIGHTED());
		setStyle(properties.toString());
	}

	public Tab getTab() {
		return tab;
	}

	public void setSelected(boolean isSelectedTab) {
		RfxTabHeaderContent tabHeaderContent= (RfxTabHeaderContent) getControl();
		tabHeaderContent.setSelected(isSelectedTab);
	}
	
}
