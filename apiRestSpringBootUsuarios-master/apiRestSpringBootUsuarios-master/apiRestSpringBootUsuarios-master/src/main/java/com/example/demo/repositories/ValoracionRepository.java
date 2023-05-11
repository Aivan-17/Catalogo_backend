package com.example.demo.repositories;

import java.util.ArrayList;


import com.example.demo.models.UsuarioModel;
import com.example.demo.models.ValoracionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValoracionRepository extends CrudRepository<ValoracionModel, Long> {
    public abstract ArrayList<ValoracionModel> findByIdProducto(Long idProducto);
    public abstract ArrayList<ValoracionModel> findByStatus(Integer status);

}
