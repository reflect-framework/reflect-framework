package nth.reflect.util.maven.plugin.githubdoc.dom.html;

/**
 * See https://jsoup.org/cookbook/extracting-data/selector-syntax
 * 
 * @author nilsth
 *
 */
public class JSoupQuery {

	private final StringBuilder query;

	public JSoupQuery() {
		query = new StringBuilder();
	}

	@Override
	public String toString() {
		return query.toString();
	}

	public JSoupQuery addElement(String elementName, String... attributeQuerys) {
		if (query.length() > 0) {
			query.append(", ");
		}
		query.append(elementName);
		if (attributeQuerys.length > 0) {
			query.append("[");
			boolean firstAttributeQuery = true;
			for (String attributeQuery : attributeQuerys) {
				if (firstAttributeQuery) {
					firstAttributeQuery = false;
				} else {
					query.append(", ");
				}
				query.append(attributeQuery);
			}
			query.append("]");
		}

		return this;
	}

}
