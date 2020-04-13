package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import nth.reflect.util.maven.plugin.githubdoc.dom.page.FileUtil;

public class FilesFromJar {

	public static void copy(URL packagePath, File destinationFolder) {
		String jarPath = getJarPath(packagePath);
		try {
			JarFile jarFile = new JarFile(new File(jarPath));
			List<JarEntry> jarEntries = getJarEntries(jarFile);

			for (JarEntry jarEntry : jarEntries) {
				if (jarEntry.isDirectory()) {
					createDirectory(destinationFolder, jarEntry);
				} else {
					copyFile(destinationFolder, jarFile, jarEntry);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException("Could not read jar file: " + jarPath);
		}

	}

	private static void createDirectory(File destinationFolder, JarEntry jarEntry) {
		File newDirectory = new File(destinationFolder, jarEntry.getName());
		newDirectory.mkdir();
	}

	private static void copyFile(File destinationFolder, JarFile jarFile, JarEntry jarEntry)
			throws IOException, FileNotFoundException {
		InputStream inputStream = jarFile.getInputStream(jarEntry);
		File newFile = new File(destinationFolder, jarEntry.getName());
		FileOutputStream outputStream = new FileOutputStream(newFile);
		FileUtil.copyFile(inputStream, outputStream);
	}

	private static String getJarPath(URL packagePath) {
		return packagePath.toString().replaceAll("^jar:file:/", "").replaceAll("\\!.*", "");
	}

	private static List<JarEntry> getJarEntries(JarFile jarFile) {
		Spliterator<JarEntry> spliterator = createSpliterator(jarFile);
		List<JarEntry> jarEntries = StreamSupport
				.stream(spliterator, false)
				.filter(entry -> isMmenuFile(entry))
				.collect(Collectors.toList());
		return jarEntries;
	}

	private static boolean isMmenuFile(JarEntry entry) {
		boolean isMmenuFile = entry.getName().contains("mmenu");
		return isMmenuFile;
	}

	private static Spliterator<JarEntry> createSpliterator(JarFile jarFile) {
		Iterator<JarEntry> iterator = createJarEntryIterator(jarFile);
		return Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT);
	}

	private static Iterator<JarEntry> createJarEntryIterator(JarFile jarFile) {
		final Enumeration<JarEntry> entries = jarFile.entries();
		Iterator<JarEntry> iterator = new Iterator<JarEntry>() {

			@Override
			public JarEntry next() {
				return entries.nextElement();
			}

			@Override
			public boolean hasNext() {
				return entries.hasMoreElements();
			}
		};
		return iterator;
	}

}
