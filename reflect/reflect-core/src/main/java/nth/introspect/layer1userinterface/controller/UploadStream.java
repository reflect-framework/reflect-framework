package nth.introspect.layer1userinterface.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadStream {

	private final String fileTypeDescription;
	private final String[] fileExtentionFilters;
	private File file;

	public UploadStream(String fileTypeDescription, String... fileExtentionFilters) {
		this.fileTypeDescription = fileTypeDescription;
		this.fileExtentionFilters = fileExtentionFilters;
	}

	public File getFile() {
		return file;
	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(file);
	}

	public String getFileTypeDescription() {
		return fileTypeDescription;
	}

	public String[] getFileExtentionFilters() {
		return fileExtentionFilters;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		if (file == null) {
			return fileTypeDescription;
		} else {
			return file.getPath();
		}
	}

}
