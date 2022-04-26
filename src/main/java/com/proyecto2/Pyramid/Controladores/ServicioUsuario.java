/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto2.Pyramid.Controladores;

import com.proyecto2.Pyramid.Modelos.Arbol;
import com.proyecto2.Pyramid.Modelos.Operaciones;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
   List<String> result = new ArrayList<>();
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

      for (int i = 0; i < result.size(); i++) {
         nom = nom + result.get(i);
      }
      operaciones.recibirPost(result, tree);
      tree.imprimir(tree.raiz, "", true);
      return result.get(0);
   }

   @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
   public String getDataInsert(@RequestBody Operaciones data) {

      String respuesta = operaciones.recibirInsert(data.getInsert(), tree);

      return respuesta;
   }

}
