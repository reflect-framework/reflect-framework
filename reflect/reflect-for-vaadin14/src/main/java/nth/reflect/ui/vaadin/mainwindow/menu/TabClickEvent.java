package nth.reflect.ui.vaadin.mainwindow.menu;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.tabs.Tab;

public class TabClickEvent extends ClickEvent<Tab> {

	private static final long serialVersionUID = 3605845364545815570L;

	public TabClickEvent(Tab source) {
		super(source);
	}
}
