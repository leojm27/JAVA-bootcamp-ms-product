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

    /**
     * Obtiene todas las categorías.
     * @return Lista de categorías
     */
    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Categoria::getId))
                .filter(categoria -> categoria.getDeletedAt() == null)
                .toList();
    }

    /**
     * Obtiene una categoría por su ID.
     * @param id
     * @return Categoria encontrada o null si no existe
     */
    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .filter(categoria -> categoria.getDeletedAt() == null)
                .orElse(null);
    }

    /**
     * Crea una nueva categoría.
     * @param categoria
     * @return Categoria creada
     */
    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /**
     * Actualiza una categoría existente.
     * @param updateCategoria
     * @param id
     * @return Categoria actualizada o null si no existe
     */
    @Override
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

    /**
     * Elimina una categoría de forma lógica (soft delete).
     * @param id
     */
    @Override
    public void softDeleteCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if(categoria == null){
            throw new IllegalArgumentException("La categoría con ID " + id + " no existe");
        }

        categoria.setDeletedAt(new Date());
        categoriaRepository.save(categoria);
    }

}
