package com.example.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String nombre;

    @Column (length = 255)
    private String descripcion;

    private String imagen;

    @Column (nullable = false)
    private boolean activo = true;

    @Column (nullable = false)
    private Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());

    @OneToMany(mappedBy = "categoria")
    private Set<Product> productos = new HashSet<>();

    public Categoria() {
    }

    public Categoria(Long id, String nombre, String descripcion, String imagen, boolean activo, Timestamp fechaCreacion, Set<Product> productos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Product> getProductos() {
        return productos;
    }

    public void setProductos(Set<Product> productos) {
        this.productos = productos;
    }
}
