package nth.reflect.util.maven.plugin.language.files.texts;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import nth.reflect.fw.ReflectFramework;

/**
 * gets all {@link Texts} from {@link ReflectFramework} classes
 * 
 * @author nilsth
 *
 */
public class ReflectFrameworkTexts extends Texts {

	private static final long serialVersionUID = 7841875561134044499L;

	public ReflectFrameworkTexts() {
		Set<Class<?>> reflectFrameWorkClasses = findReflectFrameworkClasses();

		for (Class<?> reflectFrameWorkClass : reflectFrameWorkClasses) {
			putPropertiesFromTranslatableStringsFromStaticFields(reflectFrameWorkClass);
		}
	}

	private Set<Class<?>> findReflectFrameworkClasses() {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
				.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
				.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("nth.reflect"))));

		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

		return classes;
	}
}
