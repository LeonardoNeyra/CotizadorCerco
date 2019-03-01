package com.neyra.leonardo.cotizadorcerco;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
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
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

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
    ImageButton excel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // region Read elements
        posttCantidad = (TextView) findViewById(R.id.posttCantidad);
        //posttPrecio = (TextView) findViewById(R.id.posttPrecio);
        //posttSubtotal = (TextView) findViewById(R.id.posttSubtotal);
        postiCantidad = (TextView) findViewById(R.id.postiCantidad);
        //postiPrecio = (TextView) findViewById(R.id.postiPrecio);
        //postiSubtotal = (TextView) findViewById(R.id.postiSubtotal);
        aisltCantidad = (TextView) findViewById(R.id.aisltemp_Cantidad);
        //aisltPrecio = (TextView) findViewById(R.id.aisltemp_Precio);
        //aisltSubtotal = (TextView) findViewById(R.id.aisltemp_Subtotal);
        aisliCantidad = (TextView) findViewById(R.id.aislint_Cantidad);
        //aisliPrecio = (TextView) findViewById(R.id.aislint_Precio);
        //aisliSubtotal = (TextView) findViewById(R.id.aislint_Subtotal);
        abrazaderaCantidad = (TextView) findViewById(R.id.abrazadera_Cantidad);
        //abrazaderaPrecio = (TextView) findViewById(R.id.abrazadera_Precio);
        //abrazaderaSubtotal = (TextView) findViewById(R.id.abrazadera_Subtotal);
        alambreacCantidad = (TextView) findViewById(R.id.alambreac_Cantidad);
        //alambreacPrecio = (TextView) findViewById(R.id.alambreac_Precio);
        //alambreacSubtotal = (TextView) findViewById(R.id.alambreac_Subtotal);
        alambregalCantidad = (TextView) findViewById(R.id.alambregal_Cantidad);
        //alamabregalPrecio = (TextView) findViewById(R.id.alambregal_Precio);
        //alambregalSubtotal = (TextView) findViewById(R.id.alambregal_Subtotal);
        cableCantidad = (TextView) findViewById(R.id.cable_Cantidad);
        //cablePrecio = (TextView) findViewById(R.id.cable_Precio);
        //cableSubtotal = (TextView) findViewById(R.id.cable_Subtotal);
        letreroCantidad = (TextView) findViewById(R.id.letrero_Cantidad);
        //letreroPrecio = (TextView) findViewById(R.id.letrero_Precio);
        //letreroSubtotal = (TextView) findViewById(R.id.letrero_Subtotal);
        aroCantidad = (TextView) findViewById(R.id.aro_Cantidad);
        //aroPrecio = (TextView) findViewById(R.id.aro_Precio);
        //aroSubtotal = (TextView) findViewById(R.id.aro_Subtotal);
        xpowerCantidad = (TextView) findViewById(R.id.xpower_Cantidad);
        //xpowerPrecio = (TextView) findViewById(R.id.xpower_Precio);
        //xpowerSubtotal = (TextView) findViewById(R.id.xpower_Subtotal);
        sensorCantidad = (TextView) findViewById(R.id.sensor_Cantidad);
        //sensorPrecio = (TextView) findViewById(R.id.sensor_Precio);
        //sensorSubtotal = (TextView) findViewById(R.id.sensor_Subtotal);
        total = (TextView) findViewById(R.id.total);
        //venta = (TextView) findViewById(R.id.);
        excel = (ImageButton) findViewById(R.id.excel);
        //endregion

        // region Send results
        // Recive data from MainActivity and set data
        posttCantidad.setText(String.valueOf(getIntent().getDoubleExtra("POSTT_CANTIDAD",0)));
        //posttPrecio.setText(String.valueOf(getIntent().getDoubleExtra("POSTT_PRECIO",0)));
        //posttSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("POSTT_SUBTOTAL",0)));
        postiCantidad.setText(String.valueOf(getIntent().getDoubleExtra("POSTI_CANTIDAD",0)));
        //postiPrecio.setText(String.valueOf(getIntent().getDoubleExtra("POSTI_PRECIO",0)));
        //postiSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("POSTI_SUBTOTAL",0)));
        aisltCantidad.setText(String.valueOf(getIntent().getDoubleExtra("AISLTEMP_CANTIDAD",0)));
        //aisltPrecio.setText(String.valueOf(getIntent().getDoubleExtra("AISLTEMP_PRECIO",0)));
        //aisltSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("AISLTEMP_SUBTOTAL",0)));
        aisliCantidad.setText(String.valueOf(getIntent().getDoubleExtra("AISLINT_CANTIDAD",0)));
        //aisliPrecio.setText(String.valueOf(getIntent().getDoubleExtra("AISLINT_PRECIO",0)));
        //aisliSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("AISLINT_SUBTOTAL",0)));
        abrazaderaCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ABRAZADERA_CANTIDAD",0)));
        //abrazaderaPrecio.setText(String.valueOf(getIntent().getDoubleExtra("ABRAZADERA_PRECIO",0)));
        //abrazaderaSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("ABRAZADERA_SUBTOTAL",0)));
        alambreacCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREACERADO_CANTIDAD",0)));
        //alambreacPrecio.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREACERADO_PRECIO",0)));
        //alambreacSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREACERADO_SUBTOTAL",0)));
        alambregalCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREGALVANIZADO_CANTIDAD",0)));
        //alamabregalPrecio.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREGALVANIZADO_PRECIO",0)));
        //alambregalSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("ALAMBREGALVANIZADO_SUBTOTAL",0)));
        cableCantidad.setText(String.valueOf(getIntent().getDoubleExtra("CABLEBUJIA_CANTIDAD",0)));
        //cablePrecio.setText(String.valueOf(getIntent().getDoubleExtra("CABLEBUJIA_PRECIO",0)));
        //cableSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("CABLEBUJIA_SUBTOTAL",0)));
        letreroCantidad.setText(String.valueOf(getIntent().getDoubleExtra("LETRERO_CANTIDAD",0)));
        //letreroPrecio.setText(String.valueOf(getIntent().getDoubleExtra("LETRERO_PRECIO",0)));
        //letreroSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("LETRERO_SUBTOTAL",0)));
        aroCantidad.setText(String.valueOf(getIntent().getDoubleExtra("ARODOBLE_CANTIDAD",0)));
        //aroPrecio.setText(String.valueOf(getIntent().getDoubleExtra("ARODOBLE_PRECIO",0)));
        //aroSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("ARODOBLE_SUBTOTAL",0)));
        xpowerCantidad.setText(String.valueOf(getIntent().getDoubleExtra("XPOWERi8_CANTIDAD",0)));
        //xpowerPrecio.setText(String.valueOf(getIntent().getDoubleExtra("XPOWERi8_PRECIO",0)));
        //xpowerSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("XPOWERi8_SUBTOTAL",0)));
        sensorCantidad.setText(String.valueOf(getIntent().getDoubleExtra("SENSOR_CANTIDAD",0)));
        //sensorPrecio.setText(String.valueOf(getIntent().getDoubleExtra("SENSOR_PRECIO",0)));
        //sensorSubtotal.setText(String.valueOf(getIntent().getDoubleExtra("SENSOR_SUBTOTAL",0)));
        total.setText(String.valueOf(getIntent().getDoubleExtra("TOTAL",0)));
        //venta.setText(String.valueOf(getIntent().getDoubleExtra("VENTA",0)));
        // endregion

        excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExcel(this, "/sdcard/myExcel.xls");
            }
        });
    }

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
}
