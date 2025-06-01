package com.morales.ms_product.controllers;

import com.morales.ms_product.models.Producto;
import com.morales.ms_product.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    /**
     * * Obtiene todos los productos.
     * @return ResponseEntity con la lista de productos o un mensaje de error.
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

    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto a buscar.
     * @return ResponseEntity con el producto encontrado o un mensaje de error.
     */
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

    /**
     * Crea un nuevo producto.
     * @param producto Producto a crear.
     * @return ResponseEntity con el producto creado o un mensaje de error.
     */
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

    /**
     * Actualiza un producto existente.
     * @param producto Producto con los datos actualizados.
     * @param id ID del producto a actualizar.
     * @return ResponseEntity con el producto actualizado o un mensaje de error.
     */
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

    /**
     * Elimina un producto de forma lógica.
     * @param id ID del producto a eliminar.
     * @return ResponseEntity con el resultado de la operación.
     */
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
