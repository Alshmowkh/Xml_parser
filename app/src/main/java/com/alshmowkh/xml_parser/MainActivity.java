package com.alshmowkh.xml_parser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    StringBuilder sb;
    private String pathFile;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filename = "filexml01.xml";
        TextView txt1 = findViewById(R.id.txt1);
        textView = txt1;
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(connectMydb);
        xmlContentFrag();
        btn2.setOnClickListener(btn2Listener);

    }

    View.OnClickListener btn2Listener = v -> {
        String excelFileName = "excelFile-1.xlsx";
        Fragment ExcelFrag = new ExcelFragment(excelFileName);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, ExcelFrag).commit();
    };

    void xmlContentFrag() {
        Fragment xmlFrag = new XmlFragment(filename);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, xmlFrag).commit();
    }

    View.OnClickListener connectMydb = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MyDataxml mydb = new MyDataxml(getApplicationContext(), filename, null);
            boolean conn = mydb.connect();
            String root;

            mydb.addNode("Dhiah", "SafatFarm");
            root = mydb.getRoot();
            textView.setText(root.toString());
            xmlContentFrag();

        }
    };
    View.OnClickListener fileXML = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String filename = "filexml01.xml";
            FileXML xml = new FileXML(getApplicationContext(), filename);
            // xml.parseFile(filename);
            xml.createNewFile("filexml2.xml");
            xml.addTag("root");
            xml.insertValue("root", "habibi");
            String root = xml.getRoot();
            textView.setText(root);
        }
    };
    View.OnClickListener runXMLFile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Xml_File(getApplicationContext());
        }
    };


}