package nth.introspect.provider;

import nth.introspect.Introspect;

/**
 * The {@link Introspect} framework uses the <a href="http://alistair.cockburn.us/Hexagonal+architecture">Ports and Adapters</a> design pattern. The introspect framework calls these ports and adapters a {@link Provider}<br>
 * <br>
 * A lot of application issues are caused by the same error in design and programming — the entanglement between the business logic and the interaction with external entities. The rule to obey is that code pertaining to the ‘’inside’’ part should not leak into the ‘’outside’’ part. In other words: Domain objects (entities) should have no knowledge (dependencies) on Service objects (use cases) and
 * service objects should have no knowledge of the user interface.<br>
 * <br>
 * To do this we use Ports. The application communicates over ‘’ports’’ to external agencies. The word “port” is supposed to evoke thoughts of ‘’ports’’ in an operating system, where any device that adheres to the protocols of a port can be plugged into it; and ‘’ports’’ on electronics gadgets, where again, any device that fits the mechanical and electrical protocols can be plugged in. The
 * protocol for a port is given by the purpose of the conversation between the two devices. The protocol takes the form of an application program interface (API).<br>
 * <br>
 * A port identifies a purposeful conversation. There will typically be multiple adapters for any one port, for various technologies that may plug into that port. A graphical human interface, a test harness, an http interface, a direct program-to-program interface, a mock (in-memory) database, a real database (perhaps different databases for development, test, and real use).<br>
 * <br>
 * For each external device there is an ‘’adapter’’ that converts the API definition to the signals needed by that device and vice versa. A graphical user interface or GUI is an example of an adapter that maps to the API of the port. There could be different user interface adapters that could be plugged in (command line, desktop, mobile, web, etc). Other adapters that fit the same port are
 * automated test harnesses such as FIT or Fitnesse, and any code needed for communication between applications across the enterprise or net (with i.e. a SOAP adapter or RESTful XML or JSON adapter).<br>
 * <br>
 * On another side of the application, the application communicates with an external entity to get data. The protocol is typically a database protocol. From the application’s perspective, if the database is moved from a SQL database to a flat file or any other kind of database, the conversation across the API should not change. Additional adapters for the same port thus include an SQL adapter, a
 * flat file adapter, and most importantly, an adapter to a “mock” database, one that sits in memory and doesn’t depend on the presence of the real database at all. Other examples of data adapters are SOAP clients, RESTfull XML or JSON clients, JPA adapter, etc..<br>
 * 
 * @author Nils ten Hoeve
 * @author Some paragraphs where inspired (or almost bluntly copied) from Alistair Cockburn's article <a href="http://alistair.cockburn.us/Hexagonal+architecture">Hexagonal architecture</a>
 */
public interface Provider {

}
