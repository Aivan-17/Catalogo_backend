package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;


import com.example.demo.models.ProductoModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService  productoService;

    public ProductoController(com.example.demo.services.ProductoService productoService) {

    }

    @GetMapping()
    public ArrayList<ProductoModel> obtenerProducto(){
        return productoService.obtenerProducto();
    }

    @PostMapping()
    public ProductoModel guardarProducto(@RequestBody ProductoModel producto) throws IOException {
        System.out.println(producto);

        return productoService.guardarProducto(producto);

    }

    @GetMapping( path = "/{id}")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable("id") Long id) {
        return productoService.obtenerPorId(id);
    }
    @GetMapping( path = "/marca")
    public ArrayList<ProductoModel> obtenerProductoPorMarca(@RequestParam("marca") String marca) {
        return productoService.obtenerPorMarca(marca);
    }

    @GetMapping( path = "/categoria")
    public ArrayList<ProductoModel> obtenerProductoPorCategoria(@RequestParam("categoria") String categoria) {
        return productoService.obtenerPorCategoria(categoria);
    }
    @GetMapping( path = "/nombre")
    public ArrayList<ProductoModel> obtenerProductoPorNombre(@RequestParam("nombre") String nombre) {
        return productoService.obtenerPorNombre(nombre);
    }

  //  @GetMapping("/query")
 //   public ArrayList<ProductoModel> obtenerProductoPorIdUsuario(@RequestParam("id_usuario") Integer id_usuario){
  //      return this.ProductoService.obtenerPorIdUsuario(id_usuario);
 //   }



    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.productoService.eliminarProducto(id);
        if (ok){
            return "Se eliminó el producto con id " + id;
        }else{
            return "No pudo eliminar el producto con id" + id;
        }
    }

    @PutMapping(path = "/{id}")
    public ProductoModel editarPorId(@RequestBody ProductoModel productoModel, @PathVariable("id") Long id  ){
        return  productoService.editarProducto(productoModel, id );

    }


}
