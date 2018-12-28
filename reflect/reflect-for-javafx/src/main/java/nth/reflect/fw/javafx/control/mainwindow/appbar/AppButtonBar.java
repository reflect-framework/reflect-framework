package nth.reflect.fw.javafx.control.mainwindow.appbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.stage.PopupWindow.AnchorLocation;
import nth.reflect.fw.javafx.UserinterfaceControllerForJavaFX;
import nth.reflect.fw.javafx.control.button.PrimaryButton;
import nth.reflect.fw.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreePanel;
import nth.reflect.fw.javafx.control.mainwindow.MainWindow;
import nth.reflect.fw.javafx.control.mainwindow.content.ContentPane;
import nth.reflect.fw.javafx.control.mainwindow.mainmenu.MainMenuPane;
import nth.reflect.fw.javafx.control.style.StyleProperties;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.tab.table.TableTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.component.applicationbar.ApplicationBarStyle;
import nth.reflect.fw.ui.component.mainmenu.MainMenuStyle;
import nth.reflect.fw.ui.component.tab.Tabs;
import nth.reflect.fw.ui.component.tab.TabsListener;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.item.tab.SelectTabItem;
import nth.reflect.fw.ui.style.ReflectColorName;

/**
 * Button bar as part of the {@link AppBar} which contains the main navigation
 * buttons:
 * <ul>
 * <li>the MainMenu button (see {@link MainMenuPane})</li>
 * <li>the {@link RfxtabHeader}'s that navigate to the content {@link Tab}s
 * (e.g. See {@link FormTab} and {@link TableTab})</li>
 * <li>a tab menu button when not all {@link RfxtabHeader}'s can be displayed on
 * the {@link AppButtonBar}. It shows a list of all {@link RfxtabHeader}'s when
 * clicked)</li>
 * </ul>
 * 
 * @author nilsth
 *
 */

public class AppButtonBar extends Pane implements TabsListener<Tab> {

	private static final int IGNORE_BASE_LINE = 0;

	private final BooleanProperty mainMenuVisibleProperty;
	private final JFXButton mainMenuButton;
	private final MainWindow mainWindow;
	private final BooleanBinding extraWideBinding;
	private final JFXButton tabSelectionButton;
	private final MainMenuPane mainMenuPane;
	private final UserInterfaceContainer userInterfaceContainer;
	private final Tabs<Tab> tabs;

	public AppButtonBar(UserInterfaceContainer userInterfaceContainer, MainMenuPane mainMenuPane) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.mainMenuPane = mainMenuPane;
		mainWindow = userInterfaceContainer.get(MainWindow.class);
		tabs = getTabs(userInterfaceContainer);
		tabs.addListener(this);
		mainMenuVisibleProperty = mainWindow.getMainMenuVisibleProperty();
		mainMenuVisibleProperty.addListener(this::onMainMenuVisibleChanged);
		extraWideBinding = mainWindow.getExtraWideBinding();

		mainMenuButton = createMainMenuButtton();
		getChildren().add(mainMenuButton);

		tabSelectionButton = createTabSelectionButton();
		getChildren().add(tabSelectionButton);

		setMaxHeight(ApplicationBarStyle.HEIGHT);
	}

	private Tabs<Tab> getTabs(UserInterfaceContainer userInterfaceContainer) {
		UserinterfaceControllerForJavaFX userInterfaceController = userInterfaceContainer
				.get(UserinterfaceControllerForJavaFX.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();
		return tabs;
	}

	public void onMainMenuVisibleChanged(ObservableValue<? extends Boolean> observable, Boolean oldValue,
			Boolean newValue) {
		requestLayout();
	}

	/**
	 * Custom layout: to position its children:
	 * <ul>
	 * <li>The bar starts with a main menu button to slide the
	 * {@link MainMenuPane} in and out (show or hide)</li>
	 * <li>The {@link RfxtabHeader}s:</li>
	 * <ul>
	 * <li>The number of displayed {@link RfxtabHeader}s depend on the available
	 * space.</li>
	 * <li>A tab selection button is displayed on the right of the bar when not
	 * all {@link RfxtabHeader}s fit on the bar. It will open a pop-up menu to
	 * select a tab from a list.</li>
	 * <li>The {@link RfxtabHeader}s indent to the right (above the
	 * {@link ContentPane} when the {@link MainMenuPane} is continuously visible
	 * and the {@link MainWindow#getExtraWideBinding()}==True
	 * ({@link ContentPane} next to {@link MainMenuPane})</li>
	 * </ul>
	 * </ul>
	 * <br>
	 * This method is also called when then {@link #mainMenuPane} slides in or
	 * out (see
	 * {@link MainWindow#onMenuMovingLeftOrRight(ObservableValue, Number, Number)})
	 */
	@Override
	protected void layoutChildren() {
		double width = getWidth();
		boolean snapToPixel = isSnapToPixel();
		double x = 0;
		double y = 0;

		mainMenuButton.autosize();
		double mainMenuButtonWidth = mainMenuButton.getWidth();
		double mainMenuButtonHeight = mainMenuButton.getHeight();
		positionInArea(mainMenuButton, x, y, mainMenuButtonWidth, mainMenuButtonHeight, IGNORE_BASE_LINE, Insets.EMPTY,
				HPos.LEFT, VPos.TOP, snapToPixel);

		double menuIndent = MainMenuStyle.WIDTH + mainMenuPane.getTranslateX();
		if (extraWideBinding.get() && menuIndent > mainMenuButton.getWidth()) {
			x = menuIndent;
		} else {
			x = mainMenuButton.getWidth();
		}

		List<TabHeader> tabHeaders = getTabHeaders();
		resizeTabHeaders(tabHeaders);

		tabSelectionButton.autosize();
		double tabSelectionButtonWidth = tabSelectionButton.getWidth();
		double tabSelectionButtonHeight = tabSelectionButton.getHeight();

		double availableWidth = width - x - tabSelectionButtonWidth;
		// minus tab selection button width?

		List<TabHeader> visibletabHeaders = getVisibleTabHeaders(tabHeaders, availableWidth);

		tabSelectionButton.setVisible(visibletabHeaders.size() < tabHeaders.size());
		positionInArea(tabSelectionButton, width - tabSelectionButtonWidth, y, tabSelectionButtonWidth,
				tabSelectionButtonHeight, IGNORE_BASE_LINE, Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);

		for (TabHeader tabHeader : tabHeaders) {
			if (visibletabHeaders.contains(tabHeader)) {
				tabHeader.setVisible(true);
				double tabHeaderWidth = tabHeader.getWidth();
				double tabHeaderHeight = tabHeader.getHeight();
				double remainingWidth = width - x - tabHeaderWidth;
				if (tabSelectionButton.isVisible()) {
					remainingWidth -= tabSelectionButtonWidth;
				}
				if (remainingWidth < 0) {
					tabHeaderWidth = width - x;
					if (tabSelectionButton.isVisible()) {
						tabHeaderWidth = tabHeaderWidth - tabSelectionButtonWidth;
					}
				}
				tabHeader.resize(tabHeaderWidth, tabHeaderHeight);
				positionInArea(tabHeader, x, y, tabHeaderWidth, tabHeaderHeight, IGNORE_BASE_LINE, Insets.EMPTY,
						HPos.LEFT, VPos.TOP, snapToPixel);

				x += tabHeaderWidth;
			} else {
				tabHeader.setVisible(false);
			}
		}

	}

	private List<TabHeader> getTabHeaders() {
		List<TabHeader> tabHeaders = new ArrayList<>();
		for (Node node : getChildren()) {
			if (node instanceof TabHeader) {
				TabHeader tabHeader = (TabHeader) node;
				tabHeaders.add(tabHeader);
			}
		}
		return tabHeaders;
	}

	private List<TabHeader> getVisibleTabHeaders(List<TabHeader> tabHeaders, double availableWidth) {
		List<TabHeader> visibleTabHeaders = new ArrayList<>(tabHeaders);
		int selectedTabHeaderIndex = 0;
		Optional<TabHeader> selectedTabHeader = getTabHeader(tabHeaders, tabs.getSelected());
		if (selectedTabHeader.isPresent()) {
			selectedTabHeaderIndex = visibleTabHeaders.indexOf(selectedTabHeader.get());
		}

		double visibleTabHeaderWidth = gettabHeadersWidth(visibleTabHeaders);

		while (visibleTabHeaderWidth > availableWidth && visibleTabHeaders.size() > 1) {
			int nrOfTabHeadersBeforeSelected = selectedTabHeaderIndex;
			int nrOfTabHeadersAfterSelected = visibleTabHeaders.size() - selectedTabHeaderIndex - 1;
			boolean hasButtonsToRemoveBeforeSelected = nrOfTabHeadersBeforeSelected > 0;
			boolean hasButtonsToRemoveAfterSelected = nrOfTabHeadersAfterSelected > 0;
			boolean removeBefore = hasButtonsToRemoveBeforeSelected
					&& ((nrOfTabHeadersBeforeSelected > nrOfTabHeadersAfterSelected)
							|| !hasButtonsToRemoveAfterSelected);
			if (removeBefore) {
				TabHeader firstVisibleTabHeader = visibleTabHeaders.get(0);
				visibleTabHeaderWidth -= firstVisibleTabHeader.getWidth();
				visibleTabHeaders.remove(firstVisibleTabHeader);
			} else {
				TabHeader lastVisibleTabHeader = visibleTabHeaders.get(visibleTabHeaders.size() - 1);
				visibleTabHeaderWidth -= lastVisibleTabHeader.getWidth();
				visibleTabHeaders.remove(lastVisibleTabHeader);
			}
		}
		return visibleTabHeaders;

	}

	private Optional<TabHeader> getTabHeader(List<TabHeader> tabHeaders, Tab tabToFind) {
		return tabHeaders.stream().filter(tabHeader -> tabHeader.getTab().equals(tabToFind)).findFirst();
	}

	private double gettabHeadersWidth(List<TabHeader> visibletabHeaders) {
		double totaltabHeadersWidth = 0;
		for (TabHeader tabHeader : visibletabHeaders) {
			totaltabHeadersWidth += tabHeader.getWidth();
		}
		return totaltabHeadersWidth;
	}

	private void resizeTabHeaders(List<TabHeader> tabHeaders) {
		for (TabHeader tabHeader : tabHeaders) {
			tabHeader.autosize();
		}
	}

	private JFXButton createTabSelectionButton() {
		PrimaryButton tabSelectionButton = new PrimaryButton(FontAwesomeIconName.FILES_ALT);
		// TODO use de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.CLONE
		// when supported by newer version of jfoenix (fontawesomefx8.9)
		tabSelectionButton.setOnAction(this::onTabSelectionButtonAction);
		return tabSelectionButton;
	}

	public void onTabSelectionButtonAction(ActionEvent actionEvent) {
		showSelectTabPopUp();
	}

	private void showSelectTabPopUp() {
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		UserinterfaceControllerForJavaFX userInterfaceController = userInterfaceContainer
				.get(UserinterfaceControllerForJavaFX.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();

		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);
		ItemTreePanel itemTreePanel = new ItemTreePanel(rootNode);

		for (Tab tab : tabs) {
			SelectTabItem<Tab> selectTabItem = new SelectTabItem<>(languageProvider, tabs, tab);
			TreeItem<Item> selectTabNode = new TreeItem<>(selectTabItem);
			rootNode.getChildren().add(selectTabNode);
		}

		JFXPopup popup = new JFXPopup();
		popup.setPopupContent(itemTreePanel);
		popup.setAnchorLocation(AnchorLocation.CONTENT_TOP_RIGHT);
		popup.show(tabSelectionButton);
	}

	private JFXButton createMainMenuButtton() {
		// TODO find out why this does not work:
		// PrimaryButton mainMenuButton = new
		// PrimaryButton(FontAwesomeIconName.BARS);
		// mainMenuButton.setOnAction(this::onMainMenuButton);
		// return mainMenuButton;

		JFXButton button = new JFXButton();
		FontAwesomeIcon icon = new FontAwesomeIcon();
		icon.setIcon(de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.BARS);
		icon.setSize("17px");
		String iconStyle = icon.getStyle() + ";"
				+ new StyleProperties().setFill(ReflectColorName.PRIMARY.FOREGROUND()).toString();
		icon.setStyle(iconStyle);

		// FontIcon icon=new FontIcon(FontAwesomeIconName.BARS, 16,
		// Color.WHITE);
		button.setGraphic(icon);
		button.setPadding(new Insets(8, 16, 8, 17));
		// button.getStyleClass().add("button-flat");
		button.setOnAction(this::onMainMenuButton);
		return button;

	}

	private void onMainMenuButton(ActionEvent event) {
		mainMenuVisibleProperty.set(!mainMenuVisibleProperty.get());
	}

	@Override
	public void onRemoveTab(Tab removedTab) {
		Iterator<Node> iterator = getChildren().iterator();
		while (iterator.hasNext()) {
			Node child = iterator.next();
			if (child instanceof TabHeader) {
				TabHeader tabHeader = (TabHeader) child;
				Tab tab = tabHeader.getTab();
				if (tab.equals(removedTab)) {
					iterator.remove();
				}
			}
		}
	}

	@Override
	public void onAddTab(Tab newTab) {
		TabHeader tabHeader = new TabHeader(tabs, newTab);
		getChildren().add(tabHeader);
	}

	@Override
	public void onSelectTab(Tab selectedTab) {
		for (Node child : getChildren()) {
			if (child instanceof TabHeader) {
				TabHeader tabHeader = (TabHeader) child;
				Tab tab = tabHeader.getTab();
				boolean isSelectedTab = tab.equals(selectedTab);
				tabHeader.setSelected(isSelectedTab);
			}
		}
	}

}
