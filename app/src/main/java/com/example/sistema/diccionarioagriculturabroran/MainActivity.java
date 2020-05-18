package com.example.sistema.diccionarioagriculturabroran;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    int index = 0; //Indica la página actual
    float start = 0; //Almacena la posición inicial del toque de la app
    int upperBound = 104; //Indica el final de página
    int lowerBound = 1; //Indica la el inicio de página

    String nombre = "alimentacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = lowerBound;

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e)
        {

        }

        //Asigna la detección deslizamiento izquierdo y derecho
        ImageView img = (ImageView) findViewById(R.id.imageView);

        //Permite desplazar la página según la dirección del deslizamiento del dedo
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
                        rightSlide(v);
                    }
                    else
                    {
                        leftSlide(v);
                    }
                }

                return true;
            }
        });
    }

    /**
     * Actualiza la imagen desplegada dependiendo del indice actual, el cual es global
     * @param imageView la rerefencia al imageView que despliega la imagen
     */
    public void actualizarImagen(ImageView imageView)
    {
        String nombre_imagen = nombre + index;
        int id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());

        imageView.setImageResource(id_imagen);
    }

    /**
     * Avanza a la próxima página
     * @param img la referencia al imageView que despliega la imagen
     */
    public void rightSlide(View img)
    {
        ImageView imageView = (ImageView) img;

        if (index < upperBound) {
            index++;
            actualizarImagen(imageView);

        }
    }

    /**
     * Retrocede a la página anterior
     * @param img referencia al imageView
     */
    public void leftSlide(View img)
    {
        ImageView imageView = (ImageView) img;

        if (index > lowerBound) {
            index--;
            actualizarImagen(imageView);

        }
    }

    /**
     * Cambia la imagen desplegada de acuerdo a la opción seleccionada en el menú
     * @param newIndex
     */
    public void irOpcion(int newIndex)
    {
        ImageView img = (ImageView) findViewById(R.id.imageView);
        index = newIndex;
        actualizarImagen(img);
    }

    /**
     * Despliega un menú con todas las opciones disponibles de navegación
     * @param v
     */
    public void displayMenuList(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Indice");


        String[] categorias = {"Portada", "DŔÍ", "DŔÍ SHAŔIEI SÓŔË ÓBRË ÓBRË", "Québin̈ éga ibín̈",
                "é̈b", "C'uofrurún", "Íc", "Zhóc", "Có", "Shúb", "Créditos"
        };

        int c = R.drawable.ic_launcher_background;

        builder.setItems(categorias, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:  irOpcion(1);    break;//Portada
                    case 1:  irOpcion(8);    break;//Alimentos
                    case 2:  irOpcion(21);    break;//Utensilios
                    case 3:  irOpcion(39);    break;//Cocina
                    case 4:  irOpcion(51);    break;//Maiz
                    case 5:  irOpcion(75);    break;//Arroz
                    case 6:  irOpcion(84);    break;//Yuca
                    case 7:  irOpcion(90);    break;//Palma
                    case 8:  irOpcion(93);    break;//¿?
                    case 9:  irOpcion(100);    break;//Pejibaye
                    case 10: irOpcion(104);    break;//Créditos

                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
