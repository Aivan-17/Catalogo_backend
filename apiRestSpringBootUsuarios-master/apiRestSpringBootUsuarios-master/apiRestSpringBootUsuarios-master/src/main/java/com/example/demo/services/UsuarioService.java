package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios(){

        return (ArrayList<UsuarioModel>) usuarioRepository.findByStatus(1);
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        usuario.setStatus(1);
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long idUsuario){
        return usuarioRepository.findById(idUsuario);
    }


    public ArrayList<UsuarioModel>  obtenerPorPrioridad(Integer prioridad) {
        return usuarioRepository.findByPrioridad(prioridad);
    }
    public ArrayList<UsuarioModel>  obtenerPorUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

    public boolean eliminarUsuario(Long idUsuario) {
        UsuarioModel usuarioModelo=usuarioRepository.findById(idUsuario).get();
           usuarioModelo.setStatus(0);
           usuarioRepository.save(usuarioModelo);
            return true;

    }


    public UsuarioModel editarUsuario(UsuarioModel usuarioModel) {

           /* UsuarioModel usuarioModelo=usuarioRepository.findById(idUsuario).get();
            usuarioModelo.setNombre(usuarioModel.getNombre());
            usuarioModelo.setUsuario(usuarioModel.getUsuario());
            usuarioModelo.setClave(usuarioModel.getClave());
            usuarioModelo.setPrioridad(usuarioModel.getPrioridad());


            usuarioModelo = usuarioRepository.save(usuarioModel);
            usuarioModelo.setIdUsuario(idUsuario);
*/
            return usuarioRepository.save(usuarioModel);

    }




}