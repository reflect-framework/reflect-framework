package nth.reflect.fw.layer5provider.about;

import java.net.URI;
import java.net.URISyntaxException;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenFor;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class VersionInfo {
	private String className;
	private String fullClassName;
	private String archiveTitle;
	private String archiveVersion;
	private String archiveCreatedBy;
	private String archiveName;
	private String archivePath;

	public VersionInfo(Class<?> type) {
		className=type.getSimpleName();
		fullClassName = type.getName();
		// get jar war or ear.
		try {
			URI archiveURI = type.getProtectionDomain().getCodeSource().getLocation().toURI();
			String path = archiveURI.getPath();
			archiveName = path.substring(path.lastIndexOf("/") + 1);
			archivePath = path.substring(0, path.lastIndexOf("/"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (archiveName.endsWith(".class")) {
			// we did not get the jar or war file. Try to set archive fields via the ServletContext
//TODO			setArchiveFieldsForEclipseTomcatClassLoader(provider);
		} else {
			Package pack = type.getPackage();
			archiveTitle = pack.getImplementationTitle();
			archiveVersion = pack.getImplementationVersion();
			archiveCreatedBy = pack.getImplementationVendor();
		}
		if (archiveTitle == null || archiveTitle.trim().length() == 0) {
			archiveTitle = StringUtil.convertToCamelCase(className, true);
		}
	}

//	/**
//	 * Eclipse uses a custom class loader for Tomcat. This means we need to get the information via the ServletContext.
//	 */
//	private void setArchiveFieldsForEclipseTomcatClassLoader(Object provider) {
//		//assuming the provider is a (extension of) com.vaadin.Application
//		Object context = getValue(provider, "getContext", new Object[0]);
//		if (context != null) {
//			Object httpSession = getValue(context, "getHttpSession", new Object[0]);
//			if (httpSession != null) {
//				Object servletContext = getValue(httpSession, "getServletContext", new Object[0]);
//				if (servletContext != null) {
//					Object archiveFilePath = getValue(servletContext, "getRealPath", new Object[] {"/"});
//					if (archiveFilePath!=null) {
//						File archiveFile = new File((String) archiveFilePath);
//					    archiveName=archiveFile.getName();
//					    archivePath=archiveFile.getPath();
//					}
//					Object stream = getValue(servletContext, "getResourceAsStream", new Object[] {"/META-INF/MANIFEST.MF"});
//					if (stream != null) {
//						Properties properties = new Properties();
//						try {
//							properties.load((InputStream) stream);
//							archiveTitle = properties.getProperty("Implementation-Title");
//							archiveVersion = properties.getProperty("Implementation-Version");
//							archiveCreatedBy = properties.getProperty("Implementation-Vendor");
//						} catch (IOException e) {
//							// Failed but not the end of the world
//						}
//					}
//				}
//			}
//		}
//
//	}
//
//	private Object getValue(Object obj, String methodName, Object[] parameters) {
//		Method[] methods = obj.getClass().getMethods();
//		for (Method method : methods) {
//			if (method.getName() == methodName) {
//				try {
//					return method.invoke(obj, parameters);
//				} catch (Exception e) {
//					return null;
//				}
//			}
//		}
//		return null;
//	}


	@Order(value=1)
	@Hidden(propertyHiddenFor=HiddenFor.TABLES)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Hidden(propertyHiddenFor=HiddenFor.TABLES)
	@Order(value=2)
	public String getFullClassName() {
		return fullClassName;
	}

	public void setFullClassName(String fullClassName) {
		this.fullClassName = fullClassName;
	}
	
	@Order(value=3)
	@Hidden(propertyHiddenFor=HiddenFor.TABLES)
	public String getArchiveTitle() {
		return archiveTitle;
	}

	public void setArchiveTitle(String title) {
		this.archiveTitle = title;
	}
	
	@Order(value=4)
	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	@Hidden(propertyHiddenFor=HiddenFor.TABLES)
	@Order(value=5)
	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}

	@Order(value=6)
	public String getArchiveVersion() {
		return archiveVersion;
	}

	public void setArchiveVersion(String archiveVersion) {
		this.archiveVersion = archiveVersion;
	}

	@Order(value=7)
	public String getArchiveCreatedBy() {
		return archiveCreatedBy;
	}

	public void setArchiveCreatedBy(String archiveCreatedBy) {
		this.archiveCreatedBy = archiveCreatedBy;
	}

}
