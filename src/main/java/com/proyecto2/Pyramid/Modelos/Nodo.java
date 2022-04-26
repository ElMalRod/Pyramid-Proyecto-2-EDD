/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto2.Pyramid.Modelos;

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
}
