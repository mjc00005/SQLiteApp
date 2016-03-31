package com.example.manuel.tarea3.base_de_datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manuel on 22/01/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String aa = crearTablaCoche();
        db.execSQL(aa);
        aa = crearTablaConductor();
        db.execSQL(aa);
        aa = crearTablaCC();
        db.execSQL(aa);

    }


    private String crearTablaCoche(){

        String sqlCoche = "CREATE TABLE If NOT EXISTS "
                + EstructuraBd.TABLA_COCHES.TABLA_NOMBRE + " ( "
                + EstructuraBd.TABLA_COCHES.ID + " integer primary key autoincrement, "
                + EstructuraBd.TABLA_COCHES.NOMBRE + " text, "
                + EstructuraBd.TABLA_COCHES.COLOR + " text, "
                + EstructuraBd.TABLA_COCHES.ASIENTOS + " integer);";


        return sqlCoche;
    }

    private String crearTablaConductor(){

        String sqlCoche = "CREATE TABLE If NOT EXISTS "
                + EstructuraBd.TABLA_CONDUCTORES.TABLA_NOMBRE + " ( "
                + EstructuraBd.TABLA_CONDUCTORES.ID + " integer primary key autoincrement, "
                + EstructuraBd.TABLA_CONDUCTORES.DNI + " text, "
                + EstructuraBd.TABLA_CONDUCTORES.NOMBRE + " text, "
                + EstructuraBd.TABLA_CONDUCTORES.EMAIL + " text, "
                + EstructuraBd.TABLA_CONDUCTORES.DIRECCION + " text, "
                + EstructuraBd.TABLA_CONDUCTORES.TELEFONO + " integer, "
                + EstructuraBd.TABLA_CONDUCTORES.ID_COCHE + " integer);";


        return sqlCoche;
    }


    private String crearTablaCC(){

        String sqlCoche = "CREATE TABLE If NOT EXISTS "
                + EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE + " ( "
                + EstructuraBd.TABLA_CONDU_COCHES.ID + " integer primary key autoincrement, "
                + EstructuraBd.TABLA_CONDU_COCHES.ID_CCONDUCTOR + " text, "
                + EstructuraBd.TABLA_CONDU_COCHES.ID_COCHE + " text);";


        return sqlCoche;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
