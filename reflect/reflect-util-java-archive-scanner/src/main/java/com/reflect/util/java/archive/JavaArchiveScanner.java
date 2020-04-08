package com.reflect.util.java.archive;

import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.reflect.util.java.archive.consumer.Collector;
import com.reflect.util.java.archive.file.FileInsideArchive;
import com.reflect.util.java.archive.loader.ClassLoaderFactory;
import com.reflect.util.java.archive.streamfactory.DirectoryStreamFactory;
import com.reflect.util.java.archive.streamfactory.JarStreamFactory;

/**
 * The {@link JavaArchiveScanner} is a library for scanning Java archives
 * (jar,war,ear files or folders with classes) for classes or class members
 * (fields, methods or properties) using a filter and a {@link Collector}
 * 
 * @author nilsth
 *
 */
public class JavaArchiveScanner {
	protected final List<Path> archivePaths;
	private final DirectoryStreamFactory directoryStreamFactory;
	private final JarStreamFactory jarStreamFactory;

	public JavaArchiveScanner(List<Path> archivePaths) {
		this.archivePaths = archivePaths;
		URLClassLoader classLoader = ClassLoaderFactory.create(archivePaths);
		directoryStreamFactory = new DirectoryStreamFactory(classLoader);
		jarStreamFactory = new JarStreamFactory(classLoader);
	}

	public <T extends Object> List<T> find(Predicate<FileInsideArchive> filter, Collector<T> collector) {
		for (Path archivePath : archivePaths) {
			Stream<FileInsideArchive> stream = createStream(archivePath);
			stream.filter(filter).forEach(collector);
		}
		return collector.getResults();
	}

	private Stream<FileInsideArchive> createStream(Path archivePath) {
		if (jarStreamFactory.canCreateFor(archivePath)) {
			return jarStreamFactory.createFor(archivePath);
		} else if (directoryStreamFactory.canCreateFor(archivePath)) {
			return directoryStreamFactory.createFor(archivePath);
		}
		throw new RuntimeException("Could not create a stream for: " + archivePath.toString());
	}

}
