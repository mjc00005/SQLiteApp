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
import com.example.manuel.tarea3.modelos.CustomAdapterCoches;

import java.util.ArrayList;

public class GestionarCoches extends AppCompatActivity {

    Button btOk, btClear;
    public EditText modelo, color, asientos;
    RecyclerView rvList;
    ArrayList<CCoche> listCoches=new ArrayList<>();
    ArrayList<CConductor> listConductores=new ArrayList<>();
    final public BdAdapter adapter=new BdAdapter(GestionarCoches.this);
    public boolean isModificando =false;
    public int sPosModificando=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_coches);

        //Inicializamos todos los elementos del layout.
        btOk=(Button) findViewById(R.id.btnOk);
        btClear=(Button) findViewById(R.id.btnClear);

        modelo = (EditText) findViewById(R.id.etxtModelo);
        color = (EditText) findViewById(R.id.etxtColor);
        asientos = (EditText) findViewById(R.id.etxtAsientos);

        rvList=(RecyclerView) findViewById(R.id.rcv);

        rvList = (RecyclerView) findViewById(R.id.rcv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(GestionarCoches.this);
        rvList.setLayoutManager(manager);

        //Cuando el usuario pulsa el BOTON OK
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((!asientos.getText().toString().equals("")) && (!modelo.getText().toString().equals(""))
                        && (!color.getText().toString().equals(""))) {


                    CCoche c = new CCoche();
                    c.setNombre(modelo.getText().toString());
                    c.setColor(color.getText().toString());
                    c.setAsientos(Integer.parseInt(asientos.getText().toString()));
                    if(isModificando){ //Modificando
                        c.setId(listCoches.get(sPosModificando).getId());
                    }else{ //Insertando
                        c.setId(findIDCocche());
                    }

                    guardarBD(c);


                } else {
                    Toast.makeText(GestionarCoches.this, getResources().getString(R.string.rellanaBien), Toast.LENGTH_LONG).show();
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




        if(!adapter.abrirDB()) finish();


//        CCoche c = new CCoche(4,"rojo","ferrari");
//        CCoche c1 = new CCoche(4,"rojo","ferrari");
//        CCoche c2 = new CCoche(4,"rojo","ferrari");
//        ArrayList<CCoche> list = new ArrayList();
//        list.add(c);
//        list.add(c1);
//        list.add(c2);


        listCoches = adapter.getAllCoches();
        listConductores=adapter.getAllConductores();
        //listConductores = adapter.getAllAsociaciones(listConductores,listCoches);

//        listCoches = list;


        if(listConductores==null){
            listConductores = new ArrayList<>();
        }

        if(listCoches==null){
            listCoches = new ArrayList<>();
        }

        //this.encontrarConductoresAsociados();

        inicializarRecycler();





    }


    private void inicializarRecycler(){
        //list= new ArrayList<>();
        CustomAdapterCoches adaptador = new CustomAdapterCoches(GestionarCoches.this, listCoches);
        rvList.setAdapter(adaptador);

    }

    private void actualizarListado(){

        if( null != rvList.getAdapter() ){

            ((CustomAdapterCoches) rvList.getAdapter()).notifyDataSetChanged();
        }



    }

    private void limpiarCajas(){

        isModificando =false;
        sPosModificando=0;
        modelo.setText("");
        color.setText("");
        asientos.setText("");
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

    public void guardarBD(CCoche c){


        if(null != c){

            long result=-1;
            if(isModificando){ // Estamos modificando un elemento
                result = adapter.updateCoche(c);

            }else{  // Estoy insertando

                result = adapter.insertCoches(c); //Devuelve el id si se introduce correctamente

            }

            //Comprobamos si se ha introducido correctamente en la BBDD
            if (result > 0) { //Dato introducido correctamente en la BBDD

                if(!isModificando){ //Insertando
                    listCoches.add(c); // Insertamos en la lista
                }else{ //Modificando
                    listCoches.get(sPosModificando).setNombre(c.getNombre());
                    listCoches.get(sPosModificando).setColor(c.getColor());
                    listCoches.get(sPosModificando).setAsientos(c.getAsientos());
                    listCoches.get(sPosModificando).setId(c.getId());

                }

                Toast.makeText(GestionarCoches.this, "Insertado correctamente", Toast.LENGTH_LONG).show();
                actualizarListado(); // Mostramos por la pantalla


            } else {
                Toast.makeText(GestionarCoches.this, "No se inserto correctamente", Toast.LENGTH_LONG).show();

            }

            limpiarCajas();


        }


    }

    public void encontrarConductoresAsociados(){

       // ArrayList<CConductor> condAsociados = new ArrayList<>();

        for(int k=0;k<listCoches.size();k++) {
            for (int i = 0; i < listConductores.size(); i++) {
                for (int j = 0; j < listConductores.get(i).getListaCochesAsignados().size(); j++) {

                    if(listCoches.get(k).getId()== listConductores.get(i).getListaCochesAsignados().get(j).getId() ){
                        CConductor c =listConductores.get(i);

                        listCoches.get(k).addConductorAsociado(c);
                    }

                }

            }
        }
        //return null;
    }


}
