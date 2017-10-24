package nth.reflect.javafx.control.tabpane;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.effects.JFXDepthManager;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.style.MaterialColors;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarButton;
import nth.reflect.javafx.control.window.RfxWindow;
import nth.reflect.javafx.control.window.appbar.RfxAppBar;
import nth.reflect.javafx.control.window.mainmenu.RfxMainMenuPane;
/**
 * TODO: remove this class by moving its contents to {@link RfxWindow} class
 * @author nilsth
 *
 */
public class RfxTabBarPane extends BorderPane {

	
	private final ObservableList<View> tabs;
	private final ObjectPropertyBase<View> selectedTabProperty;
	
	private RfxMenuAndContentPane menuAndContentPane;
	private RfxTabButtonBar tabButtonBar;

	
	public RfxTabBarPane(UserInterfaceContainer userInterfaceContainer, RfxMainMenuPane menuPane) {
		tabs = FXCollections.<View>observableArrayList();
		tabs.addListener(this::onTabsChanged);
		selectedTabProperty = new SimpleObjectProperty<>();
		selectedTabProperty.addListener(this::onSelectedTabChanged);

		tabButtonBar = new RfxTabButtonBar(this);
		BorderPane appBar = new RfxAppBar( userInterfaceContainer, tabButtonBar);
		setTop(appBar);
		
		menuAndContentPane = new RfxMenuAndContentPane(userInterfaceContainer, menuPane);
		setCenter(menuAndContentPane);
	}

	public void onTabsChanged(Change change) {
		if (tabs.size() == 0) {
			selectedTabProperty.set(null);
			updateTabs();
		} else if (!change.getList().contains(selectedTabProperty.get())) {
			selectNewTab(change);
		} else {
			updateTabs();
		}
	}

	private void updateTabs() {
		tabButtonBar.update(tabs);
		updateContent();
	}

	private void updateContent() {
		View selectedTab = selectedTabProperty.get();
		if (selectedTab==null) {
			menuAndContentPane.clearContentPane();
		} else {
			menuAndContentPane.setContentPane((Node)selectedTab);
		}
	}

	private void selectNewTab(Change<View> change) {
		while (change.next()) {

			if (change.wasAdded()) {
				List<View> added = change.getAddedSubList();
				View lastAdded = added.get(added.size() - 1);
				selectedTabProperty.set(lastAdded);// this also calls
													// updateTabBar();
			} else if (change.wasRemoved()) {
				int newIndex = tabs.indexOf(selectedTabProperty.get()) - 1;
				if (newIndex < 0) {
					newIndex = 0;
				}
				View selectedTab = change.getList().get(newIndex);
				selectedTabProperty.set(selectedTab);// this also calls
														// updateTabBar();
			} else {
				// mutated or pre-mutated (changed order)
				updateTabs();
			}
		}
	}

	public void onSelectedTabChanged(Observable observable) {
		updateTabs();
	}
	
	public ObjectProperty<View> getSelectedTabProperty() {
		return selectedTabProperty;
	}

	public ObservableList<View> getTabs() {
		return tabs;
	}
}
