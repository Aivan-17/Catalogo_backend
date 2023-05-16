package com.example.demo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;


import com.example.demo.models.ProductoModel;


import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoService {

    ProductoRepository ProductoRepository;

    UsuarioRepository usuarioRepository;

    @Autowired
    public ProductoService(com.example.demo.repositories.ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {
        ProductoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ArrayList<ProductoModel> obtenerProducto(){
        return (ArrayList<ProductoModel>) ProductoRepository.findByStatus(1);

    }

    public ProductoModel guardarProducto(ProductoModel producto) {
        producto.setIdUsuario(usuarioRepository.findById(producto.getIdUsuario().getId()).get());
        producto.setStatus(1);
        producto.setVistas(0);
       // producto.setImagen(imagen);







        return ProductoRepository.save(producto);


    }
    public Optional<ProductoModel> obtenerPorId(Long id){
        return ProductoRepository.findById(id);
    }
    public ArrayList<ProductoModel>  obtenerPorMarca(String marca) {
        return ProductoRepository.findByMarca(marca);
    }

    public ArrayList<ProductoModel> obtenerPorNombre(String nombre) {
        return ProductoRepository.findByNombre(nombre);
    }


     public ArrayList<ProductoModel> obtenerPorCategoria(String categoria){return ProductoRepository.findByCategoria(categoria);}



    public ArrayList<ProductoModel>  obtenerPorIdUsuario(UsuarioModel usuarioModel) {

        return ProductoRepository.findByIdUsuarioAndStatus(usuarioModel, 1);
    }

    public boolean eliminarProducto(Long idProducto) {
        ProductoModel productoModelo=ProductoRepository.findById(idProducto).get();
        productoModelo.setStatus(0);
        ProductoRepository.save(productoModelo);
        return true;

    }

    public ProductoModel editarProducto(ProductoModel newProductoModel,Long idProducto) {
        ProductoModel productoModel = new ProductoModel();
        productoModel = ProductoRepository.findById(idProducto).get();
        productoModel.setCategoria(newProductoModel.getCategoria());
        productoModel.setMarca(newProductoModel.getMarca());
        productoModel.setDescripcion(newProductoModel.getDescripcion());
        productoModel.setNombre(newProductoModel.getNombre());

        return ProductoRepository.save(productoModel);
    }
public ProductoModel saveVista(ProductoModel newProductoModel, Long idProducto ){

    ProductoModel productoModel = new ProductoModel();
    productoModel = ProductoRepository.findById(idProducto).get();
int x=newProductoModel.getVistas()+1;
    productoModel.setVistas(x);
    return ProductoRepository.save(productoModel);
    }








}
