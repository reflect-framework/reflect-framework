package nth.introspect.ui.item.tab;

import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;

public class SelectTabItem extends Item {

	private final View view;

	public SelectTabItem(LanguageProvider languageProvider, final ViewContainer<View> viewContainer ,  final View view) {
		super(languageProvider);
		this.view = view;
		setAction(new Action() {
			@Override
			public void run() {
				viewContainer.setSelectedView(view);
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
