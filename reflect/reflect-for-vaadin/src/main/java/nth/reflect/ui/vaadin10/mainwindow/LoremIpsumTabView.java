package nth.reflect.ui.vaadin10.mainwindow;

import java.net.URL;

import com.vaadin.flow.component.tabs.Tab;

import javafx.scene.control.TableView;
import nth.reflect.ui.vaadin10.css.Overflow;
import nth.reflect.ui.vaadin10.css.StyleBuilder;
import nth.reflect.ui.vaadin10.view.TabView;

public class LoremIpsumTabView extends TabView {

	private static final long serialVersionUID = -1584900886816315646L;
	private Tab tab;

	public LoremIpsumTabView(Tab tab) {
		this.tab = tab;
		new StyleBuilder().setOverflow(Overflow.AUTO).setPadding(20).setFor(this);
		StringBuilder text = new StringBuilder();
		text.append(tab.getLabel());
		text.append(":     ");
		for (int i = 0; i < 30; i++) {
			text.append(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ut ante dolor. Integer sit amet efficitur lorem. Etiam scelerisque velit et elementum pulvinar. Phasellus eu nisi vel dui faucibus cursus ac luctus ipsum. Nam ullamcorper ex nisl. Donec lobortis sem ac bibendum ultrices. Ut ullamcorper facilisis consequat. Integer et lacus id urna venenatis placerat. Fusce gravida velit et maximus viverra.");
			// Random rand = new Random();
			// int totalNrOfNewLines = rand.nextInt(3);
			// for (int nrOfNewLines = 0; nrOfNewLines < totalNrOfNewLines;
			// ++nrOfNewLines) {
			// text.append("<br/>");
			// }
		}
		setText(text.toString());
	}

	@Override
	public String getViewTitle() {
		return tab.getLabel();
	}

	@Override
	public String getViewDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getViewIconURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onViewActivate() {
		// TODO Auto-generated method stub
		
	}

}
