package com.proyecto2.Pyramid.Modelos;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Niveles {

    String carta;
    int nivel;

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
    /**
     * Estos hasmap devuelven el request de los metodos
     */
    HashMap<Integer, String> mapa = new HashMap<Integer, String>();

    public HashMap<Integer, String> recibirNivel(int nivel, Arbol tree) {
        return tree.mandarJsonNiveles(nivel);
    }
    
    public void niveles(int nivel, Arbol tree)
    {
        
    }

    public HashMap<Integer, String> recibirOrden(String orden, Arbol tree) {
        System.out.println("entro a recibir");
        clasificarOrdenes(orden, tree);
        return mapa;
    }

    public void clasificarOrdenes(String orden, Arbol tree) {
        System.out.println("entro a clasificar");
        if (orden.equals("preOrden")) {
            mapa = tree.preOrden();
            System.out.println("1");
        }
        if (orden.equals("inOrden")) {
            mapa = tree.inOrden();
            System.out.println("2");
        }
        if (orden.equals("postOrden")) {
            mapa = tree.postOrden();
            System.out.println("3");
        }
    }

    public void graficarArbol(Arbol tree) {
        tree.eliminarfichero();
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Niveles.class.getName()).log(Level.SEVERE, null, ex);
        }
        tree.graficar("tree.jpg");
    }

    public HashMap<String, String> graficarJson(Arbol tree) {
        HashMap<String, String> mapa = new HashMap<String, String>();
        mapa.put("path", "https://6ed9-2803-d100-e000-f79-9d67-d19-4fe8-958.ngrok.io/Game/tree");
        return mapa;

    }
}
