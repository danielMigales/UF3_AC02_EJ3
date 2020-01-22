package uf3_ac02_ej3;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 * @Daniel Migales
 */
public class XML {

    public static void crearXML(String ciudad, String fecha) throws IOException, ParserConfigurationException, TransformerException {

        String nombreDocumento = ciudad + "_" + fecha;
        
        //Creamos la instancia para crear el documento xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Creamos un documento vacio con la version del xml
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, nombreDocumento, null);
        document.setXmlVersion("1.0");

        //Creamos un elemento raiz
        Element raiz = document.createElement("prediccion");
        document.getDocumentElement().appendChild(raiz);
        
        Predicciones predicciones = new Predicciones();
        
        
        String provincia = predicciones.getProvincia();
        String ciudad1 = predicciones.getCiudad();
        int temperatura = predicciones.getTemperatura();
        String hora1 = predicciones.getHora1();
        String hora2 = predicciones.getHora2();
        String hora3 = predicciones.getHora3();
        String hora4 = predicciones.getHora4();
        String hora5 = predicciones.getHora5();
        
        
        CrearElemento("provincia",provincia,raiz,document);
        CrearElemento("ciudad",ciudad1,raiz,document);
       // CrearElemento("temperatura_media",temperatura,raiz,document);
        CrearElemento("hora1",hora1,raiz,document);
        CrearElemento("hora2",hora2,raiz,document);
        CrearElemento("hora3",hora3,raiz,document);
        CrearElemento("hora4",hora4,raiz,document);
        CrearElemento("hora5",hora5,raiz,document);

        //Creamos la fuente del documento xml
        Source source = new DOMSource(document);
        Result result = new StreamResult(new java.io.File( nombreDocumento + ".xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

   public static void CrearElemento(String dato, String valor,Element raiz, Document document) {
       
       Element elem = document.createElement(dato);       
       Text text = document.createTextNode(valor);
       raiz.appendChild(elem);//pegamos el elemento hijo a la raiz
       elem.appendChild(text); //pegamos el valor
        
    }

}

