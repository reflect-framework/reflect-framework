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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.button.RfxContentButton;
import nth.reflect.javafx.control.button.RfxPrimaryButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.view.form.RfxDomainPropertyPane;
import nth.reflect.javafx.control.view.form.RfxFormView;
import nth.reflect.javafx.control.view.table.RfxTableView;
import nth.reflect.javafx.control.window.RfxWindow;
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
	private final RfxPrimaryButton tabSelectionButton;

	public RfxAppButtonBar(UserInterfaceContainer userInterfaceContainer) {
		rfxWindow = userInterfaceContainer.get(RfxWindow.class);
		mainMenuVisibleProperty = rfxWindow.getMainMenuVisibleProperty();
		mainMenuVisibleProperty.addListener(this::onMainMenuVisibleChanged);
		extraWideBinding = rfxWindow.getExtraWideBinding();
		tabsProperty = rfxWindow.getTabsProperty();
		tabsProperty.addListener(this::onTabsChanged);
		selectedTabProperty = rfxWindow.getSelectedTabProperty();
		selectedTabProperty.addListener(this::onSelectedTabChanged);

		//mainMenuButton = createMainMenuButtton();
		mainMenuButton=createMainMenuButtton();
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
	
	public void onSelectedTabChanged(ObservableValue<? extends View> observable, View oldValue, View newValue) {
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

	@Override
	protected void layoutChildren() {
		double width = getWidth();
		double height = getHeight();
		boolean snapToPixel = isSnapToPixel();
		double x = 0;
		double y = 0;

		// double mainMenuButtonWidth = mainMenuButton.getMinWidth();
		// double mainMenuButtonHeight = mainMenuButton.getMinHeight();
		// mainMenuButton.resize(mainMenuButtonWidth, mainMenuButtonHeight);
		mainMenuButton.autosize();
		double mainMenuButtonWidth = mainMenuButton.getWidth();
		double mainMenuButtonHeight = mainMenuButton.getHeight();
		positionInArea(mainMenuButton, x, y, mainMenuButtonWidth, mainMenuButtonHeight,
				0/* ignore baseline */, Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);
		x += mainMenuButton.getWidth();

		if (indentNextToMenuIfEnoughSpace()) {
			x = RfxWindow.MENU_WIDTH;
		}

		List<RfxTabButton> tabButtons = getTabButtons();
		resizeTabButtons(tabButtons);

		double availableWidth = width - x;
		List<RfxTabButton> visibleTabButtons = getVisibleTabButtons(tabButtons, availableWidth);

		tabSelectionButton.setVisible(visibleTabButtons.size()<tabButtons.size());
		tabSelectionButton.autosize();
		positionInArea(tabSelectionButton, width-tabSelectionButton.getWidth(), y, tabSelectionButton.getWidth(), tabSelectionButton.getHeight(), IGNORE_BASE_LINE,
				Insets.EMPTY, HPos.LEFT, VPos.TOP, snapToPixel);

		
		for (RfxTabButton tabButton : tabButtons) {
			if (visibleTabButtons.contains(tabButton)) {
				tabButton.setVisible(true);
				double tabButtonWidth = tabButton.getWidth();
				double tabButtonHeight = tabButton.getHeight();
				double remainingWidth = width-x-tabButtonWidth;
				if (remainingWidth<0) {
					tabButtonWidth=width-x;
					if (tabSelectionButton.isVisible()) {
						tabButtonWidth-=tabSelectionButton.getWidth();
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
			boolean hasButtonsToRemoveBeforeSelected = nrOfTabButtonsBeforeSelected>0;
			boolean hasButtonsToRemoveAfterSelected = nrOfTabButtonsAfterSelected>0;
			boolean removeBefore = hasButtonsToRemoveBeforeSelected
					&& ((nrOfTabButtonsBeforeSelected > nrOfTabButtonsAfterSelected)
							|| !hasButtonsToRemoveAfterSelected);
//			System.out.println("hasButtonsToRemoveBeforeSelected="+hasButtonsToRemoveBeforeSelected+" nrOfTabButtonsBeforeSelected="+nrOfTabButtonsBeforeSelected+" nrOfTabButtonsAfterSelected="+nrOfTabButtonsAfterSelected+" hasButtonsToRemoveAfterSelected="+hasButtonsToRemoveAfterSelected+" removeBefore="+removeBefore);
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

	private boolean indentNextToMenuIfEnoughSpace() {
		return mainMenuVisibleProperty.get() && extraWideBinding.get();
	}


	private RfxPrimaryButton createTabMenuButton() {
		RfxPrimaryButton tabMenuButton = new RfxPrimaryButton(
				FontAwesomeIconName.ELLIPSIS_V);
		// tabMenuButton.setOnAction(this::onMenuButtonAction);
		return tabMenuButton;
	}

	private RfxPrimaryButton createTabSelectionButton() {
		RfxPrimaryButton tabSelectionButton = new RfxPrimaryButton(
				FontAwesomeIconName.CLONE);
		createSelectTabPopUp(tabSelectionButton);
		// menuButton.setOnAction(this::onMenuButtonAction);
		return tabSelectionButton;
	}


	
	private JFXButton createMainMenuButtton() {
//		RfxPrimaryButton mainMenuButton = new RfxPrimaryButton(
//				FontAwesomeIconName.BARS);
//		mainMenuButton.setOnAction(this::toggleMainMenuVisibility);
		
		JFXButton button = new JFXButton();
		 FontAwesomeIcon icon = new FontAwesomeIcon();
		 icon.setIcon(de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName.BARS);
		 icon.setSize("17px");
		 String iconStyle = icon.getStyle()+";"+new RfxStyleProperties().setFill(Color.WHITE).toString();
		 icon.setStyle(iconStyle);
		
//		RfxFontIcon icon=new RfxFontIcon(FontAwesomeIconName.BARS, 16, Color.WHITE);
		button.setGraphic(icon);
		 	button.	setPadding(new Insets(8, 16, 8, 17));
		//button.getStyleClass().add("button-flat");
		button.setOnAction(this::toggleMainMenuVisibility);
//	TODO the above in RfxPrimaryButton and move it to the appbar package
		return button;
		//return mainMenuButton;
	}

	private void toggleMainMenuVisibility(ActionEvent event) {
		mainMenuVisibleProperty.set(!mainMenuVisibleProperty.get());
	}

	
	private void createSelectTabPopUp(RfxContentButton tabSelectionButton) {
		StackPane root = new StackPane();
		JFXListView<String> list = new JFXListView<String>();
		for(int i = 1 ; i < 5 ; i++) list.getItems().add("Item " + i);
		 
		JFXPopup popup = new JFXPopup();
		popup.setPopupContent(list);
		tabSelectionButton.setOnMouseClicked((e)-> popup.show(tabSelectionButton));
	}
}
