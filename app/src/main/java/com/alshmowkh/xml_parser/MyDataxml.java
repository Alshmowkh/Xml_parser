package com.alshmowkh.xml_parser;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class MyDataxml {
    private Context context;
    private File xmlFile;
    private String filename;
    private String pathFile;
    private Document doc;
    private ParsingXmlFile parsing;

    public MyDataxml(Context context, String filename, String pathFile) {
        this.context = context;
        this.filename = filename;
        this.pathFile = pathFile;
        doc = null;
    }

    public boolean connect() {
        if (doc == null) {
           parsing = new ParsingXmlFile(context, filename, pathFile);
            if (!parsing.parse()) {
              return false;
            }
            doc = parsing.getDocument();
        }
        return true;
    }

    public String getRoot() {

        String root = doc.getDocumentElement().getNodeName();
        return root;
    }
    void addNode(String nodeName,String parent){
        Element newNode=doc.createElement(nodeName);
        newNode.setTextContent("new tag...");
        newNode.setAttribute("id","30");
        Node node;
      node=doc.getElementsByTagName(parent).item(0);
//         node=doc.getDocumentElement();

        node.appendChild(newNode);
         parsing.update(doc);
        doc=parsing.getDocument();
    }
    /*

    إضافة ظيعة
            ضع اسم الظيعة، رقمها.
                 تشييك لو اسم الظيعة موجود بالاسم في الظياع أو بالرقم المدخل.
              تشييك لو رقم الظيعة مسنود لظيعة اخرى. أو لنفس اسم الضيعة مع اختلاف الهمزة او الظاد او التاء المربوطة او ماشابه.
              يجب اضافة قسم رئيسي للظيعة عند اضافة ضيعة جديد
    إضافة قسم
                في أي ضيعة تريد الاضافة، الظياع على combobox .
                اسم القسم ، رقم القسم، المساحة ، .....
                التأكد من عدم وجود قسم بهذا الاسم أو الرقم في الظيعة المراد الاضافة فيها.

    إضافة حقبة
    إضافة خروجة
    إضافة سبة
    إضافة معلقة


     */
}
