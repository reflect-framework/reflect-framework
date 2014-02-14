package nth.introsepect.ui.swing.view.form.field;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

import nth.introsepect.ui.swing.item.popupmenu.PopupMenu;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.provider.userinterface.view.FormView;
import nth.introspect.ui.item.Item;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.valuemodel.PropertyValueModel;

public class OneToOneField extends DropDownTextField implements Refreshable {

	private static final long serialVersionUID = -567238728222479488L;
	private final PropertyValueModel propertyValueModel;
	private final  FormView formView;

	public OneToOneField(FormView formView, PropertyValueModel propertyValueModel) {
		super();
		this.formView = formView;
		this.propertyValueModel = propertyValueModel;
		refresh();

		JButton button = getDropDownButton();
		button.setAction(createDropDownButtonAction());

	}

	private Action createDropDownButtonAction() {
		return new AbstractAction() {

			private static final long serialVersionUID = 3204962085027822468L;

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Item> items = ItemFactory.createFormViewRelationalFieldItems(formView, propertyValueModel);
				PopupMenu popupmenu = new PopupMenu(items);
				popupmenu.show((Component) e.getSource(), 17, -3);
			}
		};

	}

	@Override
	public void refresh() {
		// set text
		DomainProvider domainProvider = Introspect.getDomainProvider();
		ClassInfo classInfo = domainProvider.getClassInfo(propertyValueModel.getValueType());
		Object propertyValue = propertyValueModel.getValue();
		String title = classInfo.getTitle(propertyValue);
		setText(title);
		// TODO description?
		setEnabled(propertyValueModel.canSetValue());

	}

}
