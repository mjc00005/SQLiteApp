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

import com.example.manuel.tarea3.DialogAltaConductor;
import com.example.manuel.tarea3.DialogConductoresAsociados;
import com.example.manuel.tarea3.GestionarCoches;
import com.example.manuel.tarea3.GestionarConductores;
import com.example.manuel.tarea3.R;

import java.util.ArrayList;

/**
 * Created by Manuel on 01/11/2015.
 */
public class CustomAdapterCochesAsociados extends RecyclerView.Adapter<CustomAdapterCochesAsociados.ElementosLinea>{

    private ArrayList<CCoche> aclistado;
    GestionarConductores ctx;

    public CustomAdapterCochesAsociados(GestionarConductores cdx, ArrayList<CCoche> coches) {
        this.aclistado=coches;
        this.ctx=cdx;
    }

    @Override
    /* Encargado de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección. */
    public ElementosLinea onCreateViewHolder(ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_coches_asignados,parent,false);

        ElementosLinea vh = new ElementosLinea(v);

        return vh;
    }

    @Override
    /* Encargado de actualizar los datos de un ViewHolder ya existente. */
    public void onBindViewHolder(final ElementosLinea holder, final int i) {

        final CCoche coche = aclistado.get(i);

        if(null!=coche){

            holder.txtNombre.setText(String.valueOf(coche.getNombre()));
            holder.txtColor.setText(String.valueOf(coche.getColor()));
            holder.txtAsiento.setText((String.valueOf(coche.getAsientos())));


        }

    }

    @Override
    /* Indica el número de elementos de la colección de datos. */
    public int getItemCount() {
        return aclistado.size();
    }






    // Esta es la clase ViewHolder
    public class ElementosLinea extends RecyclerView.ViewHolder{

        TextView txtNombre, txtColor, txtAsiento;
        Button btEliminar,btAsignar, btVer, btModificar;
        LinearLayout lnyPadre;

        public ElementosLinea(View itemView) {
            super(itemView);

            txtNombre= (TextView) itemView.findViewById(R.id.txtModeloAsig);
            txtColor= (TextView) itemView.findViewById(R.id.txtColorAsig);
            txtAsiento= (TextView) itemView.findViewById(R.id.txtAsientosAsig);

        }
    }
}
