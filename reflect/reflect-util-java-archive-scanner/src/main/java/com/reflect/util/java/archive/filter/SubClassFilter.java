package com.reflect.util.java.archive.filter;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import com.reflect.util.java.archive.file.ClassFileInsideArchive;
import com.reflect.util.java.archive.file.FileInsideArchive;

public class SubClassFilter implements Predicate<FileInsideArchive> {

	private String canonicalNameOfSuperClassToFind;

	public SubClassFilter(List<Path> classPaths, Class<?> superClassToFind) {
		this.canonicalNameOfSuperClassToFind = superClassToFind.getCanonicalName();
	}

	public SubClassFilter(List<Path> classPaths, String canonicalNameOfSuperClassToFind) {
		this.canonicalNameOfSuperClassToFind = canonicalNameOfSuperClassToFind;
	}

	@Override
	public boolean test(FileInsideArchive fileInsideArchive) {
		try {
			if (fileInsideArchive instanceof ClassFileInsideArchive) {
				ClassFileInsideArchive classFile = (ClassFileInsideArchive) fileInsideArchive;
				Class<?> classFromArchive = classFile.getClassInsideArchive();
				boolean isSubType = isSubType(classFromArchive);
				return isSubType;
			} else {
				return false;
			}
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * For some reason the {@link Class#isAssignableFrom(Class)} does not work
	 * sometimes, so we need to check it our selves using recursive calls
	 * 
	 * @param subType
	 * @return
	 */
	private boolean isSubType(Class<?> subType) {
		if (canonicalNameOfSuperClassToFind.equals(subType.getCanonicalName())) {
			return true;
		}
		Class<?>[] interfaces = subType.getInterfaces();
		for (Class<?> interf : interfaces) {
			if (isSubType(interf)) {
				return true;
			}
		}
		Class<?> superClass = subType.getSuperclass();
		if (superClass == null) {
			return false;
		} else {
			return isSubType(superClass);
		}
	}

}
