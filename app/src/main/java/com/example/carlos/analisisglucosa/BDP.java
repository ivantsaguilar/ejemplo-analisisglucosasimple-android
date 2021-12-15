package com.example.carlos.analisisglucosa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by carlos on 31/03/16.
 */
public class BDP extends SQLiteOpenHelper {

    private final Context myContext;
    private static BDP mInstance;
    //private static SQLiteDatabase myWritableDb;

    //Sentencia SQL para crear una tabla
    String sqlCreateP = "CREATE TABLE Paciente(idpaciente INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, direccion TEXT, telefono TEXT, peso TEXT, estatura TEXT, genero TEXT, edad TEXT, login TEXT, password TEXT)";
   // String sqlInsert = "INSERT INTO Paciente(nombre,direccion,telefono,peso,estatura,genero,edad,login,password) VALUES ('Carlos','3 sur','8882801','65','174','Masculino','25','hola','clave' )";
    String sqlCreateM = "CREATE TABLE Medico(idmedico INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nombre TEXT, telefono TEXT, email TEXT)";
    String sqlCreateR = "CREATE TABLE Registro (idregistro  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,mgdl DECIMAL NOT NULL ,fechahora DATETIME NOT NULL ,idpaciente INTEGER NOT NULL)";
   // String sqlInsert1 = "INSERT INTO Paciente(nombre,direccion,telefono,padecimiento,medicamento,peso,estatura,genero,edad) VALUES ('Juan','5 sur','8882801','S/P','S/M','69','178','H','45')";

    private BDP(Context contexto){
        super(contexto, "Paciente", null, 1);
        this.myContext=contexto;
    }

    public static BDP getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BDP(context);
        }
        return mInstance;
    }

    public void onCreate(SQLiteDatabase db){
        //se ejecuta la sentencia de creacion de la tabla
        db.execSQL(sqlCreateP);
        db.execSQL(sqlCreateM);
        db.execSQL(sqlCreateR);

      //  db.execSQL(sqlInsert);
       // db.execSQL(sqlInsert1);

    }
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Paciente");
        db.execSQL("DROP TABLE IF EXISTS Medico");
        db.execSQL("DROP TABLE IF EXISTS Registro");
        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateP);
        db.execSQL(sqlCreateM);
        db.execSQL(sqlCreateR);
    }


}
