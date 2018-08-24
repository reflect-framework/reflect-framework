package nth.reflect.fw.ui.swing.view.form.field;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nth.reflect.fw.ui.swing.icon.IconFactory;
import nth.reflect.fw.ui.swing.image.ReflectImage;

@SuppressWarnings("serial")
public class DropDownTextfield<T extends JComponent> extends JPanel {

	private final JButton dropDownButton;
	private final T textField;
	private final JTextField TEXT_FIELD_DEFAULT = new JTextField();

	public DropDownTextfield() {
		setLayout(new BorderLayout());
		setBorder(TEXT_FIELD_DEFAULT.getBorder());

		textField = createTextField();
		add(textField, BorderLayout.CENTER);
		dropDownButton = createDropDownButton();
		add(dropDownButton, BorderLayout.EAST);

	}

	@Override
	public Dimension getPreferredSize() {
		int width = (int) super.getPreferredSize().getWidth();
		int height = (int) TEXT_FIELD_DEFAULT.getPreferredSize().getHeight();
		return new Dimension(width, height);
	}

	public T createTextField() {
		JTextField textField = new JTextField();
		textField.setBorder(BorderFactory.createEmptyBorder(1,2,1,1));
		return (T) textField;
	}

	protected void fireDropDown() {
		Action action = getDropDownButton().getAction();
		if (action != null) {
			action.actionPerformed(new ActionEvent(getDropDownButton(), 0, null));
		}
	}

	public JButton createDropDownButton() {
		final Icon icon1 = IconFactory
				.create(ReflectImage.BUTTON_DROPDOWN_1);
		final Icon icon2 = IconFactory
				.create(ReflectImage.BUTTON_DROPDOWN_2);
		final Icon icon3 = IconFactory
				.create(ReflectImage.BUTTON_DROPDOWN_3);
		final Icon icon4 = null;// disabled: no MaterialAppBarIcon visible
		JButton button = new JButton(icon1) {
			@Override
			public void setEnabled(boolean b) {
				setIcon(isEnabled() ? icon1 : icon4);
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
		button.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		button.setFocusable(false);
		button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		return button;
	}

	public JButton getDropDownButton() {
		return dropDownButton;
	}

	public T getTextField() {
		return textField;
	}

}
