package com.alshmowkh.xml_parser;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

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

public class FileXML {
    Context context;
    String filename;
    String path;
    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document doc;

    public FileXML(Context context, String filename) {
        this.context = context;
        this.filename = filename;
        dbf = DocumentBuilderFactory.newInstance();

    }
    boolean bindingXmlFile(String path){

        return bindingXmlFile(new File(path));
    }

    private boolean bindingXmlFile(File file) {
        parseFile(file.getAbsolutePath());
        return true;
    }

    boolean createNewFile(String filename) {
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        try {
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            message(doc.getXmlVersion());
        } catch (ParserConfigurationException e) {
            message(new StringBuilder().append("there is error: \n").append(e.getMessage()).toString());
        }
        Element element= doc.createElement("Dhaih");
        Element gacim=doc.createElement("Gacim");
        gacim.setTextContent("ظع الاقسام هنا...");
        gacim.setAttribute("id","20");

        element.appendChild(gacim);

        writeToXML(element);
        return true;
    }

    public boolean parseFile(String filename) {

        String path = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString();
        File file = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document d;

//      message("the file \n" + path);

        if (file.exists()) {
            try {
                db = dbf.newDocumentBuilder();
                d = db.parse(file);
//                message("the file parsed" + filename);
                return true;
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
//                message("there is error :" + e.getMessage());

            }
        } else {
//            message("the file below is not exist" + filename);
        }
        return false;
    }

    public boolean addTag(String tagName) {

        return true;
    }

    public boolean insertValue(String tagName, String value) {

        return true;
    }

    public String getRoot() {
        return filename;
    }

    //    boolean writeToXML(Element rootNode) throws SAXException, ParserConfigurationException, IOException, TransformerException {
    boolean writeToXML(Element rootNode) {

        doc.appendChild(rootNode);
        TransformerFactory tf = TransformerFactory.newInstance();
        doc.setXmlStandalone(true);
        DOMSource dom = new DOMSource(doc);
        File out = new File(new StringBuilder().append(path).append("/").append(filename).toString());
        StreamResult sr = new StreamResult(out);
        try {
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.VERSION, "1.0");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.transform(dom, sr);
        } catch (TransformerException e) {
//            message("error occurred: \n" + e.getMessage());
            return false;
        }

        if (out.exists()) {
//            System.out.println("The db has been done created in directory : " + path);
        } else {
            System.out.println("There is error at creating db.");
        }
        return true;
    }

    void message(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
