package com.morales.ms_product.services;

import com.morales.ms_product.models.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> getCategorias();
    Categoria createCategoria(Categoria categoria);

    Categoria getCategoriaById(Long id);

    Categoria updateCategoria(Categoria categoria, Long id);

    void softDeleteCategoria(Long id);

}
