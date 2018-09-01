package nth.reflect.fw.ui.item.tab;

import java.net.URL;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class SelectTabItem extends Item {

	private final View view;

	public SelectTabItem(LanguageProvider languageProvider, final ViewController<View> viewContainer ,  final View view) {
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
	public URL getIconURL() {
		return view.getViewIconURL();
	}

}
