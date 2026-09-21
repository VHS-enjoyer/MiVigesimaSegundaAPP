package com.curso_simulaciones.mivigesimasegundaapp.vista;

/**
 * Clase para convertir entre porcentajes y píxeles
 * Proporciona independencia de resolución de pantalla
 */
public class CR {
    public static float anchoPizarra;
    public static float altoPizarra;

    public CR() {
    }

    /**
     * Convierte porcentaje de posición en X a píxeles
     * @param pcX porcentaje
     * @return píxeles
     */
    public static float pcApxX(float pcX) {
        float pxX = pcX * anchoPizarra / 100f;
        return pxX;
    }

    /**
     * Convierte porcentaje de posición en Y a píxeles
     * @param pcY porcentaje
     * @return píxeles
     */
    public static float pcApxY(float pcY) {
        float pxY = pcY * altoPizarra / 100f;
        return pxY;
    }

    /**
     * Convierte porcentaje de longitud a píxeles
     * (referido al menor entre ancho y alto)
     * @param pcL porcentaje de longitud
     * @return píxeles
     */
    public static float pcApxL(float pcL) {
        float pxL = 0;
        if (anchoPizarra > altoPizarra) {
            pxL = pcL * altoPizarra / 100f;
        } else {
            pxL = pcL * anchoPizarra / 100f;
        }
        return pxL;
    }

    /**
     * Convierte píxeles de posición en X a porcentaje
     * @param pxX píxeles
     * @return porcentaje
     */
    public static float pxXApc(float pxX) {
        float pcX = pxX * 100f / anchoPizarra;
        return pcX;
    }

    /**
     * Convierte píxeles de posición en Y a porcentaje
     * @param pxY píxeles
     * @return porcentaje
     */
    public static float pxYApc(float pxY) {
        float pcY = pxY * 100f / altoPizarra;
        return pcY;
    }

    /**
     * Convierte píxeles de longitud a porcentaje
     * (referido al menor entre ancho y alto)
     * @param pxL píxeles
     * @return porcentaje
     */
    public static float pxApcL(float pxL) {
        float pcL = 0;
        if (anchoPizarra > altoPizarra) {
            pcL = pxL * 100f / altoPizarra;
        } else {
            pcL = pxL * 100f / anchoPizarra;
        }
        return pcL;
    }
}