package com.ms.product.ms_product.services.impl;

import com.ms.product.ms_product.models.Categoria;
import com.ms.product.ms_product.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    @Override
    public List<Categoria> getCategorias() {
        return List.of();
    }
    @Override
    public Categoria createCategoria(Categoria categoria) {
        return null;
    }

    public Categoria getCategoriaById(Long id) {
        return null;
    }

    public Categoria updateCategoria(Categoria categoria, Long id) {
        return null;
    }

    public void softDeleteCategoria(Long id) {
        // Lógica para eliminar una categoría de forma suave
    }



}
