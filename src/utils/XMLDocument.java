/*
 * Classe pour générer un fichier au format XML
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
package utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLDocument {

    /**
     * MyDocument : référence au document XML.
     */
    protected Document MyDocument;

    /**
     * MyElements : Liste des éléments à exporter.
     */
    protected Element MyElements;

    /**
     * Initialise le document XML.
     *
     * @param RootName nom de la racine du document XML
     * @param XsdFile nom du fichier contenant le schema XML
     */
    public XMLDocument(String RootName, String XsdFile) {

        DocumentBuilderFactory MyFactory;
        DocumentBuilder MyBuilder;

        MyFactory = DocumentBuilderFactory.newInstance();
        try {
            MyBuilder = MyFactory.newDocumentBuilder();
            MyDocument = MyBuilder.newDocument();

            MyElements = MyDocument.createElement(RootName);
            MyElements.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance"
            );
            MyElements.setAttribute("xsi:noNamespaceSchemaLocation", XsdFile);
            MyDocument.appendChild(MyElements);

        } catch (ParserConfigurationException MyException) {
            Logger.getLogger(XMLDocument.class.getName()).log(Level.SEVERE, null, MyException);
            System.out.println("Problem creating XML document " + MyException);
        }
    }

    /**
     * Pensez à rajouter une methode pour enrichir le document XML.
     */
    /**
     * Finalise le document XML.
     *
     * @param FileOut nom du fichier qui recevra les résultats.
     */
    public void FinalizeXMLDocument(String FileOut) {

        TransformerFactory MyTransformerFactory;
        Transformer MyTransformer;
        DOMSource MySource;
        StreamResult MyOutput;

        try {
            MyTransformerFactory = TransformerFactory.newInstance();
            MyTransformer = MyTransformerFactory.newTransformer();

            MySource = new DOMSource(MyDocument);
            MyOutput = new StreamResult(new File(FileOut));
//          MyOutput = new StreamResult(System.out);

            // Prologue
            MyTransformer.setOutputProperty(OutputKeys.VERSION, "1.0");
//        MyTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            MyTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-15");
            MyTransformer.setOutputProperty(OutputKeys.STANDALONE, "no");
//        MyTransformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"agences.dtd");

            // Formatting results
            MyTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            MyTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Output
            MyTransformer.transform(MySource, MyOutput);

            System.out.println("Fichier des résultats : " + FileOut);
        } catch (TransformerConfigurationException MyException) {
            Logger.getLogger(XMLDocument.class.getName()).log(Level.SEVERE, null, MyException);
            System.out.println("Problem configuring XML document " + MyException);
        } catch (TransformerException MyException) {
            Logger.getLogger(XMLDocument.class.getName()).log(Level.SEVERE, null, MyException);
            System.out.println("Problem writing XML document " + MyException);
        }
    }
}
