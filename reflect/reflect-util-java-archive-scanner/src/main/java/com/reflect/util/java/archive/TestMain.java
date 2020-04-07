package com.reflect.util.java.archive;

import java.util.List;

import com.reflect.util.java.archive.consumer.ClassInsideArchiveCollector;
import com.reflect.util.java.archive.filter.SubClassFilter;

public class TestMain {
	public static void main(String[] args) {
		TestPaths archivePaths = new TestPaths();
		JavaArchiveScanner javaArchiveScanner = new JavaArchiveScanner(archivePaths);
		SubClassFilter filter = new SubClassFilter(archivePaths, "nth.reflect.fw.ReflectApplication");
		ClassInsideArchiveCollector<Object> collector = new ClassInsideArchiveCollector<Object>();
		List<Class<? extends Object>> result = javaArchiveScanner.find(filter, collector);
		for (Class<? extends Object> class1 : result) {
			System.out.println(class1.getCanonicalName());
		}
	}
}
