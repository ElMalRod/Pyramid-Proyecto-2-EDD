/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto2.Pyramid.Modelos;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author emili
 */
public class Nodo {

    int item, altura, valor;// item guarda el valor completo de la carta para el backend , valor guarda el
                            // valor superficial para el frontend
    Nodo izquierda;
    Nodo derecha;
    String carta;// es el valor de la carta con su simbolo

    Nodo(int d, int valor, String carta) {
        this.item = d;
        this.altura = 1;
        this.valor = valor;
        this.carta = carta;

    }

    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("aux_grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo aux_grafico.dot");
            }
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o " + path + " aux_grafico.dot");
            // Esperamos medio segundo para dar tiempo a que la imagen se genere.
            // Para que no sucedan errores en caso de que se decidan graficar varios
            // árboles sucesivamente.
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
        }
    }

    /**
     * Método que retorna el código que grapviz usará para generar la imagen del
     * árbol binario de búsqueda.
     *
     * @return
     */
    private String getCodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape =record, style=filled, fillcolor=gray100];\n"
                + getCodigoInterno()
                + "}\n";
    }

    /**
     * Genera el código interior de graphviz, este método tiene la
     * particularidad de ser recursivo, esto porque recorrer un árbol de forma
     * recursiva es bastante sencillo y reduce el código considerablemente.
     *
     * @return
     */
    private String getCodigoInterno() {
        
                String etiqueta;
       
        if (izquierda == null && derecha == null) {
            // treboles
            if (item >= 0 && item <= 20 || item >= 60 && item <= 73) {
                etiqueta = "nodo" + valor + " [ label =\"" + valor + "\",fontcolor=black];\n";
            }else {etiqueta = "nodo" + valor + " [ label =\"" + valor + "\",fontcolor=red];\n";}

        } else {
            if (item >= 0 && item <= 20 || item >= 60 && item <= 73) {
                etiqueta = "nodo" + valor + " [ label =\"" + valor + "\",fontcolor=black];\n";
            }else { etiqueta = "nodo" + valor + " [ label =\"" + valor + "\",fontcolor=red];\n";}
           
        }
        if (izquierda != null) {
            etiqueta = etiqueta + izquierda.getCodigoInterno()
                    + "nodo" + valor + ":C0->nodo" + izquierda.valor + "\n";
        }
        if (izquierda != null) {
            etiqueta = etiqueta + derecha.getCodigoInterno()
                    + "nodo" + valor + ":C1->nodo" + derecha.valor + "\n";
        }
        return etiqueta;
    }
}
