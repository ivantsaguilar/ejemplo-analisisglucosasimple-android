package com.example.carlos.analisisglucosa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Acceso extends AppCompatActivity {

    private String usr;
    private String psw;
    private String login;
    private String password;


    EditText textusr;
    EditText textpsw;

    BDP base;
    SQLiteDatabase b;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
        base = BDP.getInstance(this);

        b = base.getReadableDatabase();
        Cursor c = b.rawQuery(" SELECT login,password FROM Paciente ", null);
        if(c.moveToFirst()) {

            String name = c.getString(0);
            String phone = c.getString(1);

            Toast.makeText(getBaseContext(), name + "\n" + phone, Toast.LENGTH_LONG).show();
        }
        c.close();
        b.close();

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

    public void acceso(View v){
        textusr = (EditText) findViewById(R.id.usuario);
        this.usr = textusr.getText().toString();

        textpsw = (EditText) findViewById(R.id.clave);
        this.psw = textpsw.getText().toString();
        textusr.setText("");
        textpsw.setText("");

        if(usr.equals("") || (psw.equals(""))) {
           Toast.makeText(getBaseContext(), "Por favor ingrese la información que se pide", Toast.LENGTH_SHORT).show();
        }else {
            // Busqueda
            b = base.getReadableDatabase();

            String[] campos1 = new String[]{"login", "password"};
            Cursor c = b.query("Paciente", campos1, null, null, null, null, null);

           // Cursor c = b.rawQuery(" SELECT login,clave FROM Paciente ", null);

            if(c.moveToFirst()){

                    login = c.getString(0);
                    password = c.getString(1);

                //Toast.makeText(getBaseContext(), , Toast.LENGTH_LONG).show();
                //getString(R.string.contraseña)
                    if(usr.equals(login) && psw.equals(password)){

                        Bundle p = new Bundle();
                        p.putString("nombre", usr);

                        Intent intent = new Intent(Acceso.this, Analisis.class);
                        intent.putExtras(p);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getBaseContext(),"Error al intentar accesar, por favor verifique sus datos" , Toast.LENGTH_SHORT).show();
                    }

            }else Toast.makeText(getBaseContext(),"Error al intentar accesar, por favor verifique sus datos" , Toast.LENGTH_SHORT).show();
            c.close();
            b.close();

        }
    }

    public void cancelar(View v){
        textusr = (EditText) findViewById(R.id.usuario);
        textpsw = (EditText) findViewById(R.id.clave);
        textusr.setText("");
        textpsw.setText("");

    }
}
