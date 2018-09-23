package nth.reflect.fw.ui.swing.properygrid;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import nth.reflect.fw.ui.swing.style.ColorUtil;
import nth.reflect.fw.ui.swing.view.form.proppanel.PropertyPanel;

public class PropertyGrid extends JScrollPane {
	private static final long serialVersionUID = 1821162902242037807L;
	// private static final String USE_METHOD_ADD_PROPERTY_ROW_INSTEAD = "Do not use this method. Use the addPropertyRow method!";
	// private static final String TREE_EXPANDED_ICON_KEY = "Tree.expandedIcon";
	// private static final String TREE_COLLAPSED_ICON_KEY = "Tree.collapsedIcon";
	
	private static JPanel propertyGridPanel;

	public PropertyGrid() {
		super(createPropertyGridPanel());
		// setLayout(new PropertyGridLayout());
		// setBackground(getLabelBackground());
	}

	private static Component createPropertyGridPanel() {
		propertyGridPanel = new JPanel();
		propertyGridPanel.setLayout(new PropertyGridLayout());
		propertyGridPanel.setBackground(ColorUtil.getLightColor());
		return propertyGridPanel;
	}

	public void addPropertyRow(JPanel panel) {
		if (panel instanceof PropertyPanel) {
			setMnenomic((PropertyPanel) panel);
		}
		propertyGridPanel.add(panel);
		propertyGridPanel.add(new PropertySeperatorRow());
	}

	private void setMnenomic(PropertyPanel propertyPanel) {//FIXME doesn't work
		List<Character> mnenomics=getMnenomics();
		JLabel label = propertyPanel.getPropertyLabel();
		String labelText = label.getText();
		// try to find a unique mnenomic by trying the first letter of each word
		StringTokenizer tokenizer=new StringTokenizer(labelText," ");//Separate on words
		while (tokenizer.hasMoreTokens() ) {
			String word=tokenizer.nextToken().trim();
			if (word.length()>0) {
				char ch = Character.toUpperCase(word.charAt(0));
				if (!mnenomics.contains(ch)) {
					label.setDisplayedMnemonic(ch);
					return;
				}
			}
		}
		// try to find unique mnenomic by trying all the letters
		for (int i=0;i<labelText.length();i++) {
			char ch = Character.toUpperCase(labelText.charAt(i));
			if (Character.isLetterOrDigit(ch) && !mnenomics.contains(ch)) {
				label.setDisplayedMnemonic(ch);
				return;
			}
			
			
		}
	}

	private List<Character> getMnenomics() {
		List<Character> mnemonics=new ArrayList<Character>();
		for (Component child:propertyGridPanel.getComponents()) {
			if (child instanceof PropertyPanel) {
				PropertyPanel propertyPanel = (PropertyPanel) child;
				int mneunomic = propertyPanel.getPropertyLabel().getDisplayedMnemonic();
				mnemonics.add(new Character((char)mneunomic));
			}
		}
		return mnemonics;
	}

	// /**
	// * @deprecated use AddPorperty Method
	// */
	// @Override
	// public Component add(Component comp, int index) {
	// throw new IllegalAccessError(USE_METHOD_ADD_PROPERTY_ROW_INSTEAD);
	// }
	//
	// /**
	// * @deprecated use AddPorperty Method
	// */
	//
	// @Override
	// public void add(Component comp, Object constraints, int index) {
	// throw new IllegalAccessError(USE_METHOD_ADD_PROPERTY_ROW_INSTEAD);
	// }
	//
	// /**
	// * @deprecated use AddPorperty Method
	// */
	//
	// @Override
	// public void add(Component comp, Object constraints) {
	// throw new IllegalAccessError(USE_METHOD_ADD_PROPERTY_ROW_INSTEAD);
	// }
	//
	// /**
	// * @deprecated use AddPorperty Method
	// */
	//
	// @Override
	// public Component add(Component comp) {
	// throw new IllegalAccessError(USE_METHOD_ADD_PROPERTY_ROW_INSTEAD);
	// }
	//
	// /**
	// * @deprecated use AddPorperty Method
	// */
	//
	// @Override
	// public Component add(String name, Component comp) {
	// throw new IllegalAccessError(USE_METHOD_ADD_PROPERTY_ROW_INSTEAD);
	// }
	//
	// /**
	// * @deprecated do not use remove!
	// */
	//
	// @Override
	// public void remove(Component comp) {
	// throw new IllegalAccessError();
	// }
	//
	// /**
	// * @deprecated use AddPorpertyMethod
	// */
	//
	// @Override
	// public void remove(int index) {
	// throw new IllegalAccessError();
	// }
	//
	// /**
	// * @deprecated use AddPorpertyMethod
	// */
	//
	// @Override
	// public void removeAll() {
	// throw new IllegalAccessError();
	// }



}
