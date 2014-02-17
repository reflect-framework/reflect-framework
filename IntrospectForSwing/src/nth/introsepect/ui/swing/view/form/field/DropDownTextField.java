package nth.introsepect.ui.swing.view.form.field;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import nth.introsepect.ui.swing.icon.IconFactory;
import nth.introspect.ui.images.IntrospectImage;

@SuppressWarnings("serial")
public class DropDownTextField extends JTextField {
	
	private final JButton dropDownButton;

	public DropDownTextField() {
		dropDownButton = createDropDownButton();
		setLayout(new BorderLayout());
		add(getDropDownButton(), BorderLayout.EAST);
//		setDocument(new PlainDocument() {//FIXME: this will disable setText, but is needed to prevent the user from changing the text value
//
//			@Override
//			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
//				// dont change text. We want a read only text field, but with a enabled look and feel
//				fireDropDown();
//			}
//
//		});

	}

	protected void fireDropDown() {
		Action action = getDropDownButton().getAction();
		if (action != null) {
			action.actionPerformed(new ActionEvent(getDropDownButton(), 0, null));
		}
	}

	private JButton createDropDownButton() {
		final Icon icon1 = IconFactory.create(IntrospectImage.BUTTON_DROPDOWN_1);
		final Icon icon2 = IconFactory.create(IntrospectImage.BUTTON_DROPDOWN_2);
		final Icon icon3 = IconFactory.create(IntrospectImage.BUTTON_DROPDOWN_3);
		final Icon icon4 = null;//disabled: no icon visible
		JButton button = new JButton(icon1) {
			@Override
			public void setEnabled(boolean b) {
				setIcon(isEnabled() ? icon1 : icon4); //FIXME drop down button is invisible when clicked on when field is disabled. Button needs to remain active when field is disabled!
				super.setEnabled(b);
			}
		};
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setIcon(isEnabled() ? icon1 : icon4);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setIcon(isEnabled() ? icon3 : icon4);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setIcon(isEnabled() ? icon1 : icon4);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setIcon(isEnabled() ? icon2 : icon4);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocus();
			}
		});
		button.setBorder(null);
		button.setFocusable(false);
		button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		return button;
	}

	public JButton getDropDownButton() {
		return dropDownButton;
	}

	
}
