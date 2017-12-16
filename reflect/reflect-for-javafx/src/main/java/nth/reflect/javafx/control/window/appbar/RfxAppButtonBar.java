package nth.reflect.javafx.control.window.appbar;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.PopupWindow.AnchorLocation;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.ui.item.tab.SelectTabItem;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.RfxUserinterfaceController;
import nth.reflect.javafx.control.button.RfxContentButton;
import nth.reflect.javafx.control.button.RfxPrimaryButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.list.RfxList;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.view.form.RfxDomainPropertyPane;
import nth.reflect.javafx.control.view.form.RfxFormView;
import nth.reflect.javafx.control.view.table.RfxTableView;
import nth.reflect.javafx.control.window.RfxWindow;
import nth.reflect.javafx.control.window.content.RfxContentPane;
import nth.reflect.javafx.control.window.mainmenu.RfxMainMenuPane;

/**
 * Button bar as part of the {@link RfxAppBar} which contains the main
 * navigation buttons:
 * <ul>
 * <li>the MainMenu button (see {@link RfxMainMenuPane})</li>
 * <li>the {@link RfxTabButton}'s that navigate to the content views (e.g. See
 * {@link RfxFormView} and {@link RfxTableView})</li>
 * <li>a tab menu button when not all {@link RfxTabButton}'s can be displayed on
 * the {@link RfxAppButtonBar}. It shows a list of all {@link RfxTabButton}'s
 * when clicked)</li>
 * </ul>
 * 
 * TODO: This class to replace {@link RfxTabButtonBar}<br>
 * TODO: This class to extend Pane and override {@link #layoutChildren()} and
 * compute methods (e.g. see {@link RfxDomainPropertyPane})
 * 
 * @author nilsth
 *
 */

public class RfxAppButtonBar extends Pane {

	private static final int IGNORE_BASE_LINE = 0;
	public static final int BAR_HEIGHT = 38;
	private final BooleanProperty mainMenuVisibleProperty;
	private final JFXButton mainMenuButton;
	private final ObservableList<View> tabsProperty;
	private final RfxWindow rfxWindow;
	private final BooleanBinding extraWideBinding;
	private final ObjectProperty<View> selectedTabProperty;
	private final JFXButton tabSelectionButton;
	private RfxMainMenuPane mainMenuPane;
	private final UserInterfaceContainer userInterfaceContainer;

	public RfxAppButtonBar(UserInterfaceContainer userInterfaceContainer,
			RfxMainMenuPane mainMenuPane) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.mainMenuPane = mainMenuPane;
		rfxWindow = userInterfaceContainer.get(RfxWindow.class);
		mainMenuVisibleProperty = rfxWindow.getMainMenuVisibleProperty();
		mainMenuVisibleProperty.addListener(this::onMainMenuVisibleChanged);
		extraWideBinding = rfxWindow.getExtraWideBinding();
		tabsProperty = rfxWindow.getTabsProperty();
		tabsProperty.addListener(this::onTabsChanged);
		selectedTabProperty = rfxWindow.getSelectedTabProperty();
		selectedTabProperty.addListener(this::onSelectedTabChanged);

		mainMenuButton = createMainMenuButtton();
		getChildren().add(mainMenuButton);

		tabSelectionButton = createTabSelectionButton();
		getChildren().add(tabSelectionButton);

		setMaxHeight(BAR_HEIGHT);
		// setPadding(new Insets(1));
		// HBox menuBar = createMenuBar();
		// setLeft(menuBar);
		//
		// setCenter(tabButtonBar);
		//
		// HBox rightButtonPane = new HBox();
		// rightButtonPane.setMaxHeight(BAR_HEIGHT);
		// setRight(rightButtonPane);
		// RfxContentButton tabSelectionButton = createTabSelectionButton();
		// rightButtonPane.getChildren().add(tabSelectionButton);
		// RfxContentButton tabMenuButton = createTabMenuButton();
		// rightButtonPane.getChildren().add(tabMenuButton);
	}

	public void onMainMenuVisibleChanged(ObservableValue<? extends Boolean> observable,
			Boolean oldValue, Boolean newValue) {
		requestLayout();
	}

	public void onTabsChanged(Change change) {
		if (tabsProperty.size() == 0) {
			selectedTabProperty.set(null);
			updateTabButtons();
		} else if (!change.getList().contains(selectedTabProperty.get())) {
			// TODO selectNewTab(change);
		} else {
			updateTabButtons();
		}
	}

	public void onSelectedTabChanged(ObservableValue<? extends View> observable, View oldValue,
			View newValue) {
		requestLayout();
	}

	public void updateTabButtons() {
		List<RfxTabButton> tabButtons = getTabButtons();
		addNewTabButtons(tabButtons);
		removeOldTabButtons(tabButtons);
		layout();
	}

	private List<RfxTabButton> getTabButtons() {
		List<RfxTabButton> tabButtons = new ArrayList<>();
		for (Node child : getChildren()) {
			if (child instanceof RfxTabButton) {
				RfxTabButton tabButton = (RfxTabButton) child;
				tabButtons.add(tabButton);
			}
		}
		return tabButtons;
	}

	private void addNewTabButtons(List<RfxTabButton> tabButtons) {
		List<View> tabs = new ArrayList<>();
		for (RfxTabButton tabButton : tabButtons) {
			tabs.add(tabButton.getTab());
		}
		for (View tab : tabsProperty) {
			if (!tabs.contains(tab)) {
				RfxTabButton tabButton = new RfxTabButton(rfxWindow, tab);
				getChildren().add(tabButton);
			}
		}
	}

	private void removeOldTabButtons(List<RfxTabButton> tabButtons) {
		for (RfxTabButton tabButton : tabButtons) {
			if (!tabsProperty.contains(tabButton.getTab())) {
				getChildren().remove(tabButton);
			}
		}
	}

	/**
	 * Custom layout: to position its children:
	 * <ul>
	 * <li>The bar starts with a main menu button to slide the
	 * {@link RfxMainMenuPane} in and out (show or hide)</li>
	 * <li>The {@link RfxTabButton}s:</li>
	 * <ul>
	 * <li>The number of displayed {@link RfxTabButton}s depend on the
	 * available space.</li>
	 * <li>A tab selection button is displayed on the right of the bar when not
	 * all {@link RfxTabButton}s fit on the bar. It will open a pop-up menu to
	 * select a tab from a list.</li>
	 * <li>The {@link RfxTabButton}s indent to the right (above the
	 * {@link RfxContentPane} when the {@link RfxMainMenuPane} is continuously
	 * visible and the {@link RfxWindow#getExtraWideBinding()}==True
	 * ({@link RfxContentPane} next to {@link RfxMainMenuPane})</li></ul></ul><br>
	 * This method is also called when then {@link #mainMenuPane} slides in or
	 * out (see
	 * {@link RfxWindow#onMenuMovingLeftOrRight(ObservableValue, Number, Number)})
	 */
	@Override
	protected void layoutChildren() {
		double width = getWidth();
		double height = getHeight();
		boolean snapToPixel = isSnapToPixel();
		double x = 0;
		double y = 0;

		mainMenuButton.autosize();
		double mainMenuButtonWidth = mainMenuButton.getWidth();
		double mainMenuButtonHeight = mainMenuButton.getHeight();
		positionInArea(mainMenuButton, x, y, mainMenuButtonWidth, mainMenuButtonHeight,
				IGNORE_BASE_LINE, Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);

		double menuIndent = RfxWindow.MENU_WIDTH + mainMenuPane.getTranslateX();
		if (extraWideBinding.get() && menuIndent > mainMenuButton.getWidth()) {
			x = menuIndent;
		} else {
			x = mainMenuButton.getWidth();
		}
		List<RfxTabButton> tabButtons = getTabButtons();
		resizeTabButtons(tabButtons);

		tabSelectionButton.autosize();
		double tabSelectionButtonWidth = tabSelectionButton.getWidth();
		double tabSelectionButtonHeight = tabSelectionButton.getHeight();
		
		double availableWidth = width - x-tabSelectionButtonWidth;//minus tab selection button width?
		List<RfxTabButton> visibleTabButtons = getVisibleTabButtons(tabButtons, availableWidth);
		
		tabSelectionButton.setVisible(visibleTabButtons.size() < tabButtons.size());
		positionInArea(tabSelectionButton, width - tabSelectionButtonWidth, y,
				tabSelectionButtonWidth, tabSelectionButtonHeight, IGNORE_BASE_LINE,
				Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);
		
		for (RfxTabButton tabButton : tabButtons) {
			if (visibleTabButtons.contains(tabButton)) {
				tabButton.setVisible(true);
				double tabButtonWidth = tabButton.getWidth();
				double tabButtonHeight = tabButton.getHeight();
				double remainingWidth = width - x - tabButtonWidth;
				if (tabSelectionButton.isVisible()) {
					remainingWidth -=tabSelectionButtonWidth;
				}
				if (remainingWidth < 0) {
					tabButtonWidth = width - x;
					if (tabSelectionButton.isVisible()) {
						tabButtonWidth = tabButtonWidth-tabSelectionButtonWidth;
					}
				}
				tabButton.resize(tabButtonWidth, tabButtonHeight);
				positionInArea(tabButton, x, y, tabButtonWidth, tabButtonHeight, IGNORE_BASE_LINE,
						Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);
				x += tabButtonWidth;
			} else {
				tabButton.setVisible(false);
			}
		}
		

	}

	private List<RfxTabButton> getVisibleTabButtons(List<RfxTabButton> tabButtons,
			double availableWidth) {
		List<RfxTabButton> visibleTabButtons = new ArrayList<>(tabButtons);
		int selectedTabButtonIndex = 0;
		RfxTabButton selectedTabButton = getTabButton(selectedTabProperty.get());
		if (selectedTabButton != null) {
			selectedTabButtonIndex = visibleTabButtons.indexOf(selectedTabButton);
		}

		double visibleTabButtonsWidth = getTabButtonsWidth(visibleTabButtons);

		while (visibleTabButtonsWidth > availableWidth && visibleTabButtons.size() > 1) {
			int nrOfTabButtonsBeforeSelected = selectedTabButtonIndex;
			int nrOfTabButtonsAfterSelected = visibleTabButtons.size() - selectedTabButtonIndex - 1;
			boolean hasButtonsToRemoveBeforeSelected = nrOfTabButtonsBeforeSelected > 0;
			boolean hasButtonsToRemoveAfterSelected = nrOfTabButtonsAfterSelected > 0;
			boolean removeBefore = hasButtonsToRemoveBeforeSelected
					&& ((nrOfTabButtonsBeforeSelected > nrOfTabButtonsAfterSelected)
							|| !hasButtonsToRemoveAfterSelected);
			if (removeBefore) {
				RfxTabButton firstVisibleButton = visibleTabButtons.get(0);
				visibleTabButtonsWidth -= firstVisibleButton.getWidth();
				visibleTabButtons.remove(firstVisibleButton);
			} else {
				RfxTabButton lastVisibleButton = visibleTabButtons
						.get(visibleTabButtons.size() - 1);
				visibleTabButtonsWidth -= lastVisibleButton.getWidth();
				visibleTabButtons.remove(lastVisibleButton);
			}
		}
		return visibleTabButtons;

	}

	private double getTabButtonsWidth(List<RfxTabButton> visibleTabButtons) {
		double totalTabButtonsWidth = 0;
		for (RfxTabButton tabButton : visibleTabButtons) {
			totalTabButtonsWidth += tabButton.getWidth();
		}
		return totalTabButtonsWidth;
	}

	private void resizeTabButtons(List<RfxTabButton> tabButtons) {
		for (RfxTabButton tabButton : tabButtons) {
			tabButton.autosize();
		}
	}

	private RfxTabButton getTabButton(View tabToFind) {
		List<RfxTabButton> tabButtons = getTabButtons();
		for (RfxTabButton tabButton : tabButtons) {
			if (tabButton.getTab().equals(tabToFind)) {
				return tabButton; // TODO create tabButtonsAndTabs HashMap
			}
		}
		return null;
	}

	private RfxPrimaryButton createTabMenuButton() {
		RfxPrimaryButton tabMenuButton = new RfxPrimaryButton(FontAwesomeIconName.ELLIPSIS_V);
		// tabMenuButton.setOnAction(this::onMenuButtonAction);
		return tabMenuButton;
	}

	private JFXButton createTabSelectionButton() {
		RfxPrimaryButton tabSelectionButton = new RfxPrimaryButton(FontAwesomeIconName.FILES_ALT);
		//TODO use de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.CLONE when supported by newer version of jfoenix (fontawesomefx8.9)
		tabSelectionButton.setOnAction(this::onTabSelectionButtonAction); 
		return tabSelectionButton;
	}
	
	public void onTabSelectionButtonAction(ActionEvent actionEvent) {
		showSelectTabPopUp();
	}

	private void showSelectTabPopUp() {
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		RfxUserinterfaceController userInterfaceController=userInterfaceContainer.get(RfxUserinterfaceController.class);
		ViewContainer<View> viewContainer = userInterfaceController.getViewContainer();
		
		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);
		RfxItemTreeView itemTreeView=new RfxItemTreeView(rootNode);
		
		for (int i=0;i<viewContainer.getViewCount(); i++) {
			View view=viewContainer.getView(i);
			SelectTabItem selectTabItem=new SelectTabItem(languageProvider, viewContainer, view);
			TreeItem<Item> selectTabNode = new TreeItem<>(selectTabItem);
			rootNode.getChildren().add(selectTabNode);
		};
		
		JFXPopup popup = new JFXPopup();
		popup.setPopupContent(itemTreeView);
		popup.setAnchorLocation(AnchorLocation.CONTENT_TOP_RIGHT);
		popup.show(tabSelectionButton);
	}

	private JFXButton createMainMenuButtton() {
//		 RfxPrimaryButton mainMenuButton = new RfxPrimaryButton(FontAwesomeIconName.BARS);
//		 mainMenuButton.setOnAction(this::onMainMenuButton);
//		 return mainMenuButton;

		JFXButton button = new JFXButton();
		FontAwesomeIcon icon = new FontAwesomeIcon();
		icon.setIcon(de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.BARS);
		icon.setSize("17px");
		String iconStyle = icon.getStyle() + ";"
				+ new RfxStyleProperties().setFill(MaterialColorSetCssName.PRIMARY.FOREGROUND1()).toString();
		icon.setStyle(iconStyle);

		// RfxFontIcon icon=new RfxFontIcon(FontAwesomeIconName.BARS, 16,
		// Color.WHITE);
		button.setGraphic(icon);
		button.setPadding(new Insets(8, 16, 8, 17));
		// button.getStyleClass().add("button-flat");
		button.setOnAction(this::onMainMenuButton);
		// TODO the above in RfxPrimaryButton and move it to the appbar package
		return button;

	}

	private void onMainMenuButton(ActionEvent event) {
		mainMenuVisibleProperty.set(!mainMenuVisibleProperty.get());
	}

}
