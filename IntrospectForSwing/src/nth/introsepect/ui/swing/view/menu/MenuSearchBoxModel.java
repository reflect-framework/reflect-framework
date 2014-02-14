package nth.introsepect.ui.swing.view.menu;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;

import nth.introspect.ui.item.HierarchicalItem;
import nth.introspect.ui.item.Item;
import nth.introspect.ui.item.method.MethodItem;
import nth.introspect.ui.item.method.MethodOwnerItem;

@SuppressWarnings("serial")
public class MenuSearchBoxModel extends AbstractListModel implements ComboBoxModel, KeyListener {

	private HierarchicalItem selectedItem;
	static final String SEPERATOR = " - ";
	private List<Item> comboBoxItems = new ArrayList<Item>();
	private List<MethodOwnerItem> menuItems = new ArrayList<MethodOwnerItem>();
	private Object selection;
	private MenuSearchBox menuSearchBox;
	private ComboBoxEditor menuSearchBoxEditor;
	private int currPos = 0;

	public MenuSearchBoxModel(final MenuSearchBox menuSearchBox, List<MethodOwnerItem> menuItems) {

		this.menuSearchBox = menuSearchBox;
		this.menuItems = menuItems;
		this.menuSearchBoxEditor = menuSearchBox.getEditor();
		// here we add the key listener to the text field that the combobox is wrapped around
		this.menuSearchBoxEditor.getEditorComponent().addKeyListener(this);
		menuSearchBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (e.getItem() instanceof HierarchicalItem) {
						selectedItem = (HierarchicalItem) e.getItem();
						menuSearchBoxEditor.setItem(selectedItem.getTextPath(SEPERATOR));
						menuSearchBox.setSelectedItem(selectedItem);
						menuSearchBox.onSelectionChanged(selectedItem);
					}
				}

			}
		});

	}

	public void updateModel(String searchString, boolean autoOpen) {
		comboBoxItems = getMatchingMenuItems(searchString);

		super.fireContentsChanged(this, 0, comboBoxItems.size());

		switch (comboBoxItems.size()) {
		case 0:
			menuSearchBox.setPopupVisible(false);
			break;
		case 1:
			// only one option available
			// menuSearchBox.setPopupVisible(false);
			// selectedItem = comboBoxItems.get(0);
			// menuSearchBoxEditor.setItem(selectedItem.getTextPath(SEPERATOR));//TODO DOES NOT WORK
			// menuSearchBox.setSelectedItem(selectedItem);
			// menuSearchBox.onSelectionChanged(selectedItem);
			break;
		default:
			if (autoOpen) {
				menuSearchBox.setPopupVisible(false);// this is a hack to get around redraw problems when changing the list length of the displayed popups
				menuSearchBox.setPopupVisible(true);
			} else {
				menuSearchBox.setPopupVisible(false);
			}

		}

	}

	private List<Item> getMatchingMenuItems(String searchString) {
		searchString = searchString.toLowerCase(); // TODO remove illegal chars and chop to array on white spaces

		List<Item> matchingItems = new ArrayList<Item>();
		for (MethodOwnerItem serviceObjectItem : menuItems) {
			if (serviceObjectItem.isVisible() && serviceObjectItem.isEnabled()) {
				String serviceObjectText = serviceObjectItem.getText();
				if (matches(serviceObjectText, searchString)) {
					matchingItems.add(serviceObjectItem);// TODO make searchText parts bold
				}
				for (Item item : serviceObjectItem.getChildren()) {
					
					MethodItem methodItem=(MethodItem) item;
					if (methodItem.isVisible() && methodItem.isEnabled()) {
						StringBuffer methodItemText = new StringBuffer();
						methodItemText.append(serviceObjectItem.getText());
						methodItemText.append(SEPERATOR);
						methodItemText.append(methodItem.getText());
						if (matches(methodItemText.toString(), searchString)) {
							matchingItems.add(methodItem);// TODO make searchText parts bold
						}
					}
				}
			}
		}
		return matchingItems;
	}

	private boolean matches(String text, String lowerCaseSearchText) {
		// TODO change method to find, convert text so that searchTexts are bold or return null when not found
		return text.toLowerCase().contains(lowerCaseSearchText);
	}

	public int getSize() {
		return comboBoxItems.size();
	}

	public Object getElementAt(int index) {
		return comboBoxItems.get(index);
	}

	public void setSelectedItem(Object item) {
		selection = item;
	}

	public Object getSelectedItem() {
		return selection;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		String str = menuSearchBoxEditor.getItem().toString();
		JTextField jtf = (JTextField) menuSearchBoxEditor.getEditorComponent();
		currPos = jtf.getCaretPosition();

		if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
			if (e.getKeyCode() != KeyEvent.VK_ENTER) {
				menuSearchBoxEditor.setItem(str);
				jtf.setCaretPosition(currPos);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// menuSearchBoxEditor.setItem(selectedItem.getTextPath(SEPERATOR));
			menuSearchBox.setSelectedItem(selectedItem);
			menuSearchBox.onEnter(selectedItem);
			updateModel("",false);
			menuSearchBoxEditor.setItem("");
		} else {
			updateModel(menuSearchBox.getEditor().getItem().toString(),true);
			menuSearchBoxEditor.setItem(str);
			jtf.setCaretPosition(currPos);
		}
	}

}