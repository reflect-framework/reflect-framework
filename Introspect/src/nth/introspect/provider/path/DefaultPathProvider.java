package nth.introspect.provider.path;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.provider.path.id.PathID;
import nth.introspect.util.StringUtil;

public class DefaultPathProvider implements PathProvider {

	private static final String BIN_FOLDER = "BIN";
	private static final String JAR_EXTENTION = ".JAR";
	private static final String PNG = ".png";
	private static final String CONFIG_SUB_PATH = "configs";
	private static final String DOCUMENT_SUB_PATH = "documents";
	private static final String IMAGE_SUB_PATH = "images";
	private static final String SLASH = "/";
	private final URI rootPath;
	private final URI configPath;
	private final URI documenPath;
	private final URI imagePath;
	private HashMap<String, URI> configPaths;
	private HashMap<String, URI> documentPaths;
	private HashMap<CharSequence, URI> existingImagePaths;
	private HashMap<CharSequence, URI> noneExistingImagePaths;

	public DefaultPathProvider(IntrospectApplication application)
			throws URISyntaxException {
		this(createRootPath(application));
	}

	private static URI createRootPath(IntrospectApplication application)
			throws URISyntaxException {
		URI rootPathUri = application.getClass().getProtectionDomain()
				.getCodeSource().getLocation().toURI();
		File rootPath = new File(rootPathUri);
		if (rootPath.getName().toUpperCase().contains(JAR_EXTENTION)) {
			rootPath = rootPath.getParentFile();
		} else if (rootPath.getName().toUpperCase().equals(BIN_FOLDER)) {
			rootPath = rootPath.getParentFile();
			rootPath=new File(rootPath.getAbsolutePath()+"/dist");
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
		// this.configPath = createURI(rootPath, configSubPath);
		this.configPath = rootPath;
		configPaths = new HashMap<String, URI>();
		this.documenPath = createURI(rootPath, documentSubPath);
		documentPaths = new HashMap<String, URI>();
		this.imagePath = createURI(rootPath, imageSubPath);
		existingImagePaths = new HashMap<CharSequence, URI>();
		noneExistingImagePaths = new HashMap<CharSequence, URI>();
	}

	public DefaultPathProvider(URI rootPath, URI configPath, URI documenPath,
			URI imagePath) {
		this.rootPath = rootPath;
		this.configPath = configPath;
		configPaths = new HashMap<String, URI>();
		this.documenPath = documenPath;
		documentPaths = new HashMap<String, URI>();
		this.imagePath = imagePath;
		existingImagePaths = new HashMap<CharSequence, URI>();
		noneExistingImagePaths = new HashMap<CharSequence, URI>();
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
	public URI getConfigPath(String relativePath) {
		if (!configPaths.containsKey(relativePath)) {
			URI path = null;
			try {
				path = createURI(configPath, relativePath);
			} catch (URISyntaxException e) {
			}
			// if (uriExists(path)) {
			configPaths.put(relativePath, path);
			// }
		}
		return configPaths.get(relativePath);
	}

	@Override
	public URI getDocumenPath() {
		return documenPath;
	}

	@Override
	public URI getDocumentPath(String relativePath) {
		if (!documentPaths.containsKey(relativePath)) {
			URI path = null;
			try {
				path = createURI(documenPath, relativePath);
			} catch (URISyntaxException e) {
			}
			if (uriExists(path)) {
				documentPaths.put(relativePath, path);
			}
		}
		return documentPaths.get(relativePath);
	}

	@Override
	public URI getImagePath() {
		return imagePath;
	}

	@Override
	public URI getImagePath(CharSequence identifier) {
		if (identifier == null
				|| noneExistingImagePaths.containsKey(identifier)) {
			return null;
		}
		if (existingImagePaths.containsKey(identifier)) {
			return existingImagePaths.get(identifier);
		}
		URI uri = null;
		try {
			if (identifier instanceof PathID) {
				PathID pathID = (PathID) identifier;
				uri = pathID.getPath();

				// absolute path
				if (uri != null && !uriExists(uri)) {
					// absolute path with PNG extension
					uri = new URI(uri.toString() + PNG);
				}
			} else {
				String relativePath = identifier.toString();
				if (!uriExists(uri)) {
					// relativePath
					uri = createURI(imagePath, relativePath);
				}
				if (!uriExists(uri)) {
					// relativePath with PNG extension
					uri = createURI(imagePath, relativePath + PNG);
				}
				if (!uriExists(uri)) {
					// unified relativePath
					relativePath = StringUtil.convertToCamelCase(relativePath,
							false);
					uri = createURI(imagePath, relativePath);
				}
				if (!uriExists(uri)) {
					// unified relativePath with PNG extension
					uri = createURI(imagePath, relativePath + PNG);
				}
			}
		} catch (URISyntaxException e) {
		}
		if (uriExists(uri)) {
			existingImagePaths.put(identifier, uri);
			return uri;
		} else {
			noneExistingImagePaths.put(identifier, uri);
			return null;
		}
	}

	private URI createURI(URI rootPath, String pathAppendix)
			throws URISyntaxException {
		String scheme = rootPath.getScheme();
		String userInfo = rootPath.getUserInfo();
		String host = rootPath.getHost();
		int port = rootPath.getPort();
		String path = rootPath.getPath();
		if (!path.endsWith(SLASH)) {
			path += SLASH;
		}
		if (pathAppendix != null) {
			while (pathAppendix.startsWith(SLASH)) {
				// remove slash as a first char
				pathAppendix = pathAppendix.substring(1);
			}
			path = path + pathAppendix;
		}
		String query = rootPath.getQuery();
		String fragment = rootPath.getFragment();
		return new URI(scheme, userInfo, host, port, path, query, fragment);
	}

	// public URI getImagePath(String imageName) throws Exception {
	// //get path
	// StringBuffer filePath = new StringBuffer();
	// if (!(imageName.startsWith("/") || imageName.startsWith("\\"))) {
	// filePath.append(getImagePath().toString());
	// // filePath.append("/");
	// }
	// //add file name
	// filePath.append(StringUtil.convertToCamelCase(imageName, false));
	// //append extension when not available
	// if (!imageName.contains(".")) {
	// filePath.append(".png");// default extension
	// }
	// URI uri = new URI(filePath.toString());
	// //TODO cache results?
	// return uri;
	// }

	// public Image getImage(String imageName) throws Exception {
	// URI imagePath = getImagePath(imageName);
	// try {
	// return ImageIO.read(imagePath.toURL());
	// } catch (Exception e) {
	// System.out.println("Could not read: " + imagePath);
	// throw e;
	// }
	// }

	@Override
	public boolean uriExists(URI uri) {
		try {
			uri.toURL().openStream();
			return true;
		} catch (Exception e) {
			// image not found
			return false;
		}

	}

}
