package com.ms.product.ms_product.controllers;

import com.ms.product.ms_product.models.Categoria;
import com.ms.product.ms_product.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    /**
     * Obtiene todas las categorías.
     * @return ResponseEntity con la lista de categorías o un mensaje de error.
     */
    @GetMapping("/api/categorias")
    public ResponseEntity<?> getCategorias() {
        try {
            return ResponseEntity.ok(categoriaService.getCategorias());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener categorías: " + e.getMessage());
        }
    }

    /**
     * Obtiene una categoría por su ID.
     * @param id
     * @return ResponseEntity con la categoría o un mensaje de error.
     */
    @GetMapping("/api/categorias/{id}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.getCategoriaById(id);
            if (categoria != null) {
                return ResponseEntity.ok(categoria);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener categoría con ID " + id + ": " + e.getMessage());
        }
    }

    /**
     * Crea una nueva categoría.
     * @param categoria
     * @return ResponseEntity con la categoría creada o un mensaje de error.
     */
    @PostMapping("/api/categorias")
    public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria) {
        try {
            Categoria nuevaCategoria = categoriaService.createCategoria(categoria);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevaCategoria);
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrio un error al intentar crear nueva Categoria");
        }
    }

    /**
     * Actualiza una categoría existente.
     * @param categoria
     * @param id
     * @return ResponseEntity con la categoría actualizada o un mensaje de error.
     */
    @PutMapping("/api/categorias/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody Categoria categoria, @PathVariable("id") Long id) {
        try {
            Categoria updatedCategoria = categoriaService.updateCategoria(categoria, id);
            if (updatedCategoria != null) {
                return ResponseEntity.ok(updatedCategoria);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar categoría con ID " + id);
        }
    }

    /**
     * Elimina una categoría de forma lógica (soft delete).
     * @param id
     * @return ResponseEntity con el resultado de la operación o un mensaje de error.
     */
    @DeleteMapping("/api/categorias/{id}")
    public ResponseEntity<?> softDeleteCategoria(@PathVariable Long id){
        try {
            categoriaService.softDeleteCategoria(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ie) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ie.getMessage());
        }catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar categoría con ID " + id + ": " + e.getMessage());
        }
    }
}
