package nth.reflect.ui.vaadin.mainwindow;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.selection.SelectionEvent;

import javafx.scene.control.TreeItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;
import nth.reflect.fw.ui.item.method.menu.MainMenuItems;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.StyleBuilder;

/**
 * The {@link MainMenu} HTML element is a placeholder (a container) for the main
 * menu. The {@link MainMenu} element and all its children will be repositioned
 * and resized with javascript (see main-window.js)
 */
public class MainMenu extends Div {

	private static final long serialVersionUID = -6303703937022797926L;
	private final UserInterfaceContainer userInterfaceContainer;

	public MainMenu(UserInterfaceContainer userInterfaceContainer) {
		this.userInterfaceContainer = userInterfaceContainer;
		setId("mainMenu");
		new StyleBuilder().setProperty("border-right", "1px solid lightgray").setZIndex(MainWindow.Z_INDEX_MAIN_MENU)
				.setBackground(Color.WHITE).setFor(this);

		Grid<Item> mainMenuContent = createMainMenuContent();
		add(mainMenuContent);
	}
	
	private Grid<Item> createMainMenuContent() {
		Grid<Item> grid = new Grid<>();
		new StyleBuilder().setOverflow(Overflow.AUTO).setFor(grid);
		grid.setItems(createMainMenuItems());
		grid.addColumn(Item::getText);
		grid.addSelectionListener(this::onMainMenuItemSelected);
		return grid;
	}

	private void onMainMenuItemSelected(SelectionEvent<Grid<Item>, Item> event) {
		Optional<Item> item = event.getFirstSelectedItem();
		if (item.isPresent()) {
			item.get().getAction().run();
		}
	}

	private List<Item> createMainMenuItems() {
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);

		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);

		Collection<MethodOwnerItem> serviceObjectItems = new MainMenuItems(userInterfaceContainer);

		List<Item> items = new ArrayList<>();
		// TODO wait for Vaadin11 to release a TreeGrid (current state: planned
		// but no time line) for hierarchical MainMenuItems (with indentation)
		for (MethodOwnerItem serviceObjectItem : serviceObjectItems) {
			items.add(serviceObjectItem);
			for (Item actionMethodItem : serviceObjectItem.getChildren()) {
				items.add(actionMethodItem);
			}
		}
		return items;
	}

}
