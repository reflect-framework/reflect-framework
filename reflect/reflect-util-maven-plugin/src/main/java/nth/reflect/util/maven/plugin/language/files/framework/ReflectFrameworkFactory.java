package nth.reflect.util.maven.plugin.language.files.framework;

import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.project.MavenProject;

import com.reflect.util.java.archive.JavaArchiveScanner;
import com.reflect.util.java.archive.consumer.ClassInsideArchiveCollector;
import com.reflect.util.java.archive.filter.SubClassFilter;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.util.maven.plugin.language.files.maven.MavenProjectClassPathFactory;

public class ReflectFrameworkFactory {

	private final static boolean MUST_HAVE_SERVICE_CLASSES = true;

	public static DependencyInjectionContainer createContainer(MavenProject mavenProject) {
		ReflectApplication application = createReflectApplication(mavenProject);
		// Creating the reflect container: skipping the creation of the
		// UserInterfaceContainer on purpose, because the ReflectApplication must be
		// created by the GUI framework in most cases
		DependencyInjectionContainer container = new ServiceContainer(application, MUST_HAVE_SERVICE_CLASSES);
		return container;
	}

	private static ReflectApplication createReflectApplication(MavenProject mavenProject) {
		Class<? extends ReflectApplication> reflectApplicationClass = findReflectApplicationClass(mavenProject);
		try {
			ReflectApplication reflectApplication = reflectApplicationClass.newInstance();
			return reflectApplication;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Could not instantiate ReflectApplication: "
					+ reflectApplicationClass.getCanonicalName() + ". Does it have an empty constructor?");
		}
	}

	private static Class<? extends ReflectApplication> findReflectApplicationClass(MavenProject mavenProject) {

		List<Class<? extends ReflectApplication>> reflectApplications = findNoneReflectForJUnitApplications(
				mavenProject);

		if (reflectApplications.size() == 0) {
			throw new RuntimeException("Could not find a Reflect Application.");
		} else if (reflectApplications.size() > 1) {
			throw new RuntimeException("Found more then one Reflect Applications.");
		} else {
			return reflectApplications.get(0);
		}
	}

	private static List<Class<? extends ReflectApplication>> findNoneReflectForJUnitApplications(
			MavenProject mavenProject) {
		Set<Class<? extends ReflectApplication>> allFoundReflectApplicationClasses = findAllReflectApplications(
				mavenProject);

		List<Class<? extends ReflectApplication>> foundNoneReflectForJUnitApplications = allFoundReflectApplicationClasses
				.stream()
				.filter(c -> !ReflectApplicationForJUnit.class.isAssignableFrom(c)
						&& !Modifier.isAbstract(c.getModifiers()))
				.collect(Collectors.toList());

		return foundNoneReflectForJUnitApplications;
	}

	private static Set<Class<? extends ReflectApplication>> findAllReflectApplications(MavenProject mavenProject) {
		List<Path> archivePaths = MavenProjectClassPathFactory.createArchivePaths(mavenProject);
		JavaArchiveScanner scanner = new JavaArchiveScanner(archivePaths);
		SubClassFilter subClassFilter = new SubClassFilter(ReflectApplication.class);
		ClassInsideArchiveCollector<Object> collector = new ClassInsideArchiveCollector();
		List<Class<? extends Object>> classList = scanner.find(subClassFilter, collector);
		HashSet classSet = new HashSet(classList);
		return classSet;
	}
}
