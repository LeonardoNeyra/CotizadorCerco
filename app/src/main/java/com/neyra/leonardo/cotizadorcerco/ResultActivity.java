package com.neyra.leonardo.cotizadorcerco;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    private static final String FILE_NAME = "CotizadorCerco.txt";

    TextView posttCantidad, posttPrecio, posttSubtotal;
    TextView postiCantidad, postiPrecio, postiSubtotal;
    TextView aisltCantidad, aisltPrecio, aisltSubtotal;
    TextView aisliCantidad, aisliPrecio, aisliSubtotal;
    TextView abrazaderaCantidad, abrazaderaPrecio, abrazaderaSubtotal;
    TextView alambreacCantidad, alambreacPrecio, alambreacSubtotal;
    TextView alambregalCantidad, alamabregalPrecio, alambregalSubtotal;
    TextView cableCantidad, cablePrecio, cableSubtotal;
    TextView letreroCantidad, letreroPrecio, letreroSubtotal;
    TextView aroCantidad, aroPrecio, aroSubtotal;
    TextView xpowerCantidad, xpowerPrecio, xpowerSubtotal;
    TextView sensorCantidad, sensorPrecio, sensorSubtotal;
    TextView total, venta;
    ImageButton excel, txt, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // region Read elements
        posttCantidad = (TextView) findViewById(R.id.posttCantidad);
        postiCantidad = (TextView) findViewById(R.id.postiCantidad);
        aisltCantidad = (TextView) findViewById(R.id.aisltemp_Cantidad);
        aisliCantidad = (TextView) findViewById(R.id.aislint_Cantidad);
        abrazaderaCantidad = (TextView) findViewById(R.id.abrazadera_Cantidad);
        alambreacCantidad = (TextView) findViewById(R.id.alambreac_Cantidad);
        alambregalCantidad = (TextView) findViewById(R.id.alambregal_Cantidad);
        cableCantidad = (TextView) findViewById(R.id.cable_Cantidad);
        letreroCantidad = (TextView) findViewById(R.id.letrero_Cantidad);
        aroCantidad = (TextView) findViewById(R.id.aro_Cantidad);
        xpowerCantidad = (TextView) findViewById(R.id.xpower_Cantidad);
        sensorCantidad = (TextView) findViewById(R.id.sensor_Cantidad);
        total = (TextView) findViewById(R.id.total);
        excel = (ImageButton) findViewById(R.id.excel);
        txt = (ImageButton) findViewById(R.id.txt);
        share = (ImageButton) findViewById(R.id.wsp);
        //endregion

        // region Send results
        // Recive data from MainActivity and set data
        posttCantidad.setText(String.valueOf(getIntent().getDoubleExtra("POSTT_CANTIDAD",0)));
        postiCantidad.setText(String.valueOf(getIntent().getDoubleExtra("POSTI_CANTIDAD",0)));
        aisltCantidad.setText(String.valueOf(getIntent().getDoubleExtra("AISLTEMP_CANTIDAD",0)));
        aisliCantidad.setText(String.valueOf(getIntent().getDoubleExtra("AISLINT_CANTIDAD",0)));
        abrazaderaCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ABRAZADERA_CANTIDAD",0)));
        alambreacCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREACERADO_CANTIDAD",0)));
        alambregalCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREGALVANIZADO_CANTIDAD",0)));
        cableCantidad.setText(String.valueOf(getIntent().getDoubleExtra("CABLEBUJIA_CANTIDAD",0)));
        letreroCantidad.setText(String.valueOf(getIntent().getDoubleExtra("LETRERO_CANTIDAD",0)));
        aroCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ARODOBLE_CANTIDAD",0)));
        xpowerCantidad.setText(String.valueOf(getIntent().getDoubleExtra("XPOWERi8_CANTIDAD",0)));
        sensorCantidad.setText(String.valueOf(getIntent().getDoubleExtra("SENSOR_CANTIDAD",0)));
        total.setText(String.valueOf(getIntent().getDoubleExtra("TOTAL",0)));
        // endregion

        // Button Excel
        excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExcel(this, "/sdcard/myExcel.xls");
            }
        });

        // Button Txt
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTxt();
            }
        });

        // Button Share
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareTxt();
            }
        });
    }

    // region Button EXCEL
    private boolean saveExcel(View.OnClickListener context, String fileName){
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            //  Log.w("FileUtils", "Storage not available or read only");
            return false;
        }

        boolean success = false;

        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIME.getIndex());

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("myOrder");

        // Generate column headings
        Row row = sheet1.createRow(0);
        c = row.createCell(0);
        c.setCellValue("Item Number");
        c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("Quantity");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Price");
        c.setCellStyle(cs);

        sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (15 * 500));
        sheet1.setColumnWidth(2, (15 * 500));

        // Create a path where we will place our List of objects on external storage
        File file = new File(getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            success = true;
        } catch (IOException e) {
        } catch (Exception e) {
            //  Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }

        return success;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    // endregion

    // region Button TEXT
    public void saveTxt(){
        String text = "Tipo\t\tCantidad"
                + "\nPost T\t\t" + String.valueOf(getIntent().getDoubleExtra("POSTT_CANTIDAD",0))
                + "\nPost I\t\t" + String.valueOf(getIntent().getDoubleExtra("POSTI_CANTIDAD",0))
                + "\nAisl Temp\t" + String.valueOf(getIntent().getDoubleExtra("AISLTEMP_CANTIDAD",0))
                + "\nAisl Int\t" + String.valueOf(getIntent().getDoubleExtra("AISLINT_CANTIDAD",0))
                + "\nAbrazadera\t" + String.valueOf(getIntent().getDoubleExtra("ABRAZADERA_CANTIDAD",0))
                + "\nAlambre Ace.\t" + String.valueOf(getIntent().getDoubleExtra("ALAMBREACERADO_CANTIDAD",0))
                + "\nAlambre Gal.\t" + String.valueOf(getIntent().getDoubleExtra("ALAMBREGALVANIZADO_CANTIDAD",0))
                + "\nCable Bujía\t" + String.valueOf(getIntent().getDoubleExtra("CABLEBUJIA_CANTIDAD",0))
                + "\nLetrero\t\t" + String.valueOf(getIntent().getDoubleExtra("LETRERO_CANTIDAD",0))
                + "\nArodoble\t" + String.valueOf(getIntent().getDoubleExtra("ARODOBLE_CANTIDAD",0))
                + "\nXPower i8\t" + String.valueOf(getIntent().getDoubleExtra("XPOWERi8_CANTIDAD",0))
                + "\nSensor\t\t" + String.valueOf(getIntent().getDoubleExtra("SENSOR_CANTIDAD",0))
                + "\nCantidad Total\t" + String.valueOf(getIntent().getDoubleExtra("TOTAL",0));

        FileOutputStream fos = null;

        // Create File
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), FILE_NAME);

        // Write File
        try {
            fos = new FileOutputStream(file);
            fos.write(text.getBytes());

            Toast.makeText(this, "Guardado como: " + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "Archivo no Encontrado", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al Guardar", Toast.LENGTH_LONG).show();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    // endregion

    // region Button SHARE
    public void ShareTxt(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + FILE_NAME);

        if (file.exists()){
            shareIntent.setType("*/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse("file://" + file.getAbsolutePath()));
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartiendo...");
            startActivity(Intent.createChooser(shareIntent, "Compartir Archivo"));
        }
    }
    // endregion
}
