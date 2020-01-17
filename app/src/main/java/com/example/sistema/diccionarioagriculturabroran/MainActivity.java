package com.example.sistema.diccionarioagriculturabroran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    float start = 0;
    int upperBound = 260;
    int lowerBound = 201;

    String nombre = "broranso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = 201;

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e)
        {

        }

        //Asigna la detección deslizamiento izquierdo y derecho
        ImageView img = (ImageView) findViewById(R.id.imageView);

        img.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if(event.getAction() == MotionEvent.ACTION_DOWN) //Cuando empieza el toque
                {
                    start = event.getX();
                }
                else
                    if(event.getAction() == MotionEvent.ACTION_UP) //Cuando termina el toque
                    {
                        float end = event.getX();

                        if(end - start < 0)
                        {
                            rightTap(v);
                        }
                        else
                        {
                            leftTap(v);
                        }
                    }

                return true;
            }
        }

        );
    }

    public void actualizarImagen(ImageView imageView)
    {
        String nombre_imagen = nombre + index;
        int id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());

        imageView.setImageResource(id_imagen);
    }

    public void rightTap(View img)
    {
        ImageView imageView = (ImageView) img;

        if (index < upperBound) {
            index++;
            actualizarImagen(imageView);

        }
    }

    public void leftTap(View img)
    {
        ImageView imageView = (ImageView) img;

        if (index > lowerBound) {
            index--;
            actualizarImagen(imageView);

        }
    }
}
