package com.reflect.util.java.archive.streamfactory;

import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.reflect.util.java.archive.file.FileInsideArchive;
import com.reflect.util.java.archive.file.FileInsideArchiveFactory;

public class JarStreamFactory implements ArchiveFileStreamFactory {

	private final ClassLoader classLoader;

	public JarStreamFactory(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public boolean canCreateFor(Path archivePath) {
		return archivePath.toString().toLowerCase().endsWith(".jar");
	}

	@Override
	public Stream<FileInsideArchive> createFor(Path archivePath) {
		JarFile jarFile = createJarFile(archivePath);
		Stream<FileInsideArchive> stream = createStream(archivePath, jarFile);
		return stream;
	}

	private JarFile createJarFile(Path archivePath) {
		try {
			JarFile jarFile = new JarFile(archivePath.toFile());
			return jarFile;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	private Stream<FileInsideArchive> createStream(Path archivePath, JarFile jarFile) {
		Spliterator<JarEntry> spliterator = createSpliterator(jarFile);
		return StreamSupport.stream(spliterator, false).filter(entry -> !entry.isDirectory())
				.map(entry -> FileInsideArchiveFactory.create(classLoader, archivePath, entry));
	}

	private Spliterator<JarEntry> createSpliterator(JarFile jarFile) {
		Iterator<JarEntry> iterator = createJarEntryIterator(jarFile);
		return Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT);
	}

	private Iterator<JarEntry> createJarEntryIterator(JarFile jarFile) {
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
