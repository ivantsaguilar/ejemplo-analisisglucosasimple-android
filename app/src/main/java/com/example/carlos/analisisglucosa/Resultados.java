package com.example.carlos.analisisglucosa;
import java.text.Format;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Arrays;

//import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class Resultados extends Activity {

    private XYPlot mySimpleXYPlot;
    private float points[];
    private Number[] datos;
    /*final String[] dates={"Ene","Feb","Mar",
                          "Apr","May","Jun",
                          "Jul","Ago","Sep",
                          "Oct","Nov","Dic"};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Bundle bundle = getIntent().getExtras();
        points = bundle.getFloatArray("puntos");
        final String dates[] = bundle.getStringArray("fechas");

        // Inicializamos el objeto XYPlot búscandolo desde el layout:
        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        // Creamos dos arrays de prueba. En el caso real debemos reemplazar
        // estos datos por los que realmente queremos mostrar
        //Number[] datos = { 1, 8, 5, 2, 7, 4, 12, 15, 20, 6, 3, 31 };
        datos = new Number[points.length];
        for( int i = 0 ; i < points.length ; i++ )
            datos[i] = points[i];

        // Añadimos Línea Número UNO:
        SimpleXYSeries serie =  new SimpleXYSeries(Arrays.asList(datos), // Array de datos
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Sólo valores verticales
                "Grafica Semanal"); // Nombre de la primera serie

        mySimpleXYPlot.setDomainLabel("Fechas");
        //double arg0 = 2.5;

        mySimpleXYPlot.setRangeLabel("Niveles de Glucosa");
        //mySimpleXYPlot.setTicksPerDomainLabel(10);

        mySimpleXYPlot.setDomainValueFormat(new Format() {

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                return new StringBuffer( dates[ ( (Number)obj).intValue() ]  );
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        mySimpleXYPlot.getGraphWidget().setRangeLabelWidth(50);
        mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
        // Reduce the number of range labels
        mySimpleXYPlot.setTicksPerRangeLabel(2);

        // Remove all the developer guides from the chart
        //mySimpleXYPlot.disableAllMarkup();

        // Modificamos los colores de la primera serie
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),                   // line color
                Color.rgb(0, 100, 0),                   // point color
                null,                                   // fill color (none)
                new PointLabelFormatter(Color.WHITE));

        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(serie, series1Format);

    }

}
