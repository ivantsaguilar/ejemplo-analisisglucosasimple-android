package com.example.carlos.analisisglucosa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Medico extends AppCompatActivity {

    private String nombre_med;
    private String telefono_med;
    private String e_med;

    EditText myEditText;
    EditText myEditText1;
    EditText myEditText2;

    BDP base1;
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);
        base1 = BDP.getInstance(this);
        Toast.makeText(getBaseContext(), "Por favor registre a su médico", Toast.LENGTH_LONG).show();
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

    public void guardarMed(View v){
        myEditText = (EditText) findViewById(R.id.nom_med);
        this.nombre_med = myEditText.getText().toString();
        myEditText1 = (EditText) findViewById(R.id.tel_med);
        this.telefono_med = myEditText1.getText().toString();
        myEditText2 = (EditText) findViewById(R.id.emai_med);
        this.e_med = myEditText2.getText().toString();



        if (nombre_med.equals("") || telefono_med.equals("") || e_med.equals("")  ) {
            Toast.makeText(getBaseContext(), "Algunos campos están vacios, no se puede agregar registro", Toast.LENGTH_SHORT).show();
        } else {
            db1 = base1.getWritableDatabase();
            Cursor cur = db1.rawQuery("SELECT nombre FROM Medico", null);
            if (db1 != null) {

                if (cur.moveToNext()){
                    db1.execSQL("DELETE FROM Medico WHERE idmedico = 1");
                    Toast.makeText(getBaseContext(),"Se borro medico",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getBaseContext(),"No hay medico",Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO Medico(nombre,telefono,email) " + "VALUES ('" + nombre_med + "','" + telefono_med + "','" + e_med + "')");

            } else
                Toast.makeText(getBaseContext(), "No se ha abierto de manera correcta base de datos", Toast.LENGTH_SHORT).show();

            cur.close();
            db1.close();
            myEditText.setText("");
            myEditText1.setText("");
            myEditText2.setText("");


            Intent intent = new Intent(Medico.this, Acceso.class);
            startActivity(intent);
        }
    }
}
