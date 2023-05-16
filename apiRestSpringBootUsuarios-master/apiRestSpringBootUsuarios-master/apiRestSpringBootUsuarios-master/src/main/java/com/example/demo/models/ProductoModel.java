package com.example.demo.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "producto")

public class ProductoModel {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_producto",unique = true, nullable = false)
        private Long idProducto;

        private String nombre;
        private String descripcion;
        private String marca;
        private String categoria;

        private Integer status;
    private Integer vistas;
    //@Lob
    private String imagen;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private UsuarioModel idUsuario;

    public Integer getVistas() {
        return vistas;
    }

    public void setVistas(Integer vistas) {
        this.vistas = vistas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }





        public Long getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(Long idProducto) {
            this.idProducto = idProducto;
        }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getCategoria() {
        return categoria;
    }

        public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public UsuarioModel getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(UsuarioModel idUsuario) {
            this.idUsuario = idUsuario;
        }
    }




