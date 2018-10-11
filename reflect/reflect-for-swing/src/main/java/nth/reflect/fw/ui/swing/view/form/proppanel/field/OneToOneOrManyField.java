package nth.reflect.fw.ui.swing.view.form.proppanel.field;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.ui.item.method.menu.FormFieldMenuItems;
import nth.reflect.fw.ui.swing.item.popupmenu.PopupMenu;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldWidth;

//public class OneToOneField extends DropDownTextField implements Refreshable {
public class OneToOneOrManyField extends DropDownTextfield<JTextField> implements  PropertyField {

	private static final long serialVersionUID = -567238728222479488L;
	private final PropertyValueModel propertyValueModel;
	private final FormView formView;
	private boolean allowTextChange;

	public OneToOneOrManyField(FormView formView,
			PropertyValueModel propertyValueModel) {
		this.formView = formView;
		this.propertyValueModel = propertyValueModel;
		this.allowTextChange = false;
	}

	private Action createDropDownButtonAction() {
		return new AbstractAction() {

			private static final long serialVersionUID = 3204962085027822468L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Collection<Item> items = new FormFieldMenuItems(formView,
								propertyValueModel,
								propertyValueModel.getPropertyInfo());
				PopupMenu popupmenu = new PopupMenu(items);
				popupmenu.show((Component) e.getSource(), 17, -3);
			}
		};

	}

	
	
	@Override
	public JButton createDropDownButton() {
		JButton button=super.createDropDownButton();
		button.setAction(createDropDownButtonAction());
		return button;
	}

	public JTextField createTextField() {
		final JTextField textField = super.createTextField();
		
		textField.addKeyListener(createKeyListenerToOpenPopUpMenu());
		
		textField.addMouseListener(createMouseListenerToOpenPopUpMenu());
		
		//add document filter so that the user can't manipulate the textfield, but still acts as a textfield (focusable, cursor, enabled disabled look, etc)
		AbstractDocument document = (AbstractDocument) textField.getDocument();
		document.setDocumentFilter(createImmutableDocumentFilter());
		return textField;
	}

	private DocumentFilter createImmutableDocumentFilter() {
		return new DocumentFilter() {

			@Override
			public void remove(FilterBypass fb, int offset, int length)
					throws BadLocationException {
				if (allowTextChange) {
					super.remove(fb, offset, length);
				}
			}

			@Override
			public void insertString(FilterBypass fb, int offset,
					String string, AttributeSet attr)
					throws BadLocationException {
				if (allowTextChange) {
					super.insertString(fb, offset, string, attr);
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length,
					String text, AttributeSet attrs)
					throws BadLocationException {
				if (allowTextChange) {
					super.replace(fb, offset, length, text, attrs);
				}
			}

		};
	}

	private MouseListener createMouseListenerToOpenPopUpMenu() {
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton button = getDropDownButton();
				Action action = button.getAction();
				ActionEvent actionEvent=new ActionEvent(button, 0, "showDropDownMenu");
				action.actionPerformed(actionEvent);
			}
		};
	}

	private KeyListener createKeyListenerToOpenPopUpMenu() {
		return new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				JButton button = getDropDownButton();
				Action action = button.getAction();
				ActionEvent actionEvent=new ActionEvent(button, 0, "showDropDownMenu");
				action.actionPerformed(actionEvent);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
	}


	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.SMALL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ClassInfo classInfo = reflectionProvider.getClassInfo(propertyValueModel
				.getValueType());
		String title = classInfo.getTitle(propertyValue);
		allowTextChange = true;
		getTextField().setText(title);
		allowTextChange = false;
		// TODO description?
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		getTextField().setEnabled(enabled);
	}

}
