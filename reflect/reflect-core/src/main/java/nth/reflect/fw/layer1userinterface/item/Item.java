package nth.reflect.fw.layer1userinterface.item;

import java.net.URL;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class Item {

	private Action action;
	private URL iconURL;
	private boolean enabled = true;
	private boolean visible = true;
	private String text;
	private String description;
	private final LanguageProvider languageProvider;

	public Item(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	/**
	 * Constructs a new menu item that can optionally have an icon and a action
	 * associated with it. FontIcon and action can be null, but a caption must be
	 * given.
	 * 
	 * @param text
	 *            The text associated with the action
	 * @param action
	 *            The action to be fired
	 */
	public Item(LanguageProvider languageProvider, String text, URL iconURL, Action action) {

		this.languageProvider = languageProvider;
		if (text == null) {
			throw new IllegalArgumentException("caption cannot be null");
		}
		setText(text);
		setDescription(text);
		setIconURL(iconURL);
		setAction(action);
	}

	public void setIconURL(URL iconURL) {
		this.iconURL = iconURL;
	}

	public Item(LanguageProvider languageProvider, String text, Action action) {
		this.languageProvider = languageProvider;
		if (text == null) {
			throw new IllegalArgumentException("caption cannot be null");
		}
		setText(text);
		setDescription(text);
		setAction(action);
		//TODO setIconURI(iconUri);???
	}

	/**
	 * For the associated action.
	 * 
	 * @return The associated action, or null if there is none
	 */
	public Action getAction() {// TODO re-factor to action that extends runnable
		return action;
	}

	/**
	 * Gets the objects icon.
	 * 
	 * @return The icon of the item, null if the item doesn't have an icon
	 */
	public URL getIconURL() {
		return iconURL;
	}

	public String getText() {
		return languageProvider.getText(text);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isVisible() {
		return visible;
	}

	public String getDescription() {
		String descText = null;
		if (description == null || description.trim().length() == 0) {
			descText = getText();
		} else {
			descText = languageProvider.getText(description);
		}
		return descText;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setText(String text) {
		this.text = text;
	}


	public interface Action extends Runnable {
	}
}
