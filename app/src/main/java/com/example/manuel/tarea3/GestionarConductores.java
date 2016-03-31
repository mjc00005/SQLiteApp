package com.example.manuel.tarea3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manuel.tarea3.base_de_datos.BdAdapter;
import com.example.manuel.tarea3.modelos.CCoche;
import com.example.manuel.tarea3.modelos.CConductor;
import com.example.manuel.tarea3.modelos.CustomAdapterConductores;

import java.util.ArrayList;

public class GestionarConductores extends AppCompatActivity {


    Button btOk, btClear;
    public EditText nombre, dire, dni,email, telefono;
    RecyclerView rvList;
    ArrayList<CConductor> listConductores;
    ArrayList<CCoche> listCoches;
    final public BdAdapter adapter=new BdAdapter(GestionarConductores.this);
    public boolean isModificando =false;
    public int sPosModificando=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_conductores);

        //Inicializamos todos los elementos del layout.
        btOk=(Button) findViewById(R.id.btnOk);
        btClear=(Button) findViewById(R.id.btnClear);

        nombre = (EditText) findViewById(R.id.etxtNombre);
        dire = (EditText) findViewById(R.id.etxtDire);
        dni = (EditText) findViewById(R.id.etxtDni);
        email = (EditText) findViewById(R.id.etxtEmail);
        telefono = (EditText) findViewById(R.id.etxtTel);

        rvList=(RecyclerView) findViewById(R.id.rcv);

        rvList = (RecyclerView) findViewById(R.id.rcv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(GestionarConductores.this);
        rvList.setLayoutManager(manager);


        //Cuando el usuario pulsa el BOTON OK
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!nombre.getText().toString().equals("")) && (!dire.getText().toString().equals(""))
                        && (!dni.getText().toString().equals("")) && (!email.getText().toString().equals("")) && (!telefono.getText().toString().equals(""))) {


                    //Capturamos lo que el usuario ha introducido en la pantalla
                    CConductor condu = new CConductor();
                    condu.setNombre(nombre.getText().toString());
                    condu.setDireccion(dire.getText().toString());
                    condu.setDni(dni.getText().toString());
                    condu.setEmail(email.getText().toString());
                    condu.setTelefono(Integer.parseInt(telefono.getText().toString()));

                    if(isModificando){
                        condu.setId(listConductores.get(sPosModificando).getId());
                    }else{
                        condu.setId(findIDConductor());
                    }


                    guardarDB(condu);


                } else {
                    Toast.makeText(GestionarConductores.this, getResources().getString(R.string.rellanaBien), Toast.LENGTH_LONG).show();
                }


            }
        });


        //Cuando el usuario pulsa el BOTON CLEAR
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                limpiarCajas();

            }
        });


//        CConductor c = new CConductor("Avenida ANdalucia", "77330199","mjc@sdsd", "Manuel Jimenez",878876);
//        CConductor c1 = new CConductor("Avenida ANdalucia", "77330199","mjc@sdsd", "Agustin Fishardo",878876);
//        CConductor c2 = new CConductor("Avenida ANdalucia", "77330199","mjc@sdsd", "Juanmi Pachin0",878876);
//        ArrayList<CConductor> list = new ArrayList();
//        list.add(c);
//        list.add(c1);
//        list.add(c2);


        if(!adapter.abrirDB()) finish();


        listConductores = adapter.getAllConductores();
        listCoches= adapter.getAllCoches();

        //listConductores = adapter.getAllAsociaciones(listConductores, listCoches);
        //listConductores = list;

        if(listConductores==null){
            listConductores = new ArrayList<>();
        }

        if(listCoches==null){
            listCoches = new ArrayList<>();
        }



        inicializarRecycler();

        //inseretarAsociaciones();





    }


    private void inicializarRecycler(){
        //list= new ArrayList<>();
        CustomAdapterConductores adaptador = new CustomAdapterConductores(GestionarConductores.this, listConductores);
        rvList.setAdapter(adaptador);

    }

    private void actualizarListado(){

        if( null != rvList.getAdapter() ){

            ((CustomAdapterConductores) rvList.getAdapter()).notifyDataSetChanged();
        }
    }


    private void limpiarCajas(){

        //Hacemos el clear de todos los campos de la pantalla
        nombre.setText("");
        email.setText("");
        telefono.setText("");
        dni.setText("");
        dire.setText("");
        isModificando =false;
        sPosModificando=0;
    }

    public int findIDConductor(){

        if(listConductores.size()> 0) {
            int aux = listConductores.get(0).getId();

            for (int i = 0; i < listConductores.size(); i++) {
                if (aux < listConductores.get(i).getId()) {
                    aux = listConductores.get(i).getId();
                }
            }

            return aux+1;
        }

        return 1;
    }

    public int findIDCocche(){

        if(listCoches.size()>0) {
            int aux = listCoches.get(0).getId();

            for (int i = 0; i < listCoches.size(); i++) {
                if (aux < listCoches.get(i).getId()) {
                    aux = listCoches.get(i).getId();
                }
            }

            return aux+1;
        }
        return 1;
    }

    public void guardarDB(CConductor condu){

        if(null != condu){

            long result=-3;

            if(isModificando){ //Modificamos
                result= adapter.updateConductor(condu);

            }else{ //Insertamos
                result = adapter.insertConductor(condu); //Devuelve el id si se introduce correctamente

            }

            if (result > 0) { //Dato introducido correctamente en la BBDD

                if(!isModificando){ //Insertando
                    listConductores.add(condu); // Insertamos en la lista
                }else{ //Modificando
                    listConductores.get(sPosModificando).setNombre(condu.getNombre());
                    listConductores.get(sPosModificando).setDni(condu.getDni());
                    listConductores.get(sPosModificando).setEmail(condu.getEmail());
                    listConductores.get(sPosModificando).setDireccion(condu.getDireccion());
                    listConductores.get(sPosModificando).setTelefono(condu.getTelefono());
                    listConductores.get(sPosModificando).setId_coche(condu.getId_coche());
                    listConductores.get(sPosModificando).setId(condu.getId());
                }

                Toast.makeText(GestionarConductores.this, "Se inserto correctamente", Toast.LENGTH_LONG).show();

                actualizarListado(); // Mostramos por la pantalla


            } else {
                Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();

            }

            limpiarCajas();


        }




    }


    public void inseretarAsociaciones(){
        long result;


        result=adapter.insertAsociacion(2,5);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }

        result=adapter.insertAsociacion(2,4);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }

        result=adapter.insertAsociacion(2,2);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }


        result=adapter.insertAsociacion(3,3);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }

        result=adapter.insertAsociacion(3,4);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }

        result=adapter.insertAsociacion(3,7);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }


        result=adapter.insertAsociacion(4,4);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }

        result=adapter.insertAsociacion(4,2);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }

        result=adapter.insertAsociacion(4,3);
        if (result<0){
            Toast.makeText(GestionarConductores.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();
        }

    }


}
