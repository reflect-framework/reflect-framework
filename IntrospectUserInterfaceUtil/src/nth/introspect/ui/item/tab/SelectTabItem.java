package nth.introspect.ui.item.tab;

import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;

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
