package nth.reflect.maven.plugin.language.files.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.maven.plugin.language.files.texts.AllTexts;

public class LanguageFile {

	private final Path languageFilePath;

	public LanguageFile(Path languageFilePath) {
		validateLanguageFileName(languageFilePath);
		this.languageFilePath = languageFilePath;
	}

	private void validateLanguageFileName(Path languageFilePath) {
		String fileName = languageFilePath.getFileName().toString();
		if (!fileName.startsWith(DefaultLanguageProvider.PREFIX_LANGUAGE_FILE)) {
			throw new RuntimeException(String.format("File name: %s does not start with %s", languageFilePath,
					DefaultLanguageProvider.PREFIX_LANGUAGE_FILE));
		}
		if (!fileName.endsWith(DefaultLanguageProvider.PROPERTIES_EXTENSION)) {
			throw new RuntimeException(String.format("File name: %s does not end with extension %s", languageFilePath,
					DefaultLanguageProvider.PROPERTIES_EXTENSION));
		}
		if (fileName.startsWith(DefaultLanguageProvider.PREFIX_LANGUAGE_FILE + "_en")) {
			throw new RuntimeException(String.format(
					"A language file for English is discouraged, because the English text is dirived from code and annotations. Please remove file: %2",
					languageFilePath));
		}
		if (!Files.exists(languageFilePath)) {
			throw new RuntimeException(String.format("File name: %s does not exist", languageFilePath));
		}
	}

	public void update(AllTexts newTexts) {
		try {
			Properties oldTexts = readOldTexts();

			List<String> lines = new LanguageFileLines(newTexts, oldTexts);

			write(lines);

		} catch (Exception e) {
			throw new RuntimeException("Error updating language file: " + languageFilePath.toString(), e);
		}
	}

	private void write(List<String> lines) throws IOException {
		Files.write(languageFilePath, lines);
	}

	private Properties readOldTexts() throws IOException {
		InputStream inputStream = Files.newInputStream(languageFilePath);
		Properties oldTexts = new Properties();
		oldTexts.load(inputStream);
		return oldTexts;
	}

}
