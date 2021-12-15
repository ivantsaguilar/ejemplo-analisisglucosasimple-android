package com.example.carlos.analisisglucosa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    BDP base1;
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        base1 = BDP.getInstance(this);


    }
    @Override
    protected void onStart() {
        super.onStart();
        db1 = base1.getReadableDatabase();
        if (db1 != null) {
            Cursor cur = db1.rawQuery("SELECT nombre FROM Paciente", null);
            if (cur.moveToNext()){
                Intent intent = new Intent(Registro.this, Acceso.class);
                startActivity(intent);
                Registro.this.finish();
            } else {
                Toast.makeText(getBaseContext(), "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registro.this, Paciente.class);
                startActivity(intent);
                Registro.this.finish();
            }
            cur.close();
            db1.close();
        }
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




}
