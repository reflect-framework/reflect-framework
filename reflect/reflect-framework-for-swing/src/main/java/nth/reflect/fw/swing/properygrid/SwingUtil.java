package nth.reflect.fw.swing.properygrid;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import nth.reflect.fw.layer1userinterface.controller.Refreshable;

public class SwingUtil {
	public static final int GRID_HEIGHT = 20;
	public static final Font FONT = new Font("Universe", Font.PLAIN, 18);

	
	public static <T extends Component> T findParentComponentOfType(Component component, Class<T> type) {
		do {
			component = component.getParent();
			if (component != null && type.isAssignableFrom(component.getClass())) {
				return (T) component;
			}
		} while (component != null);

		return null;
	}

	public static List<Component> findChildComponentsOfType(Component component, Class<?> type) {
		List<Component> foundComponents = new ArrayList<Component>();
		if (component instanceof Container) {
			Container container = (Container) component;
			for (Component child : container.getComponents()) {
				if (type.isAssignableFrom(child.getClass())) {
					foundComponents.add(child);
				}
				foundComponents.addAll(findChildComponentsOfType(child, type));// recursive
			}
		}
		return foundComponents;
	}

	public static int getButtonHeight() {
		return 20;
	}

	public static Rectangle getScreenSize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		return new Rectangle(0, 0, d.width, d.height);
	}

	public static Component findComponent(Container container, String componentName) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (componentName.equals(component.getName())) {
				return component;
			} else {
				if (component instanceof Container) {
					Container childContainer = (Container) component;
					component = findComponent(childContainer, componentName);
					if (component != null) {
						return component;
					}
				}
			}
		}
		return null;
	}

	public static void setUIFont(FontUIResource f) {
		//
		// sets the default FONT for all Swing components.
		// ex.
		// setUIFont (new javax.swing.plaf.FontUIResource
		// ("Serif",Font.ITALIC,12));
		//
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
				UIManager.put(key, f);
		}
	}

	public static void scrollToComponent(Component cmp) {
		Container parent = cmp.getParent();
		while (parent != null) {
			if (parent instanceof JScrollPane) {
				JScrollPane pane = (JScrollPane) parent;
				Point location = getRelativeLocation(pane.getViewport().getView(), cmp);
				Rectangle bounds = cmp.getBounds();
				bounds.setLocation(location);
				pane.getViewport().scrollRectToVisible(bounds);
				pane.repaint();
			}
			parent = parent.getParent();
		}
	}

	/**
	 * Return relative location between root and child components.
	 * 
	 * <pre>
	 * &lt;b&gt;root&lt;/b&gt;
	 *   component
	 *     component
	 *       &lt;b&gt;child&lt;/b&gt;
	 * </pre>
	 * 
	 * Between root and child may contains other components, but root must be one of ancestor of child, or euals to child.
	 * <p/>
	 * This method differs from {@link Component#getLocation()} with compute relative location from specified parent element, but not direclty one only.
	 * 
	 * @param root
	 *            Root of child
	 * @param component
	 *            Child component
	 * @return Location relative to specified root
	 * @see Component#getLocation()
	 */
	public static Point getRelativeLocation(Component root, Component component) {
		if (root == null) {
			throw new IllegalArgumentException("root is null");
		}
		if (component == null) {
			throw new IllegalArgumentException("component is null");
		}
		Point location = component.getLocation();
		while (!component.equals(root)) {
			component = component.getParent();
			if (component == null) {
				throw new IllegalArgumentException("root is not ancestor of component");
			}
			Point parentLocation = component.getLocation();
			location.translate(parentLocation.x, parentLocation.y);
		}
		return location;
	}

	public static void showAsOnlyVisibleChild(JComponent container, Component childToBeMadeVisible) {
		for (Component child : container.getComponents()) {
			boolean visible = child.equals(childToBeMadeVisible);
			child.setVisible(visible);
			if (visible) {
				container.getLayout().addLayoutComponent(BorderLayout.CENTER, child);
			} else {
				container.getLayout().removeLayoutComponent(child);
			}
			child.repaint();
		}
		container.revalidate();
		container.repaint();
	}

	public static void refreshComponentAndItsChildren(Component component) {
		if (component instanceof Container) {
			//recursive call to refresh children first
			Container container = (Container) component;
			Component[] children = container.getComponents();
			for (Component child : children) {
				refreshComponentAndItsChildren(child);
			}
		}
		if (component instanceof Refreshable) {
			Refreshable refreshable = (Refreshable) component;
			refreshable.refresh();
		}
	}

}
