
package web;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the web package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegistroFormMiscelaneoResponse_QNAME = new QName("http://web/", "registroFormMiscelaneoResponse");
    private final static QName _RegistroFormMiscelaneo_QNAME = new QName("http://web/", "registroFormMiscelaneo");
    private final static QName _Exception_QNAME = new QName("http://web/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: web
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link RegistroFormMiscelaneoResponse }
     * 
     */
    public RegistroFormMiscelaneoResponse createRegistroFormMiscelaneoResponse() {
        return new RegistroFormMiscelaneoResponse();
    }

    /**
     * Create an instance of {@link RegistroFormMiscelaneo }
     * 
     */
    public RegistroFormMiscelaneo createRegistroFormMiscelaneo() {
        return new RegistroFormMiscelaneo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroFormMiscelaneoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web/", name = "registroFormMiscelaneoResponse")
    public JAXBElement<RegistroFormMiscelaneoResponse> createRegistroFormMiscelaneoResponse(RegistroFormMiscelaneoResponse value) {
        return new JAXBElement<RegistroFormMiscelaneoResponse>(_RegistroFormMiscelaneoResponse_QNAME, RegistroFormMiscelaneoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroFormMiscelaneo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web/", name = "registroFormMiscelaneo")
    public JAXBElement<RegistroFormMiscelaneo> createRegistroFormMiscelaneo(RegistroFormMiscelaneo value) {
        return new JAXBElement<RegistroFormMiscelaneo>(_RegistroFormMiscelaneo_QNAME, RegistroFormMiscelaneo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
