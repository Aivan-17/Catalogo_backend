package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;


import com.example.demo.models.ValoracionModel;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.UsuarioRepository;

import com.example.demo.repositories.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValoracionService {

    ValoracionRepository valoracionRepository;
    UsuarioRepository usuarioRepository;

    ProductoRepository productoRepository;
    @Autowired
    public ValoracionService(ValoracionRepository valoracionRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.valoracionRepository = valoracionRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    public ArrayList<ValoracionModel> obtenerValoracion(){

        return (ArrayList<ValoracionModel>) valoracionRepository.findByStatus(1);
    }

    public ValoracionModel guardarValoracion(ValoracionModel valoracion){

        valoracion.setIdUsuario(usuarioRepository.findById(valoracion.getIdUsuario().getId()).get());
        valoracion.setIdProducto(productoRepository.findById(valoracion.getIdProducto().getIdProducto()).get());
        valoracion.setStatus(1);

        return valoracionRepository.save(valoracion);
    }
    public Optional<ValoracionModel> obtenerPorId(Long id){
        return valoracionRepository.findById(id);
    }


    public ArrayList<ValoracionModel>  obtenerPorIdProducto(Integer idProducto) {


        return valoracionRepository.findByIdProducto(idProducto);
    }



    public boolean eliminarValoracion(Long id) {
        ValoracionModel valoracionModelo=valoracionRepository.findById(id).get();
        valoracionModelo.setStatus(0);
        valoracionRepository.save(valoracionModelo);
        return true;

    }


    public ValoracionModel editarValoracion(ValoracionModel valoracionModel) {

       /* EstadiaModel estadiaModelo=estadiaRepository.findById(idEstadia).get();
        estadiaModelo.setCosto(estadiaModel.getCosto());
        estadiaModelo.setIngreso(estadiaModel.getIngreso());
        estadiaModelo.setSalida(estadiaModel.getSalida());
        estadiaModelo.setIdUsuario(estadiaModel.getIdUsuario());
        estadiaModelo.setIdVehiculo(estadiaModel.getIdVehiculo());

        estadiaModelo.setEspacio(estadiaModel.getEspacio());

        estadiaModelo = estadiaRepository.save(estadiaModelo);
        estadiaModelo.setIdEstadia(idEstadia);*/

        return valoracionRepository.save(valoracionModel);

    }







}
