package nth.reflect.fw.container;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.documentation.ReflectArchitecture;

/**
 * <p>
 * The {@link ReflectFramework} favors constructor injection (see <a
 * href="http://en.wikipedia.org/wiki/Martin_Fowler">Martin Fowler</a>'s easy to
 * read <a href=
 * "http://martinfowler.com/articles/injection.html#ConstructorVersusSetterInjection"
 * >article</a> for the arguments why).
 * </p>
 * <p>
 * All objects within an {@link ReflectApplication} can have references to
 * (may know) other objects. These reference objects are injected into an object
 * as constructor parameter during the creation of the object by the
 * {@link DependencyInjectionContainer} of that specific layer. This constructor
 * parameter can than be linked to a private final field, so that it can be used
 * throughout the object.
 * </p>
 * In example:
 * 
 * <pre>
 * public class ProductService {
 * 
 * 	private final ProductRepository productRepository;
 * 
 * 	public ProductService(ProductRepository productRepository) {
 * 		this.productRepository = productRepository;
 * 	}
 * 
 * 	public List&lt;Product&gt; findProduct(ProductSearchCritiria searchCritiria) {
 * 		return productRepository.findProduct(searchCritiria);
 * 	}
 * 
 * 	// other action methods...
 * }
 * </pre>
 * 
 * <p>
 * It is good practice to link the constructor parameter (reference object) to a
 * <a href="https://en.wikibooks.org/wiki/Java_Programming/Keywords/private"
 * >private</a> <a
 * href="https://en.wikipedia.org/wiki/Final_(Java)#Final_variables">final</a>
 * field, so that it is <a
 * href="https://en.wikipedia.org/wiki/Encapsulation_(computer_programming)"
 * >encapsulated</a> and <a
 * href="https://en.wikipedia.org/wiki/Immutable_object">immutable</a>.
 * </p>
 * 
 * <p>
 * If your object needs a lot of references to other objects (too many
 * constructor parameters), your object has most likely to many
 * responsibilities, which could be solved by splitting up this object.
 * </p>
 * 
 * Note that you can only inject reference object types (use constructor
 * parameters types) that are known to the that specific layer:
 * <ul>
 * <li>The ReflectApplication class can be injected in all layers</li>
 * <li>You can only inject objects of the same layer or lower layers (see
 * {@link ReflectArchitecture}). The {@link ReflectFramework} tries to
 * prevent that objects in the lower layer know (have references to) objects in
 * the higher layers because this is against several design principles of an
 * layered architecture</li>
 * <li>You can only inject objects that are registered to the
 * {@link ReflectApplication} by overriding the get...Classes() or
 * get...Class() methods.</li>
 * </ul>
 * 
 * @author Nils ten Hoeve
 *
 */
public interface ConstructionInjection extends ReflectDocumentationInterface {

}
