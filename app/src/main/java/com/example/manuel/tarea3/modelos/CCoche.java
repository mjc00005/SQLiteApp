package com.example.manuel.tarea3.modelos;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Manuel on 22/01/2016.
 */
public class CCoche {
    private int Id;
    private String nombre;
    private String color;
    private int asientos;
    ArrayList<CConductor> listaCondAsociados;

    public CCoche(int asientos, String color, String nombre) {
        //this.Id=0;
        this.asientos = asientos;
        this.color = color;
        this.nombre = nombre;
        this.listaCondAsociados=new ArrayList<>();
    }

    public CCoche() {
        this.Id=0;
        this.asientos = 0;
        this.color = "";
        this.nombre = "";
        this.listaCondAsociados=new ArrayList<>();
    }

    public ArrayList<CConductor> getListaCondAsociados() {
        return listaCondAsociados;
    }

    public void setListaCondAsociados(ArrayList<CConductor> listaCondAsociados) {
        this.listaCondAsociados = listaCondAsociados;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAsientos() {
        return asientos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public static CCoche toCursorToCoche(Cursor cursor){

        CCoche coche = new CCoche();

        if(!cursor.isNull(0)){
            coche.setId(cursor.getInt(0));
        }
        if(!cursor.isNull(1)){
            coche.setNombre(cursor.getString(1));
        }
        if(!cursor.isNull(2)){
            coche.setColor(cursor.getString(2));
        }
        if(!cursor.isNull(3)){
            coche.setAsientos(cursor.getInt(3));
        }

        return coche;
    }

    public void addConductorAsociado(CConductor c){
        this.listaCondAsociados.add(c);
    }
}
