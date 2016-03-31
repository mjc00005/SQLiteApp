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

import com.example.manuel.tarea3.DialogAltaCoche;
import com.example.manuel.tarea3.DialogAltaConductor;
import com.example.manuel.tarea3.DialogCochesAsociados;
import com.example.manuel.tarea3.GestionarConductores;
import com.example.manuel.tarea3.R;

import java.util.ArrayList;

/**
 * Created by Manuel on 01/11/2015.
 */
public class CustomAdapterConductores extends RecyclerView.Adapter<CustomAdapterConductores.ElementosLinea>{

    private ArrayList<CConductor> aclistado;
    GestionarConductores ctx;

    public CustomAdapterConductores(GestionarConductores cdx, ArrayList<CConductor> conductores) {
        this.aclistado=conductores;
        this.ctx=cdx;
    }

    @Override
    /* Encargado de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección. */
    public ElementosLinea onCreateViewHolder(ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_conductores,parent,false);

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


            //Cuando le demos al boton de eliminar nos elimine el elemento de la BBDD y  de la lista
            holder.btEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialogo = new AlertDialog.Builder(ctx).create();
                    dialogo.setTitle("AVISO");
                    dialogo.setMessage("Quiere eliminar el conductor?");
                    dialogo.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialogo.setButton(DialogInterface.BUTTON_POSITIVE, "SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            CConductor c = aclistado.get(i);

                            int result = ctx.adapter.deleteConductorById(c);

                            if(result>0){
                                aclistado.remove(i);
                                notifyDataSetChanged();

                            }else{
                                Toast.makeText(ctx, "No se elimino correctamente", Toast.LENGTH_LONG).show();
                            }

                            //Eliminamos las asociaciones asociadas a este conductor
                            result = ctx.adapter.deleteAsoByCondu(c);
                            if(result<0){
                                Toast.makeText(ctx, "No se elimino correctamente las asociaciones", Toast.LENGTH_LONG).show();
                            }


                            dialog.cancel();
                        }
                    });

                    dialogo.show();
                }
            });


            holder.btModificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ctx.isModificando =true;
                    ctx.sPosModificando=i;

                    CConductor condu = aclistado.get(i);
                    ctx.nombre.setText(condu.getNombre());
                    ctx.dni.setText(condu.getDni());
                    ctx.email.setText(condu.getEmail());
                    ctx.dire.setText(condu.getDireccion());
                    ctx.telefono.setText(String.valueOf(condu.getTelefono()));


                    //ctx.guardarDB(aclistado.get(i));


                }
            });

            holder.btAsignar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CConductor condu = aclistado.get(i);
                    DialogAltaCoche dialogo = new DialogAltaCoche(ctx,condu,ctx);
                    dialogo.show();
                }
            });

            holder.btVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CConductor condu = aclistado.get(i);
                    DialogCochesAsociados diaAsociados = new DialogCochesAsociados(ctx, condu, ctx);
                    diaAsociados.show();
                }
            });


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
            txtNombre= (TextView) itemView.findViewById(R.id.txtNombre);


            btEliminar = (Button) itemView.findViewById(R.id.btnEliminar);
            btVer = (Button) itemView.findViewById(R.id.btnVer);
            btModificar = (Button) itemView.findViewById(R.id.btnModificar);
            btAsignar = (Button) itemView.findViewById(R.id.btnAsignar);
        }
    }
}
