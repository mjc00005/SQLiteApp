package com.example.manuel.tarea3.base_de_datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manuel.tarea3.modelos.CCoche;
import com.example.manuel.tarea3.modelos.CConductor;

import java.util.ArrayList;

/**
 * Created by Manuel on 22/01/2016.
 */
public class BdAdapter {

    Context _ctx;
    DbHelper helper;
    SQLiteDatabase db;


    public BdAdapter(Context ctx) {
        this._ctx = ctx;
        helper = new DbHelper(getContext(), EstructuraBd.NOMBRE_DB,null, EstructuraBd.VERSION_DB);
    }

    private Context getContext(){

        return _ctx;
    }

    public Boolean abrirDB(){

        if (null != helper) {

            try {

                db= helper.getWritableDatabase();
                if(null!=db){
                    return true;
                }
                return false;

            } catch (Exception e) {
                return false;

            }
        }
        return null;

    }

    public long insertCoches(CCoche coche){

        ContentValues valoresInsertar = new ContentValues();
        valoresInsertar.put(EstructuraBd.TABLA_COCHES.NOMBRE,coche.getNombre());
        valoresInsertar.put(EstructuraBd.TABLA_COCHES.COLOR,coche.getColor());
        valoresInsertar.put(EstructuraBd.TABLA_COCHES.ASIENTOS,coche.getAsientos());

        return db.insert(EstructuraBd.TABLA_COCHES.TABLA_NOMBRE,null,valoresInsertar);
    }

    public ArrayList<CCoche> getAllCoches(){

        ArrayList<CCoche> lista = null;
        Cursor consulta = db.query(EstructuraBd.TABLA_COCHES.TABLA_NOMBRE, null, null,null, null,null,null);

        if(null != consulta && consulta.moveToFirst()){

            do{
                if(null== lista) {
                    lista = new ArrayList<>();
                }
                CCoche coche = CCoche.toCursorToCoche(consulta);
                lista.add(coche);

            }while(consulta.moveToNext());

        }

        return lista;
    }

    public int deleteCocheById(CCoche coche){

        String sWhere = EstructuraBd.TABLA_COCHES.ID + "=" + String.valueOf(coche.getId());

        return db.delete(EstructuraBd.TABLA_COCHES.TABLA_NOMBRE,sWhere,null);
    }


    public long insertAsociacion(int codCond, int codCoche){

        ContentValues valoresInsertar = new ContentValues();
        valoresInsertar.put(EstructuraBd.TABLA_CONDU_COCHES.ID_CCONDUCTOR,codCond);
        valoresInsertar.put(EstructuraBd.TABLA_CONDU_COCHES.ID_COCHE,codCoche);

        return db.insert(EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE, null, valoresInsertar);
    }


    public long insertConductor(CConductor conductor){

        ContentValues valoresInsertar = new ContentValues();
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.NOMBRE,conductor.getNombre());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.DIRECCION,conductor.getDireccion());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.DNI,conductor.getDni());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.EMAIL,conductor.getEmail());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.TELEFONO,conductor.getTelefono());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.ID_COCHE,conductor.getId_coche());

        return db.insert(EstructuraBd.TABLA_CONDUCTORES.TABLA_NOMBRE, null, valoresInsertar);
    }



    public ArrayList<CConductor> getAllConductores(){

        ArrayList<CConductor> lista = null;
        //int i = deleteAso();
        Cursor consulta = db.query(EstructuraBd.TABLA_CONDUCTORES.TABLA_NOMBRE, null, null,null, null,null,null);

        if(null != consulta && consulta.moveToFirst()){

            do{
                if(null== lista) {
                    lista = new ArrayList<>();
                }
                CConductor condu = CConductor.toCursorToConductor(consulta);
                lista.add(condu);

            }while(consulta.moveToNext());

        }



        return lista;
    }

    public ArrayList<CConductor> getAllConductoresbyIdCoche(int id){

        ArrayList<CConductor> lista = null;

        String sWhere = EstructuraBd.TABLA_CONDUCTORES.ID_COCHE + "=" + String.valueOf(id);

        Cursor consulta = db.query(EstructuraBd.TABLA_CONDUCTORES.TABLA_NOMBRE, null, null,null, null,null,null);

        if(null != consulta && consulta.moveToFirst()){

            do{
                if(null== lista) {
                    lista = new ArrayList<>();
                }
                CConductor condu = CConductor.toCursorToConductor(consulta);
                lista.add(condu);

            }while(consulta.moveToNext());

        }

        return lista;
    }


    public int deleteConductorById(CConductor condu){

        String sWhere = EstructuraBd.TABLA_CONDUCTORES.ID + "=" + String.valueOf(condu.getId());

        return db.delete(EstructuraBd.TABLA_CONDUCTORES.TABLA_NOMBRE,sWhere,null);
    }

    public long updateConductor(CConductor conductor){

        String sWhere = EstructuraBd.TABLA_CONDUCTORES.ID + "=" + String.valueOf(conductor.getId());

        ContentValues valoresInsertar = new ContentValues();
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.NOMBRE,conductor.getNombre());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.DIRECCION,conductor.getDireccion());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.DNI,conductor.getDni());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.EMAIL,conductor.getEmail());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.TELEFONO,conductor.getTelefono());
        valoresInsertar.put(EstructuraBd.TABLA_CONDUCTORES.ID_COCHE,conductor.getId_coche());

        return db.update(EstructuraBd.TABLA_CONDUCTORES.TABLA_NOMBRE, valoresInsertar, sWhere, null);
    }

    public long updateCoche(CCoche coche){

        String sWhere = EstructuraBd.TABLA_COCHES.ID + "=" + String.valueOf(coche.getId());

        ContentValues valoresInsertar = new ContentValues();
        valoresInsertar.put(EstructuraBd.TABLA_COCHES.NOMBRE,coche.getNombre());
        valoresInsertar.put(EstructuraBd.TABLA_COCHES.COLOR,coche.getColor());
        valoresInsertar.put(EstructuraBd.TABLA_COCHES.ASIENTOS,coche.getAsientos());

        return db.update(EstructuraBd.TABLA_COCHES.TABLA_NOMBRE, valoresInsertar, sWhere,null);
    }

    public ArrayList<CConductor> getAllAsociaciones(ArrayList<CConductor> listCond, ArrayList<CCoche> listCoches){

        ArrayList<CConductor> lista = null;
        //int i = deleteAso();

        Cursor consulta = db.query(EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE, null, null,null, null,null,null);

        if(null != consulta && consulta.moveToFirst()){

            do{
                int posCoche=0;
                int posCond=0;

                if((!consulta.isNull(0))
                        && (!consulta.isNull(1))
                        && (!consulta.isNull(2)) ){

                    posCond = posConductores(listCond, consulta.getInt(1));
                    posCoche = posCoches(listCoches, consulta.getInt(2));

                }

                listCond.get(posCond).addCocheAsociado(listCoches.get(posCoche));






            }while(consulta.moveToNext());

        }

        lista=listCond;

        return lista;
    }

    private int posConductores(ArrayList<CConductor> list , int id){
        int pos=-1;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==id){
                return i;
            }
        }
        return -1;

    }

    private int posCoches(ArrayList<CCoche> list , int id){
        int pos=-1;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==id){
                return i;
            }
        }
        return -1;

    }

    public int deleteAso(){

        int i=21;
        String sWhere = EstructuraBd.TABLA_CONDU_COCHES.ID + "=" + String.valueOf(i);

        return db.delete(EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE,sWhere,null);
    }

    public int deleteAsoByCondu(CConductor condu){

        String sWhere = EstructuraBd.TABLA_CONDU_COCHES.ID_CCONDUCTOR + "=" + String.valueOf(condu.getId());

        return db.delete(EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE,sWhere,null);
    }

    public int deleteAsoByCoche(CCoche coche){

        String sWhere = EstructuraBd.TABLA_CONDU_COCHES.ID_COCHE + "=" + String.valueOf(coche.getId());

        return db.delete(EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE,sWhere,null);
    }

    public ArrayList<Integer> getAllConductoresAsociadosbyIdCoche(int id){

        //ArrayList<CConductor> lista = null;
        ArrayList<Integer> listaCod = null;

        String sWhere = EstructuraBd.TABLA_CONDU_COCHES.ID_COCHE + "=" + String.valueOf(id);

        Cursor consulta = db.query(EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE, null, sWhere,null, null,null,null);

        if(null != consulta && consulta.moveToFirst()){

            //
            do{
                if(null== listaCod) {
                    listaCod = new ArrayList<>();
                }
                listaCod.add(consulta.getInt(1));

            }while(consulta.moveToNext());

        }

        return listaCod;
    }

    public ArrayList<Integer> getAllCochesAsociadosbyIdCoonductor(int id){

        //ArrayList<CConductor> lista = null;
        ArrayList<Integer> listaCod = null;

        String sWhere = EstructuraBd.TABLA_CONDU_COCHES.ID_CCONDUCTOR + "=" + String.valueOf(id);

        Cursor consulta = db.query(EstructuraBd.TABLA_CONDU_COCHES.TABLA_NOMBRE, null, sWhere,null, null,null,null);

        if(null != consulta && consulta.moveToFirst()){

            //
            do{
                if(null== listaCod) {
                    listaCod = new ArrayList<>();
                }
                listaCod.add(consulta.getInt(2));

            }while(consulta.moveToNext());

        }

        return listaCod;
    }


}
