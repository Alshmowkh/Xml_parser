package com.alshmowkh.xml_parser;

import android.content.Context;
import android.os.Environment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ParsingXmlFile {
    private Context context;
    String pathFile;
    String pathDir;
    File file;
    String filename;
    Utils utils;
    Document doc;

    public ParsingXmlFile(Context context, String filename, String pathFile) {
        this.context = context;
        this.filename = filename;
        this.pathFile = pathFile;
        utils = new Utils(context);
    }

    public boolean parse() {

        if (!checkFile()) {
            return false;
        }
        if (!parseDOM()) {
            return false;
        }
        return true;
    }

    private boolean parseDOM() {
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            utils.message(new StringBuilder().append("DOM parsing error: ").append(e.getMessage()).toString());
            return false;
        }
        return true;
    }

    private boolean checkFile() {
        if (filename == null) {
            utils.message("The file name is null");
            return false;
        }

        String extension = filename.trim().substring(filename.length() - 3).toUpperCase().trim();
        if (!extension.equals("XML")) {
            utils.message(new StringBuilder().append("The file extension is not xml. \n It's ").append(extension).toString());
            return false;
        }

        if (pathFile == null) {
            pathFile = Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        file = new File(new StringBuilder().append(pathFile).append("/").append(filename).toString());
        if (!file.exists()) {
            utils.message(new StringBuilder().append("The file is not exist in ").append(pathFile).toString());
            return false;
        }
        return true;
    }

    public File getxmlFile() {
        return file;
    }

    public Document getDocument() {
        return doc;
    }

    boolean writeToXML(Element rootNode) {

        doc.appendChild(rootNode);
        TransformerFactory tf = TransformerFactory.newInstance();
        doc.setXmlStandalone(true);
        DOMSource dom = new DOMSource(doc);
        File out = new File(new StringBuilder().append(pathFile).append("/").append(filename).toString());
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
            utils.message(new StringBuilder().append("error occurred: \n").append(e.getMessage()).toString());
            return false;
        }

        if (out.exists()) {
//            System.out.println("The db has been done updated in directory : " + pathFile);
        } else {
            System.out.println("There is error at updating db.");
        }
        return true;
    }

    public void update(Document newDoc) {
        doc=newDoc;
        TransformerFactory tf = TransformerFactory.newInstance();
        newDoc.setXmlStandalone(true);
        DOMSource dom = new DOMSource(newDoc);
        File out = new File(new StringBuilder().append(pathFile).append("/").append(filename).toString());
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
            utils.message(new StringBuilder().append("error occurred: \n").append(e.getMessage()).toString());
            return;
        }

        if (out.exists()) {
//            System.out.println("The db has been done updated in directory : " + pathFile);
        } else {
            System.out.println("There is error at updating db.");
        }
    }
}
