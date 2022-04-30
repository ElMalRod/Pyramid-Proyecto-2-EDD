package com.proyecto2.Pyramid.Modelos;
import java.util.HashMap;

public class Niveles {
    String carta;
    public HashMap<Integer, String> recibirNivel(int nivel, Arbol tree)
    {
           return tree.mandarJsonNiveles(nivel);
    }
}
