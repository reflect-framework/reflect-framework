package nth.reflect.fw.javafx.control.verticalflingscroller;

import javafx.scene.Node;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class NoScrollBarInComponentException extends TranslatableException {

	private static final TranslatableString MESSAGE = new TranslatableString(
			NoScrollBarInComponentException.class.getCanonicalName() + ".message",
			"Could not find the vertical scrollbar in component: %s");

	public NoScrollBarInComponentException(Node node) {
		super(MESSAGE.withParameters(node.getClass().getCanonicalName()));
	}

	private static final long serialVersionUID = -7055036621273012455L;

}
