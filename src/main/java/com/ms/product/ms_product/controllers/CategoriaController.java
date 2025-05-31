package com.ms.product.ms_product.controllers;

import com.ms.product.ms_product.models.Categoria;
import com.ms.product.ms_product.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

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
