package nth.reflect.fw.javafx.control.window.content;

import javafx.scene.layout.BorderPane;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;

public class RfxContentPane extends BorderPane implements TabsListener<Tab> {

	public RfxContentPane(Tabs<Tab> tabs) {
		super();
		addStyleClass();
		tabs.addListener(this);
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxContentPane.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxContentPane.class)).getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1());
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		// Do nothing
	}

	@Override
	public void onAddTab(Tab newTab) {
		// Do nothing
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		setCenter(selectedTab);
	}
}
