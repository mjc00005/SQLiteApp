package com.example.manuel.tarea3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tCoches = new Intent(MainActivity.this, GestionarCoches.class);
                startActivity(tCoches);
            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tConductores = new Intent(MainActivity.this, GestionarConductores.class);
                startActivity(tConductores);

            }
        });







    }
}
