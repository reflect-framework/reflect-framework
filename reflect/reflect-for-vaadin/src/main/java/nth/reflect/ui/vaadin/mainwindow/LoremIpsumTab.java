package nth.reflect.ui.vaadin.mainwindow;

import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.tab.Tab;

public class LoremIpsumTab extends Tab {

	private static final long serialVersionUID = -1584900886816315646L;
	private final String title;

	public LoremIpsumTab(String title) {
		this.title = title;
		new StyleBuilder().setOverflow(Overflow.AUTO).setPadding(20).setFor(this);
		StringBuilder text = new StringBuilder();
		text.append(title);
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
	public String getDisplayName() {
		return title;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

}
