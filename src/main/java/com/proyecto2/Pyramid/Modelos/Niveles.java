package com.proyecto2.Pyramid.Modelos;

import java.util.HashMap;

public class Niveles {
    String carta;
    /**Estos hasmap devuelven el request de los metodos  */
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
    public HashMap<String, String> graficarJson(Arbol tree)
    {
        HashMap<String, String> mapa = new HashMap<String, String>();
        mapa.put("url","https://5663-2803-d100-e000-f79-1406-d160-70e9-7c0c.ngrok.io/Game/tree");
        return mapa;

    }
}
