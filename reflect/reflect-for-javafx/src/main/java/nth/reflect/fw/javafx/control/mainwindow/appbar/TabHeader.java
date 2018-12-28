package nth.reflect.fw.javafx.control.mainwindow.appbar;

import com.jfoenix.controls.JFXRippler;

import nth.reflect.fw.javafx.control.style.StyleProperties;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.ui.component.tab.Tabs;
import nth.reflect.fw.ui.style.ReflectColorName;

/**
 * Wraps {@link TabHeaderContent} into a {@link JFXRippler} to give it a rippler effect
 * @author nilsth
 *
 */
public class TabHeader extends JFXRippler {

	private final Tab tab;

	public TabHeader(Tabs<Tab> tabs, Tab tab) {
		super(new TabHeaderContent(tabs, tab));
		this.tab = tab;
		initStyle();
	}

	private void initStyle() {
		StyleProperties properties=new StyleProperties();
		properties.put("-jfx-rippler-fill", ReflectColorName.PRIMARY.BACKGROUND_20());
		setStyle(properties.toString());
	}

	public Tab getTab() {
		return tab;
	}

	public void setSelected(boolean isSelectedTab) {
		TabHeaderContent tabHeaderContent= (TabHeaderContent) getControl();
		tabHeaderContent.setSelected(isSelectedTab);
	}
	
}
