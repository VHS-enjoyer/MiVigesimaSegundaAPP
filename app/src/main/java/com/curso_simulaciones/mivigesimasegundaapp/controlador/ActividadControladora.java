package com.curso_simulaciones.mivigesimasegundaapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.ViewGroup;

import com.curso_simulaciones.mivigesimasegundaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimasegundaapp.vista.CR;
import com.curso_simulaciones.mivigesimasegundaapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Cuerda;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Masa;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Polea;

/**
 * Actividad controladora que gestiona la escena de simulación física
 * Sistema de poleas con tres masas
 */
public class ActividadControladora extends Activity {

    private Pizarra pizarra;
    private final ObjetoLaboratorio[] objetos = new ObjetoLaboratorio[30];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionarResolucion();
        crearElementosGUI();

        ViewGroup.LayoutParams parametro_layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        this.setContentView(crearGUI(), parametro_layout_principal);
    }

    /**
     * Gestiona la resolución de pantalla
     */
    private void gestionarResolucion() {
        CR.anchoPizarra = AlmacenDatosRAM.ancho_pantalla;
        CR.altoPizarra = AlmacenDatosRAM.alto_pantalla;
    }

    /**
     * Crea los elementos de la GUI
     */
    private void crearElementosGUI() {
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.WHITE);
        crearObjetosLaboratorio();
    }

    /**
     * Crea el layout principal
     */
    private LinearLayout crearGUI() {
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.addView(pizarra);
        return linear_principal;
    }

    /**
     * Crea todos los objetos de laboratorio para el sistema de tres masas
     */
    private void crearObjetosLaboratorio() {

        // Radios de las poleas
        float radio_polea_azul = CR.pcApxL(4.5f);
        float radio_polea_p = CR.pcApxL(3.5f);

        // Dimensiones de las masas (rectángulos negros delgados)
        float ancho_masa = CR.pcApxL(2.2f);
        float alto_masa = CR.pcApxL(8.0f);

        // Dimensiones de la base (piso negro)
        float ancho_base = CR.pcApxL(70);
        float alto_base = CR.pcApxL(10);
        float x_base = CR.pcApxX(50);
        float y_base = CR.pcApxY(87);

        // Dimensiones de la plataforma (bloque amarillo)
        float ancho_plataforma = CR.pcApxL(36);
        float y_top_plataforma = CR.pcApxY(22);
        float y_bottom_plataforma = y_base - alto_base / 2;
        float alto_plataforma = y_bottom_plataforma - y_top_plataforma;

        float x_plataforma = CR.pcApxX(50);
        float y_plataforma = y_top_plataforma + alto_plataforma / 2;

        // ===== CÁLCULO DE VÉRTICES Y DESPLAZAMIENTO DE POLEAS AZULES =====
        float x_vertice_izq = x_plataforma - ancho_plataforma / 2;
        float x_vertice_der = x_plataforma + ancho_plataforma / 2;
        float y_vertice = y_top_plataforma;

        // Ángulo de inclinación del soporte respecto a la horizontal (~20 grados)
        float angulo_soporte = -60f;
        float angulo_rad = (float) Math.toRadians(angulo_soporte);

        // La longitud del soporte equivale al radio de la polea
        float longitud_soporte = radio_polea_azul;

        // Desplazamiento para llevar el centro de la polea arriba y afuera
        float dx = longitud_soporte * (float) Math.cos(angulo_rad);
        float dy = longitud_soporte * (float) Math.sin(angulo_rad);

        // Centros ajustados de las poleas azules
        float x_polea_azul_1 = x_vertice_izq - 3*dx; // Desplazado a la izquierda
        float y_polea_azul_1 = y_vertice + dy;     // Desplazado hacia arriba

        float x_polea_azul_2 = x_vertice_der + 3*dx; // Desplazado a la derecha
        float y_polea_azul_2 = y_vertice + dy;     // Desplazado hacia arriba

        // Posición de la polea verde P (alineada verticalmente con el borde izquierdo de la polea azul 1)
        float x_polea_p = x_polea_azul_1 - radio_polea_azul;
        float y_polea_p = CR.pcApxY(45);

        // Posiciones de las masas
        float x_m1 = x_polea_p - radio_polea_p;
        float y_m1 = y_polea_p + CR.pcApxL(12f); // Masa m1 más alta

        float x_m2 = x_polea_p + radio_polea_p;
        float y_m2 = y_polea_p + CR.pcApxL(20f); // Masa m2 más baja

        float x_m3 = x_polea_azul_2 + radio_polea_azul;
        float y_m3 = y_polea_azul_2 + CR.pcApxL(25f); // Masa m3 a la derecha

        int indice = 0;

        // ===== BASE (PISO NEGRO) =====
        CuerpoRectangular base = new CuerpoRectangular(x_base, y_base, ancho_base, alto_base);
        base.setColor(Color.BLACK);
        objetos[indice++] = base;

        // ===== PLATAFORMA (BLOQUE AMARILLO) =====
        CuerpoRectangular plataforma = new CuerpoRectangular(x_plataforma, y_plataforma,
                ancho_plataforma, alto_plataforma);
        plataforma.setColor(Color.YELLOW);
        objetos[indice++] = plataforma;

        // ===== POLEAS =====
        Polea polea_azul_1 = new Polea(x_polea_azul_1, y_polea_azul_1, radio_polea_azul);
        polea_azul_1.setColor(Color.BLUE);
        polea_azul_1.setGrosorLinea(CR.pcApxL(0.5f));
        polea_azul_1.setSoportePolea(true);
        polea_azul_1.rotarEje(angulo_soporte); // Apunta la barra directamente al vértice izquierdo
        objetos[indice++] = polea_azul_1;

        Polea polea_azul_2 = new Polea(x_polea_azul_2, y_polea_azul_2, radio_polea_azul);
        polea_azul_2.setColor(Color.BLUE);
        polea_azul_2.setGrosorLinea(CR.pcApxL(0.5f));
        polea_azul_2.setSoportePolea(true);
        polea_azul_2.rotarEje(-angulo_soporte); // Apunta la barra directamente al vértice derecho
        objetos[indice++] = polea_azul_2;

        Polea polea_verde = new Polea(x_polea_p, y_polea_p, radio_polea_p);
        polea_verde.setColor(Color.GREEN);
        polea_verde.setGrosorLinea(CR.pcApxL(0.5f));
        polea_verde.setSoportePolea(true);
        objetos[indice++] = polea_verde;

        // ===== CUERDAS (ROJAS) =====
        float grosor_cuerda = CR.pcApxL(0.4f);

        // 1. Cuerda horizontal superior entre la tangente superior de ambas poleas azules
        Cuerda cuerda_horizontal = new Cuerda(x_polea_azul_1, y_polea_azul_1 - radio_polea_azul,
                x_polea_azul_2, y_polea_azul_2 - radio_polea_azul);
        cuerda_horizontal.setColor(Color.RED);
        cuerda_horizontal.setGrosorLinea(grosor_cuerda);
        objetos[indice++] = cuerda_horizontal;

        // 2. Cuerda vertical que conecta la tangente izquierda de la polea azul 1 con la polea P
        Cuerda cuerda_p_azul = new Cuerda(x_polea_p, y_polea_azul_1,
                x_polea_p, y_polea_p - radio_polea_p);
        cuerda_p_azul.setColor(Color.RED);
        cuerda_p_azul.setGrosorLinea(grosor_cuerda);
        objetos[indice++] = cuerda_p_azul;

        // 3. Cuerda de m1 a la polea P
        Cuerda cuerda_m1 = new Cuerda(x_m1, y_polea_p, x_m1, y_m1 - alto_masa / 2);
        cuerda_m1.setColor(Color.RED);
        cuerda_m1.setGrosorLinea(grosor_cuerda);
        objetos[indice++] = cuerda_m1;

        // 4. Cuerda de m2 a la polea P
        Cuerda cuerda_m2 = new Cuerda(x_m2, y_polea_p, x_m2, y_m2 - alto_masa / 2);
        cuerda_m2.setColor(Color.RED);
        cuerda_m2.setGrosorLinea(grosor_cuerda);
        objetos[indice++] = cuerda_m2;

        // 5. Cuerda de m3 desde la tangente derecha de la polea azul 2
        Cuerda cuerda_m3 = new Cuerda(x_m3, y_polea_azul_2, x_m3, y_m3 - alto_masa / 2);
        cuerda_m3.setColor(Color.RED);
        cuerda_m3.setGrosorLinea(grosor_cuerda);
        objetos[indice++] = cuerda_m3;

        // ===== MASAS (BLOQUES NEGROS) =====
        Masa masa_1 = new Masa(x_m1, y_m1, ancho_masa, alto_masa);
        masa_1.setColor(Color.BLACK);
        objetos[indice++] = masa_1;

        Masa masa_2 = new Masa(x_m2, y_m2, ancho_masa, alto_masa);
        masa_2.setColor(Color.BLACK);
        objetos[indice++] = masa_2;

        Masa masa_3 = new Masa(x_m3, y_m3, ancho_masa, alto_masa);
        masa_3.setColor(Color.BLACK);
        objetos[indice++] = masa_3;

        // ===== ETIQUETAS (TEXTOS) =====
        float tamano_texto = CR.pcApxL(2.8f);

        // Marca "P"
        Marca marca_p = new Marca("P", x_polea_p - CR.pcApxL(4.0f), y_polea_p - radio_polea_p - CR.pcApxL(0.5f));
        marca_p.setColor(Color.BLACK);
        marca_p.setTamano(tamano_texto);
        objetos[indice++] = marca_p;

        // Marca "m1"
        Marca marca_m1 = new Marca("m1", x_m1 - CR.pcApxL(7f), y_m1);
        marca_m1.setColor(Color.BLACK);
        marca_m1.setTamano(tamano_texto);
        objetos[indice++] = marca_m1;

        // Marca "m2"
        Marca marca_m2 = new Marca("m2", x_m2 - CR.pcApxL(7f), y_m2);
        marca_m2.setColor(Color.BLACK);
        marca_m2.setTamano(tamano_texto);
        objetos[indice++] = marca_m2;

        // Marca "m3"
        Marca marca_m3 = new Marca("m3", x_m3 - CR.pcApxL(7f), y_m3);
        marca_m3.setColor(Color.BLACK);
        marca_m3.setTamano(tamano_texto);
        objetos[indice++] = marca_m3;

        // Desplegar la escena en la pizarra
        pizarra.setEstadoEscena(objetos);
    }
}