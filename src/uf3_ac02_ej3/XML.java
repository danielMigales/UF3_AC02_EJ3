package uf3_ac02_ej3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * @Daniel Migales
 */
public class XML {

    public static void crearXML(String ciudad, String fecha, Predicciones prediccionDiaria) throws IOException, ParserConfigurationException, TransformerException {

        String nombreDocumento = ciudad + "_" + fecha;

        //Se crea la instancia para crear el documento xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Se crea un documento vacio con la version del xml
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, nombreDocumento, null);
        document.setXmlVersion("1.0");

        //Se crea un elemento raiz
        Element raiz = document.createElement("prediccion");
        document.getDocumentElement().appendChild(raiz);

        //Se crean los elementos obteniendo los datos del objeto 
        String provincia = prediccionDiaria.getProvincia();
        CrearElemento("provincia", provincia, raiz, document);

        String ciudad1 = prediccionDiaria.getCiudad();
        CrearElemento("ciudad", ciudad1, raiz, document);

        String fecha1 = prediccionDiaria.getFecha();
        CrearElemento("fecha", fecha1, raiz, document);

        String temperatura = String.valueOf(prediccionDiaria.getTemperatura());
        CrearElemento("temperatura_media", temperatura, raiz, document);

        String hora1 = prediccionDiaria.getHora1();
        CrearElemento("hora1", hora1, raiz, document);

        String hora2 = prediccionDiaria.getHora2();
        CrearElemento("hora2", hora2, raiz, document);

        String hora3 = prediccionDiaria.getHora3();
        CrearElemento("hora3", hora3, raiz, document);

        String hora4 = prediccionDiaria.getHora4();
        CrearElemento("hora4", hora4, raiz, document);

        String hora5 = prediccionDiaria.getHora5();
        CrearElemento("hora5", hora5, raiz, document);

        //Se crea la fuente del documento xml
        Source source = new DOMSource(document);
        Result result = new StreamResult(new java.io.File(nombreDocumento + ".xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    public static void CrearElemento(String dato, String valor, Element raiz, Document document) {

        Element elem = document.createElement(dato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

    public static void leerXML() {

        Scanner teclado = new Scanner(System.in);
        System.out.println("¿Que ciudad desea consultar?");
        String ciudad = teclado.nextLine();
        System.out.println("¿Que fecha desea consultar?");
        String fecha = teclado.nextLine();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            String nombreArchivo = ciudad + "_" + fecha + ".xml";
            Document document = builder.parse(new File(nombreArchivo));
            document.getDocumentElement().normalize();

            //Crea una lista con todos los nodos
            NodeList prediccion = document.getElementsByTagName("prediccion");

            //recorrer la lista
            for (int i = 0; i < prediccion.getLength(); i++) {
                Node prediccionMeteorologica = prediccion.item(i);
                if (prediccionMeteorologica.getNodeType() == Node.ELEMENT_NODE) {//obtener los elementos del nodo

                    Element elemento = (Element) prediccionMeteorologica;
                    System.out.println("");
                    System.out.printf("PROVINCIA = %s %n", elemento.getElementsByTagName("provincia").item(0).getTextContent());
                    System.out.printf("CIUDAD = %s %n", elemento.getElementsByTagName("ciudad").item(0).getTextContent());
                    System.out.printf("FECHA = %s %n", elemento.getElementsByTagName("fecha").item(0).getTextContent());
                    System.out.printf("TEMPERATURA MEDIA = %s %n", elemento.getElementsByTagName("temperatura_media").item(0).getTextContent());
                    System.out.printf("HORA 1 = %s %n", elemento.getElementsByTagName("hora1").item(0).getTextContent());
                    System.out.printf("HORA 2 = %s %n", elemento.getElementsByTagName("hora2").item(0).getTextContent());
                    System.out.printf("HORA 3 = %s %n", elemento.getElementsByTagName("hora3").item(0).getTextContent());
                    System.out.printf("HORA 4 = %s %n", elemento.getElementsByTagName("hora4").item(0).getTextContent());
                    System.out.printf("HORA 5 = %s %n", elemento.getElementsByTagName("hora5").item(0).getTextContent());
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }

}
