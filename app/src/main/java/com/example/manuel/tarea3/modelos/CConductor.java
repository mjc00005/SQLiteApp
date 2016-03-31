package com.example.manuel.tarea3.modelos;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Manuel on 25/01/2016.
 */
public class CConductor {

    int id;
    String _sNombre;
    String _sDireccion;
    String _iDni;
    String _sEmail;
    int _iTelefono;
    int _id_coche;
    ArrayList<CCoche> listaCochesAsignados;

    public CConductor(String direccion, String dni, String email, String nombre, int telefono) {
        this._sDireccion = direccion;
        this._iDni = dni;
        this._sEmail = email;
        this._sNombre = nombre;
        this._iTelefono = telefono;
        this._id_coche=0;
        this.listaCochesAsignados =new ArrayList<>();
        //this.id=0;
    }

    public ArrayList<CCoche> getListaCochesAsignados() {
        return listaCochesAsignados;
    }

    public void setListaCochesAsignados(ArrayList<CCoche> listaCochesAsignados) {
        this.listaCochesAsignados = listaCochesAsignados;
    }

    public int getId_coche() {
        return _id_coche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_coche(int id_coche) {
        this._id_coche = id_coche;
    }

    public String getDireccion() {
        return _sDireccion;
    }

    public void setDireccion(String direccion) {
        this._sDireccion = direccion;
    }

    public String getDni() {
        return _iDni;
    }

    public void setDni(String dni) {
        this._iDni = dni;
    }

    public String getEmail() {
        return _sEmail;
    }

    public void setEmail(String email) {
        this._sEmail = email;
    }

    public String getNombre() {
        return _sNombre;
    }

    public void setNombre(String nombre) {
        this._sNombre = nombre;
    }

    public int getTelefono() {
        return _iTelefono;
    }

    public void setTelefono(int telefono) {
        this._iTelefono = telefono;
    }

    public CConductor() {
        this.id=0;
        this._id_coche=0;
        this.listaCochesAsignados=new ArrayList<>();
    }

    public static CConductor toCursorToConductor(Cursor cursor){

        CConductor conductor = new CConductor();

        if(!cursor.isNull(0)){
            conductor.setId(cursor.getInt(0));
        }
        if(!cursor.isNull(1)){
            conductor.setDni(cursor.getString(1));
        }
        if(!cursor.isNull(2)){
            conductor.setNombre(cursor.getString(2));
        }
        if(!cursor.isNull(3)){
            conductor.setEmail(cursor.getString(3));
        }
        if(!cursor.isNull(3)){
            conductor.setDireccion(cursor.getString(3));
        }
        if(!cursor.isNull(3)){
            conductor.setTelefono(cursor.getInt(3));
        }
        if(!cursor.isNull(3)){
            conductor.setId_coche(cursor.getInt(3));
        }

        return conductor;
    }

    public void addCocheAsociado(CCoche c){
        this.listaCochesAsignados.add(c);
    }



}
