package com.reflect.util.java.archive.filter;

import java.util.function.Predicate;

import com.reflect.util.java.archive.file.ClassFileInsideArchive;
import com.reflect.util.java.archive.file.FileInsideArchive;

public class PackagePrefixFilter implements Predicate<FileInsideArchive> {

	private final String prefix;

	public PackagePrefixFilter(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean test(FileInsideArchive fileInsideArchive) {
		try {
			if (fileInsideArchive instanceof ClassFileInsideArchive) {
				ClassFileInsideArchive classFile = (ClassFileInsideArchive) fileInsideArchive;
				Class<?> classFromArchive = classFile.getClassInsideArchive();
				String classCanonicalName = classFromArchive.getCanonicalName();
				boolean startsWithPrefix = classCanonicalName.startsWith(prefix);
				return startsWithPrefix;
			} else {
				return false;
			}
		} catch (Throwable e) {
			return false;
		}
	}
}
