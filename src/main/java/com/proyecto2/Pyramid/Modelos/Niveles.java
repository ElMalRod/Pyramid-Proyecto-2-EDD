package com.proyecto2.Pyramid.Modelos;

import java.util.HashMap;

public class Niveles {
    String carta;
    HashMap<Integer, String> mapa = new HashMap<Integer, String>();

    public HashMap<Integer, String> recibirNivel(int nivel, Arbol tree) {
        return tree.mandarJsonNiveles(nivel);
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
    public void graficarArbol(Arbol tree)
    {
        tree.graficar("tree.jpg");
    }
}
