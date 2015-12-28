package nth.introspect.layer5provider.path;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import nth.introspect.IntrospectApplication;

public class DefaultPathProvider implements PathProvider {

	private static final String BIN_FOLDER = "BIN";
	private static final String JAR_EXTENTION = ".JAR";
	private static final String CONFIG_SUB_PATH = "configs";
	private static final String DOCUMENT_SUB_PATH = "documents";
	private static final String IMAGE_SUB_PATH = "images";
	private final URI rootPath;
	private final URI configPath;
	private final URI documenPath;
	private final URI imagePath;

	public DefaultPathProvider(IntrospectApplication application)
			throws URISyntaxException {
		this(createRootPath(application.getClass()));
	}

	public static URI createRootPath(Class<? extends IntrospectApplication> applicationClass)
			throws URISyntaxException {
		URI rootPathUri = applicationClass.getProtectionDomain()
				.getCodeSource().getLocation().toURI();
		File rootPath = new File(rootPathUri);
		if (rootPath.getName().toUpperCase().contains(JAR_EXTENTION)) {
			rootPath = rootPath.getParentFile();
//		} else if (rootPath.getName().toUpperCase().equals(BIN_FOLDER)) {
//			rootPath = rootPath.getParentFile();
//			rootPath=new File(rootPath.getAbsolutePath()+"/dist");
		}
		
		return rootPath.toURI();
	}

	public DefaultPathProvider(URI rootPath) throws URISyntaxException {
		this(rootPath, CONFIG_SUB_PATH, DOCUMENT_SUB_PATH, IMAGE_SUB_PATH);
	}

	public DefaultPathProvider(URI rootPath, String configSubPath,
			String documentSubPath, String imageSubPath)
			throws URISyntaxException {
		this.rootPath = rootPath;
		this.configPath = rootPath.resolve(configSubPath);
		this.documenPath = rootPath.resolve(documentSubPath);
		this.imagePath = rootPath.resolve(imageSubPath);
	}

	public DefaultPathProvider(URI rootPath, URI configPath, URI documenPath,
			URI imagePath) {
		this.rootPath = rootPath;
		this.configPath = configPath;
		this.documenPath = documenPath;
		this.imagePath = imagePath;
	}

	public DefaultPathProvider(
			Class<? extends IntrospectApplication> applicationClass) throws URISyntaxException {
		this(createRootPath(applicationClass));
	}

	@Override
	public URI getRootPath() {
		return rootPath;
	}

	@Override
	public URI getConfigPath() {
		return configPath;
	}

	@Override
	public URI getDocumenPath() {
		return documenPath;
	}

	@Override
	public URI getImagePath() {
		return imagePath;
	}

}
