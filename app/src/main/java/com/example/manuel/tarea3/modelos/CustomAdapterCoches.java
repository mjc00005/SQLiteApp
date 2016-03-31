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
public class CustomAdapterCoches extends RecyclerView.Adapter<CustomAdapterCoches.ElementosLinea>{

    private ArrayList<CCoche> aclistado;
    GestionarCoches ctx;

    public CustomAdapterCoches(GestionarCoches cdx, ArrayList<CCoche> coches) {
        this.aclistado=coches;
        this.ctx=cdx;
    }

    @Override
    /* Encargado de crear los nuevos objetos ViewHolder necesarios para los elementos de la colección. */
    public ElementosLinea onCreateViewHolder(ViewGroup parent, int i) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_coches,parent,false);

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
            //holder.txtAsiento.setText((String.valueOf(coche.getAsientos())));


            //Cuando le demos al boton de eliminar nos elimine el elemento de la BBDD y  de la lista
            holder.btEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialogo = new AlertDialog.Builder(ctx).create();
                    dialogo.setTitle("AVISO");
                    dialogo.setMessage("Quiere eliminar el coche?");
                    dialogo.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialogo.setButton(DialogInterface.BUTTON_POSITIVE, "SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            CCoche c = aclistado.get(i);
//                            BdAdapter adap = new BdAdapter(ctx);
//                            int result = adap.deleteCocheById(c);

                            int result = ctx.adapter.deleteCocheById(c);

                            if(result>0){
                                aclistado.remove(i);
                                notifyDataSetChanged();

                            }else{
                                Toast.makeText(ctx, "No se elimino correctamente", Toast.LENGTH_LONG).show();
                            }

                            //Eliminamos las asociaciones asociadas a este conductor
                            result = ctx.adapter.deleteAsoByCoche(c);
                            if(result<0){
                                Toast.makeText(ctx, "No se elimino correctamente las asociaciones", Toast.LENGTH_LONG).show();
                            }


                            dialog.cancel();
                        }
                    });

                    dialogo.show();
                }
            });


            //Cuando pulsamos el boton de modificar
            holder.btModificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ctx.isModificando =true;
                    ctx.sPosModificando=i;
                    CCoche coche = aclistado.get(i);

                    ctx.modelo.setText(coche.getNombre());
                    ctx.color.setText(coche.getColor());
                    ctx.asientos.setText(String.valueOf(coche.getAsientos()));

                    //ctx.guardarBD(aclistado.get(i));



                }
            });

            //Cuando le damos al boton de asignar
            holder.btAsignar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CCoche coche = aclistado.get(i);
                    DialogAltaConductor dialogo = new DialogAltaConductor(ctx,coche,ctx);
                    dialogo.show();
                }
            });


            //Cuando le damos la boton de ver
            holder.btVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CCoche coche = aclistado.get(i);
                    DialogConductoresAsociados diaAsociados = new DialogConductoresAsociados(ctx, coche, ctx);
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

        TextView txtNombre, txtColor, txtAsiento;
        Button btEliminar,btAsignar, btVer, btModificar;
        LinearLayout lnyPadre;

        public ElementosLinea(View itemView) {
            super(itemView);

            txtNombre= (TextView) itemView.findViewById(R.id.txtModelo);
            txtColor= (TextView) itemView.findViewById(R.id.txtcolor);
            //txtAsiento= (TextView) itemView.findViewById(R.id.txtAsiento);
            //lnyPadre= (LinearLayout) itemView.findViewById(R.id.lyPadre);

            btEliminar = (Button) itemView.findViewById(R.id.btnEliminar);
            btVer = (Button) itemView.findViewById(R.id.btnVer);
            btModificar = (Button) itemView.findViewById(R.id.btnModificar);
            btAsignar = (Button) itemView.findViewById(R.id.btnAsignar);
        }
    }
}
