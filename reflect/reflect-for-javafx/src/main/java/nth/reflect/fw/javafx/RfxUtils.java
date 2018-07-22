package nth.reflect.fw.javafx;

import java.util.Optional;

import javafx.scene.Parent;

public class RfxUtils {

	public static <T extends Object> Optional<T>  findParent(Parent parentNode,
			Class<T> classToFind) {
		Parent parent = parentNode.getParent();
		
		if (parent==null ) {
			return Optional.empty();
		}
		
		if (parent.getClass()==classToFind) {
			return (Optional<T>) Optional.of(parent);
		}
		
		return findParent(parent, classToFind);
	}

}
