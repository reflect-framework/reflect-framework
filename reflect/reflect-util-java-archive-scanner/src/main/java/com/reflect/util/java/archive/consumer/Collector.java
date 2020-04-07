package com.reflect.util.java.archive.consumer;

import java.util.List;
import java.util.function.Consumer;

import com.reflect.util.java.archive.file.FileInsideArchive;

public interface Collector<T> extends Consumer<FileInsideArchive> {

	public List<T> getResults();
}
