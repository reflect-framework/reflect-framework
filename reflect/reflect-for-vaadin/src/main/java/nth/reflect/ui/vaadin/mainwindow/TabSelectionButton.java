package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;
import nth.reflect.ui.vaadin.css.Cursor;
import nth.reflect.ui.vaadin.css.FloatType;
import nth.reflect.ui.vaadin.css.Position;
import nth.reflect.ui.vaadin.css.SizeUnit;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.tab.Tab;

/**
 * <p>
 * The tab selection button is displayed in the right hand side of the
 * {@link TabHeaderBar} when there is not enough width to display all
 * {@link TabHeader}s. This is done with javascript, (see tabHeaderBar.js).
 * </p>
 * <p>
 * The {@link TabSelectionButton} is a rounded square with the numberOfTabs of
 * {@link Tab}s that are open
 * </p>
 * <p>
 * The user can click on the {@link TabSelectionButton} when it is visible. It
 * will open a pop up context menu with a list of all available
 * {@link Tab}s. This tab is activated when the user clicks on one of the
 * {@link Tab} items in the list.
 * </p>
 * 
 * @param children
 *            to append the TabSelectionButton to
 */

public class TabSelectionButton extends Div implements TabsListener<Tab> {

	private static final int SIZE = 20;
	private static final long serialVersionUID = -3754153221855076322L;
	private final Span numberOfTabs;
	private final Tabs<Tab> tabs;

	public TabSelectionButton(Tabs<Tab> tabs) {
		this.tabs = tabs;
		tabs.addListener(this);
		numberOfTabs = createNumberOfTabs();
		add(numberOfTabs);
		new StyleBuilder().setWidth(SIZE, SizeUnit.PX).setHeight(SIZE, SizeUnit.PX).setBorderStyle(BorderStyle.SOLID)
				.setBorderColor(ReflectColorName.PRIMARY.FOREGROUND()).setBorderWidth(2, SizeUnit.PX).setBorderRadius(7, SizeUnit.PX)
				.setFor(this);
	}

	private Span createNumberOfTabs() {
		Span span = new Span();
		new StyleBuilder().setColor(ReflectColorName.PRIMARY.FOREGROUND()).setCursor(Cursor.DEFAULT).setMargin(0).setPosition(Position.RELATIVE)
				.setFloat(FloatType.LEFT).setTop(50, SizeUnit.PERCENT).setLeft(50, SizeUnit.PERCENT)
				.setProperty("transform", "translate(-50%, -50%)").setFor(span);
		return span;
	}

	private void updateNumberOfTabs() {
		String number = Integer.toString(tabs.size());
		numberOfTabs.setText(number);
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		updateNumberOfTabs();
	}

	@Override
	public void onAddTab(Tab newTab) {
		updateNumberOfTabs();
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		// Does nothing
	}

}
