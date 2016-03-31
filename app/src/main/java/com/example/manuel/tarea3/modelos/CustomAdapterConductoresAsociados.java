package com.example.manuel.tarea3.modelos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manuel.tarea3.GestionarCoches;
import com.example.manuel.tarea3.GestionarConductores;
import com.example.manuel.tarea3.R;

import java.util.ArrayList;

/**
 * Created by Manuel on 01/11/2015.
 */
public class CustomAdapterConductoresAsociados extends RecyclerView.Adapter<CustomAdapterConductoresAsociados.ElementosLinea>{

    private ArrayList<CConductor> aclistado;
    GestionarCoches ctx;

    public CustomAdapterConductoresAsociados(GestionarCoches cdx, ArrayList<CConductor> conductores) {
        this.aclistado=conductores;
        this.ctx=cdx;
    }

    @Override
    /* Encargado de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección. */
    public ElementosLinea onCreateViewHolder(ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_conductores_asignados,parent,false);

        ElementosLinea vh = new ElementosLinea(v);

        return vh;
    }

    @Override
    /* Encargado de actualizar los datos de un ViewHolder ya existente. */
    public void onBindViewHolder(final ElementosLinea holder, final int i) {

        final CConductor condu = aclistado.get(i);

        if(null!=condu){

            holder.txtNombre.setText(String.valueOf(condu.getNombre()));
            //holder.txtDni.setText(String.valueOf(condu.getDni()));




        }

    }

    @Override
    /* Indica el número de elementos de la colección de datos. */
    public int getItemCount() {
        return aclistado.size();
    }






    // Esta es la clase ViewHolder
    public class ElementosLinea extends RecyclerView.ViewHolder{

        TextView txtNombre, txtDni;
        Button btEliminar,btAsignar, btVer, btModificar;
        LinearLayout lnyPadre;

        public ElementosLinea(View itemView) {
            super(itemView);

            //txtDni= (TextView) itemView.findViewById(R.id.txtDni);
            txtNombre= (TextView) itemView.findViewById(R.id.txtNombreConductor);
//
//
//            btEliminar = (Button) itemView.findViewById(R.id.btnEliminar);
//            btVer = (Button) itemView.findViewById(R.id.btnVer);
//            btModificar = (Button) itemView.findViewById(R.id.btnModificar);
//            btAsignar = (Button) itemView.findViewById(R.id.btnAsignar);
        }
    }
}
