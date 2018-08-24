package nth.reflect.fw.ui.swing.image;

/**
 * 
 * <p>
 * Provides a list of standard images used by the ReflectFramework. These URI's
 * are for internal use only. To convert them to a real world URI use:
 * {@link IconUriClassResource}.
 * </p>
 * E.G.: URI uri=new {@link IconUriClassResource}(
 * {@link ReflectImage#MENU_OPENED}).getAbsoluteURI()
 * 
 * @author nilsth
 *
 * @deprecated Use FontAwesomeUrl (https://fortawesome.github.io/Font-Awesome/)
 *             instead (because it vector based so scales better and is faster
 *             than images)
 *
 */
public class ReflectImage {

	public static final String SCHEMA = nth.reflect.fw.ui.swing.image.IconUriClassResource.SCHEMA;
	public static final String PATH = SCHEMA + "://nth.reflect.fw.ui.images.ReflectImage/";
	public static final String EDIT_COPY2 = PATH + "edit_copy.png";

	public static final String EDIT_COPY = PATH + "edit_copy.png";
	public static final String EDIT_PASTE = PATH + "edit_paste.png";
	public static final String EDIT_FIND = PATH + "edit_find.png";

	public static final String MENU_OPENED = PATH + "menu_opened.png";
	public static final String MENU_CLOSED = PATH + "menu_closed.png";

	public static final String BUTTON_ROUND_ABOUT = PATH + "button_round_about.png";// remove
																					// about.png???
	public static final String BUTTON_ROUND_QUIT = PATH + "application_quit.png";
	public static final String BUTTON_ROUND_PLUS = PATH + "button_round_plus.png";
	public static final String BUTTON_ROUND_MINUS = PATH + "button_round_minus.png";
	public static final String BUTTON_ROUND_EXPAND = PATH + "button_round_expand.png";
	public static final String BUTTON_ROUND_COLLAPSE = PATH + "button_round_collapse.png";
	public static final String BUTTON_ROUND_CANCEL = PATH + "button_round_close.png";

	public static final String BUTTON_DROPDOWN_1 = PATH + "btn_dropdown_1.png";// TODO
																				// replace
																				// with
																				// a
																				// little
																				// triangle
																				// or
																				// round
																				// button.
																				// than
																				// remove
																				// btn_drop*.*
	public static final String BUTTON_DROPDOWN_2 = PATH + "btn_dropdown_2.png";
	public static final String BUTTON_DROPDOWN_3 = PATH + "btn_dropdown_3.png";

	public static final String TABS = PATH + "tabs.png";
	public static final String TABS_CLOSE_THIS = PATH + "tab_menu_close_this.png";
	public static final String TABS_CLOSE_ALL = PATH + "tab_menu_close_all.png";
	public static final String TABS_CLOSE_OTHERS = PATH + "tab_menu_close_other.png";

}
