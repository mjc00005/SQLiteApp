package com.example.manuel.tarea3;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.manuel.tarea3.modelos.CCoche;
import com.example.manuel.tarea3.modelos.CConductor;
import com.example.manuel.tarea3.modelos.CustomAdapterCochesAsociados;
import com.example.manuel.tarea3.modelos.CustomAdapterConductoresAsociados;

import java.util.ArrayList;

/**
 * Created by Manuel on 26/01/2016.
 */
public class DialogCochesAsociados extends Dialog implements View.OnClickListener {

    CConductor condu;
    GestionarConductores gC;
    RecyclerView rcv;
    Button btCancelar;


    public DialogCochesAsociados(Context context, CConductor c, GestionarConductores gC) {
        super(context);
        this.condu=c;
        this.gC=gC;

        setTitle("Coches");
        setContentView(R.layout.jdialog_ver_coches);

        btCancelar = (Button) findViewById(R.id.btnSalirListaCoches);



        rcv = (RecyclerView) findViewById(R.id.rcvListaCondCoches);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);

        btCancelar.setOnClickListener(this);

        verCochesAsociados(c);


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


    private void verCochesAsociados(CConductor c){

        //ArrayList<CConductor> list = gC.adapter.getAllConductoresbyIdCoche(coche.getId());

        //Modo de hacerlo en memoria
//        ArrayList<CCoche> list = c.getListaCochesAsignados();

        ArrayList<Integer> listCods =gC.adapter.getAllCochesAsociadosbyIdCoonductor(c.getId());
        ArrayList<CCoche> list=null;

        if(listCods!=null){
            list = extraerCoches(listCods);
        }


        if(null != list){
            CustomAdapterCochesAsociados adap = new CustomAdapterCochesAsociados(gC,list);
            rcv.setAdapter(adap);


        }else{
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.noTieneAso), Toast.LENGTH_LONG).show();
            cancel();
        }
    }



    private ArrayList<CCoche> extraerCoches(ArrayList<Integer> liscod){

        ArrayList<CCoche> list = null;

        for(int i=0; i<liscod.size(); i++) {

            for (int j = 0; j < gC.listCoches.size(); j++) {

                if (gC.listCoches.get(j).getId() == liscod.get(i)){
                    if(list==null){
                        list=new ArrayList<>();
                    }

                    list.add(gC.listCoches.get(j));
                }
            }
        }
        return list;

    }

}
