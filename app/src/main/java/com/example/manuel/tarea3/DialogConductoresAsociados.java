package com.example.manuel.tarea3;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manuel.tarea3.modelos.CCoche;
import com.example.manuel.tarea3.modelos.CConductor;
import com.example.manuel.tarea3.modelos.CustomAdapterConductores;
import com.example.manuel.tarea3.modelos.CustomAdapterConductoresAsociados;

import java.util.ArrayList;

/**
 * Created by Manuel on 26/01/2016.
 */
public class DialogConductoresAsociados extends Dialog implements View.OnClickListener {

    CCoche coche;
    GestionarCoches gC;
    RecyclerView rcv;
    Button btCancelar;


    public DialogConductoresAsociados(Context context, CCoche c, GestionarCoches gC) {
        super(context);
        this.coche=c;
        this.gC=gC;

        setTitle("Conductores");
        setContentView(R.layout.jdialog_ver_conductores);

        btCancelar = (Button) findViewById(R.id.btnSalirLista);



        rcv = (RecyclerView) findViewById(R.id.rcvListaCond);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);



        verCochesAsociados(c);

        btCancelar.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCance:
                break;
            default:
                cancel();
                break;

        }
    }


    private void verCochesAsociados(CCoche c){

        //ArrayList<CConductor> list = gC.adapter.getAllConductoresbyIdCoche(coche.getId());

        //Modo de hacerlo en memoria
        //ArrayList<CConductor> list = c.getListaCondAsociados();

        //Accediendo a la BBDD
        ArrayList<Integer> listCods =gC.adapter.getAllConductoresAsociadosbyIdCoche(c.getId());
        ArrayList<CConductor> list=null;

        if(listCods!=null){
            list = extraerConmductores(listCods);
        }

        if(null != list){
            CustomAdapterConductoresAsociados adap = new CustomAdapterConductoresAsociados(gC,list);
            rcv.setAdapter(adap);


        }else{
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.noTieneAso), Toast.LENGTH_LONG).show();
            cancel();
        }
    }

    private ArrayList<CConductor> extraerConmductores(ArrayList<Integer> liscod){

        ArrayList<CConductor> list = null;

        for(int i=0; i<liscod.size(); i++) {

            for (int j = 0; j < gC.listConductores.size(); j++) {

                if (gC.listConductores.get(j).getId() == liscod.get(i)){
                    if(list==null){
                        list=new ArrayList<>();
                    }

                    list.add(gC.listConductores.get(j));
                }
            }
        }
        return list;

    }
}
