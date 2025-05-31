package com.ms.product.ms_product.controllers;

import com.ms.product.ms_product.models.Producto;
import com.ms.product.ms_product.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    /**
     * @return Obtiene todos los productos.
     */
    @GetMapping("/api/productos")
    public ResponseEntity<?> getProductos() {
        try {
            return ResponseEntity.ok(productoService.getProductos());
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Error al obtener productos");
        }
    }

    @GetMapping("/api/productos/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable("id") Long id) {
        try {
            Producto producto = productoService.getProductoById(id);
            if (producto != null) {
                return ResponseEntity.ok(producto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Error al obtener producto con ID " + id);
        }
    }

    @PostMapping("/api/productos")
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.createProducto(producto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevoProducto);
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear producto");
        }
    }

    @PutMapping("/api/productos/{id}")
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto, @PathVariable("id")Long id) {
        try {
            Producto updatedProducto = productoService.updateProducto(producto, id);
            if (updatedProducto != null) {
                return ResponseEntity.ok(updatedProducto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar producto");
        }
    }

    @DeleteMapping("/api/productos/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable("id") Long id) {
        try {
            productoService.softDeleteProducto(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ie) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar producto con ID " + id);
        }
    }
}
