package nth.introspect.ui.item.tab;

import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.item.Item;

public class SelectTabItem extends Item {

	private final View view;

	public SelectTabItem(final View view) {
		@SuppressWarnings("unchecked")
		final ViewContainer<View> viewContainer = Introspect.getUserInterfaceProvider().getViewContainer();
		this.view = view;
		setAction(new Action() {
			@Override
			public void run() {
				viewContainer.selectView(view);
			}
		});
	}
	public String getText() {
		return view.getViewTitle();
	};

	@Override
	public String getDescription() {
		return view.getViewDescription();
	}

	@Override
	public URI getIconURI() {
		return view.getViewIconURI();
	}

}
