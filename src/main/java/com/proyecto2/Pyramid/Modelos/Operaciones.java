/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto2.Pyramid.Modelos;

import java.util.List;

/**
 *
 * @author emili
 */
public class Operaciones {

    int num, item;
    String carta;
    String figura;
    String insert;

    public String getCarta() {
        return carta;
    }

    public void setCarta(String Carta) {
        this.carta = carta;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFigura() {
        return figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public String recibirPost(List<String> result, Arbol tree) {

        if (tree.verificarDuplicado(tree.raiz, carta) == true) {
            return "Status Code 406";
        } else {
            for (int i = 0; i < result.size(); i++) {
                verificarInsertar(result.get(i), tree);
                System.out.println("Carta: " + result.get(i));
            }
            return "Cartas ingresadas";
        }

    }

    public String recibirInsert(String insert, Arbol tree) {
        if (tree.verificarDuplicado(tree.raiz, insert) == true) {
            System.out.println("statues code 400");
            return "Status Code 406";
        } else {
            verificarInsertar(insert, tree);
            System.out.println("no debio entrar aqui loco");
            return "Carta ingresada";
        }
    }

    public void verificarInsertar(String carta, Arbol tree) {

        /*
         * Este metodo tiene como funcion:
         * Primero tenemos que ver si existe la carta , tenemos que extraer numero o
         * letra y simbolo , agregar el verdadero valor segun el simbolo
         * Trebol 0
         * Diamante 20
         * Corazón 40
         * Pica 60
         * por ultimo se hara la insecion al arbol
         */

        for (int i = 0; i < carta.length(); i++) {
            // ver si es un numero

            // System.out.println("char" +carta.charAt(i));
            if (Character.toString(carta.charAt(i)).matches("[0-9?]")) {

                if (carta.contains("10")) {
                    num = 10;
                } else {

                    num = Character.getNumericValue(carta.charAt(i));
                }

            }
            //
            if (Character.toString(carta.charAt(i)).matches("[a-z?]")
                    || Character.toString(carta.charAt(i)).matches("[A-Z?]")) {
                if (carta.charAt(i) == 'A' || carta.charAt(i) == 'a') {
                    num = 1;

                }
                if (carta.charAt(i) == 'J' || carta.charAt(i) == 'j') {
                    num = 11;
                }
                if (carta.charAt(i) == 'Q' || carta.charAt(i) == 'q') {
                    num = 12;
                }
                if (carta.charAt(i) == 'K' || carta.charAt(i) == 'k') {
                    num = 13;
                }

            }

        }
        // guardar figuras y numero backend de la carta
        if (carta.contains("♣")) {
            figura = "♣";
            item = num;
        }
        if (carta.contains("♦")) {
            figura = "♦";
            item = num + 20;
        }
        if (carta.contains("♥")) {
            figura = "♥";
            item = num + 40;
        }
        if (carta.contains("♠")) {
            figura = "♠";
            item = num + 60;
        }
        this.carta = carta;

        System.out.println("-----------------------");
        System.out.println("numero: " + num);
        System.out.println("figura: " + figura);
        System.out.println("item: " + item);

        tree.raiz = tree.insertarNodo(tree.raiz, item, num, carta);
    }
}
