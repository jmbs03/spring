package com.example.repository;

import com.example.entity.Categoria;
import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoriaRepository extends JpaRepository<Product, Long> {
    Optional<Categoria> findByTitle (String title);
    List<Categoria> findAllByCategoria_ImagenNotContaining (String imagen);
    List<Categoria> findAllByActive (boolean active);
    List<Categoria> findAllByCategoria_DescripcionContaining (String descripcion);




}
