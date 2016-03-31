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
public class DialogAltaConductor extends Dialog implements View.OnClickListener {

    CCoche coche;
    GestionarCoches gC;
    Button btAceptar, btCancelar;
    EditText etxnombre, etxdire, etxdni, etxemail, etxtelefono;

    public DialogAltaConductor(Context context, CCoche coche, GestionarCoches gC) {
        super(context);
        this.coche=coche;
        this.gC=gC;

        setTitle("Asignar Conductor");
        setContentView(R.layout.jdialog_formulario_conductor);

        btAceptar = (Button) findViewById(R.id.btmAcep);
        btCancelar = (Button) findViewById(R.id.btnCance);

        etxnombre = (EditText) findViewById(R.id.etxtNombrej);
        etxdire = (EditText) findViewById(R.id.etxtDirej);
        etxdni = (EditText) findViewById(R.id.etxtDnij);
        etxemail = (EditText) findViewById(R.id.etxtEmailj);
        etxtelefono = (EditText) findViewById(R.id.etxtTelj);

        btAceptar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btmAcep:

                if ((!etxnombre.getText().toString().equals("")) && (!etxdire.getText().toString().equals(""))
                        && (!etxdni.getText().toString().equals("")) && (!etxemail.getText().toString().equals("")) && (!etxtelefono.getText().toString().equals(""))) {



                    //Capturamos lo que el usuario ha introducido en la pantalla
                    CConductor condu = new CConductor();
                    condu.setNombre(etxnombre.getText().toString());
                    condu.setDireccion(etxdire.getText().toString());
                    condu.setDni(etxdni.getText().toString());
                    condu.setEmail(etxemail.getText().toString());
                    condu.setTelefono(Integer.parseInt(etxtelefono.getText().toString()));
                    condu.setId(gC.findIDConductor());

                    //Aqui asignamos un conductor a un coche
                    //condu.setId_coche();
                    coche.addConductorAsociado(condu);


                    long result = gC.adapter.insertConductor(condu);

                    if(result>0){
                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.inserOk), Toast.LENGTH_LONG).show();
                        gC.listConductores.add(condu);

                    }else{
                        Toast.makeText(getContext(), getContext().getResources().getString(R.string.inserMal), Toast.LENGTH_LONG).show();
                    }

                    result = gC.adapter.insertAsociacion(condu.getId(),coche.getId());
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
