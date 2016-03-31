package com.example.manuel.tarea3;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manuel.tarea3.modelos.CCoche;
import com.example.manuel.tarea3.modelos.CConductor;

/**
 * Created by Manuel on 26/01/2016.
 */
public class DialogAltaCoche extends Dialog implements View.OnClickListener {

    CConductor condu;
    GestionarConductores gC;
    Button btAceptar, btCancelar;
    EditText etxModelo, etxdColor, etxAsientos;

    public DialogAltaCoche(Context context, CConductor c, GestionarConductores gC) {
        super(context);
        this.condu=c;
        this.gC=gC;

        setTitle("Asignar Conductor");
        setContentView(R.layout.jdialog_formulario_coche);

        btAceptar = (Button) findViewById(R.id.btmAcep);
        btCancelar = (Button) findViewById(R.id.btnCance);

        etxModelo = (EditText) findViewById(R.id.etxtModeloj);
        etxdColor = (EditText) findViewById(R.id.etxtColorj);
        etxAsientos = (EditText) findViewById(R.id.etxtAsientosj);

        btAceptar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btmAcep:

                if ((!etxAsientos.getText().toString().equals("")) && (!etxModelo.getText().toString().equals(""))
                        && (!etxdColor.getText().toString().equals(""))) {


                    //Capturamos lo que el usuario ha introducido en la pantalla
                    CCoche c = new CCoche();
                    c.setNombre(etxModelo.getText().toString());
                    c.setColor(etxdColor.getText().toString());
                    c.setAsientos(Integer.parseInt(etxAsientos.getText().toString()));
                    c.setId(gC.findIDCocche());

                    //Aqui asignamos un conductor a un coche
                    //condu.setId_coche();
                    condu.addCocheAsociado(c);


                    long result = gC.adapter.insertCoches(c);

                    if(result>0){
                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.inserOk), Toast.LENGTH_LONG).show();
                        gC.listCoches.add(c);
                    }else{
                        Toast.makeText(getContext(),getContext().getResources().getString(R.string.inserMal), Toast.LENGTH_LONG).show();
                    }


                    result = gC.adapter.insertAsociacion(condu.getId(), c.getId());

                    if(result>0){

                        cancel();
                    }else{
                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.inserMal), Toast.LENGTH_LONG).show();
                    }



                } else {
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.rellanaBien), Toast.LENGTH_LONG).show();
                }

                    break;
            case R.id.btnCance:
                cancel();
                break;
            default:
                cancel();
                break;

        }
    }
}
