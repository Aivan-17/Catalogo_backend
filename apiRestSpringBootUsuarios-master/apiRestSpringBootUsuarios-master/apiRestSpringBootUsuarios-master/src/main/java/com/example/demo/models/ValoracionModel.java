package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "valoracion")
public class ValoracionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_valoracion",unique = true, nullable = false)
    private Long idValoracion;

    private Integer calificacion;
    private Integer status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private UsuarioModel idUsuario;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto")
    private ProductoModel idProducto;

    public Long getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(Long idValoracion) {
        this.idValoracion = idValoracion;
    }

    public Integer getCalifiacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UsuarioModel getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioModel idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ProductoModel getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(ProductoModel idProducto) {
        this.idProducto = idProducto;
    }


}
