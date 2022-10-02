package com.alshmowkh.xml_parser;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    Context context;

    public Utils(Context context) {
        this.context = context;
    }

    void message(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
