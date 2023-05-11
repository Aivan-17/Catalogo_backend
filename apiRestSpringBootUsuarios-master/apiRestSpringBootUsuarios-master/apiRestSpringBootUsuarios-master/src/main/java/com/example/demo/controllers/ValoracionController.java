package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;


import com.example.demo.models.ProductoModel;
import com.example.demo.models.ValoracionModel;
import com.example.demo.services.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/valoracion")
public class ValoracionController {
    @Autowired
    ValoracionService valoracionService;


    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    @GetMapping()
    public ArrayList<ValoracionModel> obtenerValoracion(){
        return valoracionService.obtenerValoracion();
    }

    @PostMapping()
    public ValoracionModel guardarValoracion(@RequestBody ValoracionModel valoracion){
        System.out.println(valoracion);
        return this.valoracionService.guardarValoracion(valoracion);

    }

    @GetMapping( path = "/{id}")
    public Optional<ValoracionModel> obtenerValoracionPorId(@PathVariable("id") Long id) {
        return this.valoracionService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<ValoracionModel> obtenerValoracionPorIdProducto(@RequestParam("idProducto")ProductoModel idProducto){
        return this.valoracionService.obtenerPorIdProducto(idProducto.getIdProducto());
    }



    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.valoracionService.eliminarValoracion(id);
        if (ok){
            return "Se eliminó el productocon id " + id;
        }else{
            return "No pudo eliminar el producto con id" + id;
        }
    }



    @PutMapping()
    public ValoracionModel editarPorId(@RequestBody ValoracionModel valoracionModel ){
        return  valoracionService.editarValoracion(valoracionModel);

    }





}
