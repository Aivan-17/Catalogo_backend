package com.example.demo.repositories;

import java.util.ArrayList;

import com.example.demo.models.ProductoModel;


import com.example.demo.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long> {
    public abstract ProductoModel findByIdProducto(Long idProducto);
    public abstract ArrayList<ProductoModel> findByMarca(String marca);
    public abstract ArrayList<ProductoModel> findByNombre(String Nombre);
    public abstract ArrayList<ProductoModel> findByCategoria(String Categoria);
    public abstract ArrayList<ProductoModel> findByStatus(Integer status);
   public abstract ArrayList<ProductoModel> findByIdUsuarioAndStatus(UsuarioModel usuarioModel,Integer status);

}
