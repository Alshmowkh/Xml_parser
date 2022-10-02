package com.alshmowkh.xml_parser;

import android.content.Context;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Xml_File {
    Context context;
    String filePath;

    public Xml_File(Context context) {
        this.context = context;
        String fileName = "firstXML.xml";
//        this.filePath = filePath;
        //   this.createFileXML();
        this.saveInFile2(fileName, "<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        this.saveInFile2(fileName, "\nlasdfkl");
        //createNewFile(fileName);
    }

    void saveInFile2(String filename, String text) {
        String path = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString();
        if (new File((path)).exists()) {
            FileWriter fw = null;
            BufferedWriter bw = null;

            try {
                fw = new FileWriter(path, true);
                bw = new BufferedWriter(fw);
                bw.write(text);
                fw.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                message("some wrong happened!\n");
            }
        } else {
            message("the file not exist\n");
        }
    }

    void createNewFile(String nameFile) {
        String path = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(nameFile).toString();
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            message("the file created in :\n" );
        }
        message("the file is already exist.");

    }

    public File saveInFile(String nameFile, String string) {
        FileOutputStream fos = null;
        File output = null;
        String path = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(nameFile).toString();
        //  message(path);
        try {
            output = new File(path);
            message(output.getAbsolutePath());
            fos = new FileOutputStream(output);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//            sentences.forEach(i -> {
//            try {
            bw.write(string.toString());
            bw.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//                    }
//            );
//        }

        return output;
    }

    void updateFile(String filename, String str) {

        try {
            FileOutputStream fos = new FileOutputStream(new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString());
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        message(new StringBuilder().append(filename).append("   ").append("updated").toString());
    }

    private void writeToFile(File file, String s) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(s.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void createFile(String filename) {
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        writeInitial(file);

    }

    boolean handleFile(String pathFile) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Document doc = builder.parse(context.openFileInput(dbXml);
        return true;
    }

    //create file in phone storage
    void createFileXML(String filename) {
        File file;
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), new StringBuilder().append("/").append(filename).toString());
        // create file in the public download folder
//        dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

//        textView.setText(file.getAbsolutePath());
        writeInitial(file);
        message(new StringBuilder().append(filename).append(" ").append("created").toString());
    }

    private void message(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    private void writeInitial(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("\n".getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void readFromFile(String filename) throws IOException {
        FileInputStream ios = null;
//        ios=openFileInput(filename);

        int c;
        StringBuilder temp = new StringBuilder();
        while ((c = ios.read()) != -1) {
            temp.append(Character.toString((char) c));
        }
//        TextView textView = findViewById(R.id.txt1);
        File file = new File(filename);
//        textView.setText(file.getParent());
        ios.close();
    }
}
