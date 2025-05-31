package com.ms.product.ms_product.services.impl;

import com.ms.product.ms_product.models.Categoria;
import com.ms.product.ms_product.repository.CategoriaRepository;
import com.ms.product.ms_product.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Categoria::getId))
                .toList();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Categoria updateCategoria, Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    if(updateCategoria.getNombre() != null){
                        categoria.setNombre(updateCategoria.getNombre());
                    }
                    if(updateCategoria.getDescripcion() != null){
                        categoria.setDescripcion(updateCategoria.getDescripcion());
                    }

                    categoriaRepository.save(categoria);
                    return categoria;
                }).orElse(null);
    }

    public void softDeleteCategoria(Long id) {
        Categoria categoria = this.getCategoriaById(id);
        if(categoria == null){
            throw new IllegalArgumentException("La categoría con ID " + id + " no existe");
        }

        categoria.setDeletedAt(new Date());
        categoriaRepository.save(categoria);
    }



}
