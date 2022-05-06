/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.proyecto2.Pyramid.Controladores;

import com.proyecto2.Pyramid.Modelos.Arbol;
import com.proyecto2.Pyramid.Modelos.Operaciones;
import com.proyecto2.Pyramid.Modelos.Niveles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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
   Niveles opNiveles = new Niveles();
   List<String> result = new ArrayList<>();
   HashMap<Integer, String> mapa = new HashMap<Integer, String>();;
   int nivel = 0;
   String nom = "carga correcta";

   @RequestMapping(value = "/start", method = RequestMethod.POST, consumes = "application/json")
   public ResponseEntity<String> getData(@RequestBody HashMap<String, String> data) {

      for (String val : data.values()) {
         result.add(val);
      }
      // return operaciones.recibirPost(result, tree);
      if (operaciones.recibirPost(result, tree) == true) {
         return new ResponseEntity<String>("Cartas ingresadas", HttpStatus.OK);
      } else {
         return new ResponseEntity<String>("No ingresado: cartas duplicadas", HttpStatus.NOT_ACCEPTABLE);
      }

   }

   @RequestMapping("/ver")
   public String imprimir() {

      tree.imprimir(tree.raiz, "", true);
      return result.get(0);
   }

   @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
   public ResponseEntity<String> getDataInsert(@RequestBody Operaciones data) {

      // String respuesta = operaciones.recibirInsert(data.getInsert(), tree);
      if (operaciones.recibirInsert(data.getInsert(), tree) == true) {
         return new ResponseEntity<String>("Cartas ingresadas", HttpStatus.OK);
      } else {
         return new ResponseEntity<String>("No ingresado: cartas duplicadas", HttpStatus.NOT_ACCEPTABLE);
      }

   }

   @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "application/json")
   public ResponseEntity<String> getDataDelete(@RequestBody Operaciones data) {

      if (data.getDelete_2() != null) {
         // operaciones.ingresarDelete(data.getDelete_1(), data.getDelete_2(), tree);
         if (operaciones.ingresarDelete(data.getDelete_1(), data.getDelete_2(), tree) == 4) {
            return new ResponseEntity<String>("Cartas eliminadas", HttpStatus.OK);}
         if (operaciones.ingresarDelete(data.getDelete_1(), data.getDelete_2(), tree) == 1) {
               return new ResponseEntity<String>("Las cartas no suman 13", HttpStatus.NOT_ACCEPTABLE);}
         if (operaciones.ingresarDelete(data.getDelete_1(), data.getDelete_2(), tree) == 2) {
                  return new ResponseEntity<String>("La carta no se encuentra en el arbol", HttpStatus.NOT_FOUND);}
         if (operaciones.ingresarDelete(data.getDelete_1(), data.getDelete_2(), tree) == 3) {
               return new ResponseEntity<String>("La carta no se puede eliminar porque tiene hijos.", HttpStatus.CONFLICT);}
         // } else {
         //    return new ResponseEntity<String>("No ingresado: cartas duplicadas", HttpStatus.NOT_ACCEPTABLE);
         // }
      } else {
         //if(operaciones.ingresarDelete(data.getDelete_1(), tree)==true)
         if (operaciones.ingresarDelete(data.getDelete_1(), tree) == 4) {
            return new ResponseEntity<String>("Cartas eliminadas", HttpStatus.OK);}
         if (operaciones.ingresarDelete(data.getDelete_1(), tree) == 1) {
               return new ResponseEntity<String>("Las cartas no suman 13", HttpStatus.NOT_ACCEPTABLE);}
         if (operaciones.ingresarDelete(data.getDelete_1(), tree) == 2) {
                  return new ResponseEntity<String>("La carta no se encuentra en el arbol", HttpStatus.NOT_FOUND);}
         if (operaciones.ingresarDelete(data.getDelete_1(), tree) == 3) {
               return new ResponseEntity<String>("La carta no se puede eliminar porque tiene hijos.", HttpStatus.CONFLICT);}
         
      }
      return new ResponseEntity<String>("Error al eliminar", HttpStatus.BAD_REQUEST);

      //return "respuesta";
   }

   // get-level?level=
   @RequestMapping(value = "/prueba={id}", method = RequestMethod.GET)
   public ResponseEntity <HashMap<Integer, String>> mandarNivel(@PathVariable("id") int id) {

      return new ResponseEntity<HashMap<Integer, String>> (opNiveles.recibirNivel(id, tree), HttpStatus.OK);
   }

   // avltree?transversal=postOrder
   @RequestMapping(value = "/={orden}", method = RequestMethod.GET)
   public ResponseEntity <HashMap<Integer, String>> mandarOrden(@PathVariable("orden") String orden) {

      //return opNiveles.recibirOrden(orden, tree);
      return new ResponseEntity<HashMap<Integer, String>> (opNiveles.recibirOrden(orden, tree), HttpStatus.OK);
   }

   // Game/status-avltree
   @RequestMapping(value = "/status", method = RequestMethod.GET)
   public  ResponseEntity <HashMap<String, String>> generarImagen() {

      opNiveles.graficarArbol(tree);
      //opNiveles.graficarJson(tree);
      return new ResponseEntity<HashMap<String, String>> (opNiveles.graficarJson(tree), HttpStatus.OK);
   }
   @RequestMapping(value = "/tree",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage() throws IOException {
    RandomAccessFile f = new RandomAccessFile("C:/Users/emili/OneDrive/Escritorio/PROYECTOS/EstructuraDeDatos/Pyramid EDD/Pyramid-Proyecto-2-EDD/tree.jpg", "r");
    byte[] b = new byte[(int)f.length()];
    f.readFully(b);
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);
    return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);

}


}
