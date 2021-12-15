package com.example.carlos.analisisglucosa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Paciente extends AppCompatActivity {

    private String nombre_pac;
    private String direccion_pac;
    private String telefono_pac;
    private String peso_pac;
    private String estatura_pac;
    private String genero_pac;
    private String edad_pac;
    private String login_pac;
    private String password_pac;

    EditText myEditText;
    EditText myEditText1;
    EditText myEditText2;
    EditText myEditText3;
    EditText myEditText4;
    EditText myEditText5;
    EditText myEditText6;
    EditText myEditText7;
    EditText myEditText8;

    BDP base1;
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        base1 = BDP.getInstance(this);
        Toast.makeText(getBaseContext(), "Por favor registre sus datos", Toast.LENGTH_LONG).show();
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

    public void guardarPac(View v){
        myEditText = (EditText) findViewById(R.id.nom_pac);
        this.nombre_pac = myEditText.getText().toString();
        myEditText1 = (EditText) findViewById(R.id.dir_pac);
        this.direccion_pac = myEditText1.getText().toString();
        myEditText2 = (EditText) findViewById(R.id.tel_pac);
        this.telefono_pac = myEditText2.getText().toString();
        myEditText3 = (EditText) findViewById(R.id.peso_pac);
        this.peso_pac = myEditText3.getText().toString();
        myEditText4 = (EditText) findViewById(R.id.est_pac);
        this.estatura_pac = myEditText4.getText().toString();
        myEditText5 = (EditText) findViewById(R.id.gen_pac);
        this.genero_pac = myEditText5.getText().toString();
        myEditText6 = (EditText) findViewById(R.id.edad_pac);
        this.edad_pac = myEditText6.getText().toString();
        myEditText7 = (EditText) findViewById(R.id.log_pac);
        this.login_pac = myEditText7.getText().toString();
        myEditText8 = (EditText) findViewById(R.id.psw_pac);
        this.password_pac = myEditText8.getText().toString();


        if (nombre_pac.equals("") || direccion_pac.equals("") ||  telefono_pac.equals("")|| peso_pac.equals("") || estatura_pac.equals("") || genero_pac.equals("") || edad_pac.equals("") || login_pac.equals("") || password_pac.equals("") ) {
            Toast.makeText(getBaseContext(), "Algunos campos están vacios, no se puede agregar registro", Toast.LENGTH_SHORT).show();
        } else {
            db1 = base1.getWritableDatabase();
            if (db1 != null) {

                db1.execSQL("INSERT INTO Paciente(nombre,direccion,telefono,peso,estatura,genero,edad,login,password) " + "VALUES ('" + nombre_pac + "','" + direccion_pac + "','" + telefono_pac + "','" + peso_pac + "','" + estatura_pac + "','" + genero_pac + "','" + edad_pac + "','" + login_pac + "','" + password_pac + "')");
            } else
            Toast.makeText(getBaseContext(), "No se ha abierto de manera correcta base de datos", Toast.LENGTH_SHORT).show();
            db1.close();

            myEditText.setText("");
            myEditText1.setText("");
            myEditText2.setText("");
            myEditText3.setText("");
            myEditText4.setText("");
            myEditText5.setText("");
            myEditText6.setText("");


            Intent intent = new Intent(Paciente.this, Medico.class);
            startActivity(intent);


        }
    }


}
