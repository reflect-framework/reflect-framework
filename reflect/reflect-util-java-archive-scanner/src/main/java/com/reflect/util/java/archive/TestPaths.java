package com.reflect.util.java.archive;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TestPaths extends ArrayList<Path> {

	private static final long serialVersionUID = 3810580699582362606L;

	public TestPaths() {
		add("C:\\Users\\nilsth\\.git\\reflect-examples\\examples\\reflect-web-shop-for-javafx\\target\\classes");
		add("C:\\Users\\nilsth\\.m2\\repository\\com\\github\\reflect-framework\\reflect-for-javafx\\0.0.2-SNAPSHOT\\reflect-for-javafx-0.0.2-SNAPSHOT.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\com\\github\\reflect-framework\\reflect-graphical-user-interface\\0.0.2-SNAPSHOT\\reflect-graphical-user-interface-0.0.2-SNAPSHOT.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\com\\github\\reflect-framework\\reflect-core\\0.0.2-SNAPSHOT\\reflect-core-0.0.2-SNAPSHOT.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\org\\hibernate\\validator\\hibernate-validator\\6.1.2.Final\\hibernate-validator-6.1.2.Final.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\jakarta\\validation\\jakarta.validation-api\\2.0.2\\jakarta.validation-api-2.0.2.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\org\\jboss\\logging\\jboss-logging\\3.3.2.Final\\jboss-logging-3.3.2.Final.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\org\\glassfish\\javax.el\\3.0.1-b09\\javax.el-3.0.1-b09.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\org\\hibernate\\validator\\hibernate-validator-cdi\\6.0.14.Final\\hibernate-validator-cdi-6.0.14.Final.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\com\\fasterxml\\classmate\\1.4.0\\classmate-1.4.0.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\org\\webjars\\font-awesome\\4.5.0\\font-awesome-4.5.0.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\de\\jensd\\fontawesomefx\\8.1\\fontawesomefx-8.1.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\org\\webjars\\bower\\roboto-fontface\\0.4.3\\roboto-fontface-0.4.3.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\com\\jfoenix\\jfoenix\\1.11.1\\jfoenix-1.11.1.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\com\\github\\reflect-framework\\example\\reflect-web-shop-domain\\0.0.2-SNAPSHOT\\reflect-web-shop-domain-0.0.2-SNAPSHOT.jar");
		add("C:\\Users\\nilsth\\.m2\\repository\\com\\github\\reflect-framework\\reflect-infrastructure-random-generator\\0.0.2-SNAPSHOT\\reflect-infrastructure-random-generator-0.0.2-SNAPSHOT.jar");
	}

	private void add(String pathString) {
		Path path = Paths.get(pathString);
		add(path);
	}
}
