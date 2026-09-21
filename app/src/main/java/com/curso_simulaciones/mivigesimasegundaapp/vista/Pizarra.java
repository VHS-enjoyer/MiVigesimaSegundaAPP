package com.curso_simulaciones.mivigesimasegundaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;

/**
 * Vista personalizada para dibujar objetos de laboratorio en el canvas
 */
public class Pizarra extends View {

    private ObjetoLaboratorio[] objetosLab;
    private Paint pincel;

    public Pizarra(Context context) {
        super(context);
        this.pincel = new Paint();
        this.pincel.setAntiAlias(true);
    }

    /**
     * Establece el estado de la escena con los objetos a dibujar
     * @param cuerpos arreglo de objetos de laboratorio
     */
    public void setEstadoEscena(ObjetoLaboratorio[] cuerpos) {
        this.objetosLab = cuerpos;
    }

    /**
     * Dibuja todos los objetos de laboratorio
     */
    private void dibujarEscena(Canvas canvas) {
        for (ObjetoLaboratorio objeto : objetosLab) {
            if (objeto != null) {
                objeto.dibujese(canvas, pincel);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        pincel.reset();
        pincel.setAntiAlias(true);

        if (objetosLab != null) {
            dibujarEscena(canvas);
        }

        invalidate();
    }
}