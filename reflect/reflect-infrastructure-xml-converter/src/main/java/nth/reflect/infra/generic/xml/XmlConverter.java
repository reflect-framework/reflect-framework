package nth.reflect.infra.generic.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.InstanceFactory;
import nth.reflect.fw.generic.util.JavaTypeConverter;
import nth.reflect.fw.layer4infrastructure.InfrastructureContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.infra.generic.xml.transform.DefaultMatcher;
import nth.reflect.infra.generic.xml.transform.Transform;

/**
 * TODO implement Marshaller, Unmarshaller, Parser and Printer, see
 * reflect-infrastructure-converter
 * 
 * @author nilsth
 *
 */

public class XmlConverter {

	private static final String REFLEC_FRAMEWORK = ReflectFramework.class.getSimpleName();
	private static final String ID = "id";
	private static DefaultMatcher XML_TRANSFORM_MATCHER = new DefaultMatcher(null);
	private final ReflectionProvider reflectionProvider;
	private final InfrastructureContainer providerContainer;

	public XmlConverter(ReflectionProvider reflectionProvider, InfrastructureContainer providerContainer) {
		this.reflectionProvider = reflectionProvider;
		this.providerContainer = providerContainer;
	}

	/**
	 * Parses a XML string to a Document Object Model (DOM)<br>
	 * See http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	 * 
	 * @throws Exception
	 */
	public Document parse(String xml) throws Exception {
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputStream);

		// Normalize: optional, but recommended. //read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		return doc;
	}

	/**
	 * prints a Document Object Model (DOM) to an XML string<br>
	 * See http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	 * 
	 * @throws Exception
	 */
	public String print(Document document, boolean indent) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		if (indent) {
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		}

		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult(outWriter);
		// StreamResult result = new StreamResult(new File("C:\\file.xml"));
		// StreamResult result = new StreamResult(System.out);

		DOMSource source = new DOMSource(document);
		transformer.transform(source, result);

		StringBuffer sb = outWriter.getBuffer();
		return sb.toString();
	}

	public String marshal(Object object, boolean indent) throws Exception {
		Document document = marshal(object);
		return print(document, indent);
	}

	public Document marshal(Object object) throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document document = docBuilder.newDocument();

		// root element
		Element rootElement = document.createElement(REFLEC_FRAMEWORK);
		document.appendChild(rootElement);

		List<Object> marshaledObjects = new ArrayList<Object>();
		if (object instanceof Collection) {
			@SuppressWarnings("rawtypes")
			Collection collection = (Collection) object;
			for (Object item : collection) {
				if (object != null) {
					Element objectElement = marshal(document, rootElement, item, marshaledObjects);
					rootElement.appendChild(objectElement);
				}
			}

		} else {
			if (object != null) {
				Element objectElement = marshal(document, rootElement, object, marshaledObjects);
				rootElement.appendChild(objectElement);
			}
		}
		return document;
	}

	private Element marshal(Document document, Element parentElement, Object objectToMarshal,
			List<Object> marshaledObjects) {
		// create an element that represents an object
		Element objectElement = document.createElement(objectToMarshal.getClass().getCanonicalName());
		objectElement.setAttribute(ID, Integer.toString(objectToMarshal.hashCode()));
		// parentElement.appendChild(objectElement);

		// was the object already marshaled? YES: the id reference suffices, no
		// need to marshal the properties, since this is already done
		if (!marshaledObjects.contains(objectToMarshal)) {
			// NO: marshal all the properties

			// remember the objects that where marshaled so we do not have to do
			// them twice.
			marshaledObjects.add(objectToMarshal);

			DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(objectToMarshal.getClass());

			List<PropertyInfo> properyInfos = domainClassInfo.getPropertyInfosSorted();
			for (PropertyInfo propertyInfo : properyInfos) {

				Object propertyValue = propertyInfo.getValue(objectToMarshal);
				if (propertyValue != null) {
					// create a property element (if property value !=null)
					Element propertyElement = document.createElement(propertyInfo.getSimpleName());
					objectElement.appendChild(propertyElement);
					TypeInfo propertyTypeInfo = propertyInfo.getTypeInfo();
					if (propertyTypeInfo.isCollection()) {
						// is a collection: add elements that reference to
						// domain objects
						Collection<?> propertyCollection = (Collection<?>) propertyValue;
						for (Object propertyItem : propertyCollection) {
							// assuming it is a collection of domain objects
							Element collectionItemElement = marshal(document, propertyElement, propertyItem,
									marshaledObjects);
							propertyElement.appendChild(collectionItemElement);
						}
					} else if (propertyTypeInfo.isDomainClass()) {
						Element domainObjectElement = marshal(document, propertyElement, propertyValue,
								marshaledObjects);
						propertyElement.appendChild(domainObjectElement);
					} else {
						// is a java value: set text content on property element
						String value = printElementValue(propertyTypeInfo.getType(), propertyValue);
						propertyElement.setTextContent(value);
					}
				}

			}
		}
		return objectElement;

	}

	public Collection<?> unmarshal(String xml) throws Exception {
		if (xml.length() == 0) {
			return null;
		}
		Document document = parse(xml);
		return unmarshal(document, false);
	}

	public Object unmarshalFirst(String xml) throws Exception {
		Document document = parse(xml);
		Collection<?> collection = unmarshal(document, true);
		if (collection.isEmpty()) {
			return null;
		} else {
			return collection.iterator().next();
		}
	}

	public Collection<?> unmarshal(Document document, boolean firstObjectOnly) throws Exception {
		// get root element
		List<Object> rootObjects = new ArrayList<Object>();
		Map<String, Object> unMarshaledObjects = new HashMap<String, Object>();
		Element rootElement = document.getDocumentElement();
		if (!rootElement.getNodeName().equals(REFLEC_FRAMEWORK)) {
			throw new RuntimeException("The XML document must have a root element <" + REFLEC_FRAMEWORK + ">");
		}

		// create objects
		List<Element> objectElements = getChildElements(rootElement);
		for (Element objectElement : objectElements) {
			Object object = unmarshalObject(objectElement, unMarshaledObjects);
			rootObjects.add(object);
			if (firstObjectOnly) {
				return rootObjects;
			}
		}

		return rootObjects;
	}

	/**
	 * Creates an object form a element.<br>
	 * 
	 * @param objectElement
	 *            Element that represents the object. <br>
	 *            By {@link ReflectFramework} convention: the elements tag name
	 *            must correspond with the canonical class name.<br>
	 *            This class name needs to be in the class path and must have
	 *            default constructor (no-argument constructor) in order to be
	 *            instantiated
	 * @return
	 * @throws Exception
	 */
	private Object unmarshalObject(Element objectElement, Map<String, Object> unMarshaledObjects) throws Exception {
		String id = objectElement.getAttribute(ID);
		// see if we already un-marshaled the object
		Object object = unMarshaledObjects.get(id);
		if (object == null) {
			// not yet un-marshaled, so create the object
			String className = objectElement.getTagName();
			Class<?> classToInstantiate = Class.forName(className);
			InstanceFactory instanceFactory = new InstanceFactory(classToInstantiate, providerContainer);
			List<Class<?>> classesWaitingToBeInstantiated = new ArrayList<Class<?>>();
			object = instanceFactory.createInstance(classesWaitingToBeInstantiated);

			// cash the object so we can reference to it if needed
			unMarshaledObjects.put(id, object);

			// get PropertyInfos
			DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(object.getClass());
			List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
			// get property Elements
			List<Element> propertyElements = getChildElements(objectElement);

			// iterate trough PropertyInfos and set property values
			for (PropertyInfo propertyInfo : propertyInfos) {
				if (!propertyInfo.isReadOnly()) {// TODO what if
													// !propertyInfo.isEnabled())
					Object propertyValue = unMarshalProperty(propertyInfo, propertyElements, unMarshaledObjects);
					propertyInfo.setValue(object, propertyValue);
				}
			}

		}
		return object;
	}

	private Object unMarshalProperty(PropertyInfo propertyInfo, List<Element> propertyElements,
			Map<String, Object> unMarshaledObjects) throws Exception {
		Element propertyElement = findFirstElement(propertyElements, propertyInfo.getSimpleName());
		Object propertyValue = null;
		if (propertyElement != null) {
			// get property value from element
			TypeInfo propertyTypeInfo = propertyInfo.getTypeInfo();
			if (propertyTypeInfo.isCollection()) {
				// Is a collection
				propertyValue = unMarshalPropertyOfCollectionType(unMarshaledObjects, propertyElement, propertyValue,
						propertyTypeInfo.getType());
			} else if (propertyTypeInfo.isDomainClass() || propertyTypeInfo.getType() == Object.class) {
				// Is a domain object
				propertyValue = unMarshalPropertyOfDomainType(propertyElement, unMarshaledObjects);
			} else {
				// Is a java value: convert XML value (string) from element to
				// property value
				propertyValue = unMarshalPropertyOfJavaType(propertyElement, propertyInfo);
			}

		}
		return propertyValue;
	}

	public Object unMarshalPropertyOfCollectionType(Map<String, Object> unMarshaledObjects, Element propertyElement,
			Object propertyValue, Class<?> propertyType) throws Exception {
		List<Element> collectionElements = getChildElements(propertyElement);
		if (List.class.isAssignableFrom(propertyType)) {
			// create a new list
			List<Object> list = new ArrayList<Object>();
			propertyValue = list;
			// add items to the list
			for (Element collectionElement : collectionElements) {
				Object listItemObject = unmarshalObject(collectionElement, unMarshaledObjects);
				list.add(listItemObject);
			}
		} else {
			StringBuffer message = new StringBuffer("A collection of type: ");
			message.append(propertyType.getCanonicalName());
			message.append(" is not supported yet");
			throw new RuntimeException(message.toString());
			// TODO implement other type of collections and maybe even arrays?
		}
		return propertyValue;
	}

	public Object unMarshalPropertyOfDomainType(Element propertyElement, Map<String, Object> unMarshaledObjects)
			throws Exception {
		List<Element> domainObjectElements = getChildElements(propertyElement);
		Object propertyValue = null;
		if (domainObjectElements.size() == 1) {// size must be 0 or 1
			Element domainObjectElement = domainObjectElements.get(0);
			propertyValue = unmarshalObject(domainObjectElement, unMarshaledObjects);
		}
		return propertyValue;
	}

	@SuppressWarnings("unchecked")
	private Object unMarshalPropertyOfJavaType(Node propertyElement, PropertyInfo propertyInfo) {
		String value = propertyElement.getTextContent();

		// unify type to complex type
		Class<?> propertyType = propertyInfo.getTypeInfo().getType();
		propertyType = JavaTypeConverter.getComplexType(propertyType);

		// get xml transformer and transform value
		Transform<Object> transform = null;
		try {
			transform = XML_TRANSFORM_MATCHER.matchType(propertyType);
			return transform.read(value);
		} catch (Exception e) {
			if (transform == null) {
				StringBuffer message = new StringBuffer("Type: ");
				message.append(propertyType.getCanonicalName());
				message.append(" for property: ");
				message.append(propertyInfo.getCanonicalName());
				message.append(" is not supported in ");
				message.append(XmlConverter.class.getCanonicalName());
				throw new RuntimeException(message.toString());
			} else {
				StringBuffer message = new StringBuffer("Error converting type: ");
				message.append(propertyType.getCanonicalName());
				message.append(" for property: ");
				message.append(propertyInfo.getCanonicalName());
				message.append("  in ");
				message.append(XmlConverter.class.getCanonicalName());
				throw new RuntimeException(message.toString());
			}
		}

	}

	@SuppressWarnings("unchecked")
	private String printElementValue(Class<?> type, Object value) {
		// unify type to complex type
		type = JavaTypeConverter.getComplexType(type);
		// get xml transformer and transform value
		Transform<Object> transform = null;
		try {
			transform = XML_TRANSFORM_MATCHER.matchType(type);
			return transform.write(value);
		} catch (Exception e) {
			if (transform == null) {
				StringBuffer message = new StringBuffer("Type: ");
				message.append(type.getCanonicalName());
				message.append(" is not supported in ");
				message.append(XmlConverter.class.getCanonicalName());
				throw new RuntimeException(message.toString());
			} else {
				StringBuffer message = new StringBuffer("Error converting type: ");
				message.append(type.getCanonicalName());
				message.append("  in ");
				message.append(XmlConverter.class.getCanonicalName());
				throw new RuntimeException(message.toString());
			}
		}
	}

	private Element findFirstElement(List<Element> elements, String tagNameToFind) {
		for (Element element : elements) {
			if (element.getTagName().equals(tagNameToFind)) {
				return element;
			}
		}
		return null;// not found
	}

	private List<Element> getChildElements(Element parentElement) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList propertyNodes = parentElement.getChildNodes();
		for (int index = 0; index < propertyNodes.getLength(); index++) {
			Node childNode = propertyNodes.item(index);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) childNode;
				childElements.add(childElement);
			}
		}
		return childElements;
	}

}
