package nth.reflect.fw.javafx.control.mainwindow.content;

import javafx.scene.layout.BorderPane;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.TabsListener;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.javafx.control.tab.Tab;

public class ContentPane extends BorderPane implements TabsListener<Tab> {

	public ContentPane(Tabs<Tab> tabs) {
		super();
		addStyleClass();
		tabs.addListener(this);
	}

	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(ContentPane.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(ContentPane.class)).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND())
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND());
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
