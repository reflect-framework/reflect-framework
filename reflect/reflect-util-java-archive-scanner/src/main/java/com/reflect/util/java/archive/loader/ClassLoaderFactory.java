package com.reflect.util.java.archive.loader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;

public class ClassLoaderFactory {

	public static URLClassLoader create(List<Path> classPaths) {
		URL[] urls = toUrlArray(classPaths);
		URLClassLoader urlClassLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
		return urlClassLoader;
	}

	private static URL[] toUrlArray(List<Path> classPaths) {
		return classPaths.stream().map(p -> pathToUrl(p)).toArray(URL[]::new);
	}

	private static URL pathToUrl(Path path) {
		try {
			return path.toUri().toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
