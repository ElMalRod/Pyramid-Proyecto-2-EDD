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
    String delete_1, delete_2;

    public String getCarta() {
        return carta;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getDelete_1() {
        return delete_1;
    }

    public void setDelete_1(String delete_1) {
        this.delete_1 = delete_1;
    }

    public String getDelete_2() {
        return delete_2;
    }

    public void setDelete_2(String delete_2) {
        this.delete_2 = delete_2;
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

        for (int i = 0; i < result.size(); ++i) {
            obtenerValores(result.get(i));
            if (tree.existe(this.item) == false) {
                tree.raiz = tree.insertarNodo(tree.raiz, item, num, carta);
            } else {
                return "Status Code 406";
            }

        }

        return "Cartas ingresadas";

    }

    public void ingresarDelete(String d1, String d2, Arbol tree) {
        /// ver si existe
        System.out.println("delete 1");
        int valorD1, itemD1; // auxiliares para carta 1
        int valorD2, itemD2; // auxilares para carta2
        obtenerValores(d1);
        valorD1 = this.num;
        itemD1 = this.item;
        System.out.println("D1 " + itemD1);
        obtenerValores(d2);
        valorD2 = this.num;
        itemD2 = this.item;
        System.out.println("D2 " + itemD2);
        int suma = valorD1 + valorD2;
        if (suma == 13) {
            System.out.println("1. las cartas suman 13");
            if ((tree.existe(itemD1) == true) && (tree.existe(itemD2) == true)) {
                Nodo encontrado1 = tree.encontrarNodo(itemD1);
                Nodo encontrado2 = tree.encontrarNodo(itemD2);
                System.out.println("2. si existen los nodos");
                if (encontrado1.derecha == null && encontrado1.izquierda == null) {
                    if (encontrado2.derecha == null && encontrado2.izquierda == null) {
                        System.out.println("3. si estan en el ulimo nivel");

                        System.out.println("4. eliminado " + encontrado1.item + " " +
                                encontrado2.item);
                        tree.raiz = tree.eliminarNodo(tree.raiz, encontrado1.item);
                        tree.raiz = tree.eliminarNodo(tree.raiz, encontrado2.item);

                    }
                }
            }

        }

        /// si suman 13
        /// si estan en el ultimo nivel

        // tree.verificarEliminar(d1, d2, tree);
    }

    public void ingresarDelete(String delete, Arbol tree) {
        obtenerValores(delete);
        System.out.println("delete 2");
        if (this.num == 13) {
            System.out.println("1. las cartas suman 13");
            if ((tree.existe(this.item) == true)) {
                Nodo encontrado1 = tree.encontrarNodo(this.item);
                System.out.println("2. si existen ");
                if (encontrado1.derecha == null && encontrado1.izquierda == null) {

                    System.out.println("3. si estan en el ulimo nivel");

                    System.out.println("4. eliminado " + encontrado1.item);
                    tree.raiz = tree.eliminarNodo(tree.raiz, encontrado1.item);

                }
            }
        }
    }

    public String recibirInsert(String insert, Arbol tree) {
        obtenerValores(insert);
        if (tree.existe(this.item) == false) {
            tree.raiz = tree.insertarNodo(tree.raiz, item, num, carta);
            return "Carta ingresada";
        } else {
            return "Status Code 406";
        }

    }

    public void obtenerValores(String carta) {

        for (int i = 0; i < carta.length(); i++) {
            // ver si es un numero

            // System.out.println("char" +carta.charAt(i));
            if (Character.toString(carta.charAt(i)).matches("[0-9?]")) {

                if (carta.contains("10")) {
                    this.num = 10;
                } else {

                    this.num = Character.getNumericValue(carta.charAt(i));
                }

            }
            //
            if (Character.toString(carta.charAt(i)).matches("[a-z?]")
                    || Character.toString(carta.charAt(i)).matches("[A-Z?]")) {
                if (carta.charAt(i) == 'A' || carta.charAt(i) == 'a') {
                    this.num = 1;

                }
                if (carta.charAt(i) == 'J' || carta.charAt(i) == 'j') {
                    this.num = 11;
                }
                if (carta.charAt(i) == 'Q' || carta.charAt(i) == 'q') {
                    this.num = 12;
                }
                if (carta.charAt(i) == 'K' || carta.charAt(i) == 'k') {
                    this.num = 13;
                }

            }

        }
        // guardar figuras y numero backend de la carta
        if (carta.contains("♣")) {
            this.figura = "♣";
            this.item = num;
        }
        if (carta.contains("♦")) {
            this.figura = "♦";
            this.item = num + 20;
        }
        if (carta.contains("♥")) {
            this.figura = "♥";
            this.item = num + 40;
        }
        if (carta.contains("♠")) {
            this.figura = "♠";
            this.item = num + 60;
        }
    }
 
}
