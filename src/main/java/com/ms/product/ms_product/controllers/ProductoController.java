package com.ms.product.ms_product.controllers;

import com.ms.product.ms_product.models.Producto;
import com.ms.product.ms_product.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// @AllArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

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
}
