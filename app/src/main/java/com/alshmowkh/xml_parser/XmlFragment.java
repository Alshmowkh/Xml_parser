package com.alshmowkh.xml_parser;

import android.net.ipsec.ike.IkeTrafficSelector;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.zip.Inflater;

public class XmlFragment extends Fragment {
    View layoutView;
    String filename;
    TextView lbl;
    Utils utils;

    public XmlFragment(String filename) {
        this.filename = filename;

    }

    //    @Override
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.xml_display);
//       // layoutView=View.inflate(R.layout)
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedBundle) {

        layoutView = inflater.inflate(R.layout.xml_display, viewGroup, false);
        lbl = layoutView.findViewById(R.id.lbl1);
        lbl.setText("يامراحيب!");
        utils = new Utils(layoutView.getContext());
        showXmlContent();
        return layoutView;
    }

    void showXmlContent() {
        TextView mText;
        mText = layoutView.findViewById(R.id.multiText);
        String path = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString();
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(new File(path));
            br = new BufferedReader(fr);
        } catch (IOException e) {
            utils.message(new StringBuilder().append("Error in read file").append(path).append(e.getMessage()).toString());
            e.printStackTrace();
        }
        Stream lines = null;
        Iterator itr;
        if (br != null) {
            lines = br.lines();
            itr=lines.iterator();
            while (itr.hasNext()) {
                mText.append(new StringBuilder().append(itr.next()).append("\n").toString());
            }
        }
    }
}
