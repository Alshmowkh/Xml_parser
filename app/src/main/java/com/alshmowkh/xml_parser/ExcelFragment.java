package com.alshmowkh.xml_parser;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelFragment extends Fragment {
    View layoutView;
    String filename;
    TextView lbl;
    Utils utils;
    TextView mText;
    String pathFile;

    public ExcelFragment(String filename) {
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

        layoutView = inflater.inflate(R.layout.excel_data, viewGroup, false);
        lbl = layoutView.findViewById(R.id.lbl1);
        lbl.setText("يامراحيب!");
        mText = layoutView.findViewById(R.id.multiText);
        pathFile = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString();
        utils = new Utils(layoutView.getContext());
        showExcelContent();
        return layoutView;
    }

    void showExcelContent2() {



        Workbook wwb = null;

        try {
            wwb = Workbook.getWorkbook(new File(pathFile));

        } catch (BiffException | IOException e) {
            utils.message(e.getMessage());
            e.printStackTrace();
        }
        if (wwb == null) {

            return;
        }
        Sheet sheet = wwb.getSheet(0);
        String sheetName = sheet.getCell(3,3).getContents();

        mText.setText(sheetName);
        wwb.close();

    }

    void showExcelContent() {

        String path = new StringBuilder().append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/").append(filename).toString();

        Workbook wb;
        Sheet sheet;



        XSSFWorkbook xwb = null;


        try {
            xwb=new XSSFWorkbook(new File(path));

        } catch (IOException | InvalidFormatException e) {
            utils.message(new StringBuilder().append("Error in read file").append(path).append(e.getMessage()).toString());
            e.printStackTrace();
        }
        if (xwb == null) {
            return;
        }
        mText.setText(xwb.getSheetName(0));

    }

    //        void ini() throws IOException, WriteException, InterruptedException {


}
