package nth.reflect.fw.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Receives a file from the user to the application (e.g. opens a file selection
 * dialog)
 * 
 * @author nilsth
 *
 */
public class UploadStream {

	private final String fileTypeDescription;
	private final String[] fileExtentionFilters;
	private InputStream inputStream;
	private String fileName;

	public UploadStream(String fileTypeDescription, String... fileExtentionFilters) {
		if (fileTypeDescription == null || fileTypeDescription.isBlank()) {
			throw new IllegalArgumentException("fileTypeDescription may not be empty");
		}
		this.fileTypeDescription = fileTypeDescription;
		this.fileExtentionFilters = fileExtentionFilters;
	}

	public UploadStream() {
		this("All files", "*.*");
	}

	public String getFileTypeDescription() {
		return fileTypeDescription;
	}

	// TODO change to MIME types?
	public String[] getFileExtentionFilters() {
		return fileExtentionFilters;
	}

	@Override
	public String toString() {
		if (fileName == null) {
			return "";
		} else {
			return fileName;
		}
	}

	public void setResult(InputStream inputStream, String fileName) {
		this.inputStream = inputStream;
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setResult(File file) throws FileNotFoundException {
		fileName = file.getAbsolutePath();
		inputStream = new FileInputStream(file);
	}

}
