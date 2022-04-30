/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto2.Pyramid.Modelos;

import java.util.HashMap;

/**
 *
 * @author emili
 */
public class Arbol {

  public Nodo raiz;
  HashMap<Integer, String> mapa = new HashMap<Integer, String>();
  int key = 0;

  int alturAvl(Nodo N) {
    if (N == null) {
      return 0;
    }
    return N.altura;
  }

  int max(int a, int b) {
    return (a > b) ? a : b;
  }

  Nodo rotarD(Nodo y) {
    Nodo x = y.izquierda;
    Nodo T2 = x.derecha;
    x.derecha = y;
    y.izquierda = T2;
    y.altura = max(alturAvl(y.izquierda), alturAvl(y.derecha)) + 1;
    x.altura = max(alturAvl(x.izquierda), alturAvl(x.derecha)) + 1;
    return x;
  }

  Nodo rotarI(Nodo x) {
    Nodo y = x.derecha;
    Nodo T2 = y.izquierda;
    y.izquierda = x;
    x.derecha = T2;
    x.altura = max(alturAvl(x.izquierda), alturAvl(x.derecha)) + 1;
    y.altura = max(alturAvl(y.izquierda), alturAvl(y.derecha)) + 1;
    return y;
  }

  // Get balance factor of a node
  int getFactorEquilibrio(Nodo N) {
    if (N == null) {
      return 0;
    }
    return alturAvl(N.izquierda) - alturAvl(N.derecha);
  }

  // Insert a
  // node--------------------------------------------------------------------------
  Nodo insertarNodo(Nodo node, int item, int valor, String carta) {

    // Find the position and insert the node
    if (node == null) {
      return (new Nodo(item, valor, carta));
    }
    if (item < node.item) {
      node.izquierda = insertarNodo(node.izquierda, item, valor, carta);
    } else if (item > node.item) {
      node.derecha = insertarNodo(node.derecha, item, valor, carta);
    } else {
      return node;
    }

    // Update the balance factor of each node
    // And, balance the tree
    node.altura = 1 + max(alturAvl(node.izquierda), alturAvl(node.derecha));
    int balanceFactor = getFactorEquilibrio(node);
    if (balanceFactor > 1) {
      if (item < node.izquierda.item) {
        return rotarD(node);
      } else if (item > node.izquierda.item) {
        node.izquierda = rotarI(node.izquierda);
        return rotarD(node);
      }
    }
    if (balanceFactor < -1) {
      if (item > node.derecha.item) {
        return rotarI(node);
      } else if (item < node.derecha.item) {
        node.derecha = rotarD(node.derecha);
        return rotarI(node);
      }
    }

    return node;

  }

  Nodo nodoMinimo(Nodo node) {
    Nodo current = node;
    while (current.izquierda != null) {
      current = current.izquierda;
    }
    return current;
  }

  // Delete a node
  Nodo eliminarNodo(Nodo raiz, int item) {

    // Find the node to be deleted and remove it
    if (raiz == null) {
      return raiz;
    }
    if (item < raiz.item) {
      raiz.izquierda = eliminarNodo(raiz.izquierda, item);
    } else if (item > raiz.item) {
      raiz.derecha = eliminarNodo(raiz.derecha, item);
    } else {
      if ((raiz.izquierda == null) || (raiz.derecha == null)) {
        Nodo temp = null;
        if (temp == raiz.izquierda) {
          temp = raiz.derecha;
        } else {
          temp = raiz.izquierda;
        }
        if (temp == null) {
          temp = raiz;
          raiz = null;
        } else {
          raiz = temp;
        }
      } else {
        Nodo temp = nodoMinimo(raiz.derecha);
        raiz.item = temp.item;
        raiz.derecha = eliminarNodo(raiz.derecha, temp.item);
      }
    }
    if (raiz == null) {
      return raiz;
    }

    // Update the balance factor of each node and balance the tree
    raiz.altura = max(alturAvl(raiz.izquierda), alturAvl(raiz.derecha)) + 1;
    int balanceFactor = getFactorEquilibrio(raiz);
    if (balanceFactor > 1) {
      if (getFactorEquilibrio(raiz.izquierda) >= 0) {
        return rotarD(raiz);
      } else {
        raiz.izquierda = rotarI(raiz.izquierda);
        return rotarD(raiz);
      }
    }
    if (balanceFactor < -1) {
      if (getFactorEquilibrio(raiz.derecha) <= 0) {
        return rotarI(raiz);
      } else {
        raiz.derecha = rotarD(raiz.derecha);
        return rotarI(raiz);
      }
    }
    return raiz;
  }

  public void preOrden(Nodo node) {
    if (node != null) {
      System.out.print(node.carta + " ");
      preOrden(node.izquierda);
      preOrden(node.derecha);
    }
  }

  void inOrden(Nodo node) {
    if (node != null) {
      System.out.print(node.item + " ");
      inOrden(node.izquierda);
      // imprime node.item
      inOrden(node.derecha);
    }
  }

  void postOrden(Nodo node) {
    if (node != null) {
      System.out.print(node.item + " ");
      postOrden(node.izquierda);
      postOrden(node.derecha);
      // imprime nodo.item
    }
  }

  // Print the tree
  public void imprimir(Nodo currPtr, String indent, boolean last) {
    if (currPtr != null) {
      System.out.print(indent);
      if (last) {
        System.out.print("R----");
        indent += "   ";
      } else {
        System.out.print("L----");
        indent += "|  ";
      }
      System.out.println(currPtr.carta + " " + currPtr.item);
      imprimir(currPtr.izquierda, indent, false);
      imprimir(currPtr.derecha, indent, true);
    }
  }

  // -------------------------------------
  public boolean existe(int busqueda) {
    return existe(this.raiz, busqueda);
  }

  private boolean existe(Nodo n, int busqueda) {
    if (n == null) {
      return false;
    }
    if (n.item == busqueda) {
      return true;
    } else if (busqueda < n.item) {
      return existe(n.izquierda, busqueda);
    } else {
      return existe(n.derecha, busqueda);
    }
  }

  // ENCONTRAR NODO
  public Nodo encontrarNodo(int busqueda) {
    return encontrarNodo(this.raiz, busqueda);
  }

  private Nodo encontrarNodo(Nodo n, int busqueda) {
    if (n == null) {
      return null;
    }
    if (n.item == busqueda) {
      return n; // devuelve el nodo encontrado
    } else if (busqueda < n.item) {
      return encontrarNodo(n.izquierda, busqueda);
    } else {
      return encontrarNodo(n.derecha, busqueda);
    }
  }

  int getLevelUtil(Nodo node, int data, int level) {
    if (node == null)
      return 0;

    if (node.item == data)
      return level;

    int downlevel = getLevelUtil(node.izquierda, data, level + 1);
    if (downlevel != 0)
      return downlevel;

    downlevel = getLevelUtil(node.derecha, data, level + 1);
    return downlevel;
  }

  /* Returns level of given data value */
  int getLevel(int data) {
    return getLevelUtil(this.raiz, data, 1);
  }
  // mandar carta hashmap

  private HashMap<Integer, String> encontrarCartaHashmap(Nodo node, int nivel) {

    if (node != null) {

      if (getLevel(node.item) == nivel) {
        mapa.put(key, node.carta);
        key++;
      }

      encontrarCartaHashmap(node.izquierda, nivel);
      encontrarCartaHashmap(node.derecha, nivel);

    }
    return mapa;

  }

  public HashMap<Integer, String> mandarJsonNiveles(int nivel) {
    mapa.clear();
    key = 0;
    // imprimirEntreConNivel();
    return encontrarCartaHashmap(this.raiz, nivel);
  }

}
