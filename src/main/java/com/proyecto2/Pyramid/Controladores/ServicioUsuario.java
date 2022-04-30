/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto2.Pyramid.Controladores;

import com.proyecto2.Pyramid.Modelos.Arbol;
import com.proyecto2.Pyramid.Modelos.Operaciones;
import com.proyecto2.Pyramid.Modelos.Niveles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author emili
 * 
 */

@RestController
@RequestMapping(path = "/Game")
public class ServicioUsuario {
   Arbol tree = new Arbol();
   Operaciones operaciones = new Operaciones();
   Niveles opNiveles= new Niveles();
   List<String> result = new ArrayList<>();
   HashMap<Integer, String> mapa= new HashMap<Integer, String>(); ;
   int nivel=0;
   String nom = "carga correcta";

   @RequestMapping(value = "/start", method = RequestMethod.POST, consumes = "application/json")
   public String getData(@RequestBody HashMap<String, String> data) {

      for (String val : data.values()) {
         result.add(val);
      }
      return operaciones.recibirPost(result, tree);
   }

   @RequestMapping("/ver")
   public String imprimir() {

      tree.imprimir(tree.raiz, "", true);
      return result.get(0);
   }

   @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
   public String getDataInsert(@RequestBody Operaciones data) {

      String respuesta = operaciones.recibirInsert(data.getInsert(), tree);

      return respuesta;
   }

   @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "application/json")
   public String getDataDelete(@RequestBody Operaciones data) {

      if (data.getDelete_2() != null) {
         operaciones.ingresarDelete(data.getDelete_1(), data.getDelete_2(), tree);
      } else {
         operaciones.ingresarDelete(data.getDelete_1(), tree);
      }

      return "respuesta";
   }
                           //get-level?level=
   @RequestMapping(value = "/prueba={id}", method = RequestMethod.GET)
   public HashMap <Integer,String> jaja(@PathVariable("id") int id){
      
     
      return opNiveles.recibirNivel(id, tree);
   }

   public void probar()
   {
      mapa.put( 1, "fas");
      mapa.put( 2, "3♥");
      mapa.put( 3, "4♥");
      mapa.put( 4, "5♥");
   }
   public void probar2()
   {
      mapa.put(1, "5♥");
      mapa.put(2, "4♥");
      mapa.put(3, "4534♥");
      mapa.put(4, "5345♥");
   }
 
}
