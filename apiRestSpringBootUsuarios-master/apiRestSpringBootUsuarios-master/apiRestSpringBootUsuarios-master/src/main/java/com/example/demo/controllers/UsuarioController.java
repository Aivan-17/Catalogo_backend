package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        System.out.println(usuario);
        return this.usuarioService.guardarUsuario(usuario);

    }

    @GetMapping( path = "/{idUsuario}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("idUsuario") Long idUsuario) {
        return this.usuarioService.obtenerPorId(idUsuario);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @GetMapping("/query2")
    public ArrayList<UsuarioModel> obtenerPorUsuario(@RequestParam("usuario") String usuario){
        return this.usuarioService.obtenerPorUsuario(usuario);
    }



    @DeleteMapping( path = "/{idUsuario}")
    public String eliminarPorId(@PathVariable("idUsuario") Long idUsuario){
        boolean ok = this.usuarioService.eliminarUsuario(idUsuario);
        if (ok){
            return "Se eliminó el usuario con id " + idUsuario;
        }else{
            return "No pudo eliminar el usuario con id" + idUsuario;
        }
    }

    @PutMapping()
    public UsuarioModel editarPorId(@RequestBody UsuarioModel usuarioModel ){
        return  usuarioService.editarUsuario(usuarioModel);

    }

}