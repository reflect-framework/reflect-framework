package nth.reflect.util.maven.plugin.language.files.file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import nth.reflect.util.maven.plugin.language.files.UpdateLanguageFiles;
import nth.reflect.util.maven.plugin.language.files.texts.AllTexts;
import nth.reflect.util.maven.plugin.language.files.texts.DepricatedTexts;
import nth.reflect.util.maven.plugin.language.files.texts.Texts;

public class LanguageFileLines extends ArrayList<String> {

	private static final String TO_BE_TRANSLATED_PREFIX = "@";
	private static final String EMPTY_LINE = "";
	private static final int TITLE_LINE_LENGTH = 78;
	private static final long serialVersionUID = 3516765429053170003L;
	private final Properties oldTexts;

	public LanguageFileLines(AllTexts newTexts, Properties oldTexts) {
		this.oldTexts = oldTexts;
		addHeader();
		addSection("ReflectApplication", newTexts.getReflectApplicationTexts());
		addSection("ServiceObjects", newTexts.getServiceTexts());
		addSection("DomainObjects", newTexts.getDomainTexts());
		addSection("InfrastructureObjects", newTexts.getInfrasturctureTexts());
		addSection("ReflectFramework", newTexts.getReflectFrameWorkTexts());
		addDepricatedSection(newTexts);
	}

	private void addDepricatedSection(AllTexts newTexts) {
		DepricatedTexts depricatedTexts = new DepricatedTexts(newTexts, oldTexts);
		if (!depricatedTexts.isEmpty()) {
			addSectionTitle("Depricated");
			addTexts(depricatedTexts);
		}
	}

	private void addHeader() {
		add(String.format("# This file was updated by the reflect-maven-plugin:%s on %s",
				UpdateLanguageFiles.class.getSimpleName(),
				LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
		add(EMPTY_LINE);
		add("# Guidlines for translations:");
		add("# - DO NOT remove lines! Each line contains information that is used by the applications and may not be removed");
		add("# - DO NOT change the text before the = character. These textst contain reference keys for the applications and may not be modified or removed");
		add("# - A @ indicates that the following English text still needs to be translated (remove @ after translation)");
		add("# - Prevent that the modified texts after the = character contain much more characters then the original texts. Use abbreviations to shorten the text when required.");
		add("# - Use unicode for special characters. In example: replace the ö character in löschen with \\u00F6. so: clear=l\\u00F6schen");

	}

	private void addSection(String title, Texts texts) {
		if (!texts.isEmpty()) {
			addSectionTitle(title);
			addTexts(texts);
		}
	}

	private void addTexts(DepricatedTexts depricatedTexts) {
		for (String key : depricatedTexts.keySet()) {
			String text = depricatedTexts.get(key);
			add(key + "=" + text);
		}
	}

	private void addSectionTitle(String title) {
		add(EMPTY_LINE);
		title = " " + title + " ";
		int headingLength = (TITLE_LINE_LENGTH - title.length()) / 2;
		String header = new String(new char[headingLength]).replace("\0", "=");
		int suffixLength = TITLE_LINE_LENGTH - title.length() - headingLength;
		String suffix = new String(new char[suffixLength]).replace("\0", "=");
		String titleLine = "# " + header + title + suffix;
		add(titleLine);
	}

	private void addTexts(Texts newTexts) {
		for (String key : newTexts.keySet()) {
			if (textAlreadyTranslated(key)) {
				String value = oldTexts.getProperty(key);
				add(key + "=" + value);
			} else {
				String value = newTexts.get(key);
				add(key + "=" + TO_BE_TRANSLATED_PREFIX + value);
			}
		}
	}

	private boolean textAlreadyTranslated(String key) {
		return oldTexts.containsKey(key) && !oldTexts.getProperty(key).startsWith(TO_BE_TRANSLATED_PREFIX);
	}

}
