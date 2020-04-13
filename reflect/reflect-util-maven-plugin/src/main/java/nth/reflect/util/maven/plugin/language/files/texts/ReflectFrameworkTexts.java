package nth.reflect.util.maven.plugin.language.files.texts;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.project.MavenProject;

import com.reflect.util.java.archive.JavaArchiveScanner;
import com.reflect.util.java.archive.consumer.ClassInsideArchiveCollector;
import com.reflect.util.java.archive.filter.PackagePrefixFilter;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.util.maven.plugin.language.files.maven.MavenProjectClassPathFactory;

/**
 * gets all {@link Texts} from {@link ReflectFramework} classes
 * 
 * @author nilsth
 *
 */
public class ReflectFrameworkTexts extends Texts {

	private static final long serialVersionUID = 7841875561134044499L;

	public ReflectFrameworkTexts(MavenProject mavenProject) {
		Set<Class<?>> reflectFrameWorkClasses = findReflectFrameworkClasses(mavenProject);

		for (Class<?> reflectFrameWorkClass : reflectFrameWorkClasses) {
			putPropertiesFromTranslatableStringsFromStaticFields(reflectFrameWorkClass);
		}
	}

	private Set<Class<?>> findReflectFrameworkClasses(MavenProject mavenProject) {
//		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
//		classLoadersList.add(ClasspathHelper.contextClassLoader());
//		classLoadersList.add(ClasspathHelper.staticClassLoader());
//
//		Reflections reflections = new Reflections(new ConfigurationBuilder()
//				.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
//				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
//				.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("nth.reflect"))));

		List<Path> archivePaths = MavenProjectClassPathFactory.createArchivePaths(mavenProject);
		JavaArchiveScanner scanner = new JavaArchiveScanner(archivePaths);
		PackagePrefixFilter packageFilter = new PackagePrefixFilter("nth.reflect");
		ClassInsideArchiveCollector<Object> collector = new ClassInsideArchiveCollector();
		List<Class<? extends Object>> classList = scanner.find(packageFilter, collector);
		Set<Class<?>> classSet = new HashSet<Class<?>>(classList);
		return classSet;
	}
}
