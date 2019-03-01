package com.neyra.leonardo.cotizadorcerco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    final double ListaUnidad_postT = 8.5;
    final double ListaUnidad_postI = 8.5;
    final double ListaUnidad_AislTemp = 0.5;
    final double ListaUnidad_AislInt = 0.4;
    final double ListaUnidad_Abrazadera = 0.15;
    final double ListaUnidad_AlambreAcerado = 6;
    final double ListaUnidad_AlambreGalvanizado = 5.4;
    final double ListaUnidad_CableBujia = 40;
    final double ListaUnidad_Letrero = 1.5;
    final double ListaUnidad_Arodoble = 0.5;
    final double ListaUnidad_XpowerI8 = 138;
    final double ListaUnidad_SensorFlexion = 8;

    private EditText perimetro;
    private EditText segmento;
    private EditText linea;
    private EditText distancia;
    private EditText ganancia;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readElements();

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get raw values
                String strPerimetro = perimetro.getText().toString();
                String strSegmento = segmento.getText().toString();
                String strLinea = linea.getText().toString();
                String strDistancia = distancia.getText().toString();
                String strGanancia = ganancia.getText().toString();

                // Check if all fields are empty or full
                if (strPerimetro.length() == 0 || strSegmento.length() == 0 || strLinea.length() == 0 || strDistancia.length() == 0 || strGanancia.length() == 0){
                    Toast.makeText(MainActivity.this, "Llene todos los campos", Toast.LENGTH_LONG).show();

                }
                else{
                    // Parse raw values into double
                    double valPerimetro = Double.parseDouble(strPerimetro);
                    double valSegmento = Double.parseDouble(strSegmento);
                    double valLinea = Double.parseDouble(strLinea);
                    double valDistancia = Double.parseDouble(strDistancia);
                    double valGanancia = Double.parseDouble(strGanancia);

                    // region Calculate new values
                    //region POST T
                    double postT_Cantidad = valSegmento*2;
                    double postT_Precio = 0;
                    if(postT_Cantidad >= 0){
                        postT_Precio = ListaUnidad_postT/2;
                    }
                    double postT_SubTotal = postT_Cantidad*postT_Precio;
                    //endregion

                    //region POST I
                    double postI_Cantidad = getDecimal ((valPerimetro/valDistancia)-valSegmento);
                    double postI_Precio = 0;
                    if(postI_Cantidad >= 0){
                        postI_Precio = ListaUnidad_postI/2;
                    }
                    double postI_SubTotal = postI_Cantidad*postI_Precio;
                    //endregion

                    //region AISL TEMP
                    double AislTemp_Cantidad = valLinea*postT_Cantidad;
                    double AislTemp_Precio = 0;
                    if(AislTemp_Cantidad >= 0){
                        AislTemp_Precio = ListaUnidad_AislTemp/2;
                    }
                    double AislTemp_SubTotal = AislTemp_Cantidad*AislTemp_Precio;
                    //endregion

                    //region AISL INT
                    double AislInt_Cantidad = valLinea*postI_Cantidad;
                    double AislInt_Precio = 0;
                    if(AislInt_Cantidad >= 0){
                        AislInt_Precio = ListaUnidad_AislInt/2;
                    }
                    double AislInt_SubTotal = AislInt_Cantidad*AislInt_Precio;
                    //endregion

                    //region ABRAZADERA
                    double Abrazadera_Cantidad = (((valLinea*2)-2)+(valSegmento*valLinea*2)-(valLinea*2));
                    double Abrazadera_Precio = 0;
                    if(Abrazadera_Cantidad >= 0){
                        Abrazadera_Precio = ListaUnidad_Abrazadera/2;
                    }
                    double Abrazadera_SubTotal = Abrazadera_Cantidad*Abrazadera_Precio;
                    //endregion

                    //region ALAMBRE ACERADO (Kg.)
                    double AlambreAcerado_Cantidad = getDecimal(((valPerimetro*valLinea)/20));
                    double AlambreAcerado_Precio = 0;
                    if(AlambreAcerado_Cantidad >= 0){
                        AlambreAcerado_Precio = ListaUnidad_AlambreAcerado/2;
                    }
                    double AlambreAcerado_SubTotal = AlambreAcerado_Cantidad*AlambreAcerado_Precio;
                    //endregion

                    //region ALAMBRE GALVANIZADO (Kg.)
                    double AlambreGalvanizado_Cantidad = getDecimal(((valSegmento*4)/20));
                    double AlambreGalvanizado_Precio = 0;
                    if(AlambreGalvanizado_Cantidad >= 0){
                        AlambreGalvanizado_Precio = ListaUnidad_AlambreGalvanizado/2;
                    }
                    double AlambreGalvanizado_SubTotal = AlambreGalvanizado_Cantidad*AlambreGalvanizado_Precio;
                    //endregion

                    //region CABLE BUJIA (Rollo 50m.)
                    double CableBujia_Cantidad = 1;
                    double CableBujia_Precio = 0;
                    if(CableBujia_Cantidad >= 0){
                        CableBujia_Precio = ListaUnidad_CableBujia/2;
                    }
                    double CableBujia_SubTotal = CableBujia_Cantidad*CableBujia_Precio;
                    //endregion

                    //region LETRERO
                    double Letrero_Cantidad = getDecimal((valPerimetro/10));
                    double Letrero_Precio = 0;
                    if(Letrero_Cantidad >= 0){
                        Letrero_Precio = ListaUnidad_Letrero/2;
                    }
                    double Letrero_SubTotal = Letrero_Cantidad*Letrero_Precio;
                    //endregion

                    //region ARODOBLE/SIMPLE
                    double Arodoble_Cantidad = postI_Cantidad;
                    double Arodoble_Precio = 0;
                    if(Arodoble_Cantidad >= 0){
                        Arodoble_Precio = ListaUnidad_Arodoble/2;
                    }
                    double Arodoble_SubTotal = Arodoble_Cantidad*Arodoble_Precio;
                    //endregion

                    //region KIT-XPOWERi8SIR
                    double XpowerI8_Cantidad = 1;
                    double XpowerI8_Precio = 0;
                    if(XpowerI8_Cantidad >= 0){
                        XpowerI8_Precio = ListaUnidad_XpowerI8/2;
                    }
                    double XpowerI8_SubTotal = XpowerI8_Cantidad*XpowerI8_Precio;
                    //endregion

                    //region SENSOR DE FLEXION
                    double SensorFlexion_Cantidad = valSegmento*2;
                    double SensorFlexion_Precio = 0;
                    if(SensorFlexion_Cantidad >= 0){
                        SensorFlexion_Precio = ListaUnidad_SensorFlexion/2;
                    }
                    double SensorFlexion_SubTotal = SensorFlexion_Cantidad*SensorFlexion_Precio;
                    //endregion

                    //region TOTAL
                    double total = postT_SubTotal + postI_SubTotal + AislTemp_SubTotal + AislInt_SubTotal + Abrazadera_SubTotal
                            + AlambreAcerado_SubTotal + AlambreGalvanizado_SubTotal + CableBujia_SubTotal + Letrero_SubTotal
                            + Arodoble_SubTotal + XpowerI8_SubTotal + SensorFlexion_SubTotal;
                    double vent = (total*((valGanancia/100)+1));
                    //endregion

                    //endregion

                    Toast.makeText(MainActivity.this, "Venta: $" + vent, Toast.LENGTH_LONG).show();

                    // Pack new values an intent object
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    // post T
                    intent.putExtra("POSTT_CANTIDAD", postT_Cantidad);
                    intent.putExtra("POSTT_PRECIO", postT_Precio);
                    intent.putExtra("POSTT_SUBTOTAL", postT_SubTotal);
                    // post I
                    intent.putExtra("POSTI_CANTIDAD", postI_Cantidad); //
                    intent.putExtra("POSTI_PRECIO", postI_Precio);
                    intent.putExtra("POSTI_SUBTOTAL", postI_SubTotal);
                    // AISL TEMP
                    intent.putExtra("AISLTEMP_CANTIDAD", AislTemp_Cantidad);
                    intent.putExtra("AISLTEMP_PRECIO", AislTemp_Precio);
                    intent.putExtra("AISLTEMP_SUBTOTAL", AislTemp_SubTotal);
                    // AISL INT
                    intent.putExtra("AISLINT_CANTIDAD", AislInt_Cantidad);
                    intent.putExtra("AISLINT_PRECIO", AislInt_Precio);
                    intent.putExtra("AISLINT_SUBTOTAL", AislInt_SubTotal);
                    // ABRAZADERA
                    intent.putExtra("ABRAZADERA_CANTIDAD", Abrazadera_Cantidad);
                    intent.putExtra("ABRAZADERA_PRECIO", Abrazadera_Precio);
                    intent.putExtra("ABRAZADERA_SUBTOTAL", Abrazadera_SubTotal);
                    // ALAMBRE ACERADO
                    intent.putExtra("ALAMBREACERADO_CANTIDAD", AlambreAcerado_Cantidad); //
                    intent.putExtra("ALAMBREACERADO_PRECIO", AlambreAcerado_Precio);
                    intent.putExtra("ALAMBREACERADO_SUBTOTAL", AlambreAcerado_SubTotal);
                    // ALAMBRE GALVANIZADO
                    intent.putExtra("ALAMBREGALVANIZADO_CANTIDAD", AlambreGalvanizado_Cantidad); //
                    intent.putExtra("ALAMBREGALVANIZADO_PRECIO", AlambreGalvanizado_Precio);
                    intent.putExtra("ALAMBREGALVANIZADO_SUBTOTAL", AlambreGalvanizado_SubTotal);
                    // CABLE BUJIA
                    intent.putExtra("CABLEBUJIA_CANTIDAD", CableBujia_Cantidad);
                    intent.putExtra("CABLEBUJIA_PRECIO", CableBujia_Precio);
                    intent.putExtra("CABLEBUJIA_SUBTOTAL", CableBujia_SubTotal);
                    // LETRERO
                    intent.putExtra("LETRERO_CANTIDAD", Letrero_Cantidad); //
                    intent.putExtra("LETRERO_PRECIO", Letrero_Precio);
                    intent.putExtra("LETRERO_SUBTOTAL", Letrero_SubTotal);
                    // ARODOBLE/SIMPLE
                    intent.putExtra("ARODOBLE_CANTIDAD", Arodoble_Cantidad); //
                    intent.putExtra("ARODOBLE_PRECIO", Arodoble_Precio);
                    intent.putExtra("ARODOBLE_SUBTOTAL", Arodoble_SubTotal);
                    // KIT-XPOWERi8SIR
                    intent.putExtra("XPOWERi8_CANTIDAD", XpowerI8_Cantidad); //
                    intent.putExtra("XPOWERi8_PRECIO", XpowerI8_Precio);
                    intent.putExtra("XPOWERi8_SUBTOTAL", XpowerI8_SubTotal);
                    // SENSOR DE FLEXION
                    intent.putExtra("SENSOR_CANTIDAD", SensorFlexion_Cantidad);
                    intent.putExtra("SENSOR_PRECIO", SensorFlexion_Precio);
                    intent.putExtra("SENSOR_SUBTOTAL", SensorFlexion_SubTotal);
                    // TOTAL
                    intent.putExtra("TOTAL", total);
                    intent.putExtra("VENTA", vent);

                    // Clear EditText
                    //clearAll();

                    // Start ResultActivity
                    startActivity(intent);
                }
            }
        });
    }

    private void readElements(){
        perimetro = (EditText) findViewById(R.id.perimetro);
        segmento = (EditText) findViewById(R.id.segmentos);
        linea = (EditText) findViewById(R.id.lineas);
        distancia = (EditText) findViewById(R.id.distanciaPostes);
        ganancia = (EditText) findViewById(R.id.ganancia);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
    }

    private void clearAll(){
        perimetro.setText("");
        segmento.setText("");
        linea.setText("");
        distancia.setText("");
        ganancia.setText("");
    }

    private double getDecimal(double number){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
        double intValue = Double.parseDouble(String.valueOf(bigDecimal.intValue()));
        double decimalValue = Double.parseDouble(bigDecimal.subtract(new BigDecimal(intValue)).toPlainString());
        if (decimalValue == 0){
            return intValue;
        }
        else{
            return intValue + 1;
        }
    }
}
