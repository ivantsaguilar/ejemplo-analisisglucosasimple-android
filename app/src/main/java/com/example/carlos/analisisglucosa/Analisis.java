package com.example.carlos.analisisglucosa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Analisis extends AppCompatActivity {

    private String level;
    private float nivel;
    private String fecha;
    private float points[];
    private String dates[];

    EditText lvl;
    BDP base;
    SQLiteDatabase b;
    Bundle p ; //Definimos el contenedor de parametros
    String nombre; //Guardamos el parametro nombre en la variable nombre
   // String psw; //Guardamos el parametro nombre en la variable nombre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);
        base = BDP.getInstance(this);
        p = this.getIntent().getExtras(); //Definimos el contenedor de parametros
        nombre = p.getString("nombre"); //Guardamos el parametro nombre en la variable nombre
      //  psw = p.getString("psw"); //Guardamos el parametro nombre en la variable nombre


        Toast.makeText(getBaseContext(),"Usuario válido\nBienvenido "+nombre, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void ayunas(View v) {

        lvl = (EditText) findViewById(R.id.nivel);
        this.level = lvl.getText().toString();
        lvl.setText("");
        if (level.equals("")) {
            Toast.makeText(getBaseContext(), "Ingrese datos", Toast.LENGTH_SHORT).show();
        } else {
            nivel = Float.parseFloat(level);
            fecha = (new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((Calendar.getInstance()).getTime());

            b = base.getWritableDatabase();
            b.execSQL("INSERT INTO Registro(mgdl,fechahora,idpaciente) " + "VALUES ('" + nivel + "','" + fecha + "','" + 1 + "' )");
            b.close();


            //comparaciones
            if ((nivel >= 70) && (nivel < 100)) {
                Toast.makeText(getBaseContext(), "Usted tiene un nivel dentro de lo normal", Toast.LENGTH_SHORT).show();
                //control = "SinD";

            } else if (nivel >= 100) {
               // Toast.makeText(getApplicationContext(), "Glucosa Alta", Toast.LENGTH_SHORT).show();
                String phone;
                String email;
                String n = null;
                b = base.getReadableDatabase();
                Cursor r = b.rawQuery("SELECT telefono,email FROM Medico", null);
                if (r.moveToNext()) {

                    phone = r.getString(0);
                    email = r.getString(1);
                    Toast.makeText(getBaseContext(), phone+"\n"+email, Toast.LENGTH_SHORT).show();
                    r = b.rawQuery("SELECT nombre FROM Paciente", null);
                    if (r.moveToNext()) {
                        n = r.getString(0);
                    }

                    /****ENVIO DE CORREO ELECTRONICO****/
                    sendsms(phone, n + " ha presentado un nivel de glucosa alto");
                    sendmail(email, n + " ha presentado un nivel de glucosa Alto", "Su paciente " + n + " presento un nivel de glucosa de " + nivel + " mg/dl, el dia " + fecha);

                    /******ENVIO DE SMS******/


                }
                r.close();
                b.close();


            } else if (nivel < 70) {

                //Toast.makeText(getApplicationContext(), "Glucosa Alta", Toast.LENGTH_SHORT).show();
                String phone;
                String email;
                String n = null;
                b = base.getReadableDatabase();
                Cursor r = b.rawQuery("SELECT telefono,email FROM Medico", null);
                if (r.moveToNext()) {

                    phone = r.getString(0);
                    email = r.getString(1);
                    Toast.makeText(getBaseContext(), phone+"\n"+email, Toast.LENGTH_SHORT).show();
                    r = b.rawQuery("SELECT nombre FROM Paciente", null);
                    if (r.moveToNext()) {
                        n = r.getString(0);
                    }
                    String mensaje = n + " ha presentado un nivel de glucosa BAJO";
                    String msjasun = n + " ha presentado un nivel de glucosa BAJO";
                    String msjmail = "Su paciente " + n + " presento un nivel de glucosa de " + nivel + " mg/dl, el dia " + fecha;

                    /****ENVIO DE CORREO ELECTRONICO****/
                    sendsms(phone, mensaje);
                    sendmail(email, msjasun, msjmail);

                    /******ENVIO DE SMS******/


                }
                r.close();
                b.close();
                // Toast.makeText(getBaseContext(), "Diabetes", Toast.LENGTH_SHORT).show();
                //control = "D";

            }
                //Toast.makeText(getBaseContext(), "Error en la lectura", Toast.LENGTH_SHORT).show();


        }
    }

    public void despues(View v) {

        lvl = (EditText) findViewById(R.id.nivel);
        this.level = lvl.getText().toString();
        lvl.setText("");
        if (level.equals("")) {
            Toast.makeText(getBaseContext(), "Ingrese datos", Toast.LENGTH_SHORT).show();
        } else {
            nivel = Float.parseFloat(level);
            fecha = (new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format((Calendar.getInstance()).getTime());

            b = base.getWritableDatabase();
            b.execSQL("INSERT INTO Registro(mgdl,fechahora,idpaciente) " + "VALUES ('" + nivel + "','" + fecha + "','" + 1 + "' )");
            b.close();


            //comparaciones
            if (nivel>=70 && nivel <140) {
                Toast.makeText(getBaseContext(), "Usted tiene un nivel dentro de lo normal", Toast.LENGTH_SHORT).show();
                //control = "SinD";

            } else if (nivel >= 140) {

                //Toast.makeText(getApplicationContext(), "Glucosa Alta", Toast.LENGTH_SHORT).show();
                String phone;
                String email;
                String n = null;
                b = base.getReadableDatabase();
                Cursor r = b.rawQuery("SELECT telefono,email FROM Medico", null);
                if (r.moveToNext()) {

                    phone = r.getString(0);
                    email = r.getString(1);
                    Toast.makeText(getBaseContext(), phone+"\n"+email, Toast.LENGTH_SHORT).show();
                    r = b.rawQuery("SELECT nombre FROM Paciente", null);
                    if (r.moveToNext()) {
                        n = r.getString(0);
                    }
                    String mensaje = n + " ha presentado un nivel de glucosa ALTO";
                    String msjasun = n + " ha presentado un nivel de glucosa ALTO";
                    String msjmail = "Su paciente " + n + " presento un nivel de glucosa de " + nivel + " mg/dl, el dia " + fecha;

                    /****ENVIO DE CORREO ELECTRONICO****/
                    sendsms(phone, mensaje);
                    sendmail(email, msjasun, msjmail);

                    /******ENVIO DE SMS******/


                }
                r.close();
                b.close();
                // Toast.makeText(getBaseContext(), "Diabetes", Toast.LENGTH_SHORT).show();
                //control = "D";

            } else if (nivel < 70) {

                //Toast.makeText(getApplicationContext(), "Glucosa Alta", Toast.LENGTH_SHORT).show();
                String phone;
                String email;
                String n = null;
                b = base.getReadableDatabase();
                Cursor r = b.rawQuery("SELECT telefono,email FROM Medico", null);
                if (r.moveToNext()) {

                    phone = r.getString(0);
                    email = r.getString(1);
                    Toast.makeText(getBaseContext(), phone+"\n"+email, Toast.LENGTH_SHORT).show();
                    r = b.rawQuery("SELECT nombre FROM Paciente", null);
                    if (r.moveToNext()) {
                        n = r.getString(0);
                    }
                    String mensaje = n + " ha presentado un nivel de glucosa BAJO";
                    String msjasun = n + " ha presentado un nivel de glucosa BAJO";
                    String msjmail = "Su paciente " + n + " presento un nivel de glucosa de " + nivel + " mg/dl, el dia " + fecha;

                    /****ENVIO DE CORREO ELECTRONICO****/
                    sendsms(phone, mensaje);
                    sendmail(email, msjasun, msjmail);

                    /******ENVIO DE SMS******/


                }
                r.close();
                b.close();
                // Toast.makeText(getBaseContext(), "Diabetes", Toast.LENGTH_SHORT).show();
                //control = "D";

            }
               // Toast.makeText(getBaseContext(), "Error en la lectura", Toast.LENGTH_SHORT).show();


            //Bundle parametros = new Bundle();
            //parametros.putString("control", control);
            //parametros.putString("nombre", nombre);

            //Define la actividad
            //Intent i = new Intent(Analisis.this, Resultados.class);

            //i.putExtras(parametros);

            //Inicia la actividad
            //startActivity(i);

        }
    }


    public void graficar(View v){
        String sql;


        int maxid;
        b=base.getReadableDatabase();
        Cursor r = b.rawQuery("SELECT max(idregistro) FROM Registro", null);
        if(r.moveToNext()){
            maxid = r.getInt(0);

            points = new float[maxid];
            dates = new String[maxid];

            sql = "SELECT mgdl,fechaHora FROM Registro";
            r = b.rawQuery(sql, null);

            if(r.moveToNext()){
                int i = 0;
                points[i] = r.getFloat(0);
                dates[i] = r.getString(1).substring(0, 3)+ getMonth( Integer.parseInt( r.getString(1).substring(4, 5) ));
                i++;
                for( ; r.moveToNext() ; i++ ){
                    points[i] = r.getFloat(0);
                    dates[i] = r.getString(1).substring(0, 3)+ getMonth( Integer.parseInt( r.getString(1).substring(4, 5) ));
                }
                Intent grafica = new Intent(this, Resultados.class);
                grafica.putExtra("puntos", points);
                grafica.putExtra("fechas", dates);
                startActivity(grafica);
            }else Toast.makeText(getApplicationContext(), "Aun no se han capturado datos", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getApplicationContext(), "Aun no se han capturado datos", Toast.LENGTH_SHORT).show();
    }


    private void sendsms(String phone,String msj){
       // SmsManager smsmanager = SmsManager.getDefault();
        //smsmanager.sendTextMessage(phone, null, msj, null, null);



    Uri uri = Uri.parse("smsto:" +phone);
    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
    it.putExtra("sms_body", msj);
    startActivity(it);
}



    public void sendmail(String destine, String subject, String body){
		/* es necesario un intent que levante la actividad deseada */
        Intent itSend = new Intent(android.content.Intent.ACTION_SEND);

        /* vamos a enviar texto plano a menos que el checkbox est� marcado */
        itSend.setType("plain/text");

        /* colocamos los datos para el env�o */
        itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ destine });
        itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, subject );
        itSend.putExtra(android.content.Intent.EXTRA_TEXT, body );
        /* iniciamos la actividad */
        startActivity(itSend);

    }
    private String getMonth(int m){
        if( m == 1 ) return "Ene";
        else if( m == 2 ) return "Feb";
        else if( m == 3 ) return "Mar";
        else if( m == 4 ) return "Abr";
        else if( m == 5 ) return "May";
        else if( m == 6 ) return "Jun";
        else if( m == 7 ) return "Jul";
        else if( m == 8 ) return "Ago";
        else if( m == 9 ) return "Sep";
        else if( m == 10 ) return "Oct";
        else if( m == 11 ) return "Nov";
            //else if( m == 12 ) return "Dic";
        else return "Dic";
    }

    public void actMed(View v){
        Intent intent = new Intent(Analisis.this, Medico.class);
        startActivity(intent);
    }

}
