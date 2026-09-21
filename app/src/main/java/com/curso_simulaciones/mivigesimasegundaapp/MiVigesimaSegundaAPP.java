package com.curso_simulaciones.mivigesimasegundaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.curso_simulaciones.mivigesimasegundaapp.controlador.ActividadControladora;
import com.curso_simulaciones.mivigesimasegundaapp.datos.AlmacenDatosRAM;

/**
 * Actividad principal que gestiona la resolución de pantalla
 * e inicia la actividad controladora
 */
public class MiVigesimaSegundaAPP extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionarResolucion();
        lanzarActividadControladora();
    }

    /**
     * Obtiene las dimensiones de pantalla y las guarda en RAM
     */
    private void gestionarResolucion() {
        DisplayMetrics displayMetrics =
                this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;

        AlmacenDatosRAM.ancho_pantalla = ancho;
        AlmacenDatosRAM.alto_pantalla = alto;
    }

    /**
     * Lanza la actividad controladora
     */
    private void lanzarActividadControladora() {
        Intent intent = new Intent(this, ActividadControladora.class);
        startActivity(intent);
    }
}