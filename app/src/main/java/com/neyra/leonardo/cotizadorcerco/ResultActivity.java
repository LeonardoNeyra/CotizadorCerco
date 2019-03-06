package com.neyra.leonardo.cotizadorcerco;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ResultActivity extends AppCompatActivity {
    private static final String FILE_NAME_TXT = "CotizadorCerco.txt";
    private static final String FILE_NAME_XLS = "CotizadorCerco.xls";

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

        // Back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cerco Electrico");


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
                writeExcel();
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
    public void writeExcel(){
        //final String fileName = "TodoList.xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/javatechig");

        //create directory if not exist
        if(!directory.isDirectory()){
            directory.mkdirs();
        }

        //file path
        File file = new File(directory, FILE_NAME_XLS);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;

        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet wsheet = workbook.createSheet("Cotizador", 0);

            wsheet.addCell(new Label(0, 0, "Tipo"));
            wsheet.addCell(new Label(0, 1, "Post T"));
            wsheet.addCell(new Label(0, 2, "Post I"));
            wsheet.addCell(new Label(0, 3, "Aisl Temp"));
            wsheet.addCell(new Label(0, 4, "Aisl Int"));
            wsheet.addCell(new Label(0, 5, "Abrazadera"));
            wsheet.addCell(new Label(0, 6, "Alambre Ace."));
            wsheet.addCell(new Label(0, 7, "Alambre Gal."));
            wsheet.addCell(new Label(0, 8, "Cable Bujía"));
            wsheet.addCell(new Label(0, 9, "Letrero"));
            wsheet.addCell(new Label(0, 10, "Arodoble"));
            wsheet.addCell(new Label(0, 11, "XPower i8"));
            wsheet.addCell(new Label(0, 12, "Sensor"));
            wsheet.addCell(new Label(0, 13, "TOTAL"));

            wsheet.addCell(new Label(1, 0, "Cantidad"));
            wsheet.addCell(new jxl.write.Number(1, 1, getIntent().getDoubleExtra("POSTT_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 2, getIntent().getDoubleExtra("POSTI_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 3, getIntent().getDoubleExtra("AISLTEMP_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 4, getIntent().getDoubleExtra("AISLINT_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 5, getIntent().getDoubleExtra("ABRAZADERA_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 6, getIntent().getDoubleExtra("ALAMBREACERADO_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 7, getIntent().getDoubleExtra("ALAMBREGALVANIZADO_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 8, getIntent().getDoubleExtra("CABLEBUJIA_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 9, getIntent().getDoubleExtra("LETRERO_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 10, getIntent().getDoubleExtra("ARODOBLE_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 11, getIntent().getDoubleExtra("XPOWERi8_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 12, getIntent().getDoubleExtra("SENSOR_CANTIDAD",0)));
            wsheet.addCell(new jxl.write.Number(1, 13, getIntent().getDoubleExtra("TOTAL",0)));
            workbook.write();
            Toast.makeText(this, "Archivo Guardado", Toast.LENGTH_LONG).show();
            workbook.close();


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error en la creación", Toast.LENGTH_LONG).show();
        } catch (RowsExceededException e) {
            e.printStackTrace();
            Toast.makeText(this, "Número de filas excedida", Toast.LENGTH_LONG).show();
        } catch (WriteException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error en la escritura del xlsx", Toast.LENGTH_LONG).show();
        }
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
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), FILE_NAME_TXT);

        // Write File
        try {
            fos = new FileOutputStream(file);
            fos.write(text.getBytes());

            Toast.makeText(this, "Guardado como: " + FILE_NAME_TXT, Toast.LENGTH_LONG).show();
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
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + FILE_NAME_TXT);

        if (file.exists()){
            shareIntent.setType("*/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse("file://" + file.getAbsolutePath()));
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartiendo...");
            startActivity(Intent.createChooser(shareIntent, "Compartir Archivo"));
        }
    }
    // endregion
}
