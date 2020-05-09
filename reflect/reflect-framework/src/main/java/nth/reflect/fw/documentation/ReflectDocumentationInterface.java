package nth.reflect.fw.documentation;

import nth.reflect.fw.ReflectFramework;

/**
 * The {@link ReflectFramework} manual is generated from the Javadoc. This gives the following advantages:
 * <ul>
 * <li>The documentation is in-line with the code</li>
 * <li>The documentation can be read from the code base</li>
 * <li>The IDE is used as an editor (with spelling checker and text completion)</li>
 * <li>Basic HTML features can be used</li>
 * <li>References to other code can be used (which is updated during refactoring)</li>
 * </ul>
 * 
 * The {@link ReflectFramework} manual uses the JavaDoc in the {@link ReflectFramework} projects that
 * describes the classes, but also needs additional information. This is done
 * with empty interfaces that contain Javadoc that explain a certain subject.
 * These interfaces should extend this interface so that their purpose is clear.
 * 
 * @author nilsth
 * 
 */
public interface ReflectDocumentationInterface {

}
